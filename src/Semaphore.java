public class Semaphore {
    public static int queue;
//constructors
    Semaphore(int num){queue=num;}
 //methods
    public synchronized void P(Client ob){
        queue--;
        if(queue<0) {
            try {
                System.out.println(ob.getName() + " arrived and waiting");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println(ob.getName() + " arrived");
        }
    }
    public synchronized void V() {
        queue++;
        if (queue <= 0)
            notify();
    }
}
