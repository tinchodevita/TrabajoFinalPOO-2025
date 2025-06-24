package interfaz;

import clasesPrincipales.Evento;
import clasesPrincipales.GestorEventos;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class FormularioEvento extends JFrame {

    public FormularioEvento(GestorEventos gestor, PantallaPrincipal pantalla) {
        setTitle("Agregar nuevo evento");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // panel principal con márgenes
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // titulo
        JLabel titulo = new JLabel("Agregar nuevo evento");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titulo, BorderLayout.NORTH);

        // formulario central con sus labels
        JPanel centro = new JPanel(new GridLayout(6, 2, 10, 10));

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();

        JLabel lblDescripcion = new JLabel("Descripción:");
        JTextField txtDescripcion = new JTextField();

        JLabel lblFecha = new JLabel("Fecha (AAAA-MM-DD):");
        JTextField txtFecha = new JTextField();

        JLabel lblHora = new JLabel("Hora (HH:MM):");
        JTextField txtHora = new JTextField();

        JLabel lblUbicacion = new JLabel("Ubicación:");
        JTextField txtUbicacion = new JTextField();

        JLabel lblCapacidad = new JLabel("Capacidad:");
        JTextField txtCapacidad = new JTextField();

        centro.add(lblNombre); centro.add(txtNombre);
        centro.add(lblDescripcion); centro.add(txtDescripcion);
        centro.add(lblFecha); centro.add(txtFecha);
        centro.add(lblHora); centro.add(txtHora);
        centro.add(lblUbicacion); centro.add(txtUbicacion);
        centro.add(lblCapacidad); centro.add(txtCapacidad);

        panel.add(centro, BorderLayout.CENTER);

        // botonn guardar
        JButton botonGuardar = new JButton("Guardar evento");

        botonGuardar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            String descripcion = txtDescripcion.getText().trim();
            String fecha = txtFecha.getText().trim();
            String hora = txtHora.getText().trim();
            String ubicacion = txtUbicacion.getText().trim();
            String capacidadTexto = txtCapacidad.getText().trim();

            if (nombre.isEmpty() || descripcion.isEmpty() || fecha.isEmpty() || hora.isEmpty() || ubicacion.isEmpty() || capacidadTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, completá todos los campos.");
            } else {
                try {
                    LocalDate fechaFinal = LocalDate.parse(fecha);
                    LocalTime horaFinal = LocalTime.parse(hora);
                    int capacidad = Integer.parseInt(capacidadTexto);

                    gestor.crearEvento(nombre, descripcion, fechaFinal, hora, ubicacion, capacidad);
                    pantalla.cargarEventos(); // refrescar lista principal
                    JOptionPane.showMessageDialog(this, "📅 Evento agregado correctamente.");
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Datos inválidos. Revisá la fecha, hora y capacidad.");
                }
            }
        });

        JPanel panelBoton = new JPanel();
        panelBoton.add(botonGuardar);
        panel.add(panelBoton, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }
}
