package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.ManagerProduct;

public class ManagerProductController implements ActionListener{
	
	private ManagerProduct view;
	public ManagerProductController(ManagerProduct view) {
		this.view = view;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equals("Exit")) {
			view.exitFunc();
		}else if(command.equals("AddProduct")) {
			view.addProduct();
		}else if(command.equals("Reload")) {
			view.reload();
		}else if(command.equals("ModifyProduct")) {
			view.modifyProduct();
		}else if(command.equals("Search")) {
			view.searchProduct();
		}
	}
}
