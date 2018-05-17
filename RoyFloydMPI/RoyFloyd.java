package com.java;

import mpi.MPI;

public class RoyFloyd {

    private static final int NR = 4;
    private static final int INF = 99999;


    public static int[][] readBuffer() {
        int buffer[][] = {
                {0, 5, INF, 10},
                {INF, 0, 3, INF},
                {INF, INF, 0, 1,},
                {INF, INF, INF, 0}
        };

        System.out.println(buffer[0].length);

        return buffer;
    }


    public static void main(String[] args) {

        MPI.Init(args);
        int buffer[][] = new int[NR][NR];
        int recv_buffer[][] = new int[NR][NR];
        int rank = MPI.COMM_WORLD.Rank();

        if (rank == 0) {
            buffer = readBuffer();
        }

        MPI.COMM_WORLD.Bcast(buffer, 0, NR, MPI.OBJECT, 0);


        for (int i = 0; i < NR; i++) {
            for (int j = 0; j < NR; j++) {
                if (buffer[rank][i] + buffer[i][j] < buffer[rank][j]) {
                    buffer[rank][j] = buffer[rank][i] + buffer[i][j];
                }
            }

        }

        MPI.COMM_WORLD.Alltoall(buffer[rank],0, NR-1, MPI.INT, recv_buffer[rank],0 , NR-1, MPI.OBJECT);

        if (rank == 0) {
            System.out.println("Matricea finala este:");
            for (int i = 0; i < NR; i++) {
                for (int j = 0; j < NR; j++) {
                    System.out.print(recv_buffer[i][j] + "   ");
                }
                System.out.println();
            }
        }

    }
}

