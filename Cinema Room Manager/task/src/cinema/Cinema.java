package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        // Write your code here
        int purchasedTickets = 0;
        int currentIncome = 0;
        int totalIncome = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int row = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int col = scanner.nextInt();

        int totalSeats = row * col;
        String[][] cinemaSeats = new String[row + 1][col + 1];
        for (String[] cinemaSeat : cinemaSeats) {
            Arrays.fill(cinemaSeat, "S");
            totalIncome = calculateIncome(row, col, totalSeats);
        }

        while (true) {
            System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
            int choice = scanner.nextInt();
            if (choice == 0) {
                break;
            } else if (choice == 1) {
                printCinema(cinemaSeats);
            } else if (choice == 2) {
                currentIncome += buyTicket(cinemaSeats, row, col, totalSeats);
                purchasedTickets++;
            } else if (choice == 3) {
                calculateStatistics(purchasedTickets, totalSeats, currentIncome, totalIncome);
            } else {
                System.out.println("Wrong input!");
            }
        }
        scanner.close();

    }

    public static void printCinema(String[][] cinemaSeats) {
        System.out.println("Cinema:");
        System.out.print("  "); // Leading space for row numbers
        for (int j = 1; j < cinemaSeats[0].length; j++) {
            System.out.print(j + " "); // Print column numbers
        }
        System.out.println(); // End of column header line

        // Print rows with seat labels
        for (int i = 1; i < cinemaSeats.length; i++) {
            System.out.print(i + " "); // Print row number
            for (int j = 1; j < cinemaSeats[i].length; j++) {
                System.out.print(cinemaSeats[i][j] + " "); // Print seat status
            }
            System.out.println(); // End of row
        }
    }

    public static int calculateIncome(int row, int col, int totalSeats) {
        if (totalSeats < 60) {
            return row * col * 10;
        } else {
            int frontHalf = row >> 1;
            int backHalf = row - frontHalf;
            int frontHalfIncome = frontHalf * col * 10;
            int backHalfIncome = backHalf * col * 8;
            return frontHalfIncome + backHalfIncome;
        }
    }

    public static void calculateStatistics(int purchasedTickets, int totalSeats, int currentIncome, int totalIncome) {
        System.out.println("Number of purchased tickets: " + purchasedTickets);
        System.out.println("Percentage: " + String.format("%.2f", (double) purchasedTickets / totalSeats * 100) + "%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

    public static int buyTicket(String[][] cinemaSeats, int row, int col, int totalSeats) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int selectedRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int selectedSeat = scanner.nextInt();

        if (selectedRow < 1 || selectedRow > row || selectedSeat < 1 || selectedSeat > col) {
            System.out.println("Wrong input!");
            buyTicket(cinemaSeats, row, col, totalSeats);
        } else if (cinemaSeats[selectedRow][selectedSeat].equals("B")) {
            System.out.println("That ticket has already been purchased!");
            buyTicket(cinemaSeats, row, col, totalSeats);
        } else if (totalSeats < 60 || selectedRow <= (row >> 1)) {
            System.out.println("Ticket price: $10");
            cinemaSeats[selectedRow][selectedSeat] = "B";
            return 10;
        } else {
            System.out.println("Ticket price: $8");
            cinemaSeats[selectedRow][selectedSeat] = "B";
            return  8;
        }
        return 0;
    }
}