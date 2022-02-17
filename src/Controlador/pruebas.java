package Controlador;

public class pruebas {

    public static void main(String[] args) {
        Usuario usuario = new Usuario("1","administrador", "administrador", "administrador", "0", "password");
        Usuario usuario2 = new Usuario("2","damian", "penia", "hola", "1", "xd");

        usuario.crearUsuario();
        usuario2.crearUsuario();

        for (int i = 0; i < Usuario.datosUsuario().length; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(Usuario.datosUsuario()[i][j] + " | ");
            }
            System.out.println("");
        }

        System.out.println("");
        System.out.println("");


        String [] pruebax = Usuario.login("e", "e");

        for (String respuesta: pruebax) {
            System.out.println(respuesta);
        }

    }
}
