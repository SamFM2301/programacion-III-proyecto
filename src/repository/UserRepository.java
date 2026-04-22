package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import models.UserModel;

public class UserRepository {
	private final String FILE = System.getProperty("user.dir") + "/src/assets/files/users.csv";

    public void save(UserModel user) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE, true), StandardCharsets.UTF_8))) {
            writer.write(user.toCsv());
            writer.newLine();
        }
    }
    

    // LEER A LOS USUARIOS
    public List<UserModel> getUsers() throws IOException {
        List<UserModel> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                UserModel user = UserModel.fromCsv(line);
                users.add(user);
            }
        }

        return users;
    }
    
    public void delete(String email) throws IOException {
        List<UserModel> users = getUsers();
        users.removeIf(u -> u.getEmail().equals(email));
        rewriteFile(users);
    }

    public void update(String originalEmail, UserModel updatedUser) throws IOException {
        List<UserModel> users = getUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(originalEmail)) {
                users.set(i, updatedUser);
                break;
            }
        }
        rewriteFile(users);
    }

    private void rewriteFile(List<UserModel> users) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE, false), StandardCharsets.UTF_8))) {
            for (UserModel user : users) {
                writer.write(user.toCsv());
                writer.newLine();
            }
        }
    }
}
