//Sukhman Singh 18041216
package Question3;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class HashSetWithChaining<E> implements Set<E>
{
    private Node<E>[] hashTable;
    private int numElements;
    private float loadFactor;
    private int initialCapacity;
    
    public HashSetWithChaining()
    {
        numElements = 0;
        loadFactor = 0.75f;
        initialCapacity = 10;
        hashTable = new Node[initialCapacity];
    }
    
    public HashSetWithChaining(float load, int capacity)
    {
        numElements = 0;
        loadFactor = load;
        initialCapacity = capacity;
        hashTable = new Node[initialCapacity];
    }
    
    @Override
    public String toString()
    {
        String output = "";
        Node<E> current;
        
        for(int i = 0; i < hashTable.length; i++)
        {
            if(hashTable[i] != null)
            {
                current = hashTable[i];
                output += "row "+i+": "+hashTable[i].element.toString();
                
                while(current.next != null)
                {
                    output += "-->"+current.next.element.toString();
                    current = current.next;
                }
            }
            else
            {
                output += "row "+i+": ";
            }
            
            output += "\n";
        }
        
        return output;
    }
    
    protected class Node<E>
    {  
        protected E element;
        protected Node<E> next;

        public Node(E element)
        {  
            this.element = element;
            next = null;
        }
    }
    
    
    @Override
    public boolean add(E e) 
    {
        boolean added = false;
        Node<E> newNode = new Node<>(e);
        Node<E> current;
        
        if(e == null)
        {
            return false;
        }
        
        if(contains(e))
        {
            System.out.println(e+"  already exists");
            return false;
        }
        
        if((float)numElements/hashTable.length >= loadFactor)
        {
            expandCapacity();
        }
        
        int index = e.hashCode();
        
        if(index >= hashTable.length || index < 0)
        {
            index = index % hashTable.length;
            
            if(index < 0)
            {
                index *= -1;
            }
        }
        
        if(hashTable[index] == null)
        {
            hashTable[index] = newNode;
            numElements++;
            added = true;
        }
        else
        {
            current = hashTable[index];
            newNode.next = current;
            hashTable[index] = newNode;
            numElements++;
            added = true;
        }
        
        return added;
    }
    
    private void expandCapacity()
    {
        Node<E>[] largerTable = new Node[hashTable.length*2];
        Node<E> current;
        Node<E> newNode;
        int index;
        
        for(int i = 0; i < hashTable.length; i++)
        {
            if(hashTable[i] != null)
            {
                index = hashTable[i].element.hashCode() % largerTable.length;
                if(index < 0)
                {
                    index *= -1;
                }
                
                if(largerTable[index] == null)
                {
                    newNode = new Node<>(hashTable[i].element);
                    largerTable[index] = newNode;
                }
                else
                {
                    current = hashTable[i];
                    current.next = largerTable[index];
                    largerTable[index] = current;
                }
                
                if(hashTable[i].next != null)
                {
                    current = hashTable[i];
                    
                    while(current.next != null)
                    {
                        index = current.next.element.hashCode() % largerTable.length;
                        if(index < 0)
                        {
                            index *= -1;
                        }
                        
                        if(largerTable[index] == null)
                        {
                            newNode = new Node<>(current.next.element);
                            largerTable[index] = newNode;
                        }
                        else
                        {
                            newNode = new Node<>(current.next.element);
                            newNode.next = largerTable[index];
                            largerTable[index] = newNode;
                        }
                        
                        current = current.next;
                    }
                }
            }
        }
        
        hashTable = largerTable;
    }
    
    @Override
    public boolean addAll(Collection<? extends E> c) 
    {
        boolean added = false;
        
        E[] temp = (E[]) c.toArray();
        
        for(int i = 0; i < temp.length; i++)
        {
            added = add(temp[i]);
        }
        
        return added;
    }
    
    @Override
    public void clear() 
    {
        hashTable = new Node[initialCapacity];
        numElements = 0;
    }
    
    @Override
    public boolean contains(Object o) 
    { 
        int index = o.hashCode() % hashTable.length;
        if(index < 0)
        {
            index *= -1;
        }
        
        Node<E> current = hashTable[index];
        
        if(current != null)
        {
            if(current.element == o)
            {
                return true;
            }
            else
            {
                while(current.next != null)
                {
                    if(current.next.element == o)
                    {
                        return true;
                    }
                    current = current.next;
                }
            }
        }
        
        return false;
    }
    
    @Override
    public boolean containsAll(Collection<?> c) 
    {
         E[] temp = (E[]) c.toArray();
         
         for(int i = 0; i < temp.length; i++)
         {
             if(!contains(temp[i]))
             {
                 return false;
             }
         }

         return true;
    }
    
    @Override
    public boolean equals(Object o)
    {
        return hashTable == o;
    }
    
    @Override
    public int hashCode() 
    {
        return Arrays.hashCode(hashTable);
    }
    
    @Override
    public boolean isEmpty() 
    {
        return numElements <= 0;
    }
    
    @Override
    public Iterator<E> iterator() 
    {
        return new HashSetIterator(hashTable);
    }
    
    private class HashSetIterator<E> implements Iterator<E>
    {
        Node<E>[] hashTab;
        Node<E> nextNode;
        int index;
        
        public HashSetIterator(Node<E>[] hash)
        {
            hashTab = hash;
            for(int i = 0; i < hash.length; i++)
            {
                if(hash[i] != null)
                {
                    nextNode = hash[i];
                    index = i;
                    break;
                }
            }
        }
        
        @Override
        public boolean hasNext() 
        {
            return nextNode != null;
        }

        @Override
        public E next() 
        {
            E element = nextNode.element;
            nextNode = nextNode.next;
            while(nextNode == null)
            {
                if(index < hashTab.length-1)
                {
                    index++;
                    nextNode = hashTab[index];
                }
                else
                {
                    nextNode = null;
                    break;
                }
            }
            return element;
        } 
    }
    
    @Override
    public boolean remove(Object o) 
    {   
        if(o == null)
        {
            System.out.println("Element cannot be null");
            return false;
        }
        if(!contains(o))
        {
            System.out.println(o+"Does not exist");
            return false;
        }
        
        int index = o.hashCode() % hashTable.length;
        if(index < 0)
        {
            index *= -1;
        }
        
        Node<E> current = hashTable[index];
        
        if(current.element != o)
        {
            while(current.next != null)
            {
                if(current.next.element == o)
                {
                    current.next = current.next.next;
                    numElements--;
                    return true;
                }
                current = current.next;
            }
        }
        else
        {
            hashTable[index] = current.next;
            numElements--;
            return true;
        }
        
        return false;  
    }

    @Override
    public boolean removeAll(Collection<?> c) 
    {
        boolean changed = false;
        
        E[] temp = (E[]) c.toArray();
        
        for(int i = 0; i < temp.length; i++)
        {
            if(contains(temp[i]))
            {
                remove(temp[i]);
                changed = true;
            }
        }
        
        return changed;
    }
    
    @Override
    public boolean retainAll(Collection<?> c) 
    {
        boolean changed = false;
        
        E[] temp = (E[]) c.toArray();
        
        for(int i = 0; i < temp.length; i++)
        {
            if(!contains(temp[i]))
            {
                temp[i] = null;
                changed = true;
            }
        }
        clear();
        
        for(int i = 0; i < temp.length; i++)
        {
            add(temp[i]);
        }
        
        return changed;
    }
    
    @Override
    public int size() 
    {
        return hashTable.length;
    }
    
    @Override
    public Object[] toArray() 
    {
        Object[] array = new Object[numElements];
        Node<E> current;
        int index = 0;
        
        for(int i = 0; i < hashTable.length; i++)
        {
            if(hashTable[i] != null)
            {
                current = hashTable[i];
                array[index] = current.element;
                index++;
                
                while(current.next != null)
                {
                    array[index] = current.next.element;
                    index++;
                    
                    current = current.next;
                }
            }
        }
        
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) 
    {
        Node<E> current;
        int index = 0;
        
        for(int i = 0; i < hashTable.length; i++)
        {
            if(hashTable[i] != null)
            {
                current = hashTable[i];
                a[index] = (T)current.element;
                index++;
                
                while(current.next != null)
                {
                    a[index] = (T)current.next.element;
                    index++;
                    current = current.next;
                }
            }
        }
        
        return a;
    } 
}
