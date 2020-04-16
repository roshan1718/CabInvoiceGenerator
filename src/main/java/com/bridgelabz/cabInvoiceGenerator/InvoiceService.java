package com.bridgelabz.cabInvoiceGenerator;

public class  InvoiceService {
    public static final double MIN_COST_PER_KMS_NORMAL = 10 ;
    public static final int COST_PER_TIME_NORMAL = 1 ;
    public static final int MIN_FARE_NORMAL=5;

    public static final double MIN_COST_PER_KMS_PREMIUM = 15 ;
    public static final int COST_PER_TIME_PREMIUM = 2 ;
    public static final int MIN_FARE_PREMIUM=20;
    double totalFare=0;


    private CabSubscriptions cabSubscriptions;
    RideRepository rideRepository;

    public InvoiceService(CabSubscriptions cabSubscriptions) {
        this.rideRepository = new RideRepository();
        this.cabSubscriptions=cabSubscriptions ;
    }

    public double calculateFare(double distance, int time) {
        if (CabSubscriptions.NORMAL.equals(this.cabSubscriptions)) {
            Double totalFare = distance * MIN_COST_PER_KMS_NORMAL + time * COST_PER_TIME_NORMAL;
            if (totalFare < MIN_FARE_NORMAL)
                return MIN_FARE_NORMAL;
            return totalFare;
        }
        if (CabSubscriptions.PREMIUM.equals(this.cabSubscriptions)) {
            Double totalFare = distance * MIN_COST_PER_KMS_PREMIUM + time * COST_PER_TIME_PREMIUM;
            if (totalFare < MIN_FARE_PREMIUM)
                return MIN_FARE_PREMIUM;
            return totalFare;
        }
        return 0.0;
    }


    public InvoiceSummary calculateFare(Ride[] rides) {
        for (Ride ride : rides) {
            totalFare += this.calculateFare(ride.distance, ride.time);
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
