package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Main;

public class MainController implements ActionListener{
	private Main view;
	
	public MainController(Main view) {
		this.view = view;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equals("Exit")) {
			view.exitProgram();
		}else if(command.equals("Logout")) {
			view.logOut();
		}else if(command.equals("AddProductToTable")) {
			view.addProductToTable();
		}else if(command.equals("ModifyProductInTable")) {
			view.modifyProductInTable();
		}else if(command.equals("DeleteProductInTable")) {
			view.deleteProductInTable();
		}else if(command.equals("CalculatePrice")) {
			view.calculatePrice();
		}else if(command.equals("ManagerUser")) {
			view.managerUser();
		}else if(command.equals("ManagerProduct")) {
			view.managerProduct();
		}else if(command.equals("ImportProduct")) {
			view.importProduct();
		}else if(command.equals("Pay")) {
			view.pay();
		}else if(command.equals("TransferBank")) {
			view.transferBank();
		}
		
	}
}
