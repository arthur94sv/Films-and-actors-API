package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class JoinTableFilmActor {
    Connection connection;
    PreparedStatement preparedStatement;

    @Autowired
    public JoinTableFilmActor(DbConnection dbConnection) {
        this.connection = dbConnection.getConnection();

    }


    public void mappingIDActorAndIDFilmToJoinTable(int idActor, int idFilm) {
        String query = "INSERT INTO ACTOR_FILM (film_id, actor_id)\n" +
                "VALUES (?,?)";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idFilm);
            preparedStatement.setInt(2, idActor);

            preparedStatement.execute();

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                System.out.println("Error during rollback");
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
