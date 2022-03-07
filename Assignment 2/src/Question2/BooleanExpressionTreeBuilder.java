//Sukhman Singh 18041216
package Question2;

import java.util.Stack;

public class BooleanExpressionTreeBuilder 
{
    private Stack<BoolExpNode> treeStack;
    private String output;
    
    public BooleanExpressionTreeBuilder()
    {
        treeStack = new Stack<>();
        output = "";
    }
    
    public BoolExpNode buildExpressionTree(char[] expression)
    {
        for(int i = 0; i < expression.length; i++)
        {
            if(expression[i] == 'F' || expression[i] == 'T')
            {
                treeStack.push(new BoolOperandNode(expression[i]));
            }
            
            if(expression[i] == '|' || expression[i] == '&' || expression[i] == '!' || expression[i] == '^')
            {
                BoolExpNode operatorNode = new BoolOperatorNode(expression[i]);
                
                if(treeStack.size() < 2)
                {
                    return null;
                }
                
                if(expression[i] == '!')
                {
                    operatorNode.rightChild = treeStack.pop();
                    treeStack.push(operatorNode);
                }
                else
                {
                    operatorNode.rightChild = treeStack.pop();
                    operatorNode.leftChild = treeStack.pop();
                    treeStack.push(operatorNode);
                }
            }
        }
        
        if(treeStack.size() == 1)
        {
            return treeStack.pop();
        }
        else
        {
            return null;
        }
    }
    
    public String toInfixString(BoolExpNode node)
    {   
        if(node!=null)
        {
            if(node.leftChild != null)
            {
                output += "(";
            }
            toInfixString(node.leftChild);
            output += node.toString();
            toInfixString(node.rightChild);
            if(node.rightChild != null)
            {
                if(node.symbol != '!')
                {
                    output += ")";
                }
            }
        }
        
        return output;
    }
    
    public int countNodes(BoolExpNode node)
    {
        if(node == null)
        {
            return 0;
        }
        else
        {
            return countNodes(node.leftChild) + 1 + countNodes(node.rightChild);
        }
    }
}
