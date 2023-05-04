package calculatordemo2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.WindowConstants.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import javafx.scene.layout.Border;

import java.util.ArrayList;
import java.awt.GridBagLayout;

/**
 * CalculatorUI class that creates and adds buttons, event handling for the buttons, and calls calculator
 * methods and functions for logic when necessary
 * @author Soria, Steckler, Tesic, Metsis
 */

public class CalculatorUI implements ActionListener {
	public final JFrame frame;
	public final JTextArea text;
	public final JButton add, sub, mult, div, equal, cancel, sqrRt, sqr, inverse, cos, sin, tan, acos, asin, atan;
	public ArrayList<JButton> buttonList;
	public ArrayList<JPanel> panels;
	public final Calculator calc;
	public final JPanel mainPanel, numPanel, primPanel, funcPanel, trigPanel, invTrigPanel, cPanel;

	/**
	 * The main top level GUI of the calculator and it's frame, button, and text area for # display
	 */
	public CalculatorUI() {
		buttonList = new ArrayList<>();
		panels = new ArrayList<>();
		frame = new JFrame("Calculator");
		frame.setResizable(true);
		mainPanel = new JPanel(new GridBagLayout());
		numPanel = new JPanel(new GridBagLayout());
		panels.add(numPanel);
		primPanel = new JPanel(new GridBagLayout());
		panels.add(primPanel);
		funcPanel = new JPanel(new GridBagLayout());
		panels.add(funcPanel);
		trigPanel = new JPanel(new GridBagLayout());
		panels.add(trigPanel);
		invTrigPanel = new JPanel(new GridBagLayout());
		panels.add(invTrigPanel);
		cPanel = new JPanel();
		panels.add(cPanel);
		text = new JTextArea(2, 25);

		String value = "";
		JButton buttonHolder;
		for (int i = 0; i < 10; i++) {
			value = String.valueOf(i);
			buttonHolder = new JButton(value);
			buttonList.add(buttonHolder);
		}

		// create buttons and add to master list
		add = new JButton("+");
		buttonList.add(add);

		sub = new JButton("-");
		buttonList.add(sub);

		mult = new JButton("*");
		buttonList.add(mult);

		div = new JButton("/");
		buttonList.add(div);

		equal = new JButton("=");
		buttonList.add(equal);

		sqrRt = new JButton("√");
		buttonList.add(sqrRt);

		sqr = new JButton("x*x");
		buttonList.add(sqr);

		inverse = new JButton("1/x");
		buttonList.add(inverse);

		cos = new JButton("Cos");
		buttonList.add(cos);

		sin = new JButton("Sin");
		buttonList.add(sin);

		tan = new JButton("Tan");
		buttonList.add(tan);

		acos = new JButton("cos^-1");
		buttonList.add(acos);

		asin = new JButton("sin^-1");
		buttonList.add(asin);

		atan = new JButton("tan^-1");
		buttonList.add(atan);

		cancel = new JButton("C");
		buttonList.add(cancel);

		calc = new Calculator();
	}

