package de.msgdavid.crud.database.operation;

import de.msgdavid.crud.movie.Movie;

import javax.faces.context.FacesContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

import static de.msgdavid.crud.database.operation.DatabaseConnection.getConnection;



public class DatabaseOperation {
    public static Statement statementObject;
    public static Connection connectionObject;
    public static ResultSet resultSetObject;
    public static PreparedStatement preparedStatementObject;


    public static ArrayList<Movie> getMovieListFromDB() {
        ArrayList<Movie> movieList = new ArrayList<>();
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

    private static void SetMovieObject(Movie movieObject) throws SQLException {
        movieObject.setMovieID(resultSetObject.getInt("ID"));
        movieObject.setMovieTitle(resultSetObject.getString("MovieTitle"));
        movieObject.setGenre(resultSetObject.getString("Genre"));
        movieObject.setMovieLength(resultSetObject.getString("MovieLength"));
        movieObject.setImdbRating(resultSetObject.getDouble("ImdbRating"));
        movieObject.setReleaseYear(resultSetObject.getInt("ReleaseYear"));
    }

    public static String saveMovieDetailInDB(Movie newMovieObject) {
        int saveResult = 0;
        String navigationResult;
        try {
            preparedStatementObject = getConnection().prepareStatement("insert into movielist (MovieTitle, Genre, MovieLength, ImdbRating, ReleaseYear) values (?, ?, ?, ?, ?)");
            preparedStatementObject.setString(1, newMovieObject.getMovieTitle());
            preparedStatementObject.setString(2, newMovieObject.getGenre());
            preparedStatementObject.setString(3, newMovieObject.getMovieLength());
            preparedStatementObject.setDouble(4, newMovieObject.getImdbRating());
            preparedStatementObject.setInt(5, newMovieObject.getReleaseYear());
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

    public static String editMovieRecordInDB(int movieID) {
        Movie editRecord = null;
        System.out.println("editMovieRecordInDB() : Movie ID: " + movieID);

        Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
            statementObject = getConnection().createStatement();
            resultSetObject = statementObject.executeQuery("select * from movielist where ID = "+movieID);
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

    public static String updateMovieDetailInDB(Movie updateMovieObject) {
        try {
            preparedStatementObject = getConnection().prepareStatement("update movielist set MovieTitle=?, Genre=?, " +
                    "MovieLength=?, ImdbRating=?, ReleaseYear=? where ID=?");
            preparedStatementObject.setString(1,updateMovieObject.getMovieTitle());
            preparedStatementObject.setString(2,updateMovieObject.getGenre());
            preparedStatementObject.setString(3,updateMovieObject.getMovieLength());
            preparedStatementObject.setDouble(4,updateMovieObject.getImdbRating());
            preparedStatementObject.setInt(5,updateMovieObject.getReleaseYear());
            preparedStatementObject.setInt(6,updateMovieObject.getMovieID());
            preparedStatementObject.executeUpdate();
            connectionObject.close();
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        return "/movieList.xhtml?faces-redirect=true";
    }

    public static String deleteMovieRecordInDB(int movieID){
        System.out.println("deleteMovieRecord() : Student ID: " + movieID);
        try {
            preparedStatementObject = getConnection().prepareStatement("delete from movielist where ID = "+movieID);
            preparedStatementObject.executeUpdate();
            connectionObject.close();
        } catch(Exception sqlException){
            sqlException.printStackTrace();
        }
        return "/movieList.xhtml?faces-redirect=true";
    }
}
