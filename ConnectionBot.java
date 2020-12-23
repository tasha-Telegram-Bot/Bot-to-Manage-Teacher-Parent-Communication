import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionBot {
    public static Connection connection;

    public Connection getConnection() {
        try {
            if(connection==null || connection.isClosed()) {
                System.out.println("loading jdbc driver..");
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("connecting to the database...");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fypbot?useTimezone=true&serverTimezone=UTC","root","natashah96");

            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return connection;
    }

}
