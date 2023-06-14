package de.msgdavid.crud.bean;

import de.msgdavid.crud.dao.factory.DaoFactory;
import de.msgdavid.crud.entity.Movie;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean(name = "moviemanager")
@RequestScoped
public class MovieBean {
    private List<Movie> movieList;

    @PostConstruct
    public void init() {
        movieList = DaoFactory.getInstance().getMovieDaoImpl().readAll();
    }
    public List<Movie> movieList() {
        return movieList;
    }

    public String addMovie(Movie movie) {
        return DaoFactory.getInstance().getMovieDaoImpl().addMovie(movie);
    }
    public String getMovie(int movieID) {
        return DaoFactory.getInstance().getMovieDaoImpl().getMovie(movieID);
    }
    public String updateMovie(Movie movie) {
        return DaoFactory.getInstance().getMovieDaoImpl().updateMovie(movie);
    }
    public String deleteMovie(int movieID) {
        return DaoFactory.getInstance().getMovieDaoImpl().deleteMovie(movieID);
    }
}