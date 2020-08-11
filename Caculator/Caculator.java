package Caculator;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;

import javax.swing.*;
import java.awt.event.KeyAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.Stack;

public class Caculator{
	public static void main(String [] args) {
		Caculate c1 = new Caculate();
		
	}
}

class Caculate extends JFrame implements ActionListener,KeyListener{
	private Container c = getContentPane();
	//private JTextField formula = new JTextField();
	private JLabel formula = new JLabel();
	private JLabel result = new JLabel();
	private JPanel Pane_label=new JPanel();
	private JPanel buttons = new JPanel();
	private JButton jbt[] = new JButton[20];
	Font font_label = new Font("Times New Roman", Font.BOLD, 20);
	Font font_button = new Font("Times New Roman", Font.BOLD, 25);
	String str_formula = "";
	String str_result = "";

    
    //private JTextField result = new JTextField();
    //private TextField formula = new TextField();
    
   
    public Caculate(){
    	super("電卓");
    	
    	buttons.setLayout(new GridLayout(5,4));
    	
    	for (int i=0 ; i<10 ; i++) {
    		jbt[i] = new JButton(String.valueOf(i));
    	}
    	jbt[10] = new JButton("(");
    	jbt[11] = new JButton(")");
    	jbt[12] = new JButton("+");
    	jbt[13] = new JButton("-");
    	jbt[14] = new JButton("*");
    	jbt[15] = new JButton("/");
    	jbt[16] = new JButton("=");
    	jbt[17] = new JButton("C");
    	jbt[18] = new JButton("←");
    	jbt[19] = new JButton("(-)");
    	
    	
    	for (int i = 0; i<20; i++) {
    		jbt[i].setFont(font_button);
    	}
    	
    	buttons.add(jbt[17]);
    	buttons.add(jbt[18]);
    	buttons.add(jbt[10]);
    	buttons.add(jbt[11]);
    	buttons.add(jbt[7]);
    	buttons.add(jbt[8]);
    	buttons.add(jbt[9]);
    	buttons.add(jbt[15]);
    	buttons.add(jbt[4]);
    	buttons.add(jbt[5]);
    	buttons.add(jbt[6]);
    	buttons.add(jbt[14]);
    	buttons.add(jbt[1]);
    	buttons.add(jbt[2]);
    	buttons.add(jbt[3]);
    	buttons.add(jbt[13]);
    	buttons.add(jbt[19]);
    	buttons.add(jbt[0]);
    	buttons.add(jbt[16]);
    	buttons.add(jbt[12]);
    	
    	Pane_label.setLayout(new GridLayout(2,1));
    	Pane_label.setPreferredSize(new Dimension(100, 100));
    	result.setFont(font_label);
    	formula.setFont(font_label);
    	Pane_label.add(formula);
    	Pane_label.add(result);
    	
    	
    	
    	c.add(Pane_label,BorderLayout.NORTH);
    	c.add(buttons,BorderLayout.CENTER);
    	  	
    	for (int i=0; i<jbt.length; i++) {
    		jbt[i].addActionListener(this); 
    		
    	}
    	
    	for (int i=0;i<jbt.length;i++) {
    		jbt[i].addKeyListener(this);
    	}
    	

    	
    	setBounds(250,300,500,500); 
    	setLocationRelativeTo(this.getOwner());
    	setVisible(true);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	
    }
    



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//NumberButton 0
		if((JButton)e.getSource() == jbt[0]) {

			if(str_result.length() == 0) {
				str_result = "0";
				result.setText(str_result);
			}else if(str_result.length() > 0 && str_result.length() < 8 ) {
				str_result += e.getActionCommand();
			    result.setText(str_result);
			}else {
				JOptionPane.showMessageDialog(null,"Only for eight digitals caculating");	
				str_result = "";
				result.setText(str_result);
			}
		}
			
