package views;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

import components.TextPrompt;

public class LoginView extends JPanel{
	
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JLabel lblError;

    public LoginView() {
        setBackground(new Color(233, 233, 233));
        setLayout(new GridBagLayout()); //lo usamos solo para centrar el panel del login en el centro de la ventana
        createComponents();
    }
	
    private void createComponents() {
    	//separar contenedor externo y layout interno
        JPanel panel = new JPanel();
        panel.setBackground(new Color(244, 244, 244));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(30, 40, 30, 40));

        // TÍTULO
        JLabel lblTitle = new JLabel("Iniciar Sesión");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitle.setForeground(new Color(86, 174, 194));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblTitle);

        //Crea un espacio para generar separaciones 
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        // ERROR
        lblError = new JLabel("Correo o contraseña incorrecta");
        lblError.setFont(new Font("Arial", Font.PLAIN, 12));
        lblError.setForeground(Color.RED);
        lblError.setVisible(false);//pone la visibilidad del mensaje de error en falso
        lblError.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblError);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // EMAIL
        JLabel lblEmail = new JLabel("Usuario *");
        lblEmail.setFont(new Font("Arial", Font.BOLD, 12));
        lblEmail.setForeground(new Color(100, 156, 167));
        lblEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblEmail);

        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        txtEmail = new JTextField();
        styleTextField(txtEmail);
        panel.add(txtEmail);
        //se agrega un placeholder encima de las etiquetas de texto
        new TextPrompt("Ingresa tu correo", txtEmail);

        //
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        // PASSWORD
        JLabel lblPassword = new JLabel("Contraseña *");
        lblPassword.setFont(new Font("Arial", Font.BOLD, 12));
        lblPassword.setForeground(new Color(100, 156, 167));
        lblPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblPassword);

        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        txtPassword = new JPasswordField();
        styleTextField(txtPassword);
        panel.add(txtPassword);

        new TextPrompt("Ingresa tu contraseña", txtPassword);

        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        // BOTÓN
        JButton btnSesion = new JButton("Iniciar Sesión");
        btnSesion.setBackground(new Color(86, 174, 194));
        btnSesion.setForeground(Color.WHITE);
        btnSesion.setFont(new Font("Arial", Font.BOLD, 14));
        btnSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSesion.setMaximumSize(new Dimension(150, 100));
        panel.add(btnSesion);

        add(panel); // Lo centramos con GridBagLayout
    }
	
	//Define los espacios maximos que pueden ocupar los TextFIeld y configurar los estilos que queramos
    private void styleTextField(JTextField field) {
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setMaximumSize(new Dimension(250, 35));
        field.setPreferredSize(new Dimension(250, 35));
        field.setBorder(new LineBorder(new Color(200, 200, 200), 1));
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
