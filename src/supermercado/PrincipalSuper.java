package supermercado;

import java.util.ArrayList;
import java.util.Scanner;
/*
Ejercicio supermercado
Un supermercado nos encarga una aplicación que nos permita simular el comportamiento de las colas para estimar
el número de cajas que necesita tener operativas en función del número de clientes que en ese momento estén comprando.

Para ello necesitamos tres clases:
    Cliente: Un cliente entra en el supermercado, pasa un tiempo comprando y luego se pone en cola para pagar.
        El tiempo que pasa comprando será variable entre 5 y 60 minutos.
        En nuestro modelo este tiempo de compra (Tc) se asigna al crear el objeto cliente.
        El tiempo que tarda el cliente en ser atendido en la caja (Tiempo de Pago) es un número aleatorio
        que varía entre el tiempo de compra dividido por 60 y ese mismo tiempo dividido por 10.
    Cajero: Un cajero atiende al primer cliente de la cola.
        Espera el Tiempo de Pago de ese cliente y atiende al siguiente.  Si no hay siguiente, queda en espera.
    Cola: Es el buffer.


Necesitaremos otra clase que controle cuántos clientes hay esperando y cuántas veces algún cajero se queda ocioso.
Queremos estas clases y un programa conductor que pregunte el número de clientes por minuto
 que entran al supermercado y el número de cajeros de que disponemos.
LA simulación abarcará 5 horas y al final nos dará un informe indicando
 cuantas veces alguno de los cajeros ha estado esperando sin hacer nada,
 el número máximo de clientes que ha habido esperando en la cola
  y el tiempo medio desde que un cliente se pone en la cola hasta que es atendido.
Para acelerar la simulación, cada minuto real durará 10ms en nuestro modelo.
*/

//TODO Documentar
public class PrincipalSuper {
    public static void main(String[] args) {
        //region Inicializaciones

        Scanner teclado=new Scanner(System.in);
        int clientesMinuto,cajerosTotales;
        long tiempoMinutos=3000;
        ArrayList<Cliente> clientes=new ArrayList<>();
        ArrayList<Cola> colas=new ArrayList<>();
        ArrayList<Cajero> cajeros=new ArrayList<>();
        ArrayList<Thread> hilosClientes=new ArrayList<>();
        //endregion

        System.out.println("Introduce el número de clientes por minuto: ");
        clientesMinuto=teclado.nextInt();

        System.out.println("Introduce el número de cajeros disponible: ");
        cajerosTotales=teclado.nextInt();

        for(int i=0;i<cajerosTotales;i++){
            Cajero miCajero=new Cajero(tiempoMinutos,i);
            cajeros.add(miCajero);
            colas.add(miCajero.getCola());
            Thread hiloCajero=new Thread(miCajero);
            hiloCajero.start();
        }

        for(int i=0;i<tiempoMinutos/10*clientesMinuto;i++){
            long tiempoCompra=(long) (Math.random()*550+50);
            Cliente miCliente=new Cliente(tiempoCompra,colas);
            clientes.add(miCliente);
            Thread hiloCliente=new Thread(miCliente);
            hilosClientes.add(hiloCliente);
        }
        for(int i=0;i<tiempoMinutos/10*clientesMinuto;i++){
            hilosClientes.get(i).start();
        }
        try {
            Thread.sleep(tiempoMinutos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int clientesTotales=0;
        for(int i=0;i<colas.size();i++){
            System.out.print("El cajero "+i+" ha estado: ");
            System.out.println(colas.get(i).tiempoParado()+"ms parado");
            System.out.print("El número de clientes máximos de la cola "+i);
            System.out.println(" ha sido "+colas.get(i).getMaximo());
            System.out.println("Clientes totales de la cola "+i+" ha sido "+colas.get(i).getTotal());
            clientesTotales+=colas.get(i).getTotal();
        }
        System.out.println("Total clientes: "+clientesTotales);

    }
}
