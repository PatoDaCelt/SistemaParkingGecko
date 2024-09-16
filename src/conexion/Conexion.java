package conexion;

import java.sql.*;

/* @author patmi */
public class Conexion {
    //Conexión local
    public static Connection conectar() {
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/bd_vehiculos", "root", "");
            return cn;
        } catch (SQLException e) {
            System.out.println("ERROR DE CONEXION LOCAL" + e);
        }
        return null;
    }
}
