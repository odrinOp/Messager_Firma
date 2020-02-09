package sample.generics;

import java.util.LinkedList;
import java.util.List;

public abstract class Observable {
    private List<Observer> list;

    public Observable() {
        this.list = new LinkedList<>();
    }


    public void addObserver(Observer obs){
        list.add(obs);
    }


    public void removeObserver(Observer obs){
        list.remove(obs);
    }


    public void notifyObservers(){
        list.forEach(x->x.update());
    }


}
