package Entities;

public class Booking {

    private String firstname;
    private String lastname;
    private float totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;

    public Booking(String firstName, String lastName, float totalPrice, boolean depositPaid, BookingDates bookingDates,
            String additionalNeeds) {
        this.firstname = firstName;
        this.lastname = lastName;
        this.totalprice = totalPrice;
        this.depositpaid = depositPaid;
        this.bookingdates = bookingDates;
        this.additionalneeds = additionalNeeds;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(float totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public BookingDates getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(BookingDates bookingdates) {
        this.bookingdates = bookingdates;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

}
