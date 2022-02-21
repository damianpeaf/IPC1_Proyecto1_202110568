package Vista;

import Controlador.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListarUsuarios extends JFrame implements ActionListener {

    String[] datosUsuario;
    JTable tablaUsuarios = new JTable();
    JButton btnCancelar = new JButton("Regresar");

    public ListarUsuarios (String id){
        datosUsuario = Usuario.buscarUsuario(id);
        EstilosBase estilosBase = new EstilosBase(this,"Ver Usuario");

        cargarDatos();

        this.setLayout(new BorderLayout());

        btnCancelar.addActionListener(this);

        this.add(tablaUsuarios.getTableHeader(),BorderLayout.NORTH);
        this.add(tablaUsuarios, BorderLayout.CENTER);
        this.add(btnCancelar,BorderLayout.SOUTH);

    }

    void cargarDatos(){
        DefaultTableModel modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("Id");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellido");
        modeloTabla.addColumn("Usuario");
        modeloTabla.addColumn("Rol");
        modeloTabla.addColumn("Password");

        for (String[] dato: Usuario.datosUsuario()) {
            String tipoRol = "Administrador";
            if (dato[4].equals("2")) {
                tipoRol = "Estudiante";
            }else if (dato[4].equals("3")) {
                tipoRol = "Catedratico";
            }
            modeloTabla.addRow(new Object[]{dato[0],dato[1], dato[2], dato[3],tipoRol,dato[5]});
        }

        tablaUsuarios.setModel(modeloTabla);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancelar) {
            ListarUsuarios.this.setVisible(false);
            PanelAdministrador panelAdministrador = new PanelAdministrador(datosUsuario[0]);
            panelAdministrador.setVisible(true);
        }
    }
}
