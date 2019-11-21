package com.example.demo.service;

import com.example.demo.model.Actor;
import com.example.demo.model.Film;
import com.example.demo.model.FilmForActor;
import com.example.demo.repository.ActorRepository;
import com.example.demo.repository.FilmRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ActorServiceTest {

    ActorRepository mockedActorRepository = mock(ActorRepository.class);


    ActorService actorService = new ActorService(mockedActorRepository);
    List<Actor> listOfActorsReturnedByRepository = new ArrayList<>();

    @Before
    public void setUp() {
        listOfActorsReturnedByRepository.add(new Actor());
    }


    @Test
    public void getAllActorsWhenParameterNull() {

        when(mockedActorRepository.getAllActors()).thenReturn(listOfActorsReturnedByRepository);

        List<Actor> listOfActorsReturnedByService = actorService.getAllActors(null);

        assertEquals(listOfActorsReturnedByService, listOfActorsReturnedByRepository);
    }

    @Test
    public void getAllActorsWhenParameterNotNull() {

        when(mockedActorRepository.getAllActorsByName("Dicaprio")).thenReturn(listOfActorsReturnedByRepository);

        List<Actor> listOfActorsReturnedByService = actorService.getAllActors("Dicaprio");

        assertEquals(listOfActorsReturnedByService, listOfActorsReturnedByRepository);
    }

    @Test
    public void getActor() {
        Actor actorReturnedByRepository = new Actor();

        when(mockedActorRepository.getActor(1)).thenReturn(actorReturnedByRepository);

        Actor actorReturnedByService = actorService.getActor(1);

        assertEquals(actorReturnedByService, actorReturnedByRepository);

    }

    Actor actor = new Actor();

    @Test
    public void addActor() {
        actorService.addActor(actor);

        verify(mockedActorRepository).addActor(actor);

    }

    @Test
    public void updateActor() {
        actorService.updateActor(1, actor);

        verify(mockedActorRepository).updateActor(1, actor);

    }

    @Test
    public void deleteActor() {
        actorService.deleteActor(1);

        verify(mockedActorRepository).deleteActor(1);
    }

    @Test
    public void getAllTheFilmsForAnActor() {
        List<FilmForActor> listOfFilmReturnedByRepository = new ArrayList<>();
        listOfFilmReturnedByRepository.add(new FilmForActor());

        when(mockedActorRepository.getAllFilmsForAnActor(1)).thenReturn(listOfFilmReturnedByRepository);

        List<FilmForActor> listOfFilmsReturnedByService = actorService.getAllTheFilmsForAnActor(1);


        assertEquals(listOfFilmReturnedByRepository, listOfFilmsReturnedByService);

    }

    @Test
    public void addFilmToAnActor() {
        Film film = new Film();
        actorService.addFilmToAnActor(1, film);

        verify(mockedActorRepository).addFilmToAnActor(1, film);

    }


}
