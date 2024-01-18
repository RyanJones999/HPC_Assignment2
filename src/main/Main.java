package main;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        // Task 1 and Task 2 Setup
        System.out.print("Enter the number of threads for the multi-threaded multiplication: ");
        int threadCount = scanner.nextInt();

        System.out.println("Generating matrices for Task 1 and Task 2...");
        double[][] matrix1 = Task1.generateMatrix();
        double[][] matrix2 = Task1.generateMatrix();
        double[][] matrix3 = Task1.generateMatrix();

        // Perform and compare Task 1 and Task 2
        performAndCompareTask1AndTask2(matrix1, matrix2, matrix3, threadCount);

        // Task 3: Network Simulation
        System.out.println("\nPerforming Task 3: Network Simulation with Delay...");
        Task3.main(new String[]{}); // Assuming Task3 has a main method

        // Task 4: Node Failure Simulation
        System.out.println("\nPerforming Task 4: Node Failure Simulation...");
        Task4.main(new String[]{}); // Assuming Task4 has a main method

        scanner.close();
    }

    private static void performAndCompareTask1AndTask2(double[][] matrix1, double[][] matrix2, double[][] matrix3, int threadCount) throws InterruptedException {
        // Task 1: Single-threaded matrix multiplication
        System.out.println("Performing single-threaded matrix multiplication (Task 1)...");
        long startTime = System.currentTimeMillis();
        double[][] singleThreadedResult = Task1.multiply(matrix1, matrix2);
        singleThreadedResult = Task1.multiply(singleThreadedResult, matrix3);
        long endTime = System.currentTimeMillis();
        System.out.println("Single-threaded multiplication completed in " + (endTime - startTime) + " ms.");

        // Task 2: Multi-threaded matrix multiplication
        System.out.println("Performing multi-threaded matrix multiplication (Task 2)...");
        startTime = System.currentTimeMillis();
        double[][] multiThreadedResult = Task2.multiThreadMultiply(matrix1, matrix2, threadCount);
        multiThreadedResult = Task2.multiThreadMultiply(multiThreadedResult, matrix3, threadCount);
        endTime = System.currentTimeMillis();
        System.out.println("Multi-threaded multiplication completed in " + (endTime - startTime) + " ms.");

        // Compare results
        System.out.println("Comparing results of Task 1 and Task 2...");
        if (verifyResults(singleThreadedResult, multiThreadedResult)) {
            System.out.println("Verification successful: The results match.");
        } else {
            System.out.println("Verification failed: The results do not match.");
        }
    }

    private static boolean verifyResults(double[][] matrix1, double[][] matrix2) {
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[0].length; j++) {
                if (Math.abs(matrix1[i][j] - matrix2[i][j]) > 1e-9) {
                    return false;
                }
            }
        }
        return true;
    }
}
