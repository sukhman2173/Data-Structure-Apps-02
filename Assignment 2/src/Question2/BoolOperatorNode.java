//Sukhman Singh 18041216
package Question2;

public class BoolOperatorNode extends BoolExpNode
{
    public BoolOperatorNode(char symb)
    {
        super(symb);
    }
    
    @Override
    public boolean evaluate()
    {
        switch (symbol) {
            case '|':
                return (leftChild.evaluate() | rightChild.evaluate());
            case '&':
                return (leftChild.evaluate() & rightChild.evaluate());
            case '!':
                return (!rightChild.evaluate());
            case '^':
                return (leftChild.evaluate() ^ rightChild.evaluate());
            default:
                throw new IllegalArgumentException("Invalid Operator");
        }
    }
}
