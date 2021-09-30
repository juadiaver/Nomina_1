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
            int numEmp = 1;
            Connection con = null;
            String sURL = "jdbc:mysql://localhost:3306/nomina";
            con = DriverManager.getConnection(sURL, "root", "");
            Statement comando = con.createStatement();

            switch (opcion) {

                case 1:
                    numEmp = 1;
                    try (ResultSet rs = comando.executeQuery("select * from empleados");) {

                        while (rs.next()) {
                            System.out.println("Empleado " + numEmp + ":");
                            System.out.println("Nombre: " + rs.getString(3) + ", dni: " + rs.getString(4) + " ,sexo: "
                                    + rs.getString(5) + " ,categoria: " + rs.getInt(1) + " ,antiguedad: "
                                    + rs.getInt(1));
                        }

                    } catch (SQLException sqle) {

                        System.out.println("Error en la ejecución:" + sqle.getErrorCode() + " " + sqle.getMessage());

                    }
                    break;
                case 2:
                    numEmp = 1;
                    try (ResultSet rs = comando.executeQuery("select * from nominas");) {

                        while (rs.next()) {
                            System.out.println("Empleado " + numEmp + ":");
                            System.out.println("Dni: " + rs.getString(2) + ", sueldo: " + rs.getInt(1));
                        }

                    } catch (SQLException sqle) {

                        System.out.println("Error en la ejecución:" + sqle.getErrorCode() + " " + sqle.getMessage());

                    }
                    break;
                case 3:

                    String dni = "";
                    System.out.println("Vas a modificar los datos de un empleado.");
                    System.out.println("Dime el dni del empleado a modificar");
                    dni = sn.nextLine();

                    System.out.println("Que quieres cambiar:");
                    System.out.println("1. Cambiar nombre");
                    System.out.println("2. Cambiar dni");
                    System.out.println("3. Cambiar antiguedad");
                    System.out.println("4. Cambiar categoria");
                    System.out.println("5. Cambiar sexo");
                    System.out.println("6. Salir");

                    System.out.println("Seleciona la opcion a ejecutar: ");
                    opcion = sn.nextInt();
                    while (!salir) {

                        switch (opcion) {

                            case 1:
                            String newName;
                            System.out.println("Dime el nuevo nombre: ");
                            newName= sn.nextLine();
                            try (PreparedStatement stmt = con.prepareStatement("UPDATE empleados SET nombre = ? where dni = ?");) {
            
                                stmt.setString(1, newName);
                                stmt.setString(2, dni);
                                
                                stmt.executeUpdate();
                    
                            } catch (SQLException sqle) {
                                System.out.println("Error en la ejecución:" + sqle.getErrorCode() + " " + sqle.getMessage());
                            }
                            break;
                            case 2:
                            String newDni;
                            System.out.println("Dime el nuevo dni: ");
                            newDni= sn.nextLine();
                            try (PreparedStatement stmt = con.prepareStatement("UPDATE empleados SET dni = ? where dni = ?");) {
            
                                stmt.setString(1, newDni);
                                stmt.setString(2, dni);
                                
                                stmt.executeUpdate();
                    
                            } catch (SQLException sqle) {
                                System.out.println("Error en la ejecución:" + sqle.getErrorCode() + " " + sqle.getMessage());
                            }
                            break;
                            case 3:
                            int newAnt;
                            System.out.println("Dime la nueva antiguedad ");
                            newAnt= sn.nextInt();
                            sn.nextLine();
                            try (PreparedStatement stmt = con.prepareStatement("UPDATE empleados SET antiguedad = ? where dni = ?");) {
            
                                stmt.setInt(1, newAnt);
                                stmt.setString(2, dni);
                                
                                stmt.executeUpdate();
                    
                            } catch (SQLException sqle) {
                                System.out.println("Error en la ejecución:" + sqle.getErrorCode() + " " + sqle.getMessage());
                            }
                            break;
                            case 4:
                            int newCat;
                            System.out.println("Dime la nueva categoria: ");
                            newCat= sn.nextInt();
                            sn.nextLine();
                            try (PreparedStatement stmt = con.prepareStatement("UPDATE empleados SET categoria = ? where dni = ?");) {
            
                                stmt.setInt(1, newCat);
                                stmt.setString(2, dni);
                                
                                stmt.executeUpdate();

                                Nomina n=new Nomina();
                    
                            } catch (SQLException sqle) {
                                System.out.println("Error en la ejecución:" + sqle.getErrorCode() + " " + sqle.getMessage());
                            }
                            break;
                            case 5:
                            String newSex;
                            System.out.println("Dime el nuevo sexo: ");
                            newSex=sn.nextLine();

                            try (PreparedStatement stmt = con.prepareStatement("UPDATE empleados SET sexo = ? where dni = ?");) {
            
                                stmt.setString(1, newSex);
                                stmt.setString(2, dni);
                                
                                stmt.executeUpdate();
                    
                            } catch (SQLException sqle) {
                                System.out.println("Error en la ejecución:" + sqle.getErrorCode() + " " + sqle.getMessage());
                            }
                            break;
                            case 6:

                            salir=true;
                            break;
                        }

                    }

                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Solo números entre 1 y 4");
            }
            con.close();
        }

    }
}
