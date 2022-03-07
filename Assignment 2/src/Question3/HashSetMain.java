//Sukhman Singh 18041216
package Question3;

import java.util.ArrayList;
import java.util.Iterator;

public class HashSetMain 
{
    public static void main(String[] args) 
    {
        HashSetWithChaining<String> set = new HashSetWithChaining<>(0.75f,6);
        set.add("Seth");
        set.add("Bob");
        set.add("Ian");
        set.add("Adam");
        System.out.println("Creating Set, initial capacity=6... Adding Seth,Bob,Ian,Adam");
        System.out.println(set);
        System.out.println("Size is: "+set.size());
        System.out.println("Adding Jill, Amy, Nat, Seth, Bob, Simon, Andy");
        set.add("Jill");
        set.add("Amy");
        set.add("Nat");
        set.add("Seth");
        set.add("Bob");
        set.add("Simon");
        set.add("Andy");
        System.out.println("Size is: "+set.size());
        System.out.println(set);
        System.out.println("Contain Seth? "+set.contains("Seth")+" Contains Nat? "+set.contains("Nat")+ " Contains Gary? "+set.contains("Gary"));
        System.out.println("Iterating!");
        Iterator<String> itr = set.iterator();
        while(itr.hasNext())
        {
            System.out.print(itr.next()+" ");
        }
        System.out.println();
        System.out.println("REMOVING Seth, Adam, Bob");
        set.remove("Seth");
        set.remove("Adam");
        set.remove("Bob");
        System.out.println("Size is: "+set.size());
        System.out.println(set);
        System.out.println("CLEARING Set");
        set.clear();
        System.out.println(set);
        
        ArrayList<String> collection = new ArrayList<>();
        collection.add("Seth");
        collection.add("Adam");
        collection.add("Bob");
        collection.add("Sukhman");
        System.out.println("Adding collection: "+collection);
        set.addAll(collection);
        System.out.println(set);
        collection.remove("Bob");
        collection.remove("Sukhman");
        System.out.println("Retaining all with collection: "+collection);
        set.retainAll(collection);
        System.out.println(set);
        System.out.println("Adding Rebecca, Amy");
        set.add("Rebecca");
        set.add("Amy");
        System.out.println(set);
        collection.add("Amy");
        System.out.println("Removing all with colllection: "+collection);
        set.removeAll(collection);
        System.out.println(set);
        System.out.println("Size is: "+set.size());
        System.out.println("Is set empty? "+set.isEmpty());
        System.out.println("Removing Rebecca");
        set.remove("Rebecca");
        System.out.println(set);
        System.out.println("Is set empty? "+set.isEmpty());
        System.out.println("Adding all with collection: "+collection);
        set.addAll(collection);
        System.out.println(set);
        collection.add("Tom");
        System.out.println("Contains all with collection: "+collection+"?\n"+set.containsAll(collection));
        System.out.println("Adding Tom");
        set.add("Tom");
        System.out.println(set);
        System.out.println("Contains all with collection: "+collection+"?\n"+set.containsAll(collection));
        
        System.out.println("Testing both toArray methods");
        System.out.println("Set:\n"+set);
        Object[] objArray = set.toArray();
        String[] strArray = new String[set.size()];
        strArray = set.toArray(strArray);
        
        System.out.print("objArray: [");
        for(int i = 0; i < objArray.length; i++)
        {
            System.out.print(objArray[i]);
            if(i != objArray.length-1)
            {
                System.out.print(",");
            }
        }
        System.out.println("]");
        
        System.out.print("strArray: [");
        for(int i = 0; i < strArray.length; i++)
        {
            System.out.print(strArray[i]);
            if(i != strArray.length-1)
            {
                System.out.print(",");
            }
        }
        System.out.println("]");
        
        System.out.println("Clearing Set");
        set.clear();
        System.out.println(set);
    }
}
