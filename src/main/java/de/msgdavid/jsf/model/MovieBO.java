package de.msgdavid.jsf.model;

import de.msgdavid.crud.movieBean.MovieBean;

import java.util.ArrayList;

/**
 * Contains all data from the database
 */
public class MovieBO {
    private int movieID;
    private String movieTitle;
    private String genre;
    private String movieLength;
    private double imdbRating;
    private int releaseYear;
    public ArrayList<MovieBean> movieListFromDB;

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getMovieLength() {
        return movieLength;
    }

    public void setMovieLength(String movieLength) {
        this.movieLength = movieLength;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return "MovieBO{" +
                "movieID=" + movieID +
                ", movieTitle='" + movieTitle + '\'' +
                ", genre='" + genre + '\'' +
                ", movieLength='" + movieLength + '\'' +
                ", imdbRating=" + imdbRating +
                ", releaseYear=" + releaseYear +
                ", movieListFromDB=" + movieListFromDB +
                '}';
    }
}
