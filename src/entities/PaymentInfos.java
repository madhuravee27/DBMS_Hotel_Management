package entities;

import edu.csc.dbms.Constants;
import edu.csc.dbms.DBTablePrinter;
import edu.csc.dbms.DBUtil;
import edu.csc.dbms.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PaymentInfos implements Entity {
    private static Scanner scan = new Scanner(System.in);

    @Override
    public void retrieve() throws SQLException {

        String query = "select * from " + Constants.PAYMENT_INFOS_TABLE;

        ResultSet result = DBUtil.executeQuery(query);

        DBTablePrinter.printResultSet(result);

    }

    @Override
    public void create() throws SQLException {

        System.out.println("Enter payer SSN : ");
        String ssn = scan.nextLine();
        System.out.println("Enter line 1 billing address : ");
        String address = scan.nextLine();
        System.out.println("Enter City : ");
        String city = scan.nextLine();
        System.out.println("Enter State : ");
        String state = scan.nextLine();
        System.out.println("Enter Country : ");
        String country = scan.nextLine();
        System.out.println("Enter payment method (credit/ hotel credit/ cash): ");
        String paymentMethod = scan.nextLine();
        System.out.println("Enter card number : ");
        String cardNum = scan.nextLine();
        System.out.println("Enter customer Id : ");
        String customerId = scan.nextLine();


        String query = "Insert into " + Constants.PAYMENT_INFOS_TABLE + "(" + Constants.PAYMENT_INFOS_SSN + ","
                + Constants.PAYMENT_INFOS_BILLING_ADDRESS + "," + Constants.PAYMENT_INFOS_CITY + "," + Constants.PAYMENT_INFOS_STATE + ","
                + Constants.PAYMENT_INFOS_COUNTRY + "," + Constants.PAYMENT_INFOS_PAYMENT_METHOD + "," + Constants.PAYMENT_INFOS_CARD_NUM
                + "," + Constants.PAYMENT_INFOS_CUSTOMER_ID + ") values('" + ssn + "','" + address + "','" + city + "','"
                + state + "','" + country + "','" + paymentMethod + "','" + cardNum + "'," + customerId + ")";
        DBUtil.executeQuery(query);

    }

    @Override
    public void update() throws SQLException {

        System.out.println("Enter payment ID to update : ");
        String paymentId = scan.nextLine();

        String updateString = "";

        System.out.println("Enter only update values");
        System.out.println("Enter payer SSN : ");
        String ssn = scan.nextLine();

        if (!ssn.isEmpty())
            updateString += Constants.PAYMENT_INFOS_SSN + " = '" + ssn + "'";

        System.out.println("Enter line 1 billing address : ");
        String address = scan.nextLine();

        if (!address.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.PAYMENT_INFOS_BILLING_ADDRESS + " = '" + address + "'";
        }


        System.out.println("Enter City : ");
        String city = scan.nextLine();

        if (!city.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.PAYMENT_INFOS_CITY + " = '" + city + "'";
        }

        System.out.println("Enter State : ");
        String state = scan.nextLine();

        if (!state.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.PAYMENT_INFOS_STATE + " = '" + state + "'";
        }

        System.out.println("Enter Country : ");
        String country = scan.nextLine();

        if (!country.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.PAYMENT_INFOS_COUNTRY + " = '" + country + "'";
        }

        System.out.println("Enter payment method (credit/ hotel credit/ cash) : ");
        String paymentMethod = scan.nextLine();

        if (!paymentMethod.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.PAYMENT_INFOS_PAYMENT_METHOD + " = '" + paymentMethod + "'";
        }

        System.out.println("Enter card number : ");
        String cardNum = scan.nextLine();

        if (!cardNum.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.PAYMENT_INFOS_CARD_NUM + " = '" + cardNum + "'";
        }

        System.out.println("Enter Customer ID : ");
        String customerId = scan.nextLine();

        if (!customerId.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.PAYMENT_INFOS_CUSTOMER_ID + " = " + customerId + "";
        }

        String query = "Update " + Constants.PAYMENT_INFOS_TABLE + " set " + updateString + " where " + Constants.PAYMENT_INFOS_ID + " = " + paymentId;
        DBUtil.executeQuery(query);

    }

    @Override
    public void delete() throws SQLException {

        System.out.println("Enter payment ID : ");
        String paymentId = scan.nextLine();

        String query = "Delete from " + Constants.PAYMENT_INFOS_TABLE + " where " + Constants.PAYMENT_INFOS_ID + " = " + paymentId;
        DBUtil.executeQuery(query);

    }

}
