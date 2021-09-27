package laboral;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConexionBD {

    public static void main(String[] args) throws SQLException {

        Connection con = null;
        String sURL = "jdbc:mysql://localhost:3306/nomina";
        con = DriverManager.getConnection(sURL, "root", "");

        try (PreparedStatement stmt = con.prepareStatement("INSERT INTO empleados VALUES (?,?,?,?,?)");) {
            
            int categoria = 4;
            int anyos = 7;
            String nombre = "James Cosling";
            String dni = "32000032G";
            String sexo = "M";

            stmt.setInt(1, categoria);
            stmt.setInt(2, anyos);
            stmt.setString(3, nombre);
            stmt.setString(4, dni);
            stmt.setString(5, sexo);

            stmt.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:" + sqle.getErrorCode() + " " + sqle.getMessage());
        }

        try (PreparedStatement stmt = con.prepareStatement("INSERT INTO empleados VALUES (?,?,?,?,?)");) {
            
            int categoria = 1;
            int anyos = 0;
            String nombre = "Ada Lovelace";
            String dni = "32000031R";
            String sexo = "F";

            stmt.setInt(1, categoria);
            stmt.setInt(2, anyos);
            stmt.setString(3, nombre);
            stmt.setString(4, dni);
            stmt.setString(5, sexo);

            stmt.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:" + sqle.getErrorCode() + " " + sqle.getMessage());
        }

        try (PreparedStatement stmt = con.prepareStatement("UPDATE empleados SET anyos = ? where dni = ?");) {
            
            stmt.setInt(1, 1);
            stmt.setString(2, "32000031R");
            
            stmt.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:" + sqle.getErrorCode() + " " + sqle.getMessage());
        }

        try (PreparedStatement stmt = con.prepareStatement("UPDATE empleados SET categoria = ? where dni = ?");) {
            
            stmt.setInt(1, 9);
            stmt.setString(2, "32000031R");
            
            stmt.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:" + sqle.getErrorCode() + " " + sqle.getMessage());
        }

    }
}
