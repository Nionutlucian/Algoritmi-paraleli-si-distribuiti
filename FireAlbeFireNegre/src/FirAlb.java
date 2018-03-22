public class FirAlb extends Thread {

    private int prioritate;

    FirAlb(int prioritate){
        this.prioritate = prioritate;
        this.setPriority(prioritate);
    }


    @Override
    public void run() {
        super.run();

        synchronized (TestFire.resura){
            TestFire.resura++;
            System.out.println(this.getName() + " a accesat resursa si a incrementat-o, resura: " + TestFire.resura + " si are prioritatea: " + this.getPriority());
            this.setPriority(prioritate--);
        }
    }
}
