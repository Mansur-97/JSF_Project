package de.msgdavid.crud.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "movie_list")
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "movie_title")
    private String title;
    @Column(name = "movie_genre")
    private String genre;
    @Column(name = "movie_length")
    private String length;
    @Column(name = "imdb_rating")
    private double imdbRating;
    @Column(name = "release_year")
    private int releaseYear;

    public Movies() {
    }

    public Movies(String title, String genre, String length, double imdbRating, int releaseYear) {
        this.title = title;
        this.genre = genre;
        this.length = length;
        this.imdbRating = imdbRating;
        this.releaseYear = releaseYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdb) {
        this.imdbRating = imdb;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int release) {
        this.releaseYear = release;
    }
}
