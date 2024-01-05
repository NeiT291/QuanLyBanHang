package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.ImportProductView;

public class ImportProductController implements ActionListener{
	
	private ImportProductView view;
	public ImportProductController(ImportProductView view) {
		this.view = view;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equals("Exit")) {
			view.exitFunc();
		}else if(command.equals("AddProduct")) {
			view.addProduct();
		}else if(command.equals("ModifyProduct")) {
			view.modifyProduct();
		}else if(command.equals("DeleteProduct")) {
			view.deleteProduct();
		}else if(command.equals("ImportProduct")) {
			view.importProduct();
		}
	}

}
