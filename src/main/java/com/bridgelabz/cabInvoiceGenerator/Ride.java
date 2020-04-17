package com.bridgelabz.cabInvoiceGenerator;

public class Ride {
    public final CabSubscriptions cabRide ;
    double distance;
    int time;

    public Ride(CabSubscriptions ride, double distance, int time) {
        this.distance = distance;
        this.time = time;
        this.cabRide =ride;
    }
}
