/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nestedclassesexample;

/**
 *
 * @author castelao
 */
public class Contenedora {

    private String msg = "Mensaje desde Outer";
    private static String msgStatic = "Mensaje static desde Outer";

    // AnidadaNoStatic static
    public static class AnidadaStatic {

        public static String staticAnidadaMsg = "Mensaje static desde anidada-static";

        public void sayHello() {
            System.out.println("Hola desde clase anidada-static");

            // System.out.println(msg); No tiene acceso a los miembros no static de 
            // la clase contenedora
            System.out.println(msgStatic);
        }
    }

    // AnidadaNoStatic NO static
    public class AnidadaNoStatic {
        //private static String staticAnidadaMsg;  NO puede contener miembros static

        public void sayHello() {
            System.out.println("Hola desde clase anidada no-static");

            // Acceso a miembros static
            System.out.println(msgStatic);

            // Acceso a miembros no static
            System.out.println(msg);
        }
    }

    // Anidada local
    public void showMessageFromLocal() {
        /*public*/ class AnidadaLocal { // No pueden llevar modificador de visibilidad

            // private static String msg;  No pueden contener miembros static
            public void sayHello() {
                System.out.println("Hola desde clase anidada local");

                // Acceso a miembros static
                System.out.println(msgStatic);

                // Acceso a miembros no static
                System.out.println(msg);
            }
        }

        AnidadaLocal anidadaLocal = new AnidadaLocal();
        anidadaLocal.sayHello();
    }
}
