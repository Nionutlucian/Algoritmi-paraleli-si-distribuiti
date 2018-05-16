package com.java;
import mpi.MPI;

public class RoyFloyd {

    private static final int NR = 5;
    private static final int INF = 99999;

    public static void main(String[] args) {

        MPI.Init(args);


        int[][] buffer_result = new int[NR][NR];
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        int buffer[][] = {
                {0, 5, INF, 3, INF},
                {INF, 0, 6, INF, 4},
                {INF, INF, 0, 9, INF},
                {INF, INF, INF, 0, INF},
                {INF, INF, INF, 2, 0}
            };

        if (rank == 0) {
            MPI.COMM_WORLD.Bcast(buffer, 0, NR * NR, MPI.INT, 0);

            for (int i = 0; i < NR; i++) {
                for (int j = 0; j < NR; j++) {
                    if (buffer[0][i] + buffer[i][j] < buffer[0][j]) {
                        buffer[0][j] = buffer[0][i] + buffer[i][j];
                    }
                }
            }
        }
        if (rank != 0) {
            MPI.COMM_WORLD.Recv(buffer, 0, NR, MPI.OBJECT, 0, 0);
                for (int i = 0; i < NR; i++) {
                    for (int j = 0; j < NR; j++) {
                        if (buffer[rank][i] + buffer[i][j] < buffer[rank][j]) {
                            buffer[rank][j] = buffer[rank][i] + buffer[i][j];
                        }
                    }
                }
                MPI.COMM_WORLD.Alltoall(buffer, 0, NR, MPI.OBJECT, buffer, 0, NR, MPI.OBJECT);
            }
            if (rank == 0) {

                for (int i = 1; i < size; i++) {
                    MPI.COMM_WORLD.Recv(buffer, 0, NR, MPI.OBJECT, i, 0);
                    for (int j = i; j < buffer.length; j++) {
                        for (int k = 0; k < buffer.length; k++) {
                            buffer_result[j][k] = buffer[j][k];
                        }
                    }
                }

                System.out.println("Matricea finala este:");
                for (int i = 0; i < NR; i++) {
                    for (int j = 0; j < NR; j++) {
                        System.out.print(buffer_result[i][j] + "   ");
                    }
                }
        }
        MPI.Finalize();
    }
}
