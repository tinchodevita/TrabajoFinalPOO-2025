package clasesPrincipales;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class GestorArchivos {

    // guardar evento en archivo
    public static void guardarEventos(List<Evento> eventos, String ruta) {
        // lectura del archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            for (Evento e : eventos) {
                writer.write("EVENTO;" + e.getId() + ";" + e.getNombre() + ";" + e.getDescripcion() + ";" +
                e.getFecha() + ";" + e.getHora() + ";" + e.getUbicacion() + ";" + e.getCapacidadMaxima());
                writer.newLine(); //se guardan los datos del evento separados por ";"
                
                // se recorren los invitados de ese evento y se agregan al final
                for (Invitadx i : e.getInvitadxs()) {
                    writer.write("INVITADX;" + i.getDni() + ";" + i.getNombre() + ";" + i.getEmail());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error al guardar eventos: " + e.getMessage());
        }
    }
    
    // leeer evento desde archivo
    public static void cargarEventos(GestorEventos gestor, String ruta) {
        // lectura del archivo
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {

            Evento eventoActual = null; //carga el ultimo EVENTO lei en el archivo
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");

                //si el dato en el archivo empieza con EVENTO, se crea un evento
                if (partes[0].equals("EVENTO")) {
                    //a partir del .split asignamos valores
                    int id = Integer.parseInt(partes[1]);
                    String nombre = partes[2];
                    String descripcion = partes[3];
                    LocalDate fecha = LocalDate.parse(partes[4]);
                    LocalTime hora = LocalTime.parse(partes[5]);
                    String ubicacion = partes[6];
                    int capacidad = Integer.parseInt(partes[7]);
                    String recurso = partes.length >= 9 ? partes[8] : "";
                    
                    // se crea evento y se actualiza lista
                    eventoActual = new Evento(id, nombre, descripcion, fecha, hora, ubicacion, capacidad, recurso);
                    gestor.getTodosLosEventos().add(eventoActual);
                    
                    // id + 1
                    if (id >= gestor.getProximoId()) {
                        gestor.setProximoId(id + 1);
                    }
                }
                //si el dato en el archivo empieza con INVITADX, se crea un invitado                
                if (partes[0].equals("INVITADX") && eventoActual != null) {
                    // asignacion de valores
                    String dni = partes[1];
                    String nombre = partes[2];
                    String email = partes[3];
                    //  se crea un invitadp y se le agrega al ultimo dato que empieza con EVENTO
                    eventoActual.agregarInvitadx(new Invitadx(dni, nombre, email));
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar el archivo: " + e.getMessage());
        }
    }
}
