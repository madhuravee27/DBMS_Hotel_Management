package entities;

import edu.csc.dbms.Constants;
import edu.csc.dbms.DBTablePrinter;
import edu.csc.dbms.DBUtil;
import edu.csc.dbms.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Services implements Entity {
    private static Scanner scan = new Scanner(System.in);

    @Override
    public void retrieve() throws SQLException {

        String query = "select * from " + Constants.SERVICES_TABLE;

        ResultSet result = DBUtil.executeQuery(query);

        DBTablePrinter.printResultSet(result);

    }

    @Override
    public void create() throws SQLException {

        System.out.println("Enter Service name : ");
        String name = scan.nextLine();
        System.out.println("Enter base price : ");
        String basePrice = scan.nextLine();

        String query = "Insert into " + Constants.SERVICES_TABLE + "(" + Constants.SERVICES_NAME + "," + Constants.SERVICES_BASE_PRICE + ") values('" + name + "'," + basePrice + ")";
        DBUtil.executeQuery(query);

    }

    @Override
    public void update() throws SQLException {

        System.out.println("Enter service ID to update : ");
        String serviceId = scan.nextLine();

        String updateString = "";

        System.out.println("Enter only update values");
        System.out.println("Enter service name : ");
        String name = scan.nextLine();

        if (!name.isEmpty())
            updateString += Constants.SERVICES_NAME + " = '" + name + "'";

        System.out.println("Enter base price : ");
        String basePrice = scan.nextLine();

        if (!basePrice.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.SERVICES_BASE_PRICE + " = " + basePrice;
        }

        String query = "Update " + Constants.SERVICES_TABLE + " set " + updateString + " where " + Constants.SERVICES_ID + " = " + serviceId;
        DBUtil.executeQuery(query);

    }

    @Override
    public void delete() throws SQLException {

        System.out.println("Enter service ID : ");
        String serviceId = scan.nextLine();

        String query = "Delete from " + Constants.SERVICES_TABLE + " where " + Constants.SERVICES_ID + " = " + serviceId;
        DBUtil.executeQuery(query);

    }

}
