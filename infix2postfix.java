import java.util.*;


public class infix2postfix {
	
	/*
	boolean isOperator(char c){
		boolean stat = false;
		if(c== '*'|| c== '+' || c=='|' || c=='?' || c== '.')
			stat =  true;
		else
			stat =  false;
		return stat;
	}
	
	int precedence(char a) {
		int temp=0;
		switch (a) {
		case '.': //concatenate
		case '|': //alternate
			temp = 1; 
		case '*': //zero or more
		case '+': //one or more
		case '?': //zero or one
			temp = 0; 
		}
		return temp;
	}

	String postfix(String inputStr)
	{
		Stack<Character> stack = new Stack<Character>();
		String output="";
		for(int i =0; i<inputStr.length();i++){
			if(inputStr.charAt(i) == '('){
				stack.add(inputStr.charAt(i));
			}
			else if(inputStr.charAt(i) == ')'){
				while((char)stack.elementAt(stack.size()-1) != '('){
					output += (char)stack.pop();
				}
				stack.pop();
			}
			else if(isOperator(inputStr.charAt(i)) == true){
				if(stack.isEmpty()==true){
					stack.add(inputStr.charAt(i));
				}
				else if(precedence(inputStr.charAt(i)) < precedence((char) stack.elementAt(stack.size()-1)))
				{
					while(precedence(inputStr.charAt(i)) < precedence((char) stack.elementAt(stack.size()-1))){
						output += (char)stack.pop();
					}
				}
				else
					stack.add(inputStr.charAt(i));
			}
			else if((isOperator(inputStr.charAt(i)) == false) && (inputStr.charAt(i) != ')') && inputStr.charAt(i) != '('){
				output+=inputStr.charAt(i);
			}
		}
		while(stack.isEmpty()!=true){
			output += (char)stack.pop();
		}
		return output;
	}
	*//*
	/** Operators precedence map. */
	private static final Map<Character, Integer> precedenceMap;
	static {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('(', 1);
		map.put('|', 3);
		//map.put('.', 3); // explicit concatenation operator
		map.put('?', 4);
		map.put('*', 4);
		map.put('+', 4);
		map.put('^', 5);
		precedenceMap = Collections.unmodifiableMap(map);
	};
 
	/**
	 * Get character precedence.
	 * 
	 * @param c character
	 * @return corresponding precedence
	 */
	private static Integer getPrecedence(Character c) {
		Integer precedence = precedenceMap.get(c);
		return precedence == null ? 6 : precedence;
	}
 
	
	public String postfix(String regex) {
		String postfix = new String();
 
		Stack<Character> stack = new Stack<Character>();
 
		String formattedRegEx = regex;
 
		for (Character c : formattedRegEx.toCharArray()) {
			switch (c) {
				case '(':
					stack.push(c);
					break;
 
				case ')':
					while (!stack.peek().equals('(')) {
						postfix += stack.pop();
					}
					stack.pop();
					break;
 
				default:
					while (stack.size() > 0) {
						Character peekedChar = stack.peek();
 
						Integer peekedCharPrecedence = getPrecedence(peekedChar);
						Integer currentCharPrecedence = getPrecedence(c);
 
						if (peekedCharPrecedence >= currentCharPrecedence) {
							postfix += stack.pop();
						} else {
							break;
						}
					}
					stack.push(c);
					break;
			}
 
		}
 
		while (stack.size() > 0)
			postfix += stack.pop();
 
		return postfix;
	}
	}
