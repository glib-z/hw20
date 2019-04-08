#Дз20

Есть класс User:

class User {
 int id;
 String name;
 int age;
}

Написать интерфейс Storage, и класс FileStorage который в конструкторе принимает имя файла где будут храниться данные:


(++)- void removeAll()

(+)- void removeUser(int id)

(+)- void removeUserByName(String name)

(++)- void addUser(User user)

(+)- void updateUser(User user)

(+)- User getUser(int id)

(++)- List<User> getAllUsers()


Продемонстрирвоать работу всех методов.

Данные должны храниться в тектовом файле в ормате JSON.

При добавлении метод addUser должен назначить User уникальный id - порядковый номер.