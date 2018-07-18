package edu.csc.dbms;

import java.sql.*;

import java.util.Scanner;

public class GuestCheckInCheckOut {

	private static Scanner scan = new Scanner(System.in);
	
	//Check in Check out operations
	public void operations() throws SQLException {

        System.out.println("1. Check-In");

        System.out.println();
        System.out.print("Select the operation :");

        int option = Util.getOption();

        switch (option) {
            case 1:
                guestCheckIn();
                break;
            case 2:
                guestCheckOut();
                break;
            default:
                System.out.println("Please Enter a valid option ....");
        }

    }

	private void guestCheckIn() throws SQLException {
		
		//GetConnection
		//RoomSelection
		//PaymentInfo
		//CheckIn
		//Commit/Roll back 
		Connection conn = DBUtil.getConnection();
		try {
			
			Info_Processing.printRoomCategories();
			System.out.println("Enter the category of Room to check availability: ");
	        String category = scan.nextLine();

	        System.out.println("Enter hotelId : ");
	        String hotelId = scan.nextLine();
			
			check_available_rooms(hotelId, category);
			
			System.out.println();
			
			System.out.println("Enter the roomNumber of Room to check availability and book: ");
	        String roomNumber = scan.nextLine();
			
			assign_rooms_by_roomnumber(conn, hotelId, roomNumber);
			
			System.out.println();
			
			enterPaymentInfo(conn);
			
			System.out.println();
			
			checkInDetails(conn, hotelId, roomNumber);
			
			conn.commit();
			conn.close();
			System.out.println("Successfully Checked-In");
			
		}catch (SQLException se) {
			conn.rollback();
            System.out.println("Transaction rollback " + se.getMessage());
        } catch (Exception e) {
        	conn.rollback();
            System.out.println("Transaction rollback " + e.getLocalizedMessage());
        }
	}
	
	
	private void guestCheckOut() throws SQLException {
		
		//GetConnection
		//ReleaseRoom
		//GenerateTotal
		//GenerateItemizedRecipt
		//UpdateTotal
		//Commit/Roll back
		
		Connection conn = DBUtil.getConnection();
		try {
			System.out.println("Enter hotel ID : ");
	        String hotelId = scan.nextLine();
	        
	        System.out.println("Enter checkIn ID : ");
	        String checkinId = scan.nextLine();
			
	        validateCheckinID(conn, checkinId);
	        
			System.out.println("Enter room number : ");
	        String roomNumber = scan.nextLine();
	        
	        release_room(conn, hotelId, roomNumber);
	        
	        System.out.println();
	        
	        getTotalPrice(checkinId);
	        
	        System.out.println();
	        
	        getItemizedReceipt(checkinId);
	        
	        updateTotalPrice(conn, checkinId);
	        
	        conn.commit();
	        conn.close();
			System.out.println("Successfully Checked-Out");
			
		}catch (SQLException se) {
			conn.rollback();
            System.out.println(se.getMessage());
        } catch (Exception e) {
        	conn.rollback();
            System.out.println("Exception " + e.getLocalizedMessage());
        }
		
	}
	
	public void check_available_rooms(String hotelId, String category) throws SQLException {
        
        String query = "Select " + Constants.ROOMS_ROOMNUMBER + " FROM " + Constants.ROOMS_TABLE + " where " + Constants.ROOMS_HOTELID + " = " + hotelId + " and " + Constants.ROOMS_CATEGORY + " = '" + category + "' and " + Constants.ROOMS_AVAILABILITY + " = " + "1";
        ResultSet result = DBUtil.executeQuery(query);
        System.out.println("Available Rooms");
        DBTablePrinter.printResultSet(result);
    }
	
