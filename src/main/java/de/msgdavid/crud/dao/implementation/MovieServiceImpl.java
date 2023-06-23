package de.msgdavid.crud.dao.implementation;

import de.msgdavid.crud.dao.interf.MovieService;
import de.msgdavid.crud.entity.Movies;
import de.msgdavid.crud.repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepo movieRepo;

    @Override
    public List<Movies> getAllMovies() {
        return movieRepo.findAll();
    }

    @Override
    public void saveMovie(Movies movies) {
        this.movieRepo.save(movies);
    }

    @Override
    public Movies getMovieById(int id) {
        Optional<Movies> optional = movieRepo.findById(id);
        Movies movies;

        if (optional.isPresent()) {
            movies = optional.get();
        } else {
            throw new RuntimeException("Movie not found for id { "+ id + " }");
        }
        return movies;
    }

    @Override
    public void deleteMovieById(int id) {
        this.movieRepo.deleteById(id);
    }
}
