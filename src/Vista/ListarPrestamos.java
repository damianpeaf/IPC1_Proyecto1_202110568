package Vista;

import Controlador.Prestamo;
import Controlador.Reporte;
import Controlador.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ListarPrestamos extends JFrame implements ActionListener, MouseListener {

    String[] datosUsuario;
    JLabel labelTitulo = new JLabel("Devolver préstamo");
    JTable tablaPrestamos = new JTable();
    JButton btnCancelar = new JButton("Regresar");
    JButton btnDevolver = new JButton("Devolver");
    JLabel labelTituloIdPrestamo = new JLabel("ID del prestamo seleccionado:");
    JLabel labelIdPrestamo = new JLabel("");


    public ListarPrestamos(String id){
        cargarBibliografias(id);
        datosUsuario = Usuario.buscarUsuario(id);
        EstilosBase estilosBase = new EstilosBase(this,"Devolver préstamo");

        this.setLayout(new BorderLayout());

        //panel inferior
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(2,1));
        JPanel panelBotonesDevolver = new JPanel();

        panelBotonesDevolver.setLayout(new GridLayout(1,3));
        panelBotonesDevolver.add(labelTituloIdPrestamo);
        panelBotonesDevolver.add(labelIdPrestamo);
        panelBotonesDevolver.add(btnDevolver);

        panelBotones.add(panelBotonesDevolver);
        panelBotones.add(btnCancelar);


        //Panel tabla
        JPanel panelTabla = new JPanel();
        panelTabla.setLayout(new BorderLayout());
        panelTabla.add(tablaPrestamos.getTableHeader(),BorderLayout.NORTH);
        panelTabla.add(tablaPrestamos,BorderLayout.CENTER);
        JScrollPane scrollTabla = new JScrollPane(panelTabla);
        panelTabla.setAutoscrolls(true);

        //Listeners
        btnCancelar.addActionListener(this);
        btnDevolver.addActionListener(this);
        tablaPrestamos.addMouseListener(this);



        this.add(labelTitulo,BorderLayout.NORTH);
        this.add(panelTabla, BorderLayout.CENTER);
        this.add(panelBotones,BorderLayout.SOUTH);
    }

    private void cargarBibliografias(String id){
        DefaultTableModel modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("Id del prestamo");
        modeloTabla.addColumn("Titulo prestado");
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Devuelto");

        String [][] datosPrestamo = Prestamo.listarPrestamos(id);

        if (datosPrestamo != null) {
            if (datosPrestamo[0] != null) {
                for (String[] dato: datosPrestamo) {
                    String devuelto = "No";
                    if (dato[3].equals("1")) {
                        devuelto ="Si";
                    }
                    modeloTabla.addRow(new Object[]{dato[0],dato[1], dato[2], devuelto});
                }
            }
        }

        tablaPrestamos.setModel(modeloTabla);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancelar) {
            ListarPrestamos.this.setVisible(false);
            PanelUsuario panelAdministrador = new PanelUsuario(datosUsuario[0]);
            panelAdministrador.setVisible(true);
        }

        if (e.getSource()==btnDevolver){
            if (!labelIdPrestamo.getText().equals("")){
                String mensaje = Prestamo.devolverPrestamo(labelIdPrestamo.getText());
                JOptionPane.showMessageDialog(this, mensaje, "Aviso", JOptionPane.INFORMATION_MESSAGE);

                cargarBibliografias(datosUsuario[0]);
            }else {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un prestamo para devolver", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tablaPrestamos) {
            final int fila = tablaPrestamos.getSelectedRow();
            final String idPrestamo = (String)tablaPrestamos.getValueAt(fila, 0);

            labelIdPrestamo.setText(idPrestamo);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
