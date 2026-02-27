package main;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import utils.AppFont;
import views.LoginWindow;
import views.RegisterForm;

public class Main {
	
	/*
	 * PALETA DE COLORES
	 * 
	 * - Dark Slate Grey
	 * 57, 94, 102
	 * 
	 * - Pine Blue
	 * 56, 125, 122
	 * 
	 * - Rosy Granite
	 * 154, 143, 151
	 * 
	 * - Silver
	 * 195, 186, 186
	 * 
	 * - Frosted Blue
	 * 144, 224, 239
	 * 
	 */

	public static void main(String[] args) {
		
		//FlatLightLaf.setup(); 
		
		UIManager.put("Label.font", AppFont.normal());
		UIManager.put("TextField.font", AppFont.normal());
		UIManager.put("Button.font", AppFont.normal());
		
		LoginWindow loginWindow = new LoginWindow(); 
		//RegisterForm registerWindow = new RegisterForm();
		
	}

}
