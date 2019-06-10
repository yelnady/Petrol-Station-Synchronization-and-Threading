//thread class
public class Client extends Thread {
    public static PetrolStation sharedStation;
    public boolean enter = false;
    public int assignedPump ;
    Client(String clientName, PetrolStation ps) {
        super(clientName); //the name of this thread which is the client name
        sharedStation = ps;
    }

    public void run() {
        //to run this client, you should see if any has the ability to let us enter to the pumps
        //we do this using the pump semaphore which is common across all of the petrolStation

        sharedStation.pump.P(this); /* as a start first till the semaphore that a new one has arrived */
        try {
            assignedPump=sharedStation.occupy(this); //occupy is a synchronized so not two client enter at the same time to get pump
        }
        catch (InterruptedException e) {}
       // if (this.enter) {
            try {
                /* Problem that he could ignore the occupy then it will be pump 0 as default so error no pump 0 */
                System.out.println("pump " + assignedPump + ": " + this.getName() + sharedStation.serve());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("pump " + assignedPump + ": " + this.getName() + sharedStation.pay());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("pump " +assignedPump + ": " + this.getName() + sharedStation.leave(this));
       // }
        sharedStation.pump.V();
    }
}
