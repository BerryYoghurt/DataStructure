package eg.edu.alexu.csd.datastructure.stack;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UIApplication {
	
	public static void main(String[] args) {
		Stack stack = new Stack();
		ExpressionEvaluator e = new ExpressionEvaluator();
		int choice = -1;
		int result;
		String input;
		try(Scanner sc = new Scanner(System.in)){
			while(choice != 7) {
				System.out.println("1: Push\r\n" + 
						"2: Pop\r\n" + 
						"3: Peek\r\n" + 
						"4: Get size\r\n" + 
						"5: Check if empty\r\n" +
						"6: Convert infix to Postfix and Evaluate\r\n"+
						"7: Exit\r\n");
				if(sc.hasNextInt()) {
					choice = sc.nextInt();
					sc.nextLine();
				}else {
					System.out.println("Please enter a valid choice");
					sc.nextLine();
					continue;
				}
				switch(choice) {
				case 1:
					System.out.println("Enter element to push:");
					input = sc.next();
					sc.nextLine();
					stack.push(input);
					System.out.println("Element pushed: "+input);
					break;
				case 2:
					if(stack.isEmpty()) {
						System.out.println("Stack already empty!");
					}
					else {
						System.out.println("Popped Element: "+(String) stack.pop());
					}
					break;
				case 3:
					if(stack.isEmpty()) {
						System.out.println("Stack empty!");
					}
					else {
						System.out.println("Top Element: "+(String) stack.peek());
					}
					break;
				case 4:
					System.out.println("Stack size: " + stack.size());
					break;
				case 5:
					System.out.println("Stack is "+(stack.isEmpty()?"":"not ")+"empty");
					break;
				case 6:
					System.out.println("Input infix expression in one line: ");
					input = sc.nextLine();
					try {
						input = e.infixToPostfix(input);
						System.out.printf("As postfix: %s \n", input);
						input = getValues(sc, e, input);
						try {
							result = e.evaluate(input);
							if(result == Integer.MAX_VALUE) {
								System.out.printf("Value = Infinity\n");
							}
							else if(result == Integer.MIN_VALUE) {
								System.out.printf("Value = -Infinity\n");
							}
							else {
							System.out.printf("Value = %d\n",result);
							}
						}
						catch(IllegalArgumentException e1) {
							System.out.println("Can't evaluate");
							System.out.println(e1.getMessage());
						}
					}
					catch(IllegalArgumentException e1) {
						System.out.println("Can't convert");
						System.out.println(e1.getMessage());
					}
					break;
				case 7:
					break;
				default:
					System.out.println("Invalid choice");
				}
			}
		}
		
	}

	private static String getValues(Scanner sc, ExpressionEvaluator e, String postfix) {
		Pattern term = Pattern.compile(e.TERM);
		Pattern alpha = Pattern.compile("[A-Z|a-z]");
		String token;
		int value;
		//Scanner s = new Scanner(postfix);
		//try(Scanner s = new Scanner(postfix)){
			//while(s.hasNext()) {
		for(Matcher m = term.matcher(postfix); m.find();/*s.hasNext(term)*/) {
			//token = s.next(term);
			token = m.group();
			if(!alpha.matcher(token).find()) {
				continue;
			}
			System.out.println("Enter the value of "+token+" :");
			value = sc.nextInt();
			postfix = postfix.replaceAll("\\b"+token+"\\b", Integer.toString(value));
			 m = term.matcher(postfix);
			//s.close();
			//s = new Scanner(postfix);
		}
				/*while(s.hasNext() && !s.hasNext(term)) {
					s.next();
				}*/
			//}
		//}
		//s.close();
		return postfix;
	}
}
