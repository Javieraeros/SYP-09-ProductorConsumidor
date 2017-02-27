package supermercado;

public class Cajero implements Runnable {
    private Cola cola;

    public Cajero(){
        cola=new Cola();
    }

    public Cola getCola() {
        return cola;
    }

    public void setCola(Cola cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        while (true) {
            cola.atiende();
        }
    }
}
