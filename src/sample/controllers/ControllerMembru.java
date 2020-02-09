package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.entities.*;
import sample.generics.Observer;
import sample.generics.ValidationException;
import sample.services.ServiceMaster;
import sample.services.ServiceMembri;

import java.util.List;

public class ControllerMembru implements Observer {

    @FXML
    private ListView<String> membriiList;

    @FXML
    private TextArea mesajText;

    @FXML
    private TableView<Mesaj> mesajeTable;

    @FXML
    private Label numeLabel;

    @FXML
    private Label rolLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private Button activBtn;

    @FXML
    private Button convBtn;

    @FXML
    private Button inactivBtn;

    @FXML Button trimiteBtn;

    private Membru membru;
    private ServiceMaster master;
    boolean firstInactivity;

    public ControllerMembru() {
    }


    public void initialize(ServiceMaster master,Membru membru){
        this.master = master;
        this.membru = membru;
        this.master.addObserver(this);


        initTableMembrii(master.getMembriiActivi());
        initTableMesaje(master.getMesage());

        numeLabel.setText(membru.getNume());
        rolLabel.setText("Rol: " + membru.getRol().toString());

        statusLabel.setText("Status: " + membru.getStatus().toString());

        activBtn.setDisable(true);


        firstInactivity = false;



    }


    @FXML
    public void addMesaj(){
        String mesaj = mesajText.getText();

        try{
            Mesaj result = master.addMesaj(membru,mesaj);
            if(result != null)
            {
                ErrorMessageAlert.showErrorMessage("Eroare de procesare!","Mesajul nu a fost trimis!");
                return;
            }
        }catch (ValidationException e){
            ErrorMessageAlert.showErrorMessage("Validation Error",e.getMessage());
        }

    }


    @FXML
    public void stergeMesaj(){
        mesajText.setText("");
    }


    @FXML
    public void setActiv(){
        this.firstInactivity = true;
        Membru result = master.updateMembru(membru, StatusMembru.ACTIV);
        if(result==null)
        {
            activBtn.setDisable(true);
            inactivBtn.setDisable(false);
            trimiteBtn.setDisable(false);
            membru.setStatus(StatusMembru.ACTIV);
            statusLabel.setText("Status: ACTIV"  );

        }
    }


    @FXML
    public  void setInactiv(){
        if(membru.getRol().equals(TipMembru.SEF) && master.getMembriiActivi().size() != 1)
        {
            ErrorMessageAlert.showErrorMessage("Eroare!!","Pentru a iesi, trebuie sa iasa mai intai toti membrii!!");
            return;
        }

        Membru result = master.updateMembru(membru,StatusMembru.INACTIV);
        if(result==null)
        {
            activBtn.setDisable(false);
            inactivBtn.setDisable(true);
            trimiteBtn.setDisable(true);
            membru.setStatus(StatusMembru.INACTIV);
            statusLabel.setText("Status: INACTIV"  );

        }
    }





    private void initTableMesaje(List<Mesaj> mesaje){
        mesajeTable.getItems().clear();
        mesaje.forEach(x->mesajeTable.getItems().add(x));

    }


    private void initTableMembrii(List<Membru> membrii){
        membriiList.getItems().clear();
        membrii.forEach(x->membriiList.getItems().add(x.toString()));
    }


    @FXML
    public void getAllConversations(){
        if(!firstInactivity) {
            ErrorMessageAlert.showErrorMessage("Eroare", "Nu puteti sa vedeti toate mesajele pentru ca inca nu v-ati deconectat!");
            return;
        }
        if(membru.getStatus().equals(StatusMembru.INACTIV)) {
            ErrorMessageAlert.showErrorMessage("Erore", "Trebuie sa fiti activi ca sa accesati aceasta functie!");
            return;
        }



        String s = "";
        for(Mesaj m: master.getMesage()){
            s += m + "\n";
        }

        MessageWindowList.messageWindowList("Mesaje",s);
        return;

    }

    @Override
    public void update() {

        if(membru.getStatus() == StatusMembru.ACTIV){
            initTableMesaje(master.getMesage());
            initTableMembrii(master.getMembriiActivi());
        }


    }
}
