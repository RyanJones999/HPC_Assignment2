package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Task4 {

    private static final int SIZE = 1000;
    private static final double FAILURE_PROBABILITY = 0.1; // 10% chance of failure for each thread

    public static void main(String[] args) throws InterruptedException {
        // Generating matrices
        double[][] matrix1 = generateMatrix();
        double[][] matrix2 = generateMatrix();
        double[][] matrix3 = generateMatrix();

        // Perform the first matrix multiplication
        double[][] result1 = performMultiplication(matrix1, matrix2);

        // Perform the second matrix multiplication
        double[][] finalResult = performMultiplication(result1, matrix3);

        // Print a portion of the final result for verification
        printMatrixPortion(finalResult);
    }

    private static double[][] performMultiplication(double[][] matrix1, double[][] matrix2) throws InterruptedException {
        double[][] result = new double[SIZE][SIZE];
        List<Thread> threads = new ArrayList<>();
        Random random = new Random();

        for (int row = 0; row < SIZE; row++) {
            final int currentRow = row;
            Thread thread = new Thread(() -> {
                if (random.nextDouble() > FAILURE_PROBABILITY) {  // Thread succeeds
                    for (int col = 0; col < SIZE; col++) {
                        for (int k = 0; k < SIZE; k++) {
                            result[currentRow][col] += matrix1[currentRow][k] * matrix2[k][col];
                        }
                    }
                } else {
                    // Simulate thread (node) failure
                    System.out.println("Thread for row " + currentRow + " failed.");
                }
            });
            threads.add(thread);
            thread.start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            thread.join();
        }

        return result;
    }

    private static double[][] generateMatrix() {
        double[][] matrix = new double[SIZE][SIZE];
        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                matrix[i][j] = random.nextDouble();
            }
        }
        return matrix;
    }

    // Method to print a portion of a matrix
    private static void printMatrixPortion(double[][] matrix) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.printf("%.2f ", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println("... (Matrix continues)");
    }
}
