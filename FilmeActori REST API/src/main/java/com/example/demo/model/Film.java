package com.example.demo.model;


import java.util.Objects;

public class Film {

    private Integer id;
    private String nume;
    private int anulAparitiei;
    private int gen;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getAnulAparitiei() {
        return anulAparitiei;
    }

    public void setAnulAparitiei(int anulAparitiei) {
        this.anulAparitiei = anulAparitiei;
    }

    public int getGen() {
        return gen;
    }

    public void setGen(int gen) {
        this.gen = gen;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(id, film.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
