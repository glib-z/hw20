package gz.model;

import java.util.ArrayList;
import java.util.List;

public class UserBase {

    private List<User> users = new ArrayList<User>();

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "UserBase{" +
                "users=" + users +
                '}';
    }
}
