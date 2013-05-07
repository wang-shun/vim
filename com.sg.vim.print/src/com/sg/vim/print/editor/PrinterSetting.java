package com.sg.vim.print.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.util.Util;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.FocusCellOwnerDrawHighlighter;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TableViewerEditor;
import org.eclipse.jface.viewers.TableViewerFocusCellManager;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import com.mobnut.commons.util.Utils;
import com.mobnut.db.DBActivator;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sg.vim.datamodel.util.VimUtils;

public class PrinterSetting extends EditorPart {

    public static final String[] functionsNameList = new String[] { "打印合格证", "打印车辆一致性证书", "打印燃油标识" };

    public class ComboEditingSpport extends StringEditingSpport {

        protected ComboEditingSpport(String properties) {
            super(properties, true);

        }

        @Override
        protected CellEditor getEditor() {
            ComboBoxCellEditor editor = new ComboBoxCellEditor(viewer.getTable(),
                    functionsNameList, SWT.READ_ONLY);
            return editor;
        }

        @Override
        protected Object getValue(Object element) {
            PrinterItem printerItem = (PrinterItem) element;
            String text = (String) printerItem.getValue(properties);
            for (int i = 0; i < functionsNameList.length; i++) {
                if (functionsNameList[i].equals(text)) {
                    return i;
                }
            }
            return 0;
        }

        @Override
        protected void setValue(Object element, Object value) {
            int i = ((Integer) value).intValue();
            super.setValue(element, functionsNameList[i]);
        }
    }

    protected class StringIntegerEditingSupport extends StringEditingSpport {

        public StringIntegerEditingSupport(String properties, boolean noEmpty) {
            super(properties, noEmpty);

        }

        protected CellEditor getEditor() {
            TextCellEditor editor = new TextCellEditor(viewer.getTable());
            editor.setValidator(new ICellEditorValidator() {

                @Override
                public String isValid(Object value) {
                    if (noEmpty && Utils.isNullOrEmptyString(value)) {
                        return "您需要为属性设置值";
                    }
                    if ((value instanceof String) && (Utils.isNumbers((String) value))) {
                        return null;
                    } else {
                        return "您需要输入数字";
                    }
                }
            });

            return editor;
        }

    }

    protected class StringEditingSpport extends EditingSupport {

        protected String properties;
        private CellEditor editor;
        protected boolean noEmpty;

        public StringEditingSpport(String properties, boolean noEmpty) {
            super(viewer);
            this.properties = properties;
            this.noEmpty = noEmpty;
            editor = getEditor();

        }

        protected CellEditor getEditor() {
            TextCellEditor editor = new TextCellEditor(viewer.getTable());
            if (noEmpty) {
                editor.setValidator(new ICellEditorValidator() {

                    @Override
                    public String isValid(Object value) {
                        if (Utils.isNullOrEmptyString(value)) {
                            return "您需要为属性设置值";
                        } else {
                            return null;
                        }
                    }
                });
            }
            return editor;
        }

        @Override
        protected CellEditor getCellEditor(Object element) {
            return editor;
        }

        @Override
        protected boolean canEdit(Object element) {
            return !(element instanceof AddActionPrintItem);
        }

        @Override
        protected Object getValue(Object element) {
            PrinterItem printerItem = (PrinterItem) element;
            return printerItem.getValue(properties);
        }

        @Override
        protected void setValue(Object element, Object value) {
            PrinterItem printerItem = (PrinterItem) element;
            printerItem.setValue(properties, value);
            viewer.update(printerItem, null);
        }

    }

