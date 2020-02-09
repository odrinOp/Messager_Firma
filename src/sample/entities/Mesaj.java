package sample.entities;

import sample.generics.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Mesaj extends Entity<Integer> {
    private String numeExpeditor;
    private String mesaj;
    private LocalDateTime ora;



    public Mesaj(String numeExpeditor, String mesaj, LocalDateTime ora) {
        this.numeExpeditor = numeExpeditor;
        this.mesaj = mesaj;
        this.ora = ora;
    }

    public String getNumeExpeditor() {
        return numeExpeditor;
    }

    public void setNumeExpeditor(String numeExpeditor) {
        this.numeExpeditor = numeExpeditor;
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    public LocalDateTime getOra() {
        return ora;
    }

    public void setOra(LocalDateTime ora) {
        this.ora = ora;
    }



    @Override
    public String toString() {
        return numeExpeditor + ";" + mesaj + ";" + ora;
    }

}
