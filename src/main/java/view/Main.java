package view;

import model.Booking;
import database.StorageMock;

import java.util.List;
import java.util.Scanner;

/**
 * Minimal console UI :)
 */
public class Main {
    public static void main(String[] args) {
        controller.TourController controller = new controller.TourController(new StorageMock());
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to 3D DayTours!");
        while (true) {
            System.out.println("\nMenu: \n1: List of tours \n2: Search for a tour \n3: Filter tours by type \n4: Filter tours by region \n5: List travelers \n6: Book a tour \n0: Exit");
            System.out.print("What do you want to do? ");
            String c = sc.nextLine().trim();
            if ("0".equals(c)) break;
            switch (c) {
                case "1":
                    controller.listAll().forEach(System.out::println);
                    break;
                case "2":
                    System.out.print("Search for: ");
                    String q = sc.nextLine().trim();
                    List<model.Tour> results = controller.search(q);
                    if (results.isEmpty()) {
                        System.out.println("No tours found for: " + q);
                    } else {
                        System.out.println("Search results: ");
                        results.forEach(System.out::println);
                    }
                    break;
                case "3":
                    System.out.print("Type: ");
                    String t = sc.nextLine();
                    controller.filterByType(t).forEach(System.out::println);
                    break;
                case "4":
                    System.out.print("Region: ");
                    String r = sc.nextLine();
                    controller.filterByRegion(r).forEach(System.out::println);
                    break;
                case "5":
                    controller.listTravelers().forEach(System.out::println);
                    break;
                case "6":
                    try {
                        System.out.print("Tour id (numeric): ");
                        String tid = sc.nextLine().trim();
                        System.out.print("Traveler id (numeric): ");
                        int trid = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("How many tickets? ");
                        // Maybe we should print out how many tickets are available for the chosen tour?
                        int tickets = Integer.parseInt(sc.nextLine().trim());
                        Booking b = controller.book(tid, trid, tickets);
                        System.out.println("Booked: " + b);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Unknown choice, please choose a number between 1-6 from the menu or 0 to exit.");
            }
        }
        sc.close();
        System.out.println("Thanks for stopping by, until next time!");
    }
}
