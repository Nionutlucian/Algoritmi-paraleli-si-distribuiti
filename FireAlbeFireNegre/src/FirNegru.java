public class FirNegru extends Thread {

    private int prioritate;

    FirNegru(int prioritate){
        this.prioritate = prioritate;
        this.setPriority(prioritate);
    }


    @Override
    public void run() {
        super.run();

        synchronized (TestFire.resura){
            TestFire.resura--;
            System.out.println(this.getName() + " a accesat resursa si a decrementat-o, resura: " + TestFire.resura + " si are prioritatea: " + this.getPriority());
            this.setPriority(prioritate--);
        }
    }
}
