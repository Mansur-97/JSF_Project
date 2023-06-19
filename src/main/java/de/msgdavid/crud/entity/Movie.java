package de.msgdavid.crud.entity;

import javax.persistence.*;

@Entity
@Table(name = "movielist")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int movieID;
    @Column(name = "MovieTitle")
    private String movieTitle;
    @Column(name = "Genre")
    private String genre;
    @Column(name = "MovieLength")
    private String movieLength;
    @Column(name = "ImdbRating")
    private double imdbRating;
    @Column(name = "ReleaseYear")
    private int releaseYear;

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
        return "Movie{" +
                "movieTitle='" + movieTitle + '\'' +
                ", genre='" + genre + '\'' +
                ", movieLength='" + movieLength + '\'' +
                ", imdbRating=" + imdbRating +
                ", releaseYear=" + releaseYear +
                '}';
    }
}
