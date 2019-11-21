package com.example.demo.controller;

import com.example.demo.model.Actor;
import com.example.demo.model.Film;

public final class Helper {

    private static String uppercaseAllWordsInAString(String string) {
        String[] words = string.split(" ");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            sb.append(Character.toUpperCase(words[i].charAt(0))).append(words[i].substring(1)).append(" ");
        }

        return sb.toString().trim();
    }

    public static void uppercaseFilm(Film film) {
        String numeFilm = film.getNume();
        film.setNume(uppercaseAllWordsInAString(numeFilm));
    }

    public static void uppercaseActor(Actor actor){
        String numeActor = actor.getNume();
        actor.setNume(uppercaseAllWordsInAString(numeActor));

        String prenumeActor = actor.getPrenume();
        actor.setPrenume(uppercaseAllWordsInAString(prenumeActor));
    }


}
