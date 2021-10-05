package laboral;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ManejoDatos {

    public void mostrarEmpleados() throws SQLException {
        Connection con = null;
        String sURL = "jdbc:mysql://localhost:3306/nomina";
        con = DriverManager.getConnection(sURL, "root", "");
        Statement comando = con.createStatement();
        int numEmp = 1;

        try (ResultSet rs = comando.executeQuery("select * from empleados");) {

            while (rs.next()) {
                System.out.println("Empleado " + numEmp + ":");
                System.out.println("Nombre: " + rs.getString(3) + ", dni: " + rs.getString(4) + " ,sexo: "
                        + rs.getString(5) + " ,categoria: " + rs.getInt(1) + " ,antiguedad: " + rs.getInt(2));
                numEmp++;
            }

        } catch (SQLException sqle) {

            System.out.println("Error en la ejecución:" + sqle.getErrorCode() + " " + sqle.getMessage());

        }
        con.close();
    };

    public void mostrarSalario() throws SQLException {

        Connection con = null;
        String sURL = "jdbc:mysql://localhost:3306/nomina";
        con = DriverManager.getConnection(sURL, "root", "");
        Statement comando = con.createStatement();
        int numEmp = 1;

        try (ResultSet rs = comando.executeQuery("select * from nominas");) {

            while (rs.next()) {
                System.out.println("Empleado " + numEmp + ":");
                System.out.println("Dni: " + rs.getString(1) + ", sueldo: " + rs.getInt(2));

            }

        } catch (SQLException sqle) {

            System.out.println("Error en la ejecución:" + sqle.getErrorCode() + " " + sqle.getMessage());

        }

        con.close();

    };

    public void modificarUsuario(String dni) throws SQLException, DatosNoCorrectosException {

        Connection con = null;
        String sURL = "jdbc:mysql://localhost:3306/nomina";
        con = DriverManager.getConnection(sURL, "root", "");
        Statement comando = con.createStatement();
        Scanner sr = new Scanner(System.in);
        boolean salirSub = false;
        int opcion = 0;
        while (!salirSub) {

            System.out.println("Que quieres cambiar:");
            System.out.println("1. Cambiar nombre");
            System.out.println("2. Cambiar categoria");
            System.out.println("3. Cambiar antiguedad");
            System.out.println("4. Cambiar sexo");
            System.out.println("5. Salir");
            System.out.println("Seleciona la opcion a ejecutar: ");
            opcion = sr.nextInt();

            switch (opcion) {

                case 1:
                    cambiarNombre(dni);
                    break;
                case 2:
                    cambiarCategoria(dni);
                    calcularSueldo(dni);
                    break;
                case 3:
                    cambiarAntiguedad(dni);
                    calcularSueldo(dni);
                    break;
                case 4:
                    cambiarSexo(dni);
                    break;
                case 5:

                    salirSub = true;
                    break;
            }
        }

        con.close();
    };

    /**
     * Metodo cambia nombre
     * 
     * @param dni
     * @throws SQLException
     */
    public void cambiarNombre(String dni) throws SQLException {
        Connection con = null;
        String sURL = "jdbc:mysql://localhost:3306/nomina";
        con = DriverManager.getConnection(sURL, "root", "");
        Statement comando = con.createStatement();
        Scanner sr = new Scanner(System.in);

        String newName;
        System.out.println("Dime el nuevo nombre: ");
        newName = sr.nextLine();
        try (PreparedStatement stmt = con.prepareStatement("UPDATE empleados SET nombre = ? where dni = ?");) {

            stmt.setString(1, newName);
            stmt.setString(2, dni);

            stmt.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:" + sqle.getErrorCode() + " " + sqle.getMessage());
        }

        con.close();
    };

    public void cambiarCategoria(String dni) throws SQLException {

        Connection con = null;
        String sURL = "jdbc:mysql://localhost:3306/nomina";
        con = DriverManager.getConnection(sURL, "root", "");
        Statement comando = con.createStatement();
        Scanner sr = new Scanner(System.in);

        String newCat;
        System.out.println("Dime la nueva categoria: ");
        newCat = sr.nextLine();
        try (PreparedStatement stmt = con.prepareStatement("UPDATE empleados SET categoria = ? where dni = ?");) {

            stmt.setString(1, newCat);
            stmt.setString(2, dni);

            stmt.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:" + sqle.getErrorCode() + " " + sqle.getMessage());
        }

        con.close();
    };

    public void cambiarAntiguedad(String dni) throws SQLException {

        Connection con = null;
        String sURL = "jdbc:mysql://localhost:3306/nomina";
        con = DriverManager.getConnection(sURL, "root", "");
        Statement comando = con.createStatement();
        Scanner sr = new Scanner(System.in);

        String newAnt;
        System.out.println("Dime la nueva antiguedad: ");
        newAnt = sr.nextLine();
        try (PreparedStatement stmt = con.prepareStatement("UPDATE empleados SET anyos = ? where dni = ?");) {

            stmt.setString(1, newAnt);
            stmt.setString(2, dni);

            stmt.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:" + sqle.getErrorCode() + " " + sqle.getMessage());
        }
        
        con.close();
    };

    public void cambiarSexo(String dni) throws SQLException {

        Connection con = null;
        String sURL = "jdbc:mysql://localhost:3306/nomina";
        con = DriverManager.getConnection(sURL, "root", "");
        Statement comando = con.createStatement();
        Scanner sr = new Scanner(System.in);

        String newSex;
        System.out.println("Dime el nuevo sexo: ");
        newSex = sr.nextLine();

        try (PreparedStatement stmt = con.prepareStatement("UPDATE empleados SET sexo = ? where dni = ?");) {

            stmt.setString(1, newSex);
            stmt.setString(2, dni);

            stmt.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:" + sqle.getErrorCode() + " " + sqle.getMessage());
        }
    };

    public void calcularSueldo(String dni) throws SQLException, DatosNoCorrectosException {

        Nomina n = new Nomina();
        Connection con = null;
        String sURL = "jdbc:mysql://localhost:3306/nomina";
        con = DriverManager.getConnection(sURL, "root", "");
        Statement comando = con.createStatement();
        Scanner sr = new Scanner(System.in);
        Empleado a = crearEmpleado(dni);
        try (PreparedStatement stmt = con.prepareStatement("UPDATE nominas SET sueldo = ? where dni = ?");) {

            stmt.setInt(1, n.sueldo(a));
            stmt.setString(2, dni);

            stmt.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:" + sqle.getErrorCode() + " " + sqle.getMessage());
        }
        

    };

    public Empleado crearEmpleado(String dni) throws SQLException, DatosNoCorrectosException {
        Empleado a = null;
        ResultSet rs;
        Connection con = null;
        String sURL = "jdbc:mysql://localhost:3306/nomina";
        con = DriverManager.getConnection(sURL, "root", "");
        PreparedStatement stmt = con.prepareStatement("SELECT * from empleados where dni=?");
        stmt.setString(1, dni);
        rs = stmt.executeQuery();

        while (rs.next()) {

            a = new Empleado(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5).charAt(0));
        }

        return a;
    }

}
