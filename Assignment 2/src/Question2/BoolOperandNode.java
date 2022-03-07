//Sukhman Singh 18041216
package Question2;

public class BoolOperandNode extends BoolExpNode
{
    public BoolOperandNode(char symb)
    {
        super(symb);
    }
    
    @Override
    public boolean evaluate()
    {
        return symbol == 'T';
    }
}
