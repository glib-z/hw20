package gz.storage;

import gz.model.User;
import gz.model.UserBase;
import gz.utills.UserDao;

import java.sql.SQLException;
import java.util.List;

public class SQLStorage implements Storage {

    private UserBase userBase;
    private String dataBaseName;
    private UserDao userDao;

    public SQLStorage(String dataBaseName) {
        this.dataBaseName = dataBaseName;
        try {
            userDao= new UserDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeAll() {
        try {
            userDao.clean();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUser(int id) {

    }

    public void removeUserByName(String name) {

    }

    public void addUser(User user) {
        try {
            userDao.insertUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {

    }

    public User getUser(int id) {
        return null;
    }

    public List<User> getAllUsers() {
        try {
            return userDao.getUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