    private static final class EditorActivationStrategy extends
            ColumnViewerEditorActivationStrategy {

        private EditorActivationStrategy(ColumnViewer viewer) {
            super(viewer);

            setEnableEditorActivationWithKeyboard(true);
        }

        @Override
        protected boolean isEditorActivationEvent(ColumnViewerEditorActivationEvent event) {
            // boolean result;
            if (event.eventType == ColumnViewerEditorActivationEvent.MOUSE_DOUBLE_CLICK_SELECTION) {
                return true;
            } else if (event.character == '\r') {
                return true;
            } else {
                return false;
            }

            // if (event.character == '\r') {
            // result = true;
            // } else {
            // result = super.isEditorActivationEvent(event);
            // }
            // return result;
        }
    }

    public class AddActionPrintItem extends PrinterItem {

        public AddActionPrintItem() {
        }

        public String getText(String parameter) {
            if (VimUtils.mVeh_PrinterName.equals(parameter)) {
                return "<a href=\"create\" target=\"_rwt\">注册新打印机</a>";
            } else {
                return "";
            }
        }

    }

    public class PrinterItem {

        private DBObject data;

        public PrinterItem(DBObject next) {
            data = next;
        }

        public void setValue(String properties, Object value) {
            Object oldValue = data.get(properties);
            if (!Util.equals(oldValue, value)) {
                data.put(properties, value);
                isDirty = true;
                firePropertyChange(PROP_DIRTY);
            }
        }

        public PrinterItem() {
        }

        public Object getValue(String parameter) {
            Object value = data.get(parameter);
            if (value == null) {
                return "";
            } else {
                return value;
            }
        }

        public String getText(String parameter) {
            int index = input.indexOf(this);
            String text = (String) getValue(parameter);
            if (parameter.equals(VimUtils.mVeh_PrinterName)) {
                if (!Utils.isNullOrEmpty(text)) {
                    text = text + "     <a href=\"remove@" + index + "\" target=\"_rwt\">删除</a>";
                }
            }
            return text;
        }

    }

    private TableViewer viewer;
    private List<PrinterItem> input;
    private DBCollection collection;
    private boolean isDirty;

    public PrinterSetting() {
    }

    @Override
    public void doSave(IProgressMonitor monitor) {
        ArrayList<DBObject> dataList = new ArrayList<DBObject>();
        for (int i = 0; i < input.size(); i++) {
            PrinterItem item = input.get(i);
            if (item instanceof AddActionPrintItem) {
                continue;
            }
            dataList.add(item.data);
        }

        collection.remove(new BasicDBObject());
        collection.insert(dataList);
        isDirty = false;
        firePropertyChange(PROP_DIRTY);
    }

    @Override
    public void doSaveAs() {
    }

    @Override
    public void init(IEditorSite site, IEditorInput input) throws PartInitException {
        setSite(site);
        setInput(input);
        collection = DBActivator.getCollection("appportal", "printers");

    }

    @Override
    public boolean isDirty() {
        return isDirty;
    }

    @Override
    public boolean isSaveAsAllowed() {
        return false;
    }

