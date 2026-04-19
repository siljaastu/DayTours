package view;

import database.TourDB;
import model.Booking;
import model.Tour;
import model.Traveler;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Minimal console UI :)
 */
public class Main {
    public static void main(String[] args) {
        controller.TourController controller = new controller.TourController();
        controller.TravelerController trController = new controller.TravelerController(new TourDB());
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to 3D DayTours!");
        while (true) {
            System.out.println("\nMenu: \n1: List of tours \n2: Search for a tour \n3: Filter tours by type \n4: Filter tours by region \n5: List travelers \n6: Book a tour \n0: Exit");
            System.out.print("What do you want to do? ");
            String c = sc.nextLine().trim();
            if ("0".equals(c)) break;
            switch (c) {
                case "1":
                    List<Tour> tours = controller.listUniqueTours();
                    System.out.println("Available tours:");
                    for (int i = 0; i < tours.size(); i++) {
                        System.out.println((i + 1) + ": " + tours.get(i).getTourName());
                    }
                    break;
                case "2":
                    System.out.print("Search for: ");
                    String q = sc.nextLine().trim();
                    List<Tour> results = controller.search(q);
                    if (results.isEmpty()) {
                        System.out.println("No tours found for: " + q);
                    } else {
                        System.out.println("Search results: ");
                        results.forEach(System.out::println);
                    }
                    break;
                case "3":
                    System.out.println("These are the types of tours we offer:");
                    List<String> types = controller.getAllTourTypes();

                    if (types.isEmpty()) {
                        System.out.println("Sorry, there are no tour types available.");
                        break;
                    }

                    for (int i = 0; i < types.size(); i++) {
                        System.out.println((i + 1) + ". " + types.get(i));
                    }

                    System.out.print("Which tour type would you like to filter by? Number: ");
                    String input = sc.nextLine();

                    int choice;
                    try {
                        choice = Integer.parseInt(input);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        break;
                    }

                    if (choice < 1 || choice > types.size()) {
                        System.out.println("Sorry, that input is not valid.");
                        break;
                    }

                    String selectedType = types.get(choice - 1);

                    controller.filterByType(selectedType)
                            .forEach(System.out::println);

                    break;
                case "4":
                    System.out.println("These are the regions we offer tours in:");
                    List<String> regions = controller.getAllRegions();

                    if (regions.isEmpty()) {
                        System.out.println("Sorry, there are no regions available.");
                        break;
                    }

                    // print list
                    for (int i = 0; i < regions.size(); i++) {
                        System.out.println((i + 1) + ". " + regions.get(i));
                    }

                    System.out.print("Which region would you like to filter by? Number: ");
                    String inputRegion = sc.nextLine();

                    int regionChoice;
                    try {
                        regionChoice = Integer.parseInt(inputRegion);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        break;
                    }

                    if (regionChoice < 1 || regionChoice > regions.size()) {
                        System.out.println("Sorry, that input is not valid.");
                        break;
                    }

                    String selectedRegion = regions.get(regionChoice - 1);

                    controller.filterByRegion(selectedRegion)
                            .forEach(System.out::println);

                    break;
                case "5":
                    trController.listAll().forEach(System.out::println);
                    break;
                case "6":
                    try {
                        List<Tour> allTours = controller.listAllTours();

                        System.out.println("Available tours (use Tour ID to book):");
                        for (Tour t : allTours) {
                            System.out.println(
                                    t.getTourID() + " | " +
                                            t.getTourName() + " | " +
                                            t.getDate() + " " +
                                            t.getStartTime() +
                                            " | Available seats: " + t.getRemainingSeats()
                            );
                        }

                        // 2: user enters tour ID
                        System.out.print("\nEnter Tour ID: ");
                        String tid = sc.nextLine().trim();

                        // validate tour exists
                        if (controller.findById(tid).isEmpty()) {
                            System.out.println("Tour not found.");
                            break;
                        }

                        // 3: traveler id
                        System.out.print("Traveler id: ");
                        String travelerInput = sc.nextLine().trim();

                        int trid;
                        try {
                            trid = Integer.parseInt(travelerInput);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid traveler id.");
                            break;
                        }

                        // 4: tickets
                        System.out.print("How many tickets? ");
                        String ticketInput = sc.nextLine().trim();

                        int tickets;
                        try {
                            tickets = Integer.parseInt(ticketInput);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number of tickets.");
                            break;
                        }

                        // 5: book
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
