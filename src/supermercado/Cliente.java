package supermercado;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Cliente implements Runnable {
    private long compra;
    private long pago;
    private ArrayList<Cola> colas;

    public Cliente(long compra,ArrayList<Cola> colas) {
        this.compra = compra;
        this.colas= colas;
        this.pago=(long) (Math.random()*5*compra/60+compra/60);
    }

    public long getCompra() {
        return compra;
    }

    public void setCompra(long compra) {
        this.compra = compra;
    }

    public long getPago() {
        return pago;
    }

    public void setPago(long pago) {
        this.pago = pago;
    }

    //Si no pongo synchronized no va bien :/
    public synchronized void comprar(){
        try {
            Thread.sleep(compra);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        comprar();
        //tamaño cola menos poblada
        int tcmp=0;
        //indice cola menos poblada
        int icmp=0;
        for (int i = 0; i < colas.size(); i++) {
            if (i == 0) {
                tcmp = colas.get(i).getTamanio();
                icmp = i;
            } else {
                if (tcmp > colas.get(i).getTamanio()) {
                    tcmp = colas.get(i).getTamanio();
                    icmp = i;
                }
            }
        }

        colas.get(icmp).add(this);
    }
}
