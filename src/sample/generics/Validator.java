package sample.generics;

public interface Validator<E extends Entity> {
    public void validate(E e) throws ValidationException;
}
