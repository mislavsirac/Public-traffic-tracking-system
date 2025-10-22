package entities;

public class Route {
    public Stop[] stops;
    public Driver driver;

    public Route(Stop[] stops, Driver driver) {
        this.stops = stops;
        this.driver = driver;
    }

    public Stop[] getStops() {
        return stops;
    }

    public void setStops(Stop[] stops) {
        this.stops = stops;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
