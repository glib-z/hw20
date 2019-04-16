package gz.utills;

import gz.model.User;
import org.h2.tools.DeleteDbFiles;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserH2Dao {

    private Connection connection;
    private Statement statement;

    static {
        // delete the database named 'test' in the user home directory
        DeleteDbFiles.execute("~", "test_h2", true);

        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public UserH2Dao() {
        try {
            connection = DriverManager.getConnection("jdbc:h2:~/test_h2");
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS users_h2(_id int primary key, name varchar(30), age int)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void close() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void removeAll() {
        try {
            statement.execute("DELETE FROM users_h2");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insert(User user) {
        try {
            statement.execute(String.format("INSERT INTO users_h2 VALUES ('%d', '%s', '%d')",
                    user.getId(), user.getName(), user.getAge()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users_h2");
            while (resultSet.next()) {
                int id = resultSet.getInt("_id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                users.add(new User(id, name, age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    public void removeUserByName(String name) {
        try {
            statement.execute(String.format("DELETE FROM users_h2 WHERE name='%s'", name));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void removeUser(int id) {
        try {
            statement.execute(String.format("DELETE FROM users_h2 WHERE _id='%s'", id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public User getUser(int id) {
        try {
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM users_h2 WHERE _id = %d", id));
            while (resultSet.next()) {
                int idd = resultSet.getInt("_id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                return new User(idd, name, age);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void updateUser(User user) {
        try {
            statement.execute(String.format("UPDATE users_h2 SET name = '%s', age = %d WHERE _id = %d",
                    user.getName(), user.getAge(), user.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
