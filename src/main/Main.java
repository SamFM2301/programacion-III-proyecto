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
        new LoginController();
        
    	//MainView mainView = new MainView();
    	//new HomeController(mainView);
    	
        UserRepository repo = new UserRepository();
    }
}