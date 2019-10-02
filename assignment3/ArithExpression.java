/* 
 * Kiana Pazdernik
 * CSC 115 Assignment 3: Calculator 
 * Code ArithExpression class given by Csc 115, edited by Kiana Pazdernik
 * November 4, 2018
 *
 */

import java.util.regex.Pattern;
import java.util.regex.Matcher;
// A scanner is imported for the main method 
import java.util.Scanner;

/*
 * The ArithExpression class uses the StringStack class's methods to create an Infix expression
 * Then convert the Infix expression to a Postfix expression using stacks, and evaluated the Postfix expression
 */
public class ArithExpression {

	public TokenList postfixTokens;
	public TokenList infixTokens;


	public ArithExpression(String word) {
		if (Tools.isBalancedBy("()",word)) {
			tokenizeInfix(word);
			infixToPostfix();
		} else {
			throw new InvalidExpressionException("Parentheses unbalanced");
		}
	}

	
	private void tokenizeInfix(String express) {
		infixTokens  = new TokenList(express.length());


		Pattern opParenPattern = Pattern.compile("[-+*/^()]");
		Matcher opMatcher = opParenPattern.matcher(express);

		String matchedBit, nonMatchedBit;
		int lastNonMatchIndex = 0;
		String lastMatch = "";


		while (opMatcher.find()) {
			matchedBit = opMatcher.group();

			nonMatchedBit = express.substring(lastNonMatchIndex, opMatcher.start());
			nonMatchedBit = nonMatchedBit.trim();
			if (matchedBit.charAt(0) == '-') {
				if (opMatcher.start() == 0 ||
					!lastMatch.equals(")") && nonMatchedBit.equals("")) {
					continue; 
				}
			}

			if (nonMatchedBit.length() != 0) {
				infixTokens.append(nonMatchedBit);
			}
			lastNonMatchIndex = opMatcher.end();
			infixTokens.append(matchedBit);
			lastMatch = matchedBit;
		}

		if (lastNonMatchIndex < express.length()) {
			nonMatchedBit = express.substring(lastNonMatchIndex,express.length());
			nonMatchedBit = nonMatchedBit.trim();
			infixTokens.append(nonMatchedBit);
		}
	}

	public static boolean isOperator(String op) {
		switch(op) {
			case "+":
			case "-":
			case "/":
			case "*":
			case "^":
				return true;
			default:
				return false;
		}
	}
	
	/*
	 * A helper method operatorPrecedence is created to help determine the operator precedence
	 * It accepts a string and returns an integer value of precedence
	 *
	 */
	public static int operatorPrecedence(String position)
	{
		// Compares the string to each operator, then returns their precedence
		switch(position){
			case "+":
			case "-":
				return 1;
			case "*":
			case "/":
				return 2;
			case "^":
				return 3;
			default:
				return -1;
		}
	}

	/*
	 * A private method that converts the Infix expression to a Postfix expression using stacks
	 */
	public void infixToPostfix() 
	{
		// Creates a temporary stack that will hold the items from the Infix expression
		StringStack currentStack = new StringStack();
		// Initializing the Postfix list
		postfixTokens  = new TokenList();
		
		// Iterating through the Infix List 
		for(int i = 0; i < infixTokens.size(); i ++)
		{
			// If the value is an opening bracket
			if(infixTokens.get(i).equals("("))
			{
				// Push the value into the current stack
				currentStack.push(infixTokens.get(i));
			}
			// If the value is an operator
			else if(isOperator(infixTokens.get(i)))
			{
				// While the stack is full, and the top is not an opening bracket 
				// And if the operator precedence is less than the top of the stacks operator
				while(!currentStack.isEmpty() && !currentStack.peek().equals("(")
					&& operatorPrecedence(infixTokens.get(i)) <= operatorPrecedence(currentStack.peek()) )
				{
					// The items in the stack are pushed to the Postfix list
					// Until the while loop is false
					postfixTokens.append(currentStack.pop());
				}
				// Otherwise the operator is pushed to the stack
				currentStack.push(infixTokens.get(i));
			}
			// If the value is a closing bracket
			else if(infixTokens.get(i).equals(")"))
			{
				// Until the top of the stack reaches an opening bracket
				while(!currentStack.peek().equals("("))
				{
					// Each item in the stack is appended to the Postfix list
					postfixTokens.append(currentStack.pop());
				}
				// If the top of the stack an opening bracket
				if(currentStack.peek().equals("("))
				{
					// The opening bracket is popped out of the stack 
					currentStack.pop();
				}
			}
			// Otherwise if the value is a digit or letter it is appended to the Postfix List
			else
			{
				postfixTokens.append(infixTokens.get(i));
			}
			
		}	
		// While the stack is not empty 
		while(!currentStack.isEmpty())
		{
			// Every item is appended to the Postfix list 
			postfixTokens.append(currentStack.pop());
		}
	}
	
