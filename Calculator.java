import java.awt.*;  
import java.awt.event.*;  

public class Calculator extends Frame implements ActionListener {  
    // Declare components
    TextField tf1;  
    Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;  
    Button bAdd, bSub, bMul, bDiv, bEq, bClear;  
    String operator;  
    double num1, num2, result;  

    // Constructor
    Calculator() {  
        // Initialize components
        tf1 = new TextField();  
        tf1.setBounds(30, 50, 240, 50);  

        b1 = new Button("1"); b2 = new Button("2"); b3 = new Button("3");
        b4 = new Button("4"); b5 = new Button("5"); b6 = new Button("6");
        b7 = new Button("7"); b8 = new Button("8"); b9 = new Button("9");
        b0 = new Button("0");

        bAdd = new Button("+"); bSub = new Button("-");
        bMul = new Button("*"); bDiv = new Button("/");
        bEq = new Button("="); bClear = new Button("C");

        // Set bounds for buttons
        int x = 30, y = 120, width = 60, height = 50;
        b7.setBounds(x, y, width, height); b8.setBounds(x + 70, y, width, height); b9.setBounds(x + 140, y, width, height);
        b4.setBounds(x, y + 60, width, height); b5.setBounds(x + 70, y + 60, width, height); b6.setBounds(x + 140, y + 60, width, height);
        b1.setBounds(x, y + 120, width, height); b2.setBounds(x + 70, y + 120, width, height); b3.setBounds(x + 140, y + 120, width, height);
        b0.setBounds(x, y + 180, width, height);

        bAdd.setBounds(x + 210, y, width, height);
        bSub.setBounds(x + 210, y + 60, width, height);
        bMul.setBounds(x + 210, y + 120, width, height);
        bDiv.setBounds(x + 210, y + 180, width, height);
        bEq.setBounds(x + 140, y + 180, width, height);
        bClear.setBounds(x + 70, y + 180, width, height);

        // Add components to frame
        add(tf1);  
        add(b1); add(b2); add(b3); add(b4); add(b5); add(b6); add(b7); add(b8); add(b9); add(b0);
        add(bAdd); add(bSub); add(bMul); add(bDiv); add(bEq); add(bClear);

        // Add action listeners
        b1.addActionListener(this); b2.addActionListener(this); b3.addActionListener(this);
        b4.addActionListener(this); b5.addActionListener(this); b6.addActionListener(this);
        b7.addActionListener(this); b8.addActionListener(this); b9.addActionListener(this); b0.addActionListener(this);
        bAdd.addActionListener(this); bSub.addActionListener(this);
        bMul.addActionListener(this); bDiv.addActionListener(this);
        bEq.addActionListener(this); bClear.addActionListener(this);

        // Set frame properties
        setSize(320, 400);  
        setLayout(null);  
        setVisible(true);  
        setTitle("Calculator");
    }  

    @Override
    public void actionPerformed(ActionEvent e) {  
        String command = e.getActionCommand();
        
        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            tf1.setText(tf1.getText() + command);
        } else if (command.equals("C")) {
            tf1.setText("");
            num1 = num2 = result = 0;
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(tf1.getText());
            switch (operator) {
                case "+": result = num1 + num2; break;
                case "-": result = num1 - num2; break;
                case "*": result = num1 * num2; break;
                case "/": result = num1 / num2; break;
            }
            tf1.setText(String.valueOf(result));
        } else {
            operator = command;
            num1 = Double.parseDouble(tf1.getText());
            tf1.setText("");
        }
    }  

    public static void main(String[] args) {  
        new Calculator();  
    }  
}