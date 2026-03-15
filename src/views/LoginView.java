package views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import components.*;
import img.ImagePanel;
import utils.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LoginView extends JFrame {

	private RoundedTextField txtEmail;
	private RoundedPasswordField txtPassword;
	
	private JLabel lblErrorEmail;
	private JLabel lblErrorPassword;
	
	public LoginView() {
		initFrame();
		initComponents();
	}
	
	private void initFrame() {
		setTitle("Login In");
		
		Toolkit tk = Toolkit.getDefaultToolkit(); 
        Image icon = tk.getImage("src/img/iniciosesion.png"); 
        setIconImage(icon);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(840, 480));
		setResizable(true);
		setLocationRelativeTo(null);
	}
	
	private void initComponents() {
		JPanel mainPanel = new JPanel(new GridLayout(1, 2));
		mainPanel.setBackground(AppColors.BACKGROUND);
		mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
		
		mainPanel.add(createImagePanel("src/img/background.jpg"));
		mainPanel.add(createLoginPanel());
		
		add(mainPanel);
		setVisible(true);
	}
	
	private JPanel createImagePanel(String imagePath) {
	    ImageIcon icon = new ImageIcon(imagePath);
	    Image image = icon.getImage();

	    JPanel panel = new JPanel() {
	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);

	            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	        }
	    };

	    panel.setPreferredSize(new Dimension(390, 420));

	    return panel;
	}
	
	private JPanel createLoginPanel() {
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
		loginPanel.setBackground(AppColors.PANEL);
		loginPanel.setBorder(new EmptyBorder(35, 55, 35, 55));
		loginPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
		
		// PANELS
		JLabel lblTitle = new JLabel("Inicio de Sesion");
		lblTitle.setFont(AppFonts.title());
		lblTitle.setForeground(AppColors.TEXT_LIGHT);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setMaximumSize(new Dimension(Integer.MAX_VALUE, lblTitle.getPreferredSize().height));
		
		JLabel lblEmail = new JLabel("Correo: *");
		txtEmail = new RoundedTextField(8);
		lblErrorEmail = new JLabel(" ");
		
		JLabel lblPassword = new JLabel("Contraseña: *");
		txtPassword = new RoundedPasswordField(8);
		lblErrorPassword = new JLabel(" ");
		
		// LOGIN BUTTON
		JButton btnLoginIn = createButton(
			"Inciar Sesion", 
			AppColors.YELLOW, 
			AppColors.TEXT_DARK,
			15
		);
		
		btnLoginIn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				changeBackground(btnLoginIn);
			}
			
			public void mouseExited(MouseEvent e) {
				resetBackground(btnLoginIn);
			}
		});
		
		btnLoginIn.addActionListener(e -> buttonValidate());
		
		// REGISTER BUTTON
		JButton btnRegister = createButton(
			"No tienes una cuenta? Registrate aqui", 
			AppColors.FIELDS, 
			AppColors.TEXT_LIGHT,
			11
		);
		
		
		btnRegister.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				changeBackground(btnRegister);
			}
			
			public void mouseExited(MouseEvent e) {
				resetBackground(btnRegister);
			}
		});
		
		btnRegister.addActionListener(e -> handleRegistration());
		
		// ADD
		loginPanel.add(lblTitle);
		loginPanel.add(Box.createVerticalStrut(10));
		
		loginPanel.add(createPanelField(txtEmail, lblEmail, lblErrorEmail));
		loginPanel.add(Box.createVerticalStrut(20));
		
		loginPanel.add(createPanelField(txtPassword, lblPassword, lblErrorPassword));
		loginPanel.add(Box.createVerticalStrut(30));
		
		loginPanel.add(btnLoginIn);
		loginPanel.add(Box.createVerticalStrut(15));
		loginPanel.add(btnRegister);
		
		return loginPanel;
	}
	
	private void changeBackground(JComponent c) {
		Color color = c.getBackground();
		
		if (color == AppColors.YELLOW)
			c.setBackground(AppColors.YELLOW_HOVER);
		else 
			c.setBackground(AppColors.FIELDS_HOVER);
	}
	
	private void resetBackground(JComponent c) {
		Color color = c.getBackground();
		
		if (color == AppColors.YELLOW_HOVER)
			c.setBackground(AppColors.YELLOW);
		else 
			c.setBackground(AppColors.FIELDS);
	}
	
	private JPanel createPanelField(JComponent field, JLabel label, JLabel error) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setOpaque(false);
		panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
		
		label.setFont(AppFonts.bold(12));
		label.setForeground(AppColors.TEXT_LIGHT);
		label.setAlignmentX(LEFT_ALIGNMENT);
		
		field.setBackground(AppColors.FIELDS);
		field.setForeground(AppColors.TEXT_LIGHT);
		field.setBorder(new EmptyBorder(5, 6, 5, 6));
		field.setFont(AppFonts.bold(12));
		field.setAlignmentX(LEFT_ALIGNMENT);
		field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
		
		panel.add(Box.createVerticalStrut(5));
		error.setFont(AppFonts.small());
		error.setForeground(Color.RED);
		error.setText(" ");
		error.setAlignmentX(LEFT_ALIGNMENT);
		
		panel.add(label);
		panel.add(field);
		panel.add(Box.createVerticalStrut(3));
		panel.add(error);
		
		return panel;
	}
	
	private JButton createButton(String text, Color background, Color textColor, int textSize) {
		RoundedButton button = new RoundedButton(text, 8);
		
		button.setFont(AppFonts.bold(textSize));
		button.setBackground(background);
		button.setForeground(textColor);
		button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
		
		return button;
	}
	
	private void buttonValidate() {
		if (isValidFields()) {
			showMessageLoginSuccesful();
            resetErrorMsg();
            resetFields();
            
            new MainView();
            dispose();
		}
	}
	
	private boolean isValidFields() {
		boolean isValid = true;
		
		String textEmail = txtEmail.getText().trim();
		String textPassword = new String(txtPassword.getPassword()).trim();
		
		if (textEmail.isEmpty()) {
    		lblErrorEmail.setText("El campo correo es obligatorio");
    		lblErrorEmail.setVisible(true);
    		isValid = false;
    	} else {
    		lblErrorEmail.setText(" ");
    	}
    	
    	if (textPassword.isEmpty()) {
    		lblErrorPassword.setText("El campo contraseña es obligatorio");
    		lblErrorPassword.setVisible(true);
    		isValid = false;
    	} else {
    		lblErrorPassword.setText(" ");
    	}

		return isValid;
	}
	
	public void resetErrorMsg() {
    	lblErrorEmail.setText(" ");
    	lblErrorPassword.setText(" ");
    }
    
    public void resetFields() {
    	txtEmail.setText("");
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