	/*
	 * Returns the string of the Infix expression
	 */
	public String getInfixExpression() 
	{
		return infixTokens.toString();
	}
	
	/*
	 * Returns the string of the Postfix expression
	 */
	public String getPostfixExpression() 
	{
		return postfixTokens.toString();
	}
	
	/*
	 * The evaluate() method evaluates the Postfix expression and returns the double value 
	 */
	public double evaluate() 
	{
		// If the Postfix list has less than or equal to 2 values
		// It is an invalid expression and cannot be evaluated
	 	if(postfixTokens.size() <= 2)
	 	{
	 		throw new InvalidExpressionException();
	 	}
		
		// Creates a current stack to hold the values
		StringStack evaluateStack = new StringStack();
		
		// For the length of the Postfix list,
	 	for(int i = 0; i < postfixTokens.size(); i++)
	 	{
			// If the top item is an operator
			if(isOperator(postfixTokens.get(i)))
			{
				// Two temporary numbers are created to execute the operator
				int number1;
				int number2;
				
				try
				{
					// The temporary numbers become the value from the top of the stack
					number1 = Integer.parseInt(evaluateStack.pop());
					number2 = Integer.parseInt(evaluateStack.pop());
				}
				// If the values popped from the stack are not numbers
				// The Postfix list is invalid
				catch(NumberFormatException e)
				{
					throw new InvalidExpressionException();
				}
				// While the operator is one the following, 
				// The numbers are evaluated given the specific operator
				// The evaluated number is then pushed to the stack
				switch(postfixTokens.get(i))
				{
					case "+":
					evaluateStack.push(String.valueOf(number1 + number2));
					break;
				
					case "-":
					evaluateStack.push(String.valueOf(-number1 + number2));
					break;
				
					case "*":
					evaluateStack.push(String.valueOf(number1 * number2));
					break;
				
					case "/":
					evaluateStack.push(String.valueOf(number2 / number1));
					break;
				
					case "^":
					// The power to ^ calls upon a math method of Math.pow (the power to method)
					// The numbers are converted into doubles to be evaluated
					double number = Math.pow(number2, number1);
					int newNumber = (int)number;
					evaluateStack.push(String.valueOf(newNumber));
					break;
				}
			}
			// If the item is not an operator, it is pushed to the stack
			else
			{
				evaluateStack.push(postfixTokens.get(i));
			}
	
	 	}
	 	// Converts the string from the stack into a double
	 	double answer = Double.valueOf(evaluateStack.pop());
	 	
	 	// If the stack is not empty after the answer has been popped from the stack
	 	// The Postfix expression is invalid
	 	if(!evaluateStack.isEmpty())
	 	{
	 		throw new InvalidExpressionException();
	 	}
	 	// Returns the double answer
	 	return answer;

	
	}
	
	/*
	 * The main method is used in the terminal as the face of the calculator
	 * The ArithExpression can be tested by inputting an infix expression in the terminal
	 * The StringStack class is tested through the infixToPostfix() method and the evaluate() method
	 */
	public static void main(String[] args) 
	{
		// The scanner is used for an Infix expression to be inputted
		Scanner userInput = new Scanner(System.in);
		System.out.print("\nInput an equation ==> ");
		ArithExpression a1 = new ArithExpression(userInput.next());
		
		// The inputted expression is then returned as a Postfix expression
		// And the evaluation of the expression is given
		System.out.print("\nPostfix of inputted equation ==> " + a1.getPostfixExpression());
		System.out.print("\nEvaluation of inputted equation ==> " + a1.evaluate() + " \n\n");
		
		// If the user would like to input another expression
		// They can type yes/y to enter another expression and the Postfix expression is given and the evaluated answer
		System.out.print("\nEnter another equation y/n? \n");
		String user = userInput.next();
		if(user.equals("y") || user.equals("yes"))
		{
			System.out.print("\nInput an equation ==> ");
			ArithExpression a2 = new ArithExpression(userInput.next());

			System.out.print("\nPostfix of inputted equation ==> " + a2.getPostfixExpression());
			System.out.print("\nEvaluation of inputted equation ==> " + a2.evaluate() + " \n\n");
		}

	}

}
