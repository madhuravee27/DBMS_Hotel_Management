package entities;

import edu.csc.dbms.Constants;
import edu.csc.dbms.DBTablePrinter;
import edu.csc.dbms.DBUtil;
import edu.csc.dbms.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Staffs implements Entity {
    private static Scanner scan = new Scanner(System.in);

    @Override
    public void retrieve() throws SQLException {
        String query = "select * from " + Constants.STAFFS_TABLE;

        ResultSet result = DBUtil.executeQuery(query);

        DBTablePrinter.printResultSet(result);

    }

    @Override
    public void create() throws SQLException {

        System.out.println("Enter Staff name : ");
        String name = scan.nextLine();
        System.out.println("Enter age : ");
        String age = scan.nextLine();
        System.out.println("Enter jobTitle : ");
        String jobTitle = scan.nextLine();
        System.out.println("Enter phone number : ");
        String phNum = scan.nextLine();
        System.out.println("Enter line 1 address : ");
        String address = scan.nextLine();
        System.out.println("Enter City : ");
        String city = scan.nextLine();
        System.out.println("Enter State : ");
        String state = scan.nextLine();
        System.out.println("Enter Country : ");
        String country = scan.nextLine();
        System.out.println("Enter Hotel Id : ");
        String hotelId = scan.nextLine();


        String query = "Insert into " + Constants.STAFFS_TABLE + "(" + Constants.STAFFS_NAME + "," + Constants.STAFFS_AGE + "," + Constants.STAFFS_JOB_TITLE + ","
                + Constants.STAFFS_PH_NUMBER + "," + Constants.STAFFS_ADDRESS + "," + Constants.STAFFS_CITY + "," + Constants.STAFFS_STATE + ","
                + Constants.STAFFS_COUNTRY + "," + Constants.STAFFS_HOTEL_ID + ") values('" + name + "'," + age + ",'" + jobTitle
                + "','" + phNum + "','" + address + "','" + city + "','" + state + "','" + country + "'," + hotelId
                + ")";
        DBUtil.executeQuery(query);

    }

    @Override
    public void update() throws SQLException {

        System.out.println("Enter Staff ID to update : ");
        String staffId = scan.nextLine();

        String updateString = "";

        System.out.println("Enter only update values");
        System.out.println("Enter staff name : ");
        String name = scan.nextLine();

        if (!name.isEmpty())
            updateString += Constants.STAFFS_NAME + " = '" + name + "'";

        System.out.println("Enter age : ");
        String age = scan.nextLine();

        if (!age.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.STAFFS_AGE + " = " + age;
        }

        System.out.println("Enter Job Title : ");
        String jobTitle = scan.nextLine();

        if (!jobTitle.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.STAFFS_JOB_TITLE + " = '" + jobTitle + "'";
        }

        System.out.println("Enter phone number : ");
        String phNum = scan.nextLine();

        if (!phNum.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.STAFFS_PH_NUMBER + " = '" + phNum + "'";
        }

        System.out.println("Enter line 1 address : ");
        String address = scan.nextLine();

        if (!address.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.STAFFS_ADDRESS + " = '" + address + "'";
        }

        System.out.println("Enter City : ");
        String city = scan.nextLine();

        if (!city.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.STAFFS_CITY + " = '" + city + "'";
        }

        System.out.println("Enter State : ");
        String state = scan.nextLine();

        if (!state.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.STAFFS_STATE + " = '" + state + "'";
        }

        System.out.println("Enter Country : ");
        String country = scan.nextLine();

        if (!country.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.STAFFS_COUNTRY + " = '" + country + "'";
        }

        System.out.println("Enter Hotel ID : ");
        String hotelId = scan.nextLine();

        if (!hotelId.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.STAFFS_HOTEL_ID + " = " + hotelId + "";
        }

        String query = "Update " + Constants.STAFFS_TABLE + " set " + updateString + " where " + Constants.STAFFS_ID + " = " + staffId;
        DBUtil.executeQuery(query);

    }

    @Override
    public void delete() throws SQLException {

        System.out.println("Enter staff ID : ");
        String staffId = scan.nextLine();

        String query = "Delete from " + Constants.STAFFS_TABLE + " where " + Constants.STAFFS_ID + " = " + staffId;
        DBUtil.executeQuery(query);

    }

}
