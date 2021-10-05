package laboral;

import java.io.File;
import java.sql.SQLException;
import java.util.Scanner;

public class PuebaNomina {
    public static void main(String[] args) {

        Alta alta = new Alta();

        File fichero = new File("empleadosNuevos.txt");
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion;
        Nomina n = new Nomina();
        ManejoDatos m = new ManejoDatos();

        while (!salir) {

            System.out.println("1. Mostrar empleados");
            System.out.println("2. Mostrar salarios");
            System.out.println("3. Modificar datos de empleados");
            System.out.println("4. Recalcular y actualizar los sueldos de todos los empleados.");
            System.out.println("5. Realizar una copia de seguridad de la base de datos en ficheros.");
            System.out.println("6. Dar de alta a un usuario");
            System.out.println("7. Salir");

            System.out.println("Seleciona la opcion a ejecutar: ");
            opcion = sn.nextInt();

            switch (opcion) {

                case 1:
                    try {
                        m.mostrarEmpleados();
                    } catch (SQLException e) {

                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        m.mostrarSalario();
                    } catch (SQLException e) {

                        e.printStackTrace();
                    }
                    break;
                case 3:

                    String dni = "";
                    System.out.println("Vas a modificar los datos de un empleado.");
                    System.out.println("Dime el dni del empleado a modificar");
                    sn.nextLine();
                    dni = sn.nextLine();

                    try {

                        m.modificarUsuario(dni);
                    } catch (SQLException e) {

                        e.printStackTrace();
                    } catch (DatosNoCorrectosException e) {

                        e.printStackTrace();
                    }

                    break;
                case 4:

                    break;
                case 5:
                    salir = true;
                    break;

                case 6:
                    try {
                        alta.altaEmpleado();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 7:
                    salir = true;
                    break;
                default:
                    System.out.println("Solo números entre 1 y 6");
            }

        }

    }
}
