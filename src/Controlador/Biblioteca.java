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
        //PanelAdministrador panelAdministrador = new PanelAdministrador("1");
        //.setVisible(true);

        //para pruebas usuario normal
        PanelUsuario panelAdministrador = new PanelUsuario("1");
        panelAdministrador.setVisible(true);
    }
}
