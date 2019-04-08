package gz.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gz.model.User;
import gz.model.UserBase;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileStorage implements Storage {

    private UserBase userBase;
    private String fileName;


    public FileStorage(String fileName) {
        this.fileName = fileName;
        readFile();
    }


    public void removeAll() {
        try {
            FileUtils.forceDelete(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        readFile();
    }


    public void removeUser(int id) {
        for (int i = 0; i < userBase.getUsers().size(); i++) {
            if (userBase.getUsers().get(i).getId() == id) {
                userBase.getUsers().remove(i);
            }
        }
        saveToFile();
    }


    public void removeUserByName(String name) {
        for (int i = 0; i < userBase.getUsers().size(); i++) {
            if (userBase.getUsers().get(i).getName().compareTo(name) == 0) {
                userBase.getUsers().remove(i);
            }
        }
        saveToFile();
    }


    public void addUser(User user) {
        userBase.addUser(user);
        saveToFile();
    }


    public void updateUser(User user) {
        for (int i = 0; i < userBase.getUsers().size(); i++) {
            if (userBase.getUsers().get(i).getId() == user.getId()) {
                userBase.getUsers().remove(i);
                userBase.addUser(user);
                saveToFile();
                return;
            }
        }
    }


    public User getUser(int id) {
        for (int i = 0; i < userBase.getUsers().size(); i++) {
            if (userBase.getUsers().get(i).getId() == id) {
                return userBase.getUsers().get(i);
            }
        }
        return null;
    }


    public List<User> getAllUsers() {
        return userBase.getUsers();
    }


    private void readFile(){
        File file = new File(fileName);
        if (file.exists() && !file.isDirectory()) {
            try {
                String jsonString = FileUtils.readFileToString(file, "UTF-8");
                Gson gson = new Gson();
                userBase = gson.fromJson(jsonString, UserBase.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            userBase = new UserBase();
            saveToFile();
        }
    }


    private void saveToFile() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileUtils.writeStringToFile(new File(fileName), gson.toJson(userBase), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
