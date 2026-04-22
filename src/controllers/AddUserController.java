package controllers;

import java.io.IOException;

import models.UserModel;
import repository.UserRepository;
import views.AddUserView;
import views.LoginView;

public class AddUserController {
    private AddUserView view;
    private UserRepository userRepository;
    private UserModel originalUser;
    private Runnable onSuccess;
    
    public AddUserController(AddUserView view, Runnable onSuccess) {
    	this.view = view;
    	this.onSuccess = onSuccess;
    	this.userRepository = new UserRepository();
    	this.originalUser = null;
    	initController();
    	view.setVisible(true);
    }
    
    public AddUserController(AddUserView view, UserModel originalUser, Runnable onSuccess) {
    	this.view = view;
    	this.onSuccess = onSuccess;
    	this.userRepository = new UserRepository();
    	this.originalUser = originalUser;
    	view.prefillData(originalUser);
    	initController();
    	view.setVisible(true);
    }
    
    private void initController() {
    	view.setRegisterListener(e -> {
            if (originalUser == null) 
            	registerUser();
            else 
            	updateUser();
        });
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
    
    private void updateUser() {
        UserModel updated = view.getUserData();

        if (updated.getPassword() == null || updated.getPassword().isEmpty()) {
            updated.setPassword(originalUser.getPassword());
        }

        if (validateUserData(updated)) {
            try {
                userRepository.update(originalUser.getEmail(), updated);
                view.showSuccessMessage("Usuario actualizado");
                view.dispose();
                onSuccess.run();
            } catch (IOException e) {
                view.showError("Error al actualizar: " + e.getMessage());
            }
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

        return isValid;
    }

    private void saveUser(UserModel user) {
        try {
            userRepository.save(user);
        } catch (IOException e) {
            System.out.println("Error al guardar el usuario");
            e.printStackTrace();
        }
    }
}