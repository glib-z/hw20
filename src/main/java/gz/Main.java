package gz;

import gz.model.User;
import gz.storage.FileStorage;
import gz.storage.SQLStorage;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {

    static List<User> users = new ArrayList<User>();
    static User user = new User();


    public static void main(String[] args) {
        testFileStorage();
        testSQLStorage();
    }


    private static void testFileStorage() {

        System.out.println("\n================================================================================");
        System.out.println("FileStorage testing...");

        FileStorage fileStorage = new FileStorage("users");

        fileStorage.addUser(new User(UUID.randomUUID().hashCode(), "First", 1));
        fileStorage.addUser(new User(UUID.randomUUID().hashCode(), "Second", 2));
        System.out.println("\nNew users have been added. List of users:");
        users.addAll(fileStorage.getAllUsers());
        System.out.println(users);

        fileStorage.removeAll();
        users.clear();
        users.addAll(fileStorage.getAllUsers());
        System.out.println("\nAll users have been removed. List of users:\n" + users);

        fileStorage.addUser(new User(UUID.randomUUID().hashCode(), "Third", 3));
        fileStorage.addUser(new User(UUID.randomUUID().hashCode(), "Fourth", 4));
        fileStorage.addUser(new User(UUID.randomUUID().hashCode(), "Fifth", 5));
        fileStorage.addUser(new User(UUID.randomUUID().hashCode(), "Sixth", 6));
        fileStorage.addUser(new User(UUID.randomUUID().hashCode(), "Seventh", 7));
        fileStorage.addUser(new User(UUID.randomUUID().hashCode(), "Eighth", 8));
        users.clear();
        users.addAll(fileStorage.getAllUsers());
        System.out.println("\nNew users have been added. List of users:\n" + users);

        fileStorage.removeUserByName("Fifth");
        users.clear();
        users.addAll(fileStorage.getAllUsers());
        System.out.println("\nUser 'Fifth' have been removed. List of users:\n" + users);

        System.out.println("\nRemoving user with ID=" + users.get(2).getId() + "...");
        fileStorage.removeUser(users.get(2).getId());
        users.clear();
        users.addAll(fileStorage.getAllUsers());
        System.out.println("\nList of users:\n" + users);

        user = fileStorage.getUser(users.get(2).getId());
        System.out.println("\nGet user by ID=" + users.get(2).getId() + ":\n" + user);

        fileStorage.updateUser(new User(user.getId(), user.getName() + " Boss", user.getAge() + 30));
        users.clear();
        users.addAll(fileStorage.getAllUsers());
        System.out.println("\nUser with ID=" + users.get(2).getId() + " have been changed. List of users:\n" + users);

    }


    private static void testSQLStorage() {

        System.out.println("\n================================================================================");
        System.out.println("SQLStorage testing...");

        SQLStorage sqlStorage = new SQLStorage("users");

        sqlStorage.addUser(new User(UUID.randomUUID().hashCode(), "First", 1));
        sqlStorage.addUser(new User(UUID.randomUUID().hashCode(), "Second", 2));
        System.out.println("\nNew users have been added. List of users:");
        users.addAll(sqlStorage.getAllUsers());
        System.out.println(users);

        sqlStorage.removeAll();
        users.clear();
        users.addAll(sqlStorage.getAllUsers());
        System.out.println("\nAll users have been removed. List of users:\n" + users);

        sqlStorage.addUser(new User(UUID.randomUUID().hashCode(), "Third", 3));
        sqlStorage.addUser(new User(UUID.randomUUID().hashCode(), "Fourth", 4));
        sqlStorage.addUser(new User(UUID.randomUUID().hashCode(), "Fifth", 5));
        sqlStorage.addUser(new User(UUID.randomUUID().hashCode(), "Sixth", 6));
        sqlStorage.addUser(new User(UUID.randomUUID().hashCode(), "Seventh", 7));
        sqlStorage.addUser(new User(UUID.randomUUID().hashCode(), "Eighth", 8));
        users.clear();
        users.addAll(sqlStorage.getAllUsers());
        System.out.println("\nNew users have been added. List of users:\n" + users);

        sqlStorage.removeUserByName("Fifth");
        users.clear();
        users.addAll(sqlStorage.getAllUsers());
        System.out.println("\nUser 'Fifth' have been removed. List of users:\n" + users);

        System.out.println("\nRemoving user with ID=" + users.get(2).getId() + "...");
        sqlStorage.removeUser(users.get(2).getId());
        users.clear();
        users.addAll(sqlStorage.getAllUsers());
        System.out.println("\nList of users:\n" + users);

        user = sqlStorage.getUser(users.get(2).getId());
        System.out.println("\nGet user by ID=" + users.get(2).getId() + ":\n" + user);

        sqlStorage.updateUser(new User(user.getId(), user.getName() + " Boss", user.getAge() + 30));
        users.clear();
        users.addAll(sqlStorage.getAllUsers());
        System.out.println("\nUser with ID=" + users.get(2).getId() + " have been changed. List of users:\n" + users);

    }

}