	public void assign_rooms_by_roomnumber(Connection conn, String hotelId, String roomNumber) throws SQLException {

		String query = "SELECT " + Constants.ROOMS_AVAILABILITY + " FROM " + Constants.ROOMS_TABLE + " WHERE "
				+ Constants.ROOMS_HOTELID + " = " + hotelId + " AND " + Constants.ROOMS_ROOMNUMBER + " = " + roomNumber;
		
		Statement stm = conn.createStatement();
		ResultSet result = stm.executeQuery(query);
		
		if(result.next() && result.getInt(Constants.ROOMS_AVAILABILITY) == 0) {
			throw new SQLException("Room is unavailable. Kindly check-In again.");
		}

		query = "Update " + Constants.ROOMS_TABLE + " set " + Constants.ROOMS_AVAILABILITY + " =  0 " + " where "
				+ Constants.ROOMS_ROOMNUMBER + " = " + roomNumber + " and " + Constants.ROOMS_HOTELID + " = " + hotelId;
		
		Statement stm1 = conn.createStatement();
        stm1.executeQuery(query);
        
    }
	
	private void checkInDetails(Connection conn, String hotelId, String roomNumber) throws SQLException {
		
		System.out.println("Enter Check-In Details : ");
		
		System.out.println("Enter startDate (YYYY-MM-DD) : ");
        String startDate = scan.nextLine();
        System.out.println("Enter endDate (YYYY-MM-DD) :  ");
        String endDate = scan.nextLine();
        System.out.println("Enter checkinTime (HH:MM): ");
        String checkinTime = scan.nextLine();
        System.out.println("Enter numberOfGuests : ");
        String numberOfGuests = scan.nextLine();
        System.out.println("Enter customerId : ");
        String customerId = scan.nextLine();
        System.out.println("Enter paymentId : ");
        String paymentId = scan.nextLine();

		String query = "Insert into " + Constants.CHECK_INS_TABLE + "(" + Constants.CHECK_INS_STARTDATE + ","
				+ Constants.CHECK_INS_ENDDATE + "," + Constants.CHECK_INS_CHECKINTIME + ","
			    + Constants.CHECK_INS_NUMBEROFGUESTS + ","
				+ Constants.CHECK_INS_CUSTOMERID + "," + Constants.CHECK_INS_HOTELID + ","
				+ Constants.CHECK_INS_ROOMNUMBER + "," + Constants.CHECK_INS_PAYMENTID + ") values('" + startDate
				+ "','" + endDate + "','" + checkinTime + "','" + numberOfGuests + "','"
				+ customerId + "','" + hotelId + "','" + roomNumber + "','" + paymentId + "')";
        
        Statement stm = conn.createStatement();
        stm.executeQuery(query);
        
        query = "select * from " + Constants.CHECK_INS_TABLE + " order by checkinId desc limit 1";

        ResultSet result = stm.executeQuery(query);
        DBTablePrinter.printResultSet(result);
	}
	
	private void enterPaymentInfo(Connection conn) throws SQLException{
		
		System.out.println("Enter Payment Informations : ");
		
		System.out.println("Enter a valid payment ID :");
		String paymentId = scan.nextLine();
		
		String query1 = "SELECT * FROM " + Constants.PAYMENT_INFOS_TABLE + " WHERE " + Constants.PAYMENT_INFOS_ID + " = " + paymentId;
		ResultSet result1 = DBUtil.executeQuery(query1);
		DBTablePrinter.printResultSet(result1);
		
		query1 = "SELECT * FROM " + Constants.PAYMENT_INFOS_TABLE + " WHERE " + Constants.PAYMENT_INFOS_ID + " = " + paymentId;
		result1 = DBUtil.executeQuery(query1);
		int temp = 0;
		if(result1.next()) {
			temp = result1.getInt(Constants.PAYMENT_INFOS_ID);
		}
		
		if(temp != 0) {
			return;
		}else {
			System.out.println("Invaild payment ID, add new payment information.");
		}
		
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
        System.out.println("Enter payment method : ");
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
        
        Statement stm = conn.createStatement();
        stm.executeQuery(query);
        
        query = "SELECT * FROM " + Constants.PAYMENT_INFOS_TABLE + " order by paymentId desc limit 1";
        
        stm = conn.createStatement();
        ResultSet result = stm.executeQuery(query);
        DBTablePrinter.printResultSet(result);

	}

