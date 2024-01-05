package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Login;

public class LoginController implements ActionListener{
	private Login view;
	
	public LoginController(Login view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equals("Login")) {
			view.login();
		}
		
	}
}
