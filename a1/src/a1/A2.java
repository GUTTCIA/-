package a1;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
public class A2 extends JFrame {
    private static final long serialVersionUID = -9075562467166618473L;
    private JPanel contentPane;//定义主面板对象
    private JTextField display;//定义文本对象
    private ActionListener insert = new InsertAction();//嵌入(监听对象）
    private ActionListener command = new CommandAction();//命令
    private double result = 0;
    private String lastCommand = "=";
    private boolean start = true;
    public A2() {
        setTitle("\u8BA1\u7B97\u5668");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);//设置在下次窗口可见时，窗口是应显示位置
        contentPane = new JPanel();//创建内容面板（主面板）
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));//边框
        contentPane.setLayout(new BorderLayout(0, 0));//设置边界布局
        setContentPane(contentPane);//重置面板对象
        JPanel displayPanel = new JPanel();//文本面板
        contentPane.add(displayPanel, BorderLayout.NORTH);//添加文本面板，并设置为边界布局（中央位置）
        display = new JTextField();//文本框
        display.setText("0");//文本框初始内容为0
        display.setHorizontalAlignment(SwingConstants.RIGHT);//使输入文本靠右
        display.setEditable(false);//设置文本框不能被编辑
        display.setFont(new Font("微软雅黑", Font.PLAIN, 15));//设置输入字体
        displayPanel.add(display);//将文本内容填入到文本面板中
        display.setColumns(13);//设置宽度
        JPanel buttonPanel = new JPanel();//设置按钮面板
        contentPane.add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));

        JButton number7Button = new JButton("7");
        number7Button.addActionListener(insert);
        number7Button.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        buttonPanel.add(number7Button);

        JButton number8Button = new JButton("8");
        number8Button.addActionListener(insert);
        number8Button.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        buttonPanel.add(number8Button);

        JButton number9Button = new JButton("9");
        number9Button.addActionListener(insert);
        number9Button.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        buttonPanel.add(number9Button);

        JButton divideButton = new JButton("/");
        divideButton.addActionListener(command);
        divideButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        buttonPanel.add(divideButton);

        JButton number4Button = new JButton("4");
        number4Button.addActionListener(insert);
        number4Button.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        buttonPanel.add(number4Button);

        JButton number5Button = new JButton("5");
        number5Button.addActionListener(insert);
        number5Button.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        buttonPanel.add(number5Button);

        JButton number6Button = new JButton("6");
        number6Button.addActionListener(insert);
        number6Button.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        buttonPanel.add(number6Button);

        JButton multiplyButton = new JButton("*");
        multiplyButton.addActionListener(command);
        multiplyButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        buttonPanel.add(multiplyButton);

        JButton number3Button = new JButton("1");
        number3Button.addActionListener(insert);
        number3Button.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        buttonPanel.add(number3Button);

        JButton number2Button = new JButton("2");
        number2Button.addActionListener(insert);
        number2Button.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        buttonPanel.add(number2Button);

        JButton number1Button = new JButton("3");
        number1Button.addActionListener(insert);
        number1Button.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        buttonPanel.add(number1Button);

        JButton subtractButton = new JButton("-");
        subtractButton.addActionListener(command);
        subtractButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        buttonPanel.add(subtractButton);

        JButton number0Button = new JButton("0");
        number0Button.addActionListener(insert);
        number0Button.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        buttonPanel.add(number0Button);

        JButton dotButton = new JButton(".");
        dotButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        buttonPanel.add(dotButton);

        JButton equalButton = new JButton("=");
        equalButton.addActionListener(command);
        equalButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        buttonPanel.add(equalButton);

        JButton addButton = new JButton("+");
        addButton.addActionListener(command);
        addButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        buttonPanel.add(addButton);
        pack();
    }

    private class InsertAction implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String input = e.getActionCommand();//得到标签
            String text = display.getText();//获取文本
            if (start) {
                display.setText("");
                start = false;
            }
            if (text.startsWith(".")) {//以....开始
                display.setText(display.getText() + input);
            } else if (text.startsWith("-0.") || text.startsWith("0.")) {
                display.setText(display.getText() + input);
            } else if (text.startsWith("-0")) {
                display.setText("-" + input);
            } else if (text.startsWith("0")) {
                display.setText(input);
            } else {
                display.setText(display.getText() + input);
            }
        }
    }

    private class CommandAction implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();//得到标签
            if (start) {
                if (command.equals("-")) {
                    display.setText(command);
                    start = false;
                } else {
                    lastCommand = command;
                }
            } else {
                calculate(Double.parseDouble(display.getText()));//把字符串转换为Double
                lastCommand = command;
                start = true;
            }
        }
    }

    public void calculate(double x) {
        char operator = lastCommand.charAt(0);//返回的是某个索引下的char值
        switch (operator) {
            case '+':
                result += x;
                break;
            case '-':
                result -= x;
                break;
            case '*':
                result *= x;
                break;
            case '/':
                result /= x;
                break;
            case '=':
                result = x;
                break;
        }
        display.setText("" + result);
    }




    public static void main(String[] args) {
        A2 frame=new A2();
        frame.setVisible(true);


    }}




