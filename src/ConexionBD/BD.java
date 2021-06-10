package ConexionBD;

import Class.usuario;
import Class.producto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BD {

    private static Connection mConection;
    private static Statement mStatement;
    private static ResultSet mResultSet;
    private final String bd;
    private final String user;
    private final String password;

    public BD(String bd, String user, String password) {
        mConection = null;
        mStatement = null;
        mResultSet = null;
        this.bd = bd;
        this.user = user;
        this.password = password;
    }

    public boolean Conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            mConection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/" + bd, user, password);
            return mConection != null;
        } catch (Exception e) {
            return false;
        }
    }

    public void Desconectar() {
        try {
            this.mConection.close();
        } catch (Exception e) {
        }
    }

    public Boolean AddUser(usuario mUsuario) {
        try {
            mStatement = mConection.createStatement();
            mStatement.execute("INSERT INTO usuarios (Nombre, Apellido, Email, Dirección, username, password) "
                    + " VALUES ('" + mUsuario.getNombre() + "', '" + mUsuario.getApellido() + "','" + mUsuario.getEmail()
                    + "','" + mUsuario.getDirección() + "','" + mUsuario.getUsername() + "','" + mUsuario.getPassword() + "')");
            return true;
        } catch (SQLException e) {
            System.err.println(e.toString());
            return false;
        }
    }

    public usuario GetUsuario(String usuario) {
        usuario mUsuario = null;
        try {
            mStatement = mConection.createStatement();
            mResultSet = mStatement.executeQuery("SELECT * FROM usuarios WHERE username = '" + usuario + "' ");
            while (mResultSet.next()) {
                mUsuario = new usuario();
                mUsuario.setIdUsuario(mResultSet.getInt("idUsuario"));
                mUsuario.setNombre(mResultSet.getString("Nombre"));
                mUsuario.setApellido(mResultSet.getString("Apellido"));
                mUsuario.setEmail(mResultSet.getString("Email"));
                mUsuario.setDirección(mResultSet.getString("Dirección"));
                mUsuario.setUsername(mResultSet.getString("username"));
                mUsuario.setPassword(mResultSet.getString("password"));
                return mUsuario;
            }
        } catch (SQLException e) {
            return null;
        }
        return mUsuario;
    }

    
        public Boolean AddProducto(producto mProducto) {
        try {
            mStatement = mConection.createStatement();
            mStatement.execute("INSERT INTO productos (nombre, tipo, cantidad, distribuidor, sucursal) "
                    + " VALUES ('" + mProducto.getNombre() + "', '" + mProducto.getTipo()+ "','" + mProducto.getCantidad()
                    + "','" + mProducto.getDistribuidor()+ "','" + mProducto.getSucursal()+ "')");
            return true;
        } catch (SQLException e) {
            System.err.println(e.toString());
            return false;
        }
    }
    
    
}
