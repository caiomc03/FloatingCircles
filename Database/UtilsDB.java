package Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UtilsDB {

public static void createTableIfNotExists(Connection conn) {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS FloatingCircles (" +
                            "time DOUBLE , " +
                            "speed DOUBLE , " +
                            "size DOUBLE , " +
                            "healthgoal DOUBLE , " +
                            "countalive INTEGER , " +
                            "PRIMARY KEY (time))";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sqlCreate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

public static void insertTable(Connection conn, double time, double speed, double size, double health_goal, int count_alive){
    String sqlInsert = "INSERT INTO FloatingCircles(time, speed, size, healthgoal, countalive) VALUES (?, ?, ?, ?, ?)";
    PreparedStatement stm = null;
    try{
        stm = conn.prepareStatement(sqlInsert);
        stm.setDouble(1, time);
        stm.setDouble(2, speed );
        stm.setDouble(3, size);
        stm.setDouble(4, health_goal);
        stm.setInt(5, count_alive);
        stm.execute();

    }

catch (Exception e){
    e.printStackTrace();
    try{
        conn.rollback();
    }
    catch (SQLException e1){
        System.out.println(e1.getStackTrace());
    }
    finally {
        if (stm != null){
            try{
                stm.close();
            }
            catch(SQLException e1){
                System.out.println(e1.getStackTrace());
            }
        }
    }
}
}


public static void clearTable(Connection conn) {
    String query = "DELETE FROM FloatingCircles";

    try {
        PreparedStatement pst = conn.prepareStatement(query);
        pst.executeUpdate();

        System.out.println("Tabela limpa!");

    } catch (SQLException e) {
        e.printStackTrace();
    }
}



}