package utils;

import models.User;

public class Session {
	private static User currentUser;
	
	public static void login(User user) {
		currentUser = user;
	}
	
	public static User getCurrentUser() {
		return currentUser;
	}
	
	public static void logout() {
		currentUser = null;
	}
	
	public static boolean isLoggedIn() {
		return currentUser != null;
	}
	
	public static String getRole( ) {
		// AQUI TIEINES QUE PONER EL ROL YA QUE LO AGREGES DE ATRIBUTO AL USUARIO
		// return currentUser.getRole(); <- ASI 
		return currentUser.getName(); 
	}
}
