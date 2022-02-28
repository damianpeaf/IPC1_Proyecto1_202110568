package Controlador;

import Vista.Inicio;
import Vista.PanelAdministrador;
import Vista.PanelUsuario;

public class Biblioteca {

    public static void main(String[] args) {
        Usuario administrador = new Usuario("1","administrador", "administrador", "administrador", "1", "password");
        administrador.crearUsuario();

        Inicio inicio = new Inicio();
        inicio.setVisible(true);
    }
}
