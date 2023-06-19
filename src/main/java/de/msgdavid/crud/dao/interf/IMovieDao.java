package de.msgdavid.crud.dao.interf;

import de.msgdavid.crud.entity.Movies;

import java.util.List;

public interface IMovieDao {
    List<Movies> readAll();
    String add(Movies movies);
    String get(int id);
    String update(Movies movies);
    String delete(int id);
}
