package com.example.demo.repository;

import com.example.demo.model.Film;
import org.junit.Test;


import static org.junit.Assert.assertTrue;

public class FilmRepositoryTestThatPerformsInsertUpdateDelete {
    DbConnectionTest connectionTest = new DbConnectionTest();
    FilmRepository filmRepository = new FilmRepository(connectionTest);




    @Test
    public void addFilm() {
        Film filmInsertedIntoDB = new Film();
        filmInsertedIntoDB.setNume("Avatar");
        filmInsertedIntoDB.setGen(8);
        filmInsertedIntoDB.setAnulAparitiei(2009);

        filmRepository.addFilm(filmInsertedIntoDB);


        int id = filmRepository.getFilmID(filmInsertedIntoDB);
        Film filmReturnedByDB = filmRepository.getFilm(id);


        assertTrue(filmInsertedIntoDB.getNume().equals(filmReturnedByDB.getNume()));
        assertTrue(filmInsertedIntoDB.getGen() == filmReturnedByDB.getGen());
        assertTrue(filmInsertedIntoDB.getAnulAparitiei() == filmReturnedByDB.getAnulAparitiei());

        filmRepository.deleteFilm(id);

    }


    @Test
    public void updateFilm() {
        Film filmInsertedIntoDB = new Film();
        filmInsertedIntoDB.setNume("Avatar");
        filmInsertedIntoDB.setGen(8);
        filmInsertedIntoDB.setAnulAparitiei(2009);
        filmRepository.addFilm(filmInsertedIntoDB);

        int id = filmRepository.getFilmID(filmInsertedIntoDB);



        Film filmUpdatedIntoDB = new Film();
        filmUpdatedIntoDB.setId(id);
        filmUpdatedIntoDB.setNume("Avatarrrrr");
        filmUpdatedIntoDB.setGen(8);
        filmUpdatedIntoDB.setAnulAparitiei(2009);

        filmRepository.updateFilm(id, filmUpdatedIntoDB);

        Film filmReturnedByDB = filmRepository.getFilm(id);

        assertTrue(filmUpdatedIntoDB.getNume().equals(filmReturnedByDB.getNume()));
        assertTrue(filmUpdatedIntoDB.getGen() == filmReturnedByDB.getGen());
        assertTrue(filmUpdatedIntoDB.getAnulAparitiei() == filmReturnedByDB.getAnulAparitiei());

        filmRepository.deleteFilm(id);

    }


    @Test
    public void deleteFilm() {
        Film filmInsertedIntoDB = new Film();
        filmInsertedIntoDB.setNume("Avatar");
        filmInsertedIntoDB.setGen(8);
        filmInsertedIntoDB.setAnulAparitiei(2009);
        filmRepository.addFilm(filmInsertedIntoDB);

        int id = filmRepository.getFilmID(filmInsertedIntoDB);


        filmRepository.deleteFilm(id);

        Film filmReturnedByDB = filmRepository.getFilm(id);


        assertTrue(filmReturnedByDB.getNume() == null);
        assertTrue(filmReturnedByDB.getGen() == 0);
        assertTrue(filmReturnedByDB.getAnulAparitiei() == 0);

    }
}
