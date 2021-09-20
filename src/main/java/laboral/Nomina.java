/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboral;

/**
 * Creacion de la clase nomina
 * @author juanjose
 */
public class Nomina {
    
    private static final int SUELDO_BASE[] = 
    {50000, 70000, 90000, 110000, 130000,150000, 170000, 190000, 210000, 230000};

    /**
     * Creacion del metodo sueldo
     * @param e
     * @return 
     */

    public int sueldo(Empleado e){
        
       
        return SUELDO_BASE[e.getCategoria()-1]+5000*e.anyos;
    
    };



}
