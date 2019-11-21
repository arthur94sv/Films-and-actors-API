package com.example.demo.repository;

import com.example.demo.model.Actor;
import com.example.demo.model.Film;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FilmRepositoryTestThatPerformsSelect {
    DbConnectionTest connectionTest = new DbConnectionTest();
    FilmRepository filmRepository = new FilmRepository(connectionTest);

    @Test
    public void getAllFilms() {
        List<Film> listOfFilmsExpected = new ArrayList<>();

        Film film1 = new Film();
        film1.setId(1);
        film1.setNume("Titanic");
        film1.setGen(4);
        film1.setAnulAparitiei(1997);
        listOfFilmsExpected.add(film1);

        Film film2 = new Film();
        film2.setId(2);
        film2.setNume("The Prestige");
        film2.setGen(4);
        film2.setAnulAparitiei(2006);
        listOfFilmsExpected.add(film2);

        Film film3 = new Film();
        film3.setId(3);
        film3.setNume("American Psycho");
        film3.setGen(3);
        film3.setAnulAparitiei(2000);
        listOfFilmsExpected.add(film3);

        Film film4 = new Film();
        film4.setId(4);
        film4.setNume("Forrest Gump");
        film4.setGen(2);
        film4.setAnulAparitiei(1994);
        listOfFilmsExpected.add(film4);

        List<Film> listOfFilmsReturnedByDB = filmRepository.getAllFilms();

        assertTrue(listOfFilmsExpected.containsAll(listOfFilmsReturnedByDB));
    }

    @Test
    public void getFilmIDValuePositive() {
        Film filmExpected = new Film();
        filmExpected.setId(1);
        filmExpected.setNume("Titanic");
        filmExpected.setGen(4);
        filmExpected.setAnulAparitiei(1997);

        Film filmReturnedByDB = filmRepository.getFilm(1);

        assertTrue(filmExpected.getId() == filmReturnedByDB.getId());
        assertTrue(filmExpected.getNume().equals(filmReturnedByDB.getNume()));
        assertTrue(filmExpected.getGen() == filmReturnedByDB.getGen());
        assertTrue(filmExpected.getAnulAparitiei() == filmReturnedByDB.getAnulAparitiei());
    }

    @Test
    public void getFilmIDValueZero() {
        Film filmReturnedByDB = filmRepository.getFilm(0);


        assertTrue(filmReturnedByDB.getNume() == null);
        assertTrue(filmReturnedByDB.getGen() == 0);
        assertTrue(filmReturnedByDB.getAnulAparitiei() == 0);

    }



    @Test
    public void getAllActorsForAFilmIDPositive() {
        List<Actor> listOfActorsExpected = new ArrayList<>();

        Actor actor1 = new Actor();
        actor1.setId(1);
        actor1.setNume("Dicaprio");
        actor1.setPrenume("Leonardo");
        listOfActorsExpected.add(actor1);

        Actor actor2 = new Actor();
        actor2.setId(3);
        actor2.setNume("Winslet");
        actor2.setPrenume("Kate");
        listOfActorsExpected.add(actor2);

        //The film with ID 1 is Titanic
        List<Actor> listOfActorsReturnedByDB = filmRepository.getAllActorsForAFilm(1);


        assertTrue(listOfActorsExpected.containsAll(listOfActorsReturnedByDB));
    }

    @Test
    public void getAllActorsForAFilmIDZero() {
        List<Actor> listOfActorsReturnedByDB = filmRepository.getAllActorsForAFilm(0);

        assertTrue(listOfActorsReturnedByDB.isEmpty());
    }



    @Test
    public void getAllFilmsByGenreIDValuePositive() {
        List<Film> listOfFilmsExpected = new ArrayList<>();

        Film film1 = new Film();
        film1.setId(1);
        film1.setNume("Titanic");
        film1.setGen(4);
        film1.setAnulAparitiei(1997);
        listOfFilmsExpected.add(film1);

        Film film2 = new Film();
        film2.setId(2);
        film2.setNume("The Prestige");
        film2.setGen(4);
        film2.setAnulAparitiei(2006);
        listOfFilmsExpected.add(film2);

        //The genre with ID 4 is Drama
        List<Film> listOfFilmsReturnedByDB = filmRepository.getAllFilmsByGenre(4);


        assertTrue(listOfFilmsExpected.containsAll(listOfFilmsReturnedByDB));
    }

    @Test
    public void getAllFilmsByGenreIDValueZero() {
        List<Film> listOfFilmReturnedByDB = filmRepository.getAllFilmsByGenre(0);

        assertTrue(listOfFilmReturnedByDB.isEmpty());
    }

  }
