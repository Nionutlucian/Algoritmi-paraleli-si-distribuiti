import mpi.MPI;

import java.util.Vector;

public class SumaNumerelorVector {

    private static int v[] = new int[10];
    final static int MASTER = 0;
    private static int suma;
    private static int sumaPartiala[] = new int[6];
    private static int s[] = new int[5];

    final static int NUMAR_PROCESE = 5;


    public static void initializareVector(){
        for(int i = 0;i < 10;i++){
            v[i] = i;
        }
    }

    public static void main(String args[]){

        MPI.Init(args);
        int procId = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        if(procId == MASTER){
            initializareVector();

            for(int i = 0;i < NUMAR_PROCESE;i++){

                MPI.COMM_WORLD.Isend(v,procId*(int)v.length/NUMAR_PROCESE,(procId + 1)*(int)v.length/NUMAR_PROCESE,MPI.INT,i,1);
                MPI.COMM_WORLD.Irecv(sumaPartiala,procId*(int)v.length/size,(procId + 1)*(int)v.length/size,MPI.INT,procId,1);
                suma += sumaPartiala[i];
                System.out.println("Suma totala este: " + suma);
            }
        }

        else{
            int a[] = new int[2];
            MPI.COMM_WORLD.Irecv(a,0,2,MPI.INT,procId,1);
            for(int i = 0; i < a.length;i++ ){
                sumaPartiala[procId]+=a[i];
            }
            MPI.COMM_WORLD.Isend(sumaPartiala,procId,procId+1,MPI.INT,MASTER,1);
        }

        MPI.Wtime();



        MPI.Finalize();



    }

}
