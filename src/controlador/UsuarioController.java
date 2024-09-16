package controlador;

import conexion.Conexion;
import modelo.Usuario;
import java.sql.*;
import javax.swing.JOptionPane;

/* @author patmi */
public class UsuarioController {

    //Método para iniciar sesión
    public boolean login(Usuario objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        String sql = "select usuario, password from tb_usuario where usuario = '" + objeto.getUsuario() + "' and password = '" + objeto.getPass() + "'";

        Statement st;

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("ERROR AL INICIAR SESIÓN");
            JOptionPane.showMessageDialog(null, "ERROR AL INICIAR SESIÓN");
        }
        return respuesta;
    }
}