		//NumberButton 1-9
		if((JButton)e.getSource() == jbt[1] || (JButton)e.getSource() == jbt[2] ||
				(JButton)e.getSource() == jbt[3] || (JButton)e.getSource() == jbt[4] || (JButton)e.getSource() == jbt[5] ||
				(JButton)e.getSource() == jbt[6] || (JButton)e.getSource() == jbt[7] || (JButton)e.getSource() == jbt[8] ||
				(JButton)e.getSource() == jbt[9]) {

			
			if(str_result.length() == 1 && str_result == "0") {
				str_result = e.getActionCommand();
				result.setText(str_result);
			}else if(str_result.length() == 8){
				JOptionPane.showMessageDialog(null,"Only for eight digitals caculating");
				str_result = "";
				result.setText(str_result);
			}else {
				str_result += e.getActionCommand();
			    result.setText(str_result);
			}
		}
		
								
		//Clear(C)Button
		if ((JButton)e.getSource() == jbt[17]){
			str_formula = "";		
			formula.setText(str_formula);
			str_result = "";
			result.setText(str_result);
		}
		
		//delete(←)Button
		if ((JButton)e.getSource() == jbt[18]){
			
			if (str_result.length()> 0) {
				str_result =  str_result.substring(0,str_result.length() - 1);
				result.setText(str_result);
			}
							
		}
		
		// ( button
		if((JButton)e.getSource() == jbt[10]) {
			
			if(str_result.length()==0 && str_formula.length() == 0 ) {
				
				str_formula = e.getActionCommand();
				formula.setText(str_formula);
			}else if(str_result.length()!=0 && str_formula.length() == 0 ) {
				//do nothing
			}else if(str_result.length()==0 && str_formula.length() != 0 ) {
				String tmp = str_formula.substring(str_formula.length()-1, str_formula.length());
				if(tmp.equals("+") || tmp.equals("-") || tmp.equals("*") || tmp.equals("/") || tmp.equals("(")) {
					str_formula += e.getActionCommand();
					formula.setText(str_formula);
				}else {
					JOptionPane.showMessageDialog(null,"The lack of a operation!");
				}
			}else if(str_result.length()!=0 && str_formula.length() != 0 ) {
				JOptionPane.showMessageDialog(null,"The lack of a operation!");
			}
			
			
		}
		
		// ) button
		if((JButton)e.getSource() == jbt[11]) {

			
			if(str_result.length()==0 && str_formula.length() == 0 ) {
				//do nothing
			}else if(str_result.length()!=0 && str_formula.length() == 0 ) {
				//do nothing
			}else if(str_result.length()==0 && str_formula.length() != 0 ) {
				String tmp = str_formula.substring(str_formula.length()-1, str_formula.length());
				if(tmp.equals(")") || tmp.equals("(") || tmp.equals("0") || tmp.equals("1") || tmp.equals("2") || tmp.equals("3") || 
						tmp.equals("4") || tmp.equals("5") || tmp.equals("6") || tmp.equals("7") || tmp.equals("8") || 
						tmp.equals("9")) {
					str_formula += e.getActionCommand();
					formula.setText(str_formula);
				}else {
					//do nothing
				}
			}else if(str_result.length()!=0 && str_formula.length() != 0 ) {
				String tmp = str_formula.substring(str_formula.length()-1, str_formula.length());
				if(tmp.equals("+") || tmp.equals("-") || tmp.equals("*") || tmp.equals("(")) {
					str_formula += str_result + e.getActionCommand();
					formula.setText(str_formula);
					str_result = "";
					result.setText(str_result);
				}
				
				if(tmp.equals("/")) {
					if(str_result == "0") {
						JOptionPane.showMessageDialog(null,"Divisor cannot be 0!");
						str_result = "";
						result.setText(str_result);
					}else {
						str_formula += str_result + e.getActionCommand();
						formula.setText(str_formula);
						str_result = "";
						result.setText(str_result);
					}
				}
				
				if(tmp.equals(")")) {
					JOptionPane.showMessageDialog(null,"The lack of a operation!");
					
				}
			}
			}
				
				
		
