import java.util.ArrayList;

public class TestFire {

    public static Integer resura = 0;
    public static char[] test = new char[1000];


    public static void main(String args[]){

        FirAlb a;
        FirNegru n;
        ArrayList<Thread> threads  = new ArrayList<>();

        for(int i = 0 ; i < 100 ; i++){
            a = new FirAlb(10);
            n = new FirNegru(10);
            threads.add(a);
            threads.add(n);
        }

        for(Thread t : threads){
            t.start();
        }
        System.out.println("Resursa are valoarea : " + resura);
    }

}
