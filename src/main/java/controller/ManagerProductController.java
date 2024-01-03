package controller;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;

import view.ManagerProduct;

public class ManagerProductController implements Action{
	
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
