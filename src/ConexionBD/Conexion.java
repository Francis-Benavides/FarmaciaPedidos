
package ConexionBD;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static Connection conexion;
    private static final String usuario = "root";
    private static final String contraseña = "";
    private static final String url = "jdbc:mysql://localhost:3306/farmacia_medicamentos";

    public static Connection abrirConexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, contraseña);
        }catch(SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return conexion;
    }

    public static void cerrarConexion(){
        try{
            if(!conexion.isClosed()){
                conexion.close();
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
