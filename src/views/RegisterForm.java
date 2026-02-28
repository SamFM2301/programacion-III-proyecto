package views;

import java.util.Iterator;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import components.RoundButton;
import img.ImagePanel;

import java.time.LocalDate;
import java.time.Year;

import utils.AppFont;

public class RegisterForm extends JFrame{

	public RegisterForm() {
		initFrame();
		initComponents();
	}
	
	private void initFrame() {
		setTitle("Registro de Usuario");
		
		Toolkit tk = Toolkit.getDefaultToolkit(); 
        Image icono = tk.getImage("src/img/iniciosesion.png"); 
        setIconImage(icono);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(1000, 750));
		setLocationRelativeTo(null);
	}
	
	private void initComponents() {
		JPanel mainPanel = new JPanel(new GridLayout(1, 2));
		mainPanel.setBackground(new Color(57, 94, 102));
		mainPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
		
		mainPanel.add(createFormPanel());
		mainPanel.add(createImagePanel());
		
		add(mainPanel);
		setVisible(true);
	}
	
	private JPanel createFormPanel() {
		JPanel formPanel = new JPanel();
	    formPanel.setBackground(Color.WHITE);
	    formPanel.setLayout(new GridBagLayout());

	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets(8, 10, 8, 10);
	    gbc.fill = GridBagConstraints.HORIZONTAL;

	    // TITULO
	    JLabel lblTitle = new JLabel("Registro de Usuario");
	    lblTitle.setFont(AppFont.title());
	    lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.gridwidth = 2;
	    formPanel.add(lblTitle, gbc);

	    gbc.gridwidth = 1;

	    // NOMBRE
	    gbc.gridy++;
	    formPanel.add(new JLabel("Nombre *"), gbc);

	    gbc.gridy++;
	    JTextField txtName = new JTextField(20);
	    formPanel.add(txtName, gbc);

	    // CORREO
	    gbc.gridy++;
	    formPanel.add(new JLabel("Correo *"), gbc);

	    gbc.gridy++;
	    JTextField txtEmail = new JTextField(20);
	    formPanel.add(txtEmail, gbc);

	    // FECHA
	    gbc.gridy++;
	    formPanel.add(new JLabel("Fecha Nacimiento *"), gbc);

	    gbc.gridy++;
	    JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));

	    JComboBox<Integer> cbxDay = new JComboBox<>();
	    for (int i = 1; i <= 31; i++) cbxDay.addItem(i);

	    JComboBox<Integer> cbxMonth = new JComboBox<>();
	    for (int i = 1; i <= 12; i++) cbxMonth.addItem(i);

	    JComboBox<Integer> cbxYear = new JComboBox<>();
	    int currentYear = LocalDate.now().getYear();
	    for (int i = 0; i < 100; i++) cbxYear.addItem(currentYear - i);

	    JLabel lblDay = new JLabel("Dia:");
	    datePanel.add(lblDay);
	    datePanel.add(cbxDay);
	    
	    JLabel lblMonth = new JLabel("Mes:");
	    datePanel.add(lblMonth);
	    datePanel.add(cbxMonth);
	    
	    JLabel lblYear = new JLabel("Año:");
	    datePanel.add(lblYear);
	    datePanel.add(cbxYear);

	    formPanel.add(datePanel, gbc);

	    // SEXO
	    gbc.gridy++;
	    formPanel.add(new JLabel("Sexo"), gbc);

	    gbc.gridy++;
	    JPanel sexPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));

	    JRadioButton rbMan = new JRadioButton("Hombre");
	    rbMan.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    JRadioButton rbWomen = new JRadioButton("Mujer");
	    rbWomen.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    JRadioButton rbOther = new JRadioButton("Otro");
	    rbOther.setCursor(new Cursor(Cursor.HAND_CURSOR));

	    ButtonGroup bgSex = new ButtonGroup();
	    bgSex.add(rbMan);
	    bgSex.add(rbWomen);
	    bgSex.add(rbOther);

	    sexPanel.add(rbMan);
	    sexPanel.add(rbWomen);
	    sexPanel.add(rbOther);

	    formPanel.add(sexPanel, gbc);

	    // CONTRASEÑA
	    gbc.gridy++;
	    formPanel.add(new JLabel("Contraseña *"), gbc);

	    gbc.gridy++;
	    JPasswordField txtPassword = new JPasswordField(20);
	    formPanel.add(txtPassword, gbc);

	    // CONFIRMAR
	    gbc.gridy++;
	    formPanel.add(new JLabel("Confirmar Contraseña *"), gbc);

	    gbc.gridy++;
	    JPasswordField txtConfirmPassword = new JPasswordField(20);
	    formPanel.add(txtConfirmPassword, gbc);

	    // TERMINOS
	    gbc.gridy++;
	    JCheckBox chkTerms = new JCheckBox("Acepto Términos y Condiciones");
	    chkTerms.setForeground(Color.BLUE);
	    chkTerms.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    chkTerms.setBackground(Color.WHITE);
	    formPanel.add(chkTerms, gbc);

	    // BOTON
	    gbc.gridy++;
	    gbc.anchor = GridBagConstraints.CENTER;

	    JButton btnRegister = new JButton("Registrar");
	    btnRegister.setFocusPainted(false);
	    btnRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    btnRegister.setPreferredSize(new Dimension(150, 40));

	    formPanel.add(btnRegister, gbc);

	    return formPanel;
	}
	
	private JPanel createImagePanel() {

		ImagePanel imagePanel = new ImagePanel("/img/welcome.png");
	    imagePanel.setLayout(new BorderLayout());
	    imagePanel.setPreferredSize(new Dimension(400, 0));

	    return imagePanel;
    }
}