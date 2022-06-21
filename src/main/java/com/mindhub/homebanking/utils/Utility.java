package com.mindhub.homebanking.utils;

public  final class Utility {
    public static int getRandomNumber(int min, int max) {
       return (int) ((Math.random() * (max - min)) + min);
    }

   // public static String getAccountnumber(){
      //  String number;
      //  number = "VIN" + getRandomNumber(1000,99999999);
      ///  return number;
   // }

 //   public static int getCVV(){
 //       return getRandomNumber(100,999);
   // }
 public  static String getNumberAccount() {
     String number = "VIN" + getRandomNumber(0, 99999999);
     return number;
 }
    public static  String getNumberCard() {
        String number = "" + getRandomNumber(1000,9999) +" "+ getRandomNumber(1000,9999) + " " +getRandomNumber(1000,9999) + " " +getRandomNumber(1000,9999);
        return number;
    }

    public static int getCvv() {
        int cvv = getRandomNumber(100,999);
        return cvv;
    }

}
