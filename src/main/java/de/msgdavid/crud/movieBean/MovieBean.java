package de.msgdavid.crud.movieBean;

import de.msgdavid.crud.model.Movie;
import de.msgdavid.crud.database.operation.DatabaseOperation;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;

@ManagedBean(name = "moviemanager")
@RequestScoped
public class MovieBean {

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    Movie movie = new Movie();

    @PostConstruct
    public void init() {
        movie.movieListFromDB = DatabaseOperation.getMovieListFromDB();
    }
    public ArrayList<Movie> movieList() {
        return movie.movieListFromDB;
    }
    public String saveMovieDetails(Movie newMovieObject) {
        return DatabaseOperation.saveMovieDetailInDB(newMovieObject);
    }
    public String editMovieRecord(int movieID) {
        return DatabaseOperation.editMovieRecordInDB(movieID);
    }
    public String updateMovieDetails(Movie updateMovieObject) {
        return DatabaseOperation.updateMovieDetailInDB(updateMovieObject);
    }
    public String deleteMovieRecord(int movieID) {
        return DatabaseOperation.deleteMovieRecordInDB(movieID);
    }
}