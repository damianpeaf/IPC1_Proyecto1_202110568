package Vista;

import Controlador.Bibliografia;
import Controlador.Prestamo;
import Controlador.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearBibliografiaMasiva extends JFrame implements ActionListener {

    String[] datosUsuario;
    JLabel labelTitulo = new JLabel("Carga masiva");
    JTextArea areaTexto = new JTextArea();
    JButton btnCancelar = new JButton("Regresar");
    JButton btnCrear = new JButton("Cargar");
    JLabel separador1 = new JLabel("  ");
    JLabel separador2 = new JLabel("  ");

    public CrearBibliografiaMasiva(String id){
        datosUsuario = Usuario.buscarUsuario(id);
        EstilosBase estilosBase = new EstilosBase(this,"Crear Bibliografia");

        this.setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1,2));

        btnCancelar.addActionListener(this);
        btnCrear.addActionListener(this);

        panelBotones.add(btnCrear);
        panelBotones.add(btnCancelar);


        this.add(labelTitulo,BorderLayout.NORTH);
        this.add(separador1,BorderLayout.EAST);
        this.add(separador2,BorderLayout.WEST);
        this.add(areaTexto, BorderLayout.CENTER);
        this.add(panelBotones,BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancelar) {
            CrearBibliografiaMasiva.this.setVisible(false);
            PanelAdministrador panelAdministrador = new PanelAdministrador(datosUsuario[0]);
            panelAdministrador.setVisible(true);
        }

        if (e.getSource() == btnCrear) {
            if (Bibliografia.crearBibliografiaMasiva(areaTexto.getText())){
                JOptionPane.showMessageDialog(this, "Bibliografia creada correctamente", "Aviso",JOptionPane.INFORMATION_MESSAGE);

            }else{
                JOptionPane.showMessageDialog(this, "Hubo algun error", "Aviso",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
