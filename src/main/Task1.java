package main;

import java.util.Random;

public class Task1 {

    private static final int SIZE = 1000;

    public static void main(String[] args) {
        // Generating matrices
        double[][] matrix1 = generateMatrix();
        double[][] matrix2 = generateMatrix();
        double[][] matrix3 = generateMatrix();

        // Performing matrix multiplication
        double[][] result1 = multiply(matrix1, matrix2);
        double[][] finalResult = multiply(result1, matrix3);

        // Displaying matrices
        System.out.println("Matrix 1:");
        printMatrixPortion(matrix1);
        System.out.println("Matrix 2:");
        printMatrixPortion(matrix2);
        System.out.println("Matrix 3:");
        printMatrixPortion(matrix3);
        System.out.println("Result of Multiplication:");
        printMatrixPortion(finalResult);
    }

    // Method to generate a matrix filled with random numbers
    static double[][] generateMatrix() {
        double[][] matrix = new double[SIZE][SIZE];
        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                matrix[i][j] = random.nextDouble();
            }
        }
        return matrix;
    }

    // Method to perform matrix multiplication
    static double[][] multiply(double[][] matrix1, double[][] matrix2) {
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

    // Method to print a portion of a matrix
    private static void printMatrixPortion(double[][] matrix) {
        for (int i = 0; i < 10; i++) {  // Limiting to the first 10 rows
            for (int j = 0; j < 10; j++) {  // Limiting to the first 10 columns
                System.out.printf("%.2f ", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println("...");  // Indicate that this is only a portion
    }
}
