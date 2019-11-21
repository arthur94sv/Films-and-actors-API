package com.example.demo.repository;

import com.example.demo.model.Actor;
import com.example.demo.model.FilmForActor;

import org.junit.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ActorRepositoryTestThatPerformsSelect {


    DbConnectionTest connectionTest = new DbConnectionTest();
    ActorRepository actorRepository = new ActorRepository(connectionTest);


    @Test
    public void getAllActors() {
        List<Actor> listOfActorsExpected = new ArrayList<>();

        Actor actor1 = new Actor();
        actor1.setId(1);
        actor1.setNume("Dicaprio");
        actor1.setPrenume("Leonardo");
        listOfActorsExpected.add(actor1);

        Actor actor2 = new Actor();
        actor2.setId(2);
        actor2.setNume("Bale");
        actor2.setPrenume("Christian");
        listOfActorsExpected.add(actor2);

        Actor actor3 = new Actor();
        actor3.setId(3);
        actor3.setNume("Winslet");
        actor3.setPrenume("Kate");
        listOfActorsExpected.add(actor3);

        Actor actor4 = new Actor();
        actor4.setId(4);
        actor4.setNume("Hanks");
        actor4.setPrenume("Tom");
        listOfActorsExpected.add(actor4);

        List<Actor> listOfActorsReturnedByDB = actorRepository.getAllActors();

        assertTrue(listOfActorsExpected.containsAll(listOfActorsReturnedByDB));

    }


    @Test
    public void getActorIDValuePositive() {
        Actor actorExpected = new Actor();
        actorExpected.setId(1);
        actorExpected.setNume("Dicaprio");
        actorExpected.setPrenume("Leonardo");


        Actor actorReturnedByDB = actorRepository.getActor(1);

        assertTrue(actorExpected.getId() == actorReturnedByDB.getId());
        assertTrue(actorExpected.getNume().equals(actorReturnedByDB.getNume()));
        assertTrue(actorExpected.getPrenume().equals(actorReturnedByDB.getPrenume()));
    }

    @Test
    public void getActorIDValueZero() {
        Actor actorReturnedByDB = actorRepository.getActor(0);

        assertTrue(actorReturnedByDB.getPrenume() == null);
        assertTrue(actorReturnedByDB.getNume() == null);
    }


    @Test
    public void getAllFilmsForAnActorIDValuePositive() {
        List<FilmForActor> listOfFilmsExpected = new ArrayList<>();

        FilmForActor film1 = new FilmForActor();
        film1.setNume("The Prestige");
        film1.setAnulAparitiei(2006);
        film1.setGenul("Drama");
        listOfFilmsExpected.add(film1);

        FilmForActor film2 = new FilmForActor();
        film2.setNume("American Psycho");
        film2.setAnulAparitiei(2000);
        film2.setGenul("Crima");
        listOfFilmsExpected.add(film2);

        //Actor with the ID 2, is Christian Bale
        List<FilmForActor> listOfFilmsReturnedByDB = actorRepository.getAllFilmsForAnActor(2);


        assertTrue(listOfFilmsExpected.containsAll(listOfFilmsReturnedByDB));

    }

    @Test
    public void getAllFilmsForAnActorIDValueZero() {
        List<FilmForActor> listOfFilmsReturnedByDB = actorRepository.getAllFilmsForAnActor(0);

        assertTrue(listOfFilmsReturnedByDB.isEmpty());
    }


}
