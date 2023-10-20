// Copyright (C) 2020
// All rights reserved
package com.espol.edu;

import java.util.Map;

public class VacationPackage {
  /**
   * Base cost of vacation package.
   */
  private static final int BASE_COST = 1000;
  /**
   * Penalty fee for vacations less than 7 days.
   */
  private static final int PENALTY_FEE = 200;
  /**
   * Maximum number of travelers allowed in a group.
   */
  private static final int MAX_GROUP_SIZE = 80;
  /**
   * Duration threshold for penalty fee.
   */
  private static final int DURATION_THRESHOLD = 7;
  /**
   * Number of travelers in the group.
   */
  private final String destination;
  /**
   * * Number of travelers in the group.
   */
  private final int duration;
  /**
   * Number of travelers in the group.
   */
  private final int numTravelers;
  /**
   * Map of popular tourist spots and their additional costs.
   */
  private final Map<String, Integer> popularTouristSpots = Map.of(
      "Paris", 500,
      "New York City", 600);

  /**
   * Constructor for VacationPackage class.
   * @param dest       Destination of the vacation package.
   * @param nTravelers Number of travelers in the group.
   * @param dur        Duration of the vacation package (in days).
   */
  public VacationPackage(
      final String dest, final int nTravelers, final int dur) {
    this.destination = dest;
    this.numTravelers = nTravelers;
    this.duration = dur;
  }

  /**
   * Calculates the total cost of the vacation package.
   * @return Total cost of the vacation package.
   */
  public final int calculateCost() {
    // Check if input data is valid
    if (numTravelers <= 0 || duration <= 0
        || numTravelers > MAX_GROUP_SIZE || destination == null
        || destination.isEmpty()) {
      return -1;
    }

    // Calculate base cost
    int totalCost = BASE_COST;

    // Add additional costs based on destination
    switch (destination) {
      case "Paris":
        totalCost += popularTouristSpots.get("Paris");
        break;
      case "New York City":
        totalCost += popularTouristSpots.get("New York City");
        break;
      default:
        break;
    }

    // Apply penalty fee if duration is less than 7 days
    if (duration < DURATION_THRESHOLD) {
      totalCost += PENALTY_FEE;
    }
    // Apply promotion discount if duration is greater than 30 days
    if (duration > PromotionPolicy.DURATION_THRESHOLD
        || numTravelers == PromotionPolicy.NUM_PASSENGERS_THRESHOLD) {
      totalCost -= PromotionPolicy.DISCOUNT_AMOUNT;
    }

    // Apply group discount based on number of travelers
    if (numTravelers > GroupDiscountPolicy.SMALL_GROUP_SIZE_THRESHOLD) {
      totalCost *= GroupDiscountPolicy.LARGE_GROUP_DISCOUNT;
    } else if (numTravelers > GroupDiscountPolicy.MEDIUM_GROUP_SIZE_THRESHOLD) {
      totalCost *= GroupDiscountPolicy.MEDIUM_GROUP_DISCOUNT;
    } else if (numTravelers > GroupDiscountPolicy.SMALL_GROUP_SIZE_THRESHOLD) {
      totalCost *= GroupDiscountPolicy.SMALL_GROUP_DISCOUNT;
    }

    return totalCost;
  }

  private static final class PromotionPolicy {
    /**
     * Duration threshold for promotion discount.
     */
    private static final int DURATION_THRESHOLD = 30;
    /**
     * Number of passengers threshold for promotion discount.
     */
    private static final int NUM_PASSENGERS_THRESHOLD = 2;
    /**
     * Amount of promotion discount.
     */
    private static final int DISCOUNT_AMOUNT = 200;
  }

  private static final class GroupDiscountPolicy {
    /**
     * Number of passengers threshold for small group discount.
     */
    private static final int SMALL_GROUP_SIZE_THRESHOLD = 4;
    /**
     * Number of passengers threshold for medium group discount.
     */
    private static final int MEDIUM_GROUP_SIZE_THRESHOLD = 10;
    /**
     * Number of passengers threshold for large group discount.
     */
    private static final double SMALL_GROUP_DISCOUNT = 0.1;
    /**
     * Number of passengers threshold for large group discount.
     */
    private static final double MEDIUM_GROUP_DISCOUNT = 0.2;
    /**
     * Number of passengers threshold for large group discount.
     */
    private static final double LARGE_GROUP_DISCOUNT = 0.3;
  }
}
