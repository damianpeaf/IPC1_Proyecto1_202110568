package Controlador;

import java.util.Locale;

public class Bibliografia {

    //Datos requeridos
    //Tipo; Autor; Título; Descripción; Edición; Temas; Frecuencia Actual; Ejemplares; Área; Copias; Disponibles

    //datos de las bibliografias
    private static String datos = "";

    private final static int NUMERO_CAMPOS=11;

    //propiedades de cada usuario
    private String tipo;
    private String autor;
    private String titulo;
    private String descripcion;
    private String edicion;
    private String[] temas;
    private String frecuenciaActual;
    private String ejemplares;
    private String area;
    private String copias;
    private String disponibles;

    public Bibliografia(String tipo, String autor, String titulo, String descripcion, String edicion, String[] temas, String frecuenciaActual, String ejemplares,String area,String copias, String disponibles){
        this.tipo = tipo;
        this.autor = autor.trim();
        this.titulo = titulo.trim(); //pequeño formato
        this.descripcion = descripcion.trim();
        this.edicion = edicion.trim();
        this.temas = temas;
        this.frecuenciaActual = frecuenciaActual.trim();
        this.ejemplares = ejemplares.trim();
        this.area = area.trim();
        this.copias = copias.trim();
        this.disponibles = disponibles.trim();

    }

    private static boolean existeBibliografia(String titulo){
        //siempre que tenga datos
        if (datos.length()>0) {
            boolean existe =false;
            for (int i = 0; i < datosBibiliografia().length; i++) {
                if ((datosBibiliografia()[i][2].equals(titulo))) {
                    existe=true;
                }
            }
            return existe;
        }else{
            return false;
        }
    }

    private static String formatearTemas(String[] temas){
        String temasFormateados = "";
        if (temas !=null) {
            for (int i = 0; i < temas.length; i++) {
                if (i< temas.length-1) {
                    temasFormateados = temasFormateados + temas[i] + ",";
                }else{
                    temasFormateados = temasFormateados + temas[i];
                }
            }
        }
        return temasFormateados;
    }


    public boolean crearBibliografiaIndividual(){

        if (!existeBibliografia(this.titulo)) {
            //validacion existe bibliografia ?
            String temasFormateados = formatearTemas(this.temas);
            datos = datos + this.tipo +";"+this.autor+";"+this.titulo+";"+this.descripcion+";"+this.edicion+";"+temasFormateados+";"+this.frecuenciaActual+";"+this.ejemplares+";"+this.area+";"+this.copias+";"+this.disponibles+"\n";
            return true;
        }else{
            return false;
        }

    }

    public static boolean verificarFormato(String datos) {
        return true;
    }

    public static boolean crearBibliografiaMasiva(String datosFormateados){
        if (verificarFormato(datosFormateados)) {
            //validacion existe bibliografia ? ->

            String[] datosIndividuales = datosFormateados.split("\n");

            for (int i = 0; i < datosIndividuales.length; i++) {
                String[] datosFila = datosIndividuales[i].split(";");

                String[] temasFormateados = datosFila[5].split(",");
                Bibliografia bibliografia = new Bibliografia(datosFila[0],datosFila[1],datosFila[2],datosFila[3],datosFila[4],temasFormateados,datosFila[6], datosFila[7],datosFila[8], datosFila[9],datosFila[10]);
                bibliografia.crearBibliografiaIndividual();
            }
            return true;
        }else {
            return false;
        }
    }

    public static String[][] datosBibiliografia(){
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

    public static boolean actualizarBibliografia(String tipo, String autor, String titulo, String descripcion, String edicion, String[] temas, String frecuenciaActual, String ejemplares,String area,String copias, String disponibles){
            if (existeBibliografia(titulo)) {
                //validación
                String nuevosDatos="";
                for (int i = 0; i < datosBibiliografia().length; i++) {
                    if (!(datosBibiliografia()[i][2].equalsIgnoreCase(titulo))) {
                        for (int j = 0; j < NUMERO_CAMPOS; j++) {
                            nuevosDatos=nuevosDatos+ datosBibiliografia()[i][j] +";";
                        }
                        nuevosDatos=nuevosDatos+"\n";
                    }else{
                        String temasFormateados = formatearTemas(temas);
                        nuevosDatos=nuevosDatos+tipo +";"+autor+";"+titulo+";"+descripcion+";"+edicion+";"+temasFormateados+";"+frecuenciaActual+";"+ejemplares+";"+area+";"+copias+";"+disponibles+"\n";
                    }
                }

                datos=nuevosDatos;

                return true;
            }else{
                return false;
            }
        }

    public static void eliminarBibliografia(String titulo){

        //validación
        String nuevosDatos="";
        for (int i = 0; i < datosBibiliografia().length; i++) {
            if (!(datosBibiliografia()[i][2].equals(titulo))) {
                for (int j = 0; j < NUMERO_CAMPOS; j++) {
                    nuevosDatos=nuevosDatos+ datosBibiliografia()[i][j] +";";
                }
                nuevosDatos=nuevosDatos+"\n";
            }
        }
        datos=nuevosDatos;
    }

    public static String[] buscarBibliografia(String titulo) {

        String [] datosUsuarioBusqueda = null;

        for (int i = 0; i < datosBibiliografia().length; i++) {
            if ((datosBibiliografia()[i][2].trim().equalsIgnoreCase(titulo))) {
                datosUsuarioBusqueda = datosBibiliografia()[i];
            }
        }

        return datosUsuarioBusqueda;

    }

    public static String disponibilidadBibliografia(String titulo) {

        String datosUsuarioBusqueda=null;

        for (int i = 0; i < datosBibiliografia().length; i++) {
            if ((datosBibiliografia()[i][2].trim().equalsIgnoreCase(titulo))) {
                datosUsuarioBusqueda = datosBibiliografia()[i][10];

            }
        }

        return datosUsuarioBusqueda;

    }

    public static String[][] buscarCoincidenciasBibliografia(String coincidenciaDeBusqueda) {

        //Contar coincidencias
        int coincidencias = 0;
        for (int i = 0; i < datosBibiliografia().length; i++) {
            //verificar si contiene el tema
            String [] temas = datosBibiliografia()[i][5].split(",");
            for (int j = 0; j < temas.length; j++) {
                if (temas[j].trim().equalsIgnoreCase(coincidenciaDeBusqueda)) {
                    coincidencias++;
                }
            }
        }

        if (coincidencias>0) {
            String [][] datosUsuarioBusqueda = new String[coincidencias][NUMERO_CAMPOS];
            int contadorAux =0;

            for (int i = 0; i < datosBibiliografia().length; i++) {
                String [] temas = datosBibiliografia()[i][5].split(",");
                for (int j = 0; j < temas.length; j++) {
                    if (temas[j].trim().equalsIgnoreCase(coincidenciaDeBusqueda)) {
                        datosUsuarioBusqueda[contadorAux] =datosBibiliografia()[i];
                        contadorAux++;
                    }
                }

            }

            return datosUsuarioBusqueda;
        }else{
            return null;
        }

    }

}