    @Override
    public void createPartControl(Composite parent) {
        // 查询出系统打印机列表，并且提供增、删、改的功能
        viewer = new TableViewer(parent, SWT.BORDER);
        viewer.getTable().setData(RWT.MARKUP_ENABLED, Boolean.TRUE);
        viewer.getTable().setLinesVisible(true);
        viewer.getTable().setHeaderVisible(true);
        viewer.getTable().addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (e.detail == RWT.HYPERLINK) {
                    if (e.text.equals("create")) {
                        DBObject data = new BasicDBObject();
                        data.put(VimUtils.mVeh_PrintPosLeft, "15");
                        data.put(VimUtils.mVeh_PrintPosTop, "15");
                        data.put(VimUtils.mVeh_Connect, "COM1");
                        data.put(VimUtils.mVeh_Baud, "9600");
                        data.put(VimUtils.mVeh_Parity, "N");
                        data.put(VimUtils.mVeh_Databits, "8");
                        data.put(VimUtils.mVeh_Stopbits, "1");
                        input.add(input.size() - 1, new PrinterItem(data));
                        viewer.refresh();
                        isDirty = true;
                        firePropertyChange(PROP_DIRTY);
                    } else if (e.text.startsWith("remove@")) {
                        int index = Integer.parseInt(e.text.split("@")[1]);
                        input.remove(index);
                        viewer.refresh();
                        isDirty = true;
                        firePropertyChange(PROP_DIRTY);
                    }
                }
            }
        });
        // *********************************设置表格可以编辑*********************************
        ColumnViewerEditorActivationStrategy activationStrategy = new EditorActivationStrategy(
                viewer);
        FocusCellOwnerDrawHighlighter highlighter = new FocusCellOwnerDrawHighlighter(viewer);
        TableViewerFocusCellManager focusManager = new TableViewerFocusCellManager(viewer,
                highlighter);
        int feature = ColumnViewerEditor.TABBING_HORIZONTAL
                | ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR;
        TableViewerEditor.create(viewer, focusManager, activationStrategy, feature);
        // *********************************************************************************

        TableViewerColumn col = createEditableColumn("打印机名", VimUtils.mVeh_PrinterName, 180);
        col.setEditingSupport(new StringEditingSpport(VimUtils.mVeh_PrinterName, true));

        col = createEditableColumn("功能", VimUtils.mVeh_A_PrinterFunction, 180);
        col.setEditingSupport(new ComboEditingSpport(VimUtils.mVeh_A_PrinterFunction));

        col = createEditableColumn("说明", VimUtils.mVeh_A_PrinterDesc, 180);
        col.setEditingSupport(new StringEditingSpport(VimUtils.mVeh_A_PrinterDesc, false));

        col = createEditableColumn("合格证\n左边距", VimUtils.mVeh_PrintPosLeft, 60);
        col.setEditingSupport(new StringIntegerEditingSupport(VimUtils.mVeh_PrintPosLeft, true));

        col = createEditableColumn("合格证\n上边距", VimUtils.mVeh_PrintPosTop, 60);
        col.setEditingSupport(new StringIntegerEditingSupport(VimUtils.mVeh_PrintPosTop, true));

        col = createEditableColumn("连接", VimUtils.mVeh_Connect, 60);
        col.setEditingSupport(new StringIntegerEditingSupport(VimUtils.mVeh_Connect, true));

        col = createEditableColumn("波特率", VimUtils.mVeh_Baud, 60);
        col.setEditingSupport(new StringIntegerEditingSupport(VimUtils.mVeh_Baud, true));

        col = createEditableColumn("奇偶\n校验", VimUtils.mVeh_Parity, 60);
        col.setEditingSupport(new StringIntegerEditingSupport(VimUtils.mVeh_Parity, true));

        col = createEditableColumn("数据位", VimUtils.mVeh_Databits, 60);
        col.setEditingSupport(new StringIntegerEditingSupport(VimUtils.mVeh_Databits, true));

        col = createEditableColumn("停止位", VimUtils.mVeh_Stopbits, 60);
        col.setEditingSupport(new StringIntegerEditingSupport(VimUtils.mVeh_Stopbits, true));

        viewer.setContentProvider(ArrayContentProvider.getInstance());

        input = new ArrayList<PrinterItem>();
        DBCursor cur = collection.find();
        while (cur.hasNext()) {
            PrinterItem printerItem = new PrinterItem(cur.next());
            input.add(printerItem);
        }
        input.add(new AddActionPrintItem());

        viewer.setInput(input);
    }

    private TableViewerColumn createEditableColumn(String text, final String properties, int width) {
        TableViewerColumn col = new TableViewerColumn(viewer, SWT.LEFT);
        col.getColumn().setText(text);
        col.getColumn().setWidth(width);
        col.getColumn().setResizable(true);
        col.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                PrinterItem printerItem = (PrinterItem) element;
                return printerItem.getText(properties);
            }
        });

        return col;
    }

    @Override
    public void setFocus() {

    }

}
