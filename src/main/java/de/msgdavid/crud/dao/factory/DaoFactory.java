package de.msgdavid.crud.dao.factory;

import de.msgdavid.crud.dao.implementation.HibernateMovieImpl;
import de.msgdavid.crud.dao.implementation.MovieDaoImpl;
import de.msgdavid.crud.dao.implementation.MovieServiceImpl;

public class DaoFactory {
    private static final DaoFactory factory = new DaoFactory();

    private DaoFactory() {

    }
    public static DaoFactory getInstance() {
        return factory;
    }
    public MovieDaoImpl getMovieDaoImpl() {
        return new MovieDaoImpl();
    }
    public HibernateMovieImpl getHibernateMovieImpl() {
        return new HibernateMovieImpl();
    }
    public MovieServiceImpl getMovieServiceImpl() {
        return new MovieServiceImpl();
    }
}
