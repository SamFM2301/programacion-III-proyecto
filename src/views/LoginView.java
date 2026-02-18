package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import components.TextPrompt;

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
		setLayout(new BorderLayout());
		//setBackground(Color.BLUE);
		Border emptyBorder = BorderFactory.createEmptyBorder(20,10,20,10);
		setBorder(emptyBorder);
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(233, 233, 233));
		add(topPanel, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("Iniciar Sesion");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
		lblTitle.setForeground(new Color(86, 174, 194));
		topPanel.add(lblTitle);
		
		crearPanelCentro();
		createComponents();
	}
	
	public void crearPanelCentro() {
		/*
		JPanel panelCentro = new JPanel(new BorderLayout());
		panelCentro.setBackground(Color.RED);
		
		JPanel panelCentroSur = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelCentro.add(panelCentroSur, BorderLayout.SOUTH);
		panelCentroSur.setBackground(Color.ORANGE);
		
		JButton btnInicio = new JButton("Iniciar sesión");
		panelCentroSur.add(btnInicio);
		
		JButton btnCancelar = new JButton("Cancelar");
		panelCentroSur.add(btnCancelar);
		
		add(panelCentro, BorderLayout.CENTER);
		*/
		
		JPanel centralPanel = new JPanel(new BorderLayout());
		centralPanel.setBackground(new Color(233, 233, 233));
		
		JLabel lblError = new JLabel("Correo o Contraseña incorrecta, Intenta de nuevo.");
		lblError.setFont(new Font("Arial", Font.PLAIN, 12));
		lblError.setForeground (Color.RED);
		centralPanel.add(lblError);
		
		JLabel lblEmail = new JLabel("Usuario *");
		lblEmail.setFont(new Font("Arial", Font.BOLD, 12));
		lblEmail.setForeground(new Color(100, 156, 167));
		centralPanel.add(lblEmail);
		
		JTextField txtEmail = new JTextField();
		txtEmail.setFont(new Font("Arial", Font.BOLD, 18));
		centralPanel.add(txtEmail);
		
		JLabel lblPassword = new JLabel("Contraseña *");
		lblPassword.setFont(new Font("Arial", Font.BOLD, 12));
		lblPassword.setForeground(new Color(100, 156, 167));
		centralPanel.add(lblPassword);
		
		JPasswordField txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Arial", Font.BOLD, 18));
		centralPanel.add(txtPassword);
		
		JButton btnSesion = new JButton("Iniciar Sesion");
		centralPanel.add(btnSesion);
	}
	
	
	public void createComponents() {
		createLabels();
		createTextFields();
		createButtons();
	}
	
	private void createLabels() {
		
	}
	
	private void createTextFields() {
		
	}
	
	private void createButtons() {
		
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
}
