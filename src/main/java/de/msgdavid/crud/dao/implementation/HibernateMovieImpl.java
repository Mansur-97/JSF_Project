package de.msgdavid.crud.dao.implementation;

import de.msgdavid.crud.dao.interf.IMovieDao;
import de.msgdavid.crud.entity.Movies;
import de.msgdavid.crud.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class HibernateMovieImpl implements IMovieDao {
    @Override
    public List<Movies> readAll() {
        List<Movies> movieList = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            System.out.println("Beginning Transaction");
            movieList = session.createQuery("from Movies", Movies.class).list();
            transaction.commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return movieList;
    }

    @Override
    public String add(Movies movies) {
        Transaction transaction = null;
        int saveResult = 0;
        String navResult;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.persist(movies);

            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            exception.printStackTrace();
        }
        if(saveResult !=0) {
            navResult = "movieList.xhtml?faces-redirect=true";
        } else {
            navResult = "createMovie.xhtml?faces-redirect=true";
        }
        return navResult;
    }

    @Override
    public String get(int id) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.get(Movies.class, id);

            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            exception.printStackTrace();
        }
        return "/editMovies.xhtml?faces-redirect=true";
    }

    @Override
    public String update(Movies editMovieObject) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.merge(editMovieObject);

            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            exception.printStackTrace();
        }
        return "/movieList.xhtml?faces-redirect=true";
    }

    @Override
    public String delete(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Movies movies = session.get(Movies.class, id);
            if (movies != null) {
                session.remove(movies);
                System.out.println("Movie deleted");
            }

            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            exception.printStackTrace();
        }
        return "/movieList.xhtml?faces-redirect=true";
    }
}
