package edu.csc.dbms;

public class Constants {

    //Hotel Table
    public static final String HOTELS_TABLE = "Hotels";

    public static final String HOTELS_ID = "hotelId";
    public static final String HOTELS_NAME = "Name";
    public static final String HOTELS_ADDRESS = "Address";
    public static final String HOTELS_CITY = "City";
    public static final String HOTELS_STATE = "State";
    public static final String HOTELS_COUNTRY = "Country";
    public static final String HOTELS_PH_NUMBER = "phoneNumber";
    public static final String HOTELS_MANAGER_ID = "managerId";

    //Staff Table
    public static final String STAFFS_TABLE = "Staff";

    public static final String STAFFS_ID = "staffId";
    public static final String STAFFS_NAME = "name";
    public static final String STAFFS_AGE = "age";
    public static final String STAFFS_JOB_TITLE = "jobTitle";
    public static final String STAFFS_PH_NUMBER = "phoneNumber";
    public static final String STAFFS_ADDRESS = "Address";
    public static final String STAFFS_CITY = "City";
    public static final String STAFFS_STATE = "State";
    public static final String STAFFS_COUNTRY = "Country";
    public static final String STAFFS_HOTEL_ID = "hotelId";

    //Customer Table
    public static final String CUSTOMERS_TABLE = "Customers";

    public static final String CUSTOMERS_ID = "customerId";
    public static final String CUSTOMERS_NAME = "name";
    public static final String CUSTOMERS_DOB = "dateOfBirth";
    public static final String CUSTOMERS_PH_NUMBER = "phoneNumber";
    public static final String CUSTOMERS_EMAIL = "email";

    //Services Table
    public static final String SERVICES_TABLE = "Services";

    public static final String SERVICES_ID = "serviceId";
    public static final String SERVICES_NAME = "name";
    public static final String SERVICES_BASE_PRICE = "basePrice";

    //Payment_Infos Table
    public static final String PAYMENT_INFOS_TABLE = "PaymentInfo";

    public static final String PAYMENT_INFOS_ID = "paymentId";
    public static final String PAYMENT_INFOS_SSN = "SSN";
    public static final String PAYMENT_INFOS_BILLING_ADDRESS = "billingAddress";
    public static final String PAYMENT_INFOS_CITY = "City";
    public static final String PAYMENT_INFOS_STATE = "State";
    public static final String PAYMENT_INFOS_COUNTRY = "Country";
    public static final String PAYMENT_INFOS_PAYMENT_METHOD = "paymentMethod";
    public static final String PAYMENT_INFOS_CARD_NUM = "cardNumber";
    public static final String PAYMENT_INFOS_CUSTOMER_ID = "customerId";

    //Room Table
    public static final String ROOMS_TABLE = "Rooms";

    public static final String ROOMS_ROOMNUMBER = "roomNumber";
    public static final String ROOMS_HOTELID = "hotelId";
    public static final String ROOMS_CATEGORY = "category";
    public static final String ROOMS_MAXOCCUPANCY = "maxOccupancy";
    public static final String ROOMS_AVAILABILITY = "availability";

    //Room Prices Table
    public static final String ROOM_PRICES_TABLE = "RoomPrices";

    public static final String ROOM_PRICES_CATEGORY = "category";
    public static final String ROOM_PRICES_MAXOCCUPANCY = "maxOccupancy";
    public static final String ROOM_PRICES_PRICE = "price";

    //CheckIns Table
    public static final String CHECK_INS_TABLE = "Checkins";

    public static final String CHECK_INS_CHECKINID = "checkinId";
    public static final String CHECK_INS_STARTDATE = "startDate";
    public static final String CHECK_INS_ENDDATE = "endDate";
    public static final String CHECK_INS_CHECKINTIME = "checkinTime";
    public static final String CHECK_INS_CHECKOUTTIME = "checkoutTIme";
    public static final String CHECK_INS_NUMBEROFGUESTS = "numberOfGuests";
    public static final String CHECK_INS_TOTAL = "total";
    public static final String CHECK_INS_CUSTOMERID = "customerId";
    public static final String CHECK_INS_HOTELID = "hotelId";
    public static final String CHECK_INS_ROOMNUMBER = "roomNumber";
    public static final String CHECK_INS_PAYMENTID = "paymentId";

    //Buys Table
    public static final String BUYS_TABLE = "Buys";

    public static final String BUYS_SERVICEID = "serviceId";
    public static final String BUYS_CHECKINID = "checkinId";
    public static final String BUYS_PRICE = "price";

    //Serves Table
    public static final String SERVES_TABLE = "Serves";

    public static final String SERVES_STAFFID = "staffId";
    public static final String SERVES_CHECKINID = "checkinId";

    //Other Constants
    public static final String PAY_METHOD_HOTEL_CREDIT = "hotel credit";

    //Task3 - Maintaince and Billing Accounts  
    public static final String TOTAL_PRICE = "totalPrice";
    public static final String BASE_PRICE = "basePrice";
    public static final String PRICE = "price";
    public static final String DESCRIPTION = "description";
    public static final String DESCRIPTION_VALUE = "Room rent";
    public static final String NUMBER = "number";

}
