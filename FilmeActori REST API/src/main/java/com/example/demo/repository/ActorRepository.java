package com.example.demo.repository;

import com.example.demo.model.Actor;
import com.example.demo.model.Film;
import com.example.demo.model.FilmForActor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ActorRepository {
    private DbConnection dbConnection;
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private Actor actor;
    private List<Actor> listOfActors;

    private FilmForActor filmForActor;
    private List<FilmForActor> listOfFilms;

    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private JoinTableFilmActor joinTableFilmActor;

    @Autowired
    public ActorRepository(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
        listOfActors = new ArrayList<>();
        listOfFilms = new ArrayList<>();

    }

    public List<Actor> getAllActors() {
        String query = "SELECT *\n" +
                "FROM ACTOR ORDER BY nume";
        listOfActors.clear();
        try {
            connection = dbConnection.getConnection();
            statement = dbConnection.getStatement();

            resultSet = statement.executeQuery(query);
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
                System.out.println("Error when getting all actors");
                e.printStackTrace();
            } catch (SQLException ex) {
                System.out.println("Error during rollback when getting all actors");
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection when getting all actors");
                e.printStackTrace();
            }
        }

        return listOfActors;
    }

    public Actor getActor(int id) {
        String query = "SELECT * FROM ACTOR\n" +
                " WHERE actor_id = ?";

        actor = new Actor();

        try {
            connection = dbConnection.getConnection();
            statement = dbConnection.getStatement();

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                actor.setId(resultSet.getInt("actor_id"));
                actor.setNume(resultSet.getString("nume"));
                actor.setPrenume(resultSet.getString("prenume"));


            }
        } catch (SQLException e) {
            try {
                connection.rollback();
                System.out.println("Error when getting actor by ID");
                e.printStackTrace();
            } catch (SQLException ex) {
                System.out.println("Error during rollback when getting actor by ID");
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection when getting actor by ID");
                e.printStackTrace();
            }
        }

        return actor;
    }


    public void addActor(Actor actor) {
        String query = "INSERT INTO ACTOR (nume, prenume)\n " +
                "VALUES (?, ?)";

        try {
            connection = dbConnection.getConnection();
            statement = dbConnection.getStatement();

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, actor.getNume());
            preparedStatement.setString(2, actor.getPrenume());


            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
                System.out.println("Error when adding actor");
                e.printStackTrace();
            } catch (SQLException ex) {
                System.out.println("Error during rollback when adding actor");
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection when adding actor");
                e.printStackTrace();
            }
        }


    }

    public void updateActor(int id, Actor actor) {
        String query = "UPDATE ACTOR\n" +
                "SET nume = ?, prenume = ?\n" +
                "WHERE actor_id = ?";

        try {
            connection = dbConnection.getConnection();
            statement = dbConnection.getStatement();

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, actor.getNume());
            preparedStatement.setString(2, actor.getPrenume());


            preparedStatement.setInt(3, actor.getId());


            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
                System.out.println("Error when updating actor");
                e.printStackTrace();
            } catch (SQLException ex) {
                System.out.println("Error during rollback when updating actor");
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection when updating actor");
                e.printStackTrace();
            }
        }

    }

    public void deleteActor(int id) {
        String query = "DELETE FROM ACTOR\n" +
                "WHERE actor_id = ?";

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
                System.out.println("Error when deleting an actor");
                e.printStackTrace();
            } catch (SQLException ex) {
                System.out.println("Error during rollback when deleting an actor");
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection when deleting an actor");
                e.printStackTrace();
            }
        }

    }

    public List<FilmForActor> getAllFilmsForAnActor(int idActor) {
        String query = "SELECT nume_film, anul_aparitiei, genul\n" +
                "FROM FILM\n" +
                "INNER JOIN ACTOR_FILM ON FILM.FILM_ID = ACTOR_FILM.FILM_ID\n" +
                "INNER JOIN GEN ON FILM.GEN_ID = GEN.GEN_ID\n" +
                "WHERE ACTOR_FILM.ACTOR_ID = ?\n" +
                "ORDER BY nume_film";
        listOfFilms.clear();

        try {
            connection = dbConnection.getConnection();
            statement = dbConnection.getStatement();

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, idActor);

            resultSet = preparedStatement.executeQuery();
            connection.commit();

            while (resultSet.next()) {

                filmForActor = new FilmForActor();

                filmForActor.setNume(resultSet.getString("nume_film"));
                filmForActor.setAnulAparitiei(resultSet.getInt("anul_aparitiei"));
                filmForActor.setGenul(resultSet.getString("genul"));

                listOfFilms.add(filmForActor);


            }
        } catch (SQLException e) {
            try {
                connection.rollback();
                System.out.println("Error when getting all films for a specific actor");
                e.printStackTrace();
            } catch (SQLException ex) {
                System.out.println("Error during rollback when getting all films for a specific actor");
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection when getting all films for a specific actor");
                e.printStackTrace();
            }
        }


        return listOfFilms;

    }

    public List<Actor> getAllActorsByName(String numeActor) {
        String query = "SELECT * FROM ACTOR\n" +
                " WHERE nume = ?";
        listOfActors.clear();
        try {
            connection = dbConnection.getConnection();
            statement = dbConnection.getStatement();

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, numeActor);

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
                System.out.println("Error when getting actors by name");
                e.printStackTrace();
            } catch (SQLException ex) {
                System.out.println("Error during rollback when getting actors by name");
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection when getting actors by name");
                e.printStackTrace();
            }
        }

        return listOfActors;

    }

    public void addFilmToAnActor(int idActor, Film film) {

        try {
            connection = dbConnection.getConnection();
            statement = dbConnection.getStatement();

            filmRepository.addFilm(film);
            int idFilm = filmRepository.getFilmID(film);
            joinTableFilmActor.mappingIDActorAndIDFilmToJoinTable(idActor, idFilm);

            connection.commit();
        } catch (SQLException e) {
            try {
                e.printStackTrace();
                System.out.println("Error when adding a film to a specific actor");
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Error during rollback when adding a film to a specific actor");
                ex.printStackTrace();
            }

        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection when adding a film to a specific actor");
                e.printStackTrace();
            }
        }

    }

    public int getActorID(Actor actor) {
        String query = "SELECT actor_id FROM actor WHERE nume = ? AND prenume = ?";
        int actorID = 0;

        try {
            connection = dbConnection.getConnection();
            statement = dbConnection.getStatement();

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, actor.getNume());
            preparedStatement.setString(2, actor.getPrenume());

            resultSet = preparedStatement.executeQuery();

            connection.commit();

            while (resultSet.next()) {
                actorID = resultSet.getInt("actor_id");
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
                System.out.println("Error when getting an actor's ID");
                e.printStackTrace();
            } catch (SQLException ex) {
                System.out.println("Error during rollback when getting an actor's ID");
                ex.printStackTrace();
            }

        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection when getting an actor's ID");
                e.printStackTrace();
            }
        }

        return actorID;
    }


}
