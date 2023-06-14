package de.msgdavid.crud.dao.interf;

import de.msgdavid.crud.entity.Movie;

import java.util.List;

public interface IMovieDao {
    List<Movie> readAll();
    String addMovie(Movie movie);
    String getMovie(int movie);
    String updateMovie(Movie movie);
    String deleteMovie(int movieID);
}
