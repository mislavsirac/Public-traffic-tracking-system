package entities;

public class Vehicle{
    public VehicleType type;
    public int capacity;
    public User[] travelers;

    public Vehicle(VehicleType type, int capacity, User[] travelers) {
        this.type = type;
        this.capacity = capacity;
        this.travelers = travelers;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public User[] getTravelers() {
        return travelers;
    }

    public void setTravelers(User[] travelers) {
        this.travelers = travelers;
    }
}
