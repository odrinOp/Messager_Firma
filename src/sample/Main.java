package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.controllers.ControllerMembru;
import sample.entities.Membru;
import sample.entities.TipMembru;
import sample.repositories.RepositoryMembru;
import sample.repositories.RepositoryMesaj;
import sample.services.ServiceMaster;
import sample.services.ServiceMembri;
import sample.services.ServiceMesaj;
import sample.tests.TestServiceMaster;
import sample.validations.ValidationMesaj;
import sample.validations.ValidatorMembru;

import java.io.IOException;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class Main extends Application {

    private ServiceMaster master;

    public void initMaster(){
        ValidatorMembru valMembru = new ValidatorMembru();
        ValidationMesaj valMesaj = new ValidationMesaj();

        RepositoryMembru repositoryMembru = new RepositoryMembru(valMembru);


        RepositoryMesaj repositoryMesaj = new RepositoryMesaj(valMesaj);

        ServiceMesaj serviceMesaj = new ServiceMesaj(repositoryMesaj);
        ServiceMembri serviceMembri = new ServiceMembri(repositoryMembru);

        master = new ServiceMaster(serviceMembri,serviceMesaj,"C:\\Facultate\\MAP\\PRACTIC-5FEB\\src\\sample\\files");
        master.loadData();
    }
    @Override
    public void start(Stage primaryStage) throws Exception{

        initMaster();
        List<Stage> stages = new LinkedList<>();

        for(Membru m: master.getMembrii()){
            stages.add(createStage(m));
        }

        stages.forEach(stage -> stage.show());
    }


    public Stage createStage(Membru membru) throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/views/MembruView.fxml"));
        BorderPane root = loader.load();
        ControllerMembru controllerMembru = loader.getController();


        controllerMembru.initialize(master,membru);
        if(membru.getRol().equals(TipMembru.SEF))
            primaryStage.setAlwaysOnTop(true);

        primaryStage.setTitle("Messages");
        primaryStage.setScene(new Scene(root, 600, 400));
        return primaryStage;
    }

    public static void main(String[] args) {
        TestServiceMaster t = new TestServiceMaster();
        t.testMaster();
        launch(args);

    }
}
