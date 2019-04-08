package gz.utills;

import gz.model.User;
import gz.model.UserBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public UserDao() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/users", "postgres", "123456");
        maybeCreateUsersTable();
    }

    private void maybeCreateUsersTable() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS users (\n" +
                    "_id int PRIMARY KEY,\n" +
                    "name varchar(100),\n" +
                    "age int\n" +
                    ");");
        }
    }

    public void clean() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            int count = statement.executeUpdate("DELETE FROM users;");
            System.out.println("Deleted " + count + " rows from table users");
        }
    }

    public void insertUser(User user) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String request = String.format("INSERT INTO users VALUES ('%d', '%s', '%d');", user.getId(), user.getName(), user.getAge());
            statement.execute(request);
        }
    }

    public List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String request = String.format("SELECT * FROM users;");
            ResultSet resultSet = statement.executeQuery(request);
            while (resultSet.next()) {
                int id = resultSet.getInt("_id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                users.add(new User(id, name, age));
            }
        }
        return users;
    }


/*
    public UserBase getGroupByName(String name) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String request = String.format("SELECT * FROM groups WHERE name = '%s';", name);
            ResultSet resultSet = statement.executeQuery(request);
            if (resultSet.next()) {
                String id = resultSet.getString("_id");
                List<User> users = getUsersByGroupId(id);
                return new Group(id, name, users);
            }
        }
        return null;
    }

    private List<User> getUsersByGroupId(String groupId) throws SQLException {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String request = String.format("SELECT * FROM users WHERE group_id = '%s';", groupId);
            ResultSet resultSet = statement.executeQuery(request);
            while (resultSet.next()) {
                String id = resultSet.getString("_id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                users.add(new User(id, name, age, groupId));
            }
        }
        return users;
    }

    /*
        SELECT column-list
        FROM table_name
        [WHERE condition]
        [ORDER BY column1, column2, .. columnN] [ASC | DESC];
     */
/*    public List<User> getUsersByAge(int from, int to) throws SQLException {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String request = String.format("SELECT * FROM users WHERE age >= '%s' AND age <= '%s' ORDER BY age, name ASC;", from, to);
            ResultSet resultSet = statement.executeQuery(request);
            while (resultSet.next()) {
                String id = resultSet.getString("_id");
                String groupId = resultSet.getString("group_id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                users.add(new User(id, name, age, groupId));
            }
        }
        return users;
    }
*/

}
