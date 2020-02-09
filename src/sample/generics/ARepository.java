package sample.generics;

import java.io.*;
import java.util.LinkedList;
import java.util.List;


public abstract class ARepository<ID,E extends Entity> implements IRepository<ID,E> {
    protected List<E> list;
    private Validator validator;

    public ARepository(Validator validator) {
        this.validator = validator;
        this.list = new LinkedList<>();
    }


    public abstract ID getGeneratedID();

    @Override
    public E save(E e) throws ValidationException {


        //1. Validate entity
        validator.validate(e);

        //2. Verify if the entity is not saved in the repository
        e.setId(getGeneratedID());
        if(list.contains(e))
            return e;

        //3. All done, the entity is saved in repository
        list.add(e);
        return null;
    }

    @Override
    public E update(E e) throws ValidationException {
        validator.validate(e);

        for(E temp: list)
            if(temp.equals(e)) {
                temp = e;
                return null;
            }

        return e;
    }

    @Override
    public E remove(E e) {
        if(!list.contains(e))
            return e;

        //varianta 1
        E removedEl = list.get(list.indexOf(e));
        list.remove(e);

        return removedEl;


        // varianta 2

        /*
        int index = list.indexOf(e);
        return list.remove(index);

         */
    }

    @Override
    public E findOne(ID id) {

        for(E e: list){
            if(e.getId().equals(id))
                return e;
        }
        return null;
    }

    @Override
    public Iterable<E> findAll() {
        return list;
    }


    public void readFromFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while((line = reader.readLine()) != null){
            E e = convertFromString(line);
            if(e != null)
                save(e);
        }
        reader.close();

    }

    public void writeToFile(String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

        String data = "";
        for(E e:list){
            data += e.toString() + "\n";
        }

        writer.write(data);
        writer.close();
    }


    public abstract E convertFromString(String s);

}
