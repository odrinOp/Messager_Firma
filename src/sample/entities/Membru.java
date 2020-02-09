package sample.entities;

import sample.generics.Entity;

public class Membru extends Entity<Integer> {
    String nume;
    TipMembru rol;
    StatusMembru status;

    public Membru(String nume, TipMembru rol) {
        setId(0);
        this.nume = nume;
        this.rol = rol;
        this.status = StatusMembru.ACTIV;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public TipMembru getRol() {
        return rol;
    }

    public void setRol(TipMembru rol) {
        this.rol = rol;
    }

    public StatusMembru getStatus() {
        return status;
    }

    public void setStatus(StatusMembru status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return nume + ":"+rol;
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof Membru){
            Membru m = (Membru) object;
            return m.nume.equals(this.nume) && m.rol.equals(this.rol);
        }
        return false;
    }
}
