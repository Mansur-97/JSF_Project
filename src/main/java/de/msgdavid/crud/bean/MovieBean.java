package de.msgdavid.crud.bean;

import de.msgdavid.crud.dao.factory.DaoFactory;
import de.msgdavid.crud.entity.Movies;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean(name = "moviemanager")
@RequestScoped
public class MovieBean {
    private List<Movies> movieList;

    Movies movies = new Movies();

    public Movies getMovies() {
        return movies;
    }

    public void setMovies(Movies movies) {
        this.movies = movies;
    }

    @PostConstruct
    public void init() {
        movieList = DaoFactory.getInstance().getHibernateMovieImpl().readAll();
    }
    public List<Movies> movieList() {
        return movieList;
    }
    public String addMovie(Movies movies) {
        return DaoFactory.getInstance().getHibernateMovieImpl().add(movies);
    }
    public String getMovie(int id) {
        return DaoFactory.getInstance().getHibernateMovieImpl().get(id);
    }
    public String updateMovie(Movies movies) {
        return DaoFactory.getInstance().getHibernateMovieImpl().update(movies);
    }
    public String deleteMovie(int id) {
        return DaoFactory.getInstance().getHibernateMovieImpl().delete(id);
    }
}