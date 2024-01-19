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

## Task 2
The code for this task implements a multi-threaded version of matrix multiplication, addressing the need for enhanced performance compared to the single-threaded approach (Task 1). This implementation effectively utilizes multiple threads to parallelize the computation, thereby aiming to expedite the matrix multiplication process.

The core feature of this task is the ability to dynamically set the number of threads through user input, which is illustrated in the following snippet:

~~~java
System.out.print("Enter the number of threads for the multi-threaded multiplication: ");
        int threadCount = scanner.nextInt();
~~~

This flexibility allows for adjusting the computational power based on the available resources or specific requirements

The matrix multiplication is handled by dividing the task among multiple threads. Each thread is responsible for computing a portion of the resultant matrix. This division is exemplified in the following code:

~~~java
for (int i = 0; i < threadCount; i++) {
    final int startRow = i * SIZE / threadCount;
    final int endRow = (i + 1) * SIZE / threadCount;
    Thread thread = new Thread(() -> {
        for (int row = startRow; row < endRow; row++) {
            // Perform row multiplication
        }
    });
    threads.add(thread);
    thread.start();
}
~~~
In this approach, the matrix is partitioned row-wise among the threads, with each thread calculating a specific range of rows. This method ensures that the workload is evenly distributed and allows for concurrent excecution.

Thread safety is implicitly maintained as each thread works on a distinct part of the result matrix, thus avoiding race conditions. After all threads have completed their tasks, the final matrix is assembled, ensuring that the computation is complete.

Finally, the results of the multi-threaded implementation are compared against the gold standard (single-threaded implementation) to verify correctness. This comparison is crucial to ensure that the parallelization does not compromise the accuracy of the results.

~~~java
if (verifyResults(singleThreadedResult, multiThreadedResult)) {
    System.out.println("Verification Successful: Results match.");
}
~~~
In summary, TASK 2's implementation leverages multi-threading to enhance the efficiency of matrix multiplication. The ability to set the number of threads dynamically, coupled with a well-considered distribution of the workload and attention to thread safety, makes this implementation a robust solution for large-scale matrix operations. The final verification step with the gold standard is essential to ensure both speed and accuracy.

## Task 3

The code for this task simulates a distributed network environment for matrix multiplication. The goal is to reflect the behavior and challenges that present themselves in a distributed system, such as network delays and the management of multiple computational units (or threads in this case). The code achieves these objectives through several key features.

#### Thread Management:
Unlike Task 2, which focuses on parallelizing computations across multiple threads, Task 3 introduces the concept of simulating a network environment. Each thread represents an independant computational node in a distributed system. The creation and management of threads are done manually to closely simulate the distributed nature of thie task.

~~~java
List<Thread> threads = new ArrayList<>();
for (int row = 0; row < SIZE; row++) {
    final int currentRow = row;
    Thread thread = new Thread(() -> {
        // Thread execution logic
    });
    threads.add(thread);
    thread.start();
}
~~~
In this snippet, a thread is created for each row of the matrix, mimicking the behaviour of distributed  nodes. Each thread then carries out its assigned part of the matrix multiplication.

#### Simulating Network Delays:

In real-world distributed systems, network latency is a common challenge. To simulate this, a delay is introduced in each thread using `Thread.sleep(200ms)`. This delay represents the time taken for a message to travel across the network or the processing time at each node.
~~~java
Thread.sleep(200); // Simulating network delay
~~~
The inclusion of this delay is crucial for understanding the impact of network latency on the overall performance and efficiency of distributed computations.

#### Matrix Multiplication Logic
The core computational task is the multiplication of matrices, which is performed by each thread. This operation is representative of a typical workload in a distributed system.

~~~java
for (int col = 0; col < SIZE; col++) {
    for (int k = 0; k < SIZE; k++) {
        result[currentRow][col] += matrix1[currentRow][k] * matrix2[k][col];
    }
}
~~~
Each thread computes a single row of the resultant matrix, ensuring that the workload is evenly distributed. This setup helps to prevent any single thread (or node) from becoming a bottleneck.

#### Ensuring Thread Completion

After starting all threads, the code ensures that each thread completes its task by calling `thread.join()`. This is essential to make sure that the final matrix is fully computed before proceeding.

~~~java
for (Thread thread : threads) {
    thread.join();
}
~~~
This step is analogous to synchronising distributed nodes to ensure that all parts of a distributed task have been completed before moving on to the next step.

In summary, this implementation successfully simulates a distributed network environment for matrix multiplication. It incorportates key aspects of distributed computing, such as network delay simulation, manual thread mmanagement, workload distribution, and thread synchronization. This simulation helps in understanding the complexities and performance implications of distributing computational tasks across multiple nodes in a network, a common scenario in modern computing paradigms like cloud computing and distributed data processing.