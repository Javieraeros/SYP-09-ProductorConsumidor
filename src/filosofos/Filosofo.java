package filosofos;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class Filosofo implements Runnable{
    ArrayList<Lock> listaLocks;
    int posicionIzquierda,posicionDerecha;
    String nombre;
    boolean palilloDerecha,palilloIzquierda;

    public Filosofo(String nombre,int posicion,ArrayList<Lock> listaLocks) {
        this.nombre=nombre;
        palilloDerecha=palilloIzquierda=false;
        this.posicionIzquierda =posicion;

        //Si el filósofo es el último, tendremos que decirle que uno de los dos palillos que coge
        //lo comparte con el filosofo de la posicionIzquierda 0
        if(listaLocks.size()-1==posicionIzquierda){
            posicionDerecha=0;
        }else{
            posicionDerecha=posicionIzquierda+1;
        }
        this.listaLocks = listaLocks;
    }


    public void filosofoCome(){
        System.out.println(nombre+" intenta comer");
        try {
            while(!palilloDerecha && !palilloIzquierda) {
                palilloDerecha=listaLocks.get(posicionDerecha).
                        tryLock(1000,TimeUnit.MILLISECONDS);
                if(palilloDerecha){
                    palilloIzquierda=listaLocks.get(posicionIzquierda).
                            tryLock(1000,TimeUnit.MILLISECONDS);
                    if(!palilloIzquierda){
                        listaLocks.get(posicionDerecha).unlock();
                        /*
                         *La siguiente linea de código, aunque parezca innecesaria evita un error de
                         * IllegalMonitorStateException.
                         * Esta excepción se produce cuando uno de los filósofos deja de comer e intenta soltar uno
                         * de los palillos, mientras que otro filósofo ya lo ha cogido para comer
                         */
                        palilloDerecha=false;
                    }
                }
                if(!palilloIzquierda || !palilloDerecha){
                    System.out.println(nombre+" Uno de los palillos está ocupado, esperando");

                    Thread.sleep((long) (Math.random()*3000));
                }
            }
            System.out.println(nombre+" comiendo");

            Thread.sleep((long) (Math.random()*3000));
            System.out.println(nombre+" ha terminado y está pensando");
            listaLocks.get(posicionIzquierda).unlock();
            listaLocks.get(posicionDerecha).unlock();
            palilloIzquierda=palilloDerecha=false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep((long) (Math.random() * 10000));
                filosofoCome();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
