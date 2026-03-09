package views;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.time.LocalDate;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import img.ImagePanel;
import utils.AppFont;
import components.ErrorLabel;

public class RegisterForm extends JFrame {

    private JTextField txtName;
    private JTextField txtEmail;
    private JPanel datePanel;
    private JPanel sexPanel;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private JCheckBox chkTerms;

    private ButtonGroup bgSex;
    private ErrorLabel lblErrorName;
    private JLabel lblErrorEmail;
    private JLabel lblErrorDate;
    private JLabel lblErrorGender;
    private JLabel lblErrorPassword;
    private JLabel lblErrorTerms;

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
        gbc.insets = new Insets(4, 10, 4, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        

        // Inicializar labels error
        lblErrorName = new ErrorLabel();
        lblErrorEmail = createErrorLabel();
        lblErrorDate = createErrorLabel();
        lblErrorGender = createErrorLabel();
        lblErrorPassword = createErrorLabel();
        lblErrorTerms = createErrorLabel();

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
        txtName = new JTextField(20);
        formPanel.add(txtName, gbc);

        gbc.gridy++;
        formPanel.add(lblErrorName, gbc);

        // EMAIL
        gbc.gridy++;
        formPanel.add(new JLabel("Correo *"), gbc);

        gbc.gridy++;
        txtEmail = new JTextField(20);
        formPanel.add(txtEmail, gbc);

        gbc.gridy++;
        formPanel.add(lblErrorEmail, gbc);

        // FECHA
        gbc.gridy++;
        formPanel.add(new JLabel("Fecha Nacimiento *"), gbc);

        gbc.gridy++;
        datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        datePanel.setBackground(Color.WHITE);

        JComboBox<Integer> cbxDay = new JComboBox<>();
        for (int i = 1; i <= 31; i++) cbxDay.addItem(i);

        JComboBox<Integer> cbxMonth = new JComboBox<>();
        for (int i = 1; i <= 12; i++) cbxMonth.addItem(i);

        JComboBox<Integer> cbxYear = new JComboBox<>();
        int currentYear = LocalDate.now().getYear();
        for (int i = 0; i < 100; i++) cbxYear.addItem(currentYear - i);

        datePanel.add(new JLabel("Día:"));
        datePanel.add(cbxDay);
        datePanel.add(new JLabel("Mes:"));
        datePanel.add(cbxMonth);
        datePanel.add(new JLabel("Año:"));
        datePanel.add(cbxYear);

        formPanel.add(datePanel, gbc);

        gbc.gridy++;
        formPanel.add(lblErrorDate, gbc);

        // SEXO
        gbc.gridy++;
        formPanel.add(new JLabel("Sexo *"), gbc);

        gbc.gridy++;
        sexPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        sexPanel.setBackground(Color.WHITE);

        JRadioButton rbMan = new JRadioButton("Hombre");
        JRadioButton rbWomen = new JRadioButton("Mujer");
        JRadioButton rbOther = new JRadioButton("Otro");

        bgSex = new ButtonGroup();
        bgSex.add(rbMan);
        bgSex.add(rbWomen);
        bgSex.add(rbOther);

        sexPanel.add(rbMan);
        sexPanel.add(rbWomen);
        sexPanel.add(rbOther);

        formPanel.add(sexPanel, gbc);

        gbc.gridy++;
        formPanel.add(lblErrorGender, gbc);

        // CONTRASEÑA
        gbc.gridy++;
        formPanel.add(new JLabel("Contraseña *"), gbc);

        gbc.gridy++;
        txtPassword = new JPasswordField(20);
        formPanel.add(txtPassword, gbc);

        gbc.gridy++;
        formPanel.add(lblErrorPassword, gbc);

        // CONFIRMAR
        gbc.gridy++;
        formPanel.add(new JLabel("Confirmar Contraseña *"), gbc);

        gbc.gridy++;
        txtConfirmPassword = new JPasswordField(20);
        formPanel.add(txtConfirmPassword, gbc);

        // TERMINOS
        gbc.gridy++;
        chkTerms = new JCheckBox("Acepto Términos y Condiciones");
        chkTerms.setBackground(Color.WHITE);
        formPanel.add(chkTerms, gbc);

        gbc.gridy++;
        formPanel.add(lblErrorTerms, gbc);

        // BOTÓN
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;

        JButton btnRegister = new JButton("Registrar");
        btnRegister.setPreferredSize(new Dimension(150, 40));
        btnRegister.addActionListener(e -> validateForm());

        formPanel.add(btnRegister, gbc);
        
        assignListeners();

        return formPanel;
    }

