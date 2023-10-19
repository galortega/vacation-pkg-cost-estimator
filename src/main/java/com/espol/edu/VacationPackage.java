package com.espol.edu;

import java.util.Map;
import java.util.HashMap;

public class VacationPackage {
  private String destination;
  private int numTravelers;
  private int duration;
  private int baseCost = 1000;
  private Map<String, Integer> popularTouristSpots = new HashMap<String, Integer>() {
    {
      put("Paris", 500);
      put("New York City", 600);
    }
  };
  private Map<Integer, Double> groupDiscounts = new HashMap<Integer, Double>() {
    {
      put(4, 0.1);
      put(10, 0.2);
    }
  };
  private int penaltyFee = 200;
  private Map<String, Integer> promotionPolicy = new HashMap<String, Integer>() {
    {
      put("duration", 30);
      put("numPassengers", 2);
      put("discount", 200);
    }
  };
  private int maxGroupSize = 80;

  public VacationPackage(final String destination, final int numTravelers, final int duration) {
    this.destination = destination;
    this.numTravelers = numTravelers;
    this.duration = duration;
  }

  public final int calculateCost() {
    // Check if input data is valid
    if (numTravelers <= 0 || duration <= 0 || numTravelers > maxGroupSize || destination == null || destination.isEmpty()) {
      return -1;
    }

    // Calculate base cost
    int totalCost = baseCost;

    // Add additional costs based on destination
    if (popularTouristSpots.containsKey(destination)) {
      totalCost += popularTouristSpots.get(destination);
    }

    // Add penalty fee if duration is less than 7 days
    if (duration < 7) {
      totalCost += penaltyFee;
    }

    // Subtract promotion discount if duration is more than 30 days or numPassengers
    // is 2
    if (duration > promotionPolicy.get("duration") || numTravelers == promotionPolicy.get("numPassengers")) {
      totalCost -= promotionPolicy.get("discount");
    }

    // Apply group discount based on number of travelers
    for (int groupSize : groupDiscounts.keySet()) {
      if (numTravelers > groupSize) {
        totalCost *= (1 - groupDiscounts.get(groupSize));
        break;
      }
    }

    return totalCost;
  }
}