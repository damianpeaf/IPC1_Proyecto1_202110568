package Vista;

import Controlador.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {


    Container container=getContentPane();
    JLabel labelTitulo=new JLabel("Login");
    JLabel labelUsuario=new JLabel("Usuario");
    JLabel labelContrasena=new JLabel("Contraseña");
    JTextField textFieldUsuario=new JTextField();
    JPasswordField textFieldpassword=new JPasswordField();
    JButton btnLogin =new JButton("Iniciar sesión");
    JButton btnCancelar =new JButton("Cancelar");

    public Login(){


        EstilosBase estilosBase = new EstilosBase(this, "Login");

        labelTitulo.setFont(new Font("Segoe UI Black",Font.BOLD, 24));
        labelUsuario.setFont(new Font("Arial",Font.BOLD, 16));
        labelContrasena.setFont(new Font("Arial",Font.BOLD, 16));

        this.setLayout(null);

        int variable = this.getSize().width/3;

        labelTitulo.setBounds(variable+100,50,100,30);
        labelUsuario.setBounds(variable,150,100,30);
        labelContrasena.setBounds(variable,220,100,30);
        textFieldUsuario.setBounds(100+variable,150,150,30);
        textFieldpassword.setBounds(100+variable,220,150,30);
        btnLogin.setBounds(variable,300,200,30);
        btnCancelar.setBounds(250+variable,300,100,30);

        container.add(labelTitulo);
        container.add(labelUsuario);
        container.add(labelContrasena);
        container.add(textFieldUsuario);
        container.add(textFieldpassword);
        container.add(btnLogin);
        container.add(btnCancelar);

        btnLogin.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            String usuario = textFieldUsuario.getText();
            String contrasena = textFieldpassword.getText();


            // 0->dejar pasar, 1-> Mensaje, 2->idUsuario, 3->rol

            if (usuario.equals("") || contrasena.equals("")) {
                JOptionPane.showMessageDialog(this,"Debe de rellenar los campos","Error", JOptionPane.ERROR_MESSAGE);

            }else{
                String[] respuesta = Usuario.login(usuario,contrasena);
                if (respuesta[0].equals("0")) {
                    JOptionPane.showMessageDialog(this,respuesta[1],"Error", JOptionPane.WARNING_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(this,respuesta[1],"Bienvenido", JOptionPane.INFORMATION_MESSAGE);
                }
            }

        }
    }
}
