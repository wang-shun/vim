package com.sg.vim.print;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class PrintView1 extends ViewPart {

  private PrintPanel panel;

  public PrintView1() {
  }

  @Override
  public void createPartControl(Composite parent) {
    panel = new PrintPanel(parent);
  }

  @Override
  public void setFocus() {
    panel.setFocus();
  }

}