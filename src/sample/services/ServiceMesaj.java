package sample.services;

import sample.entities.Membru;
import sample.entities.Mesaj;
import sample.repositories.RepositoryMesaj;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class ServiceMesaj {
    private RepositoryMesaj repo;

    public ServiceMesaj(RepositoryMesaj repo) {
        this.repo = repo;
    }


    public Mesaj addMesaj(Membru membru, String mesaj){
        LocalDateTime ora = LocalDateTime.now();
        return repo.save(new Mesaj(membru.getNume(),mesaj,ora));
    }


    public void loadData(String filePath) throws IOException {
        repo.readFromFile(filePath);
    }

    public void saveData(String filePath) throws IOException {
        repo.writeToFile(filePath);
    }


    public List<Mesaj> getAllMessages(){
        List<Mesaj> mesaje = new LinkedList<>();

        repo.findAll().forEach(x->mesaje.add(x));
        return mesaje;
    }




}
