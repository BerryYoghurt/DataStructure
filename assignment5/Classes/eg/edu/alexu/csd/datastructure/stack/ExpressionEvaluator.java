<<<<<<< HEAD
package eg.edu.alexu.csd.datastructure.stack;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ExpressionEvaluator implements IExpressionEvaluator {
	
	/**
	 * patterns used throughout the methods
	 * */
	/**
	 * character regex*/
	final String CHARACTER = "(?:[a-z]|[A-Z]|[0-9])"; //match only one character
	/**
	 * negative regex*/
	final String NEGATIVE = "(?:\\-?)"; //optional negative sign
	/** term regex
	 * match one or more characters
	 * this is a capturing group*/
	final String TERM = "("+ NEGATIVE +"(" + CHARACTER + "+))";//match one or more characters, this is a capturing group because I need it in replace
	/**
	 * operator regex*/
	final String OPERATOR = "(?:[-/+*])";//match only one operator
	/**
	 * open bracket regex*/
	final String OPBRACKET = "(?:(?:\\(+)?)"; //optional opening bracket
	/**
	 * close bracket regex*/
	final String CLSBRACKET = "(?:(?:\\)+)?)";//optional closing bracket
	/**
	 * sub expression regex*/
	final String SUBEXP = "(?:" +NEGATIVE+ OPBRACKET + TERM + "(?:(?:" + OPERATOR + TERM +")+)?"+CLSBRACKET+")";//match one term followed by optional (operator,term)
	/**
	 * expression regex*/
	final String EXP = NEGATIVE + OPBRACKET+SUBEXP + "(?:(?:" + OPERATOR + SUBEXP + ")+)?" + CLSBRACKET;//not exactly accurate but works because of bracket matching
	Pattern operators = Pattern.compile(OPERATOR);//used in infixToPostfix and evaluates

	
	private int precedence(char operator) {
		switch(operator) {
		case '+':
		case '-':
			return 0;
		case '*':
		case '/':
			return 1;
		default:
			throw new IllegalArgumentException("Unsupported Operator");
		}
	}
	
	/*public String obtainValues(String expression) {
		try(Scanner sc = new Scanner(expression)){
			
		}
	}*/
	
	@Override
	public String infixToPostfix(String expression) {
		StringBuilder postfix = new StringBuilder();
		Stack operatorStack = new Stack();
		
		expression = expression.replaceAll("\\s", ""); //remove all whitespace
		
		if(validateInfix(expression))
		{
			expression = expression.replaceAll("([*/])-"+TERM, "$1(0-$2)"); //if negative is preceded by * or /, ensure that the subtraction is done first
			expression = expression.replaceAll("([-+\\(])-", "$10-");//if negative is preceded by opening a bracket, no need to ensure precedence 
			expression = expression.replaceAll("^-", "0-");//same for beginning of word
			
			Character current;
			for(int i = 0; i < expression.length(); i++) {
				current = Character.valueOf(expression.charAt(i));
				if(Character.isLetterOrDigit(current)) { //part of term, no space
					postfix.append(current);
				}
				else if(current.equals('(')) {//open bracket, no space
					operatorStack.push(current);
				}
				else if(current.equals(')')) {//close bracket, no space
					while(!operatorStack.peek().equals('('))
					{
						postfix.append(' ');
						postfix.append((Character)operatorStack.pop());
					}
					operatorStack.pop();
				}
				else if(operators.matcher(current.toString()).matches()) {//TEST THIS, operator
					if(operatorStack.isEmpty() || ((Character)operatorStack.peek()).equals('(')) {//no past operator within subexp.. this branch has to be here
						operatorStack.push(current);
						postfix.append(' ');
					}
					else if(precedence(current) > precedence((Character)operatorStack.peek())) { //higher precedence, push to stack
						operatorStack.push(current);
						postfix.append(' ');
					}
					else {//left-to-right associative, so if precedence is less than or equals that at the top of the stack, pop the stack
						while(!(operatorStack.isEmpty()|| ((Character)operatorStack.peek()).equals('('))){ //as long as there are past operators
							postfix.append(" "+operatorStack.pop());
						}
						postfix.append(' ');
						operatorStack.push(current);
					}
				}
			}
			while(!operatorStack.isEmpty()) {//pop the very first operator
				postfix.append(" "+(Character)operatorStack.pop());
			}	
		}
		else {
			throw new IllegalArgumentException("Invalid Expression");
		}
		return postfix.toString();
	}

	@Override
	public int evaluate(String expression) {
		boolean advanced = true; //if scanner has advanced in latest iteration
		Stack operands = new Stack();
		try(Scanner sc = new Scanner(expression)){
			char operator;
			float result = 0;
			float op1, op2;
			while(sc.hasNext() && advanced) {
				advanced = false;
				while(sc.hasNextFloat()) {
					advanced = true;
					operands.push(sc.nextFloat());
				}
				while(sc.hasNext(operators)) {
					operator = sc.next(operators).charAt(0);
					if(operands.size() >= 2) {
						op1 = (Float)operands.pop();
						op2 = (Float)operands.pop();
						switch(operator) {
						case '+':
							result = op2 + op1;
							break;
						case '-':
							result = op2 - op1;
							break;
						case '*':
							result = op2 * op1;
							break;
						case '/':
							result = op2/op1;
							/*if(op1 == 0) {
								throw new ("Division By Zero!");
							}
							else {
								result = op2 / op1;
							}*/
							break;
						}
						operands.push(result);
					}
					else {
						throw new IllegalArgumentException("Invalid input, no operands to work on");
					}
				}
				if(!advanced) {
					throw new IllegalArgumentException("Invalid input, didn't find numbers somewhere");
				}
			}
			if(operands.size()!=1) {
				throw new IllegalArgumentException("Invalid input, number of operators and operands not compatible");
			}
		}
		return (int)(float)operands.pop();
	}
	
	private boolean balancedBrackets(String input) {
		Stack bracketStack = new Stack();
		for(char current : input.toCharArray()) {
			if(current == '(') {
				bracketStack.push(current);
			}
			else if(current == ')') {
				if(bracketStack.isEmpty()) //if no bracket to pop
					return false;
				bracketStack.pop();
			}
		}
		return bracketStack.isEmpty();
	}
	
	private boolean validateInfix(String input) {
		boolean valid = false;
		if(balancedBrackets(input)) {//ALL NON-CAPTURING GROUPS
			
			Pattern pExpression = Pattern.compile(EXP);
			valid = pExpression.matcher(input).matches();
		}
		return valid;
	}
	
}
=======
package eg.edu.alexu.csd.datastructure.stack;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ExpressionEvaluator implements IExpressionEvaluator {
	
	/**
	 * patterns used throughout the methods
	 * */
	/**
	 * character regex*/
	final String CHARACTER = "(?:[a-z]|[A-Z]|[0-9])"; //match only one character
	/**
	 * negative regex*/
	final String NEGATIVE = "(?:\\-?)"; //optional negative sign
	/** term regex
	 * match one or more characters
	 * this is a capturing group*/
	final String TERM = "("+ NEGATIVE +"(" + CHARACTER + "+))";//match one or more characters, this is a capturing group because I need it in replace
	/**
	 * operator regex*/
	final String OPERATOR = "(?:[-/+*])";//match only one operator
	/**
	 * open bracket regex*/
	final String OPBRACKET = "(?:(?:\\(+)?)"; //optional opening bracket
	/**
	 * close bracket regex*/
	final String CLSBRACKET = "(?:(?:\\)+)?)";//optional closing bracket
	/**
	 * sub expression regex*/
	final String SUBEXP = "(?:" +NEGATIVE+ OPBRACKET + TERM + "(?:(?:" + OPERATOR + TERM +")+)?"+CLSBRACKET+")";//match one term followed by optional (operator,term)
	/**
	 * expression regex*/
	final String EXP = NEGATIVE + OPBRACKET+SUBEXP + "(?:(?:" + OPERATOR + SUBEXP + ")+)?" + CLSBRACKET;//not exactly accurate but works because of bracket matching
	Pattern operators = Pattern.compile(OPERATOR);//used in infixToPostfix and evaluates

	
	private int precedence(char operator) {
		switch(operator) {
		case '+':
		case '-':
			return 0;
		case '*':
		case '/':
			return 1;
		default:
			throw new IllegalArgumentException("Unsupported Operator");
		}
	}
	
	/*public String obtainValues(String expression) {
		try(Scanner sc = new Scanner(expression)){
			
		}
	}*/
	
	@Override
	public String infixToPostfix(String expression) {
		StringBuilder postfix = new StringBuilder();
		Stack operatorStack = new Stack();
		
		expression = expression.replaceAll("\\s", ""); //remove all whitespace
		
		if(validateInfix(expression))
		{
			expression = expression.replaceAll("([*/])-"+TERM, "$1(0-$2)"); //if negative is preceded by * or /, ensure that the subtraction is done first
			expression = expression.replaceAll("([-+\\(])-", "$10-");//if negative is preceded by opening a bracket, no need to ensure precedence 
			expression = expression.replaceAll("^-", "0-");//same for beginning of word
			
			Character current;
			for(int i = 0; i < expression.length(); i++) {
				current = Character.valueOf(expression.charAt(i));
				if(Character.isLetterOrDigit(current)) { //part of term, no space
					postfix.append(current);
				}
				else if(current.equals('(')) {//open bracket, no space
					operatorStack.push(current);
				}
				else if(current.equals(')')) {//close bracket, no space
					while(!operatorStack.peek().equals('('))
					{
						postfix.append(' ');
						postfix.append((Character)operatorStack.pop());
					}
					operatorStack.pop();
				}
				else if(operators.matcher(current.toString()).matches()) {//TEST THIS, operator
					if(operatorStack.isEmpty() || ((Character)operatorStack.peek()).equals('(')) {//no past operator within subexp.. this branch has to be here
						operatorStack.push(current);
						postfix.append(' ');
					}
					else if(precedence(current) > precedence((Character)operatorStack.peek())) { //higher precedence, push to stack
						operatorStack.push(current);
						postfix.append(' ');
					}
					else {//left-to-right associative, so if precedence is less than or equals that at the top of the stack, pop the stack
						while(!(operatorStack.isEmpty()|| ((Character)operatorStack.peek()).equals('('))){ //as long as there are past operators
							postfix.append(" "+operatorStack.pop());
						}
						postfix.append(' ');
						operatorStack.push(current);
					}
				}
			}
			while(!operatorStack.isEmpty()) {//pop the very first operator
				postfix.append(" "+(Character)operatorStack.pop());
			}	
		}
		else {
			throw new IllegalArgumentException("Invalid Expression");
		}
		return postfix.toString();
	}

	@Override
	public int evaluate(String expression) {
		boolean advanced = true; //if scanner has advanced in latest iteration
		Stack operands = new Stack();
		try(Scanner sc = new Scanner(expression)){
			char operator;
			float result = 0;
			float op1, op2;
			while(sc.hasNext() && advanced) {
				advanced = false;
				while(sc.hasNextFloat()) {
					advanced = true;
					operands.push(sc.nextFloat());
				}
				while(sc.hasNext(operators)) {
					operator = sc.next(operators).charAt(0);
					if(operands.size() >= 2) {
						op1 = (Float)operands.pop();
						op2 = (Float)operands.pop();
						switch(operator) {
						case '+':
							result = op2 + op1;
							break;
						case '-':
							result = op2 - op1;
							break;
						case '*':
							result = op2 * op1;
							break;
						case '/':
							result = op2/op1;
							/*if(op1 == 0) {
								throw new ("Division By Zero!");
							}
							else {
								result = op2 / op1;
							}*/
							break;
						}
						operands.push(result);
					}
					else {
						throw new IllegalArgumentException("Invalid input, no operands to work on");
					}
				}
				if(!advanced) {
					throw new IllegalArgumentException("Invalid input, didn't find numbers somewhere");
				}
			}
			if(operands.size()!=1) {
				throw new IllegalArgumentException("Invalid input, number of operators and operands not compatible");
			}
		}
		return (int)(float)operands.pop();
	}
	
	private boolean balancedBrackets(String input) {
		Stack bracketStack = new Stack();
		for(char current : input.toCharArray()) {
			if(current == '(') {
				bracketStack.push(current);
			}
			else if(current == ')') {
				if(bracketStack.isEmpty()) //if no bracket to pop
					return false;
				bracketStack.pop();
			}
		}
		return bracketStack.isEmpty();
	}
	
	private boolean validateInfix(String input) {
		boolean valid = false;
		if(balancedBrackets(input)) {//ALL NON-CAPTURING GROUPS
			
			Pattern pExpression = Pattern.compile(EXP);
			valid = pExpression.matcher(input).matches();
		}
		return valid;
	}
	
}
>>>>>>> d7a4750f329a2af912a30500c137e49590a0efd2
