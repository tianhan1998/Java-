package cn.itcast.jcalculator;

import java.awt.*;
import java.awt.event.*;
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
	double left=0;
	double right=0;
	char op;
    int i;
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
       super("¼ÆËãÆ÷");
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
        setSize(1024, 768);
       // Show the window.
       // show(); Using show() while JDK version is below 1.5.
       setVisible(true);
       // Fit the certain size.
    }
   
    public void actionPerformed(ActionEvent e) {
       Object target = e.getSource();
       String label = e.getActionCommand();
       if (target == reset)
           handleReset();
       else if ("0123456789.".indexOf(label) > 0)
           handleNumber(label);
       else
           handleOperator(label);
    }
    private void handleOperator(String label) {
		// TODO Auto-generated method stub
    	if(!label.equals("=")){
    		left=Double.parseDouble(display.getText());
    		op=label.charAt(0);
    		display.setText(label);
    	}else{
    		if(op==' '){
    			display.setText(String.valueOf(left));
    		}else{
    			double answer=0;
    			right=Double.parseDouble(display.getText());
    			switch(op){
    			case '+':answer=left+right;break;
    			case '-':answer=left-right;break;
    			case '*':answer=left*right;break;
    			case '/':if(right!=0)answer=left/right;else display.setText("ERROR");break;
    			}
    			display.setText(String.valueOf(answer));
    		}
    	}
	}

	private void handleNumber(String label) {
		// TODO Auto-generated method stub
        double now;
        if(display.getText().indexOf(".")>0){
    	    now=Double.parseDouble(display.getText());
        }
        else {now=0;}
    	double chosen=Double.parseDouble(label);
		if(chosen!=0){
			display.setText(String.valueOf(chosen+now*10));
		}
	}

	private void handleReset() {
		// TODO Auto-generated method stub
		display.setText("0");
		left=0;right=0;op=' ';
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