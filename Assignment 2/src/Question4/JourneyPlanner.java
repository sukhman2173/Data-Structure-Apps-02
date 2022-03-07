//Sukhman Singh 18041216
package Question4;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JourneyPlanner 
{
    private Map<String, Set<BusTrip>> locationMap;
    
    public JourneyPlanner()
    {
        locationMap = new HashMap<>();
    }
    
    public boolean add(BusTrip bus)
    {        
        if(!locationMap.containsKey(bus.getDepartLocation()))
        {
            Set<BusTrip> set = new HashSet();
            set.add(bus);
            locationMap.put(bus.getDepartLocation(), set);
            return true;
        }
        else
        {
            return locationMap.get(bus.getDepartLocation()).add(bus);
        }
    }
    
    public List<BusJourney> getPossibleJourneys(String startLocation, LocalTime startTime, String endLocation, LocalTime endTime)
    {
        List<BusJourney> journey = new LinkedList<>();
        
        findPaths(startLocation, startTime, endLocation, endTime, new BusJourney(), journey);
        
        return journey;
    }
    
    private void findPaths(String currentLocation, LocalTime currentTime, String endLocation, LocalTime endTime, BusJourney currentJourney, List<BusJourney> journeyList)
    {
        BusTrip[] tripArray = new BusTrip[locationMap.get(currentLocation).size()];
        tripArray = locationMap.get(currentLocation).toArray(tripArray);

        for(int i = 0; i < tripArray.length; i++)
        {
            if(tripArray[i].getDepartTime().compareTo(currentTime) >= 0)
            {
                if(tripArray[i].getArrivalTime().compareTo(endTime) <= 0)
                {
                    if(tripArray[i].getArrivalLocation().equals(endLocation))
                    {
                        currentJourney.addBus(tripArray[i]);
                        journeyList.add(currentJourney.cloneJourney());
                        currentJourney.removeLastTrip();
                    }
                    else
                    {
                        if(!currentJourney.containsLocation(tripArray[i].getArrivalLocation()))
                        {
                            if(currentJourney.addBus(tripArray[i]))
                            {
                                findPaths(tripArray[i].getArrivalLocation(), tripArray[i].getArrivalTime(), endLocation, endTime, currentJourney, journeyList);
                            }
                        }

                    }
                }
            }
        }
        currentJourney.removeLastTrip();
    }
}
