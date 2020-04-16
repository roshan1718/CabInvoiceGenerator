package com.bridgelabz.cabInvoiceGenerator;

public class  InvoiceService {
    public static final double MIN_COST_PER_KMS = 10 ;
    public static final int COST_PER_TIME = 1 ;
    public static final int MIN_FARE=5;

    double totalFare=0.0;

    RideRepository rideRepository;

    public InvoiceService() {
        this.rideRepository = new RideRepository();
    }
    public double calculateFare(double distance, int time) {
        double totalFare= distance*MIN_COST_PER_KMS + time*COST_PER_TIME;
        if ( totalFare < MIN_FARE)
            return MIN_FARE;
        return totalFare;
    }


    public InvoiceSummary calculateFare(Ride[] rides) {
        for (Ride ride : rides) {
            totalFare += this.calculateFare(ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length, (int) totalFare);
    }

    public void addRide(String userId, Ride[] rides) {
        rideRepository.addRides(userId, rides);
    }
    public InvoiceSummary getInvoiceSummary(String userId) {
        return this.calculateFare(rideRepository.getRides(userId));
    }
}
