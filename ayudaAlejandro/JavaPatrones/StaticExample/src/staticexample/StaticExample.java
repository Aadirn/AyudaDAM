/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package staticexample;

/**
 *
 * @author Castelao
 */
public class StaticExample {
    private static int x =0;
    
    private int y=0;
    
    public StaticExample(){
        x++;
        
        y++;
    }
    public static void main(String[] args) {
        StaticExample se1 = new StaticExample();
        StaticExample se2 = new StaticExample();
        StaticExample se3 = new StaticExample();
        
        System.out.println("x: "+se1.x+" "+se2.x+" "+se3.x);
        System.out.println("x: "+se1.y+" "+se2.y+" "+se3.y);
    }
    
}
