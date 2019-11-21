package com.example.demo.controller;

import com.example.demo.model.Actor;
import com.example.demo.model.Film;
import com.example.demo.model.FilmForActor;
import com.example.demo.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping("/actors")
    public List<Actor> getAllActors(@RequestParam(value = "numeActor", required = false) String numeActor) {
        return actorService.getAllActors(numeActor);
    }

    @GetMapping("/actors/{id}")
    public Actor getActor(@PathVariable int id) {
        return actorService.getActor(id);
    }

    @GetMapping("/actors/{id}/films")
    public List<FilmForActor> getAllFilmsForAnActor(@PathVariable("id") int idActor) {
        return actorService.getAllTheFilmsForAnActor(idActor);
    }

    @PostMapping("/actors")
    public void addActor(@RequestBody Actor actor) {
        Helper.uppercaseActor(actor);
        actorService.addActor(actor);
    }


    @PostMapping("/actors/{id}/films")
    public void addFilmToAnActor(@PathVariable("id") int idActor, @RequestBody Film film) {
        Helper.uppercaseFilm(film);
        actorService.addFilmToAnActor(idActor, film);
    }

    @PutMapping("/actors/{id}")
    public void updateActor(@PathVariable int id, @RequestBody Actor actor) {
        Helper.uppercaseActor(actor);
        actorService.updateActor(id, actor);
    }


    @DeleteMapping("/actors/{id}")
    public void deleteActor(@PathVariable int id) {
        actorService.deleteActor(id);
    }


}
