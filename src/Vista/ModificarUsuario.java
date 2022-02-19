package Vista;

import Controlador.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificarUsuario extends JFrame implements ActionListener {

    String[] datosUsuario;

    Container container=getContentPane();

    JLabel labelTiulo = new JLabel("Modificar Usuario");
    JLabel labelIdBuscar = new JLabel("Id a buscar");
    JTextField textFieldBuscar = new JTextField();
    JButton btnBuscarId = new JButton("Buscar");

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
    JPasswordField textFieldContrasena = new JPasswordField();

    JLabel labelConfirmar = new JLabel("Confirmar contraseña");
    JPasswordField textFieldConfirmar = new JPasswordField();


    JButton btnCrear = new JButton("Modificar");
    JButton btnCancelar = new JButton("Regresar");

    public ModificarUsuario(String id){

        datosUsuario = Usuario.buscarUsuario(id);
        EstilosBase estilosBase = new EstilosBase(this,"Modificar Usuario");

        comboRol.addItem("Estudiante");
        comboRol.addItem("Catedratico");

        this.setLayout(null);
        int centro = this.getSize().width/3;
        int anchoCaja = 150;
        int altoCaja = 30;
        int gap = 5;

        //añadiendo elementos

        labelTiulo.setBounds(centro-anchoCaja,0,anchoCaja,altoCaja);
        labelIdBuscar.setBounds(centro,0,anchoCaja,altoCaja);
        textFieldBuscar.setBounds(centro+anchoCaja,0,anchoCaja,altoCaja);
        btnBuscarId.setBounds(centro+2*anchoCaja,0,anchoCaja,altoCaja);

        JComponent[] componentesFormulario = {labelId,textFieldId,labelNombre,textFieldNombre,labelApellido,textFieldApellido,labelUsuario,textFieldUsuario,labelRol,comboRol,labelContrasena,textFieldContrasena, labelConfirmar,textFieldConfirmar, btnCrear, btnCancelar};

        int fila =0;
        for (int i = 0; i < componentesFormulario.length; i+=2) {
            componentesFormulario[i].setBounds(centro,50+fila*altoCaja,anchoCaja,altoCaja);
            componentesFormulario[i+1].setBounds(centro+anchoCaja,50+fila*altoCaja,anchoCaja,altoCaja);
            fila++;
        }

        btnCrear.setBounds(centro,50+8*altoCaja,anchoCaja,altoCaja);
        btnCancelar.setBounds(centro+anchoCaja,50+8*altoCaja,anchoCaja,altoCaja);

        btnCrear.addActionListener(this);
        btnCancelar.addActionListener(this);
        btnBuscarId.addActionListener(this);

        //añadiendolos al frame
        container.add(labelTiulo);
        container.add(labelIdBuscar);
        container.add(btnBuscarId);
        container.add(textFieldBuscar);

        for (JComponent componente:componentesFormulario) {
            container.add(componente);
        }

    }

    public void cargarDatos(String id){
        String[] datosBusqueda =Usuario.buscarUsuario(id);
        if (datosBusqueda!= null) {
            textFieldId.setText(datosBusqueda[0]);
            textFieldId.setEnabled(false);

            textFieldNombre.setText(datosBusqueda[1]);
            textFieldApellido.setText(datosBusqueda[2]);
            textFieldUsuario.setText(datosBusqueda[3]);
            comboRol.setEnabled(true);
            if (datosBusqueda[4].equals("3")) {
                comboRol.setSelectedIndex(1);
            }else if(datosBusqueda[4].equals("2")) {
                comboRol.setSelectedIndex(0);
            }else{
                comboRol.setEnabled(false);
            }
            textFieldContrasena.setText(datosBusqueda[5]);
            textFieldConfirmar.setText(datosBusqueda[5]);
        }else{
            JOptionPane.showMessageDialog(this, "No existe registro", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBuscarId) {
            if (textFieldBuscar.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un ID", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                cargarDatos(textFieldBuscar.getText());
            }
        }

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

                    if (Usuario.actualizarUsuario(id,nombre,apellido,usuario,tipoRol,contrasena)) {
                        JOptionPane.showMessageDialog(this, "Usuario modificado correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);

                        //Borrar textfields
                        JTextField[] componentesFormulario = {textFieldId,textFieldNombre,textFieldApellido,textFieldUsuario,textFieldConfirmar,textFieldContrasena};
                        for (JTextField componente: componentesFormulario) {
                            componente.setText("");
                        }
                    }else{
                        JOptionPane.showMessageDialog(this, "Hubo algun error", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        if (e.getSource() == btnCancelar) {
            ModificarUsuario.this.setVisible(false);
            PanelAdministrador panelAdministrador = new PanelAdministrador(datosUsuario[0]);
            panelAdministrador.setVisible(true);
        }
    }
}
