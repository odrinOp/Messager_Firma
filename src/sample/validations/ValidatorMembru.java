package sample.validations;

import sample.entities.Membru;
import sample.generics.ValidationException;
import sample.generics.Validator;

public class ValidatorMembru implements Validator<Membru> {
    @Override
    public void validate(Membru membru) throws ValidationException {
        String errors = "";
        if(membru.getNume().equals("") || membru.getNume() == null)
            errors += "Nume invalid!\n";
        if(membru.getRol() == null)
            errors += "Rol nedefinit!\n";

        if(!errors.equals(""))
            throw new ValidationException(errors);
    }
}