    private JLabel createErrorLabel() {
        JLabel label = new JLabel();
        label.setFont(AppFont.small());
        label.setForeground(Color.RED);
        return label;
    }

    private JPanel createImagePanel() {
        ImagePanel imagePanel = new ImagePanel("/img/welcome.png");
        imagePanel.setLayout(new BorderLayout());
        imagePanel.setPreferredSize(new Dimension(400, 0));
        return imagePanel;
    }
    
    //VALIDACIONES 

    private void validateForm() {
        resetErrorLabels();
        boolean valid = true;

        if (!validateName()) 
        	valid = false;
        
        if (!validateEmail()) 
        	valid = false;
        
        if (!validateGender()) 
        	valid = false;
        
        if (!validatePassword()) 
        	valid = false;
        
        if (!validateTerms()) 
        	valid = false;
      
        if (valid) {
            JOptionPane.showMessageDialog(this, "Registro exitoso");
            
            new LoginView();
            dispose();
        }
    }

    private void resetErrorLabels() {
        lblErrorName.setText("");
        lblErrorEmail.setText("");
        lblErrorDate.setText("");
        lblErrorGender.setText("");
        lblErrorPassword.setText("");
        lblErrorTerms.setText("");
    }

    private boolean validateName() {
        if (txtName.getText().trim().isEmpty()) {
            lblErrorName.setText("El nombre es obligatorio");
            return false;
        }
        
        lblErrorName.setText("");
        return true;
    }

    private boolean validateEmail() {
        String email = txtEmail.getText().trim();

        if (email.isEmpty()) {
            lblErrorEmail.setText("El correo es obligatorio");
            return false;
        }

        if (!email.contains("@") || !email.contains(".")) {
            lblErrorEmail.setText("Correo inválido");
            return false;
        }

        lblErrorEmail.setText("");
        return true;
    }

    private boolean validateGender() {
        if (bgSex.getSelection() == null) {
            lblErrorGender.setText("Seleccione una opción");
            return false;
        }
        
        lblErrorGender.setText("");
        return true;
    }

    private boolean validatePassword() {
        String pass = new String(txtPassword.getPassword());
        String confirm = new String(txtConfirmPassword.getPassword());

        if (pass.isEmpty() || confirm.isEmpty()) {
            lblErrorPassword.setText("Complete ambos campos");
            return false;
        }

        if (pass.length() < 8) {
            lblErrorPassword.setText("Mínimo 8 caracteres");
            return false;
        }

        if (!pass.equals(confirm)) {
            lblErrorPassword.setText("Las contraseñas no coinciden");
            return false;
        }

        lblErrorPassword.setText("");
        return true;
    }

    
    private void assignListeners() {

        // Validacion en tiempo real para nombre
        txtName.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { 
            	validateName(); 
            }
            public void removeUpdate(DocumentEvent e) { 
            	validateName(); 
            }
            public void changedUpdate(DocumentEvent e) {
            	validateName(); 
            }
        });

        // Validacion en tiempo real para email
        txtEmail.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { 
            	validateEmail(); 
            }
            public void removeUpdate(DocumentEvent e) { 
            	validateEmail(); 
            }
            public void changedUpdate(DocumentEvent e) { 
            	validateEmail(); 
        	}
        });

        // Validacion para contraseña
        txtPassword.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { 
            	validatePassword(); 
        	}
            public void removeUpdate(DocumentEvent e) { 
            	validatePassword(); 
        	}
            public void changedUpdate(DocumentEvent e) { 
            	validatePassword(); 
        	}
        });

        // Validacion para confirmar contraseña
        txtConfirmPassword.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { 
            	validatePassword(); 
        	}
            public void removeUpdate(DocumentEvent e) { 
            	validatePassword(); 
        	}
            public void changedUpdate(DocumentEvent e) { 
            	validatePassword(); 
        	}
        });

        // Validacion de terminos
        chkTerms.addActionListener(e -> validateTerms());

    }
        
    private boolean validateTerms() {
        if (!chkTerms.isSelected()) {
            lblErrorTerms.setText("Debe aceptar los términos");
            return false;
        }
        return true;
    }
}