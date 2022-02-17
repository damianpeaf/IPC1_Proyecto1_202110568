package Controlador;

public class pruebas {

    public static void main(String[] args) {
        Usuario usuario = new Usuario("1","administrador", "administrador", "administrador", "0", "password");
        Usuario usuario2 = new Usuario("2","damian", "penia", "hola", "1", "xd");
        Usuario usuario3 = new Usuario("2","damian", "penia", "hola", "1", "xd");

        System.out.println(Usuario.datos);

        usuario.crearUsuario();
        usuario2.crearUsuario();
        System.out.println(usuario3.crearUsuario());

        /*
        for (int i = 0; i < Usuario.datosUsuario().length; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(Usuario.datosUsuario()[i][j] + " | ");
            }
            System.out.println("");
        }*/

        System.out.println(Usuario.buscarUsuario("2")[2]);

    }
}
