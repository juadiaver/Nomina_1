package laboral;

import java.io.BufferedReader;
import java.io.File;
import java.util.Scanner;

public class Ficheros {
    public static void main(String[] args) {

        File fichero = new File("Empleado.txt");
        Scanner s = null;

        try {
            // Leemos el contenido del fichero
            
            s = new Scanner(fichero);
            String[] lineas = new String[5];
            int n = 0;
            // Leemos linea a linea el fichero
            while (s.hasNextLine()) {
                String linea = s.next();
                lineas[n] = linea;
                n ++;

            }
            for (int i = 0; i < lineas.length; i++) {
                System.out.println(lineas[i]);
            }
            int antiguedad= Integer.parseInt(lineas[3]);
            int categoria= Integer.parseInt(lineas[4]);
            String nombre= lineas[0];
            String dni= lineas[1];
            String sexo= lineas[2];
            Empleado a = new Empleado(antiguedad,categoria,nombre,dni,sexo.charAt(0));
            a.imprime();

        } catch (Exception ex) {
            System.out.println("Mensaje: " + ex.getMessage());
        } finally {
            // Cerramos el fichero tanto si la lectura ha sido correcta o no
            try {
                if (s != null)
                    s.close();
            } catch (Exception ex2) {
                System.out.println("Mensaje 2: " + ex2.getMessage());
            }
        }
    }
}
