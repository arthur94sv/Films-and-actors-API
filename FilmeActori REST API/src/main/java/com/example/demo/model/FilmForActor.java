package com.example.demo.model;
/*
Objects of this class will be used to store all the relevant film information for a particular actor. Instances of this class
will be created and added into a List, resulting in a complete list of films in which a particular actor has starred.
The fields 'nume' and  'anulAparitiei' are storing information  from the FILM table, while 'genul' stores the values
from the 'GEN' table. Thus, an instance of this cass contains values that are the result of an INNER JOIN on the two mentioned
tables. The INNER JOIN can be seen at ActorRepository -> getAllFilmsForAnActor(int idActor);
 */

public class FilmForActor {
    private String nume;
    private int anulAparitiei;
    private String genul;

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

    public String getGenul() {
        return genul;
    }

    public void setGenul(String genul) {
        this.genul = genul;
    }
}
