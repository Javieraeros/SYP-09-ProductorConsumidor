package supermercado;

public class Cajero implements Runnable {
    private Cola cola;
    private long finJornada;
    private int indice;

    public Cajero(long tiempo,int indice){
        cola=new Cola();
        this.indice=indice;
        this.finJornada=System.currentTimeMillis()+(tiempo);
    }

    public Cola getCola() {
        return cola;
    }

    public void setCola(Cola cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        while (System.currentTimeMillis()<finJornada) {
            cola.atiende();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /*System.out.println("El cajero "+indice+" ha estado: "+this.cola.tiempoParado()+"ms parado");
        System.out.println("El número de clientes máximos de la cola "+indice+" ha sido "+this.cola.getMaximo());
        System.out.println("Clientes totales de la cola "+indice+" ha sido "+this.cola.getTotal());*/
    }
}
