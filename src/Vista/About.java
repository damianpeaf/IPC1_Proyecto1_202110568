package Vista;

import javax.swing.*;
import java.awt.*;

public class About extends JFrame {

    public About(){
        this.setTitle("ABOUT");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);

        Toolkit pantalla = Toolkit.getDefaultToolkit();
        Dimension tamanoPantalla = pantalla.getScreenSize();

        this.setSize(500,200);
        this.setLocation(tamanoPantalla.width/8, tamanoPantalla.height/8);

        ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("logo.png"));
        this.setIconImage(logo.getImage());

        this.getContentPane().setBackground(Color.white);

        JLabel datos = new JLabel("Creado por Damián Ignacio Peña  - 202110568");
        datos.setBounds(100,100,100,30);

        this.add(datos);
    }
}
