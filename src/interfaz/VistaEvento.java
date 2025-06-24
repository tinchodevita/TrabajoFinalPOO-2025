package interfaz;

import clasesPrincipales.Evento;
import clasesPrincipales.GestorEventos;
import clasesPrincipales.Invitadx;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VistaEvento extends JFrame {
    
    private Evento evento;
    private JTextArea textoInvitadxs;

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
        JPanel centro = new JPanel(new GridLayout(6, 1, 5, 5));
        centro.add(new JLabel("Nombre: " + evento.getNombre()));
        centro.add(new JLabel("Descripción: " + evento.getDescripcion()));
        centro.add(new JLabel("Fecha: " + evento.getFecha().toString()));
        centro.add(new JLabel("Hora: " + evento.getHora().toString()));
        centro.add(new JLabel("Ubicación: " + evento.getUbicacion()));
        centro.add(new JLabel("Capacidad: " + evento.getCantidadInvitadxs() + " / " + evento.getCapacidadMaxima()));
        panel.add(centro, BorderLayout.CENTER);

        // area de invitadxs
        textoInvitadxs = new JTextArea(8, 30);
        textoInvitadxs.setEditable(false);
        textoInvitadxs.setLineWrap(true);

        StringBuilder texto = new StringBuilder();
        List<Invitadx> lista = evento.getInvitadxs();
        if (lista.isEmpty()) {
            texto.append("Todavía no hay invitadxs.");
        } else {
            for (Invitadx i : lista) {
                texto.append("- ").append(i.getNombre()).append(" (").append(i.getDni()).append(")\n");
            }
        }

        textoInvitadxs.setText(texto.toString());
        panel.add(new JScrollPane(textoInvitadxs), BorderLayout.SOUTH);

        // panel botones
        JPanel panelBotones = new JPanel();

        // boton cerrar
        JButton botonCerrar = new JButton("Cerrar");
        botonCerrar.addActionListener(e -> dispose());

        // boton registrar invitado
        JButton botonInvitadx = new JButton("Registrar invitadx");
        botonInvitadx.addActionListener(e -> {
            new FormularioInvitadx(evento, this);
        });

        panelBotones.add(botonInvitadx);
        panelBotones.add(botonCerrar);
        panel.add(panelBotones, BorderLayout.PAGE_END);

        add(panel);
        setVisible(true);
    }

    public void actualizarLista() {
        StringBuilder texto = new StringBuilder();
        List<Invitadx> lista = evento.getInvitadxs();

        if (lista.isEmpty()) {
            texto.append("Todavía no hay invitadxs.");
        } else {
            for (Invitadx i : lista) {
                texto.append("- ").append(i.getNombre()).append(" (").append(i.getDni()).append(")\n");
            }
        }

        textoInvitadxs.setText(texto.toString());
    }

}