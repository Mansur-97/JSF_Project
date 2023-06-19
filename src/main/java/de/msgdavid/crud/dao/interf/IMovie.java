package de.msgdavid.crud.dao.interf;

import de.msgdavid.crud.entity.Movies;

import java.util.List;

public interface IMovie {
    public List<Movies> readAll();
    public void add(Movies movie);
    public void update(Movies movie);
    public void delete(int id);
}
