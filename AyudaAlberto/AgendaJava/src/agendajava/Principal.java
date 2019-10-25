package agendajava;

import excepcion.agendaExceptions;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.Contacto;
import utilities.Fecha;

public class Principal {

    private static final int MAX_CONTACTOS = 21;
    private static final int MAX_ENTRADA = 128;

    private void salir() {
        System.out.println("Bye, bye. Gracias por usar GESTION DE CONTACTOS\n");
        System.exit(0);
    }
    //static int ocupacion = 0;
    private final List<Contacto> contactList;

    public Principal() {
        contactList = new ArrayList<>();
    }

    /*
boolean esContrasenaValida(char *contrasena) {
    char miContrasena[] = "P@ssw0rd";

    return (strcmp(contrasena, miContrasena) == 0);
}
     */
//    boolean esContrasenaValida(char *contrasena) {
//    char miContrasena[MAX_ENTRADA];
//
//    FILE *fichero;
//    if ((fichero = fopen("top-secret.txt", "r")) == NULL) {
//        printf("Error. No se ha podido leer el fichero de contrasena");
//        exit(-4); //Error de lectura de fichero de contraseÃ±a
//    }
//    fgets(miContrasena, MAX_ENTRADA, fichero);
//    fclose(fichero);
//    //miContrasena[strlen(miContrasena) - 1] = '\0';
//    strcpy(miContrasena, strrev(miContrasena));
//
//    return (strcmp(contrasena, miContrasena) == 0);
//}
    public boolean esContrasenaValidaMem(String contrasena) {
        String miContrasena = "P@ssw0rd";
        return miContrasena.equals(contrasena);

    }

    public boolean esFechaValida(String fecha) {

        try {

            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

            formatoFecha.setLenient(false);

            formatoFecha.parse(fecha);

        } catch (ParseException ex) {

            return false;
        }

        return true;

    }

    public void contactoLleno() throws agendaExceptions {
        int restantes;
        if (contactList.size() == MAX_CONTACTOS) {
            throw new agendaExceptions("La agenda está llena. Se siente. \n");
        } else {
            restantes = MAX_CONTACTOS - contactList.size();
            if (restantes < 20 && restantes > 15) {
                System.out.println("Aun queda mucho espacio, quedan " + restantes + " huecos.");
            } else if (restantes < 15 && restantes > 7) {
                System.out.println("Empieza a estar llena, quedan " + restantes + " huecos.");
            } else if (restantes < 7 && restantes > 0) {
                System.out.println("Practicamente llena, quedan " + restantes + " huecos.");
            }
        }
    }

    public void borrarContacto() {
        Scanner keyboard;
        int eleccion;
        listarContactos();
        if (contactList.isEmpty()) {
            System.out.println("No hay nada que borrar, la agenda está vacia");
        } else {
            System.out.println("Que contacto quiere borrar?");
            keyboard = new Scanner(System.in);
            eleccion = Integer.parseInt(keyboard.nextLine());
            contactList.remove(eleccion);
        }
    }

    public void guardarDatos() {
        String path = "C:\\Users\\Castelao\\Desktop\\CProjects\\AgendaJava\\contactosJava.txt";
        int contador = 0;
        String contacto;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (int i = 0; i < contactList.size(); i++) {
                contacto = contactList.get(i).toContacto();
                writer.write(contacto);
                writer.newLine();
            }
            writer.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        contactList.clear();
    }

    public boolean esTipoValido(char tipo) {
        return (tipo == 'a' || tipo == 'e' || tipo == 'f' || tipo == 't');
    }

    public void nuevoContacto() throws agendaExceptions {
        contactoLleno();
        int dia;
        int mes;
        int anho;
        String nombre;
        long telefono;
        char tipo;
        String fechaRaw;
        boolean fechaValida;
        Fecha fecha = null;
        Contacto contactos;
        String fechas[];
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Nombre?: ");
        nombre = keyboard.nextLine();
        keyboard.reset();
        System.out.println("Telefono?: ");
        telefono = Long.parseLong(keyboard.nextLine());
        keyboard.reset();
        do {
            System.out.println("Fecha de Nacimiento? (dd/mm/aaaa): ");
            fechaRaw = keyboard.nextLine();

            fechaValida = esFechaValida(fechaRaw);

            if (fechaValida == true) {
                fechas = fechaRaw.split("/");
                dia = Integer.parseInt(fechas[0]);
                mes = Integer.parseInt(fechas[1]);
                anho = Integer.parseInt(fechas[2]);
                fecha = new Fecha(dia, mes, anho);
                keyboard.reset();
            } else {
                System.out.println("La fecha no es valida");
            }
        } while (!fechaValida);

        do {
            System.out.println("Tipo Contacto? (a)migo, (e)enemigo, (f)amiliar, (t)rabajo: ");
            tipo = keyboard.nextLine().charAt(0);
        } while (!esTipoValido(tipo));
        contactos = new Contacto(nombre, telefono, fecha, tipo);
        contactList.add(contactos);

    }

