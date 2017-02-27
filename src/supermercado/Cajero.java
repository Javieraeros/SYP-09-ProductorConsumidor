package supermercado;

public class Cajero implements Runnable {
    private Cola cola;
    private long finJornada;

    public Cajero(long tiempo){
        cola=new Cola();
        this.finJornada=System.currentTimeMillis()+tiempo;
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
        }
    }
}
