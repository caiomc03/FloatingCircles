package src;

import java.util.Random;
import java.awt.*;
import java.util.List;

public class helper {

    public static boolean oddIfStatement(double probability) {
        Random random = new Random();

        double randomNumber = random.nextDouble();

        if (randomNumber <= probability) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPointInsideCircle(double centerX1, double centerY1, double radius, double centerX2,
            double centerY2) {
        // Calculate the distance between the center of the circle and the given point
        double distance = Math.sqrt(Math.pow(centerX2 - centerX1, 2) + Math.pow(centerY2 - centerY1, 2));

        // Check if the distance is less than the radius
        return distance <= radius;
    }

    public static Color changeRed(Color color, int incrementValue) {
        int originalRed = color.getRed();
        int incrementedRed = originalRed + incrementValue;

        // Ensure the incremented value is within the valid range of 0-255
        incrementedRed = Math.min(incrementedRed, 255);
        incrementedRed = Math.max(incrementedRed, 0);

        // Create and return a new Color object with the incremented green value
        return new Color(incrementedRed, color.getGreen(), color.getBlue());
    }

    public static double calculateProbability(double x, double mean, double standardDeviation) {
        double coefficient = 1.0 / (standardDeviation * Math.sqrt(2 * Math.PI));
        double exponent = -Math.pow(x - mean, 2) / (2 * Math.pow(standardDeviation, 2));
        return coefficient * Math.exp(exponent);
    }

    public static double GaussianRegression(double input) {

        double mean = input; // Mean of the Gaussian distribution
        double standardDeviation = 1.5; // Standard deviation of the Gaussian distribution

        // Create a random number generator
        Random random = new Random();

        // Generate a number around the input using a Gaussian distribution
        double generatedNumber = random.nextGaussian() * standardDeviation + mean;

        return generatedNumber;
    }

    public static double performMutation(double value) {

        double g_value = Double.parseDouble(String.valueOf(value));
        double result = (GaussianRegression(g_value));

        return result;

    }

    public static double calculateDistance(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;

        return Math.sqrt(dx * dx + dy * dy);
    }

    public static double calculateMean(List<Double> values) {
        double lenght = values.size();
        double sum = 0;
        for (Double value : values) {
            sum += value;
        }

        return sum / lenght;
    }

}
