package main;

import views.LoginView;
import views.MainView;

import java.io.IOException;
import java.util.List;

import controllers.HomeController;
import controllers.LoginController;
import models.UserModel;
import repository.UserRepository;

public class Main {
    public static void main(String[] args) {
        //LoginView loginView = new LoginView();
        //LoginController controller = new LoginController(loginView);
    	
    	MainView mainView = new MainView();
    	new HomeController(mainView);
        
        UserRepository repo = new UserRepository();

        try {
            List<UserModel> users = repo.getUsers();
            for (UserModel user : users) {
                System.out.println(user);
                System.out.println("\n-------------------------------\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}