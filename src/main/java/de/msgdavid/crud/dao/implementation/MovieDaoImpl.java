package de.msgdavid.crud.dao.implementation;

import de.msgdavid.crud.dao.interf.IMovieDao;
import de.msgdavid.crud.entity.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static de.msgdavid.crud.database.operation.DatabaseConnection.getConnection;

public class MovieDaoImpl implements IMovieDao {
    private  Statement statementObject;
    private  Connection connectionObject;
    private  ResultSet resultSetObject;
    private  PreparedStatement preparedStatementObject;

    @Override
    public List<Movie> readAll() {
        List<Movie> movieList = new ArrayList<>();
        try {
            connectionObject = getConnection();
            statementObject = connectionObject.createStatement();
            resultSetObject = statementObject.executeQuery("select * from movielist");
            while (resultSetObject.next()) {
                Movie movieObject = new Movie();
                SetMovieObject(movieObject);
                movieList.add(movieObject);
            }
            System.out.println("Total Records Fetched: " + movieList);
            connectionObject.close();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return movieList;
    }

    private void SetMovieObject(Movie movieObject) throws SQLException {
        movieObject.setMovieID(resultSetObject.getInt("ID"));
        movieObject.setMovieTitle(resultSetObject.getString("MovieTitle"));
        movieObject.setGenre(resultSetObject.getString("Genre"));
        movieObject.setMovieLength(resultSetObject.getString("MovieLength"));
        movieObject.setImdbRating(resultSetObject.getDouble("ImdbRating"));
        movieObject.setReleaseYear(resultSetObject.getInt("ReleaseYear"));
    }
}
