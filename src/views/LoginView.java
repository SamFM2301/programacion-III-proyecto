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
		JLabel lblTitle = new JLabel("Iniciar Sesion");
		lblTitle.setBounds(140, 50, 200, 50);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
		lblTitle.setForeground(new Color(86, 174, 194));
		add(lblTitle);
		
		//txt error
		JLabel lblError = new JLabel("Correo o Contraseña incorrecta, Intenta de nuevo.");
		lblError.setBounds(90, 95, 300, 50);
		lblError.setFont(new Font("Arial", Font.PLAIN, 12));
		lblError.setForeground (Color.RED);
		add(lblError);
		
		//Imagen de error
		JLabel imgError = new JLabel();
		imgError.setBounds(65, 95, 300, 50);
		imgError.setIcon(loadIcon("../img/error.png", 20, 20));
		add(imgError);
		
		
		JLabel lblEmail = new JLabel("Usuario *");
		lblEmail.setBounds(60, 130, 350, 40);
		lblEmail.setFont(new Font("Arial", Font.BOLD, 12));
		lblEmail.setForeground(new Color(100, 156, 167));
		add(lblEmail);
		
		JTextField txtEmail = new JTextField();
		txtEmail.setBounds(60, 165, 350, 40);
		txtEmail.setFont(new Font("Arial", Font.BOLD, 18));
		add(txtEmail);
		
		JLabel lblPassword = new JLabel("Contraseña *");
		lblPassword.setBounds(60, 220, 350, 40);
		lblPassword.setFont(new Font("Arial", Font.BOLD, 12));
		lblPassword.setForeground(new Color(100, 156, 167));
		add(lblPassword);
		
		JPasswordField txtPassword = new JPasswordField();
		txtPassword.setBounds(60, 255, 350, 40);
		txtPassword.setFont(new Font("Arial", Font.BOLD, 18));
		add(txtPassword);
		
		JButton btnSesion = new JButton("Iniciar Sesion");
		createButton(btnSesion, "/error/icono.png");
		add(btnSesion);
	}
	
	private ImageIcon loadIcon(String url, int w,int h) {
		try {
			Image icono = ImageIO.read(getClass().getResource(url));
			icono = icono.getScaledInstance(w, h, Image.SCALE_SMOOTH);
			return new ImageIcon(icono);			
		}catch(Exception ex) {
			System.out.println("No está la imagen del ícono");
		}
		return null;
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
