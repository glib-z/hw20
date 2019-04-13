#Дз20

Есть класс User:

class User {
 int id;
 String name;
 int age;
}

Написать интерфейс Storage, и класс FileStorage который в конструкторе принимает имя файла где будут храниться данные:


(++)- void removeAll()

(++)- void removeUser(int id)

(++)- void removeUserByName(String name)

(++)- void addUser(User user)

(++)- void updateUser(User user)

(++)- User getUser(int id)

(++)- List<User> getAllUsers()


(++)Продемонстрирвоать работу всех методов.

(+)Данные должны храниться в тектовом файле в ормате JSON.

*(+)Данные должны храниться в PostgreSQL.

(+)При добавлении метод addUser должен назначить User уникальный id - порядковый номер.

===========================================================

The older methods of loading the driver for postgresql are still supported but they are no longer necessary.
The next code has been removed:

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

===========================================================


