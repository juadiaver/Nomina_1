/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboral;

/**
 * Prueba de cambio en git
 * @author juanjose
 */
public class CalculaNominas {
    
    
    public static void main(String[] args) {
        
        //Encerramos la creacion de los empleados y los metodos en yn try catch
        //que recogera los errores.
        
        try {
            Empleado a = new Empleado(4,7,"James Cosling", "32000032G",'M');
            Empleado b= new Empleado("Ada Lovelace", "32000031R",'F');
            
            Nomina n=new Nomina();
        
            escribe(a,b,n);
        
            b.incrAnyo();
            b.setCategoria(9);
        
            escribe(a,b,n);
            
            
            
        } catch (DatosNoCorrectosException ex) {
            ex.getMessage();
        }
        
        
        
    }
    
    
    /**
     * Creacion del metodo escribe
     * @param a
     * @param b
     * @param n 
     */
    
    private static void escribe(Empleado a, Empleado b, Nomina n){
        
        System.out.println("Empleado a");
        System.out.println(a.nombre+" "+a.dni+" "+a.anyos+" "+a.sexo+" "+a.getCategoria()+" "+n.sueldo(a));
        
        System.out.println("Empleado b");
        System.out.println(b.nombre+" "+b.dni+" "+b.anyos+" "+b.sexo+" "+b.getCategoria()+" "+n.sueldo(b));
    
    }
}