		//(-) button
		if((JButton)e.getSource() == jbt[19]) {

			
			if(str_result.length() == 0) {
				JOptionPane.showMessageDialog(null,"Please input number first.");
			}else if(str_formula.length()==0 && str_result.length() != 0) {
				str_formula += "(0-" + str_result + ")";
				formula.setText(str_formula);
				str_result="";
				result.setText(str_result);
			}else if(str_formula.length()!=0 && str_result.length() != 0) {
				String tmp = str_formula.substring(str_formula.length()-1, str_formula.length());
				if(tmp.equals("+") || tmp.equals("-") || tmp.equals("*") || tmp.equals("(") ) {
					str_formula += "(0-" + str_result + ")";
					formula.setText(str_formula);
					str_result="";
					result.setText(str_result);
				}
				
				if(tmp.equals("/")) {
					if(str_result == "0") {
						JOptionPane.showMessageDialog(null,"Divisor cannot be 0!");
						str_result = "";
						result.setText(str_result);
					}else {
						str_formula += "(0-" + str_result + ")";;
						formula.setText(str_formula);
						str_result="";
						result.setText(str_result);
					}
					
				}
				
				if(tmp.equals(")")) {
					JOptionPane.showMessageDialog(null,"The lack of a operation!");
					str_result="";
					result.setText(str_result);
					
					
			}
				
		}
			
	}
		
		//* - + / Button
		if((JButton)e.getSource() == jbt[12] || (JButton)e.getSource() == jbt[13] || (JButton)e.getSource() == jbt[14] || 
				(JButton)e.getSource() == jbt[15]) {
			if(str_formula.length()==0 && str_result.length() == 0) {
				//do noting
			}else if(str_formula.length()==0 && str_result.length() != 0) {
				str_formula += str_result + e.getActionCommand();
				formula.setText(str_formula);
				str_result = "";
				result.setText(str_result);
			}else if(str_formula.length() != 0 && str_result.length() == 0) {
				String tmp = str_formula.substring(str_formula.length()-1, str_formula.length());
				if(tmp.equals("+") || tmp.equals("-") || tmp.equals("*") || tmp.equals("/") || tmp.equals("(")) {
					//do nothing
				}
				
				if(tmp.equals(")")) {
					str_formula += e.getActionCommand();
					formula.setText(str_formula);
					
				}
			}else if(str_formula.length() != 0 && str_result.length() != 0) {
				String tmp = str_formula.substring(str_formula.length()-1, str_formula.length());
				if(tmp.equals("+") || tmp.equals("-") || tmp.equals("*") || tmp.equals("(")) {
					str_formula += str_result + e.getActionCommand();
					formula.setText(str_formula);
					str_result = "";
					result.setText(str_result);
				}
				
				if(tmp.equals("/")) {
					if(str_result == "0") {
						JOptionPane.showMessageDialog(null,"Divisor cannot be 0!");
						str_result = "";
						result.setText(str_result);
					}else {
						str_formula += str_result + e.getActionCommand();
						formula.setText(str_formula);
						str_result = "";
						result.setText(str_result);
					}
				}
				
				if(tmp.equals(")") ) {
					JOptionPane.showMessageDialog(null,"The lack of a operation!");
					str_result = "";
					result.setText(str_result);
					
					
				}

			}
	}
		
