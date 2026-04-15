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
}
