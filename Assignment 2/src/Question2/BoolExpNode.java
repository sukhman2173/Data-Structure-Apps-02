//Sukhman Singh 18041216
package Question2;

public abstract class BoolExpNode 
{
    protected char symbol;
    public BoolExpNode leftChild;
    public BoolExpNode rightChild;
    
    public BoolExpNode(char symb)
    {
        if(symb == 'F' || symb == 'T' || symb == '|' || symb == '&' || symb == '!' || symb == '^')
        {
            symbol = symb;
        }
        else
        {
            throw new IllegalArgumentException("Invalid Symbol");
        }
        leftChild = null;
        rightChild = null;
    }
    
    public abstract boolean evaluate();
    
    @Override
    public String toString()
    {
        return ""+symbol;
    }
}
