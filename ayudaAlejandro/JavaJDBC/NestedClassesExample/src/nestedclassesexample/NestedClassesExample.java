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
public class NestedClassesExample {
    
    private static String staticMsgExample = "Hola static desde contenedora";
    private String msgExample = "Hola desde contenedora";
    
    // Clase ANONIMA
    private void creaClaseAnonima() {
        
        // NO pueden definir constructores
        
        // No pueden extender/implementar otras interfaces
       
        Animal perro = new Animal() {
            
            //private static String msg;   No pueden contener miembros static
            // salvo que sean static final
            
            @Override
            public void sayHello() {
                System.out.println("Hola desde clase anonima");
                System.out.println("GUAUUUUU! ");
                
                // Acceso a vbles no static
                System.out.println(msgExample);
                
                // Acceso a vbles static
                System.out.println(staticMsgExample);
            }
            
        };
        
        perro.sayHello();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Accediendo a la clase anidada static
        System.out.println("ACCESO A LA CLASE INTERNA STATIC\n---------------------------");
        
        // Acceso a miembros static
        System.out.println(Contenedora.AnidadaStatic.staticAnidadaMsg);
        
        // Crear una instancia de la clase anidada
        Contenedora.AnidadaStatic anidadaStatic = new Contenedora.AnidadaStatic();
        
        anidadaStatic.sayHello();
        
        /**********************************************************************/
        // Accediendo a la clase anidada no-static
        System.out.println("\nACCESO A LA CLASE INTERNA NO STATIC\n----------------------------");
        
        Contenedora contenedora =  new Contenedora();
        Contenedora.AnidadaNoStatic anidadaNoStatic = contenedora.new AnidadaNoStatic();
        anidadaNoStatic.sayHello();
        
        //Contenedora.AnidadaNoStatic anidadaNoStatic2 = new Contenedora().new AnidadaNoStatic();
        
        /**********************************************************************/
        // Accediendo a la clase anidada local
        System.out.println("\nACCESO A LA CLASE INTERNA LOCAL\n----------------------------");
        contenedora.showMessageFromLocal();

        /**********************************************************************/
        // Accediendo a la clase anónima
        System.out.println("\nACCESO A LA CLASE ANÓNIMA\n----------------------------");
        
        new NestedClassesExample().creaClaseAnonima();
    }
    
}
