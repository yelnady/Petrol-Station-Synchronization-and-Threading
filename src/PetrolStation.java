import static java.lang.Thread.sleep;


public class PetrolStation {

    public boolean[] pumparray;
    public int nPumps;
    public Semaphore pump;
    public int nOccupied;


    PetrolStation(int x) { //gets the number of pumps and create a semaphore with these pumps (shared resource)
        //we have pumparray to check if a place is available or not.
        nPumps = x;
        pump = new Semaphore(nPumps);
        pumparray = new boolean[nPumps];
    }


    //methods
    public synchronized int occupy(Client ob) throws InterruptedException {

        for (int i = 0; i < nPumps; i++) {
            if (pumparray[i] == false) { /* means It's available for being occupied */
                nOccupied++;
                ob.assignedPump = i+1;
                System.out.println("pump " + ob.assignedPump + ": " + ob.getName() + " occupied");
                pumparray[i] = true;
                sleep(1000);
                break;
            }
        }
        return ob.assignedPump;
    }
    public String serve() throws InterruptedException {
        String val = " being served";
        sleep(1000);
        return val;
    }
    public String pay() throws InterruptedException {
        sleep(1000);
        return (" paying");
    }
    public synchronized String leave(Client ob) {

        { /* means It's available for being occupied */
            nOccupied--;
            pumparray[ob.assignedPump-1] = false;
        }
       //no need for notify here as it already called from the client after finishing the leave function
        notify();
        return " leave";
    }
}
