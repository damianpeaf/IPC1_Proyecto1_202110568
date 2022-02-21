package Controlador;

public class Pruebas {

    public static void main(String[] args) {
        String [] temas = {"tema1","tema2"};
        //Bibliografia bibliografia = new Bibliografia("0", "Autor1", "Titulo1", "Descripcion1", 2, null, "anual", 2, "Matematica", 10, 2);
        //bibliografia.crearBibliografiaIndividual();

        String cargaMasiva = "0; Autor1; Titulo1; Descripcion1; 2; tema1, tema2; ; ; ; 10; 2\n" +
                "1; Autor1; Titulo1; Descripcion1; 2; tema1, tema2; Anual; 4; ; 10; 2\n" +
                "2; Autor1; Titulo1; Descripcion1; 2; tema1, tema2; ; ; Ingeniería; 10; 2";

        Bibliografia.crearBibliografiaMasiva(cargaMasiva);

        for (int i = 0; i < Bibliografia.datosBibiliografia().length; i++) {
            for (int j = 0; j < Bibliografia.datosBibiliografia()[0].length; j++) {
                System.out.print(" " + Bibliografia.datosBibiliografia()[i][j] + " ");
            }
            System.out.print("\n");
        }


    }
}
