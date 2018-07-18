package entities;

import edu.csc.dbms.Constants;
import edu.csc.dbms.DBTablePrinter;
import edu.csc.dbms.DBUtil;
import edu.csc.dbms.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Buys implements Entity {
    private static Scanner scan = new Scanner(System.in);

    @Override
    public void retrieve() throws SQLException {

        String query = "select * from " + Constants.BUYS_TABLE;

        ResultSet result = DBUtil.executeQuery(query);

        DBTablePrinter.printResultSet(result);

    }

    @Override
    public void create() throws SQLException {

        System.out.println("Enter serviceId: ");
        String serviceId = scan.nextLine();
        System.out.println("Enter checkinId: ");
        String checkinId = scan.nextLine();
        System.out.println("Enter price : ");
        String price = scan.nextLine();

        String query = "Insert into " + Constants.BUYS_TABLE + "(" + Constants.BUYS_SERVICEID + "," + Constants.BUYS_CHECKINID + ","
                + Constants.BUYS_PRICE + ") values('" + serviceId + "','" + checkinId + "','" + price + "')";
        DBUtil.executeQuery(query);

    }

    @Override
    public void update() throws SQLException {

        System.out.println("Enter serviceId to update : ");
        String serviceId = scan.nextLine();
        System.out.println("Enter checkinId : ");
        String checkinId = scan.nextLine();

        String updateString = "";

        System.out.println("Enter only update values");
        System.out.println("Enter price: ");
        String price = scan.nextLine();

        if (!price.isEmpty())
            updateString += Constants.BUYS_PRICE + " = '" + price + "'";

        String query = "Update " + Constants.BUYS_TABLE + " set " + updateString + " where " + Constants.BUYS_SERVICEID + " = " + serviceId + " and " + Constants.BUYS_CHECKINID + " = " + checkinId;
        DBUtil.executeQuery(query);

    }

    @Override
    public void delete() throws SQLException {

        System.out.println("Enter serviceId : ");
        String serviceId = scan.nextLine();
        System.out.println("Enter checkinId : ");
        String checkinId = scan.nextLine();

        String query = "Delete from " + Constants.BUYS_TABLE + " where " + Constants.BUYS_SERVICEID + " = " + serviceId + " and " + Constants.BUYS_CHECKINID + " = " + checkinId;
        DBUtil.executeQuery(query);

    }

}
