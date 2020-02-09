package sample.services;

import sample.entities.Membru;
import sample.entities.StatusMembru;
import sample.repositories.RepositoryMembru;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class ServiceMembri {
    private RepositoryMembru repo;

    public ServiceMembri(RepositoryMembru repo) {
        this.repo = repo;
    }

    public Membru updateMembru(Membru m, StatusMembru statusMembru){
        m.setStatus(statusMembru);
        return repo.update(m);
    }


    public List<Membru> getMembrii(){
        List<Membru> membrii = new LinkedList<>();
        repo.findAll().forEach(x->membrii.add(x));

        return membrii;

    }

    public List<Membru> getMembriiActivi(){
        List<Membru> membrii = getMembrii();

        return membrii.stream().filter(x-> x.getStatus().equals(StatusMembru.ACTIV)).collect(Collectors.toList());

    }

    public void loadData(String filePath) throws IOException {
        repo.readFromFile(filePath);
    }

    public void saveData(String filePath) throws IOException {
        repo.writeToFile(filePath);
    }


    public Membru findMembru(int id){
        return repo.findOne(id);
    }


}
