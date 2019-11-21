package com.example.demo.service;

import com.example.demo.model.Actor;
import com.example.demo.model.Film;
import com.example.demo.model.FilmForActor;
import com.example.demo.repository.ActorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    private ActorRepository actorRepository;


    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;

    }

    public List<Actor> getAllActors(String numeActor) {
        if (numeActor != null)
            return actorRepository.getAllActorsByName(numeActor);
        else
            return actorRepository.getAllActors();
    }



    public Actor getActor(int id) {
        return actorRepository.getActor(id);
    }

    public void addActor(Actor actor) {
        actorRepository.addActor(actor);
    }

    public void updateActor(int id, Actor actor) {
        actorRepository.updateActor(id, actor);
    }

    public void deleteActor(int id) {
        actorRepository.deleteActor(id);
    }

    public List<FilmForActor> getAllTheFilmsForAnActor(int idActor) {
        return actorRepository.getAllFilmsForAnActor(idActor);
    }

    public void addFilmToAnActor(int idActor, Film film) {
        actorRepository.addFilmToAnActor(idActor, film);

    }

}
