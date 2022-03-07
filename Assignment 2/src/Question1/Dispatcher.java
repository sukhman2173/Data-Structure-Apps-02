//Sukhman Singh 18041216
package Question1;

import java.util.ArrayList;

public class Dispatcher implements Runnable
{
    private ArrayList<ConveyorBelt> beltArray;
    private boolean isAlive;
    
    public Dispatcher(ArrayList<ConveyorBelt> array)
    {
        beltArray = array;
        isAlive = true;
    }
    
    @Override
    public void run() 
    {
        while(isAlive)
        {
            for(int i = 0; i < beltArray.size(); i++)
            {
                if(!isAlive)
                {
                    break;
                }
                
                if(!beltArray.get(i).isEmpty())
                {
                    if(beltArray.get(i).connectDispatcher(this))
                    {
                        beltArray.get(i).retrieveParcel(this);
                        beltArray.get(i).disconnectDispatcher(this);
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
