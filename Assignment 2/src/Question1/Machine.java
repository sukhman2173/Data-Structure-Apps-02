//Sukhman Singh 18041216 
package Question1;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Machine implements Runnable
{
        
    public static int MIN_CONSUMPTION_TIME = 300;
    public static int MAX_CONSUMPTION_TIME = 1000;
    public static int MIN_PRODUCTION_TIME = 300;
    public static int MAX_PRODUCTION_TIME = 1000;
    
    private ArrayList<ConveyorBelt> beltArray;
    private boolean isAlive;
    private Random rand;
    
    public Machine(ArrayList<ConveyorBelt> array)
    {
        beltArray = array;
        isAlive = true;
        rand = new Random();
    }

    @Override
    public void run() 
    {
        while(isAlive)
        {
            for(int i = 0; i < beltArray.size(); i++)
            {
                if(!beltArray.get(i).isFull())
                {
                    if(beltArray.get(i).connectMachine(this))
                    {
                        while(!beltArray.get(i).isFull())
                        {
                            if(!isAlive)
                            {
                                beltArray.get(i).disconnectMachine(this);
                                break;
                            }
                            try 
                            {
                                Thread.sleep(rand.nextInt(MAX_PRODUCTION_TIME-MIN_PRODUCTION_TIME+1)+MIN_PRODUCTION_TIME);
                            } 
                            catch (InterruptedException ex) 
                            {
                            }

                            beltArray.get(i).postParcel(new Parcel((char) ('A'+rand.nextInt(26)), 
                                    new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)), 
                                    rand.nextInt(MAX_CONSUMPTION_TIME-MIN_CONSUMPTION_TIME+1)+MIN_CONSUMPTION_TIME,
                                    rand.nextInt(4)+1), this);
                        }

                        beltArray.get(i).disconnectMachine(this);
                    }
                }
            }
        }
    }
    
    synchronized public void kill()
    {
        isAlive = false;
    }
    
}
