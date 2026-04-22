package controllers;

import java.io.IOException;

import models.UserModel;
import repository.UserRepository;
import views.AddUserView;
import views.LoginView;

public class AddUserController {
    private AddUserView view;
    private UserRepository userRepository;
    private Runnable onSuccess;

    public AddUserController(AddUserView view, Runnable onSuccess) {
    	this.view = view;
    	this.onSuccess = onSuccess;
    	this.userRepository = new UserRepository();
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
            view.dispose();
            onSuccess.run();
        }
    }

    private boolean validateUserData(UserModel user) {
        boolean isValid = true;

        if (user.getName().isEmpty()) {
            view.setNameError("El nombre es obligatorio");
            isValid = false;
        }

        if (!user.isValidEmail()) {
            view.setEmailError("Correo no valido");
            isValid = false;
        }

        if (!user.isValidPassword()) {
            view.setPasswordError("Contraseña debe tener al menos 6 caracteres");
            isValid = false;
        }

        return isValid;
    }

    private void saveUser(UserModel user) {
        try {
            userRepository.save(user);
            System.out.println("Usuario registrado: " + user.getEmail());
        } catch (IOException e) {
            System.out.println("Error al guardar el usuario");
            e.printStackTrace();
        }
    }
}