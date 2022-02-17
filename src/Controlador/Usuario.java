package Controlador;

import javax.sound.midi.SysexMessage;

public class Usuario {

    //datos de los usuarios
    public static String datos = "";

    //propiedades de cada usuario
    public String id;
    public String nombre;
    public String apellido;
    public String nombreUsuario;
    public String rol;
    public String contrasena;

    public Usuario(String id, String nombre, String apellido, String nombreUsuario, String rol, String contrasena){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreUsuario = nombreUsuario;
        this.rol = rol;
        this.contrasena = contrasena;
    }

    public boolean crearUsuario(){

        //validacion
        if (!existeUsuario(id)) {
            datos = datos + id +";"+nombre+";"+apellido+";"+nombreUsuario+";"+rol+";"+contrasena+"\n";
            return true;
        }else{
            return false;
        }
    }

    public static String[][] datosUsuario(){
        String[] arregloDatos = datos.split("\n");

        String [][] datosFormateados = new String[arregloDatos.length][6];

        for (int i = 0; i < arregloDatos.length; i++) {
            for (int j = 0; j < 6; j++) {
                datosFormateados[i][j]=arregloDatos[i].split(";")[j];
            }
        }

        return datosFormateados;
    }

    public static void eliminarUsuario(String id){

        //validación
        String nuevosDatos="";
        for (int i = 0; i < datosUsuario().length; i++) {
            if (!(datosUsuario()[i][0].equals(id))) {
                nuevosDatos=nuevosDatos+ datosUsuario()[i][0] +";"+datosUsuario()[i][1]+";"+datosUsuario()[i][2]+";"+datosUsuario()[i][3]+";"+datosUsuario()[i][4]+";"+datosUsuario()[i][5]+"\n";
            }
        }
        datos=nuevosDatos;
    }

    public static String[] buscarUsuario(String id) {

        String [] datosUsuarioBusqueda = null;

        for (int i = 0; i < datosUsuario().length; i++) {
            if ((datosUsuario()[i][0].equals(id))) {
                datosUsuarioBusqueda = datosUsuario()[i];
            }
        }

        return datosUsuarioBusqueda;

    }

        private boolean existeUsuario(String id){

        //siempre que tenga datos
        if (datos.length()>0) {
            boolean existe =false;
            for (int i = 0; i < datosUsuario().length; i++) {
                if ((datosUsuario()[i][0].equals(id))) {
                    existe=true;
                }
            }
            return existe;
        }else{
            return false;
        }
    }

}
