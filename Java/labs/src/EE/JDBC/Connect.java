package EE.JDBC;

import java.sql.*;

public class Connect {
    public static void  main(String ... args){
        try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sys as sysdba","sys");
            // URL : jdbc:oracle:thin:@ [HOST_NAME]:[PORT_NUMBER]:[SID]
            if (con != null){
                System.out.println("Connected");
                System.out.println(con.getSchema());
                Statement statement = con.createStatement();
                ResultSet result = statement.executeQuery("select Fname, Money from Stud");
                while (result.next()){
                    System.out.println(result.getString(1)+" "+result.getString(2));
                }
                statement.close();
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
