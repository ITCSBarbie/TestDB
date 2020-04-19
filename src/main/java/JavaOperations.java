import java.util.Scanner;
import java.sql.*;
public class JavaOperations {
    public static void main( String args[] )

    {

        String flag="Y";

        do{

            System.out.println("Select DML Operation For Product Table...");

            System.out.println("1. Insert");

            System.out.println("2. Update");

            System.out.println("3. Delete");

            System.out.println("4. Select");

            System.out.println("5. Exit");

            Scanner reader = new Scanner(System.in);

            System.out.println("Enter a choice: ");

            int n = reader.nextInt();

            Connection c = null;

            Statement stmt = null;

            try {

                Class.forName("org.sqlite.JDBC");

                c = DriverManager.getConnection("jdbc:sqlite:SqliteJavaDB.db");

                c.setAutoCommit(false);

                stmt = c.createStatement();

                String name="",sql="";

                float price=0.0f;

                int quantity=0;

                int id;

                Scanner scanName;

                switch(n){



                    case 1:

                        scanName=new Scanner(System.in);

                        System.out.println("Enter Product Name:");

                        name=scanName.nextLine();

                        System.out.println("Enter Product Price:");

                        price=scanName.nextFloat();

                        System.out.println("Enter Product Quantity:");

                        quantity=scanName.nextInt();

                        sql = "INSERT INTO Product (p_name,price,quantity) " +

                                "VALUES ('" +name+ "'," +

                                price + "," + quantity + ")";

                        stmt.executeUpdate(sql);

                        System.out.println("Inserted Successfully!!!");

                        break;



                    case 2:

                        System.out.println("Enter Product id:");

                        scanName=new Scanner(System.in);

                        id=scanName.nextInt();

                        System.out.println("Enter Product Name:");

                        scanName=new Scanner(System.in);

                        name=scanName.nextLine();

                        System.out.println("Enter Product Price:");

                        price=scanName.nextFloat();

                        System.out.println("Enter Product Quantity:");

                        quantity=scanName.nextInt();



                        sql = "UPDATE Product SET p_name = '"+ name + "',price=" + price +",quantity=" + quantity +

                                " WHERE p_id=" +id ;



                        stmt.executeUpdate(sql);

                        System.out.println("Updated Successfully!!!");

                        break;



                    case 3:

                        System.out.println("Enter Product id:");

                        scanName=new Scanner(System.in);

                        id=scanName.nextInt();

                        sql="DELETE FROM Product WHERE p_id=" + id+";";

                        stmt.executeUpdate(sql);

                        System.out.println("Deleted Successfully!!!");

                        break;



                    case 4:

                        ResultSet rs = stmt.executeQuery("SELECT * FROM Product;");

                        System.out.println("ID\t Name\t\t Price\t Qty ");

                        while ( rs.next() ) {

                            id = rs.getInt("p_id");

                            name = rs.getString("p_name");

                            quantity = rs.getInt("quantity");

                            price = rs.getFloat("price");

                            System.out.println(id+"\t "+name+" \t "+price+"\t "+quantity);

                        }

                        rs.close();

                        break;



                    case 5:

                        System.exit(0);

                        break;



                    default:

                        System.out.println("Oops!!! Wrong Choice...");

                        break;

                }

                stmt.close();

                c.commit();

                c.close();

            }

            catch ( Exception e )

            {

                System.err.println( e.getClass().getName() + ": " + e.getMessage() );

                System.exit(0);

            }



            System.out.println("Continue Y OR N?");

            reader=new Scanner(System.in);

            flag=reader.nextLine();



        }while(flag.equalsIgnoreCase("Y"));

        System.exit(0);

    }
}