    public void detalleContacto() {
        listarContactos();
        Scanner keyboard;
        int eleccion;
        int i;
        System.out.println("\n\n\n");
        System.out.println("De que contacto quieres info?");
        keyboard = new Scanner(System.in);
        eleccion = Integer.parseInt(keyboard.nextLine());
        if (eleccion >= 0 && eleccion < contactList.size()) {
            System.out.println("Id: " + eleccion);
            System.out.println("\nNombre: " + contactList.get(eleccion).getNombre());
            System.out.println("\nTelefono: " + contactList.get(eleccion).getTelefono());
            System.out.println("\nFecha Nacimiento: " + contactList.get(eleccion).getFecha().getDia() + "/"
                    + contactList.get(eleccion).getFecha().getMes() + "/"
                    + contactList.get(eleccion).getFecha().getAnho());
            switch (contactList.get(eleccion).getTipo()) {
                case 'a':
                    System.out.println("\nTipo : Amigo\n");
                    break;
                case 'e':
                    System.out.println("\nTipo : Enemigo\n");
                    break;
                case 'f':
                    System.out.println("\nTipo : Familiar\n");
                    break;
                case 't':
                    System.out.println("\nTipo : Trabajo\n");
                    break;
            }

        } else {
            System.out.println("No hay ningun contacto con ese ID\n");
        }
    }

    public void cargarDatos() {
        int dia;
        int mes;
        int anho;
        String nombre;
        long telefono;
        char tipo;
        Fecha fecha;
        String path = "C:\\Users\\Castelao\\Desktop\\CProjects\\AgendaJava\\contactosJava.txt";
        String fichero;
        String valores[];
        Contacto contactos;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while ((fichero = reader.readLine()) != null) {
                valores = fichero.split("#");
                nombre = valores[0];
                telefono = Long.parseLong(valores[1]);
                dia = Integer.parseInt(valores[2]);
                mes = Integer.parseInt(valores[3]);
                anho = Integer.parseInt(valores[4]);
                fecha = new Fecha(dia, mes, anho);
                tipo = valores[5].toLowerCase().charAt(0);
                contactos = new Contacto(nombre, telefono, fecha, tipo);

                contactList.add(contactos);//Porqu2e nullPointer?
            }

        } catch (FileNotFoundException ex) {
            System.err.println("No se encontro el archivo" + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Ocurrió un error con el fichero" + ex.getMessage());
        }
    }

    public void listarContactos() {
        int i;
        for (i = 0; i < contactList.size(); i++) {
            System.out.println(i + ".-" + contactList.get(i).getNombre());
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {
        boolean salir = false;
        int intentos;
        //char cadena[MAX_ENTRADA];
        int opcion;
        Scanner keyboard;
        String contrasena;
        Principal principal = new Principal();

        //Pedimos y comprobamos la contraseña.
        //Después de 3 intentos fallidos se finaliza el programa
        for (intentos = 0;; intentos++) {
            System.out.println("Contrasena?: ");
            keyboard = new Scanner(System.in);
            contrasena = keyboard.nextLine();
            if (principal.esContrasenaValidaMem(contrasena)) {
                break;
            }
            if (intentos == 2) {
                try {
                    throw new agendaExceptions("Alcanzado limite de intentos, lo siento");
                } catch (agendaExceptions ex) {
                    System.out.println(ex.getMessage());
                    principal.salir();
                }

            }
        }

        //Cargamos datos
        //Mostramos el menu y esperamos opción hasta que se elija 5.Salir
        do {
            try {
                //Mostramos menú y pedimos opción
                System.out.println("======= AGENDA DE CONTACTOS =======\n");
                System.out.println("\n");
                System.out.println("1.Nuevo contacto\n");
                System.out.println("2.Listar contactos\n");
                System.out.println("3.Borrar contacto\n");
                System.out.println("4.Detalle contacto\n");
                System.out.println("5.Salir\n");
                System.out.println("\n");
                System.out.println("Elige una opcion: ");

                keyboard = new Scanner(System.in);
                opcion = Integer.parseInt(keyboard.nextLine());
                salir = false;
                switch (opcion) {
                    case 1:
                        principal.nuevoContacto();
                        break;
                    case 2:
                        principal.listarContactos();
                        break;
                    case 3:
                        principal.borrarContacto();
                        break;
                    case 4:
                        principal.detalleContacto();
                        break;
                    case 5:
                        principal.guardarDatos();
                        salir = true;
                        break;
                    default:
                        System.out.println("Opcion no valida\n");
                }
                System.out.println("\n\n\n");
            } catch (agendaExceptions ex) {
                System.out.println(ex.getMessage());
            }

        } while (!salir);
        principal.salir();

    }

}
