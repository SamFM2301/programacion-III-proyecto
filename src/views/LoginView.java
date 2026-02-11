package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
		
		JLabel user = new JLabel("Usuario *");
		user.setBounds(60, 115, 350, 40);
		user.setFont(new Font("Arial", Font.BOLD, 12));
		user.setForeground(new Color(100, 156, 167));
		add(user);
		
		JTextField userTxt = new JTextField();
		userTxt.setBounds(60, 150, 350, 40);
		userTxt.setFont(new Font("Arial", Font.BOLD, 18));
		add(userTxt);
		
		JLabel password = new JLabel("Contraseña *");
		password.setBounds(60, 205, 350, 40);
		password.setFont(new Font("Arial", Font.BOLD, 12));
		password.setForeground(new Color(100, 156, 167));
		add(password);
		
		JPasswordField passwordTxt = new JPasswordField();
		passwordTxt.setBounds(60, 240, 350, 40);
		passwordTxt.setFont(new Font("Arial", Font.BOLD, 18));
		add(passwordTxt);
		
		JButton button = new JButton("Iniciar Sesion");
		createButton(button, "/error/icono.png");
		add(button);
	}
	
	public void createButton(JButton b, String url) {
		b.setBounds(160, 340, 150, 40);
		b.setFont(new Font("Arial", Font.BOLD, 16));
		b.setBackground(new Color(86, 174, 194));
		b.setForeground(new Color(244, 244, 244));
		
		b.setOpaque(true);
		b.setBorderPainted(false);
		b.setFocusPainted(false);
		
		try {
			Image icon = ImageIO.read(getClass().getResource(url));
			icon = icon.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
			b.setIcon(new ImageIcon(icon));
		} catch(Exception ex) {
			System.out.println("No se encontro la imagen");
		}
	}
}
