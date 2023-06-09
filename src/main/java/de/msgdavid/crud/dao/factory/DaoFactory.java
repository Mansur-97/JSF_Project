package de.msgdavid.crud.dao.factory;

import de.msgdavid.crud.dao.implementation.MovieDaoImpl;

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
}