	private void validateCheckinID(Connection conn, String checkinId) throws SQLException {
		
		String query = "SELECT " + Constants.CHECK_INS_CHECKINID + " FROM " + Constants.CHECK_INS_TABLE + " WHERE " + Constants.CHECK_INS_CHECKINID + " = " + checkinId + " AND " + Constants.CHECK_INS_TOTAL + " is NULL" ;
		
		Statement stm = conn.createStatement();
		ResultSet result = stm.executeQuery(query);
		
		if(!result.next()) {
			throw new SQLException("Enter a valid check-In ID");
		}
		
	}
	
	public void release_room(Connection conn, String hotelId, String roomNumber) throws SQLException {

        String query = "Update " + Constants.ROOMS_TABLE + " set " + Constants.ROOMS_AVAILABILITY + " = 1  where " + Constants.ROOMS_ROOMNUMBER + " = " + roomNumber + " AND " + Constants.ROOMS_HOTELID + " = " + hotelId;
        DBUtil.executeQuery(query);
        query = "select * from " + Constants.ROOMS_TABLE;

        Statement stm = conn.createStatement();
        stm.executeQuery(query);
        
        System.out.println("Room relased");
    }
	
	private void getTotalPrice(String checkinId) throws SQLException {

        String query = "SELECT (RP." + Constants.ROOM_PRICES_PRICE + "* DATEDIFF(C." + Constants.CHECK_INS_ENDDATE + " , " + Constants.CHECK_INS_STARTDATE + ") + SUM(B." + Constants.BUYS_PRICE + ")) * (CASE WHEN P." + Constants.PAYMENT_INFOS_PAYMENT_METHOD + " <> '" +
                Constants.PAY_METHOD_HOTEL_CREDIT + "' THEN 1 ELSE 0.95 end) AS " + Constants.TOTAL_PRICE + " FROM " + Constants.ROOMS_TABLE + " R, " + Constants.ROOM_PRICES_TABLE + " RP, " + Constants.CHECK_INS_TABLE + " C, " + Constants.BUYS_TABLE + " B, " +
                Constants.PAYMENT_INFOS_TABLE + " P WHERE C." + Constants.CHECK_INS_ROOMNUMBER + " = R." + Constants.ROOMS_ROOMNUMBER + " AND C." + Constants.CHECK_INS_HOTELID + " = R." + Constants.HOTELS_ID + " AND R." + Constants.ROOMS_MAXOCCUPANCY +
                " = RP." + Constants.ROOM_PRICES_MAXOCCUPANCY + " AND R." + Constants.ROOMS_CATEGORY + " = RP." + Constants.ROOM_PRICES_CATEGORY + " AND C." + Constants.CHECK_INS_CHECKINID + " = " + checkinId + " AND B." + Constants.BUYS_CHECKINID + " = C." +
                Constants.CHECK_INS_CHECKINID + " AND P." + Constants.PAYMENT_INFOS_ID + " = C." + Constants.CHECK_INS_PAYMENTID;

        ResultSet result = DBUtil.executeQuery(query);

        if (result.next()) {
            float totalPrice = result.getFloat(Constants.TOTAL_PRICE);

            System.out.println(Constants.TOTAL_PRICE);
            System.out.println(totalPrice + "$");
        }
    }

