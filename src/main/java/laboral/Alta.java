package laboral;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.File;

public class Alta {

    public void altaEmpleado()throws SQLException {

        Scanner sc = new Scanner(System.in);
        Nomina n = new Nomina();
        System.out.println("Introduce los datos del usuario a insertar: ");
        System.out.println("nombre: ");
        String nombre= sc.nextLine();
        System.out.println("dni: ");
        String dni= sc.nextLine();
        System.out.println("categoria: ");
        int categoria= sc.nextInt();
        System.out.println("años en la empresa: ");
        int anyos= sc.nextInt();
        sc.nextLine();
        System.out.println("sexo: ");
        String sexo= sc.nextLine();

        Connection con = null;
        String sURL = "jdbc:mysql://localhost:3306/nomina";
        con = DriverManager.getConnection(sURL, "root", "");

        try (PreparedStatement stmt = con.prepareStatement("INSERT INTO empleados VALUES (?,?,?,?,?)");) {
            
            

            stmt.setInt(1, categoria);
            stmt.setInt(2, anyos);
            stmt.setString(3, nombre);
            stmt.setString(4, dni);
            stmt.setString(5, sexo);
            stmt.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:" + sqle.getErrorCode() + " " + sqle.getMessage());
        }

        try (PreparedStatement stmt = con.prepareStatement("INSERT INTO nominas VALUES (?,?)");) {
            
            Empleado a = new Empleado(anyos, categoria, nombre, dni, sexo.charAt(0));
            int sueldo= n.sueldo(a);
            stmt.setString(1, dni);
            stmt.setInt(2, sueldo);

            stmt.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:" + sqle.getErrorCode() + " " + sqle.getMessage());
        }
        catch (DatosNoCorrectosException sqle) {
            System.out.println(sqle.getMessage());
        }


    }

    


    public static void main(String[] args) throws SQLException {
        Alta alta= new Alta();
        alta.altaEmpleado();
    }

}
