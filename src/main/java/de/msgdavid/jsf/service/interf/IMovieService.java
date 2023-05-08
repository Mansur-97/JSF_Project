package de.msgdavid.jsf.service.interf;

import de.msgdavid.jsf.model.MovieBO;

import java.util.ArrayList;

public interface IMovieService {
    public ArrayList<MovieBO> getAllMovies();
    public void addMovie(MovieBO movieBO);
    public void updateMovie(MovieBO movieBO);
    public void deleteMovie(MovieBO movieBO);
}
