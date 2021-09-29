package laboral;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PuebaNomina {
    public static void main(String[] args) throws FileNotFoundException, SQLException {

        Alta alta = new Alta();

        File fichero = new File("empleadosNuevos.txt");
        alta.altaEmpleado(fichero);
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion;

        while (!salir) {

            System.out.println("1. Mostrar empleados");
            System.out.println("2. Mostrar salarios");
            System.out.println("3. Modificar datos de empleados");
            System.out.println("4. Recalcular y actualizar el sueldo de un empleado");
            System.out.println("5. Recalcular y actualizar los sueldos de todos los empleados.");
            System.out.println("6. Realizar una copia de seguridad de la base de datos en ficheros.");
            System.out.println("7. Salir");

            System.out.println("Seleciona la opcion a ejecutar: ");
            opcion = sn.nextInt();
            

            switch (opcion) {
                case 1:
                    int numEmp=1;
                    Connection con = null;
                    String sURL = "jdbc:mysql://localhost:3306/nomina";
                    con = DriverManager.getConnection(sURL, "root", "");
                    Statement comando=con.createStatement();
                    try (ResultSet rs = comando.executeQuery("select * from empleados");) {

                        while (rs.next()) {
                            System.out.println("Empleado "+numEmp+":");
                            System.out.println("Nombre: "+rs.getString(3) + ", dni: " + rs.getString(4) + " ,sexo: " + rs.getString(5)+" ,categoria: "+rs.getInt(1)+" ,antiguedad: "+rs.getInt(1));
                        }

                        con.close();

                    } catch (SQLException sqle) {
                       
                        System.out.println("Error en la ejecución:" + sqle.getErrorCode() + " " + sqle.getMessage());
                        
                    }
                    break;
                case 2:
                    System.out.println("Has seleccionado la opcion 2");
                    break;
                case 3:
                    System.out.println("Has seleccionado la opcion 3");
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Solo números entre 1 y 4");
            }

        }

    }
}
