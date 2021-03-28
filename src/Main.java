import java.util.Scanner;
import java.util.logging.SimpleFormatter;

public class Main {

    //TODO
    // fix seat reservation method, it's one off

    private static int rows;
    private static int numberOfSeatsPerRow;

    private static int rowNumber;
    private static int seatNumber;
    private static Scanner input = new Scanner(System.in);
    private static String[][] seatingArrangement;


    public static void main(String[] args){

        programStart();
        seatingArrangement = createSeatingArrangement(rows, numberOfSeatsPerRow);
        printSeatingArrangement();
        getUserSeatCoordinates();
        reserveSeat(rowNumber, seatNumber);
        System.out.println(getTicketPrice(rows, numberOfSeatsPerRow, rowNumber));


//        printSeatingArrangement();
        //


    }

    private static void programStart(){
        System.out.println("Welcome");
        System.out.println("Please enter the number of rows");
        rows = input.nextInt();
        System.out.println("Please enter the number of seats per row");
        numberOfSeatsPerRow = input.nextInt();
    }

    private static void getUserSeatCoordinates(){
        System.out.println("Please enter the row of the seat");
        rowNumber = input.nextInt();

        System.out.println("Please enter the seat number");
        seatNumber = input.nextInt();
    }


    private static String[][] createSeatingArrangement(int rows, int numberOfSeatsPerRow){
        // throw an error if rows or numberofseatsperrow exceed 9
        // create multi-dimensional array
        String[][] multiArray = new String[rows][numberOfSeatsPerRow];
        // populate array with S
        for(int i = 0; i <= multiArray.length - 1; i++){
            for(int j = 0; j < multiArray[i].length; j++){
                multiArray[i][j] = "S";
            }
        }
         //return array
        return multiArray;
    }

    private static void printSeatingArrangement(){
        System.out.println("Theatre:");
        System.out.println("  1 2 3 4 5 6 7 8 9"); // make this dynamic
        for(int i = 0; i <= seatingArrangement.length - 1; i++){
            System.out.print((i + 1) + " ");
            for(int j = 0;  j < seatingArrangement[i].length; j++){
                System.out.print(seatingArrangement[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static String[][] reserveSeat(int rowNumber, int seatNumber){
        if(seatingArrangement[rowNumber][seatNumber] == "S"){
            seatingArrangement[rowNumber][seatNumber] = "B";
            System.out.println("Reserved seat number: " +seatNumber+ " in row number: " +rowNumber);
        }

        printSeatingArrangement();

        return seatingArrangement;
    }

    public static int getTicketPrice(int rows, int numberOfSeatsPerRow, int rowNumber){

        int totalSeats = rows * numberOfSeatsPerRow;
        int frontHalfLastRow = rows / 2; //ex: 9/2 = 4 front rows
        int backHalfFirstRow = rows - frontHalfLastRow; // ex: 9 - 4 = 5 back rows
        int ticketPrice = 0;

        if(totalSeats <= 60){
            ticketPrice = 10;
        } else{
            // if size > 60, the ticket cost is 10 for the first half rows and 8 for the last half of rows
            // determine if seat is in backhalf or fronthalf based on the rowNumber
            if(rowNumber >= 1 && rowNumber <= frontHalfLastRow){// front half would be anything >= 1 && <= frontHalf
                ticketPrice = 10;
            } else if (rowNumber >= backHalfFirstRow && rowNumber <= rows){//back half would be anything >= backHalf && <= rows
                ticketPrice = 8;
            } else {
                System.out.println("Unable to get ticket price");
            }
        }
        // return ticket cost
        return ticketPrice;
    }


    public static void calculateProfit(int rows, int seatsPerRow){

        int totalSeats = rows * seatsPerRow;
        int profit = 0;

        if(totalSeats <= 60){
            profit = 10 * totalSeats;
        } else if(totalSeats > 60){
            int firstHalf  = rows / 2;
            int firstHalfSeats = firstHalf * seatsPerRow;
            int lastHalf = rows - firstHalf;
            int lastHalfSeats = lastHalf * seatsPerRow;

            profit = (firstHalfSeats * 10) + (lastHalfSeats * 8);
        }

        System.out.println("Total Income: " + "\n" + "$"+profit);
    }


    }

