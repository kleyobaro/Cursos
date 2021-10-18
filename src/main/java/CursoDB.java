/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author krist
 */
import java.util.ArrayList;
/**
 *
 * @author krist
 */




import java.util.ArrayList;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author obetancourth
 */

public class CursoDB {
    private ArrayList _cursoItems;
    
    public CursoDB(){
            this._cursoItems = new ArrayList<CursoItem>();
    }
    
    public ArrayList<CursoItem> getCursoItems(){
        return this.getCursoItems(false);
    }
    
    public void tableInitialize(){
        String sqlCreate = "CREATE TABLE IF NOT EXISTS CURSO"
                        + " (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                        + " NOMBRE DEL CURSO TEXT NOT NULL,"
                        + " PRECIO TEXT NOT NULL,"
                        + " DURACION TEXT NOT NULL"
                        + ")";
       
        try {
            Statement comando = Conexion.getConexion().createStatement();
            int resultado = comando.executeUpdate(sqlCreate);
            comando.close();
        } catch( Exception ex){
            System.err.println(ex.getMessage());
        }
    }
    
    public ArrayList<CursoItem> getCursoItems(boolean forceLoad){
        try {
           if (forceLoad) {
                Statement comando =  Conexion.getConexion().createStatement();
                ResultSet misRegistro = comando.executeQuery("SELECT * from CURSO;");
                this._cursoItems.clear();
                while (misRegistro.next()) {
                    CursoItem registro = new CursoItem();
                    registro.setId(misRegistro.getInt("ID"));
                    registro.setNombre(misRegistro.getString("NOMBRE DEL CURSO"));
                    registro.setPrecio(misRegistro.getString("PRECIO"));
                    registro.setDuracion(misRegistro.getString("DURACION"));
                    this._cursoItems.add(registro);
                }
                comando.close();
           }
           return this._cursoItems;
        } catch(Exception ex){
            System.err.println(ex.getMessage());
            return this._cursoItems;
        }   
    }
    
    public CursoItem getCursoItemById(int id){
        try {
            String SQLGetByID = "SELECT * FROM CURSO WHERE ID = ?;";
            PreparedStatement comando =  Conexion.getConexion().prepareStatement(SQLGetByID);
            comando.setInt(1, id);
            ResultSet misRegistro = comando.executeQuery();
            CursoItem registro = new CursoItem();
            while (misRegistro.next()) {
                registro.setId(misRegistro.getInt("ID"));
                registro.setNombre(misRegistro.getString("NOMBRE DEL CURSO"));
                registro.setPrecio(misRegistro.getString("PRECIO"));
                registro.setDuracion(misRegistro.getString("DURACION"));
                break;
            }
            comando.close();

            return registro;
        } catch(Exception ex){
            System.err.println(ex.getMessage());
            return null;
        }   
    }
    
    public int updateCursoItem(CursoItem ItemToUpdate) {
        try {
            String SQLUpdate = "UPDATE CURSO set NOMBRE DEL CURSO=?, PRECIO=?, DURACION=? where ID=?;";
            PreparedStatement comando = Conexion.getConexion().prepareStatement(SQLUpdate);
            
            comando.setString(1, ItemToUpdate.getNombre());
            comando.setString(2, ItemToUpdate.getPrecio());
            comando.setString(3, ItemToUpdate.getDuracion());
            comando.setInt(4, ItemToUpdate.getId());
            
            int registrAfectado = comando.executeUpdate();
            comando.close();
            return registrAfectado;
            
        } catch( Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
     public int insertCursoItem(CursoItem ItemToInsert) {
        try {
            String SQLInsert = "INSERT INTO CURSO (NOMBRE, PRECIO, DURACION) values (?, ?, ?);";
            PreparedStatement comando = Conexion.getConexion().prepareStatement(SQLInsert);
            
            comando.setString(1, ItemToInsert.getNombre());
            comando.setString(2, ItemToInsert.getPrecio());
            comando.setString(3, ItemToInsert.getDuracion());
            
            int registrAfectado = comando.executeUpdate();
            comando.close();
            return registrAfectado;
            
        } catch( Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
     
    public int deleteCursoItem(CursoItem ItemToDelete) {
        try {
            String SQLDelete = "DELETE FROM CURSO WHERE ID = ?;";
            PreparedStatement comando = Conexion.getConexion().prepareStatement(SQLDelete);
            
            comando.setInt(1, ItemToDelete.getId());
            
            int registrAfectado = comando.executeUpdate();
            comando.close();
            return registrAfectado;
            
        } catch( Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
}