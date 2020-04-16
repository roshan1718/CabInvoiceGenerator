package com.bridgelabz.cabInvoiceGenerator;

import org.junit.Assert;
import org.junit.Test;

public class InvoiceServiceTest {

    InvoiceService invoiceService=new InvoiceService();

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare(){
        double distance= 2.0;
        int time=5;
        double fare=invoiceService.calculateFare(distance,time);
        Assert.assertEquals(25,fare,0.0);
    }

    @Test
    public void givenDistanceAndTime_ShouldReturnMinimumFare(){
        double distance= 0.1;
        int time=1;
        double fare=invoiceService.calculateFare(distance,time);
        Assert.assertEquals(5,fare,0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        Ride[] rides = {
                new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        InvoiceSummary summary = invoiceService.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2.0, 30);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }
    @Test
    public void givenUserIDAndRides_ShouldReturnInvoiceSummary(){
        String userID="abc@xyz.com";
        Ride[] rides = {new Ride(2.0,5),
                new Ride(0.1,1)};
        invoiceService.addRide(userID, rides);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userID);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2 , (int) 30.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

}
