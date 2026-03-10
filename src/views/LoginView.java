package views;

import javax.swing.*;
import components.RoundButton;
import components.TextPrompt;
import img.ImagePanel;
import utils.AppFont;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LoginView extends JFrame {

	private JLabel lblErrorUsuario;
	private JLabel lblErrorPassword;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    
    private Color defualtButtonColor;

    public LoginView() {
        setTitle("Login");
        setSize(750, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Toolkit tk = Toolkit.getDefaultToolkit(); 
        Image icono = tk.getImage("src/img/iniciosesion.png"); 
        setIconImage(icono);  
        
        // Panel principal (fondo)
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(57, 94, 102));

        // Panel contenedor dividido en 2 partes
        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.setBackground(Color.WHITE);
        
        // PANEL IZQUIERDO (IMAGEN)
        ImagePanel imagePanel = new ImagePanel("/img/welcome.png");
        imagePanel.setPreferredSize(new Dimension(300, 0));
        containerPanel.add(imagePanel, BorderLayout.WEST);

        // PANEL DERECHO (FORMULARIO)   
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(56, 125, 122));
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.insets = new Insets(8, 5, 8, 5);
        gridConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridConstraints.gridx = 0; 
        
        // TÍTULO
        JLabel lblTitulo = new JLabel("INICIAR SESIÓN", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(Color.WHITE);

        gridConstraints.gridy = 0;
        formPanel.add(lblTitulo, gridConstraints);

        // USUARIO
        gridConstraints.gridy = 1;
        JLabel lblUsuario = new JLabel("Usuario*");
        lblUsuario.setForeground(Color.WHITE);
        formPanel.add(lblUsuario, gridConstraints);

        gridConstraints.gridy = 2;
        txtUsuario = new JTextField(15);
        formPanel.add(txtUsuario, gridConstraints);

        TextPrompt usuarioPlaceholder = new TextPrompt("Ingresa tu usuario", txtUsuario);
        usuarioPlaceholder.changeAlpha(0.6f);
        
        gridConstraints.gridy = 3;
        lblErrorUsuario = new JLabel("");
        lblErrorUsuario.setFont(AppFont.small());
        lblErrorUsuario.setForeground(Color.RED);
        lblErrorUsuario.setHorizontalAlignment(SwingConstants.LEFT);
        lblErrorUsuario.setVisible(true); // luego lo puedes poner en false
        formPanel.add(lblErrorUsuario, gridConstraints);
        
        // CONTRASEÑA
        gridConstraints.gridy = 4;
        JLabel lblPass = new JLabel("Contraseña*");
        lblPass.setForeground(Color.WHITE);
        formPanel.add(lblPass, gridConstraints);

        gridConstraints.gridy = 5;
        txtPassword = new JPasswordField(15);
        formPanel.add(txtPassword, gridConstraints);

        TextPrompt passPlaceholder = new TextPrompt("Ingresa tu contraseña", txtPassword);
        passPlaceholder.changeAlpha(0.6f);
   
        // MENSAJE ERROR
        gridConstraints.gridy = 6;
        lblErrorPassword = new JLabel("");
        lblErrorPassword.setFont(AppFont.small());
        lblErrorPassword.setForeground(Color.RED);
        lblErrorPassword.setHorizontalAlignment(SwingConstants.LEFT);
        lblErrorPassword.setVisible(true); // luego lo puedes ocultar
        formPanel.add(lblErrorPassword, gridConstraints);

        // BOTONES
        gridConstraints.gridy = 7;
        gridConstraints.insets = new Insets(20, 5, 5, 5);

        JPanel panelBotones = new JPanel();
        panelBotones.setOpaque(false);


        RoundButton btnIniciar = new RoundButton("Iniciar");
        btnIniciar.setPreferredSize(new Dimension(120, 40));
        btnIniciar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        defualtButtonColor = btnIniciar.getBackground();

        btnIniciar.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnIniciar.setBackground(new Color(150, 150, 150));
			}
			
			public void mouseExited(MouseEvent e) {
				btnIniciar.setBackground(defualtButtonColor);
			}
        	
        });
        
        btnIniciar.addActionListener(e -> {

            if (validateFields()) {
            	showMessageLoginSuccesful();
                resetErrorMsg();
                resetFields();
                
                new MainView();
                dispose();
            }

        });

        RoundButton btnRegistrar = new RoundButton("Registrar");
        btnRegistrar.setPreferredSize(new Dimension(120, 40));
        btnRegistrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        panelBotones.add(btnIniciar);
        panelBotones.add(btnRegistrar);

        formPanel.add(panelBotones, gridConstraints);
        
        btnRegistrar.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnRegistrar.setBackground(new Color(150, 150, 150));
			}
			
			public void mouseExited(MouseEvent e) {
				btnRegistrar.setBackground(defualtButtonColor);
			}
        	
        });
        
        btnRegistrar.addActionListener(e -> handleRegistration());

        containerPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(containerPanel);
        add(mainPanel);
        
        setVisible(true);
    }
    
    public boolean validateFields() {
    	boolean isValid = true;
    	
    	String userName = txtUsuario.getText().trim();
    	String password = new String(txtPassword.getPassword()).trim();
    	
    	if (userName.isEmpty()) {
    		lblErrorUsuario.setText("El correo es obligatorio");
    		lblErrorUsuario.setVisible(true);
    		isValid = false;
    	} else {
    		lblErrorUsuario.setText("");
    	}
    	
    	if (password.isEmpty()) {
    		lblErrorPassword.setText("Contraseña es obligatoria");
    		lblErrorPassword.setVisible(true);
    		isValid = false;
    	} else {
    		lblErrorPassword.setText("");
    	}
    	
    	return isValid;
    }
    
    public void resetErrorMsg() {
    	lblErrorUsuario.setText("");
    	lblErrorPassword.setText("");
    }
    
    public void resetFields() {
    	txtUsuario.setText("");
    	txtPassword.setText("");
    }
    
    public void showMessageLoginSuccesful() {
    	JOptionPane.showMessageDialog(
			this, 
			"Se inicio sesion", 
			"Sesion iniciada.",
			JOptionPane.INFORMATION_MESSAGE
    	);
    }
    
    public void handleRegistration() {
    	new RegisterForm();
    	dispose();
    }
}














