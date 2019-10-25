package agendajava;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import utilities.Contacto;
import utilities.Fecha;

/**/
public class Principal {

    private static final int MAX_CONTACTOS = 20;
    private static final int MAX_ENTRADA = 128;
    private static List<Contacto> contactList;
    static int ocupacion = 0;

    public static void contactoLleno() {
        int restantes;
        if (ocupacion >= MAX_CONTACTOS) {
            System.out.println("La agenda está llena. Se siente. \n");
        } else {
            restantes = MAX_CONTACTOS - ocupacion;
            if (restantes < 20 || restantes > 15) {
                System.out.println("Aun queda mucho espacio, quedan " + restantes + " huecos.");
            } else if (restantes < 15 || restantes > 7) {
                System.out.println("Empieza a estar llena, quedan " + restantes + " huecos.");
            } else if (restantes < 7 || restantes > 0) {
                System.out.println("Practicamente llena, quedan " + restantes + " huecos.");
            }
        }
    }

    public static void contrasena() {
        Scanner keyboard;
        String pass = "P@ssw0rd";
        keyboard = new Scanner(System.in);
        int contador = 0;
        String passComp;

        do {
            System.out.println("\nIntroduzca la contraseña: ");
            passComp = keyboard.nextLine();
            if (passComp.equals(pass)) {
                break;
            } else {
                contador++;
            }
            if (contador == 3) {
                System.out.println("Demasiados fallos, adios");
                System.exit(0);
            }

        } while (contador <= 3);

    }

    public static void guardarDatos() {
        String path = "C:\\Users\\Castelao\\Desktop\\CProjects\\AgendaJava\\contactos2.txt";
        int contador = 0;
        String contacto;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            Iterator iter = contactList.iterator();
            while (iter.hasNext()) {
                contacto = contactList.get(contador).toContacto();
                writer.write(contacto);
                writer.newLine();
                //contador++;
            }
            writer.close();

            /*int read;
            while ((read = input.read()) != -1) {
                char letra = (char) read;
                if (letra == ' ') {
                    letra = '-';
                }
                escribidor.write(letra);
            }*/
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static boolean esTipoValido(char tipo) {
        return (tipo == 'a' || tipo == 'e' || tipo == 'f' || tipo == 't');
    }

    public static void nuevoContacto() {
        contactoLleno();
        int dia;
        int mes;
        int anho;
        String nombre;
        long telefono;
        char tipo;
        String fechaRaw;
        Fecha fecha;
        Contacto contactos;
        String fechas[] = new String[MAX_ENTRADA];
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Nombre?: ");
        nombre = keyboard.nextLine();
        keyboard.reset();
        System.out.println("Telefono?: ");
        telefono = Long.parseLong(keyboard.nextLine());
        keyboard.reset();
        //HACER PARSEO DE FECHA
        System.out.println("Fecha de Nacimiento? (dd/mm/aaaa): ");
        fechas = keyboard.nextLine().split("/");
        dia = Integer.parseInt(fechas[0]);
        mes = Integer.parseInt(fechas[1]);
        anho = Integer.parseInt(fechas[2]);
        fecha = new Fecha(dia, mes, anho);
        keyboard.reset();

        do {
            System.out.println("Tipo Contacto? (a)migo, (e)enemigo, (f)amiliar, (t)rabajo: ");
            tipo = keyboard.nextLine().charAt(0);
        } while (!esTipoValido(tipo));
        contactos = new Contacto(nombre, telefono, fecha, tipo);
        contactList.add(contactos);
        ocupacion++;

    }

    public static void detalleContacto() {
        listarContactos();
        Scanner keyboard;
        int eleccion;
        int i;
        System.out.println("\n\n\n");
        System.out.println("De que contacto quieres info?");
        keyboard = new Scanner(System.in);
        eleccion = Integer.parseInt(keyboard.nextLine());
        if (eleccion >= 0 && eleccion < ocupacion) {
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

    public static void cargarDatos() {
        int dia;
        int mes;
        int anho;
        String nombre;
        long telefono;
        char tipo;
        Fecha fecha;
        String path = "D:\\Cosas Clase\\Cosas DAM\\CProjects\\AgendaJava\\contactos.txt";
        String fichero;
        String valores[] = new String[MAX_ENTRADA];
        Contacto contactos;
        contactList = new ArrayList<>();
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

                contactList.add(ocupacion, contactos);//Porqu2e nullPointer?
                ocupacion++;
            }

        } catch (FileNotFoundException ex) {
            System.err.println("No se encontro el archivo" + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Ocurrió un error con el fichero" + ex.getMessage());
        }
    }

    public static void listarContactos() {
        int i;
        for (i = 0; i < contactList.size(); i++) {
            System.out.println(i + ".-" + contactList.get(i).getNombre());
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {
        boolean salir;
        int intentos;
        //char cadena[MAX_ENTRADA];
        int opcion;
        Scanner keyboard;

        //Pedimos y comprobamos la contraseña.
        //Después de 3 intentos fallidos se finaliza el programa
        /*for (intentos=0; ; intentos++) {
        printf("Contrasena?: ");
        fgets(cadena, MAX_ENTRADA, stdin);
        cadena[strlen(cadena) - 1] = '\0';
        if(esContrasenaValida(cadena)) break;
        if(intentos==2) return -1;
    }*/
        contrasena();
        //Cargamos datos
        cargarDatos();
        //Mostramos el menu y esperamos opción hasta que se elija 5.Salir
        do {
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
                    nuevoContacto();
                    break;
                case 2:
                    listarContactos();
                    break;
                case 3:
                    //borrarContacto();
                    break;
                case 4:
                    detalleContacto();
                    break;
                case 5:
                    guardarDatos();
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion no valida\n");
            }
            System.out.println("\n\n");

        } while (!salir);

        System.out.println("Bye, bye. Gracias por usar GESTION DE CONTACTOS\n");
    }

}
