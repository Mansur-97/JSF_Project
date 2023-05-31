package de.msgdavid.crud.movie;

import java.util.ArrayList;
public class Movie {
    private int movieID;
    private String movieTitle;
    private String genre;
    private String movieLength;
    private double imdbRating;
    private int releaseYear;
    public ArrayList<Movie> movieListFromDB;
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
}
