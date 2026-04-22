package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.time.LocalDate;

import components.*;
import controllers.LoginController;
import models.UserModel;
import utils.*;

public class AddUserView extends JFrame {

    // Componentes de la vista
    private RoundedTextField txtName;
    private RoundedTextField txtEmail;
    
    private JComboBox<Integer> cbxDay;
    private JComboBox<Integer> cbxMonth;
    private JComboBox<Integer> cbxYear;
    
    private JRadioButton rbMan;
    private JRadioButton rbWomen;
    private JRadioButton rbOther;
    private ButtonGroup bgSex;
    
    private JCheckBox chkTerms;
    
    private JLabel lblErrorName;
    private JLabel lblErrorEmail;
    private JLabel lblErrorDate;
    private JLabel lblErrorGender;
    private JLabel lblErrorTerms;
    
    // Listeners para MVC
    private ActionListener registerListener;
    private ActionListener backToLoginListener;
    
    public AddUserView() {
        initFrame();
        initComponents();
    }
    
    private void initFrame() {
        setTitle("Registro de Usuario");
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image icon = tk.getImage("src/assets/img/iniciosesion.png");
        setIconImage(icon);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(500, 500));
        setResizable(true);
        setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        JPanel mainPanel = new JPanel(new GridLayout(1, 1));
        mainPanel.setBackground(AppColors.BACKGROUND);
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        
        mainPanel.add(createRegisterPanel());
        
