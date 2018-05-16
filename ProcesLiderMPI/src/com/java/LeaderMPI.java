package com.java;

import mpi.MPI;

import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class LeaderMPI {

	private static final int master = 0;

	public static void main(String[] args) {


		MPI.Init(args);
		int rank = MPI.COMM_WORLD.Rank();
		int size = MPI.COMM_WORLD.Size();

		int[] sendArray = new int[2];

		if (rank > master) {
			Random rand = new Random();
			sendArray[0] = rand.nextInt();
			sendArray[1] = rank;
			MPI.COMM_WORLD.Send(sendArray, 0, 2, MPI.INT, master, 0);
		}
		if (rank == 0) {
			HashMap<Integer, Integer> map = new HashMap<>();

			for (int dest = 1; dest < size; dest++) {
				MPI.COMM_WORLD.Recv(sendArray, 0, 2, MPI.INT, dest, 0);
				map.put(sendArray[1], sendArray[0]);
			}
			int max = (Collections.max(map.values()));
			for (Integer i : map.keySet()) {
				if (map.get(i) == max) {
					System.out.println("Procesul leader este: " + i
							+ " valoarea maxima: " + max);
				}
			}
		}

		MPI.Finalize();

	}

}
