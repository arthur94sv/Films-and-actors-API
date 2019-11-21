package com.example.demo.repository;

import com.example.demo.model.Actor;
import com.example.demo.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FilmRepository {
    private DbConnection dbConnection;
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Film film;
    private List<Film> listOfFilms;

    private Actor actor;
    private List<Actor> listOfActors;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private JoinTableFilmActor joinTableFilmActor;

    @Autowired
    public FilmRepository(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
        listOfFilms = new ArrayList<>();
        listOfActors = new ArrayList<>();

    }

    public List<Film> getAllFilms() {
        String query = "SELECT * FROM FILM ORDER BY nume_film";
        listOfFilms.clear();
        try {
            connection = dbConnection.getConnection();
            statement = dbConnection.getStatement();

            resultSet = statement.executeQuery(query);
            connection.commit();

            while (resultSet.next()) {

                film = new Film();

                film.setId(resultSet.getInt("film_id"));
                film.setNume(resultSet.getString("nume_film"));
                film.setAnulAparitiei(resultSet.getInt("anul_aparitiei"));
                film.setGen(resultSet.getInt("gen_id"));

                listOfFilms.add(film);


            }
        } catch (SQLException e) {
            try {
                connection.rollback();
                System.out.println("Error when getting all films");
                e.printStackTrace();
            } catch (SQLException ex) {
                System.out.println("Error during rollback when getting all films");
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection when getting all films");
                e.printStackTrace();
            }
        }
        return listOfFilms;
    }

    public Film getFilm(int id) {
        String query = "SELECT * FROM FILM\n" +
                "WHERE film_id = ?";
        film = new Film();

        try {
            connection = dbConnection.getConnection();
            statement = dbConnection.getStatement();

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            connection.commit();

            while (resultSet.next()) {
                film.setId(resultSet.getInt("film_id"));
                film.setNume(resultSet.getString("nume_film"));
                film.setAnulAparitiei(resultSet.getInt("anul_aparitiei"));
                film.setGen(resultSet.getInt("gen_id"));


            }
        } catch (SQLException e) {
            try {
                connection.rollback();
                System.out.println("Error when getting a specific film");
                e.printStackTrace();
            } catch (SQLException ex) {
                System.out.println("Error during rollback when getting a specific film");
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection when getting a specific film");
                e.printStackTrace();
            }
        }
        return film;

    }

    public void addFilm(Film film) {
        String query = "INSERT INTO FILM (nume_film, anul_aparitiei, gen_id)\n" +
                "VALUES (?, ?, ?)";

        try {
            connection = dbConnection.getConnection();
            statement = dbConnection.getStatement();

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, film.getNume());
            preparedStatement.setInt(2, film.getAnulAparitiei());
            preparedStatement.setInt(3, film.getGen());

            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
                System.out.println("Error when adding a film");
                e.printStackTrace();
            } catch (SQLException ex) {
                System.out.println("Error during rollback when adding a film");
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection when adding a film");
                e.printStackTrace();
            }
        }
    }

    public void updateFilm(int id, Film film) {
        String query = "UPDATE FILM\n" +
                "SET nume_film = ?, anul_aparitiei = ?, gen_id = ?\n" +
                "WHERE film_id = ?";

        try {
            connection = dbConnection.getConnection();
            statement = dbConnection.getStatement();

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, film.getNume());
            preparedStatement.setInt(2, film.getAnulAparitiei());
            preparedStatement.setInt(3, film.getGen());

            preparedStatement.setInt(4, id);


            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
                System.out.println("Error when updating a film");
                e.printStackTrace();
            } catch (SQLException ex) {
                System.out.println("Error during rollback when updating a film");
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection when updating a film");
                e.printStackTrace();
            }
        }
    }

    public void deleteFilm(int id) {
        String query = "DELETE FROM FILM\n" +
                "WHERE film_id = ?";

        try {
            connection = dbConnection.getConnection();
            statement = dbConnection.getStatement();

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
                System.out.println("Error when deleting a film");
                e.printStackTrace();
            } catch (SQLException ex) {
                System.out.println("Error during rollback when deleting a film");
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection when deleting a film");
                e.printStackTrace();
            }
        }
    }

    public List<Actor> getAllActorsForAFilm(int idFilm) {
        String query = "SELECT actor.actor_id, nume, prenume\n" +
                "FROM ACTOR\n" +
                "INNER JOIN ACTOR_FILM ON ACTOR.ACTOR_ID = ACTOR_FILM.ACTOR_ID\n" +
                "WHERE ACTOR_FILM.FILM_ID = ?\n" +
                "ORDER BY actor.nume";
        listOfActors.clear();

        try {
            connection = dbConnection.getConnection();
            statement = dbConnection.getStatement();

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idFilm);

            resultSet = preparedStatement.executeQuery();
            connection.commit();


            while (resultSet.next()) {
                actor = new Actor();

                actor.setId(resultSet.getInt("actor_id"));
                actor.setNume(resultSet.getString("nume"));
                actor.setPrenume(resultSet.getString("prenume"));

                listOfActors.add(actor);
            }

        } catch (SQLException e) {
            try {
                connection.rollback();
                System.out.println("Error when getting all actors for a specific film");
                e.printStackTrace();
            } catch (SQLException ex) {
                System.out.println("Error during rollback when getting all actors for a specific film");
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection when getting all actors for a specific film");
                e.printStackTrace();
            }
        }

        return listOfActors;

    }


    public List<Film> getAllFilmsByGenre(Integer idGen) {
        String query = "SELECT *\n" +
                "FROM FILM\n" +
                "WHERE FILM.GEN_ID = ?\n"+
                "ORDER BY nume_film";
        listOfFilms.clear();

        try {
            connection = dbConnection.getConnection();
            statement = dbConnection.getStatement();

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idGen);

            resultSet = preparedStatement.executeQuery();
            connection.commit();

            while (resultSet.next()) {
                film = new Film();

                film.setId(resultSet.getInt("film_id"));
                film.setNume(resultSet.getString("nume_film"));
                film.setAnulAparitiei(resultSet.getInt("anul_aparitiei"));
                film.setGen(resultSet.getInt("gen_id"));

                listOfFilms.add(film);

            }

        } catch (SQLException e) {
            try {
                connection.rollback();
                System.out.println("Error when getting all films by genre");
                e.printStackTrace();
            } catch (SQLException ex) {
                System.out.println("Error during rollback when getting all films by genre");
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection when getting all films by genre");
                e.printStackTrace();
            }
        }
        return listOfFilms;
    }

    public List<Film> getAllFilmsByName(String numeFilm) {
        String query = "SELECT *\n" +
                "FROM FILM\n" +
                "WHERE nume_film = ?";
        listOfFilms.clear();

        try {
            connection = dbConnection.getConnection();
            statement = dbConnection.getStatement();

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, numeFilm);

            resultSet = preparedStatement.executeQuery();
            connection.commit();

            while (resultSet.next()) {
                film = new Film();

                film.setId(resultSet.getInt("film_id"));
                film.setNume(resultSet.getString("nume_film"));
                film.setAnulAparitiei(resultSet.getInt("anul_aparitiei"));
                film.setGen(resultSet.getInt("gen_id"));

                listOfFilms.add(film);

            }

        } catch (SQLException e) {
            try {
                connection.rollback();
                System.out.println("Error when getting all films by name");
                e.printStackTrace();
            } catch (SQLException ex) {
                System.out.println("Error during rollback when getting all films by name");
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection when getting all films by name");
                e.printStackTrace();
            }
        }
        return listOfFilms;
    }

    public int getFilmID(Film film) {
        String query = "SELECT film_id\n" +
                "FROM film\n" +
                "WHERE nume_film = ? AND gen_id = ? AND anul_aparitiei = ?";
        int filmID = 0;

        try {
            connection = dbConnection.getConnection();
            statement = dbConnection.getStatement();

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, film.getNume());
            preparedStatement.setInt(2, film.getGen());
            preparedStatement.setInt(3, film.getAnulAparitiei());

            resultSet = preparedStatement.executeQuery();
            connection.commit();

            while (resultSet.next()) {
                filmID = resultSet.getInt("film_id");
            }
        } catch (SQLException e) {
            try {
                e.printStackTrace();
                System.out.println("Error when getting film ID");
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Error during rollback when getting film ID");
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection when getting film ID");
                e.printStackTrace();
            }
        }

        return filmID;
    }

    public void addAnActorForAMovie(int idFilm, Actor actor) {

        try {
            connection = dbConnection.getConnection();
            statement = dbConnection.getStatement();

            actorRepository.addActor(actor);
            int idActor = actorRepository.getActorID(actor);
            joinTableFilmActor.mappingIDActorAndIDFilmToJoinTable(idActor, idFilm);

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
                System.out.println("Error when adding an actor to a film");
                e.printStackTrace();
            } catch (SQLException ex) {
                System.out.println("Error during rollback when adding an actor to a film");
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Error closing connection when adding an actor to a film");
                ex.printStackTrace();
            }
        }
    }
}
