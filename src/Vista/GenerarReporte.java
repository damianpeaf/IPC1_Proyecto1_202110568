package Vista;

import Controlador.Bibliografia;
import Controlador.Reporte;
import Controlador.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerarReporte extends JFrame implements ActionListener {

    String[] datosUsuario;
    JLabel labelTitulo = new JLabel("Reporte");
    JTextArea areaTexto = new JTextArea();
    JButton btnCancelar = new JButton("Regresar");
    JLabel separador1 = new JLabel("  ");
    JLabel separador2 = new JLabel("  ");

    //reportes
    //1 -> usuarios
    //2 -> bibilio
    //3 -> prestamos
    public GenerarReporte(String id, int tipo){
        cargarReporte(tipo);
        datosUsuario = Usuario.buscarUsuario(id);
        EstilosBase estilosBase = new EstilosBase(this,"Reporte");

        this.setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1,1));

        btnCancelar.addActionListener(this);
        panelBotones.add(btnCancelar);

        JScrollPane scrollTabla = new JScrollPane(areaTexto);

        this.add(labelTitulo,BorderLayout.NORTH);
        this.add(separador1,BorderLayout.EAST);
        this.add(separador2,BorderLayout.WEST);
        this.add(scrollTabla, BorderLayout.CENTER);
        this.add(panelBotones,BorderLayout.SOUTH);
    }

    private void cargarReporte(int tipo){
        Reporte reporte = new Reporte();
        if (tipo == 1) {
            areaTexto.setText(reporte.reporteUsuarios());
        }else if (tipo == 2) {
            areaTexto.setText(reporte.reporteBibliografias());
        }else if (tipo == 3) {
            areaTexto.setText(reporte.reportePrestamos());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancelar) {
            GenerarReporte.this.setVisible(false);
            PanelAdministrador panelAdministrador = new PanelAdministrador(datosUsuario[0]);
            panelAdministrador.setVisible(true);
        }


    }
}
