import java.sql.*;
import javax.swing.*;
public class Conex {
    public Connection miconex() {
        String ip = ":3306";
        String db = "company";
        String userDb = "root";
        String passDb = ""; 
        int b = 0;
         try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver correcto");
            b = 1;
        }catch(ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null, ex);
        }

        Connection c = null;
        if(b==1){
            try{
            c = DriverManager.getConnection("jdbc:mysql://"+ip+"/"+db,userDb,passDb);
            //JOptionPane.showMessageDialog(null,"Conexion correcta!");
        } catch(SQLException s){
            System.out.println(s);
           JOptionPane.showMessageDialog(null, s);
        }
    }
        return c;
    }
}