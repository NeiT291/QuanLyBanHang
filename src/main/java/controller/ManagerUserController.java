package controller;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;

import view.ManagerUser;

public class ManagerUserController implements Action{
	
	private ManagerUser view;
	public ManagerUserController(ManagerUser view) {
		this.view = view;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		System.out.println(command);
		if(command.equals("Exit")) {
			view.exitFunc();
		}else if(command.equals("AddUser")) {
			view.addUser();
		}else if(command.equals("DeleteUser")) {
			view.deleteUser();
		}else if(command.equals("Reload")) {
			view.reload();
		}else if(command.equals("ModifyUser")) {
			view.modifyUser();
		}else if(command.equals("Search")) {
			view.searchUser();
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
