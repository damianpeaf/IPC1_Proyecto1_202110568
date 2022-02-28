package Vista;

import Controlador.Bibliografia;
import Controlador.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearBibliografiaIndividual extends JFrame implements ActionListener{

    String[] datosUsuario;

    Container container=getContentPane();

    JLabel labelTipo = new JLabel("Tipo");
    JComboBox comboTipo = new JComboBox();

    JLabel labelAutor = new JLabel("Autor");
    JTextField textFieldAutor = new JTextField();
    JLabel labelTitulo = new JLabel("Titulo");
    JTextField textFieldTitulo = new JTextField();

    JLabel labelEdicion = new JLabel("Edicion");
    JTextField textFieldEdicion = new JTextField();

    JLabel labelDescripcion = new JLabel("Descripcion");
    JTextField textFieldDescripcion = new JTextField();

    JLabel labelTemas = new JLabel("Temas");
    JTextField textFieldTemas = new JTextField();

    JLabel labelFrecuencia = new JLabel("Frecuencia");
    JTextField textFieldFrecuencia = new JTextField();

    JLabel labelEjemplares = new JLabel("Ejemplares");
    JTextField textFieldEjemplares = new JTextField();

    JLabel labelArea = new JLabel("Area");
    JTextField textFieldArea = new JTextField();


    JLabel labelCopias = new JLabel("Copias");
    JTextField textFieldCopias = new JTextField();

    JLabel labelDisponibles = new JLabel("Disponibles");
    JTextField textFieldDisponibles = new JTextField();


    JButton btnCrear = new JButton("Crear");
    JButton btnCancelar = new JButton("Regresar");

    public CrearBibliografiaIndividual(String id){

        datosUsuario = Usuario.buscarUsuario(id);

        EstilosBase estilosBase = new EstilosBase(this,"Crear Bibliografia");

        comboTipo.addItem("Libro");
        comboTipo.addItem("Revista");
        comboTipo.addItem("Tesis");

        comboTipo.addActionListener(this);

        this.setLayout(null);
        int centro = this.getSize().width/3;
        int anchoCaja = 150;
        int altoCaja = 30;
        int gap = 5;

        //añadiendo elementos

        labelTitulo.setBounds(centro+anchoCaja,0,anchoCaja,altoCaja);

        JComponent[] componentesFormulario = {labelTipo, comboTipo,labelAutor, textFieldAutor,labelTitulo,textFieldTitulo,labelEdicion,textFieldEdicion,labelDescripcion,textFieldDescripcion,labelTemas,textFieldTemas,labelFrecuencia,textFieldFrecuencia, labelEjemplares, textFieldEjemplares,labelArea,textFieldArea, labelCopias, textFieldCopias,labelDisponibles,textFieldDisponibles, btnCrear, btnCancelar};

        int fila =0;
        for (int i = 0; i < componentesFormulario.length; i+=2) {
            componentesFormulario[i].setBounds(centro,30+fila*altoCaja,anchoCaja,altoCaja);
            componentesFormulario[i+1].setBounds(centro+anchoCaja,30+fila*altoCaja,anchoCaja,altoCaja);
            fila++;
        }

        btnCrear.addActionListener(this);
        btnCancelar.addActionListener(this);

        //añadiendolos al frame
        container.add(labelTitulo);
        for (JComponent componente:componentesFormulario) {
            container.add(componente);
        }

        modificarCampos();

    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == btnCrear) {

            //campos
            String tipo = String.valueOf(comboTipo.getSelectedIndex());
            String autor = textFieldAutor.getText();
            String titulo = textFieldTitulo.getText();
            String edicion = textFieldEdicion.getText();
            String descripcion = textFieldDescripcion.getText();
            String temas = textFieldTemas.getText();
            String frecuencia = textFieldFrecuencia.getText();
            String ejemplares = textFieldEjemplares.getText();
            String area = textFieldArea.getText();
            String copias = textFieldCopias.getText();
            String disponibles = textFieldDisponibles.getText();

            //vacios?
            if (titulo.equals("") || disponibles.equals("") ) {
                JOptionPane.showMessageDialog(this, "Debe rellenar los campos obligatorios minimos", "Error", JOptionPane.WARNING_MESSAGE);
            }else{

                String[] temasFormateados = temas.split(",");
                Bibliografia bibliografia = new Bibliografia(tipo, autor, titulo, descripcion,edicion,temasFormateados,frecuencia,ejemplares,area,copias,disponibles);

                if(bibliografia.crearBibliografiaIndividual()){
                    JOptionPane.showMessageDialog(this, "Bibliografia creada correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(this, "Hubo algun problema", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        if (e.getSource() == comboTipo) {

            modificarCampos();

        }

            if (e.getSource() == btnCancelar) {
            CrearBibliografiaIndividual.this.setVisible(false);
            PanelAdministrador panelAdministrador = new PanelAdministrador(datosUsuario[0]);
            panelAdministrador.setVisible(true);
        }
    }

    private void modificarCampos(){
        int opcionSeleccionada = comboTipo.getSelectedIndex();

        //limpia los campos
        textFieldAutor.setText("");
        textFieldTitulo.setText("");
        textFieldEdicion.setText("");
        textFieldDescripcion.setText("");
        textFieldTemas.setText("");
        textFieldFrecuencia.setText("");
        textFieldEjemplares.setText("");
        textFieldArea.setText("");
        textFieldCopias.setText("");
        textFieldDisponibles.setText("");

        if (opcionSeleccionada==0){
            textFieldAutor.setEnabled(true);
            textFieldTitulo.setEnabled(true);
            textFieldEdicion.setEnabled(true);
            textFieldDescripcion.setEnabled(true);
            textFieldTemas.setEnabled(true);
            textFieldEjemplares.setEnabled(false);
            textFieldFrecuencia.setEnabled(false);
            textFieldArea.setEnabled(false);
            textFieldCopias.setEnabled(true);
            textFieldDisponibles.setEnabled(true);
        }else if (opcionSeleccionada == 1) {
            //revista
            textFieldAutor.setEnabled(true);
            textFieldTitulo.setEnabled(true);
            textFieldEdicion.setEnabled(true);
            textFieldDescripcion.setEnabled(true);
            textFieldTemas.setEnabled(true);
            textFieldEjemplares.setEnabled(true);
            textFieldFrecuencia.setEnabled(true);
            textFieldArea.setEnabled(false);
            textFieldCopias.setEnabled(true);
            textFieldDisponibles.setEnabled(true);
        }else if(opcionSeleccionada == 2){
            //tesis
            textFieldAutor.setEnabled(true);
            textFieldTitulo.setEnabled(true);
            textFieldEdicion.setEnabled(true);
            textFieldDescripcion.setEnabled(true);
            textFieldTemas.setEnabled(true);
            textFieldEjemplares.setEnabled(false);
            textFieldFrecuencia.setEnabled(false);
            textFieldArea.setEnabled(true);
            textFieldCopias.setEnabled(true);
            textFieldDisponibles.setEnabled(true);
        }
    }

}
