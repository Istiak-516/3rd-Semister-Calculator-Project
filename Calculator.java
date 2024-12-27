
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

public class Calculator extends Frame implements ActionListener {

    TextField tf1;
    Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;
    Button bAdd, bSub, bMul, bDiv, bEq, bClear, bExp, bDel, bOpenParen, bCloseParen;
    String operator;
    double num1, num2, result;

    Calculator() {
        tf1 = new TextField();
        tf1.setBounds(30, 50, 270, 50);
        tf1.setFont(new Font("Arial", Font.BOLD, 20));
        tf1.setBackground(Color.LIGHT_GRAY);
        tf1.setForeground(Color.BLACK);
        tf1.setEditable(false);

        b1 = createButton("1");
        b2 = createButton("2");
        b3 = createButton("3");
        b4 = createButton("4");
        b5 = createButton("5");
        b6 = createButton("6");
        b7 = createButton("7");
        b8 = createButton("8");
        b9 = createButton("9");
        b0 = createButton("0");

        bAdd = createButton("+");
        bSub = createButton("-");
        bMul = createButton("*");
        bDiv = createButton("/");
        bEq = createButton("=");
        bClear = createButton("C");
        bExp = createButton("^");
        bDel = createButton("Del");
        bOpenParen = createButton("(");
        bCloseParen = createButton(")");

        int x = 30, y = 120, width = 60, height = 50;
        b7.setBounds(x, y, width, height);
        b8.setBounds(x + 70, y, width, height);
        b9.setBounds(x + 140, y, width, height);
        b4.setBounds(x, y + 60, width, height);
        b5.setBounds(x + 70, y + 60, width, height);
        b6.setBounds(x + 140, y + 60, width, height);
        b1.setBounds(x, y + 120, width, height);
        b2.setBounds(x + 70, y + 120, width, height);
        b3.setBounds(x + 140, y + 120, width, height);
        b0.setBounds(x, y + 180, width, height);

        bAdd.setBounds(x + 210, y, width, height);
        bSub.setBounds(x + 210, y + 60, width, height);
        bMul.setBounds(x + 210, y + 120, width, height);
        bDiv.setBounds(x + 210, y + 180, width, height);
        bEq.setBounds(x + 140, y + 180, width, height);
        bClear.setBounds(x + 70, y + 180, width, height);
        bExp.setBounds(x + 210, y + 240, width, height);
        bDel.setBounds(x, y + 240, width, height);
        bOpenParen.setBounds(x + 70, y + 240, width, height);
        bCloseParen.setBounds(x + 140, y + 240, width, height);

        add(tf1);
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        add(b6);
        add(b7);
        add(b8);
        add(b9);
        add(b0);
        add(bAdd);
        add(bSub);
        add(bMul);
        add(bDiv);
        add(bEq);
        add(bClear);
        add(bExp);
        add(bDel);
        add(bOpenParen);
        add(bCloseParen);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        b8.addActionListener(this);
        b9.addActionListener(this);
        b0.addActionListener(this);
        bAdd.addActionListener(this);
        bSub.addActionListener(this);
        bMul.addActionListener(this);
        bDiv.addActionListener(this);
        bEq.addActionListener(this);
        bClear.addActionListener(this);
        bExp.addActionListener(this);
        bDel.addActionListener(this);
        bOpenParen.addActionListener(this);
        bCloseParen.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setBackground(Color.DARK_GRAY);
        setSize(330, 450);
        setLayout(null);
        setVisible(true);
        setTitle("Calculator");
        setResizable(false);
    }

    private Button createButton(String label) {
        Button button = new Button(label);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.GRAY);
        button.setForeground(Color.WHITE);
        return button;
    }

    boolean chk(char c) {
        if (c >= '0' && c <= '9') {
            return true; 
        }else {
            return c == '.';
        }
    }

    double calcTwo(double a, double b, char op) {
        return switch (op) {
            case '+' ->
                a + b;
            case '-' ->
                a - b;
            case '*' ->
                a * b;
            case '/' ->
                a / b;
            default ->
                Math.pow(a, b);
        };
    }

    private double evaluate(String exp) {
        double result = 0;
        Stack<Character> operators = new Stack<>();
        Stack<Double> operands = new Stack<>();
        for (int i = 0; i < exp.length(); i++) {
            char cur = exp.charAt(i);
            if (chk(cur)) {
                String num = "";
                while (i < exp.length() && chk(exp.charAt(i))) {
                    num += exp.charAt(i);
                    i++;
                }
                Double _num = Double.valueOf(num);
                operands.push(_num);
                i--;
            } else {
                if (cur == '(') {
                    operators.push('('); 
                }else if (cur == '+' || cur == '-') {
                    char tmp;
                    while (!operators.isEmpty()) {
                        tmp = operators.peek();
                        if (tmp == '(') {
                            break;
                        } else {
                            double n1 = operands.pop();
                            double n2 = operands.pop();
                            operands.push(calcTwo(n2, n1, tmp));
                        }
                        operators.pop();
                    }
                    operators.push(cur);
                } else if (cur == '*' || cur == '/') {
                    char tmp;
                    while (!operators.isEmpty()) {
                        tmp = operators.peek();
                        if (tmp == '(') {
                            break;
                        } else if (tmp == '+' || tmp == '-') {
                            break;
                        } else {
                            double n1 = operands.pop();
                            double n2 = operands.pop();
                            operands.push(calcTwo(n2, n1, tmp));
                        }
                        operators.pop();
                    }
                    operators.push(cur);
                } else if (cur == ')') {
                    while (operators.peek() != '(') {
                        double n1 = operands.pop();
                        double n2 = operands.pop();
                        operands.push(calcTwo(n2, n1, operators.peek()));
                        operators.pop();
                    }
                    operators.pop();
                } else {
                    operators.push(cur);
                }
            }
            Stack<Double> tmp = new Stack<>();
            Stack<Character> tmp1 = new Stack<>();
            while (!operands.isEmpty()) {
                System.out.print(operands.peek());
                System.out.print(" ");
                tmp.push(operands.pop());
            }
            System.out.println("");
            while (!operators.isEmpty()) {
                System.out.print(operators.peek());
                System.out.print(" ");
                tmp1.push(operators.pop());
            }
            System.out.println("");
            while (!tmp.isEmpty()) {
                operands.push(tmp.pop());
            }
            while (!tmp1.isEmpty()) {
                operators.push(tmp1.pop());
            }
        }
        while (!operators.isEmpty()) {
            double n1 = operands.pop();
            double n2 = operands.pop();
            operands.push(calcTwo(n2, n1, operators.peek()));
            operators.pop();
        }
        return operands.peek();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.charAt(0) == '=') {
            double result = evaluate(tf1.getText());
            tf1.setText(String.valueOf(result));
        } else if (command.equals("Del")) {
            String cur = tf1.getText();
            if (cur != null && cur.length() > 0) {
                cur = cur.substring(0, cur.length() - 1);
            }
            tf1.setText(cur);
        } else if (command.charAt(0) == 'C') {
            tf1.setText("");
        } else {
            String cur = tf1.getText();
            cur += command;
            tf1.setText(cur);
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
