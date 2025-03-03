import java.util.Scanner;
import java.sql.*;

public class Student
{
    static Scanner input=new Scanner(System.in);


    public static void insert(Connection con)throws SQLException{
        String insert="INSERT INTO Student(name) values(?)";
        System.out.println("Enter Student Name:");
        String inname=input.nextLine();

        
        PreparedStatement pstm=con.prepareStatement(insert);
        pstm.setString(1,inname);
        pstm.executeUpdate();
        System.out.println("Student Added "+inname);
    }

    public static void update(Connection con)throws SQLException{
        String update="UPDATE student SET name=? where name=?";
        System.out.println("Enter Student Name For Update");
        String oldname=input.nextLine();
        System.out.println("Enter New name Student:");
        String newname=input.nextLine();

        
        PreparedStatement pstm=con.prepareStatement(update);
        pstm.setString(1,newname);
        pstm.setString(2,oldname);
        pstm.executeUpdate();
        System.out.println("Student Name Update:"+newname);
        
    }

    public static void delete(Connection con)throws SQLException{
        String delete="DELETE FROM student WHERE name=?";
        System.out.println("Enter Student Name to Delete");
        String delname=input.nextLine();

        PreparedStatement pstm=con.prepareStatement(delete);
        pstm.setString(1,delname);
        pstm.executeUpdate();
        System.out.println(delname+"Is deleted");
        
    }

    public static  void display(Connection con)throws SQLException{
        String display="select * from Student";

        
        Statement stm=con.createStatement();
        ResultSet r1=stm.executeQuery(display);
        while(r1.next())
        {
            int id=r1.getInt("id");
            String name=r1.getString("name");
            System.out.println(id+" : "+name+" :");
        }
        
    }


    public static void main(String[]args)
    {

        String url="jdbc:mysql://localhost:3306/student";
        String username="root";
        String password="root";

        try{
            Connection con=DriverManager.getConnection(url,username,password);
            System.out.print("Database is Connected");

            System.out.println("---------------------");
            System.out.println("1-Insert");
            System.out.println("2-Update");
            System.out.println("3-Delete");
            System.out.println("4-Display");
            System.out.println("5-Exit");
            System.out.println("Enter Your Choice:");
            
            int choice=input.nextInt();
            input.nextLine();

            switch(choice){
            case 1:
                insert(con);
                break;

            case 2:
                update(con);
                break;

            case 3:
                delete(con);
                break;

            case 4:
                display(con);
                break;

            case 5:
                break;

            default:
                System.out.println("Invalid Input");
            }
        }
        catch(SQLException e)
        {
            System.err.print(e.getMessage());
        }
        catch(ClassNotFoundException e)
        {
            System.err.println(e.getMessage());
        }
    }
}