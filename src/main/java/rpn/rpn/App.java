package rpn.rpn;

import java.util.Stack;

public class App {
	
	private static boolean isOperator(char c)
	{
	    return c == '+' || c == '-' || c == '*' || c == '/' || c == '^'
	            || c == '(' || c == ')';
	}

	private static boolean isLowerPrecedence(char op1, char op2)
	{
	    switch (op1)
	    {
	        case '+':
	        case '-':
	            return !(op2 == '+' || op2 == '-');

	        case '*':
	        case '/':
	            return op2 == '^' || op2 == '(';

	        case '^':
	            return op2 == '(';

	        case '(':
	            return true;

	        default:
	            return false;
	    }
	}

	public static Stack<String> convertToPostfix(String infix)
	{
	    Stack<Character> stack = new Stack<Character>();
	    Stack<String> postfix = new Stack<>();
	    char c;

	    for (int i = 0; i < infix.length(); i++)
	    {
	        c = infix.charAt(i);

	        if (!isOperator(c))
	        {
	            postfix.push(Character.toString(c));
	        }

	        else
	        {
	            if (c == ')')
	            {

	                while (!stack.isEmpty() && stack.peek() != '(')
	                {
	                    postfix.push(Character.toString(stack.pop()));
	                }
	                if (!stack.isEmpty())
	                {
	                    stack.pop();
	                }
	            }

	            else
	            {
	                if (!stack.isEmpty() && !isLowerPrecedence(c, stack.peek()))
	                {
	                    stack.push(c);
	                }
	                else
	                {
	                    while (!stack.isEmpty() && isLowerPrecedence(c, stack.peek()))
	                    {
	                        Character pop = stack.pop();
	                        if (c != '(')
	                        {
	                            postfix.push(Character.toString(pop));
	                        } else {
	                          c = pop;
	                        }
	                    }
	                    stack.push(c);
	                }

	            }
	        }
	    }
	    while (!stack.isEmpty()) {
	      postfix.push(Character.toString(stack.pop()));
	    }
	    return postfix;
	}
    
    public static void main(String[] args)
	{
	    String infix = "3*2+5";
	    Stack<String> postfix = convertToPostfix(infix);
	}
}
