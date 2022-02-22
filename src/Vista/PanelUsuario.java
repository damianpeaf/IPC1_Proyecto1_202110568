package Vista;

import Controlador.Bibliografia;
import Controlador.Prestamo;
import Controlador.Usuario;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PanelUsuario extends JFrame implements ActionListener, MouseListener {

    //propiedades comunes
    String[] datosUsuario;

    JButton btnLogout = new JButton("LogOut");
    JButton btnVerPrestamos = new JButton("Ver prestamos");

    JButton btnBuscarBibliografia = new JButton("Buscar bibliografia");
    JButton btnPrestarBibliografia = new JButton("prestar");
    JTextField textFieldBuscar = new JTextField();
    JLabel labelDatosSeleccionadosTitulo = new JLabel("Datos seleccionado:");
    JLabel labelDatosSeleccionados = new JLabel("");

    JTable tablaBibliografias = new JTable();

    public PanelUsuario(String id){
        cargarDatos();
        datosUsuario = Usuario.buscarUsuario(id);

        EstilosBase estilosBase = new EstilosBase(this, "Panel de control");
        JPanel panel0 = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        panel1.setBorder(new TitledBorder(new EtchedBorder(), "Biblioteca FIUSAC"));;
        panel2.setBorder(new TitledBorder(new EtchedBorder(), "Opciones de Usuario"));
        panel3.setBorder(new TitledBorder(new EtchedBorder(), "Bibliografias"));

        //panel arriba
        ImageIcon banner = new ImageIcon(new ImageIcon("banner.png").getImage().getScaledInstance(400, 200, Image.SCALE_DEFAULT));
        JLabel imagenBanner = new JLabel(banner);
        panel1.add(imagenBanner);

        panel0.setLayout(new GridLayout(1,2));
        panel0.add(panel1);
        panel0.add(panel2);

        JLabel labelNombreUsuario = new JLabel("Nombre de usuario: " + datosUsuario[1]);
        btnLogout.addActionListener(this);
        btnVerPrestamos.addActionListener(this);

        //Ordenar botones arriba derecha
        panel2.setLayout(new GridBagLayout());
        GridBagConstraints restricciones1 = new GridBagConstraints();
        restricciones1.gridx = 0;
        restricciones1.gridy = 0;
        restricciones1.gridwidth = 3;
        restricciones1.gridheight = 1;

        GridBagConstraints restricciones2 = new GridBagConstraints();
        restricciones2.gridx = 0;
        restricciones2.gridy = 1;
        restricciones2.gridwidth = 2;
        restricciones2.gridheight = 1;

        GridBagConstraints restricciones3 = new GridBagConstraints();
        restricciones3.gridx = 2;
        restricciones3.gridy = 1;
        restricciones3.gridwidth = 1;
        restricciones3.gridheight = 1;

        panel2.add(labelNombreUsuario,restricciones1);
        panel2.add(btnLogout,restricciones2);
        panel2.add(btnVerPrestamos,restricciones3);

        //panel inferior
        panel3.setLayout(new BorderLayout());
        //barra de opciones de prestamo

        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setLayout(new GridLayout(1,2));


        panelBusqueda.add(textFieldBuscar);
        panelBusqueda.add(btnBuscarBibliografia);

        //NOTA: AÑADIR LISTENER PARA CUANDO ESCRIBA EN EL TEXTFIELD ???
        btnBuscarBibliografia.addActionListener(this);

        JPanel panelInformacionBusqueda = new JPanel();
        panelInformacionBusqueda.setLayout(new GridLayout(1,3));
        panelInformacionBusqueda.add(labelDatosSeleccionadosTitulo);
        panelInformacionBusqueda.add(labelDatosSeleccionados);
        panelInformacionBusqueda.add(btnPrestarBibliografia);
        btnPrestarBibliografia.addActionListener(this);

        //panel tabla
        JPanel panelTabla = new JPanel();
        panelTabla.setLayout(new BorderLayout());
        panelTabla.add(tablaBibliografias.getTableHeader(),BorderLayout.NORTH);
        panelTabla.add(tablaBibliografias,BorderLayout.CENTER);
        JScrollPane scrollTabla = new JScrollPane(panelTabla);
        panelTabla.setAutoscrolls(true);

        panel3.add(panelBusqueda,BorderLayout.NORTH);
        panel3.add(scrollTabla,BorderLayout.CENTER);
        panel3.add(panelInformacionBusqueda,BorderLayout.SOUTH);

        //Evento para la tabla
        tablaBibliografias.addMouseListener(this);

        //añadir paneles padres
        this.setLayout(new GridLayout(2,1));
        this.add(panel0);
        this.add(panel3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogout) {
            PanelUsuario.this.setVisible(false);
            Login login = new Login();
            login.setVisible(true);
            JOptionPane.showMessageDialog(login, "Hasta pronto", "LogOut",JOptionPane.INFORMATION_MESSAGE);
        }

        if (e.getSource() == btnBuscarBibliografia) {
            cargarDatos();
        }
        if (e.getSource()==btnPrestarBibliografia){
            if (!labelDatosSeleccionados.getText().equals("")){
                String[] options = {"Si", "No"};
                int opcionElegida = JOptionPane.showOptionDialog(this, "Confirmar","Selecciona una opcion",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                if (opcionElegida==0) {
                    //si
                    Prestamo prestamo = new Prestamo(labelDatosSeleccionados.getText(),datosUsuario[0]);
                    JOptionPane.showMessageDialog(this, prestamo.crearPrestamo(), "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    cargarDatos();
                }
            }else {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un titulo para prestar", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    void generarModelo(DefaultTableModel modeloTabla,String [][] datosCargar){
        if (datosCargar != null) {
            if (datosCargar[0][0] != null) {

                for (String[] dato : datosCargar) {
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
        }else {
            JOptionPane.showMessageDialog(this, "Información no encontrada", "Exito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    void cargarDatos() {

        //reinicia el modelo
        tablaBibliografias.setModel(new DefaultTableModel());
        DefaultTableModel modeloTabla = new DefaultTableModel();

        modeloTabla.setRowCount(0);
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

        if (!textFieldBuscar.getText().equals("")) {
            generarModelo(modeloTabla,Bibliografia.buscarCoincidenciasBibliografia(textFieldBuscar.getText()));
        }else{
            generarModelo(modeloTabla,Bibliografia.datosBibiliografia());
        }

        tablaBibliografias.setModel(modeloTabla);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tablaBibliografias) {
            final int fila = tablaBibliografias.getSelectedRow();
            final String titulo = (String)tablaBibliografias.getValueAt(fila, 2);

            labelDatosSeleccionados.setText(titulo);
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
