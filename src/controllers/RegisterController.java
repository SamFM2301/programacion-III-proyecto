// RegisterController.java
package controllers;

import models.UserModel;
import views.RegisterForm;
import views.LoginView;

public class RegisterController {
    private RegisterForm view;
    
    public RegisterController(RegisterForm view) {
        this.view = view;
        initController();
    }
    
    private void initController() {
        view.setRegisterListener(e -> registerUser());
    }
    
    private void registerUser() {
        UserModel user = view.getUserData();
        
        if (validateUserData(user)) {          
            saveUser(user);
            
            view.showSuccessMessage("Registro exitoso");
            new LoginView();
            view.dispose();
        }
    }
    
    private boolean validateUserData(UserModel user) {
        boolean isValid = true;
        
        if (user.getName().isEmpty()) {
            view.setNameError("El nombre es obligatorio");
            isValid = false;
        }
        
        if (!user.isValidEmail()) {
            view.setEmailError("Correo no válido");
            isValid = false;
        }
        
        if (!user.isValidPassword()) {
            view.setPasswordError("Contraseña debe tener al menos 6 caracteres");
            isValid = false;
        }
        
        return isValid;
    }
    
    private void saveUser(UserModel user) {
        // Lógica de persistencia
        System.out.println("Usuario registrado: " + user.getEmail());
    }
}