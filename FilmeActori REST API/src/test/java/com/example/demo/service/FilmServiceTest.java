package com.example.demo.service;

import com.example.demo.model.Actor;
import com.example.demo.model.Film;
import com.example.demo.repository.ActorRepository;
import com.example.demo.repository.FilmRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class FilmServiceTest {
    FilmRepository mockedFilmRepository = mock(FilmRepository.class);


    FilmService filmService = new FilmService(mockedFilmRepository);


    List<Film> listOfFilmsReturnedByRepository = new ArrayList<>();
    List<Film> listOfFilmsReturnedByService;

    @Before
    public void setUp() {
        listOfFilmsReturnedByRepository.add(new Film());
    }

    @Test
    public void getAllFilmsWhenAllParametersNull() {
        when(mockedFilmRepository.getAllFilms()).thenReturn(listOfFilmsReturnedByRepository);

        listOfFilmsReturnedByService = filmService.getAllFilms(null, null);

        assertEquals(listOfFilmsReturnedByService, listOfFilmsReturnedByRepository);
    }

    @Test
    public void getAllFilmsIDGenParameterPositive() {
        when(mockedFilmRepository.getAllFilmsByGenre(1)).thenReturn(listOfFilmsReturnedByRepository);

        listOfFilmsReturnedByService = filmService.getAllFilms(1, null);

        assertEquals(listOfFilmsReturnedByService, listOfFilmsReturnedByRepository);
    }

    @Test
    public void getAllFilmsNumeFilmParameterPositive() {
        when(mockedFilmRepository.getAllFilmsByName("Titanic")).thenReturn(listOfFilmsReturnedByRepository);

        listOfFilmsReturnedByService = filmService.getAllFilms(null, "Titanic");

        assertEquals(listOfFilmsReturnedByService, listOfFilmsReturnedByRepository);
    }


    @Test
    public void getFilm() {
        Film filmReturnedByRepository = new Film();
        when(mockedFilmRepository.getFilm(1)).thenReturn(filmReturnedByRepository);

        Film film = filmService.getFilm(1);

        assertEquals(filmReturnedByRepository, film);
    }


    Film film = new Film();

    @Test
    public void addFilm() {
        filmService.addFilm(film);

        verify(mockedFilmRepository).addFilm(film);
    }

    @Test
    public void updateFilm() {
        filmService.updateFilm(1, film);

        verify(mockedFilmRepository).updateFilm(1, film);

    }

    @Test
    public void deleteFilm() {
        filmService.deleteFilm(1);

        verify(mockedFilmRepository).deleteFilm(1);

    }

    @Test
    public void getAllActorsForAFilm() {
        List<Actor> listOfActorsReturnedByRepository = new ArrayList<>();
        listOfActorsReturnedByRepository.add(new Actor());

        when(mockedFilmRepository.getAllActorsForAFilm(1)).thenReturn(listOfActorsReturnedByRepository);

        List<Actor> listOfActors = filmService.getAllActorsForAFilm(1);

        assertEquals(listOfActors, listOfActorsReturnedByRepository);
    }

    @Test
    public void addAnActorForAMovie() {
        Actor actor = new Actor();
        filmService.addAnActorForAMovie(1, actor);

        verify(mockedFilmRepository).addAnActorForAMovie(1, actor);
    }
}
