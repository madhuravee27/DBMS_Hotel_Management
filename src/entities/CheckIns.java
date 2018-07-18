package entities;

import edu.csc.dbms.Constants;
import edu.csc.dbms.DBTablePrinter;
import edu.csc.dbms.DBUtil;
import edu.csc.dbms.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CheckIns implements Entity {
    private static Scanner scan = new Scanner(System.in);

    @Override
    public void retrieve() throws SQLException {

        String query = "select * from " + Constants.CHECK_INS_TABLE;

        ResultSet result = DBUtil.executeQuery(query);

        DBTablePrinter.printResultSet(result);

    }

    @Override
    public void create() throws SQLException {

        System.out.println("Enter startDate (YYYY-MM-DD) : ");
        String startDate = scan.nextLine();
        System.out.println("Enter endDate (YYYY-MM-DD) : ");
        String endDate = scan.nextLine();
        System.out.println("Enter checkinTime (HH:MM) : ");
        String checkinTime = scan.nextLine();
        System.out.println("Enter numberOfGuests : ");
        String numberOfGuests = scan.nextLine();
        System.out.println("Enter customerId : ");
        String customerId = scan.nextLine();
        System.out.println("Enter hotelId : ");
        String hotelId = scan.nextLine();
        System.out.println("Enter roomNumber : ");
        String roomNumber = scan.nextLine();
        System.out.println("Enter paymentId : ");
        String paymentId = scan.nextLine();


        String query = "Insert into " + Constants.CHECK_INS_TABLE + "(" + Constants.CHECK_INS_STARTDATE + ","
                + Constants.CHECK_INS_ENDDATE + "," + Constants.CHECK_INS_CHECKINTIME + "," + Constants.CHECK_INS_NUMBEROFGUESTS
                + "," + Constants.CHECK_INS_CUSTOMERID + "," + Constants.CHECK_INS_HOTELID + "," + Constants.CHECK_INS_ROOMNUMBER +
                "," + Constants.CHECK_INS_PAYMENTID + ") values('" + startDate + "','" + endDate + "','" + checkinTime + "',"
                + numberOfGuests + ",'" + customerId + "','" + hotelId + "','" + roomNumber + "','" + paymentId + "')";
        DBUtil.executeQuery(query);

    }

    @Override
    public void update() throws SQLException {

        System.out.println("Enter checkin ID to update : ");
        String checkinId = scan.nextLine();

        String updateString = "";

        System.out.println("Enter only update values");
        System.out.println("Enter startDate (YYYY-MM-DD) : ");
        String startDate = scan.nextLine();

        if (!startDate.isEmpty())
            updateString += Constants.CHECK_INS_STARTDATE + " = '" + startDate + "'";

        System.out.println("Enter endDate (YYYY-MM-DD) : ");
        String endDate = scan.nextLine();

        if (!endDate.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.CHECK_INS_ENDDATE + " = '" + endDate + "'";
        }


        System.out.println("Enter checkinTime (HH:MM): ");
        String checkinTime = scan.nextLine();

        if (!checkinTime.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.CHECK_INS_CHECKINTIME + " = '" + checkinTime + "'";
        }

        System.out.println("Enter checkoutTime : ");
        String checkoutTime = scan.nextLine();

        if (!checkoutTime.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.CHECK_INS_CHECKOUTTIME + " = '" + checkoutTime + "'";
        }

        System.out.println("Enter numberOfGuests : ");
        String numberOfGuests = scan.nextLine();

        if (!numberOfGuests.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.CHECK_INS_NUMBEROFGUESTS + " = '" + numberOfGuests + "'";
        }

        System.out.println("Enter total : ");
        String total = scan.nextLine();

        if (!total.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.CHECK_INS_TOTAL + " = '" + total + "'";
        }

        System.out.println("Enter customerId : ");
        String customerId = scan.nextLine();

        if (!customerId.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.CHECK_INS_CUSTOMERID + " = '" + customerId + "'";
        }

        System.out.println("Enter hotelId : ");
        String hotelId = scan.nextLine();

        if (!hotelId.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.CHECK_INS_HOTELID + " = " + hotelId + "";
        }

        System.out.println("Enter roomNumber : ");
        String roomNumber = scan.nextLine();

        if (!roomNumber.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.CHECK_INS_ROOMNUMBER + " = '" + roomNumber + "'";
        }

        System.out.println("Enter paymentId : ");
        String paymentId = scan.nextLine();

        if (!paymentId.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.CHECK_INS_PAYMENTID + " = " + paymentId + "";
        }

        String query = "Update " + Constants.CHECK_INS_TABLE + " set " + updateString + " where " + Constants.CHECK_INS_CHECKINID + " = " + checkinId;
        DBUtil.executeQuery(query);

    }

    @Override
    public void delete() throws SQLException {

        System.out.println("Enter checkin Id : ");
        String checkinId = scan.nextLine();

        String query = "Delete from " + Constants.CHECK_INS_TABLE + " where " + Constants.CHECK_INS_CHECKINID + " = " + checkinId;
        DBUtil.executeQuery(query);

    }

}
