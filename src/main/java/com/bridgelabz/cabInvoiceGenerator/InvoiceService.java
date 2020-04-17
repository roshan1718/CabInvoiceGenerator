package com.bridgelabz.cabInvoiceGenerator;

public class  InvoiceService {

     private RideRepository rideRepository;
    double totalFare = 0;

    public void setRideRepository(RideRepository rideRepository) {

        this.rideRepository = rideRepository;
    }

    public double calculateFareForNormalAndPremiumRide(Ride[] rides) {
        for (Ride ride : rides) {
            totalFare += ride.cabRide.calculateFare(ride);
        }
        return totalFare;
    }
    public InvoiceSummary calculateFare(Ride[] rides) {
        for (Ride ride : rides) {
            totalFare += ride.cabRide.calculateFare(ride);
        }
        return new InvoiceSummary(rides.length,  totalFare);
    }

    public void addRide(String userId, Ride[] rides) {

        rideRepository.addRides(userId, rides);
    }
    public InvoiceSummary getInvoiceSummary(String userId) {
        return this.calculateFare(rideRepository.getRides(userId));
    }
}
