package sample.repositories;

import sample.entities.Mesaj;
import sample.generics.ARepository;
import sample.generics.Validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class RepositoryMesaj extends ARepository<Integer, Mesaj> {
    public RepositoryMesaj(Validator validator) {
        super(validator);
    }

    @Override
    public Integer getGeneratedID() {
        return this.list.size() + 1;
    }

    @Override
    public Mesaj convertFromString(String s) {
        String [] values = s.split(";");
        if(values.length != 3)
            return null;

        String numeExp = values[0];
        String mesaj = values[1];
        LocalDateTime ora = LocalDateTime.parse(values[2]);

        return new Mesaj(numeExp,mesaj,ora);

    }
}
