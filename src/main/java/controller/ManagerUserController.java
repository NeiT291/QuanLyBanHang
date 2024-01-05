package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.ManagerUser;

public class ManagerUserController implements ActionListener{
	
	private ManagerUser view;
	public ManagerUserController(ManagerUser view) {
		this.view = view;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
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

}
