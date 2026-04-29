package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import models.UserModel;

public class UserRepository {
	private final String FILE = System.getProperty("user.dir") + "/src/assets/files/users.json";

	private final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUT);
	
    public void save(UserModel user) throws IOException {
        List<UserModel> users = getUsers();
        users.add(user);
        updateAll(users);
    }

    // LEER A LOS USUARIOS
    public List<UserModel> getUsers() throws IOException {

    	File file = new File(FILE);
    	
    	if(!file.exists() || file.length() == 0) {
    		return new ArrayList<>();
    	}
    	
    	return mapper.readValue(
			file, 
			new TypeReference<List<UserModel>>() {}
		);
    }
   
    
    public void delete(String email) throws IOException {
        List<UserModel> users = getUsers();
        users.removeIf(u -> u.getEmail().equals(email));
        rewriteFile(users);
    }
    
    public void updateAll(List<UserModel> users) throws IOException {
    	mapper.writeValue(new File(FILE), users);
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
