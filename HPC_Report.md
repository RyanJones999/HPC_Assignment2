<center>

# HPC Assignment 2

</center>

## Introduction
This is my introduction

## Task 1
This code implements a simple, single threaded matrix multiplication, fulfilling the requirement of performing computations on large matrices (1000x1000) with randomly generated numbers. The core objective is to multiply two such matrices and then multiply the result with a third matrix, demonstrating a straightforward approach to matix multiplication without utilizing multi-threading or other complex optimization techniques.

The process begins with the generation of matrices:

~~~java
double[][] matrix1 = generateMatrix();
double[][] matrix2 = generateMatrix();
~~~
Here `generateMatrix` is a method that created a 1000x1000 matrix filled with random numbers. It uses Java's `Random` class to populate each element of the matrix.
~~~java
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
~~~
The core of the task is the matrix multiplication, achieved through nested loops:
~~~java
private static double[][] multiply(double[][] matrix1, double[][] matrix2) {
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
~~~
This method multiplies two matricies in a conventional manner, summing the products of corresponding elements in the rows of the first matrix and columns of the second. The implementation focuses on clarity rather than performance optimizations, aligning with the task's aim to establish a gold standard for matrix multiplication. The absence of multi-threading ensures that the method serves as a reliable baseline for comparing more complex implementations.