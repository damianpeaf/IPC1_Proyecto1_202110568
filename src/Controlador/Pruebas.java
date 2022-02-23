package Controlador;

public class Pruebas {

    public static void main(String[] args) {
        //String [] temas = {"tema1","tema2"};
        //Bibliografia bibliografia = new Bibliografia("0", "Autor1", "Titulo1", "Descripcion1", 2, null, "anual", 2, "Matematica", 10, 2);
        //bibliografia.crearBibliografiaIndividual();

        Usuario administrador = new Usuario("1","administrador", "administrador", "administrador", "0", "password");
        administrador.crearUsuario();

        String cargaMasiva = "0; Autor1; Titulo1; Descripcion1; 2; tema1, tema2; ; ; ; 10; 2\n" +
                "1; Autor1; Titulo2; Descripcion1; 2; tema1, tema2; Anual; 4; ; 10; 2\n" +
                "2; Autor1; Titulo3; Descripcion1; 2; tema3, tema2; ; ; Ingeniería; 10; 2\n"+
                "1; Autor1; Titulo4; Descripcion1; 2; tema4, tema2; ; ; Ingeniería; 10; 2\n";

        Bibliografia.crearBibliografiaMasiva(cargaMasiva);


        System.out.println(Bibliografia.buscarCoincidenciasBibliografia("tema3")[0][2]);


        Usuario usuario2 = new Usuario("1","administrador", "administrador", "administrador", "0", "password");

        Prestamo prestamo = new Prestamo("Titulo1", "1");
        prestamo.crearPrestamo();

        Prestamo prestamo2 = new Prestamo("Titulo2", "1");
        prestamo2.crearPrestamo();

        Prestamo.devolverPrestamo("1");

        String [][] datosPrestamo = Prestamo.listarPrestamoNoDevueltos("1");

        for (int i = 0; i < Prestamo.datosPrestamo().length; i++) {
            for (int j = 0; j < Prestamo.datosPrestamo()[0].length; j++) {
                System.out.print(" " + Prestamo.datosPrestamo()[i][j] + " ");
            }
            System.out.print("\n");
        }

        Reporte reporte = new Reporte();
        System.out.println(reporte.reporteBibliografias());

    }
}
