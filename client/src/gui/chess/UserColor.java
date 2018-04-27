package gui.chess;

import java.util.Random;

public class UserColor {
    private static String userColor;


    /**
     * For setting the color what chess pieces the USERS will be using in the GUI.
     * Generates a random number and then calls the setUserColor method below
     */
    public static void settingUserColor(){
        Random r = new Random();
        int randomNumber = r.nextInt(10-1)+1;

        System.out.println("RANDOM NUMBER IS : "  + randomNumber);

        if(randomNumber<=10 && randomNumber>5) {
            String color = "BLACK";
            setUserColor(color);
        }else{
            String color = "WHITE";
            setUserColor(color);
        }
    }


    public static void setUserColor(String color){
        userColor=color;
    }

    public static String getUserColor(){
        return userColor;
    }


}
