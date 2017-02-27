package filosofos;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Practica Filosofos
 En una mesa redonda hay N filósofos sentados. También hay N palillos para comer arroz,
 estando cada palillo compartido entre dos filósofos, uno lo tiene a su izquierda y otro a su derecha.

 Los filósofos se dedican a pensar, pero de vez en cuando les entra hambre y quieren comer.
 Para poder comer arroz, necesitan los dos palillos, el de su izquierda y el de su derecha.

 Queremos implementar una simulación de la cena de filósofos mediante un programa principal que
 implemente N hilos iguales. Cada hilo representará un filósofo. El hilo tendrá un proceso infinito en
 el cual se repetirá el siguiente proceso:

 Imprimir un mensaje por pantalla diciendo "Filósofo fulanito pensando". Cada filósofo tendrá un nombre.
 Pensar surante un tiempo aleatorio
 Luego intentará coger los palillos que necesita para comer
 Caundo consiga los palillos, imprimirá un mensaje: "Filósofo fulanito comiendo"
 El filósofo comerá durante un tiempo aleatorio.
 Cuando termine de comer, deja los palillos en su sitio
 Plantear las clases del problema (interfaces y diagrama)  antes de empezar a codificarlo
 */
public class PrincipalFilosofos {
    public static void main(String[] args) {
        ArrayList<Lock> miArray=new ArrayList<>();
        int tamanio=10;
        for(int i=0;i<tamanio;i++){
            miArray.add(new ReentrantLock(true));
        }
        for(int i=0;i<tamanio;i++) {
            Filosofo miFilosofo = new Filosofo("Filósofo Fulanito "+i, i, miArray);
            Thread miHilo=new Thread(miFilosofo);
            miHilo.start();
        }

    }
}
