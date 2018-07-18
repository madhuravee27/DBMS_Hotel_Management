package entities;

import edu.csc.dbms.Constants;
import edu.csc.dbms.DBTablePrinter;
import edu.csc.dbms.DBUtil;
import edu.csc.dbms.Entity;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Customers implements Entity {
    private static Scanner scan = new Scanner(System.in);

    @Override
    public void retrieve() throws SQLException {

        String query = "select * from " + Constants.CUSTOMERS_TABLE;

        ResultSet result = DBUtil.executeQuery(query);

        DBTablePrinter.printResultSet(result);

    }

    @Override
    public void create() throws SQLException {

        System.out.println("Enter Customer name : ");
        String name = scan.nextLine();
        System.out.println("Enter DOB(YYYY-MM-DD) : ");
        String dob = scan.nextLine();
        System.out.println("Enter phone number : ");
        String phNum = scan.nextLine();
        System.out.println("Enter email : ");
        String email = scan.nextLine();

        String query = "Insert into " + Constants.CUSTOMERS_TABLE + "(" + Constants.CUSTOMERS_NAME + "," + Constants.CUSTOMERS_DOB + ","
                + Constants.CUSTOMERS_PH_NUMBER + "," + Constants.CUSTOMERS_EMAIL + ") values('" + name + "','" + dob + "','" + phNum
                + "','" + email + "')";
        DBUtil.executeQuery(query);

    }

    @Override
    public void update() throws SQLException {

        System.out.println("Enter customer ID to update : ");
        String customerId = scan.nextLine();

        String updateString = "";

        System.out.println("Enter only update values");
        System.out.println("Enter customer name : ");
        String name = scan.nextLine();

        if (!name.isEmpty())
            updateString += Constants.CUSTOMERS_NAME + " = '" + name + "'";

        System.out.println("Enter DOB : ");
        String dob = scan.nextLine();

        if (!dob.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.CUSTOMERS_DOB + " = '" + dob + "'";
        }

        System.out.println("Enter phone number : ");
        String phNum = scan.nextLine();

        if (!phNum.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.CUSTOMERS_PH_NUMBER + " = '" + phNum + "'";
        }

        System.out.println("Enter email : ");
        String email = scan.nextLine();

        if (!email.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.CUSTOMERS_EMAIL + " = '" + email + "'";
        }

        String query = "Update " + Constants.CUSTOMERS_TABLE + " set " + updateString + " where " + Constants.CUSTOMERS_ID + " = " + customerId;
        DBUtil.executeQuery(query);

    }

    @Override
    public void delete() throws SQLException {

        System.out.println("Enter customer ID : ");
        String customerId = scan.nextLine();

        String query = "Delete from " + Constants.CUSTOMERS_TABLE + " where " + Constants.CUSTOMERS_ID + " = " + customerId;
        DBUtil.executeQuery(query);

    }

}
