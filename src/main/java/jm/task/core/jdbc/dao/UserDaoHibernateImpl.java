package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import jm.task.core.jdbc.util.Util;
import org.hibernate.query.Query;

import java.util.Iterator;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(10) NOT NULL, lastName VARCHAR(10) NOT NULL, " +
                    "age TINYINT NOT NULL)";
            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("Таблица уже создана");
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS users";
            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
            System.out.println("Таблица удалена");
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Таблица не существует");
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        User user = new User();
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("INSERT Users ( name, lastName, age) VALUE (?,?,?)");
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.persist(user);
            System.out.println("User" + " с именем - " + name + " добавлен в базу данных");
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {

                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        User user = new User();
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String sql = "DELETE FROM Users WHERE id=" + id;
            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
            session.delete(user);
        } catch (Exception e) {
            if (transaction != null) {

                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    @Override
    public List<User> getAllUsers() {
        List<User> users;
        Session session = Util.getSessionFactory().openSession();
        {
            String sql = "From " + User.class.getSimpleName();
            users = session.createQuery(sql).list();
        }
        for (Iterator<User> it = users.iterator();
             it.hasNext(); ) {
            User user = it.next();
            System.out.println(user.toString());
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            String stringQuery = "DELETE FROM User";
            Query query = session.createQuery(stringQuery);
            query.executeUpdate();
            System.out.println("Таблица чиста");
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



