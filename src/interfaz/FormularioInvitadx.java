package interfaz;

import clasesPrincipales.Evento;
import clasesPrincipales.Invitadx;

import javax.swing.*;
import java.awt.*;

public class FormularioInvitadx extends JFrame {

    public FormularioInvitadx(Evento evento, VistaEvento vistaEvento) {

        // caracteristicas generales
        setTitle("Registrar invitadx");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // panel principal
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Agregar nuevx invitadx");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titulo, BorderLayout.NORTH);

        JPanel centro = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel lblDni = new JLabel("DNI:");
        JTextField txtDni = new JTextField();

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();

        centro.add(lblDni); centro.add(txtDni);
        centro.add(lblNombre); centro.add(txtNombre);
        centro.add(lblEmail); centro.add(txtEmail);

        panel.add(centro, BorderLayout.CENTER);

        // boton guardar
        JButton botonGuardar = new JButton("Guardar invitadx");

        botonGuardar.addActionListener(e -> {
            String dni = txtDni.getText().trim();
            String nombre = txtNombre.getText().trim();
            String email = txtEmail.getText().trim();

            if (dni.isEmpty() || nombre.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, completá todos los campos.");
            } else {
                Invitadx nuevx = new Invitadx(dni, nombre, email);
                evento.agregarInvitadx(nuevx);
                vistaEvento.actualizarLista(); // refresca la vista anterior
                JOptionPane.showMessageDialog(this, "✅ Invitadx registradx correctamente.");
                dispose();
            }
        });

        JPanel panelBoton = new JPanel();
        panelBoton.add(botonGuardar);
        panel.add(panelBoton, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }
}
