package interfaz;

import clasesPrincipales.Evento;
import clasesPrincipales.GestorEventos;
import clasesPrincipales.Invitadx;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VistaEvento extends JFrame {

    private Evento evento;
    private DefaultListModel<Invitadx> modeloInvitadxs;
    private JList<Invitadx> listaInvitadxs;

    public VistaEvento(GestorEventos gestor, Evento evento) {
        this.evento = evento;

        // caracteristicas generales
        setTitle("Detalle del Evento");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // panel principal
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // titulo
        JLabel titulo = new JLabel("Detalle del evento");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titulo, BorderLayout.NORTH);

        // panel central con datos
        JPanel centro = new JPanel(new GridLayout(7, 1, 5, 5)); // ahora 7 filas
        centro.add(new JLabel("Nombre: " + evento.getNombre()));
        centro.add(new JLabel("Descripción: " + evento.getDescripcion()));
        centro.add(new JLabel("Fecha: " + evento.getFecha().toString()));
        centro.add(new JLabel("Hora: " + evento.getHora().toString()));
        centro.add(new JLabel("Ubicación: " + evento.getUbicacion()));
        centro.add(new JLabel("Capacidad: " + evento.getCantidadInvitadxs() + " / " + evento.getCapacidadMaxima()));
        centro.add(new JLabel("Recursos asignados: " + evento.getRecursoAsignado()));
        panel.add(centro, BorderLayout.CENTER);

        // area de invitadxs
        modeloInvitadxs = new DefaultListModel<>();
        listaInvitadxs = new JList<>(modeloInvitadxs);
        panel.add(new JScrollPane(listaInvitadxs), BorderLayout.SOUTH);
        cargarListaInvitadxs();

        // botones
        JPanel panelBotones = new JPanel();
        JButton botonCerrar = new JButton("Cerrar");
        JButton botonInvitadx = new JButton("Registrar invitadx");
        JButton botonEliminarInvitadx = new JButton("Eliminar invitadx");

        // accion de registrar invitado
        botonInvitadx.addActionListener(e -> {
            new FormularioInvitadx(evento, this);
        });

        // accion para cerrar
        botonCerrar.addActionListener(e -> dispose());

        // accion para eliminar invitado
        botonEliminarInvitadx.addActionListener(e -> {
            Invitadx seleccionadx = listaInvitadxs.getSelectedValue();

            if (seleccionadx != null) {
                // si se selecciona un invitado...
                int confirmacion = JOptionPane.showConfirmDialog(this,
                        "¿Querés eliminar a " + seleccionadx.getNombre() + "?", "Eliminar invitadx",
                        JOptionPane.YES_NO_OPTION);

                // si se selecciona "SI" se llama a eliminarInvitadx y luego a cargar lista para
                // actualizar
                if (confirmacion == JOptionPane.YES_OPTION) {
                    evento.eliminarInvitadx(seleccionadx);
                    cargarListaInvitadxs();
                    JOptionPane.showMessageDialog(this, "✅ Invitadx eliminado.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccioná un invitadx.");
            }
        });

        panelBotones.add(botonInvitadx);
        panelBotones.add(botonCerrar);
        panel.add(panelBotones, BorderLayout.PAGE_END);

        add(panel);
        setVisible(true);
    }

    public void cargarListaInvitadxs() {
        modeloInvitadxs.clear();
        for (Invitadx i : evento.getInvitadxs()) {
            modeloInvitadxs.addElement(i);
        }
    }

}