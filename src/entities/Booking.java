package entities;

public class Booking {
    public Route route;
    public double price;
    public double length;

    public Booking(Route route, double price, double length) {
        this.route = route;
        this.price = price;
        this.length = length;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}
