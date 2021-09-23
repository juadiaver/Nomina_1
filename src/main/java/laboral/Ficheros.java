package laboral;

import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ficheros {
    public static void main(String[] args) throws FileNotFoundException {

        Nomina n = new Nomina();
        FileReader fr = null;
        BufferedReader br = null;
        FileWriter fw = null;
        BufferedWriter bw = null;
        File fichero = new File("Empleado.txt");
        List<Empleado> empleados = new ArrayList();
        List lineaEmpleado = new ArrayList();

        try {
            fr = new FileReader(fichero);
            br = new BufferedReader(fr);
            fw = new FileWriter(new File("Sueldo.txt"));
            bw = new BufferedWriter(fw);
            
            while (br.ready()) {

                String linea = br.readLine();
                lineaEmpleado.add(linea);

            }

            Iterator iter = lineaEmpleado.iterator();

            while (iter.hasNext()) {

                String string = (String) iter.next();
                String[] parts = string.split(",");
                String nombre = parts[0];
                String dni = parts[1];
                String sexo = parts[2];
                int antiguedad = Integer.parseInt(parts[3]);
                int categoria = Integer.parseInt(parts[4]);

                Empleado a = new Empleado(antiguedad, categoria, nombre, dni, sexo.charAt(0));
                empleados.add(a);

            }
            for (Empleado emp : empleados) {
                int sueldo = n.sueldo(emp);
                bw.write("Dni: "+emp.dni+" Sueldo: "+sueldo);
                bw.newLine();

            }

        } catch (IOException ex) {
            System.out.println("Mensaje: " + ex.getMessage());
        } catch (DatosNoCorrectosException ex) {
            System.out.println("Mensaje: " + ex.getMessage());
        }

        finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
}
