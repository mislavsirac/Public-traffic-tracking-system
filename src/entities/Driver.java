package entities;

import java.util.Date;

public class Driver extends Person {

    public Route route;
    public Vehicle vehicle;

    public Driver(String name, String surname, Date birthDate, Route route, Vehicle vehicle) {
        super(name, surname, birthDate);
        this.route = route;
        this.vehicle = vehicle;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
