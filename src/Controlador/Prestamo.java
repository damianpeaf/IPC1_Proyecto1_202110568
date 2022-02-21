package Controlador;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Prestamo {

    //datos de los usuarios
    public static String datos = "";
    public static int numeroPrestamo =-1;

    private final static int NUMERO_CAMPOS=4;

    //propiedades de cada prestamo
    private String id;
    private String titulo;
    private String fecha;
    private String devuelto; //0 no devuelto, 1 devuelto


    public Prestamo(String titulo){
        DateTimeFormatter formatoDeFecha = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        this.id = (numeroPrestamo++) +"";
        this.titulo = titulo;
        this.fecha = formatoDeFecha.format(LocalDateTime.now());
        this.devuelto = "0";
    }

    public String[] crearPrestamo(){

        String [] respuesta = new String[2];


        //validar si existe dispo
        if (existeDisponibilidad()) {

        }else{
            respuesta[0] ="0";
            respuesta[1] ="No existe disponibilidad de esta bibliografía";

        }

        return respuesta;

    }

    public static String[][] datosPrestamo(){
        String[] arregloDatos = datos.split("\n");

        String [][] datosFormateados = new String[arregloDatos.length][NUMERO_CAMPOS];

        if (datos.length()>0) {
            for (int i = 0; i < arregloDatos.length; i++) {
                for (int j = 0; j < NUMERO_CAMPOS; j++) {
                    datosFormateados[i][j]=arregloDatos[i].split(";")[j];
                }
            }
        }


        return datosFormateados;
    }

    

}