    private void getItemizedReceipt(String checkinId) throws SQLException {

        String query = "SELECT S." + Constants.SERVICES_NAME + " AS " + Constants.DESCRIPTION + " , S." + Constants.SERVICES_BASE_PRICE + " , cast( B." + Constants.BUYS_PRICE + " / S." + Constants.SERVICES_BASE_PRICE +
                " AS INTEGER) AS " + Constants.NUMBER + " , B." + Constants.BUYS_PRICE + " FROM " + Constants.CHECK_INS_TABLE + " C, " + Constants.BUYS_TABLE + " B, " + Constants.SERVICES_TABLE + " S WHERE C." +
                Constants.CHECK_INS_CHECKINID + " = B." + Constants.BUYS_CHECKINID + " AND B." + Constants.BUYS_SERVICEID + " = S." + Constants.SERVICES_ID + " AND C." + Constants.CHECK_INS_CHECKINID + " = " + checkinId +
                " UNION " +
                "SELECT '" + Constants.DESCRIPTION_VALUE + "' AS " + Constants.DESCRIPTION + " , RP." + Constants.ROOM_PRICES_PRICE + " AS " + Constants.BASE_PRICE + " , DATEDIFF(C." + Constants.CHECK_INS_ENDDATE + " , C." +
                Constants.CHECK_INS_STARTDATE + ") AS " + Constants.NUMBER + " , RP." + Constants.ROOM_PRICES_PRICE + "* DATEDIFF(C." + Constants.CHECK_INS_ENDDATE + " , C." + Constants.CHECK_INS_STARTDATE + ") AS " +
                Constants.PRICE + " FROM " + Constants.ROOMS_TABLE + " R, " + Constants.ROOM_PRICES_TABLE + " RP, " + Constants.CHECK_INS_TABLE + " C WHERE C." + Constants.CHECK_INS_ROOMNUMBER + " = R." + Constants.ROOMS_ROOMNUMBER +
                " AND C." + Constants.CHECK_INS_HOTELID + " = R." + Constants.HOTELS_ID + " AND R." + Constants.ROOMS_MAXOCCUPANCY + " = RP." + Constants.ROOM_PRICES_MAXOCCUPANCY + " AND R." + Constants.ROOMS_CATEGORY + " = RP." +
                Constants.ROOM_PRICES_CATEGORY + " AND C." + Constants.CHECK_INS_CHECKINID + " = " + checkinId;

        ResultSet result = DBUtil.executeQuery(query);

        System.out.println("Itemized receipt");
        DBTablePrinter.printResultSet(result);
    }
    
    private void updateTotalPrice(Connection conn, String checkinId) throws SQLException {

        String query = "UPDATE " + Constants.CHECK_INS_TABLE + " c, (" + "SELECT (RP." + Constants.ROOM_PRICES_PRICE + "* DATEDIFF(C." + Constants.CHECK_INS_ENDDATE + " , " + Constants.CHECK_INS_STARTDATE + ") + SUM(B." + Constants.BUYS_PRICE + ")) * (CASE WHEN P." + Constants.PAYMENT_INFOS_PAYMENT_METHOD + " <> '" +
                Constants.PAY_METHOD_HOTEL_CREDIT + "' THEN 1 ELSE 0.95 end) AS " + Constants.TOTAL_PRICE + " FROM " + Constants.ROOMS_TABLE + " R, " + Constants.ROOM_PRICES_TABLE + " RP, " + Constants.CHECK_INS_TABLE + " C, " + Constants.BUYS_TABLE + " B, " +
                Constants.PAYMENT_INFOS_TABLE + " P WHERE C." + Constants.CHECK_INS_ROOMNUMBER + " = R." + Constants.ROOMS_ROOMNUMBER + " AND C." + Constants.CHECK_INS_HOTELID + " = R." + Constants.HOTELS_ID + " AND R." + Constants.ROOMS_MAXOCCUPANCY +
                " = RP." + Constants.ROOM_PRICES_MAXOCCUPANCY + " AND R." + Constants.ROOMS_CATEGORY + " = RP." + Constants.ROOM_PRICES_CATEGORY + " AND C." + Constants.CHECK_INS_CHECKINID + " = " + checkinId + " AND B." + Constants.BUYS_CHECKINID + " = C." +
                Constants.CHECK_INS_CHECKINID + " AND P." + Constants.PAYMENT_INFOS_ID + " = C." + Constants.CHECK_INS_PAYMENTID + " ) i SET c." + Constants.CHECK_INS_TOTAL + " = i." + Constants.TOTAL_PRICE + " WHERE c." + Constants.CHECK_INS_CHECKINID + " = " + checkinId;

        Statement stm = conn.createStatement();
        stm.executeQuery(query);
    }
	
}