        add(mainPanel);
        //setVisible(true);
    }
    
    private JPanel createRegisterPanel() {
        
        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));
        registerPanel.setBackground(AppColors.PANEL);
        registerPanel.setBorder(new EmptyBorder(15, 35, 15, 35)); 
        
        // TÍTULO
        JLabel lblTitle = new JLabel("Registrarse");
        lblTitle.setFont(AppFonts.title());
        lblTitle.setForeground(AppColors.TEXT_LIGHT);
        lblTitle.setAlignmentX(LEFT_ALIGNMENT);
        
        registerPanel.add(lblTitle);
        registerPanel.add(Box.createVerticalStrut(10));
        
        
        initializeComponents();     
        
        initializeErrorLabels();
        
        registerPanel.add(createFieldPanel("Nombre completo: *", txtName, lblErrorName));
        registerPanel.add(Box.createVerticalStrut(5));
        
        registerPanel.add(createFieldPanel("Correo electrónico: *", txtEmail, lblErrorEmail));
        registerPanel.add(Box.createVerticalStrut(5));
        
        registerPanel.add(createDatePanel());
        registerPanel.add(Box.createVerticalStrut(5)); 
        
        registerPanel.add(createGenderPanel());
        registerPanel.add(Box.createVerticalStrut(5));
        
        registerPanel.add(createButtonsPanel());
        
      
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(AppColors.PANEL);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.WEST;
        
        wrapper.add(registerPanel, gbc);
        
        return wrapper;
    }
    
    private void initializeComponents() {
        txtName = new RoundedTextField(8);
        txtEmail = new RoundedTextField(8);
        
        cbxDay = new JComboBox<>();
        for (int i = 1; i <= 31; i++) cbxDay.addItem(i);
        
        cbxMonth = new JComboBox<>();
        for (int i = 1; i <= 12; i++) cbxMonth.addItem(i);
        
        cbxYear = new JComboBox<>();
        int currentYear = LocalDate.now().getYear();
        for (int i = 0; i < 100; i++) cbxYear.addItem(currentYear - i);
        
        // Radio buttons
        rbMan = new JRadioButton("Hombre");
        rbWomen = new JRadioButton("Mujer");
        rbOther = new JRadioButton("Otro");
        
        bgSex = new ButtonGroup();
        bgSex.add(rbMan);
        bgSex.add(rbWomen);
        bgSex.add(rbOther);
        
        chkTerms = new JCheckBox("Acepto los Términos y Condiciones");
        chkTerms.setOpaque(false);
        chkTerms.setForeground(AppColors.TEXT_LIGHT);
        chkTerms.setFont(AppFonts.small());
        chkTerms.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    private void initializeErrorLabels() {
        lblErrorName = new JLabel(" ");
        lblErrorEmail = new JLabel(" ");
        lblErrorDate = new JLabel(" ");
        lblErrorGender = new JLabel(" ");
        lblErrorTerms = new JLabel(" ");
    }
    
    private JPanel createFieldPanel(String labelText, JComponent field, JLabel errorLabel) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setAlignmentX(LEFT_ALIGNMENT);
        
        JLabel label = new JLabel(labelText);
        label.setFont(AppFonts.bold(12));
        label.setForeground(AppColors.TEXT_LIGHT);
        label.setAlignmentX(LEFT_ALIGNMENT);
        
        field.setBackground(AppColors.FIELDS);
        field.setForeground(AppColors.TEXT_LIGHT);
        field.setFont(AppFonts.regular(13));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32)); 
        field.setAlignmentX(LEFT_ALIGNMENT);
        
        errorLabel.setFont(AppFonts.small());
        errorLabel.setForeground(Color.RED);
        errorLabel.setAlignmentX(LEFT_ALIGNMENT);
        
        panel.add(label);
        panel.add(Box.createVerticalStrut(2)); 
        panel.add(field);
        panel.add(Box.createVerticalStrut(1)); 
        panel.add(errorLabel);
        
        return panel;
    }
    
    private JPanel createDatePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setAlignmentX(LEFT_ALIGNMENT);
        
        JLabel label = new JLabel("Fecha de nacimiento: *");
        label.setFont(AppFonts.bold(12));
        label.setForeground(AppColors.TEXT_LIGHT);
        label.setAlignmentX(LEFT_ALIGNMENT);
        
        JPanel comboPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        comboPanel.setOpaque(false);
        comboPanel.setAlignmentX(LEFT_ALIGNMENT);
        
        styleComboBox(cbxDay);
        styleComboBox(cbxMonth);
        styleComboBox(cbxYear);
        
        JLabel lblDay = new JLabel("Día:");
        lblDay.setForeground(AppColors.TEXT_LIGHT);
        lblDay.setFont(AppFonts.small());
        
        JLabel lblMonth = new JLabel("Mes:");
        lblMonth.setForeground(AppColors.TEXT_LIGHT);
        lblMonth.setFont(AppFonts.small());
        
        JLabel lblYear = new JLabel("Año:");
        lblYear.setForeground(AppColors.TEXT_LIGHT);
        lblYear.setFont(AppFonts.small());
        
        comboPanel.add(lblDay);
        comboPanel.add(cbxDay);
        comboPanel.add(Box.createHorizontalStrut(5));
        comboPanel.add(lblMonth);
        comboPanel.add(cbxMonth);
        comboPanel.add(Box.createHorizontalStrut(5));
        comboPanel.add(lblYear);
        comboPanel.add(cbxYear);
        
        lblErrorDate.setFont(AppFonts.small());
        lblErrorDate.setForeground(Color.RED);
        lblErrorDate.setAlignmentX(LEFT_ALIGNMENT);
        
        panel.add(label);
        panel.add(Box.createVerticalStrut(2)); 
        panel.add(comboPanel);
        panel.add(Box.createVerticalStrut(1));
        panel.add(lblErrorDate);
        
        return panel;
    }
    
    private void styleComboBox(JComboBox<?> combo) {
        combo.setBackground(AppColors.FIELDS);
        combo.setForeground(AppColors.TEXT_LIGHT);
        combo.setFont(AppFonts.regular(12));
        combo.setPreferredSize(new Dimension(65, 28)); 
        combo.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    private JPanel createGenderPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setAlignmentX(LEFT_ALIGNMENT);
        
        JLabel label = new JLabel("Sexo: *");
        label.setFont(AppFonts.bold(12));
        label.setForeground(AppColors.TEXT_LIGHT);
        label.setAlignmentX(LEFT_ALIGNMENT);
        
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        radioPanel.setOpaque(false);
        radioPanel.setAlignmentX(LEFT_ALIGNMENT);
        
        rbMan.setOpaque(false);
        rbMan.setForeground(AppColors.TEXT_LIGHT);
        rbMan.setFont(AppFonts.regular(12));
        rbMan.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        rbWomen.setOpaque(false);
        rbWomen.setForeground(AppColors.TEXT_LIGHT);
        rbWomen.setFont(AppFonts.regular(12));
        rbWomen.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        rbOther.setOpaque(false);
        rbOther.setForeground(AppColors.TEXT_LIGHT);
        rbOther.setFont(AppFonts.regular(12));
        rbOther.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        radioPanel.add(rbMan);
        radioPanel.add(rbWomen);
        radioPanel.add(rbOther);
        
        lblErrorGender.setFont(AppFonts.small());
        lblErrorGender.setForeground(Color.RED);
        lblErrorGender.setAlignmentX(LEFT_ALIGNMENT);
        
        panel.add(label);
        panel.add(Box.createVerticalStrut(2)); 
        panel.add(radioPanel);
        panel.add(Box.createVerticalStrut(1)); 
        panel.add(lblErrorGender);
        
        return panel;
    }
    
    private JPanel createButtonsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setAlignmentX(LEFT_ALIGNMENT);
        
        
        JButton btnRegister = createButton(
            "Registrarse",
            AppColors.YELLOW,
            AppColors.TEXT_DARK,
            13 
        );
        
        btnRegister.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnRegister.setBackground(AppColors.YELLOW_HOVER);
            }
            public void mouseExited(MouseEvent e) {
                btnRegister.setBackground(AppColors.YELLOW);
            }
        });
        
        btnRegister.addActionListener(e -> {
            if (registerListener != null) {
                registerListener.actionPerformed(e);
            } else {
                handleRegister();
            }
        });
        
        
        panel.add(btnRegister);
        
        return panel;
    }
    
    private JButton createButton(String text, Color background, Color textColor, int textSize) {
        RoundedButton button = new RoundedButton(text, 8);
        button.setFont(AppFonts.bold(textSize));
        button.setBackground(background);
        button.setForeground(textColor);
        
        
        button.setMaximumSize(new Dimension(350, 35)); 
        button.setPreferredSize(new Dimension(350, 35));
        button.setMinimumSize(new Dimension(350, 35));
        
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        return button;
    }
    
    private void handleRegister() {
        resetErrorLabels();
        boolean isValid = true;
        
        // Validaciones
        if (txtName.getText().trim().isEmpty()) {
            lblErrorName.setText("El nombre es obligatorio");
            isValid = false;
        }
        
        String email = txtEmail.getText().trim();
        if (email.isEmpty()) {
            lblErrorEmail.setText("El correo es obligatorio");
            isValid = false;
        } else if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            lblErrorEmail.setText("Correo no valido");
            isValid = false;
        }
        
        if (bgSex.getSelection() == null) {
            lblErrorGender.setText("Selecciona una opción");
            isValid = false;
        }
        
        if (isValid) {
            JOptionPane.showMessageDialog(
                this,
                "¡Registro exitoso!",
                "Registro completado",
                JOptionPane.INFORMATION_MESSAGE
            );
            
            dispose();
        }
    }
    
    private void resetErrorLabels() {
        lblErrorName.setText(" ");
        lblErrorEmail.setText(" ");
        lblErrorDate.setText(" ");
        lblErrorGender.setText(" ");
        lblErrorTerms.setText(" ");
    }
    
    public void prefillData(UserModel user) {
        txtName.setText(user.getName());
        txtEmail.setText(user.getEmail());
        
        switch (user.getGender()) {
            case "Hombre" -> rbMan.setSelected(true);
            case "Mujer"  -> rbWomen.setSelected(true);
            case "Otro"   -> rbOther.setSelected(true);
        }
        
        String[] parts = user.getBirthDate().split("/");
        if (parts.length == 3) {
            cbxDay.setSelectedItem(Integer.parseInt(parts[0]));
            cbxMonth.setSelectedItem(Integer.parseInt(parts[1]));
            cbxYear.setSelectedItem(Integer.parseInt(parts[2]));
        }

        chkTerms.setSelected(true);
    }
    
    public void setRegisterListener(ActionListener listener) {
        this.registerListener = listener;
    }
    
    public void setBackToLoginListener(ActionListener listener) {
        this.backToLoginListener = listener;
    }
    
    // Getters para el controlador
    public String getName() {
        return txtName.getText().trim();
    }
    
    public String getEmail() {
        return txtEmail.getText().trim();
    }
    
    public String getSelectedGender() {
        if (rbMan.isSelected()) return "Hombre";
        if (rbWomen.isSelected()) return "Mujer";
        if (rbOther.isSelected()) return "Otro";
        return null;
    }
    
    public String getBirthDate() {
        return cbxDay.getSelectedItem() + "/" + 
               cbxMonth.getSelectedItem() + "/" + 
               cbxYear.getSelectedItem();
    }
    
    public boolean isTermsAccepted() {
        return chkTerms.isSelected();
    }
    
    
    public void setNameError(String message) {
        lblErrorName.setText(message);
    }
    
    public void setEmailError(String message) {
        lblErrorEmail.setText(message);
    }
    
    public void setDateError(String message) {
        lblErrorDate.setText(message);
    }
    
    public void setGenderError(String message) {
        lblErrorGender.setText(message);
    }
    
    public void setTermsError(String message) {
        lblErrorTerms.setText(message);
    }
    
    public void clearAllErrors() {
        resetErrorLabels();
    }
    
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Exito", JOptionPane.INFORMATION_MESSAGE);
    }

	public UserModel getUserData() {
		return new UserModel(
	        getName(),
	        getEmail(),
	        null,
	        getSelectedGender(),
	        getBirthDate()
	    );
	}
}