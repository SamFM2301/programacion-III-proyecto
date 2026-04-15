package controllers;

import java.io.IOException;

import models.UserModel;
import repository.UserRepository;
import views.RegisterForm;
import views.LoginView;

public class RegisterController {
    private RegisterForm view;
    private UserRepository userRepository;

    public RegisterController(RegisterForm view) {
        this.view = view;
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