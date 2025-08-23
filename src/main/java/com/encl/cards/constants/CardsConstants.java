package com.encl.cards.constants;

public final class CardsConstants {

    private CardsConstants() {
        // restrict instantiation
    }

    public static final String  CREDIT_CARD = "Mastercard";
    public static final int  NEW_CARD_LIMIT = 1000;
    public static final String  STATUS_201 = "201";
    public static final String  STATUS_201_MESSAGE = "Card created successfully";
    public static final String  STATUS_200 = "200";
    public static final String  STATUS_200_MESSAGE = "Request processed successfully";
    public static final String STATUS_400 = "400";
    public static final Object STATUS_400_MESSAGE = "Bad Request. Please check the input parameters";

    public static final String  STATUS_417 = "417";
    public static final String  MESSAGE_417_UPDATE= "Update operation failed. Please try again or contact Dev team";
    public static final String  MESSAGE_417_DELETE= "Delete operation failed. Please try again or contact Dev team";
    // public static final String  STATUS_500 = "500";
    // public static final String  MESSAGE_500 = "An error occurred. Please try again or contact Dev team";


}
