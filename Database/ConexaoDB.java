package Database;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class ConexaoDB {
    static{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            }
        catch(ClassNotFoundException e){
            throw new RuntimeException(e);
        }
        }

    public Connection conectar() throws SQLException{
        String servidor = "localhost";
        String porta = "3306";
        String database = "dados";
        String usuario = "root";
        String senha = "meuZezinho@10";
        
        Connection seila = DriverManager.getConnection("jdbc:mysql://"+servidor+":"+porta+"/"+database+"?user="+usuario+"&password="+senha);
        
        return seila;
    }

    public static void desconectar(Connection conn) throws SQLException{
        conn.close();
    }
}