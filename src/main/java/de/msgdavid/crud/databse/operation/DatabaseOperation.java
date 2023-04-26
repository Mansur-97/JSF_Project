package de.msgdavid.crud.databse.operation;

import de.msgdavid.crud.movieBean.MovieBean;

import javax.faces.context.FacesContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class DatabaseOperation {
    public static Statement statementObject;
    public static Connection connectionObject;
    public static ResultSet resultSetObject;
    public static PreparedStatement preparedStatementObject;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String db_url = "jdbc:mysql://localhost:3306/movies",
                    db_userName = "root",
                    db_password = "password123";
            connectionObject = DriverManager.getConnection(db_url,db_userName,db_password);
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return connectionObject;
    }

    public static ArrayList<MovieBean> getMovieListFromDB() {
        ArrayList<MovieBean> movieList = new ArrayList<>();
        try {
            statementObject = getConnection().createStatement();
            resultSetObject = statementObject.executeQuery("select * from movielist");
            while (resultSetObject.next()) {
                MovieBean movieObject = new MovieBean();
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

    private static void SetMovieObject(MovieBean movieObject) throws SQLException {
        movieObject.setMovieID(resultSetObject.getInt("ID"));
        movieObject.setMovieTitle(resultSetObject.getString("MovieTitle"));
        movieObject.setGenre(resultSetObject.getString("Genre"));
        movieObject.setMovieLength(resultSetObject.getString("MovieLength"));
        movieObject.setImdbRating(resultSetObject.getDouble("ImdbRating"));
        movieObject.setReleaseYear(resultSetObject.getInt("ReleaseYear"));
    }

    public static String saveMovieDetailInDB(MovieBean newMovieObject) {
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
        MovieBean editRecord = null;
        System.out.println("editMovieRecordInDB() : Movie ID: " + movieID);

        Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
            statementObject = getConnection().createStatement();
            resultSetObject = statementObject.executeQuery("select * from movielist where ID = "+movieID);
            if(resultSetObject != null) {
                resultSetObject.next();
                editRecord = new MovieBean();
                SetMovieObject(editRecord);
            }
            sessionMapObj.put("editMovieObject", editRecord);
            connectionObject.close();
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        return "/editMovies.xhtml?faces-redirect=true";
    }

    public static String updateMovieDetailInDB(MovieBean updateMovieObject) {
        try {
            preparedStatementObject = getConnection().prepareStatement("update movielist set MovieTitle=?, Genre=?, MovieLength=?, ImdbRating=?, ReleaseYear=? where ID=?");
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
