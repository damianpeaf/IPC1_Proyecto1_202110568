package Vista;

import javax.swing.*;
import java.awt.*;

public class EstilosBase extends JFrame {

    public EstilosBase(JFrame frame, String titulo){
        frame.setTitle(titulo);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        Toolkit pantalla = Toolkit.getDefaultToolkit();
        Dimension tamanoPantalla = pantalla.getScreenSize();

        frame.setSize(tamanoPantalla.width *3/4,tamanoPantalla.height*3/4);
        frame.setLocation(tamanoPantalla.width/8, tamanoPantalla.height/8);

        ImageIcon logo = new ImageIcon("logo.png");
        frame.setIconImage(logo.getImage());

        Dimension tamanoVentana = this.getSize();
        int altoVentana = tamanoVentana.height;
        int anchoVentana = tamanoVentana.width;

        frame.getContentPane().setBackground(Color.white);

    }

}
