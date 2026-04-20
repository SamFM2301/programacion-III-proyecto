package controllers;

import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import models.UserModel;
import repository.UserRepository;
import views.MainView;
import views.LoginView;

public class HomeController {

    private MainView view;
    private UserRepository userRepository;

    public HomeController(MainView view) {
        this.view = view;
        this.userRepository = new UserRepository();
        loadUsers();
    }

    private void loadUsers() {
        try {
            List<UserModel> users = userRepository.getUsers();
            view.loadUsers(users);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(view, "Error al cargar usuarios: " + e.getMessage());
        }
    }
}