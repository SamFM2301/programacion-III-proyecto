package main;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import utils.AppFont;
import views.LoginWindow;
import views.RegisterForm;

public class Main {

	public static void main(String[] args) {
		
		//FlatLightLaf.setup(); 
		
		UIManager.put("Label.font", AppFont.normal());
		UIManager.put("TextField.font", AppFont.normal());
		UIManager.put("Button.font", AppFont.normal());
		
		//LoginWindow loginWindow = new LoginWindow(); 
		RegisterForm registerWindow = new RegisterForm();
		
	}

}
