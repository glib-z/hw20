package gz.storage;

import gz.model.User;
import gz.model.UserBase;
import gz.utills.UserDao;

import java.sql.SQLException;
import java.util.List;

public class SQLStorage implements Storage {

//    private UserBase userBase;
//    private String dataBaseName;
    private UserDao userDao;

    public SQLStorage(String dataBaseName) {
//        this.dataBaseName = dataBaseName;
        try {
            userDao= new UserDao(dataBaseName);
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
        try {
            userDao.removeUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserByName(String name) {
        try {
            userDao.removeUserByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user) {
        try {
            userDao.insertUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try {
            userDao.removeUser(user.getId());
            userDao.insertUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser(int id) {
        try {
            return userDao.getUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
