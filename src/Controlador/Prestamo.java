package Controlador;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Prestamo {

    //datos de los usuarios
    public static String datos = "";
    public static int numeroPrestamo =-1;

    private final static int NUMERO_CAMPOS=5;

    //propiedades de cada prestamo
    private String id;
    private String titulo;
    private String fecha;
    private String devuelto; //0 no devuelto, 1 devuelto
    private String idUsuario;

    public Prestamo(String titulo, String idUsuario){
        DateTimeFormatter formatoDeFecha = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        numeroPrestamo+=1;
        this.id = (numeroPrestamo) +"";
        this.titulo = titulo;
        this.fecha = formatoDeFecha.format(LocalDateTime.now());
        this.devuelto = "0";
        this.idUsuario = idUsuario;
    }

    private static boolean modificarDisponiblidad(String tituloBuscar, boolean aumentar) {

        System.out.println(tituloBuscar);
        String[] datosBibliografia = Bibliografia.buscarBibliografia(tituloBuscar);

            String tipo = datosBibliografia[0];
            String autor = datosBibliografia[1];
            String titulo = datosBibliografia[2];
            String descripcion = datosBibliografia[3];
            String edicion = datosBibliografia[4];
            String[] temas = datosBibliografia[5].split(",");
            String frecuenciaActual = datosBibliografia[6];
            String ejemplares = datosBibliografia[7];
            String areas = datosBibliografia[8];
            String copias = datosBibliografia[9];
            int disponibles = Integer.parseInt(datosBibliografia[10].replace(" ",""));

            int nuevaDisponibilidad;
            if (aumentar) {
                nuevaDisponibilidad = disponibles + 1;
            } else {
                nuevaDisponibilidad = disponibles - 1;

            }
            return Bibliografia.actualizarBibliografia(tipo, autor, titulo, descripcion, edicion, temas, frecuenciaActual, ejemplares, areas, copias, nuevaDisponibilidad+"");
    }

    public String crearPrestamo(){

        String respuesta;

        //validar si existe dispo
        if (existeDisponibilidad(this.titulo)) {

            //modificar disponibilidad
                if(modificarDisponiblidad(this.titulo, false)){
                    datos = datos + this.id +";"+this.titulo+";"+this.fecha+";"+this.devuelto+";"+this.idUsuario+"\n";
                    respuesta ="Prestamo creado correctamente";
                }else{
                    respuesta ="Hubo algun error";
                }

        }else{
            respuesta ="No existe disponibilidad de esta bibliografía";
        }
        return respuesta;
    }

    public static String[][] listarPrestamoNoDevueltos(String idUsuario){
        //String[] datosUsuario = Usuario.buscarUsuario(idUsuario);

        int datosDisponibles = 0;
        for (int i = 0; i < datosPrestamo().length; i++) {
            if ((datosPrestamo()[i][4].equals(idUsuario)) && (datosPrestamo()[i][3].equals("1"))) {
                datosDisponibles++;
            }
        }

        if (datosDisponibles>0) {
            String [][] datosPrestamoBusqueda = new String [datosDisponibles][4];
            int contadorAux = 0;
            for (int i = 0; i < datosPrestamo().length; i++) {
                if ((datosPrestamo()[i][4].equals(idUsuario)) && (datosPrestamo()[i][3].equals("1"))) {
                    datosPrestamoBusqueda[contadorAux]=datosPrestamo()[i];
                }
            }

            //regresar tipo ?
            return datosPrestamoBusqueda;
        }else{
            return null;
        }
    }


    public static void devolverPrestamo(String idPrestamo){
        String [] datosUsuarioBusqueda = null;

        for (int i = 0; i < datosPrestamo().length; i++) {
            if ((datosPrestamo()[i][0].equals(idPrestamo))) {
                datosUsuarioBusqueda = datosPrestamo()[i];
            }
        }

        //sobreescribe unicamente la propiedad devuelto
        if (datosUsuarioBusqueda != null){
            String nuevosDatos="";
            for (int i = 0; i < datosPrestamo().length; i++) {
                if (!(datosPrestamo()[i][0].equals(idPrestamo))) {
                    for (int j = 0; j < NUMERO_CAMPOS; j++) {
                        nuevosDatos=nuevosDatos+ datosPrestamo()[i][j] +";";
                    }
                    nuevosDatos=nuevosDatos+"\n";
                }else{
                    nuevosDatos=nuevosDatos+datosUsuarioBusqueda[0] +";"+datosUsuarioBusqueda[1]+";"+datosUsuarioBusqueda[2]+";"+"1"+";"+datosUsuarioBusqueda[4]+"\n";
                    modificarDisponiblidad(datosUsuarioBusqueda[1],true);
                }
            }

            datos=nuevosDatos;
        }
    }

    private boolean existeDisponibilidad(String titulo){

        int disposibles = Integer.parseInt(Bibliografia.disponibilidadBibliografia(titulo));
        if (disposibles>=1) {
            return true;
        }else{
            return false;
        }
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
