package de.msgdavid.crud.dao.implementation;

import de.msgdavid.crud.dao.connection.ConnectionFactory;
import de.msgdavid.crud.dao.interf.IMovieDao;
import de.msgdavid.crud.entity.Movies;

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
    public List<Movies> readAll() {
        List<Movies> movieList = new ArrayList<>();
        try {
            connectionObject = ConnectionFactory.getInstance().getConnection();
            statementObject = connectionObject.createStatement();
            resultSetObject = statementObject.executeQuery("select * from movie_list");
            while (resultSetObject.next()) {
                Movies movieObject = new Movies();
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
    public String add(Movies movies) {
        int saveResult = 0;
        String navigationResult;
        try {
            connectionObject = ConnectionFactory.getInstance().getConnection();
            preparedStatementObject = connectionObject.prepareStatement("insert into movie_list (movie_title, " +
                    "movie_genre, movie_length, imdb_rating, release_year) values (?, ?, ?, ?, ?)");
            preparedStatementObject.setString(1, movies.getTitle());
            preparedStatementObject.setString(2, movies.getGenre());
            preparedStatementObject.setString(3, movies.getLength());
            preparedStatementObject.setDouble(4, movies.getImdbRating());
            preparedStatementObject.setInt(5, movies.getReleaseYear());
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
    public String get(int id) {
        Movies editRecord = null;
        System.out.println("editMovieRecordInDB() : Movie ID: " + id);

        Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
            connectionObject = ConnectionFactory.getInstance().getConnection();
            statementObject = connectionObject.createStatement();
            resultSetObject = statementObject.executeQuery("select * from movie_list where id = "+ id);
            if(resultSetObject != null) {
                resultSetObject.next();
                editRecord = new Movies();
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
    public String update(Movies movies) {
        try {
            connectionObject = ConnectionFactory.getInstance().getConnection();
            preparedStatementObject = connectionObject.prepareStatement("update movie_list set movie_title=?, " +
                    "movie_genre=?, movie_length=?, imdb_rating=?, release_year=? where id=?");
            preparedStatementObject.setString(1,movies.getTitle());
            preparedStatementObject.setString(2,movies.getGenre());
            preparedStatementObject.setString(3,movies.getLength());
            preparedStatementObject.setDouble(4,movies.getImdbRating());
            preparedStatementObject.setInt(5,movies.getReleaseYear());
            preparedStatementObject.setInt(6,movies.getId());
            preparedStatementObject.executeUpdate();
            connectionObject.close();
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        return "/movieList.xhtml?faces-redirect=true";
    }

    @Override
    public String delete(int movieID) {
        System.out.println("deleteMovieRecord() : Student ID: " + movieID);
        try {
            connectionObject = ConnectionFactory.getInstance().getConnection();
            preparedStatementObject = connectionObject.prepareStatement("delete from movie_list where ID = "+movieID);
            preparedStatementObject.executeUpdate();
            connectionObject.close();
        } catch(Exception sqlException){
            sqlException.printStackTrace();
        }
        return "/movieList.xhtml?faces-redirect=true";
    }


    private void SetMovieObject(Movies movieObject) throws SQLException {
        movieObject.setId(resultSetObject.getInt("id"));
        movieObject.setTitle(resultSetObject.getString("movie_title"));
        movieObject.setGenre(resultSetObject.getString("movie_genre"));
        movieObject.setLength(resultSetObject.getString("movie_length"));
        movieObject.setImdbRating(resultSetObject.getDouble("imdb_rating"));
        movieObject.setReleaseYear(resultSetObject.getInt("release_year"));
    }
}
