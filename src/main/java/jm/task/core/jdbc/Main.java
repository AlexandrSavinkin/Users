package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;
import jm.task.core.jdbc.model.User;

public class Main extends User{

    public static void main(String[] args)  {
        Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();

        userDao.saveUser("Alexandr","Savinkin", (byte) 38);
        userDao.saveUser("Ivan","Ivanov", (byte) 45);
        userDao.saveUser("Nikolai","Nikolaev", (byte) 34);
        userDao.saveUser("Marina","Marinova", (byte) 25);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();

    }
}