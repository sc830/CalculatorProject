package calculatordemo2;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.WindowConstants.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * CalculatorUI class that creates and adds buttons, event handling for the buttons, and calls calculator
 * methods and functions for logic when necessary
 * @author Soria, Steckler, Tesic, Metsis
 */

public class CalculatorUI implements ActionListener {
	public final JFrame frame;
	public final JPanel panel;
	public final JTextArea text;
	public final JButton jButtons[], add, sub, mult, div, equal, cancel, sqrRt, sqr, inverse, cos, sin, tan;
	public final String[] buttonValue = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	public final Calculator calc;

	/**
	 * The main top level GUI of the calculator and it's frame, button, and text area for # display
	 */
	public CalculatorUI() {
		frame = new JFrame("Calculator");
		frame.setResizable(true);
		panel = new JPanel(new FlowLayout());
		text = new JTextArea(2, 25);
		jButtons = new JButton[10];

		for (int i = 0; i < 10; i++) {
			jButtons[i] = new JButton(String.valueOf(i));
		}

		add = new JButton("+");
		sub = new JButton("-");
		mult = new JButton("*");
		div = new JButton("/");
		equal = new JButton("=");
		sqrRt = new JButton("√");
		sqr = new JButton("x*x");
		inverse = new JButton("1/x");
		cos = new JButton("Cos");
		sin = new JButton("Sin");
		tan = new JButton("Tan");
		cancel = new JButton("C");

		calc = new Calculator();
	}

	/**
	 * Initializes and sets the frame size, buttons, panels. The main runner method of the UI class.
	 */
	public void init() {
		frame.setSize(300, 340);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.add(panel);

		panel.add(text);
		for (int i = 0; i < 10; i++) {
			panel.add(jButtons[i]);
			jButtons[i].addActionListener(this);
		}
		// add operand buttons
		panel.add(add);
		panel.add(sub);
		panel.add(mult);
		panel.add(div);
		panel.add(sqr);
		panel.add(sqrRt);
		panel.add(inverse);
		panel.add(cos);
		panel.add(sin);
		panel.add(tan);
		panel.add(equal);
		panel.add(cancel);
		// add event listeners
		add.addActionListener(this);
		sub.addActionListener(this);
		mult.addActionListener(this);
		div.addActionListener(this);
		sqr.addActionListener(this);
		sqrRt.addActionListener(this);
		inverse.addActionListener(this);
		cos.addActionListener(this);
		sin.addActionListener(this);
		tan.addActionListener(this);
		equal.addActionListener(this);
		cancel.addActionListener(this);

		frame.setVisible(true);
	}

	/**
	 * Event handling implementation for Calculator button pressing
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		final Object source = e.getSource();
		// check 0-9 and update textfield
		for (int i = 0; i < 10; i++) {
			if (source == jButtons[i]) {
				// Insert the buttonvalue: method inserts rather than replaces
				// because the text.select() method was not called previously
				text.replaceSelection(buttonValue[i]);
				return;
			}
		}
		if (source == add) {
			writer(calc.twoOpCaller(Calculator.twoOperator.add, reader()));
		}
		if (source == sub) {
			writer(calc.twoOpCaller(Calculator.twoOperator.subtract, reader()));
		}
		if (source == mult) {
			writer(calc.twoOpCaller(Calculator.twoOperator.multiply,
					reader()));
		}
		if (source == div) {
			writer(calc.twoOpCaller(Calculator.twoOperator.divide, reader()));
		}
		if (source == sqr) {
			writer(calc.calcScience(Calculator.singleOperator.square,
					reader()));
		}
		if (source == sqrRt) {
			writer(calc.calcScience(Calculator.singleOperator.squareRoot,
					reader()));
		}
		if (source == inverse) {
			writer(calc.calcScience(
					Calculator.singleOperator.oneDevidedBy, reader()));
		}
		if (source == cos) {
			writer(calc.calcScience(Calculator.singleOperator.cos,
					reader()));
		}
		if (source == sin) {
			writer(calc.calcScience(Calculator.singleOperator.sin,
					reader()));
		}

		if (source == tan) {
			writer(calc.calcScience(Calculator.singleOperator.tan,
					reader()));
		}
		if (source == equal) {
			writer(calc.calculateEqual(reader()));
		}
		if (source == cancel) {
			writer(calc.reset());
		}
		// for easy continued calculations/copy
		text.selectAll();
	}

	/**
	 * Helper function that gets the texfield area and updates the number input
	 * @return the updated number
	 */
	public Double reader() {
		Double num;
		String str;
		str = text.getText();
		num = Double.valueOf(str);

		return num;
	}

	/**
	 * Helper function that sets the textfield with the number, and avoids NaN issues
	 * @param num
	 */
	public void writer(final Double num) {
		if (Double.isNaN(num)) {
			text.setText("");
		} else {
			text.setText(Double.toString(num));
		}
	}
}
