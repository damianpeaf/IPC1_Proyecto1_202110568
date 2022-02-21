package Controlador;

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
    private int edicion;
    private String[] temas;
    private String frecuenciaActual;
    private int ejemplares;
    private String area;
    private int copias;
    private int disponibles;

    public Bibliografia(String tipo, String autor, String titulo, String descripcion, int edicion, String[] temas, String frecuenciaActual, int ejemplares,String area,int copias, int disponibles){
        this.tipo = tipo;
        this.autor = autor;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.edicion = edicion;
        this.temas = temas;
        this.frecuenciaActual = frecuenciaActual;
        this.ejemplares = ejemplares;
        this.area = area;
        this.copias = copias;
        this.disponibles = disponibles;

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
        //validacion existe bibliografia ?
        String temasFormateados = formatearTemas(this.temas);
        datos = datos + this.tipo +";"+this.autor+";"+this.titulo+";"+this.descripcion+";"+this.edicion+";"+temasFormateados+";"+this.frecuenciaActual+";"+this.ejemplares+";"+this.area+";"+this.copias+";"+this.disponibles+"\n";
        return true;
    }

    public static boolean verificarFormato(String datos) {
        return true;
    }

    public static boolean crearBibliografiaMasiva(String datosFormateados){
        if (verificarFormato(datosFormateados)) {

            //validacion existe bibliografia ?
            datos = datos + datosFormateados;
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

    public static boolean actualizarBibliografia(String tipo, String autor, String titulo, String descripcion, int edicion, String[] temas, String frecuenciaActual, int ejemplares,String area,int copias, int disponibles){
            if (existeBibliografia(titulo)) {
                //validación
                String nuevosDatos="";
                for (int i = 0; i < datosBibiliografia().length; i++) {
                    if (!(datosBibiliografia()[i][2].equals(titulo))) {
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

    public static String[][] buscarCoincidenciasBibliografia(String coincidenciaDeBusqueda) {

        //Contar coincidencias
        int coincidencias = 0;
        for (int i = 0; i < datosBibiliografia().length; i++) {
            for (int j = 0; j < NUMERO_CAMPOS; j++) {
                if ((datosBibiliografia()[i][j].equals(coincidenciaDeBusqueda))) {
                    coincidencias++;
                    break;
                }
            }
        }

        if (coincidencias>0) {
            String [][] datosUsuarioBusqueda = new String[coincidencias][NUMERO_CAMPOS];
            int contadorAux =0;

            for (int i = 0; i < datosBibiliografia().length; i++) {
                for (int j = 0; j < NUMERO_CAMPOS; j++) {
                    if ((datosBibiliografia()[i][j].equals(coincidenciaDeBusqueda))) {
                        datosUsuarioBusqueda[contadorAux] =datosBibiliografia()[i];
                        contadorAux++;
                        break;
                    }
                }
            }

            return datosUsuarioBusqueda;
        }else{
            return null;
        }

    }

}