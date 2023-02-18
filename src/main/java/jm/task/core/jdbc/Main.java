package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;
import jm.task.core.jdbc.model.User;
import org.hibernate.Session;

public class Main extends User {

    public static void main(String[] args) {


        Util.getSessionFactory();
        UserDao userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();

        userDaoHibernate.saveUser("Михаил", "Михайлов", (byte) 17);
        userDaoHibernate.saveUser("Егор", "Егоров", (byte) 19);
        userDaoHibernate.saveUser("Света", "Светова", (byte) 25);
        userDaoHibernate.saveUser("Катя", "Катева", (byte) 36);
        userDaoHibernate.getAllUsers();
        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();


        System.out.println();
        System.out.println("==================================================");
        System.out.println();



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