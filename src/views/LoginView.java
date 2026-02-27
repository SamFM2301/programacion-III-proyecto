package views;

import javax.swing.*;
import components.RoundButton;
import components.TextPrompt;
import img.ImagePanel;

import java.awt.*;

public class LoginView extends JFrame {

    private JLabel lblMensajeError;

    public LoginView() {
        setTitle("Login");
        setSize(750, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        
        Toolkit tk = Toolkit.getDefaultToolkit(); 
        Image icono = tk.getImage("src/img/iniciosesion.png"); 
        setIconImage(icono);
        
        
        // Panel principal (fondo)
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBackground(new Color(57, 94, 102));

        
        
        // Panel contenedor dividido en 2 partes
        JPanel panelContenedor = new JPanel(new BorderLayout());
        panelContenedor.setBackground(Color.WHITE);

        
        
        // PANEL IZQUIERDO (IMAGEN)
        ImagePanel panelImagen = new ImagePanel("/img/welcome.png");
        panelImagen.setPreferredSize(new Dimension(300, 0));
        panelContenedor.add(panelImagen, BorderLayout.WEST);

        
        

        // PANEL DERECHO (FORMULARIO)   
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(new Color(56, 125, 122));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.insets = new Insets(8, 5, 8, 5);
        gridConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridConstraints.gridx = 0;

        
        
        // TÍTULO
        JLabel lblTitulo = new JLabel("INICIAR SESIÓN", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(Color.WHITE);

        gridConstraints.gridy = 0;
        panelFormulario.add(lblTitulo, gridConstraints);

        
        
        // USUARIO
        gridConstraints.gridy = 1;
        JLabel lblUsuario = new JLabel("Usuario*");
        lblUsuario.setForeground(Color.WHITE);
        panelFormulario.add(lblUsuario, gridConstraints);

        gridConstraints.gridy = 2;
        JTextField txtUsuario = new JTextField(15);
        panelFormulario.add(txtUsuario, gridConstraints);

        TextPrompt usuarioPlaceholder = new TextPrompt("Ingresa tu usuario", txtUsuario);
        usuarioPlaceholder.changeAlpha(0.6f);

        
        
        // CONTRASEÑA
        gridConstraints.gridy = 3;
        JLabel lblPass = new JLabel("Contraseña*");
        lblPass.setForeground(Color.WHITE);
        panelFormulario.add(lblPass, gridConstraints);

        gridConstraints.gridy = 4;
        JPasswordField txtPassword = new JPasswordField(15);
        panelFormulario.add(txtPassword, gridConstraints);

        TextPrompt passPlaceholder = new TextPrompt("Ingresa tu contraseña", txtPassword);
        passPlaceholder.changeAlpha(0.6f);

        
        
        // MENSAJE ERROR
        gridConstraints.gridy = 5;
        lblMensajeError = new JLabel("Usuario o contraseña incorrectos");
        lblMensajeError.setForeground(Color.YELLOW);
        lblMensajeError.setHorizontalAlignment(SwingConstants.CENTER);
        lblMensajeError.setVisible(false);
        panelFormulario.add(lblMensajeError, gridConstraints);

        
        
        // BOTONES
        gridConstraints.gridy = 6;
        gridConstraints.insets = new Insets(20, 5, 5, 5);

        JPanel panelBotones = new JPanel();
        panelBotones.setOpaque(false);

        RoundButton btnIniciar = new RoundButton("Iniciar");
        btnIniciar.setPreferredSize(new Dimension(120, 40));

        RoundButton btnRegistrar = new RoundButton("Registrar");
        btnRegistrar.setPreferredSize(new Dimension(120, 40));

        panelBotones.add(btnIniciar);
        panelBotones.add(btnRegistrar);

        panelFormulario.add(panelBotones, gridConstraints);

        panelContenedor.add(panelFormulario, BorderLayout.CENTER);
        panelPrincipal.add(panelContenedor);
        add(panelPrincipal);
    }

    
    // Método para mostrar error
    public void mostrarError(String mensaje) {
        lblMensajeError.setText(mensaje);
        lblMensajeError.setVisible(true);
    }
    

    public static void main(String[] args) {       
            new LoginView().setVisible(true);
    }
}