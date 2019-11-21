package com.example.demo.controller;

import com.example.demo.model.Actor;
import com.example.demo.model.Film;
import org.junit.Test;

import static org.junit.Assert.*;

public class HelperTest {

    @Test
    public void uppercaseFilmSingleWord() {
       Film film = new Film();
       film.setNume("titanic");

       Helper.uppercaseFilm(film);

       assertEquals("Titanic", film.getNume());

    }

    @Test
    public void uppercaseFilmTwoWords() {
        Film film = new Film();
        film.setNume("the prestige");

        Helper.uppercaseFilm(film);

        assertEquals("The Prestige", film.getNume());

    }

    @Test
    public void uppercaseActor() {
        Actor actor = new Actor();
        actor.setNume("carrey");
        actor.setPrenume("jim");

        Helper.uppercaseActor(actor);

        assertEquals("Carrey", actor.getNume());
        assertEquals("Jim", actor.getPrenume());

    }

    @Test
    public void uppercaseActorDouaPrenume(){
        Actor actor = new Actor();
        actor.setNume("deep");
        actor.setPrenume("john christopher");

        Helper.uppercaseActor(actor);

        assertEquals("Deep", actor.getNume());
        assertEquals("John Christopher", actor.getPrenume());
    }
}
