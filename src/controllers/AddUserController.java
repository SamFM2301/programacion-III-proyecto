package controllers;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

import javax.swing.JOptionPane;

import models.UserModel;
import repository.UserRepository;
import services.PDFExporter;
import views.AddUserView;
import views.LoginView;

public class AddUserController {
	private AddUserView view;
    private UserRepository userRepository;
    private Runnable onSuccess;
    private UserModel editingUser;
    private Consumer<UserModel> onEdit;
    private PDFExporter pdfExporter;

    public AddUserController(AddUserView view, Runnable onSuccess) {
        this.view = view;
        this.onSuccess = onSuccess;
        this.userRepository = new UserRepository();
        initController();
    }

    
    public AddUserController(AddUserView view, UserModel editingUser, Consumer<UserModel> onEdit) {
        this.view = view;
        this.editingUser = editingUser;
        this.onEdit = onEdit;
        this.userRepository = new UserRepository();
        pdfExporter = new PDFExporter();
        view.prefillData(editingUser);
        initController();
    }

    private void initController() {
        view.setRegisterListener(e -> registerUser());
    }

    private void registerUser() {
        UserModel user = view.getUserData();

        if (validateUserData(user)) {
            if (editingUser != null) {
                onEdit.accept(user);
                view.showSuccessMessage("Usuario actualizado");
            } else {
                saveUser(user);
                view.showSuccessMessage("Registro exitoso");
                onSuccess.run();
            }
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

    public void generatePdf() {
		
		File file = view.selectPdfFile();
		
		if(file == null) {
			return;
		}
		
		try {
			pdfExporter.exportUsers(userRepository.getUsers(), file);
			if(Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(file);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(view, "Error al exportar");
		}
		
		
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