package laboral;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Alta {

    FileReader fr = null;
    BufferedReader br = null;
    List<Empleado> empleados = new ArrayList();
    List lineaEmpleado = new ArrayList();
    

    public void altaEmpleado() throws SQLException {

        Scanner sc = new Scanner(System.in);
        Nomina n = new Nomina();
        System.out.println("Introduce los datos del usuario a insertar: ");
        System.out.println("nombre: ");
        String nombre = sc.nextLine();
        System.out.println("dni: ");
        String dni = sc.nextLine();
        System.out.println("categoria: ");
        int categoria = sc.nextInt();
        System.out.println("años en la empresa: ");
        int anyos = sc.nextInt();
        sc.nextLine();
        System.out.println("sexo: ");
        String sexo = sc.nextLine();
        sc.close();
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
            int sueldo = n.sueldo(a);
            stmt.setString(1, dni);
            stmt.setInt(2, sueldo);

            stmt.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:" + sqle.getErrorCode() + " " + sqle.getMessage());
        } catch (DatosNoCorrectosException sqle) {
            System.out.println(sqle.getMessage());
        }

    }

    public void altaEmpleado(File a) throws SQLException,FileNotFoundException {

        Connection con = null;
        String sURL = "jdbc:mysql://localhost:3306/nomina";
        con = DriverManager.getConnection(sURL, "root", "");
        Nomina n = new Nomina();

        try  {

            fr = new FileReader(a);
            br = new BufferedReader(fr);

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

                Empleado b = new Empleado(antiguedad, categoria, nombre, dni, sexo.charAt(0));
                empleados.add(b);
               

                try (PreparedStatement stmt = con.prepareStatement("INSERT INTO empleados VALUES (?,?,?,?,?)");) {

                    stmt.setInt(1, categoria);
                    stmt.setInt(2,antiguedad );
                    stmt.setString(3, nombre);
                    stmt.setString(4, dni);
                    stmt.setString(5, sexo);
                   
                    stmt.executeUpdate();

                } catch (SQLException sqle) {
                    System.out.println("Error en la ejecución:" + sqle.getErrorCode() + " " + sqle.getMessage());
                }

                try (PreparedStatement st = con.prepareStatement("INSERT INTO nominas VALUES (?,?)");) {

            
                    int sueldo = n.sueldo(b);
                    st.setString(1, dni);
                    st.setInt(2, sueldo);
        
                    st.executeUpdate();
        
                } catch (SQLException sqle) {
                    System.out.println("Error en la ejecución:" + sqle.getErrorCode() + " " + sqle.getMessage());
                }
            }

            

        } catch (DatosNoCorrectosException ex) {
            System.out.println("Mensaje: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Mensaje: " + ex.getMessage());
        }

        

    }

    public static void main(String[] args) throws SQLException,FileNotFoundException {
        Alta alta = new Alta();

        File fichero = new File("empleadosNuevos.txt");
        alta.altaEmpleado(fichero);
    }

    

}
