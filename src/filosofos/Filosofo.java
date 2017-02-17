package filosofos;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;

public class Filosofo {
    ArrayList<Lock> listaLocks;
    int posicion;

    public Filosofo(int posicion,ArrayList<Lock> listaLocks) {
        this.posicion=posicion;
        this.listaLocks = listaLocks;
    }

    public void filosofoPiensa(){
        System.out.println("Filósofo Fulanito"+posicion+" está pensando");
        listaLocks.get(posicion).unlock();
        //Si el filósofo es el último, tendremos que decirle que uno de los dos palillos que coge
        //lo comparte con el filosofo de la posicion 0
        if(listaLocks.size()==posicion){
            listaLocks.get(0).unlock();
        }else {
            listaLocks.get(posicion+1).unlock();
        }
    }
    public void filosofoCome(){
        System.out.println("Filósofo Fulanito"+posicion+" intenta comer");

    }

}
