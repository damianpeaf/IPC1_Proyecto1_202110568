package Vista;

import Controlador.Usuario;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelAdministrador extends JFrame implements ActionListener {


    //propiedades comunes
    String[] datosUsuario;

    JButton btnLogout = new JButton("LogOut");

    JButton btnReporteUsuarios = new JButton("Usuario");
    JButton btnReporteBibliografia = new JButton("Bibliografia");
    JButton btnReportePrestamos = new JButton("Prestamos");

    JButton btnUsuariosCrear = new JButton("Crear");
    JButton btnUsuariosVer = new JButton("Ver");
    JButton btnUsuariosModificar = new JButton("Modificar");
    JButton btnUsuariosEliminar = new JButton("Eliminar");

    JButton btnBibliografiaCrear = new JButton("Crear");
    JButton btnBibliografiaVer = new JButton("Ver");
    JButton btnBibliografiaModificar = new JButton("Modificar");
    JButton btnBibliografiaEliminar = new JButton("Eliminar");

    public PanelAdministrador(String id){

        datosUsuario = Usuario.buscarUsuario(id);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();

        panel1.setBorder(new TitledBorder(new EtchedBorder(), "Biblioteca FIUSAC"));;
        panel2.setBorder(new TitledBorder(new EtchedBorder(), "Opciones de Administrador"));
        panel3.setBorder(new TitledBorder(new EtchedBorder(), "Usuarios"));
        panel4.setBorder(new TitledBorder(new EtchedBorder(), "Bibliografia"));


        //0->id, 1->nombre, 2->apellido, 3->nombreUsuario, 4->rol, 5->contrasena

        EstilosBase estilosBase = new EstilosBase(this, "Panel de control");
        this.setLayout(new GridLayout(2,2));

        //Panel arriba izq
        ImageIcon banner = new ImageIcon(new ImageIcon("banner.png").getImage().getScaledInstance(400, 200, Image.SCALE_DEFAULT));
        JLabel imagenBanner = new JLabel(banner);
        panel1.add(imagenBanner);

        //panel arriba derecha
        panel2.setLayout(new GridLayout(2,1,0,20));

        JPanel panelOpcionesUsuario = new JPanel();
        JLabel labelNombreUsuario = new JLabel("Nombre de usuario: " + datosUsuario[1]);
        btnLogout.addActionListener(this);
        panelOpcionesUsuario.add(labelNombreUsuario);
        panelOpcionesUsuario.add(btnLogout);

        JPanel panelReportes = new JPanel();
        JLabel labelReportes = new JLabel("Reportes");

        btnReporteUsuarios.addActionListener(this);
        btnReporteBibliografia.addActionListener(this);
        btnReportePrestamos.addActionListener(this);

        panelReportes.add(labelReportes);
        panelReportes.add(btnReporteUsuarios);
        panelReportes.add(btnReporteBibliografia);
        panelReportes.add(btnReportePrestamos);

        panel2.add(panelOpcionesUsuario);
        panel2.add(panelReportes);

        //Panel abajo izquierda
        panel3.setLayout(new GridLayout(3,1));

        JLabel labelUsuario = new JLabel("");
        JPanel panelUsuarios1 = new JPanel();
        JPanel panelUsuarios2 = new JPanel();

        btnUsuariosCrear.addActionListener(this);
        btnUsuariosModificar.addActionListener(this);
        btnUsuariosEliminar.addActionListener(this);
        btnUsuariosVer.addActionListener(this);

        panelUsuarios1.add(btnUsuariosCrear);
        panelUsuarios1.add(btnUsuariosVer);

        panelUsuarios2.add(btnUsuariosModificar);
        panelUsuarios2.add(btnUsuariosEliminar);

        panel3.add(labelUsuario);
        panel3.add(panelUsuarios1);
        panel3.add(panelUsuarios2);

        //Panel abajo derecha
        panel4.setLayout(new GridLayout(3,1));

        JLabel labelBibliografia = new JLabel("");
        JPanel panelBibliografia1 = new JPanel();
        JPanel panelBibliografia2 = new JPanel();

        btnBibliografiaCrear.addActionListener(this);
        btnBibliografiaModificar.addActionListener(this);
        btnBibliografiaEliminar.addActionListener(this);
        btnBibliografiaVer.addActionListener(this);

        panelBibliografia1.add(btnBibliografiaCrear);
        panelBibliografia1.add(btnBibliografiaVer);

        panelBibliografia2.add(btnBibliografiaModificar);
        panelBibliografia2.add(btnBibliografiaEliminar);

        panel4.add(labelBibliografia);
        panel4.add(panelBibliografia1);
        panel4.add(panelBibliografia2);

        this.add(panel1);
        this.add(panel2);
        this.add(panel3);
        this.add(panel4);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnLogout) {
            PanelAdministrador.this.setVisible(false);
            Login login = new Login();
            login.setVisible(true);
            JOptionPane.showMessageDialog(login, "Hasta pronto", "LogOut",JOptionPane.INFORMATION_MESSAGE);
        }

        if (e.getSource() == btnUsuariosCrear) {
            PanelAdministrador.this.setVisible(false);
            CrearUsuario crearUsuario = new CrearUsuario(datosUsuario[0]);
            crearUsuario.setVisible(true);
        }

        if (e.getSource() == btnUsuariosEliminar) {
            PanelAdministrador.this.setVisible(false);
            EliminarUsuario crearUsuario = new EliminarUsuario(datosUsuario[0]);
            crearUsuario.setVisible(true);
        }

        if (e.getSource() == btnUsuariosModificar) {
            PanelAdministrador.this.setVisible(false);
            ModificarUsuario crearUsuario = new ModificarUsuario(datosUsuario[0]);
            crearUsuario.setVisible(true);
        }

        if (e.getSource() == btnUsuariosVer) {
            PanelAdministrador.this.setVisible(false);
            ListarUsuarios crearUsuario = new ListarUsuarios(datosUsuario[0]);
            crearUsuario.setVisible(true);
        }

        if (e.getSource()==btnBibliografiaCrear){
            String[] options = {"Carga Masiva", "Carga individual"};
            int x = JOptionPane.showOptionDialog(this, "¿Forma de carga?","Selecciona una opcion",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (x ==0) {
                //carga masiva
                PanelAdministrador.this.setVisible(false);
                CrearBibliografiaMasiva nuevoPanel = new CrearBibliografiaMasiva(datosUsuario[0]);
                nuevoPanel.setVisible(true);
            }else if (x == 1) {
                //carga individual
                PanelAdministrador.this.setVisible(false);
                CrearBibliografiaIndividual nuevoPanel = new CrearBibliografiaIndividual(datosUsuario[0]);
                nuevoPanel.setVisible(true);
            }

        }

        if (e.getSource() == btnBibliografiaVer) {
            PanelAdministrador.this.setVisible(false);
            ListarBibliografia panel = new ListarBibliografia(datosUsuario[0]);
            panel.setVisible(true);
        }

        if (e.getSource() == btnBibliografiaModificar) {
            PanelAdministrador.this.setVisible(false);
            ModificarBibliografia panel = new ModificarBibliografia(datosUsuario[0]);
            panel.setVisible(true);
        }

        if (e.getSource() == btnBibliografiaEliminar) {
            PanelAdministrador.this.setVisible(false);
            EliminarBibliografia panel = new EliminarBibliografia(datosUsuario[0]);
            panel.setVisible(true);
        }


        //reportes
        //1 -> usuarios
        //2 -> bibilio
        //3 -> prestamos
        if (e.getSource() == btnReporteUsuarios) {
            PanelAdministrador.this.setVisible(false);
            GenerarReporte panel = new GenerarReporte(datosUsuario[0],1);
            panel.setVisible(true);
        }
        if (e.getSource() == btnReporteBibliografia) {
            PanelAdministrador.this.setVisible(false);
            GenerarReporte panel = new GenerarReporte(datosUsuario[0],2);
            panel.setVisible(true);
        }

        if (e.getSource() == btnReportePrestamos) {
            PanelAdministrador.this.setVisible(false);
            GenerarReporte panel = new GenerarReporte(datosUsuario[0],3);
            panel.setVisible(true);
        }

    }
}
