package de.msgdavid.crud.controller;

import de.msgdavid.crud.dao.interf.MovieService;
import de.msgdavid.crud.entity.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/movieList")
    public String viewHomePage(Model model) {
        model.addAttribute("listMovies", movieService.getAllMovies());
        return "index";
    }

    @GetMapping("/showNewMovieForm")
    public String showNewMovieForm(Model model) {
        Movies movies = new Movies();
        model.addAttribute("movies", movies);
        return "new_movie";
    }

    @PostMapping("/saveGame")
    public String saveMovie(@ModelAttribute("movies") Movies movies) {
        movieService.saveMovie(movies);
        return "redirect:/index";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") int id, Model model) {
        Movies movies = movieService.getMovieById(id);
        model.addAttribute("movies", movies);
        return "update_Movies";
    }

    @GetMapping("/deleteGame/{id}")
    public String deleteMovie(@PathVariable(value = "id") int id) {
        this.movieService.deleteMovieById(id);
        return "redirect:/index";
    }
}
