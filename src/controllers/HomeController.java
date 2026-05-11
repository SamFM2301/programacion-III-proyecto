package controllers;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import models.UserModel;
import repository.UserRepository;
import utils.Config;
import views.MainView;
import views.AddUserView;
import views.LoginView;

public class HomeController {

    private MainView view;
    private UserRepository userRepository;

    public HomeController(MainView view) {
        this.view = view;
        this.userRepository = new UserRepository();
        
        loadWindowPreferences();
        registerListeners();
        
        loadUsers();
        
        view.addAddListener(e -> add());
        view.addModifyListener(e -> modify());
        
        view.addDeleteListener(e -> delete());
        view.addPdfListener(e -> exportPdf());
    }
    
    public void registerListeners() {
    	view.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				saveWindowPreferences();
			}
		});
    }

    private void saveWindowPreferences() {
		Dimension size = view.getSize();
		Point point = view.getLocation();
		
		Config.set("registration.window.width", 
				String.valueOf(size.width));
		
		Config.set("registration.window.height", 
				String.valueOf(size.height));
		
		Config.set("registration.window.x", 
				String.valueOf(point.x));
		
		Config.set("registration.window.y", 
				String.valueOf(point.y));
		
	}
    
    private void loadWindowPreferences() {
		int width = Integer.parseInt(
				Config.get("registration.window.width"
						, "500"));
		
		int height = Integer.parseInt(
				Config.get("registration.window.height"
						, "500"));
		
		String xValue = Config.get("registration.window.x"
						, "");
		
		String yValue = Config.get("registration.window.y"
				, "");
		
		if(!xValue.isBlank() && !yValue.isBlank()) {
			view.setWindowLocation(Integer.parseInt(xValue), Integer.parseInt(yValue));
		}else {
			view.setLocationRelativeTo(null);
		}
		
		view.setWindowSize(width, height);
	}
    
    private void exportPdf() {
        List<UserModel> users;
        try {
            users = userRepository.getUsers();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(view, "Error al cargar usuarios: " + e.getMessage());
            return;
        }

        File file = view.selectPdfFile();
        if (file == null) return;

        try {
            new services.PDFExporter().exportUsers(users, file);
            JOptionPane.showMessageDialog(view, "PDF exportado correctamente:\n" + file.getAbsolutePath());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(view, "Error al exportar PDF: " + e.getMessage());
        }
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

    public void modify() {
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
    }
}