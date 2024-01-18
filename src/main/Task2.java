package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Task2 {

    private static final int SIZE = 1000;

    public static void main(String[] args) throws InterruptedException {
        // Number of threads can be set here or obtained from user input
        int threadCount = 8; // Example: using 8 threads

        // Generating matrices
        double[][] matrix1 = generateMatrix();
        double[][] matrix2 = generateMatrix();
        double[][] matrix3 = generateMatrix();

        // Performing single-threaded matrix multiplication (TASK 1 approach)
        double[][] resultTask1 = singleThreadMultiply(matrix1, matrix2);
        resultTask1 = singleThreadMultiply(resultTask1, matrix3);

        // Performing multi-threaded matrix multiplication (TASK 2 approach)
        double[][] resultTask2 = multiThreadMultiply(matrix1, matrix2, threadCount);
        resultTask2 = multiThreadMultiply(resultTask2, matrix3, threadCount);

        // Verifying results
        if (verifyResults(resultTask1, resultTask2)) {
            System.out.println("Verification Successful: Multi-threaded results match the gold standard.");
        } else {
            System.out.println("Verification Failed: Results do not match.");
        }
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

    private static double[][] singleThreadMultiply(double[][] matrix1, double[][] matrix2) {
        double[][] result = new double[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                for (int k = 0; k < SIZE; k++) {
                    result[row][col] += matrix1[row][k] * matrix2[k][col];
                }
            }
        }
        return result;
    }

    static double[][] multiThreadMultiply(double[][] matrix1, double[][] matrix2, int threadCount) throws InterruptedException {
        double[][] result = new double[SIZE][SIZE];
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            final int startRow = i * SIZE / threadCount;
            final int endRow = (i + 1) * SIZE / threadCount;

            Thread thread = new Thread(() -> {
                for (int row = startRow; row < endRow; row++) {
                    for (int col = 0; col < SIZE; col++) {
                        for (int k = 0; k < SIZE; k++) {
                            result[row][col] += matrix1[row][k] * matrix2[k][col];
                        }
                    }
                }
            });

            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        return result;
    }

    private static boolean verifyResults(double[][] matrix1, double[][] matrix2) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (Math.abs(matrix1[i][j] - matrix2[i][j]) > 1e-9) {
                    return false;
                }
            }
        }
        return true;
    }
}
