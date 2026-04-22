package controllers;

import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import models.UserModel;
import repository.UserRepository;
import views.MainView;
import views.AddUserView;
import views.LoginView;

public class HomeController {

    private MainView view;
    private UserRepository userRepository;

    public HomeController(MainView view) {
        this.view = view;
        this.userRepository = new UserRepository();
        loadUsers();
        
        view.addAddListener(e -> add());
        //view.addModifyListener(e -> modify());
        view.addDeleteListener(e -> delete());
    }
    
    private void loadUsers() {
        try {
            List<UserModel> users = userRepository.getUsers();
            view.loadUsers(users);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(view, "Error al cargar usuarios: " + e.getMessage());
        }
    }
    
    public void add() {
        AddUserView addUserView = new AddUserView();
        new AddUserController(addUserView, () -> loadUsers());
    }
    
    public void delete() {
        UserModel selected = view.getSelectedUser();
        if (selected == null) {
            JOptionPane.showMessageDialog(view, "Selecciona un usuario primero.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(view, 
            "¿Eliminar a " + selected.getName() + "?", 
            "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                userRepository.delete(selected.getEmail());
                loadUsers();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(view, "Error al eliminar: " + e.getMessage());
            }
        }
    }

    /*public void modify() {
        UserModel selected = view.getSelectedUser();
        if (selected == null) {
            JOptionPane.showMessageDialog(view, "Selecciona un usuario primero.");
            return;
        }

        AddUserView editView = new AddUserView();
        new AddUserController(editView, selected, (updatedUser) -> {
            try {
                userRepository.update(selected.getEmail(), updatedUser);
                loadUsers();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(view, "Error al modificar: " + e.getMessage());
            }
        });
    }*/
}