package de.msgdavid.crud.movieBean;

import de.msgdavid.crud.movie.Movie;
import de.msgdavid.crud.database.operation.DatabaseOperation;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;

@ManagedBean(name = "movie")
@RequestScoped
public class MovieBean extends Movie {

    @PostConstruct
    public void init() {
        movieListFromDB = DatabaseOperation.getMovieListFromDB();
    }
    public ArrayList<MovieBean> movieList() {
        return movieListFromDB;
    }
    public String saveMovieDetails(MovieBean newMovieObject) {
        return DatabaseOperation.saveMovieDetailInDB(newMovieObject);
    }
    public String editMovieRecord(int movieID) {
        return DatabaseOperation.editMovieRecordInDB(movieID);
    }
    public String updateMovieDetails(MovieBean updateMovieObject) {
        return DatabaseOperation.updateMovieDetailInDB(updateMovieObject);
    }
    public String deleteMovieRecord(int movieID) {
        return DatabaseOperation.deleteMovieRecordInDB(movieID);
    }
}