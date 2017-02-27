package supermercado;
/**
 * Cada cola pertenecerá a un único cajero
 * Importante explicar, que el cajero se quedará esperando
 * a que en al cola haya algún cliente, de ahí, que
 */

import java.util.ArrayList;

public class Cola{
    private ArrayList<Cliente> colita;
    private int maximo,tamanioActual;
    private ArrayList<Long> tiemposInicio,tiemposFin;

    public Cola(){
        colita=new ArrayList<>();
        tamanioActual=0;
        maximo=0;
        tiemposFin=new ArrayList<>();
        tiemposInicio=new ArrayList<>();
        tiemposInicio.add(System.currentTimeMillis());
    }

    public synchronized void add(Cliente c){
        if(tamanioActual==0){
            this.notify();
            tiemposFin.add(System.currentTimeMillis());
        }
        colita.add(c);
        if(colita.size()>maximo){
            maximo=colita.size();
        }
        tamanioActual++;
    }

    public synchronized void delete(){
        if(!colita.isEmpty()){
            colita.remove(0);
            tamanioActual--;
        }
        //Puede pasar, que eliminemos al último cliente de la cola
        if(tamanioActual==0){
            try {
                tiemposInicio.add(System.currentTimeMillis());
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void atiende(){
        while(tamanioActual!=0) {
            try {
                Thread.sleep(this.colita.get(0).getPago());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            delete();
        }
    }

    public int getTamanio(){
        return tamanioActual;
    }

    public int getMaximo(){
        return maximo;
    }

    public int compareTo(Cola c){
        int resultado=0;
        if(this.tamanioActual<c.tamanioActual){
            resultado=-1;
        }else if(this.tamanioActual>c.tamanioActual){
            resultado=1;
        }
        return resultado;
    }

    public long tiempoParado(){
        long tiempo=0;
        for(int i=0;i<tiemposFin.size();i++){
            tiempo+=tiemposFin.get(i)-tiemposInicio.get(i);
        }
        //Tenemos que contabilizar el tiempo que el cajero ha estado parado, desde
        //el último cliente hasta el fin de la jornada laboral (malditos empresarios)
        if(tiemposInicio.size()>tiemposFin.size()){
            tiempo+=System.currentTimeMillis()-tiemposInicio.get(tiemposInicio.size()-1);
        }

        return tiempo;
    }
}
