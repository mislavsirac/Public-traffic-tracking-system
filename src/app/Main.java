package app;

import entities.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class Main {

    private static final int N = 5;

    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("=== CREATE 5 STOPS ===");
        Stop[] stops = new Stop[N];
        for (int i = 0; i < N; i++) {
            System.out.print("Enter stop name [" + i + "]: ");
            String name = sc.nextLine();
            stops[i] = new Stop(i, name);
        }

        System.out.println("\n=== CREATE 5 VEHICLES ===");
        Vehicle[] vehicles = new Vehicle[N];
        for (int i = 0; i < N; i++) {
            System.out.print("Enter vehicle type (Bus/Tram/Train): ");
            VehicleType type = VehicleType.valueOf(sc.nextLine());
            System.out.print("Enter capacity: ");
            int capacity = Integer.parseInt(sc.nextLine());
            vehicles[i] = new Vehicle(type, capacity, new User[0]);
        }

        System.out.println("\n=== CREATE 5 DRIVERS ===");
        Driver[] drivers = new Driver[N];
        for (int i = 0; i < N; i++) {
            System.out.print("Enter driver name: ");
            String name = sc.nextLine();
            System.out.print("Enter driver surname: ");
            String surname = sc.nextLine();
            System.out.print("Enter birth date (yyyy-MM-dd): ");
            Date birth = df.parse(sc.nextLine());
            // for now, route and vehicle will be assigned later
            drivers[i] = new Driver(name, surname, birth, null, null);
        }

        System.out.println("\n=== CREATE 5 ROUTES ===");
        Route[] routes = new Route[N];
        for (int i = 0; i < N; i++) {
            System.out.println("Creating route [" + i + "]");
            // Each route uses 2 stops and 1 driver
            Stop[] routeStops = new Stop[]{stops[i % N], stops[(i + 1) % N]};
            Driver driver = drivers[i];
            Vehicle vehicle = vehicles[i];
            Route route = new Route(routeStops, driver);
            routes[i] = route;
            driver.setRoute(route);
            driver.setVehicle(vehicle);
        }

        System.out.println("\n=== CREATE 5 USERS ===");
        User[] users = new User[N];
        for (int i = 0; i < N; i++) {
            System.out.print("Enter user name: ");
            String name = sc.nextLine();
            System.out.print("Enter user surname: ");
            String surname = sc.nextLine();
            System.out.print("Enter birth date (yyyy-MM-dd): ");
            Date birth = df.parse(sc.nextLine());

            // Pick random route and stops
            Route route = routes[i];
            Stop entry = route.getStops()[0];
            Stop exit = route.getStops()[1];
            Booking booking = new Booking(route, 10.0 + i, 5.0 + i);
            User user = new User(name, surname, birth);
            user.setEntryStop(entry);
            user.setExitStop(exit);
            user.setBooking(booking);
            users[i] = user;
        }

        // Attach users to vehicles (first few)
        for (int i = 0; i < N; i++) {
            vehicles[i].setTravelers(users);
        }

        // ====== DEMO SEARCH AND MIN/MAX ======

        boolean run = true;
        while (run) {
            System.out.println("""
                \n===== MENU =====
                1) List all users
                2) Search user by surname (FOR)
                3) Search stop by name (WHILE)
                4) Search driver by name (DO-WHILE)
                5) Find vehicle with max capacity
                6) Find oldest driver
                0) Exit
                """);
            System.out.print("Choose option: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> listAll(users, drivers, vehicles, routes);
                case 2 -> searchUserBySurname(sc, users);
                case 3 -> searchStopByName(sc, stops);
                case 4 -> searchDriverByName(sc, drivers);
                case 5 -> printVehicleWithMaxCapacity(vehicles);
                case 6 -> printOldestDriver(drivers);
                case 0 -> run = false;
                default -> System.out.println("Unknown choice.");
            }
        }

        sc.close();
    }

    // --- Utility methods ---

    private static void listAll(User[] users, Driver[] drivers, Vehicle[] vehicles, Route[] routes) {
        System.out.println("\n=== USERS ===");
        for (int i = 0; i < users.length; i++) {
            User u = users[i];
            System.out.println("[" + i + "] " + u.getName() + " " + u.getSurname() +
                    ", route stops: " + u.getBooking().getRoute().getStops()[0].getName() +
                    " -> " + u.getBooking().getRoute().getStops()[1].getName());
        }

        System.out.println("\n=== DRIVERS ===");
        for (int i = 0; i < drivers.length; i++) {
            Driver d = drivers[i];
            System.out.println("[" + i + "] " + d.getName() + " " + d.getSurname() +
                    ", vehicle=" + (d.getVehicle() != null ? d.getVehicle().getType() : "none"));
        }

        System.out.println("\n=== VEHICLES ===");
        for (int i = 0; i < vehicles.length; i++) {
            Vehicle v = vehicles[i];
            System.out.println("[" + i + "] " + v.getType() + ", capacity=" + v.getCapacity());
        }

        System.out.println("\n=== ROUTES ===");
        for (int i = 0; i < routes.length; i++) {
            Route r = routes[i];
            System.out.println("[" + i + "] " + r.getStops()[0].getName() + " -> " +
                    r.getStops()[1].getName());
        }
    }

    // FOR
    private static void searchUserBySurname(Scanner sc, User[] users) {
        System.out.print("Enter part of surname: ");
        String q = sc.nextLine().toLowerCase();
        boolean found = false;
        for (int i = 0; i < users.length; i++) {
            if (users[i].getSurname().toLowerCase().contains(q)) {
                System.out.println(users[i].getName() + " " + users[i].getSurname());
                found = true;
            }
        }
        if (!found) System.out.println("No users found.");
    }

    // WHILE
    private static void searchStopByName(Scanner sc, Stop[] stops) {
        System.out.print("Enter stop name: ");
        String q = sc.nextLine().toLowerCase();
        int i = 0;
        boolean found = false;
        while (i < stops.length) {
            if (stops[i].getName().toLowerCase().contains(q)) {
                System.out.println("Found stop: " + stops[i].getName());
                found = true;
            }
            i++;
        }
        if (!found) System.out.println("No stops found.");
    }

    // DO-WHILE
    private static void searchDriverByName(Scanner sc, Driver[] drivers) {
        System.out.print("Enter driver name: ");
        String q = sc.nextLine().toLowerCase();
        int i = 0;
        boolean found = false;
        if (drivers.length == 0) return;
        do {
            if (drivers[i].getName().toLowerCase().contains(q)) {
                System.out.println("Found driver: " + drivers[i].getName() + " " + drivers[i].getSurname());
                found = true;
            }
            i++;
        } while (i < drivers.length);
        if (!found) System.out.println("No drivers found.");
    }

    // MAX
    private static void printVehicleWithMaxCapacity(Vehicle[] vehicles) {
        if (vehicles.length == 0) return;
        int maxIdx = 0;
        for (int i = 1; i < vehicles.length; i++) {
            if (vehicles[i].getCapacity() > vehicles[maxIdx].getCapacity())
                maxIdx = i;
        }
        System.out.println("Vehicle with max capacity: " +
                vehicles[maxIdx].getType() + " (" + vehicles[maxIdx].getCapacity() + ")");
    }

    // MIN (actually oldest = max age)
    private static void printOldestDriver(Driver[] drivers) {
        if (drivers.length == 0) return;
        int oldestIdx = 0;
        for (int i = 1; i < drivers.length; i++) {
            if (drivers[i].getBirthDate().before(drivers[oldestIdx].getBirthDate()))
                oldestIdx = i;
        }
        System.out.println("Oldest driver: " + drivers[oldestIdx].getName() + " " +
                drivers[oldestIdx].getSurname() + " (" + drivers[oldestIdx].getBirthDate() + ")");
    }
}
