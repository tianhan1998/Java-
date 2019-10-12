package cn.itcast.jcalculator;

import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.swing.*;
 
public class JCalculator extends JFrame implements ActionListener {
    /**
	 * 
	 */
	

	private class WindowCloser extends WindowAdapter {
       public void windowClosing(WindowEvent we) {
           System.exit(0);
       }
    }
	
	private BigInteger bileft;
	private BigInteger biright;
	private BigDecimal bdleft;
	private BigDecimal bdright;
	
//	double dleft=0;
//	double dright=0;
//
//	long lleft=0;
//	long lright=0;

//	boolean isbig=false;

	private char op;
	private boolean isdouble=false;
    private int i;
    // Strings for Digit & Operator buttons.
    private final String[] str = { "7", "8", "9", "/", "4", "5", "6", "*", "1",
           "2", "3", "-", ".", "0", "=", "+" };
    // Build buttons.
    JButton[] buttons = new JButton[str.length];
    // For cancel or reset.
    JButton reset = new JButton("CE");
    // Build the text field to show the result.
    /**
     * 
     */
    JTextField display = new JTextField("0");
   
    /**
     * Constructor without parameters.
     */
    public JCalculator() {
       super("º∆À„∆˜");
       // Add a panel.
       //JPanel panel1 = new JPanel(new GridLayout(4, 4));
       JPanel panel1 = new JPanel();
       panel1.setLayout(new GridLayout(4,4));
       for (i = 0; i < str.length; i++) {
           buttons[i] = new JButton(str[i]);
           panel1.add(buttons[i]);
       }
       //JPanel panel2 = new JPanel(new BorderLayout());
       JPanel panel2 = new JPanel();
       panel2.setLayout(new BorderLayout());
       panel2.add("Center", display);
       panel2.add("East", reset);
       // JPanel panel3 = new Panel();
       getContentPane().setLayout(new BorderLayout());
       getContentPane().add("North", panel2);
       getContentPane().add("Center", panel1);
       // Add action listener for each digit & operator button.
       for (i = 0; i < str.length; i++)
           buttons[i].addActionListener(this);
       // Add listener for "reset" button.
       reset.addActionListener(this);
       // Add listener for "display" button.
       display.addActionListener(this);
       // The "close" button "X".
       addWindowListener(new WindowCloser());
       // Initialize the window size.
        setSize(640, 480);
       // Show the window.
       // show(); Using show() while JDK version is below 1.5.
       setVisible(true);
       // Fit the certain size.
    }
//    public boolean isLong(String number){
//    	try{
//			long test=Long.parseLong(number);
//			return true;
//    	}catch(NumberFormatException e){
//    		return false;
//    	}
//    }
//    public boolean isDouble(String number){
//    	try{
//    		double test=Double.parseDouble(number);
//    		return true;
//    	}catch(NumberFormatException e){
//    		return false;
//    	}
//    }
    public void actionPerformed(ActionEvent e) {
    	if(display.getText().equals("≥¢ ‘≥˝“‘¡„"))
			display.setText("");
       Object target = e.getSource();
       String label = e.getActionCommand();
       if (target == reset)
           handleReset();
       else if ("0123456789.".indexOf(label) >= 0)
           handleNumber(label);
       else
           handleOperator(label);
    }
    public void calculateBigInteger(BigInteger left,BigInteger right,char op)throws ArithmeticException{
		switch(op){
		case '+':display.setText(left.add(right).toString());break;
		case '-':display.setText(left.subtract(right).toString());break;
		case '*':display.setText(left.multiply(right).toString());break;
		case '/':display.setText(left.divide(right).toString());break;
		}
    }
    public void calculateBigDecimal(BigDecimal left,BigDecimal right,char op)throws ArithmeticException{
		switch(op){
		case '+':display.setText(left.add(right).toString());break;
		case '-':display.setText(left.subtract(right).toString());break;
		case '*':display.setText(left.multiply(right).toString());break;
		case '/':display.setText(left.divide(right).toString());break;
		}
    }
//    public long calculateLong(long left,long right,char op){
//    	long answer=0;
//		switch(op){
//		case '+':answer=left+right;break;
//		case '-':answer=left-right;break;
//		case '*':answer=left*right;break;
//		case '/':if(right!=0)answer=left/right;else display.setText("-1");return -1;
//		}
//		return answer;
//    }
//    public double calculateDouble(double left,double right,char op){
//    	double answer=0;
//		switch(op){
//		case '+':answer=left+right;break;
//		case '-':answer=left-right;break;
//		case '*':answer=left*right;break;
//		case '/':if(right!=0)answer=left/right;else display.setText("-1");return -1;
//		}
//		return answer;
//    }
    private void handleOperator(String label) {
		// TODO Auto-generated method stub
    	if(!label.equals("=")){
    		if(isdouble){
//    			if(isDouble(display.getText())){
//					dleft=Double.parseDouble(display.getText());
//					}
//    			else{
    				bdleft=new BigDecimal(display.getText());
//    			}
    		}
    		else{
//    			if(isLong(display.getText())){
//					lleft=Long.parseLong(display.getText());
//				}
//    			else{
    				bileft=new BigInteger(display.getText());
//				}
    		}
    		op=label.charAt(0);
    		display.setText("");
    	}
    	else{
//    		long lanswer=0;
//    		double danswer=0;
			try {
				if (isdouble) {
//    			if(isDouble(display.getText())){
//					dright=Double.parseDouble(display.getText());
//    			}else{
					bdright = new BigDecimal(display.getText());
					calculateBigDecimal(bdleft, bdright, op);
					return;
////    			}
//    			danswer=calculateDouble(dleft,dright,op);
//				display.setText(String.valueOf(danswer));
				} else {
//    			if(isLong(display.getText())){
//					lright=Long.parseLong(display.getText());
//    			}else{
					biright = new BigInteger(display.getText());
					calculateBigInteger(bileft, biright, op);
					return;
//			}
//    			lanswer=calculateLong(lleft,lright,op);
//				display.setText(String.valueOf(lanswer));
				}
			}catch(ArithmeticException e){
				display.setText("≥¢ ‘≥˝“‘¡„");
			}
    	}
	}

	private void handleNumber(String label) {
		// TODO Auto-generated method stub
		if(label.equals(".")){
			if(display.getText().indexOf('.')<0&&!display.getText().equals("")){
			isdouble=true;
			display.setText(display.getText()+label);
			}
			else
			return;
		}
		else if(!display.getText().equals("0")){
			display.setText(display.getText()+label);
		}
		else
			display.setText(label);
	}

	private void handleReset() {
		// TODO Auto-generated method stub
		display.setText("0");
//		dleft=0;dright=0;op=' ';
//		lleft=0;lright=0;
		op=' ';
		bileft=null;
		biright=null;
		bdleft=null;
		bdright=null;
	}
	// Is the first digit pressed?
    boolean isFirstDigit = true;
    /**
     * Number handling.
     * @param key the key of the button.
     */
    
    /**
     * Reset the calculator.
     */
   
   
    /**
     * Handling the operation.
     * @param key pressed operator's key.
     */
    
   
    public static void main(String[] args) {
       new JCalculator();
    }
}