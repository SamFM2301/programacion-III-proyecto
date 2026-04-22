
package controllers;

import models.UserModel;
import views.LoginView;
import views.MainView;
import exceptions.*;

public class LoginController {
    private LoginView view;
    
    public LoginController(LoginView view) {
        this.view = view;
        initController();
    }
    
    private void initController() {
        view.setLoginListener(e -> authenticate());
    }
    
    private void authenticate() {
        try {
            String email = view.getEmail();
            String password = view.getPassword();
            
            validateCredentials(email, password);
            
            view.showSuccessMessage("Inicio de sesión exitoso");
            
            MainView mainView = new MainView();
            new HomeController(mainView);
            view.dispose();
            
        } catch (InvalidUserException | InvalidPasswordException e) {
            view.showError(e.getMessage());
        }
    }
    
    private void validateCredentials(String email, String password) 
            throws InvalidUserException, InvalidPasswordException {
        
        if (email.isEmpty()) {
            throw new InvalidUserException("El correo es obligatorio");
        }
        
        if (!email.equals("jcamacho@alu.uabcs.mx")) {
            throw new InvalidUserException("Correo no válido");
        }
        
        if (password.isEmpty()) {
            throw new InvalidPasswordException("La contraseña es obligatoria");
        }
        
        if (password.length() < 6) {
            throw new InvalidPasswordException("Mínimo 6 caracteres");
        }
    }
}