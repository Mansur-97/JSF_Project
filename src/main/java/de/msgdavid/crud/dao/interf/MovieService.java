package de.msgdavid.crud.dao.interf;

import de.msgdavid.crud.entity.Movies;

import java.util.List;

public interface MovieService {

    List<Movies> getAllMovies();
    void saveMovie(Movies movies);
    Movies getMovieById(int id);
    void deleteMovieById(int id);
}
