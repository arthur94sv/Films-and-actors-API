package com.example.demo.service;

import com.example.demo.model.Actor;
import com.example.demo.model.Film;
import com.example.demo.repository.ActorRepository;
import com.example.demo.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {

    private FilmRepository filmRepository;


    @Autowired
    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;

    }

    public List<Film> getAllFilms(Integer idGen, String numeFilm) {
        if (idGen != null)
            return filmRepository.getAllFilmsByGenre(idGen);
        else if (numeFilm != null)
            return filmRepository.getAllFilmsByName(numeFilm);
        else
            return filmRepository.getAllFilms();
    }

    public Film getFilm(int id) {
        return filmRepository.getFilm(id);
    }

    public void addFilm(Film film) {
        filmRepository.addFilm(film);
    }

    public void updateFilm(int id, Film film) {
        filmRepository.updateFilm(id, film);
    }

    public void deleteFilm(int id) {
        filmRepository.deleteFilm(id);
    }

    public List<Actor> getAllActorsForAFilm(int idFilm) {
        return filmRepository.getAllActorsForAFilm(idFilm);
    }

    public void addAnActorForAMovie(int idFilm, Actor actor) {
        filmRepository.addAnActorForAMovie(idFilm, actor);
    }
}
