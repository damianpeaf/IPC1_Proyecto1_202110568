package Vista;

import Controlador.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EliminarUsuario extends JFrame implements ActionListener {

    String[] datosUsuario;

    Container container=getContentPane();

    JLabel labelTiulo = new JLabel("Eliminar Usuario");
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
    JTextField comboRol = new JTextField();

    JButton btnCrear = new JButton("Eliminar");
    JButton btnCancelar = new JButton("Regresar");

    public EliminarUsuario(String id){

        datosUsuario = Usuario.buscarUsuario(id);
        EstilosBase estilosBase = new EstilosBase(this,"Eliminar Usuario");

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

        JComponent[] componentesFormulario = {labelId,textFieldId,labelNombre,textFieldNombre,labelApellido,textFieldApellido,labelUsuario,textFieldUsuario,labelRol,comboRol, btnCrear, btnCancelar};

        //evitar que se modifique los TexField (para reutilizar codigo)
        for (JComponent componente: componentesFormulario) {
            if (componente instanceof JTextField) {
                ((JTextField) componente).setEditable(false);
            }
        }

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
            textFieldNombre.setText(datosBusqueda[1]);
            textFieldApellido.setText(datosBusqueda[2]);
            textFieldUsuario.setText(datosBusqueda[3]);
            if (datosBusqueda[4].equals("3")) {
                comboRol.setText("Catedratico");
            }else if(datosBusqueda[4].equals("2")){
                comboRol.setText("Estudiante");
            }else{
                comboRol.setText("Administrador");
            }
            //no se muestra la contraseña
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
            if (id.equals("")){
                JOptionPane.showMessageDialog(this, "Debe buscar la información antes", "Aviso", JOptionPane.WARNING_MESSAGE);
            }else{
                Usuario.eliminarUsuario(id);
                JOptionPane.showMessageDialog(this, "Eliminado correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);


                //Borrar textfields
                JTextField[] componentesFormulario = {textFieldId,textFieldNombre,textFieldApellido,textFieldUsuario,comboRol};
                for (JTextField componente: componentesFormulario) {
                        componente.setText("");
                }
            }
        }

        if (e.getSource() == btnCancelar) {
            EliminarUsuario.this.setVisible(false);
            PanelAdministrador panelAdministrador = new PanelAdministrador(datosUsuario[0]);
            panelAdministrador.setVisible(true);
        }
    }
}
