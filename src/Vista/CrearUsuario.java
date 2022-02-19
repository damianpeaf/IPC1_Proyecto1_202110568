package Vista;

import Controlador.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearUsuario extends JFrame implements ActionListener {

    String[] datosUsuario;

    Container container=getContentPane();

    JLabel labelTiulo = new JLabel("Crear Usuario");

    JLabel labelId = new JLabel("ID");
    JTextField textFieldId = new JTextField();

    JLabel labelNombre = new JLabel("Nombre");
    JTextField textFieldNombre = new JTextField();

    JLabel labelApellido = new JLabel("Apellido");
    JTextField textFieldApellido = new JTextField();

    JLabel labelUsuario = new JLabel("Usuario");
    JTextField textFieldUsuario = new JTextField();

    JLabel labelRol = new JLabel("Rol");
    JComboBox comboRol = new JComboBox();

    JLabel labelContrasena = new JLabel("Contraseña");
    JTextField textFieldContrasena = new JTextField();

    JLabel labelConfirmar = new JLabel("Confirmar contraseña");
    JTextField textFieldConfirmar = new JTextField();


    JButton btnCrear = new JButton("Crear");
    JButton btnCancelar = new JButton("Regresar");

    public CrearUsuario(String id){

        datosUsuario = Usuario.buscarUsuario(id);

        EstilosBase estilosBase = new EstilosBase(this,"Crear Usuario");

        comboRol.addItem("Estudiante");
        comboRol.addItem("Catedratico");

        this.setLayout(null);
        int centro = this.getSize().width/3;
        int anchoCaja = 150;
        int altoCaja = 30;
        int gap = 5;

        labelTiulo.setBounds(centro+anchoCaja,50,anchoCaja,altoCaja);

        labelId.setBounds(centro,50+altoCaja,anchoCaja,altoCaja);
        textFieldId.setBounds(centro+anchoCaja,50+altoCaja,anchoCaja,altoCaja);

        labelNombre.setBounds(centro,50+2*altoCaja,anchoCaja,altoCaja);
        textFieldNombre.setBounds(centro+anchoCaja,50+2*altoCaja,anchoCaja,altoCaja);

        labelApellido.setBounds(centro,50+3*altoCaja,anchoCaja,altoCaja);
        textFieldApellido.setBounds(centro+anchoCaja,50+3*altoCaja,anchoCaja,altoCaja);

        labelUsuario.setBounds(centro,50+4*altoCaja,anchoCaja,altoCaja);
        textFieldUsuario.setBounds(centro+anchoCaja,50+4*altoCaja,anchoCaja,altoCaja);

        labelRol.setBounds(centro,50+5*altoCaja,anchoCaja,altoCaja);
        comboRol.setBounds(centro+anchoCaja,50+5*altoCaja,anchoCaja,altoCaja);

        labelContrasena.setBounds(centro,50+6*altoCaja,anchoCaja,altoCaja);
        textFieldContrasena.setBounds(centro+anchoCaja,50+6*altoCaja,anchoCaja,altoCaja);

        labelConfirmar.setBounds(centro,50+7*altoCaja,anchoCaja,altoCaja);
        textFieldConfirmar.setBounds(centro+anchoCaja,50+7*altoCaja,anchoCaja,altoCaja);

        btnCrear.setBounds(centro,50+8*altoCaja,anchoCaja,altoCaja);
        btnCancelar.setBounds(centro+anchoCaja,50+8*altoCaja,anchoCaja,altoCaja);
        btnCrear.addActionListener(this);
        btnCancelar.addActionListener(this);

        container.add(labelTiulo);
        container.add(labelNombre);
        container.add(textFieldNombre);
        container.add(labelApellido);
        container.add(textFieldApellido);
        container.add(labelConfirmar);
        container.add(textFieldConfirmar);
        container.add(labelContrasena);
        container.add(textFieldContrasena);
        container.add(labelId);
        container.add(textFieldId);
        container.add(labelRol);
        container.add(comboRol);
        container.add(labelUsuario);
        container.add(textFieldUsuario);
        container.add(btnCancelar);
        container.add(btnCrear);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCrear) {

            String id = textFieldId.getText();
            String nombre = textFieldNombre.getText();
            String apellido = textFieldApellido.getText();
            String usuario = textFieldUsuario.getText();
            String rol = (String) comboRol.getSelectedItem();
            String contrasena = textFieldContrasena.getText();
            String confirmar = textFieldConfirmar.getText();

            if (id.equals("") || nombre.equals("") || apellido.equals("") || usuario.equals("") || rol.equals("") || contrasena.equals("") || confirmar.equals("")) {
                JOptionPane.showMessageDialog(this, "Debe rellenar todos los campos", "Error", JOptionPane.WARNING_MESSAGE);
            }else{
                String tipoRol = "2";
                if (rol.equals("Catedratico")) {
                    tipoRol="3";
                }

                if (contrasena.equals(confirmar)) {
                    Usuario usuarioNuevo = new Usuario(id,nombre,apellido,usuario,tipoRol,contrasena);

                    if (usuarioNuevo.crearUsuario()) {
                        JOptionPane.showMessageDialog(this, "Usuario creado correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(this, "Hubo algun error, es posible que ya exista un usuario con dicha ID", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        if (e.getSource() == btnCancelar) {
            CrearUsuario.this.setVisible(false);
            PanelAdministrador panelAdministrador = new PanelAdministrador(datosUsuario[0]);
            panelAdministrador.setVisible(true);
        }
    }
}
