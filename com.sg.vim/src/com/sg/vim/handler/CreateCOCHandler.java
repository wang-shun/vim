package com.sg.vim.handler;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sg.ui.model.text.Enumerate;
import com.sg.ui.part.ICreateHandler;
import com.sg.ui.part.editor.IEditorSaveHandler;
import com.sg.ui.part.editor.field.actions.FilteredOptionsSelector;
import com.sg.vim.VimUtils;
import com.sg.vim.model.BasicInfo;
import com.sg.vim.model.IVIMFields;

public class CreateCOCHandler implements ICreateHandler {

	private BasicInfo service;

	public CreateCOCHandler() {
		service = new BasicInfo();
	}

	@Override
	public void create(IStructuredSelection selection, String editorId,
			IEditorSaveHandler saveHandler) {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		// 显示选择公告车型的对话框
		try {
			DBCursor cur;
			cur = service.find(null, null);
			Enumerate[] selectionList = new Enumerate[cur.count()];
			int i = 0;
			while (cur.hasNext()) {
				DBObject data = cur.next();
				String desc = (String) data.get(IVIMFields.F_0_2C1);
				selectionList[i] = new Enumerate(data.get("_id").toString(),
						desc.toString(), data, null);
				i++;
			}

			Enumerate option = FilteredOptionsSelector.openSelector(shell, "请选择公告车型",
					selectionList);

			if (option != null) {
				DBObject src = (DBObject) option.getValue();
				VimUtils.copyCreateCOC(src);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