		// = button
		if((JButton)e.getSource() == jbt[16]) {
			if(str_formula.length()==0 && str_result.length() == 0) {
				//do noting
			}else if(str_formula.length()!=0 && str_result.length() == 0) {
				if(checkFormula(str_formula)) {
					Compute myCompute = new Compute();
					double res = myCompute.getResult(str_formula);
					str_result = Double.toString(res);
					result.setText("=" + str_result);

				};
				
			}else if(str_formula.length()==0 && str_result.length() != 0) {
				//do nothing
			}else if(str_formula.length()!=0 && str_result.length() != 0) {
				String tmp = str_formula.substring(str_formula.length()-1, str_formula.length());
				if(tmp.equals("+") || tmp.equals("-") || tmp.equals("*")) {
					str_formula += str_result;
					formula.setText(str_formula);
					str_result = "";
					result.setText(str_result);
					if(checkFormula(str_formula)) {
						Compute myCompute = new Compute();
						double res = myCompute.getResult(str_formula);
						str_result = Double.toString(res);
						result.setText("=" + str_result);
					};
				}
				
				if(tmp.equals("/")) {
					if(str_result == "0") {
						JOptionPane.showMessageDialog(null,"Divisor cannot be 0!");
						str_result = "";
						result.setText(str_result);
					}else {
						str_formula += str_result;
						formula.setText(str_formula);
						str_result = "";
						result.setText(str_result);
						if(checkFormula(str_formula)) {
							Compute myCompute = new Compute();
							double res = myCompute.getResult(str_formula);
							str_result = Double.toString(res);
							result.setText("=" + str_result);
						};
					}
				
				}
				
				if(tmp.equals(")")) {
					JOptionPane.showMessageDialog(null,"The lack of operation.");
					str_result = "";
					result.setText(str_result);
				}
				
				if(tmp.equals("(")) {
					JOptionPane.showMessageDialog(null,"Please check your formula");
					str_result = "";
					result.setText(str_result);
				}
				
				
			}
			
		}
		
	}
		    
    

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(str_result.length() > 0 && str_result.substring(0).equals('=')) {
			str_result = "";
			result.setText(str_result);
			str_formula = "";
			formula.setText(str_formula);
		}
		
		//Press 0
		if(e.getKeyChar() == '0' || e.getKeyCode() == KeyEvent.VK_NUMPAD0) {
	
			if(str_result.length() == 0) {
				str_result = "0";
				result.setText(str_result);
			}else if(str_result.length() > 0 && str_result.length() < 8 ) {
				str_result += String.valueOf(e.getKeyChar());
			    result.setText(str_result);
			}else {
				JOptionPane.showMessageDialog(null,"Only for eight digitals caculating");	
				str_result = "";
				result.setText(str_result);
			}
			
			
			
		}
		
		
		//Press 1-9
		if(e.getKeyChar() == '1' || e.getKeyChar() == '2' || e.getKeyChar() == '3' ||
				e.getKeyChar() == '4' || e.getKeyChar() == '5' || e.getKeyChar() == '6' || e.getKeyChar() == '7' ||
				e.getKeyChar() == '8' || e.getKeyChar() == '9' || e.getKeyCode() == KeyEvent.VK_NUMPAD1 || e.getKeyCode() == KeyEvent.VK_NUMPAD2 ||
				e.getKeyCode() == KeyEvent.VK_NUMPAD3 || e.getKeyCode() == KeyEvent.VK_NUMPAD4 || e.getKeyCode() == KeyEvent.VK_NUMPAD5 || 
				e.getKeyCode() == KeyEvent.VK_NUMPAD6 || e.getKeyCode() == KeyEvent.VK_NUMPAD7 || e.getKeyCode() == KeyEvent.VK_NUMPAD8 || 
				e.getKeyCode() == KeyEvent.VK_NUMPAD9) {
			
			if(str_result.length() == 1 && str_result == "0") {
				str_result = String.valueOf(e.getKeyChar());
				result.setText(str_result);
			}else if(str_result.length() == 8){
				JOptionPane.showMessageDialog(null,"Only for eight digitals caculating");
				str_result = "";
				result.setText(str_result);
			}else {
				str_result += String.valueOf(e.getKeyChar());
			    result.setText(str_result);
			}
		}
		
		//press delete/c/C
		if(e.getKeyChar() == 'c' || e.getKeyChar() == 'C' || e.getKeyCode() == KeyEvent.VK_DELETE) {
			str_formula = "";		
			formula.setText(str_formula);
			str_result = "";
			result.setText(str_result);
		}
		
		//press backspace
		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			
			if (str_result.length()> 0) {
				str_result =  str_result.substring(0,str_result.length() - 1);
				result.setText(str_result);
			}
		}
		
		//press (
		if(e.getKeyChar() == '(') {
			
			if(str_result.length()==0 && str_formula.length() == 0 ) {
				str_formula = String.valueOf(e.getKeyChar());
				formula.setText(str_formula);
			}else if(str_result.length()!=0 && str_formula.length() == 0 ) {
				//do nothing
			}else if(str_result.length()==0 && str_formula.length() != 0 ) {
				String tmp = str_formula.substring(str_formula.length()-1, str_formula.length());
				if(tmp.equals("+") || tmp.equals("-") || tmp.equals("*") || tmp.equals("/") || tmp.equals("(")) {
					str_formula += String.valueOf(e.getKeyChar());
					formula.setText(str_formula);
				}else {
					JOptionPane.showMessageDialog(null,"The lack of a operation!!!!");
				}
			}else{
				JOptionPane.showMessageDialog(null,"The lack of a operation123!");
			}

		}
		//press )
		if(e.getKeyChar() == ')') {
			
			if(str_result.length()==0 && str_formula.length() == 0 ) {
				//do nothing
			}else if(str_result.length()!=0 && str_formula.length() != 0 ) {
				String tmp = str_formula.substring(str_formula.length()-1, str_formula.length());
				if(tmp.equals("+") || tmp.equals("-") || tmp.equals("*") || tmp.equals("(")) {
					str_formula += str_result + String.valueOf(e.getKeyChar());
					formula.setText(str_formula);
					str_result = "";
					result.setText(str_result);
				}
				
				if(tmp.equals("/")) {
					if(str_result == "0") {
						JOptionPane.showMessageDialog(null,"Divisor cannot be 0!");
						str_result = "";
						result.setText(str_result);
					}else {
						str_formula += str_result + String.valueOf(e.getKeyChar());
						formula.setText(str_formula);
						str_result = "";
						result.setText(str_result);
					}
				}
				
				if(tmp.equals(")")) {
					JOptionPane.showMessageDialog(null,"The lack of a operation!");
					
				}
			}else if(str_result.length()==0 && str_formula.length() != 0 ) {
				String tmp = str_formula.substring(str_formula.length()-1, str_formula.length());
				if(tmp.equals(")") || tmp.equals("(") || tmp.equals("0") || tmp.equals("1") || tmp.equals("2") || tmp.equals("3") || 
						tmp.equals("4") || tmp.equals("5") || tmp.equals("6") || tmp.equals("7") || tmp.equals("8") || 
						tmp.equals("9")) {
					str_formula += String.valueOf(e.getKeyChar());
					formula.setText(str_formula);
				}else {
					//do nothing
				}
			}else if(str_result.length()!=0 && str_formula.length() == 0) {
				
			}
	
	}
				
				
		
	
	    //press / * - +
		if(e.getKeyChar() == '/' || e.getKeyChar() == '*' ||
				e.getKeyChar() == '-' || e.getKeyChar() == '+') {
			
			if(str_formula.length()==0 && str_result.length() == 0) {
				//do noting
			}else if(str_formula.length()==0 && str_result.length() != 0) {
				str_formula += str_result + String.valueOf(e.getKeyChar());
				formula.setText(str_formula);
				str_result = "";
				result.setText(str_result);
			}else if(str_formula.length() != 0 && str_result.length() == 0) {
				String tmp = str_formula.substring(str_formula.length()-1, str_formula.length());
				if(tmp.equals("+") || tmp.equals("-") || tmp.equals("*") || tmp.equals("/") || tmp.equals("(")) {
					//do nothing
				}
				
				if(tmp.equals(")")) {
					str_formula += String.valueOf(e.getKeyChar());
					formula.setText(str_formula);
					
				}
			}else if(str_formula.length() != 0 && str_result.length() != 0) {
				String tmp = str_formula.substring(str_formula.length()-1, str_formula.length());
				if(tmp.equals("+") || tmp.equals("-") || tmp.equals("*") || tmp.equals("(")) {
					str_formula += str_result + String.valueOf(e.getKeyChar());
					formula.setText(str_formula);
					str_result = "";
					result.setText(str_result);
				}
				
				if(tmp.equals("/")) {
					if(str_result == "0") {
						JOptionPane.showMessageDialog(null,"Divisor cannot be 0!");
						str_result = "";
						result.setText(str_result);
					}else {
						str_formula += str_result + String.valueOf(e.getKeyChar());
						formula.setText(str_formula);
						str_result = "";
						result.setText(str_result);
					}
				}
				
				if(tmp.equals(")")) {
					JOptionPane.showMessageDialog(null,"The lack of a operation!");
					
				}
				
				
				}
			
}
		
		//press Enter 
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			
			if(str_formula.length()==0 && str_result.length() == 0) {
				//do noting
			}else if(str_formula.length()!=0 && str_result.length() == 0) {
				if(checkFormula(str_formula)) {
					Compute myCompute = new Compute();
					double res = myCompute.getResult(str_formula);
					str_result = Double.toString(res);
					result.setText("=" + str_result);
				};
			}else if(str_formula.length()==0 && str_result.length() != 0) {
				JOptionPane.showMessageDialog(null,"The lack of a operation!");
				
			}else if(str_formula.length()!=0 && str_result.length() != 0) {
				String tmp = str_formula.substring(str_formula.length()-1, str_formula.length());
				if(tmp.equals("+") || tmp.equals("-") || tmp.equals("*")) {
					str_formula += str_result;
					formula.setText(str_formula);
					str_result = "";
					result.setText(str_result);
					//
					if(checkFormula(str_formula)) {
						Compute myCompute = new Compute();
						double res = myCompute.getResult(str_formula);
						str_result = Double.toString(res);
						result.setText("=" + str_result);
					};
					
					
				}
				
				if(tmp.equals("/")) {
					if(str_result == "0") {
						JOptionPane.showMessageDialog(null,"Divisor cannot be 0!");
						str_result = "";
						result.setText(str_result);
					}else {
						str_formula += str_result;
						formula.setText(str_formula);
						str_result = "";
						result.setText(str_result);
						if(checkFormula(str_formula)) {
							Compute myCompute = new Compute();
							double res = myCompute.getResult(str_formula);
							str_result = Double.toString(res);
							result.setText("=" + str_result);
						};
					}
				
				}
				
				if(tmp.equals(")")) {
					JOptionPane.showMessageDialog(null,"The lack of operation.");
					str_result = "";
					result.setText(str_result);
				}
				
				if(tmp.equals("(")) {
					JOptionPane.showMessageDialog(null,"Please check your formula");
					str_result = "";
					result.setText(str_result);
				}
				
				
				}
		}
			
	
		
	}




	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public double Computer(String str) {
		
		double result = 0 ;
		return result;
	}
	
	public boolean checkFormula(String str) {
		boolean result = true;
		if(str.length() == 0) {
			//do nothing
		}else if(checkOperation(str)) {
			JOptionPane.showMessageDialog(null,"Error in your formula");
			result = false;
			return result;
		}else if(checkContinueParenPair(str)) {
			JOptionPane.showMessageDialog(null,"() exists in your formula");
			result = false;
			return result;
		}else if(checkParen(str)) {
			JOptionPane.showMessageDialog(null,"single paren exists in your formula");
			result = false;
			return result;
		}
		
		return result;
	}
	
	public boolean checkOperation(String str) {
		boolean result = false;
		
		String tmp = str.substring(str.length()-1,str.length());
		if(tmp.equals("(") || tmp.equals("+") || tmp.equals("-") || tmp.equals("*") || tmp.equals("/")) {
			result = true;
			return result;
		}else {
			return result;
		}
		
	}
	
	public boolean checkContinueParenPair(String str) {
		boolean result = false;
		char paren1;
		char paren2;
		for (int i = 0; i < str.length() - 1; i++) {
			paren1 = str.charAt(i);
			paren2 = str.charAt(i + 1);
			if(paren1 == '(' && paren2 == ')') {
				
				result = true;
				break;
			}	
		}
		return result;
	}
   
	public boolean checkParen(String str) {
		boolean result = false;
		Stack<Character> myStack = new Stack<Character>();
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == '(') {
				myStack.push(str.charAt(i));
			}
			
			if(str.charAt(i) == ')') {
				if(myStack.isEmpty()) {
					result = true;
					return result;
				}else {
					myStack.pop();
				}
				
			}		
			
		}
		if(myStack.isEmpty()) {
			return result;
		}else {
			result = true;
			return result;
		}
		
	}
	
}




