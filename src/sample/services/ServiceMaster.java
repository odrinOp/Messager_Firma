package sample.services;

import sample.entities.Membru;
import sample.entities.Mesaj;
import sample.entities.StatusMembru;
import sample.generics.Observable;

import java.io.IOException;
import java.util.List;

public class ServiceMaster extends Observable{
    private ServiceMembri serviceMembri;
    private ServiceMesaj serviceMesaj;
    private String filePath;
    public ServiceMaster(ServiceMembri serviceMembri, ServiceMesaj serviceMesaj,String filePath) {
        this.serviceMembri = serviceMembri;
        this.serviceMesaj = serviceMesaj;
        this.filePath = filePath;
    }

    public void loadData(){
        try {
            serviceMembri.loadData(filePath+"/Echipa.txt");
            serviceMesaj.loadData(filePath+"/discutiiCuSefu.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void saveData(){
        try {
            serviceMesaj.saveData(filePath+"/discutiiCuSefu.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Membru updateMembru(Membru membru, StatusMembru statusMembru){
        Membru result =  serviceMembri.updateMembru(membru,statusMembru);
        if(result!=null)
            return result;
        notifyObservers();
        return null;
    }


    public Mesaj addMesaj(Membru membru,String mesaj){
        Mesaj result = serviceMesaj.addMesaj(membru,mesaj);
        if(result != null)
            return result;

        saveData();
        notifyObservers();
        return null;

    }


    public List<Membru> getMembrii(){
        return serviceMembri.getMembrii();
    }

    public List<Membru> getMembriiActivi(){
        return serviceMembri.getMembriiActivi();
    }

    public List<Mesaj> getMesage(){
        return serviceMesaj.getAllMessages();
    }


    public Membru findMembru(int id){
        return serviceMembri.findMembru(id);
    }
}
