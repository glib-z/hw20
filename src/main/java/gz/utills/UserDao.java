package gz.utills;

import gz.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private final String SQL_SERVER = "jdbc:postgresql://127.0.0.1:5432/";
    private Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public UserDao(String dataBaseName) throws SQLException {
        connection = DriverManager.getConnection(SQL_SERVER + dataBaseName, "postgres", "123456");
        maybeCreateUsersTable(dataBaseName);
    }


    private void maybeCreateUsersTable(String dataBaseName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS " + dataBaseName + " (\n" +
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


    public void removeUserByName(String name) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            int count = statement.executeUpdate("DELETE FROM users WHERE name='" + name + "';");
            System.out.println("Deleted " + count + " rows from table users");
        }
    }


    public void removeUser(int id) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            int count = statement.executeUpdate(new StringBuilder().append("DELETE FROM users WHERE _id='").append(id).append("';").toString());
            System.out.println("Deleted " + count + " rows from table users");
        }
    }


    public User getUser(int id) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String request = String.format("SELECT * FROM users WHERE _id = %d;", id);
            ResultSet resultSet = statement.executeQuery(request);
            while (resultSet.next()) {
                int idd = resultSet.getInt("_id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                return new User(idd, name, age);
            }
        }
        return null;
    }

}
