//Sukhman Singh 18041216
package Question1;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.PriorityQueue;

public class ConveyorBelt 
{
    private int maxCapacity;
    private Machine connectedMachine;
    private Dispatcher connectedDispatcher;
    private PriorityQueue<Parcel<?>> beltQueue;
    
    public ConveyorBelt(int maxCapacity)
    {
        this.maxCapacity = maxCapacity;
        connectedMachine = null;
        connectedDispatcher = null;
        beltQueue = new PriorityQueue<>(this.maxCapacity);
    }
    
    public ConveyorBelt()
    {
        this.maxCapacity = 10;
        connectedMachine = null;
        connectedDispatcher = null;
        beltQueue = new PriorityQueue<>(this.maxCapacity);
    }
    
    public boolean connectMachine(Machine machine)
    {
        if(connectedMachine == null)
        {
            connectedMachine = machine;
            return true;
        }
        
        return false;
    }
    
    public boolean connectDispatcher(Dispatcher machine)
    {
        if(connectedDispatcher == null)
        {
            connectedDispatcher = machine;
            return true;
        }
        
        return false;
    }
    
    public boolean disconnectMachine(Machine machine)
    {
        if(connectedMachine == machine)
        {
            connectedMachine = null;
            return true;
        }
        
        return false;
    }
    
    public boolean disconnectDispatcher(Dispatcher machine)
    {
        if(connectedDispatcher == machine)
        {
            connectedDispatcher = null;
            return true;
        }
        
        return false;
    }
    
    public int size()
    {
        return beltQueue.size();
    }
    
    public boolean isEmpty()
    {
        return beltQueue.isEmpty();
    }
    
    public boolean isFull()
    {
        return beltQueue.size() == maxCapacity;
    }
    
    public boolean postParcel(Parcel<?> p, Machine machine)
    {
        if(connectedMachine == machine)
        {
            beltQueue.add(p);
            return true;
        }
        
        return false;
    }
    
    public Parcel<?> getFirstParcel(Dispatcher dispatcher)
    {
        if(connectedDispatcher == dispatcher)
        {
            return beltQueue.peek();
        }
        else
        {
            return null;
        }  
    }
    
    public Parcel<?> retrieveParcel(Dispatcher dispatcher)
    {
        if(connectedDispatcher == dispatcher)
        {
            if(beltQueue.peek() != null)
            {
                beltQueue.peek().consume();
                return beltQueue.poll();
            }
        }
        
        return null;
    }
    
    public void drawBelt(Graphics g, int x, int y, int width, int height)
    {
        int count = 0;
        Iterator<Parcel<?>> itr = beltQueue.iterator();
        
        if(connectedDispatcher != null)
        {
            g.setColor(Color.BLUE);
            g.fillOval(x-80, y, width-10, height);
        }
        
        synchronized(this)
        {
            while(itr.hasNext())
            {
                Parcel temp = itr.next();

                temp.drawBox(g, x, y, width, height);
                x+= width;

                count++;
            }
        }
        
        for(int i = 0; i < maxCapacity-count; i++)
        {
            g.setColor(Color.BLACK);
            g.drawRect(x, y, width, height);
            x += width;
        }
        
        if(connectedMachine != null)
        {
            g.setColor(Color.RED);
            g.fillOval(x, y, width-10, height);
        }
    }
}
