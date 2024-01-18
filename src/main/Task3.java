package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Task3 {

    private static final int SIZE = 1000;
    private static final int DELAY_MS = 200; // Delay of 200ms to simulate network latency

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

        for (int row = 0; row < SIZE; row++) {
            final int currentRow = row;
            Thread thread = new Thread(() -> {
                try {
                    // Simulating network delay
                    Thread.sleep(DELAY_MS);

                    for (int col = 0; col < SIZE; col++) {
                        for (int k = 0; k < SIZE; k++) {
                            result[currentRow][col] += matrix1[currentRow][k] * matrix2[k][col];
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
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
        for (int i = 0; i < 10; i++) {  // Print first 10 rows
            for (int j = 0; j < 10; j++) {  // Print first 10 columns
                System.out.printf("%.2f ", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println("... (Matrix continues)");
    }
}
