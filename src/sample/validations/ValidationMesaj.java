package sample.validations;

import sample.entities.Mesaj;
import sample.generics.ValidationException;
import sample.generics.Validator;

public class ValidationMesaj implements Validator<Mesaj> {
    @Override
    public void validate(Mesaj mesaj) throws ValidationException {
        String errors ="";
        if(mesaj.getNumeExpeditor().equals("") || mesaj.getNumeExpeditor() == null)
            errors +="Nume invalid!\n";
        if(mesaj.getMesaj().equals("") || mesaj.getMesaj() == null)
            errors += "Textul mesajului invalid!\n";

        if(!errors.equals(""))
            throw new ValidationException(errors);


    }
}
