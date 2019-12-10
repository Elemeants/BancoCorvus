/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancocorvus.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Jdany
 * @param <T> Model of object
 */
public abstract class SQLBase<T> {
    private static String dbUrl = "jdbc:mysql://localhost:3306/bancocorvus";
    private static String dbUsr = "root";
    private static String dbPass = "";
    
    protected ArrayList<T> ExecuteQuery(final String query, ReadFromDb<T> reader) {
        Connection Conexion = null;
        Statement Command = null;
        ResultSet Results = null;
        ArrayList<T> data = new ArrayList<>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Conexion = DriverManager.getConnection (dbUrl, dbUsr, dbPass);
            Command = Conexion.createStatement();
            Results = Command.executeQuery(query);
            final int columns = Results.getMetaData().getColumnCount(); 

            while(Results.next()){
                for (int i = 1; i < columns; i++) {
                    System.out.print(Results.getString(i));
                    System.out.print(" ");
                }
                System.out.println("");
                T row = reader.ReadFromDb(Results);
                data.add(row);
            }
        }catch(final Exception e){
            e.printStackTrace();
        }finally{
            try {
                Results.close();
            } catch (final SQLException e) {
                e.printStackTrace();
            }
            try {
                Command.close();
            } catch (final SQLException e) {
                e.printStackTrace();
            }
            try {
                Conexion.close();
            } catch (final SQLException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    protected void ExecuteUpdate(final String query) {
        Connection Conexion = null;
        Statement Command = null;
        int rowCount = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Conexion = DriverManager.getConnection (dbUrl, dbUsr, dbPass);
            Command = Conexion.createStatement();
            rowCount = Command.executeUpdate(query);
            System.out.println("Rows affected: " + rowCount);
        }catch(final Exception e){
            e.printStackTrace();
        }finally{
            try {
                Command.close();
            } catch (final SQLException e) {
                e.printStackTrace();
            }
            try {
                Conexion.close();
            } catch (final SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected String getInsertQuery(final ArrayList<String> properties, final ArrayList<String> values) {
        final String insert_params = String.join(", ", properties);
        final String insert_values = String.join(", ", values);
        return "INSERT INTO " + this.tableName + "(" + insert_params + ") VALUES(" + insert_values + ")";
    }

    protected String getUpdateQuery(final int id, final ArrayList<String> values) {
        final String update_values = String.join(", ", values);
        return "UPDATE " + this.tableName + " SET " + update_values + " WHERE Id=" + id;
    }

    protected String getDeleteQuery(final int Id) {
        return "DELETE FROM " + this.tableName + " WHERE Id=" + Id;
    }

    protected String getGetQuery(final int Id) {
        return "SELECT * FROM " + this.tableName + " WHERE Id=" + Id;
    }

    protected String getGetAllQuery() {
        return "SELECT * FROM " + this.tableName;
    }

    protected String tableName;

    public SQLBase(final String tableName) {
        this.tableName = tableName;
    }
    public abstract boolean Insert(T model);
    public abstract boolean Update(T model);
    public abstract  ArrayList<T> GetAll();
    public abstract T GetById(int id);
    public abstract boolean Delete(int id);
}
