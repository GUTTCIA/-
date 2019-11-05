package a1;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class A1 extends JFrame implements ActionListener {
    // ����
    JTextField txtResult;
    boolean firstDigit = true;//�����ж��Ƿ�������
    String operator = "=";//�ȳ�ʼ��Ϊ�Ⱥţ��ȵ�ִ����Ӧ����ʱ�ٸ���
    boolean operateValidFlag = true;//�жϳ����Ƿ�Ϊ0
    double resultNum = 0.0;//�����ݴ�Ŀǰ�����ս��

    // ����
    public A1() {
        setTitle("������");
        setSize(300, 300);//��ʼ�����ڴ�С
        setResizable(true);//�ɵ��ڴ��ڴ�С
        setLocationRelativeTo(null);//���ô������λ�ã�null���˴��ڽ�������Ļ������
        setDefaultCloseOperation(EXIT_ON_CLOSE);//,ֱ�ӹر�Ӧ�ó���

        Container contentPane = this.getContentPane();//���JFrame���������
        contentPane.setLayout(new BorderLayout(1,5));
        JPanel pnlNorth = new JPanel();
        JPanel pnlCenter = new JPanel();

        pnlNorth.setLayout(new BorderLayout());
        pnlCenter.setLayout(new GridLayout(4, 5, 3, 3));

        Font font = new Font("Times Roman", Font.BOLD, 20);

        contentPane.add(BorderLayout.NORTH, pnlNorth);
        contentPane.add(BorderLayout.CENTER, pnlCenter);

        txtResult = new JTextField();
        txtResult.setFont(font);
        txtResult.setEnabled(false);
        JButton btnClear = new JButton("C");
        btnClear.setFont(font);
        btnClear.addActionListener(this);

        pnlNorth.add(BorderLayout.CENTER, txtResult);
        pnlNorth.add(BorderLayout.EAST, btnClear);

        String[] captions = {"7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "0", ".", "/", "=",};
        for (int i = 0; i < captions.length; i++) {
            JButton btn = new JButton(captions[i]);
            btn.setFont(font);
            pnlCenter.add(btn);
            btn.addActionListener(this);

        }

    }


    public static void main(String[] args) {
        JFrame frame = new A1();
        frame.setVisible(true);

    }
    //�԰�ť���еķ�Ӧ

    @Override


    public void actionPerformed(ActionEvent event) {
        String label = event.getActionCommand();
        if (label.equals("C")) {
            handleC();

        } else if ("0123456789.".indexOf(label) >= 0) {
            //������������С����һ����ȡ����
            handleNumber(label);

        } else {
            //����ǰҪִ�е���������������operator
            handleOperator(label);

        }

    }

    //��ȡ����


    void handleNumber(String key) {
        if (firstDigit) {
            txtResult.setText(key);//���ı�������ʾ���ֵ��ַ���

        } else if ((key.equals(".")) && (txtResult.getText().indexOf(".") < 0)) {

            txtResult.setText(txtResult.getText() + ".");//���ı�������ʾ�������ֵ��ַ���

        } else if (!key.equals(".")) {

            txtResult.setText(txtResult.getText() + key);//���ı�������ʾ�������ֵ��ַ���

        }
        firstDigit = false;//��������ʾ��֮�󣬼�������Ϊfalse

    }

    //ʵ������


    void handleC() {

        txtResult.setText("0");
        firstDigit = true;
        operator = "=";

    }
//��������


    void handleOperator(String key) {
        if (operator.equals("/")) {
            //�жϳ����Ƿ�Ϊ0
            if (getNumberFromText() == 0.0) {
                //���´���ܹؼ�����������˵����ÿ�ο����в�ͬ�����
                operateValidFlag = false;
                txtResult.setText("��������Ϊ��");

            } else {
                resultNum /= getNumberFromText();

            }

        } else if (operator.equals("+")) {

            resultNum += getNumberFromText();

        } else if (operator.equals("-")) {

            resultNum -= getNumberFromText();

        } else if (operator.equals("*")) {

            resultNum *= getNumberFromText();

        } else if (operator.equals("=")) {

            resultNum = getNumberFromText();

        }

        if (operateValidFlag) {
            long t1;
            double t2;
            t1 = (long) resultNum;
            t2 = resultNum - t1;
            if (t2 == 0) {
                txtResult.setText(String.valueOf(t1));

            } else {
                txtResult.setText(String.valueOf(resultNum));

            }

        }
        operator = key;
        firstDigit = true;
        operateValidFlag = true;

    }


    double getNumberFromText() {
        double result = 0;
        try {
            result = Double.valueOf(txtResult.getText()).doubleValue();//��Stringת����Double���͵Ķ���,����double��ԭʼֵ

        } catch (NumberFormatException e) {

        }
        return result;

    }
}

