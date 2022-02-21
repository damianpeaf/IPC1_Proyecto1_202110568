package Vista;

import Controlador.Bibliografia;
import Controlador.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListarBibliografia extends JFrame implements ActionListener {

    String[] datosUsuario;
    JTable tablaUsuarios = new JTable();
    JButton btnCancelar = new JButton("Regresar");

    public ListarBibliografia(String id){
        datosUsuario = Usuario.buscarUsuario(id);
        EstilosBase estilosBase = new EstilosBase(this,"Ver bibliografia");

        cargarDatos();

        this.setLayout(new BorderLayout());

        btnCancelar.addActionListener(this);

        this.add(tablaUsuarios.getTableHeader(),BorderLayout.NORTH);
        this.add(tablaUsuarios, BorderLayout.CENTER);
        this.add(btnCancelar,BorderLayout.SOUTH);

    }

    void cargarDatos() {
        DefaultTableModel modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("Tipo");
        modeloTabla.addColumn("Autor");
        modeloTabla.addColumn("Titulo");
        modeloTabla.addColumn("Descripcion");
        modeloTabla.addColumn("Edicion");
        modeloTabla.addColumn("Temas");
        modeloTabla.addColumn("Frecuencia Actual");
        modeloTabla.addColumn("Ejemplares");
        modeloTabla.addColumn("Area");
        modeloTabla.addColumn("Copias");
        modeloTabla.addColumn("Disponibles");

        if (Bibliografia.datosBibiliografia()[0][0]!=null) {
            for (String[] dato : Bibliografia.datosBibiliografia()) {
                String tipo = "";
                if (dato[0].equals("0")) {
                    tipo = "Libro";
                } else if (dato[0].equals("1")) {
                    tipo = "Revista";
                } else if (dato[0].equals("2")) {
                    tipo = "Tesis";
                }
                modeloTabla.addRow(new Object[]{tipo, dato[1], dato[2], dato[3], dato[4], dato[5], dato[6], dato[7], dato[8], dato[9], dato[10]});
            }
        }

        tablaUsuarios.setModel(modeloTabla);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancelar) {
            ListarBibliografia.this.setVisible(false);
            PanelAdministrador panelAdministrador = new PanelAdministrador(datosUsuario[0]);
            panelAdministrador.setVisible(true);
        }
    }
}
