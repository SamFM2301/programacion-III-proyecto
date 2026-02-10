package views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView extends JPanel{
	/*
	 * PALETA DE COLORES
	 * 
	 * - AZUL CLARO 
	 * HEX #56AEC2
	 * RGB 86, 174, 194
	 * 
	 * - CYAN
	 * HEX #649CA7
	 * RGB 100, 156, 167
	 * 
	 * - BLANCO OPACO
	 * HEX #E9E9E9
	 * RGB 233, 233, 233
	 * 
	 * - BLANCO CLARO 
	 * HEX #F4F4F4
	 * RGB 244, 244, 244
	 * 
	 */
	
	
	public LoginView() {
		setBackground(new Color(233, 233, 233));
		
		setLayout(null);
		createComponents();
		
	}

	
	public void createComponents() {
		JLabel title = new JLabel("Iniciar Sesion");
		title.setBounds(140, 50, 200, 50);
		title.setFont(new Font("Arial", Font.BOLD, 30));
		title.setForeground(new Color(86, 174, 194));
		add(title);
		
		JTextField userTxt = new JTextField();
		userTxt.setBounds(60, 150, 350, 40);
		userTxt.setFont(new Font("Arial", Font.BOLD, 18));
		add(userTxt);
		
		JPasswordField passwordTxt = new JPasswordField();
		passwordTxt.setBounds(60, 240, 350, 40);
		passwordTxt.setFont(new Font("Arial", Font.BOLD, 18));
		add(passwordTxt);
		
		JButton button = new JButton("Iniciar Sesion");
		button.setBounds(60, 340, 350, 40);
		add(button);
	}
}
