/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author krist
 */
import java.sql.DriverManager;
import java.sql.Connection;
public class Conexion {
    private static Connection _conexion = null;
    
    private Conexion(){

    }
    
    public static Connection getConexion(){
        try {
            if(_conexion == null){
                Class.forName("org.sqlite.JDBC");
                _conexion = DriverManager.getConnection("jdbc:sqlite:CursoDB.db");
                return _conexion;
            } else {
                return _conexion;
            }
        
        } catch (Exception ex){
            System.err.println("Error: " + ex.getMessage());
            return null;
        }
    }
}
