package entities;

import edu.csc.dbms.Constants;
import edu.csc.dbms.DBTablePrinter;
import edu.csc.dbms.DBUtil;
import edu.csc.dbms.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Serves implements Entity {
    private static Scanner scan = new Scanner(System.in);

    @Override
    public void retrieve() throws SQLException {

        String query = "select * from " + Constants.SERVES_TABLE;

        ResultSet result = DBUtil.executeQuery(query);

        DBTablePrinter.printResultSet(result);

    }

    @Override
    public void create() throws SQLException {

        System.out.println("Enter staffId: ");
        String staffId = scan.nextLine();
        System.out.println("Enter checkinId: ");
        String checkinId = scan.nextLine();

        String query = "Insert into " + Constants.SERVES_TABLE + "(" + Constants.SERVES_STAFFID + "," + Constants.SERVES_CHECKINID +
                ") values('" + staffId + "','" + checkinId + "')";
        DBUtil.executeQuery(query);

    }

    @Override
    public void update() throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete() throws SQLException {

        System.out.println("Enter staffId : ");
        String staffId = scan.nextLine();
        System.out.println("Enter checkinId : ");
        String checkinId = scan.nextLine();

        String query = "Delete from " + Constants.SERVES_TABLE + " where " + Constants.SERVES_STAFFID + " = " + staffId + " and " + Constants.SERVES_CHECKINID + " = " + checkinId;
        DBUtil.executeQuery(query);

    }

}