	/**
	 * Initializes and sets the frame size, buttons, panels. The main runner method of the UI class.
	 */
	public void init() {
		frame.setSize(500,335);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.add(text, BorderLayout.CENTER);
		
		// function populates and arranges panels
		initPanels();

		frame.add(mainPanel, BorderLayout.SOUTH);
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
		for (int i = 10; i > 0; i--) {
			if (source == buttonList.get(i)) {
				// Insert the buttonvalue: method inserts rather than replaces
				// because the text.select() method was not called previously
				text.replaceSelection(String.valueOf(i));
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
		if (source == acos) {
			writer(calc.calcScience(Calculator.singleOperator.acos,
					reader()));
		}
		if (source == asin) {
			writer(calc.calcScience(Calculator.singleOperator.asin,
					reader()));
		}
		if (source == atan) {
			writer(calc.calcScience(Calculator.singleOperator.atan,
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

	// create layout for panels and add corresponding buttons
	public void initPanels() {

		for (JPanel panel : panels) {
			panel.setBorder(BorderFactory.createEmptyBorder());
		}

		// add action listener to all buttons
		for (JButton button : buttonList) {
			button.addActionListener(this);
		}

		// used for panel and button colors
		Color blueColor = Color.getHSBColor(0.575f, 0.4f, 0.7f);
		Color darkGrayColor = Color.getHSBColor(0.575f, 0.2f, 0.7f);
		Color lightGrayColor = Color.getHSBColor(0.575f, 0.2f, 0.85f);


		// number panel
		numPanel.setBackground(blueColor);
		GridBagConstraints con = new GridBagConstraints();
        con.fill = GridBagConstraints.BOTH;
        con.weightx = 0.5;
        con.insets = new Insets(5,5,5,5);
        con.gridwidth = 1;
    
        int x = 0;      // used for grid placement
        int y = 3;      // used for grid placement
    
        for (int i = 0; i < 10; i++) {
            if (i == 0) {
                con.gridwidth = 3;
	
            }

            con.gridx = x;
            con.gridy = y;
			buttonList.get(i).setBackground(lightGrayColor);
            numPanel.add(buttonList.get(i), con);

			con.gridwidth = 1;

            if (i != 0) {
                x++;
            }
			else {
				y--;
			}
            
            if (x == 3) {
                x = 0;
                y--;
            }
        }

		// primitives panel
		primPanel.setBackground(darkGrayColor);
		con.fill = GridBagConstraints.HORIZONTAL;
        con.weightx = 0.5;
        con.insets = new Insets(5,5,5,5);
        con.gridwidth = 1;

		con.gridx = 0;
		con.gridy = 0;

		for (int i = 10; i < 15; i ++) {
			primPanel.add(buttonList.get(i), con);
			con.gridy++;
			buttonList.get(i).setBackground(blueColor);
		}

		// functions panel
		funcPanel.setBackground(darkGrayColor);
        con.weightx = 0.5;
        con.gridwidth = 1;

		con.gridx = 0;
		con.gridy = 0;

		for (int i = 15; i < 18; i ++) {
			funcPanel.add(buttonList.get(i), con);
			buttonList.get(i).setBackground(blueColor);
			con.gridx++;
		}

		// trig panel
		trigPanel.setBackground(darkGrayColor);
        con.weightx = 0.5;
        con.gridwidth = 1;

		con.gridx = 0;
		con.gridy = 0;

		for (int i = 18; i < 21; i ++) {
			trigPanel.add(buttonList.get(i), con);
			buttonList.get(i).setBackground(blueColor);

			con.gridx++;
		}

		// inverse trig panel
		invTrigPanel.setBackground(blueColor);
        con.weightx = 0.5;
        con.gridwidth = 1;

		con.gridx = 0;
		con.gridy = 0;

		for (int i = 21; i < 24; i ++) {
			invTrigPanel.add(buttonList.get(i), con);
			buttonList.get(i).setBackground(darkGrayColor);
			con.gridx++;
		}

		// C panel
		cPanel.setBackground(blueColor);
		cPanel.add(buttonList.get(24));
		buttonList.get(24).setBackground(darkGrayColor);


		con.fill = GridBagConstraints.BOTH;
		con.insets = new Insets(0,0,0,0);

		// modify constraints for numPanel
        con.gridwidth = 8;
        con.gridheight = 1;
        con.weightx = 0.7;
        con.weighty = 0.8;
        con.gridx = 0;
        con.gridy = 5;
        // add numPanel to main panel
        mainPanel.add(numPanel, con);

		// modify constraints for trigPanel
        con.gridwidth = 10;
        con.gridheight = 1;
        con.weightx = 0.8;
        con.weighty = 0.1;
        con.gridx = 0;
        con.gridy = 0;
        // add to main panel
        mainPanel.add(trigPanel, con);

		// modify constraints for invTrigPanel
        con.gridwidth = 10;
        con.gridheight = 1;
        con.weightx = 0.8;
        con.weighty = 0.1;
        con.gridx = 0;
        con.gridy = 1;
        // add to main panel
        mainPanel.add(invTrigPanel, con);

		// modify constraints for cPanel
        con.gridwidth = 1;
        con.gridheight = 1;
        con.weightx = 0.8;
        con.weighty = 0.1;
        con.gridx = 8;
        con.gridy = 2;
        // add to main panel
        frame.add(cPanel, BorderLayout.EAST);

		// modify constraints for funcPanel
        con.gridwidth = 8;
        con.gridheight = 1;
        con.weightx = 0.8;
        con.weighty = 0.1;
        con.gridx = 0;
        con.gridy = 2;
        // add to main panel
        mainPanel.add(funcPanel, con);

		// modify constraints for primPanel
		con.gridwidth = 1;
        con.gridheight = 9;
        con.weightx = 0.1;
        con.weighty = 0.2;
        con.gridx = 8;
        con.gridy = 2;
        // add to main panel
        mainPanel.add(primPanel, con);
	}
}
