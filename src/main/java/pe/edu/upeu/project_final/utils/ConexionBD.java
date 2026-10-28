package pe.edu.upeu.project_final.utils;


import java.sql.*;

public class ConexionBD {
    private static Connection connection;

    public static Connection getConnection(){
        try{
            if (connection == null || connection.isClosed()){
                Class.forName("org.sqlite.JDBC");
                String url = "jdbc:sqlite:C:/sqlite/db/reserva_gp1.db";
                connection = DriverManager.getConnection(url);
                connection.setAutoCommit(true);
                System.out.println("Conexion exitosa a SQLite");
                try(Statement st = connection.createStatement()){
                    st.execute( "PRAGMA foreign_keys=ON;");

                }
            }
            return connection;
        }catch (Exception e){
            throw new RuntimeException("Error de conexion a la BD"+e.getMessage());
        }
    }
}
