package edu.csc.dbms;

import java.sql.SQLException;

import entities.*;

public class Entities {
	//Menu options for CRUD operations on all entities
    public static void crudOperations() throws SQLException {

        System.out.println("1. Hotels");
        System.out.println("2. Staffs");
        System.out.println("3. Customers");
        System.out.println("4. CheckIns");
        System.out.println("5. PaymentInfos");
        System.out.println("6. Rooms");
        System.out.println("7. RoomPrices");
        System.out.println("8. Services");
        System.out.println("9. Buys");
        System.out.println("10. Serves");

        System.out.println();
        System.out.print("Select the table :");
        System.out.println();

        int option = Util.getOption();
        Entity entity;

        switch (option) {

            case 1:
                entity = new Hotels();
                break;
            case 2:
                entity = new Staffs();
                break;
            case 3:
                entity = new Customers();
                break;
            case 4:
                entity = new CheckIns();
                break;
            case 5:
                entity = new PaymentInfos();
                break;
            case 6:
                entity = new Rooms();
                break;
            case 7:
                entity = new RoomPrices();
                break;
            case 8:
                entity = new Services();
                break;
            case 9:
                entity = new Buys();
                break;
            case 10:
                entity = new Serves();
                break;
            default:
                System.out.println("Please Enter a valid option ....");
                return;
        }
        entity.crudOperations();

    }
}

