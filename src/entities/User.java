package entities;

import java.util.Date;

public class User extends Person {
    public Booking booking;
    public Stop entryStop;
    public Stop exitStop;

    public User(String name, String surname, Date birthDate) {
        super(name, surname, birthDate);
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Stop getEntryStop() {
        return entryStop;
    }

    public void setEntryStop(Stop entryStop) {
        this.entryStop = entryStop;
    }

    public Stop getExitStop() {
        return exitStop;
    }

    public void setExitStop(Stop exitStop) {
        this.exitStop = exitStop;
    }
}
