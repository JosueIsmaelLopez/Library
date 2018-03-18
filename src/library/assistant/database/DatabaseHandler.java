package library.assistant.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DatabaseHandler {
    private static DatabaseHandler handler;
    
    private static final String DB_URL = "jdbc:derby:database;create=true";
    private static Connection conn = null;
    private static Statement stnt = null;
    
    private DatabaseHandler(){
        createConnection();
        setupBookTable();
        setupMemberTable();
    }
    
    public static DatabaseHandler getInstance()
    {
        if(handler == null)
        {
            handler = new DatabaseHandler();
        }
        return handler;
    }
    
    void createConnection(){
        try{
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            conn = DriverManager.getConnection(DB_URL);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public ResultSet execQuery(String query){
        ResultSet result;
        try{
            stnt = conn.createStatement();
            result = stnt.executeQuery(query);
        }catch(SQLException ex){
            System.out.println("Exception at execQuery:dataHandler"+ex.getLocalizedMessage());
            return null;
        }finally{
        }
        return result;
    }
    
    public boolean execAction(String qu){
        try{
            stnt = conn.createStatement();
            stnt.execute(qu);
            return true;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error:" + ex.getMessage(),"Error Ocurred",JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception at execQuery:dataHandler"+ex.getLocalizedMessage());
            return false;
        }finally{
        }
    }
    
    
    void setupBookTable(){
        String TABLE_NAME = "BOOK";
        try{
            stnt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
            if(tables.next()){
                System.out.println("Table " + TABLE_NAME+ " already exists. Ready for go!");
            }else{
                stnt.execute("CREATE TABLE "+ TABLE_NAME + "("
                +" id varchar(200) primary key, \n"
                +" title varchar(200),\n"
                +" author varchar(200),\n"
                +" publisher varchar(100),\n"
                +" isAvail boolean default true"
                +" )");
            }
        }catch(SQLException e){
            System.err.println(e.getMessage() + " ... setupDatabase ");
        }finally{
        }
    }
    
    void setupMemberTable(){
        String TABLE_NAME = "MEMBER";
        try{
            stnt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
            if(tables.next()){
                System.out.println("Table " + TABLE_NAME+ " already exists. Ready for go!");
            }else{
                stnt.execute("CREATE TABLE "+ TABLE_NAME + "("
                +" id varchar(200) primary key, \n"
                +" name varchar(200),\n"
                +" mobile varchar(20),\n"
                +" email varchar(50)"
                +" )");
            }
        }catch(SQLException e){
            System.err.println(e.getMessage() + " ... setupDatabase ");
        }finally{
        }
    }
}
