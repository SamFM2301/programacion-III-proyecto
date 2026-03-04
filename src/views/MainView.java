package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainView extends JFrame{

	public MainView() {
		initFrame();
		initComponents();
	}
	
	private void initFrame() {
		setTitle("Ventana Principal");
		
		Toolkit tk = Toolkit.getDefaultToolkit(); 
        Image icono = tk.getImage("src/img/iniciosesion.png"); 
        setIconImage(icono);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(600, 480));
		setLocationRelativeTo(null);
	}
	
	private void initComponents() {
		JPanel mainPanel = new JPanel();
		
		JButton btnLongOut = new JButton("Cerrar Sesion");
		
		btnLongOut.addActionListener(e -> {
			int option = JOptionPane.showConfirmDialog(this, "Seguro que desea Cerrar la Sesion?");
			
			if (option == JOptionPane.YES_OPTION) {
				new LoginView();
				dispose();
			}
		});
		
		mainPanel.add(btnLongOut);
		
		add(mainPanel);
		setVisible(true);
	}
	
}
