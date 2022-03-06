package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Inicio extends JFrame {

    public Inicio (){

        EstilosBase estilosBase = new EstilosBase(this, "Bliblioteca Facultad Ingeniería USAC");

        this.setLayout(new BorderLayout());

        //Parte superior
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1,3));
        panel1.setBackground(new Color(189, 195, 199));


        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Inicio.this.setVisible(false);
                Login login = new Login();
                login.setVisible(true);
            }
        });



        JLabel titulo = new JLabel("Página de inicio");
        titulo.setFont(new Font("Segoe UI Black",Font.BOLD, 24));
        JLabel espacio = new JLabel("");

        panel1.add(espacio);
        panel1.add(titulo);
        panel1.add(btnLogin);


        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1,2));
        panel2.setBackground(Color.white);


        ImageIcon banner = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("logo.png")).getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT));

        JLabel labelbanner = new JLabel(banner);

        JLabel vision = new JLabel("<html> <body> Vision: Ser la unidad de información especializada y técnica con estándares de <br> Biblioteca Universitaria, en beneficio del desarrollo de la ciencia y la tecnología del país </html> </body>");

        panel2.add(labelbanner);

        panel2.add(vision);

        JPanel panel3 = new JPanel();
        panel3.setBackground(new Color(189, 195, 199));
        JButton btnAbout = new JButton("About");

        btnAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                About login = new About();
                login.setVisible(true);
            }
        });

        panel3.add(btnAbout);


        this.add(panel1, BorderLayout.NORTH);
        this.add(panel2, BorderLayout.CENTER);
        this.add(panel3, BorderLayout.SOUTH);

    }

    JPanel imagenPanel = new JPanel(){
        public void  paintComponent(Image imagen, Graphics g){
            g.drawImage(imagen,0,0,getWidth(),getHeight(),this);
        }
    };

}
