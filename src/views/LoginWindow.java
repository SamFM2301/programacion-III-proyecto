package views;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class LoginWindow extends JFrame{

	public LoginWindow() {
		setSize(720, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("Inicio de sesion");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image myIcon = tk.getImage("src/img/icono.png");
		setIconImage(myIcon);
		
		LoginView loginView = new LoginView();
		add(loginView);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
}
