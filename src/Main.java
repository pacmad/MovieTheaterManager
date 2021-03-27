import java.util.Scanner;
import java.util.logging.SimpleFormatter;

public class Main {

    private static int rows;
    private static int numberOfSeatsPerRow;
    private static int rowNumber;
    private static int seatNumber;
    private static Scanner input = new Scanner(System.in);
    private static String[][] seatingArrangement;


    public static void main(String[] args){

        System.out.println("Please enter the number of rows");
        rows = input.nextInt();
        System.out.println("Please enter the number of seats per row");
        numberOfSeatsPerRow = input.nextInt();
        seatingArrangement = createSeatingArrangement(rows, numberOfSeatsPerRow);
        printSeatingArrangement();

        //seatingArrangement(rows, numberOfSeatsPerRow);

        System.out.println("Please enter the row of the seat");
        rowNumber = input.nextInt();
        System.out.println("Please enter the seat number");
        seatNumber = input.nextInt();

        reserveSeat(rowNumber, seatNumber);
        printSeatingArrangement();
        //calculateTicketCost();

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
        //get value of seat of arrangement
        // isSeatReserved? "S" false, "B" true
        // if false, reserve the seat
            // if true "please pick another seat, this one is reserved

        if(seatingArrangement[rowNumber][seatNumber] == "S"){
            seatingArrangement[rowNumber][seatNumber] = "B";
        }

        return seatingArrangement;
    }

    /*
    In a larger room, the tickets are 10 dollars for the front half of the rows and 8 dollars for the back half.
    Please note that the number of rows can be odd, for example, 9 rows.
    In this case, the first half is the first 4 rows, and the second half is the rest 5 rows.
 */
    public static void calculateTicketCost(int rows, int seatsPerRow){

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

    public static void getTicketPrice(){
        int ticketPrice = 0;

        /*
        *
        If the total number of seats in the screen room is not more than 60, then the price of each ticket is 10 dollars.
        In a larger room, the tickets are 10 dollars for the front half of the rows and 8 dollars for the back half.
        Please note that the number of rows can be odd, for example, 9 rows. In this case, the first half is the first 4 rows, and the second half is the rest 5 rows.
        * */
    }

}

