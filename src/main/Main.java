package main;

import views.LoginView;
import controllers.LoginController;

public class Main {
    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        LoginController controller = new LoginController(loginView);
    }
}