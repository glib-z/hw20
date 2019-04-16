package gz.storage;

import gz.model.User;
import gz.utills.UserH2Dao;

import java.util.List;

public class H2Storage implements Storage {

    private UserH2Dao userH2Dao;

    public H2Storage() {
        userH2Dao = new UserH2Dao();
    }

    public void close() {
        userH2Dao.close();
    }

    @Override
    public void removeAll() {
        userH2Dao.removeAll();
    }

    @Override
    public void removeUser(int id) {
        userH2Dao.removeUser(id);
    }

    @Override
    public void removeUserByName(String name) {
        userH2Dao.removeUserByName(name);
    }

    @Override
    public void addUser(User user) {
        userH2Dao.insert(user);
    }

    @Override
    public void updateUser(User user) {
        userH2Dao.updateUser(user);
    }

    @Override
    public User getUser(int id) {
        return userH2Dao.getUser(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userH2Dao.getAllUsers();
    }
}
