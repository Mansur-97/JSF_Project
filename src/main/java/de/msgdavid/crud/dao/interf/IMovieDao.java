package de.msgdavid.crud.dao.interf;

import de.msgdavid.crud.entity.Movie;

import java.util.List;

public interface IMovieDao {
    List<Movie> readAll();
    //void addMovie(Movie movie);
    //void updateMovie(Movie movie);
    //void deleteMovie(Movie movie);
}
