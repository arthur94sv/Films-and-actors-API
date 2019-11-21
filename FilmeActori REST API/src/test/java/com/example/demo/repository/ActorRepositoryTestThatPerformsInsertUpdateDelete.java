package com.example.demo.repository;

import com.example.demo.model.Actor;


import org.junit.Test;


import static org.junit.Assert.assertTrue;

public class ActorRepositoryTestThatPerformsInsertUpdateDelete {

    DbConnectionTest connectionTest = new DbConnectionTest();
    ActorRepository actorRepository = new ActorRepository(connectionTest);

    FilmRepository filmRepository = new FilmRepository(connectionTest);


    @Test
    public void addActor() {
        Actor actorToBeInsertedIntoDB = new Actor();

        actorToBeInsertedIntoDB.setNume("Ford");
        actorToBeInsertedIntoDB.setPrenume("Harrison");

        actorRepository.addActor(actorToBeInsertedIntoDB);

        int id = actorRepository.getActorID(actorToBeInsertedIntoDB);
        Actor actorReturnedByDB = actorRepository.getActor(id);


        assertTrue(actorToBeInsertedIntoDB.getNume().equals(actorReturnedByDB.getNume()));
        assertTrue(actorToBeInsertedIntoDB.getPrenume().equals(actorReturnedByDB.getPrenume()));



        actorRepository.deleteActor(id);


    }


    @Test
    public void updateActor() {
        Actor actorToBeInsertedIntoDB = new Actor();
        actorToBeInsertedIntoDB.setNume("Ford");
        actorToBeInsertedIntoDB.setPrenume("Harrison");
        actorRepository.addActor(actorToBeInsertedIntoDB);

        int id = actorRepository.getActorID(actorToBeInsertedIntoDB);

        Actor theActorUpdated = new Actor();
        theActorUpdated.setId(id);
        theActorUpdated.setNume("Forddd");
        theActorUpdated.setPrenume("Harrissonn");



        actorRepository.updateActor(id, theActorUpdated);

        Actor actorReturnedByDB = actorRepository.getActor(id);


        assertTrue(theActorUpdated.getNume().equals(actorReturnedByDB.getNume()));
        assertTrue(theActorUpdated.getPrenume().equals(actorReturnedByDB.getPrenume()));

        actorRepository.deleteActor(id);
    }



    @Test
    public void deleteActor() {
        Actor actorToBeInsertedIntoDB = new Actor();
        actorToBeInsertedIntoDB.setNume("Ford");
        actorToBeInsertedIntoDB.setPrenume("Harrison");

        actorRepository.addActor(actorToBeInsertedIntoDB);

        int id = actorRepository.getActorID(actorToBeInsertedIntoDB);


        actorRepository.deleteActor(id);

        Actor actorReturnedByDB = actorRepository.getActor(id);


        assertTrue(actorReturnedByDB.getPrenume() == null);
        assertTrue(actorReturnedByDB.getNume() == null);

    }

}
