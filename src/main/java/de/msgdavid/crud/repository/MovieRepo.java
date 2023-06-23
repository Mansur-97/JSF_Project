package de.msgdavid.crud.repository;

import de.msgdavid.crud.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movies, Integer> {
}
