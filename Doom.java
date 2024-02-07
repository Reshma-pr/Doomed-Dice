package Securin;

import java.util.Arrays;

public class Doom {

    public static double[] calculateProbabilities(int[] dieA, int[] dieB) {
        // Combine loops and use a frequency table for efficiency
        int[] counts = new int[11];
        for (int faceA : dieA) {
            for (int faceB : dieB) {
                counts[faceA + faceB - 2]++;
            }
        }

        // Directly calculate probabilities from counts and normalize
        double totalOutcomes = dieA.length * dieB.length;
        double[] probabilities = new double[11];
        for (int sum = 2; sum <= 12; sum++) {
            probabilities[sum - 2] = (double) counts[sum - 2] / totalOutcomes;
        }

        return probabilities;
    }


    public static int[] adjustDieB(int[] dieB, double[] targetProbabilities, int maxSum, double tolerance, int maxIterations) {
        double[] originalProbabilities = calculateProbabilities(new int[]{}, dieB);
        for (int iter = 0; iter < maxIterations; iter++) {
            for (int i = 0; i < dieB.length; i++) {
                int originalFaceB = dieB[i];
                for (int j = 1; j < maxSum; j++) {
                    dieB[i] = j;
                    double[] newProbabilities = calculateProbabilities(new int[]{}, dieB);
                    boolean withinTolerance = true;
                    for (int k = 0; k < newProbabilities.length; k++) {
                        if (Math.abs(newProbabilities[k] - targetProbabilities[k]) >= tolerance) {
                            withinTolerance = false;
                            break;
                        }
                    }
                    if (withinTolerance) return dieB;
                }
                dieB[i] = originalFaceB;
            }
        }
        return null;
    }


    public static int[][] undoomDice(int[] dieA, int[] dieB) {
        int maxSum = 4 + 4;
        double[] targetProbabilities = calculateProbabilities(dieA, dieB);
        int[] newDieA = new int[dieA.length];
        for (int i = 0; i < dieA.length; i++) {
            newDieA[i] = Math.min((maxSum - dieA[i]) / 2, 4);
        }
        int[] newDieB = adjustDieB(Arrays.copyOf(dieB, dieB.length), targetProbabilities, maxSum, 1e-6, 1000);
        double[] newProbabilities = calculateProbabilities(newDieA, newDieB);


        System.out.println("Original Probability: " + Arrays.toString(targetProbabilities));
        System.out.println("New Probability: " + Arrays.toString(newProbabilities));

        return new int[][]{newDieA, newDieB};
    }

    public static void main(String[] args) {
        int[] dieA = {1, 2, 3, 4, 5, 6};
        int[] dieB = {1, 2, 3, 4, 5, 6};

        int[][] result = undoomDice(dieA, dieB);
        int[] newDieA = result[0];
        int[] newDieB = result[1];


        System.out.println("Original Die A: " + Arrays.toString(dieA));
        System.out.println("Original Die B: " + Arrays.toString(dieB));
        System.out.println("New Die A: " + Arrays.toString(newDieA));
        System.out.println("New Die B: " + Arrays.toString(newDieB));
    }
}
