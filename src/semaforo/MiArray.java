package semaforo;

/**
 * Created by fjruiz on 16/02/17.
 */
public class MiArray {
    private Integer[] miArray;
    private int contador;

    public MiArray(Integer[] array){
        miArray=array;
        //Inicializo a -1 para simplificar el código de escribearray y leearray
        contador=-1;
    }

    public Integer[] getMiArray() {
        return miArray;
    }

    public void setMiArray(Integer[] miArray) {
        this.miArray = miArray;
    }

    //Método que escribe en la siguiente casilla del array
    public void escribeArray(Integer numerito){
        contador++;
        miArray[contador]=numerito;
    }
    public int leeArray(){
        return miArray[contador];
    }
}
