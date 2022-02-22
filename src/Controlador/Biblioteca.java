package Controlador;

import Vista.Inicio;
import Vista.PanelAdministrador;
import Vista.PanelUsuario;

public class Biblioteca {

    public static void main(String[] args) {
        Usuario administrador = new Usuario("1","administrador", "administrador", "administrador", "0", "password");
        administrador.crearUsuario();

        //Inicio inicio = new Inicio();
        //inicio.setVisible(true);

        //para pruebas admin
        PanelAdministrador panelAdministrador = new PanelAdministrador("1");
        panelAdministrador.setVisible(true);

        //para pruebas usuario normal
        String cargaMasiva = "0; Autor1; Titulo1; Descripcion1; 2; tema1, tema2; ; ; ; 10; 2\n" +
                "1; Autor1; Titulo2; Descripcion1; 2; tema1, tema2; Anual; 4; ; 10; 2\n" +
                "2; Autor1; Titulo3; Descripcion1; 2; tema1, tema2; ; ; Ingeniería; 10; 2\n" +
                "2; Autor1; Titulo4; Descripcion1; 2; tema1, tema2; ; ; Ingeniería; 10; 2\n" +
                "2; Autor1; Titulo5; Descripcion1; 2; tema1, tema2; ; ; Ingeniería; 10; 2\n" +
                "2; Autor1; Titulo6; Descripcion1; 2; tema1, tema2; ; ; Ingeniería; 10; 2\n" +
                "2; Autor1; Titulo7; Descripcion1; 2; tema1, tema2; ; ; Ingeniería; 10; 2\n" +
                "2; Autor1; Titulo8; Descripcion1; 2; tema1, tema2; ; ; Ingeniería; 10; 2\n" +
                "2; Autor1; Titulo9; Descripcion1; 2; tema1, tema2; ; ; Ingeniería; 10; 2\n" +
                "2; Autor1; Titulo10; Descripcion1; 2; tema1, tema2; ; ; Ingeniería; 10; 2\n" +
                "2; Autor1; Titulo11; Descripcion1; 2; tema1, tema2; ; ; Ingeniería; 10; 2\n" +
                "2; Autor1; Titulo12; Descripcion1; 2; tema1, tema2; ; ; Ingeniería; 10; 2\n" +
                "2; Autor1; Titulo13; Descripcion1; 2; tema1, tema2; ; ; Ingeniería; 10; 2\n" +
                "2; Autor1; Titulo14; Descripcion1; 2; tema1, tema2; ; ; Ingeniería; 10; 2\n" +
                "2; Autor1; Titulo15; Descripcion1; 2; tema1, tema2; ; ; Ingeniería; 10; 2\n" +
                "2; Autor1; Titulo16; Descripcion1; 2; tema1, tema2; ; ; Ingeniería; 10; 2\n" +
                "2; Autor1; Titulo17; Descripcion1; 2; tema1, tema2; ; ; Ingeniería; 10; 2\n" +
                "2; Autor1; Titulo18; Descripcion1; 2; tema1, tema2; ; ; Ingeniería; 10; 2\n" +
                "2; Autor1; Titulo19; Descripcion1; 2; tema1, tema2; ; ; Ingeniería; 10; 2\n" +
                "2; Autor1; Titulo20; Descripcion1; 2; tema1, tema2; ; ; Ingeniería; 10; 2\n" +
                "2; Autor1; Titulo21; Descripcion1; 2; tema1, tema2; ; ; Ingeniería; 10; 2\n";

        Bibliografia.crearBibliografiaMasiva(cargaMasiva);

        //PanelUsuario panelAdministrador = new PanelUsuario("1");
        //panelAdministrador.setVisible(true);
    }
}
