import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DesktopCalculator {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Calculator");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JTextField display = new JTextField();
            display.setEditable(false);

            JPanel buttons = new JPanel(new GridLayout(4,4,5,5));
            String[] keys = {"7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+"};

            for (String k : keys) {
                JButton b = new JButton(k);
                b.addActionListener(e -> {
                    String cmd = ((JButton)e.getSource()).getText();
                    if (cmd.equals("=")) {
                        try {
                            double result = eval(display.getText());
                            display.setText(Double.toString(result));
                        } catch (Exception ex) { 
                            display.setText("Error"); 
                        }
                    } else {
                        display.setText(display.getText() + cmd);
                    }
                });
                buttons.add(b);
            }

            f.add(display, BorderLayout.NORTH);
            f.add(buttons, BorderLayout.CENTER);
            f.setSize(300, 350);
            f.setVisible(true);
        });
    }

    // ✅ Custom math evaluator (supports + - * /)
    private static double eval(final String expression) {
        class Parser {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < expression.length()) ? expression.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < expression.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if (eat('*')) x *= parseFactor();
                    else if (eat('/')) x /= parseFactor();
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(expression.substring(startPos, this.pos));
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }
                return x;
            }
        }
        return new Parser().parse();
    }
}
