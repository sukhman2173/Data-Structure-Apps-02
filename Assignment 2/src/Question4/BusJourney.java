//Sukhman Singh 18041216
package Question4;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class BusJourney 
{
    private List<BusTrip> busList;
    
    public BusJourney()
    {
        busList = new LinkedList();
    }
    
    public BusJourney(List<BusTrip> list)
    {
        busList = new LinkedList();
        
        for(int i = 0; i < list.size(); i++)
        {
            busList.add(list.get(i));
        }
    }
    
    public boolean addBus(BusTrip bus)
    {
        if(busList.size() <= 0)
        {
            busList.add(bus);
            return true;
        }
        
        if(bus.getDepartLocation().equals(busList.get(busList.size()-1).getArrivalLocation()))
        {
            if(busList.get(busList.size()-1).getArrivalTime().compareTo(bus.getDepartTime()) <= 0)
            {
                if(!busList.contains(bus))
                {
                    busList.add(bus);
                    return true;
                }
            }
        }
        return false;          
    }
    
    public boolean removeLastTrip()
    {
        if(busList.size() > 0)
        {
            busList.remove(busList.size()-1);
            return true;
        }
        
        return false;
    }
    
    public boolean containsLocation(String location)
    {
        for(int i = 0; i < busList.size(); i++)
        {
            if(busList.get(i).getArrivalLocation().equals(location))
            {
                return true;
            }
            else if(busList.get(i).getDepartLocation().equals(location))
            {
                return true;
            }
        }
        
        return false;
    }
    
    public String getOrigin()
    {
        return busList.get(0).getDepartLocation();
    }
    
    public String getDestination()
    {
        return busList.get(busList.size()-1).getArrivalLocation();
    }
    
    public LocalTime getOriginTime()
    {
        return busList.get(0).getDepartTime();
    }
    
    public LocalTime getDestinationTime()
    {
        return busList.get(busList.size()-1).getArrivalTime();
    }
    
    public BusJourney cloneJourney()
    {
        return new BusJourney(busList);
    }
    
    public int getNumberOfBusTrips()
    {
        return busList.size();
    }
    
    public double getTotalCost()
    {
        double cost = 0;
        
        for(int i = 0; i < busList.size(); i++)
        {
            cost += busList.get(i).getCost();
        }
        
        return cost;
    }
    
    @Override
    public String toString()
    {
        String output = "";
        
        for(int i = 0; i < busList.size(); i++)
        {
            output += busList.get(i).toString()+"\n";
        }
        return output;
    }
}
