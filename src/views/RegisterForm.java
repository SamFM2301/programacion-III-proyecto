package views;

import java.util.Iterator;
import java.awt.*;
import javax.swing.*;
import utils.AppFont;

public class RegisterForm extends JFrame{

	public RegisterForm() {
		setSize(480, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setTitle("Registro");
		setLocationRelativeTo(null);

		createComponents();
		
		setVisible(true);
	}
	
	public void createComponents() {
		JPanel panelComponents = new JPanel();
		panelComponents.setLayout(new BoxLayout(panelComponents, BoxLayout.Y_AXIS));
		panelComponents.setBorder(BorderFactory.createEmptyBorder(20, 70, 20, 70));
		panelComponents.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel lblTitle = new JLabel("Registro");
		lblTitle.setFont(AppFont.title());
		lblTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
		panelComponents.add(lblTitle);
		
		JLabel lblName = new JLabel("Ingresa tu nombre:");
		panelComponents.add(lblName);
		JTextField txtName = new JTextField();
		panelComponents.add(txtName);
		
		JLabel lblEmail = new JLabel("Ingresa tu correo:");
		panelComponents.add(lblEmail);
		JTextField txtEmail = new JTextField();
		panelComponents.add(txtEmail);
		
		JLabel lblPassword = new JLabel("Crea tu contraseña:");
		panelComponents.add(lblPassword);
		JTextField txtPassword = new JTextField();
		panelComponents.add(txtPassword);

		JButton btnRegister = new JButton("Registrar");
		btnRegister.setAlignmentX(CENTER_ALIGNMENT);
		panelComponents.add(btnRegister);
		
		JScrollPane scroll = new JScrollPane(panelComponents);
		scroll.setHorizontalScrollBar(null);
		
		add(scroll);
	}	
}
