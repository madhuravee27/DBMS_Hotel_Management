package edu.csc.dbms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Reports {
    private static Scanner scan = new Scanner(System.in);

    //Task 4 options
    public void operations() throws SQLException {
        System.out.println("1. Staff grouped by their role");
        System.out.println("2. Staff ordered by their role");
        System.out.println("3. Staff served during the customer’s stay");
        System.out.println("4. Revenue earned by all hotels");
        System.out.println("5. Revenue earned by a hotel");
        System.out.println("6. Occupancy by hotel");
        System.out.println("7. Occupancy by city");
        System.out.println("8. Occupancy by category");
        System.out.println("9. Occupancy for a Date Range");
        System.out.println("10. Get Occupancy");

        System.out.println();
        System.out.print("Select the Report to be generated :");
        System.out.println();

        int option = Util.getOption();
        switch (option) {
            case 1:
                staffRoles();
                break;
            case 2:
                staffs();
                break;
            case 3:
                staffsForCustomerStay();
                break;
            case 4:
                getRevenue();
                break;
            case 5:
                getRevenueByHotel();
                break;
            case 6:
                getOccupancyBy("Hotel");
                break;
            case 7:
                getOccupancyBy("City");
                break;
            case 8:
                getOccupancyBy("Category");
                break;
            case 9:
                getOccupancyByDateRange();
                break;
            case 10:
                getOccupancies();
                break;
            default:
                System.out.println("Please Enter a valid option ....");
        }
    }

    //Report staff based on role
    private void staffRoles() throws SQLException {
        String query = "SELECT jobTitle AS Role, COUNT(*) AS No_of_Employees FROM " + Constants.STAFFS_TABLE + " GROUP BY jobTitle";
        ResultSet result = DBUtil.executeQuery(query);
        DBTablePrinter.printResultSet(result);
    }

    //Report staff based on jobTitle
    private void staffs() throws SQLException {
        String query = "SELECT * FROM " + Constants.STAFFS_TABLE + " ORDER BY jobTitle";
        ResultSet result = DBUtil.executeQuery(query);
        DBTablePrinter.printResultSet(result);
    }

    //Report all the staff who served the customer during their stay
    private void staffsForCustomerStay() throws SQLException {
        System.out.println("Enter checkin ID: ");
        String checkinId = scan.nextLine();
        String query = "SELECT staffId, name FROM " + Constants.SERVES_TABLE + " NATURAL JOIN " + Constants.STAFFS_TABLE + " WHERE checkinId =" + checkinId;
        ResultSet result = DBUtil.executeQuery(query);
        DBTablePrinter.printResultSet(result);
    }

    //Report revenue for the hotels for a given date range
    private void getRevenue() throws SQLException {
        System.out.println("Enter startDate (YYYY-MM-DD)  : ");
        String startDate = scan.nextLine();
        System.out.println("Enter endDate (YYYY-MM-DD) :  ");
        String endDate = scan.nextLine();

        String query = "SELECT h.hotelId, h.Name, (CASE WHEN temp.hotelId=h.hotelId THEN temp.Revenue ELSE 0 END) as Revenue " +
                "FROM " + Constants.HOTELS_TABLE + " h, (SELECT hotelId, sum(total) AS Revenue FROM " +
                Constants.CHECK_INS_TABLE + " WHERE endDate > '" + startDate + "' AND endDate < '" + endDate + "') temp";
        ResultSet result = DBUtil.executeQuery(query);
        DBTablePrinter.printResultSet(result);
    }

  //Report revenue for the hotels for a given date range and hotel ID
    private void getRevenueByHotel() throws SQLException {
        System.out.println("Enter hotel ID : ");
        String hotelId = scan.nextLine();
        System.out.println("Enter startDate (YYYY-MM-DD) : ");
        String startDate = scan.nextLine();
        System.out.println("Enter endDate (YYYY-MM-DD) :  ");
        String endDate = scan.nextLine();

        String query = "SELECT hotelId, name, sum(total) AS Revenue FROM " + Constants.CHECK_INS_TABLE + " NATURAL JOIN " + Constants.HOTELS_TABLE + " WHERE endDate > '" + startDate + "' AND endDate < '" + endDate + "' AND hotelId=" + hotelId;
        ResultSet result = DBUtil.executeQuery(query);
        DBTablePrinter.printResultSet(result);
    }

  //Report hotel occupancy based on selected criteria
    private void getOccupancyBy(String criteria) throws SQLException {
        String query;
        switch (criteria) {
            case "Hotel":
                query = "SELECT Name AS " + criteria +
                        ",(COUNT(CASE WHEN availability = false THEN 1 ELSE null END)/COUNT(*))*100 AS OccupancyPercentage, " +
                        "COUNT(CASE WHEN availability = false THEN 1 ELSE null END) AS TotalOccupancy FROM "
                        + Constants.ROOMS_TABLE + " NATURAL JOIN " + Constants.HOTELS_TABLE + " GROUP BY hotelId";
                break;
            case "City":
                query = "SELECT city AS " + criteria +
                        ",(COUNT(CASE WHEN availability = false THEN 1 ELSE null END)/COUNT(*))*100 AS OccupancyPercentage, " +
                        "COUNT(CASE WHEN availability = false THEN 1 ELSE null END) AS TotalOccupancy FROM "
                        + Constants.ROOMS_TABLE + " NATURAL JOIN " + Constants.HOTELS_TABLE + " GROUP BY city";
                break;
            case "Category":
                query = "SELECT category AS " + criteria +
                        ",(COUNT(CASE WHEN availability = false THEN 1 ELSE null END)/COUNT(*))*100 AS OccupancyPercentage, " +
                        "COUNT(CASE WHEN availability = false THEN 1 ELSE null END) AS TotalOccupancy FROM "
                        + Constants.ROOMS_TABLE + " NATURAL JOIN " + Constants.HOTELS_TABLE + " GROUP BY category";
                break;
            default:
                return;
        }
        ResultSet result = DBUtil.executeQuery(query);
        DBTablePrinter.printResultSet(result);
    }

    //Report hotel occupancy based on date range
    private void getOccupancyByDateRange() throws SQLException {
        System.out.println("Enter startDate (YYYY-MM-DD)  : ");
        String startDate = scan.nextLine();
        System.out.println("Enter endDate (YYYY-MM-DD) : ");
        String endDate = scan.nextLine();

        String query = "SELECT (occupied.counter/room.counter)*100 as OccupancyPercentage, occupied.counter as TotalOccupancy FROM " +
                "(SELECT count(*) as counter FROM Rooms r, Hotels h, Checkins c " +
                "WHERE h.hotelId=r.hotelId and r.roomNumber = c.roomNumber and r.hotelId = c.hotelId and " +
                "((endDate BETWEEN '" + startDate + "' AND '" + endDate + "') or " +
                "(startDate BETWEEN '" + startDate + "' AND '" + endDate + "') or " +
                "(endDate > '" + endDate + "' AND startDate < '" + startDate + "'))) occupied, " +
                "(SELECT count(*) as counter FROM Rooms) room";

        ResultSet result = DBUtil.executeQuery(query);
        DBTablePrinter.printResultSet(result);
    }

    //Report occupancies
    private void getOccupancies() throws SQLException {
        String query;
        query = "SELECT COALESCE((COUNT(CASE WHEN availability = false THEN 1 ELSE null END)/COUNT(*))*100, 0) as OccupancyPercentage, " +
                "COUNT(CASE WHEN availability = false THEN 1 ELSE null END) AS TotalOccupancy FROM Rooms NATURAL JOIN Hotels";
        String whereClause = "";
        String message = "Occupancy for ";

        System.out.println("Enter hotel ID: ");
        String hotelId = scan.nextLine();
        if (!hotelId.isEmpty()) {
            whereClause += (!whereClause.isEmpty()) ? " AND " : " WHERE ";
            whereClause += Constants.HOTELS_ID + " = " + hotelId;
            message += Constants.HOTELS_ID + " " + hotelId + " ";
        }

        if(hotelId.isEmpty()){
            System.out.println("Enter City : ");
            String city = scan.nextLine();

            if (!city.isEmpty()) {
                whereClause += (!whereClause.isEmpty()) ? " AND " : " WHERE ";
                whereClause += Constants.HOTELS_CITY + " = '" + city + "'";
                message += Constants.HOTELS_CITY + " " + city + " ";
            }
        }

        Info_Processing.printRoomCategories();
        System.out.println("Enter Category : ");
        String category = scan.nextLine();

        if (!category.isEmpty()) {
            whereClause += (!whereClause.isEmpty()) ? " AND " : " WHERE ";
            whereClause += Constants.ROOMS_CATEGORY + " = '" + category + "'";
            message += Constants.ROOMS_CATEGORY + " " + category + " ";
        }
        query += whereClause;

        if (!message.equals("Occupancy for ")) {
            System.out.println(message);
        } else {
            System.out.println("Occupancy for all the hotels");
        }
        ResultSet result = DBUtil.executeQuery(query);
        DBTablePrinter.printResultSet(result);
    }
}
