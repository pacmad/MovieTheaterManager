import java.util.Scanner;
import java.util.logging.SimpleFormatter;

public class Main {

    //TODO
    // Statistics:
    // 1. Current Income
    // 2. Total Income
    // 3. Statistics
    // 0. Exit
    private static int rows;
    private static int numberOfSeatsPerRow;
    private static int rowNumber;
    private static int seatNumber;
    private static int purchasedTickets;
    private static double purchasedTicketsPercent = purchasedTickets / (rows * numberOfSeatsPerRow);

    private static int currentIncome = 0; // how much money made so far
    private static int totalIncome;
    // change totalIncome name later
    private static Scanner input = new Scanner(System.in);
    private static String[][] seatingArrangement;


    public static void main(String[] args){

        setTheaterSize();
        seatingArrangement = createSeatingArrangement(rows, numberOfSeatsPerRow);
        menu();
        //printSeatingArrangement();
        //getUserSeatCoordinates();
        //System.out.println(getTicketPrice(rows, numberOfSeatsPerRow, rowNumber));
        //reserveSeat(rowNumber, seatNumber);
    }

    private static void setTheaterSize(){
        System.out.println("Please enter the number of rows");
        rows = input.nextInt();
        System.out.println("Please enter the number of seats per row");
        numberOfSeatsPerRow = input.nextInt();
    }

    private static void getUserSeatCoordinates(){
        System.out.println("Please enter a row number:");
        rowNumber = input.nextInt();

        System.out.println("Enter a seat number in that row:");
        seatNumber = input.nextInt();
    }

    private static void menu(){
        boolean exit = false;
        while(exit == false){
            System.out.println("1. Show the seats\n2. Buy a ticket\n0. Exit");
            int userInput = input.nextInt();
            switch (userInput){
                case 1:
                    showSeats();
                    break;
                case 2:
                    buyTicket();
                    break;
                case 3:
                    statistics();
                    break;
                case 0:
                    System.out.println("Exiting the program");
                    exit = true;
                default:
                    System.out.println("Invalid entry");
            }
        }
    }

    private static void buyTicket(){
        // should not allow the user to buy a ticket that has already been purchased
        getUserSeatCoordinates();
        System.out.println(getTicketPrice(rows, numberOfSeatsPerRow, rowNumber));
        reserveSeat(rowNumber, seatNumber);
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

    private static void showSeats(){
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
        if(seatingArrangement[rowNumber - 1][seatNumber - 1] == "S"){
            seatingArrangement[rowNumber - 1][seatNumber - 1] = "B";
            System.out.println("Reserved seat number: " +seatNumber+ " in row number: " +rowNumber);
        } else {
            System.out.println("Seat already reserved, please choose another seat");
        }

        showSeats();

        return seatingArrangement;
    }

    private static void statistics(){
        // number of purchased tickets
        // number of purchased tickets represented as a percentage - rounded to 2 decimal places
        // current income
        // total income shows how much money the theater will get if all of the tickets are sold
        System.out.println(currentIncome);
    }

    private static String getTicketPrice(int rows, int numberOfSeatsPerRow, int rowNumber){

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
        // add ticketPrice to current income
        currentIncome += ticketPrice;

        return "Ticket Price: " + ticketPrice;
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

