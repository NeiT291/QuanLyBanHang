package controller;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;

import view.Main;

public class MainController implements Action{
	private Main view;
	
	public MainController(Main view) {
		this.view = view;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		System.out.println(command);
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
		}else if(command.equals("AddUser")) {
			view.addUser();
		}else if(command.equals("Pay")) {
			view.pay();
		}
		
	}

	@Override
	public Object getValue(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putValue(String key, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEnabled(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

}
