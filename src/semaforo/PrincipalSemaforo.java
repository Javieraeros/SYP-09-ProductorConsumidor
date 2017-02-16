package semaforo;

import java.util.concurrent.Semaphore;

/**
 * Created by fjruiz on 16/02/17.
 */
public class PrincipalSemaforo {
    public static void main(String[] args) {
        Semaphore misemaforo=new Semaphore(0,true);
        Integer[] arrayInteger=new Integer[10];
        MiArray miArray=new MiArray(arrayInteger);
        Thread hiloProductor=new Thread(new Productor(misemaforo,miArray));
        Thread hiloConsumidor=new Thread(new Consumidor(misemaforo,miArray));
        hiloProductor.start();
        hiloConsumidor.start();

    }
}
