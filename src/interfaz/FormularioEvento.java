package interfaz;

import clasesPrincipales.Evento;
import clasesPrincipales.GestorEventos;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class FormularioEvento extends JFrame {

    public FormularioEvento(GestorEventos gestor, PantallaPrincipal pantalla, Evento eventoExistente) {
        // operador ternario para verificar si existe un evento
        setTitle(eventoExistente == null ? "Agregar nuevo evento" : "Editar evento");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // panel principal
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // titulo
        // misma logica que al comienzo
        JLabel titulo = new JLabel(eventoExistente == null ? "Agregar nuevo evento" : "Editar evento");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titulo, BorderLayout.NORTH);

        // formulario central con sus labels
        // lbl = label
        JPanel centro = new JPanel(new GridLayout(8, 2, 10, 10));

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

        JLabel lblRecurso = new JLabel("Recurso asignado:");
        JTextField txtRecurso = new JTextField();

        // si se quiere editar un evento... me traigo todos los datos
        if (eventoExistente != null) {
            txtNombre.setText(eventoExistente.getNombre());
            txtDescripcion.setText(eventoExistente.getDescripcion());
            txtFecha.setText(eventoExistente.getFecha().toString());
            txtHora.setText(eventoExistente.getHora().toString());
            txtUbicacion.setText(eventoExistente.getUbicacion());
            txtCapacidad.setText(String.valueOf(eventoExistente.getCapacidadMaxima()));
            txtRecurso.setText(eventoExistente.getRecursoAsignado());
        }

        centro.add(lblNombre);centro.add(txtNombre);
        centro.add(lblDescripcion);centro.add(txtDescripcion);
        centro.add(lblFecha);centro.add(txtFecha);
        centro.add(lblHora);centro.add(txtHora);
        centro.add(lblUbicacion);centro.add(txtUbicacion);
        centro.add(lblCapacidad); centro.add(txtCapacidad);
        centro.add(lblRecurso);centro.add(txtRecurso);

        panel.add(centro, BorderLayout.CENTER);

        // botonn guardar
        JButton botonGuardar = new JButton(eventoExistente == null ? "Guardar evento" : "Guardar cambios");

        botonGuardar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            String descripcion = txtDescripcion.getText().trim();
            String fecha = txtFecha.getText().trim();
            String hora = txtHora.getText().trim();
            String ubicacion = txtUbicacion.getText().trim();
            String capacidadTexto = txtCapacidad.getText().trim();
            String recurso = txtRecurso.getText().trim();

            if (nombre.isEmpty() || descripcion.isEmpty() || fecha.isEmpty() || hora.isEmpty() || ubicacion.isEmpty()
                    || capacidadTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, completá todos los campos."); // se ejecuta si hay campos vacios
            } else {
                try {
                    LocalDate fechaFinal = LocalDate.parse(fecha);
                    LocalTime horaFinal = LocalTime.parse(hora);
                    int capacidad = Integer.parseInt(capacidadTexto);

                    if (eventoExistente == null) {
                        gestor.crearEvento(nombre, descripcion, fechaFinal, hora, ubicacion, capacidad, recurso);
                            int id = gestor.getProximoId();
                            Evento nuevo = new Evento(id, nombre, descripcion, fechaFinal, horaFinal, ubicacion, capacidad, recurso);
                            nuevo.setRecursoAsignado(recurso);
                            gestor.setProximoId(id + 1);
                            gestor.getTodosLosEventos().add(nuevo);
                        JOptionPane.showMessageDialog(this, "📅 Evento agregado correctamente.");
                    } else {
                        // existe el evento, entonces actualizo
                        eventoExistente.setNombre(nombre);
                        eventoExistente.setDescripcion(descripcion);
                        eventoExistente.setFecha(fechaFinal);
                        eventoExistente.setHora(horaFinal);
                        eventoExistente.setUbicacion(ubicacion);
                        eventoExistente.setCapacidadMaxima(capacidad);
                        eventoExistente.setRecursoAsignado(recurso);
                    }

                    pantalla.cargarEventos(); // actualizar lista
                    dispose(); // cerrar ventana

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

    // constructor con eventoExistente como null. cuando recibe null se usa para
    // crear un nuevo evento, sino para editarlo
    public FormularioEvento(GestorEventos gestor, PantallaPrincipal pantalla) {
        this(gestor, pantalla, null);
    }
}
