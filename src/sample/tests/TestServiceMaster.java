package sample.tests;


import sample.entities.Membru;
import sample.entities.StatusMembru;
import sample.entities.TipMembru;
import sample.repositories.RepositoryMembru;
import sample.repositories.RepositoryMesaj;
import sample.services.ServiceMaster;
import sample.services.ServiceMembri;
import sample.services.ServiceMesaj;
import sample.validations.ValidationMesaj;
import sample.validations.ValidatorMembru;

public class TestServiceMaster {

    private ServiceMaster master;

    public TestServiceMaster() {
        ValidatorMembru valMembru = new ValidatorMembru();
        ValidationMesaj valMesaj = new ValidationMesaj();

        RepositoryMembru repositoryMembru = new RepositoryMembru(valMembru);
        repositoryMembru.save(new Membru("AAA", TipMembru.SEF));
        repositoryMembru.save(new Membru("BBB", TipMembru.MEMBRU));
        repositoryMembru.save(new Membru("CCC", TipMembru.MEMBRU));

        RepositoryMesaj repositoryMesaj = new RepositoryMesaj(valMesaj);

        ServiceMesaj serviceMesaj = new ServiceMesaj(repositoryMesaj);
        ServiceMembri serviceMembri = new ServiceMembri(repositoryMembru);

        master = new ServiceMaster(serviceMembri,serviceMesaj,null);
    }


    public void testMaster(){

        //teste pentru membrii

        int i = -55;
        for(Membru m : master.getMembrii()){
            assert m.getId() == i;
            i++;
        }

        assert i == 4;
        Membru m = master.findMembru(1);
        assert m.getId() == 1;
        assert m.getStatus().equals(StatusMembru.ACTIV);
        assert m.getRol().equals(TipMembru.SEF);
        assert m.getNume().equals("AAA");


        master.updateMembru(m,StatusMembru.INACTIV);
        m = master.findMembru(1);
        System.out.println(m.getStatus());


    }
}
