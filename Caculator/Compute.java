package Caculator;


import java.util.Stack;

import javax.swing.JOptionPane;

import java.util.ArrayList;

public class Compute {
//	public static void main(String [] args) {
//
//		Compute c = new Compute();
//		ArrayList<String> suffixList = new ArrayList<>();
//		ArrayList<String> formulaList = new ArrayList<>();
//		double result = 0.0;
////		formulaList = c.departFormula("9*8+((9+6)*(9-3))");
//		formulaList = c.departFormula("9+8+6+3");
//		for(String s:formulaList) {
//			System.out.print(s + "|");
//			//System.out.print(s.equals(""));
//		}
//
//		System.out.print("\n");
//		suffixList = c.makeSuffixList(formulaList);
//		for(String s:suffixList) {
//			System.out.print(s + "|||");
//		}
//		result = c.Computing(suffixList);
//		System.out.print("\n");
//		System.out.print(result);
//				
//	}
	
	public double getResult(String str) {
		double result =0.0;
		ArrayList<String> suffixList = new ArrayList<>();
		ArrayList<String> formulaList = new ArrayList<>();
		formulaList = departFormula(str);
		suffixList = makeSuffixList(formulaList);
		result = Computing(suffixList);
		return result;
		
	}
	
	public ArrayList<String> makeSuffixList(ArrayList<String> list)
	{
		Stack<String> OperationStack = new Stack<String>();
		ArrayList<String> suffixList = new ArrayList<>();
		for (String s: list) {

			if(isNumber(s)) {
				suffixList.add(s);
			}else if("(".equals(s)) {
				OperationStack.push(s);
			}else if(")".equals(s)) {
				while(!OperationStack.isEmpty()) {
					if("(".equals(OperationStack.peek())) {
						OperationStack.pop();
						break;
					}else {
						suffixList.add(OperationStack.pop());
					}
					
				}
			}else if(isOperator(s)){
				if(OperationStack.empty() || "(".equals(OperationStack.peek()) || priority(OperationStack.peek()) < priority(s)) {
					OperationStack.push(s);
				}else {
					while(!OperationStack.isEmpty() && !("(".equals(OperationStack.peek()))){
						if(priority(OperationStack.peek()) >= priority(s)) {
							suffixList.add(OperationStack.pop());
						}
					}
					
					OperationStack.push(s);
				}
			}else {
				throw new RuntimeException("unknown char in the formula");
			}
		}
		while(!OperationStack.isEmpty()) {
			suffixList.add(OperationStack.pop());
		}
		return suffixList;
	}
	
	public boolean isNumber(String str) {
		return str.matches("\\d+");
	}
	
	public boolean isOperator(String str) {
		return str.equals("/") || str.equals("*") || str.equals("-") || str.equals("+");
	}
	
	public int priority(String str) {
		if(str.equals("+") || str.equals("-")) {
			return 0;
		}else if(str.equals("/") || str.equals("*")) {
			return 1;
		}
		return -1;
	}
	
	public static ArrayList<String> departFormula(String str){
		ArrayList<String> formulaList =new ArrayList<>();
		int beginIndex = 0;
		int endIndex = 0;
		for (int i=0;i<str.length();i++) {
			if(str.charAt(i) == '/' || str.charAt(i) == '*'|| str.charAt(i) == '-' || str.charAt(i) == '+' ||
					str.charAt(i) == '(' || str.charAt(i) == ')') {
				endIndex = i;
				if(endIndex - beginIndex >= 1) {
					formulaList.add(str.substring(beginIndex,endIndex));
					formulaList.add(String.valueOf(str.charAt(i)));
					beginIndex = i + 1;
				}else {
					formulaList.add(String.valueOf(str.charAt(i)));
					beginIndex = i + 1;
					}
				}			
		}
		if(!"".equals(str.substring(beginIndex,str.length()))) {
			formulaList.add(str.substring(beginIndex,str.length()));
		}
		
		
		
		
		return formulaList;
	}
	
	public double Computing(ArrayList<String> list) {
		double result = 0.0;
		Stack<Double> myStack = new Stack<>();
		
		for(int i=0; i<list.size();i++) {
			String item = list.get(i);
			if(item.matches("\\d+")) {
				myStack.push(Double.parseDouble(item));
			}else {
				double num2 = myStack.pop();
				double num1 = myStack.pop();
				if(item.equals("+")) {
					result = num1 + num2;
					myStack.push(result);
				}else if(item.equals("-")) {
					result = num1-num2;
					myStack.push(result);
				}else if(item.equals("*")) {
					result = num1 * num2;
					myStack.push(result);
				}else if(item.equals("/")) {
						result = num1/num2;
						myStack.push(result);

					
				}else {
					
					throw new RuntimeException("Computing Error");
				}
			}
		}

		
		return result;
	}
	
}
