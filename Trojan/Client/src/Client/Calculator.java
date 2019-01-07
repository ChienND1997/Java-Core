package Client;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class Calculator {

	private JFrame frmCalculator;
	JTextArea AnswerField = new JTextArea();

	int i = 0, a = 0;
	int[] num = new int[100];
	String[] Operator = new String[99];
	double result = 0;

	public static void main(String[] args)  {

		new Thread(()->{
			SocketClient socket = new SocketClient("localhost", 6969);
			try {
				socket.SendData();
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();;

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator window = new Calculator();
					window.frmCalculator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Calculator() {
		initialize();
	}

	void mathfun(String c) {

		JLabel message = new JLabel();
		message.setBounds(417, 410, 237, 41);
		frmCalculator.getContentPane().add(message);

		try {
			String stack = AnswerField.getText(), revStack = "", OriginalNo = "";
			int strLen = stack.length();
			strLen -= 1;
			int targetNo = 0, indicator = 0;
			for (; strLen >= 0; strLen--)// String reversal
				revStack = revStack + stack.charAt(strLen);
			strLen = stack.length();
			strLen = strLen - 1;
			char data;
			for (int count = 0; count <= strLen; count++) {// Identifing the requried number
				data = revStack.charAt(count);
				switch (data) {
				case '+':
					indicator++;
					break;
				case '-':
					indicator++;
					break;
				case '*':
					indicator++;
					break;
				case '/':
					indicator++;
					break;
				case '%':
					indicator++;
					break;
				default:
					targetNo++;
				}
				if (indicator != 0)
					break;
			}
			String reqNumber = revStack.substring(0, targetNo);
			strLen = reqNumber.length();
			strLen = strLen - 1;
			for (; strLen >= 0; strLen--)
				OriginalNo = OriginalNo + reqNumber.charAt(strLen);
			num[i] = Integer.parseInt(OriginalNo);
			i++;
			Operator[a] = c;
			a++;
			AnswerField.append(c);
		} catch (NumberFormatException e1) {
			AnswerField.append(c);
			message.setText("PleAse Enter Valid Number");
		}

	}

	private void initialize() {

		AnswerField.setLineWrap(true);
		AnswerField.setFont(new Font("Times New Roman", Font.BOLD, 15));
		AnswerField.addAncestorListener(new AncestorListener() {
			public void ancestorAdded1(AncestorEvent event) {
			}

			public void ancestorMoved1(AncestorEvent event) {
			}

			public void ancestorRemoved1(AncestorEvent event) {
			}

			@Override
			public void ancestorAdded(AncestorEvent event) {
				// TODO Auto-generated method stub
			}

			@Override
			public void ancestorRemoved(AncestorEvent event) {
				// TODO Auto-generated method stub
			}

			@Override
			public void ancestorMoved(AncestorEvent event) {
				// TODO Auto-generated method stub
			}
		});
		frmCalculator = new JFrame();
		frmCalculator.setTitle("CalCulator");
		frmCalculator.setFont(new Font("Times New Roman", Font.BOLD, 15));
		frmCalculator.setBounds(100, 100, 689, 516);
		frmCalculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCalculator.getContentPane().setLayout(null);
		JFrame Test = new JFrame();
		Test.setName("CalCulator");
		JButton button_0 = new JButton("0");
		button_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnswerField.append(Integer.toString(0));
			}
		});
		button_0.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_0.setBounds(42, 387, 89, 23);
		frmCalculator.getContentPane().add(button_0);
		AnswerField.setBounds(42, 41, 340, 105);
		frmCalculator.getContentPane().add(AnswerField);
		JButton button_MUL = new JButton("MULTIPLICATION");
		button_MUL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mathfun("*");
			}
		});
		button_MUL.setBounds(417, 166, 89, 96);
		frmCalculator.getContentPane().add(button_MUL);

		JButton button_ADD = new JButton("ADDITION");
		button_ADD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mathfun("+");
			}
		});
		button_ADD.setBounds(417, 301, 89, 83);
		frmCalculator.getContentPane().add(button_ADD);
		JButton button_SUB = new JButton("SUBTRACTION");
		button_SUB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mathfun("-");
			}
		});
		button_SUB.setBounds(565, 301, 89, 83);
		frmCalculator.getContentPane().add(button_SUB);
		JButton button_MOD = new JButton("MODULUS");
		button_MOD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mathfun("%");
			}
		});
		button_MOD.setBounds(417, 58, 89, 71);
		frmCalculator.getContentPane().add(button_MOD);

		JButton button_C = new JButton("C");
		button_C.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnswerField.setText("");
				i = 0;
				a = 0;
			}
		});
		button_C.setBounds(581, 68, 54, 47);
		frmCalculator.getContentPane().add(button_C);
		JButton button_1 = new JButton("1");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnswerField.append(Integer.toString(1));
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBounds(175, 388, 89, 23);
		frmCalculator.getContentPane().add(button_1);
		JButton button_2 = new JButton("2");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnswerField.append("2");
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_2.setBounds(293, 388, 89, 23);
		frmCalculator.getContentPane().add(button_2);

		JButton button_3 = new JButton("3");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnswerField.append("3");
			}
		});
		button_3.setBounds(42, 314, 89, 23);
		frmCalculator.getContentPane().add(button_3);

		JButton button_5 = new JButton("5");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnswerField.append("5");
			}
		});
		button_5.setBounds(293, 314, 89, 23);
		frmCalculator.getContentPane().add(button_5);

		JButton button_6 = new JButton("6");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnswerField.append("6");
			}
		});
		button_6.setBounds(42, 247, 89, 23);
		frmCalculator.getContentPane().add(button_6);

		JButton button_7 = new JButton("7");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnswerField.append("7");
			}
		});
		button_7.setBounds(175, 247, 89, 23);
		frmCalculator.getContentPane().add(button_7);

		JButton button_8 = new JButton("8");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnswerField.append("8");
			}
		});
		button_8.setBounds(293, 247, 89, 23);
		frmCalculator.getContentPane().add(button_8);

		JButton button_9 = new JButton("9");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnswerField.append("9");
			}
		});
		button_9.setBounds(42, 157, 89, 41);
		frmCalculator.getContentPane().add(button_9);

		JButton button_4 = new JButton("4");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnswerField.append("4");
			}
		});
		button_4.setBounds(175, 314, 89, 23);
		frmCalculator.getContentPane().add(button_4);

		JButton button_DIV = new JButton("DIVISION");
		button_DIV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mathfun("/");
			}
		});
		button_DIV.setBounds(565, 166, 89, 96);
		frmCalculator.getContentPane().add(button_DIV);
		JButton btnNewButton = new JButton("ANSWER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mathfun("");
				result = num[0];
				for (int count = 0; count <= 99; count++) {
					if ((Operator[count] != "+") && (Operator[count] != "-") && (Operator[count] != "*")
							&& (Operator[count] != "/") && (Operator[count] != "%"))
						break;
					switch (Operator[count]) {
					case "+": {
						result = result + num[count + 1];
						break;
					}
					case "-": {
						result = result - num[count + 1];
						break;
					}
					case "*": {
						result = result * num[count + 1];
						break;
					}
					case "/": {
						result = result / num[count + 1];
						break;
					}
					case "%": {
						result = result % num[count + 1];
						break;
					}
					}
				}
				AnswerField.setText(Double.toString(result));
			}
		});
		btnNewButton.setBounds(175, 157, 207, 41);
		frmCalculator.getContentPane().add(btnNewButton);
	}
}
