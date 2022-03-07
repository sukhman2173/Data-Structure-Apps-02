//Sukhman Singh 18041216
package Question1;

import java.awt.Color;
import java.awt.Graphics;


public class Parcel<E> implements Comparable<Parcel<?>>
{  
    private E element;
    private Color colour;
    private int consumeTime;
    private int priority;
    private long timestamp;
    
    public Parcel(E element, Color colour, int consumeTime, int priority)
    {
        this.element = element;
        this.colour = colour;
        this.consumeTime = consumeTime;
        this.priority = priority;
        this.timestamp = System.nanoTime();
    }
    
    public void consume()
    {
        try 
        {
            Thread.sleep(consumeTime);
        } 
        catch (InterruptedException ex) 
        {
            System.out.println("Interrupted Exception for consume method");
        }
    }
    
    @Override
    public String toString()
    {
        String output = this.element+"("+this.priority+")";
        
        return output;
    }
    
    public void drawBox(Graphics g, int x, int y, int width, int height)
    {
        g.setColor(this.colour);
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawString(this.toString(), x+5, y+15);
    }

    @Override
    public int compareTo(Parcel<?> o) 
    {
        if(this.priority > o.priority)
        {
            return 1;
        }
        else if(this.priority < o.priority)
        {
            return -1;
        }
        else
        {
            if(this.timestamp > o.timestamp)
            {
                return 1;
            }
            else if(this.timestamp < o.timestamp)
            {
                return -1;
            }
            
            return 0;
        }
    }
}
