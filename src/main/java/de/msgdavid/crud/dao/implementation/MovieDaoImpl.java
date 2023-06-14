package de.msgdavid.crud.dao.implementation;

import de.msgdavid.crud.dao.connection.ConnectionFactory;
import de.msgdavid.crud.dao.interf.IMovieDao;
import de.msgdavid.crud.entity.Movie;

import javax.faces.context.FacesContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MovieDaoImpl implements IMovieDao {
    private  Statement statementObject;
    private  Connection connectionObject;
    private  ResultSet resultSetObject;
    private  PreparedStatement preparedStatementObject;

    @Override
    public List<Movie> readAll() {
        List<Movie> movieList = new ArrayList<>();
        try {
            connectionObject = ConnectionFactory.getInstance().getConnection();
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

    @Override
    public String addMovie(Movie movie) {
        int saveResult = 0;
        String navigationResult;
        try {
            connectionObject = ConnectionFactory.getInstance().getConnection();
            preparedStatementObject = connectionObject.prepareStatement("insert into movielist (MovieTitle, " +
                    "Genre, MovieLength, ImdbRating, ReleaseYear) values (?, ?, ?, ?, ?)");
            preparedStatementObject.setString(1, movie.getMovieTitle());
            preparedStatementObject.setString(2, movie.getGenre());
            preparedStatementObject.setString(3, movie.getMovieLength());
            preparedStatementObject.setDouble(4, movie.getImdbRating());
            preparedStatementObject.setInt(5, movie.getReleaseYear());
            saveResult = preparedStatementObject.executeUpdate();
            connectionObject.close();
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        if(saveResult !=0) {
            navigationResult = "movieList.xhtml?faces-redirect=true";
        } else {
            navigationResult = "createMovie.xhtml?faces-redirect=true";
        }
        return navigationResult;
    }

    @Override
    public String getMovie(int movie) {
        Movie editRecord = null;
        System.out.println("editMovieRecordInDB() : Movie ID: " + movie);

        Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
            connectionObject = ConnectionFactory.getInstance().getConnection();
            statementObject = connectionObject.createStatement();
            resultSetObject = statementObject.executeQuery("select * from movielist where ID = "+ movie);
            if(resultSetObject != null) {
                resultSetObject.next();
                editRecord = new Movie();
                SetMovieObject(editRecord);
            }
            sessionMapObj.put("editMovieObject", editRecord);
            connectionObject.close();
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        return "/editMovies.xhtml?faces-redirect=true";
    }

    @Override
    public String updateMovie(Movie movie) {
        try {
            connectionObject = ConnectionFactory.getInstance().getConnection();
            preparedStatementObject = connectionObject.prepareStatement("update movielist set MovieTitle=?, " +
                    "Genre=?, MovieLength=?, ImdbRating=?, ReleaseYear=? where ID=?");
            preparedStatementObject.setString(1,movie.getMovieTitle());
            preparedStatementObject.setString(2,movie.getGenre());
            preparedStatementObject.setString(3,movie.getMovieLength());
            preparedStatementObject.setDouble(4,movie.getImdbRating());
            preparedStatementObject.setInt(5,movie.getReleaseYear());
            preparedStatementObject.setInt(6,movie.getMovieID());
            preparedStatementObject.executeUpdate();
            connectionObject.close();
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        return "/movieList.xhtml?faces-redirect=true";
    }

    @Override
    public String deleteMovie(int movieID) {
        System.out.println("deleteMovieRecord() : Student ID: " + movieID);
        try {
            connectionObject = ConnectionFactory.getInstance().getConnection();
            preparedStatementObject = connectionObject.prepareStatement("delete from movielist where ID = "+movieID);
            preparedStatementObject.executeUpdate();
            connectionObject.close();
        } catch(Exception sqlException){
            sqlException.printStackTrace();
        }
        return "/movieList.xhtml?faces-redirect=true";
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
