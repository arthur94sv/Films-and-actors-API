package com.example.demo.controller;

import com.example.demo.model.Actor;
import com.example.demo.model.Film;
import com.example.demo.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
public class FilmController {
    @Autowired
    private FilmService filmService;

    @GetMapping("/films")
    public List<Film> getAllFilms(@RequestParam(value = "idGen", required = false) Integer idGen, @RequestParam(value = "numeFilm", required = false) String numeFilm) {
        return filmService.getAllFilms(idGen, numeFilm);
    }

    @GetMapping("/films/{id}")
    public Film getFilm(@PathVariable int id) {
        return filmService.getFilm(id);
    }

    @GetMapping("/films/{id}/actors")
    public List<Actor> getAllActorsForAFilm(@PathVariable("id") int idFilm) {
        return filmService.getAllActorsForAFilm(idFilm);
    }

    @PostMapping("/films/{id}/actors")
    public void addAnActorForAMovie(@PathVariable("id") int idFilm, @RequestBody Actor actor) {
        Helper.uppercaseActor(actor);
        filmService.addAnActorForAMovie(idFilm, actor);
    }

    @PostMapping("/films")
    public void addFilm(@RequestBody Film film) {
        Helper.uppercaseFilm(film);
        filmService.addFilm(film);
    }

    @PutMapping("/films/{id}")
    public void updateFilm(@PathVariable int id, @RequestBody Film film) {
        Helper.uppercaseFilm(film);
        filmService.updateFilm(id, film);
    }

    @DeleteMapping("/films/{id}")
    public void deleteFilm(@PathVariable int id) {
        filmService.deleteFilm(id);
    }


}
