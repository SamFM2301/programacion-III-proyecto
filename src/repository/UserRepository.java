package repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import models.User;

public class UserRepository {
	//private final String FILE = System.getProperty("user.dir") + "/src/assets/files/users.json";

	private final String FILE = "."
			+ File.separator 
			+ "data"
			+ File.separator
			+ "users.json";
	
	private final ObjectMapper mapper = 
		new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
	
    public void save(User user) throws IOException {
        List<User> users = getUsers();
        users.add(user);
        updateAll(users);
    }

    // LEER A LOS USUARIOS
    public List<User> getUsers() throws IOException {

    	File file = new File(FILE);
    	
    	file.getParentFile().mkdirs();
    	
    	if(!file.exists() || file.length() == 0) {
    		return new ArrayList<>();
    	}
    	
    	return mapper.readValue(
			file, 
			new TypeReference<List<User>>() {}
		);
    }
   
    
    public void delete(String email) throws IOException {
    	List<User> users = getUsers();
        users.removeIf(u -> u.getEmail().equals(email));
        updateAll(users);
    }
    
    public void updateAll(List<User> users) throws IOException {
    	mapper.writeValue(new File(FILE), users);
    }
    
    public void update(String originalEmail, User updatedUser) throws IOException {
    	List<User> users = getUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(originalEmail)) {
                users.set(i, updatedUser);
                break;
            }
        }
        updateAll(users);
    }
}
