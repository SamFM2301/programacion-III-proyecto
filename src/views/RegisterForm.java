package views;

import java.util.Iterator;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import utils.AppFont;

public class RegisterForm extends JFrame{

	public RegisterForm() {
		setSize(560, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setTitle("Registro");
		setLocationRelativeTo(null);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image myIcon = tk.getImage("src/img/iniciosesion.png");
		setIconImage(myIcon);
		
		createComponents();
		
		setVisible(true);
	}
	
	public void createComponents() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
		
		JLabel lblTitle = new JLabel("Registro de Usuario");
		lblTitle.setFont(AppFont.title());
		mainPanel.add(lblTitle, BorderLayout.NORTH);
		
		
		JButton btnRegistrar = new JButton("Registrar");
		mainPanel.add(btnRegistrar, BorderLayout.SOUTH);
		
		add(mainPanel);
	}	
}














