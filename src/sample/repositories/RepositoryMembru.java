package sample.repositories;

import sample.entities.Membru;
import sample.entities.TipMembru;
import sample.generics.ARepository;
import sample.generics.Validator;

public class RepositoryMembru extends ARepository<Integer, Membru> {
    public RepositoryMembru(Validator validator) {
        super(validator);
    }

    @Override
    public Integer getGeneratedID() {
        return this.list.size() + 1;
    }

    @Override
    public Membru convertFromString(String s) {
        String values[] = s.split(":");
        if(values.length != 2)
            return null;

        String nume = values[0];
        TipMembru rol = TipMembru.valueOf(values[1]);
        return new Membru(nume,rol);
    }
}
