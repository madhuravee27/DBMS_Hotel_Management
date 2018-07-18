package entities;

import edu.csc.dbms.Constants;
import edu.csc.dbms.DBTablePrinter;
import edu.csc.dbms.DBUtil;
import edu.csc.dbms.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Hotels implements Entity {
    private static Scanner scan = new Scanner(System.in);

    @Override
    public void retrieve() throws SQLException {
        String query = "select * from " + Constants.HOTELS_TABLE;

        ResultSet result = DBUtil.executeQuery(query);

        DBTablePrinter.printResultSet(result);

    }

    @Override
    public void create() throws SQLException {

        System.out.println("Enter hotel name : ");
        String name = scan.nextLine();
        System.out.println("Enter line 1 address : ");
        String address = scan.nextLine();
        System.out.println("Enter City : ");
        String city = scan.nextLine();
        System.out.println("Enter State : ");
        String state = scan.nextLine();
        System.out.println("Enter Country : ");
        String country = scan.nextLine();
        System.out.println("Enter phone number : ");
        String phNum = scan.nextLine();


        String query = "Insert into " + Constants.HOTELS_TABLE + "(" + Constants.HOTELS_NAME + "," + Constants.HOTELS_ADDRESS + "," + Constants.HOTELS_CITY + ","
                + Constants.HOTELS_STATE + "," + Constants.HOTELS_COUNTRY + "," + Constants.HOTELS_PH_NUMBER + ") values('" + name + "','"
                + address + "','" + city + "','" + state + "','" + country + "'," + phNum + ")";
        DBUtil.executeQuery(query);

    }

    @Override
    public void update() throws SQLException {

        System.out.println("Enter hotel ID to update : ");
        String hotelId = scan.nextLine();

        String updateString = "";

        System.out.println("Enter only update values");
        System.out.println("Enter hotel name : ");
        String name = scan.nextLine();

        if (!name.isEmpty())
            updateString += Constants.HOTELS_NAME + " = '" + name + "'";

        System.out.println("Enter line 1 address : ");
        String address = scan.nextLine();

        if (!address.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.HOTELS_ADDRESS + " = '" + address + "'";
        }

        System.out.println("Enter City : ");
        String city = scan.nextLine();

        if (!city.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.HOTELS_CITY + " = '" + city + "'";
        }

        System.out.println("Enter State : ");
        String state = scan.nextLine();

        if (!state.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.HOTELS_STATE + " = '" + state + "'";
        }

        System.out.println("Enter Country : ");
        String country = scan.nextLine();

        if (!country.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.HOTELS_COUNTRY + " = '" + country + "'";
        }

        System.out.println("Enter phone number : ");
        String phNum = scan.nextLine();

        if (!phNum.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.HOTELS_PH_NUMBER + " = '" + phNum + "'";
        }

        System.out.println("Enter Manager ID : ");
        String managerId = scan.nextLine();

        if (!country.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.HOTELS_MANAGER_ID + " = " + managerId + "";
        }

        String query = "Update " + Constants.HOTELS_TABLE + " set " + updateString + " where " + Constants.HOTELS_ID + " = " + hotelId;
        DBUtil.executeQuery(query);

    }

    @Override
    public void delete() throws SQLException {

        System.out.println("Enter hotel ID : ");
        String hotelId = scan.nextLine();

        String query = "Delete from " + Constants.HOTELS_TABLE + " where " + Constants.HOTELS_ID + " = " + hotelId;
        DBUtil.executeQuery(query);

    }
}
