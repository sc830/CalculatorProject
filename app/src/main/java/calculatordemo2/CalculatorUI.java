package calculatordemo2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.WindowConstants.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
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
	public final Calculator calc;
	public final JPanel mainPanel;
	public final Buttons buttonMaster; // creates and organizes all buttons into lists
	public final CalculatorPanel numPanel, primPanel, funcPanel, trigPanel, invTrigPanel, cPanel;

	/**
	 * The main top level GUI of the calculator and it's frame, button, and text area for # display
	 */
	public CalculatorUI() {
		buttonMaster = new Buttons();
		text = new JTextArea(2, 25);
		calc = new Calculator();

		frame = new JFrame("Calculator");
		frame.setResizable(true);

		mainPanel = new JPanel(new GridBagLayout());
		numPanel = new CalculatorPanel(buttonMaster.getNumButtons(), this);
		primPanel = new CalculatorPanel(buttonMaster.getPrimButtons(), this);
		funcPanel = new CalculatorPanel(buttonMaster.getFuncButtons(), this);
		trigPanel = new CalculatorPanel(buttonMaster.getTrigButtons(), this);
		invTrigPanel = new CalculatorPanel(buttonMaster.getInvTrigButtons(), this);
		cPanel = new CalculatorPanel(buttonMaster.getMiscButtons(), this);

		// creates global variable names for each button
		// this isn't necessary, but makes actionPerformed() more readable
		// see older versions for other implementation
		add = buttonMaster.get("+");
		sub = buttonMaster.get("-");
		mult = buttonMaster.get("*");
		div = buttonMaster.get("/");
		equal = buttonMaster.get("=");
		cancel = buttonMaster.get("C");
		sqrRt = buttonMaster.get("√");
		inverse = buttonMaster.get("1/x");
		sqr = buttonMaster.get("x^2");
		cos = buttonMaster.get("Cos");
		sin = buttonMaster.get("Sin");
		tan = buttonMaster.get("Tan");
		acos = buttonMaster.get("cos^-1");
		asin = buttonMaster.get("sin^-1");
		atan = buttonMaster.get("tan^-1");
	}

	/**
	 * Initializes and sets the frame size, buttons, panels. The main runner method of the UI class.
	 */
	public void init() {
		frame.setSize(400,335);
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
		for (int i = 0; i < buttonMaster.getNumButtons().size(); i++) {
			if (source == buttonMaster.getNumButtons().get(i)) {
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
		// used for panel and button colors
		Color blueColor = Color.getHSBColor(0.575f, 0.4f, 0.7f);
		Color darkGrayColor = Color.getHSBColor(0.575f, 0.2f, 0.7f);
		Color lightGrayColor = Color.getHSBColor(0.575f, 0.2f, 0.85f);

		GridBagConstraints con = new GridBagConstraints();

		// number
        con.fill = GridBagConstraints.BOTH;
        con.weightx = 0.5;
        con.insets = new Insets(5,5,5,5);
        con.gridwidth = 1;
        con.gridx = 0;      // used for grid placement
        con.gridy = 3;      // used for grid placement

		numPanel.colorPanel(blueColor, lightGrayColor);
		numPanel.addButtonsToPanel(con, "number grid");

		// functions panel
		con.weightx = 0.5;
        con.gridwidth = 1;
		con.gridx = 0;
		con.gridy = 0;

		funcPanel.colorPanel(darkGrayColor, blueColor);
		funcPanel.addButtonsToPanel(con, "horizontal");


		// trig panel
        con.weightx = 0.5;
        con.gridwidth = 1;
		con.gridx = 0;
		con.gridy = 0;

		trigPanel.colorPanel(darkGrayColor, blueColor);
		trigPanel.addButtonsToPanel(con, "horizontal");


		// inverse trig panel
        con.weightx = 0.5;
        con.gridwidth = 1;
		con.gridx = 0;
		con.gridy = 0;

		invTrigPanel.colorPanel(blueColor, darkGrayColor);
		invTrigPanel.addButtonsToPanel(con, "horizontal");

		// primitive operations panel
        con.weightx = 0.5;
        con.gridwidth = 1;
		con.gridx = 0;
		con.gridy = 0;

		primPanel.colorPanel(blueColor, darkGrayColor);
		primPanel.addButtonsToPanel(con, "vertical");


		// C panel
		con.gridx = 0;
		con.gridy = 0;

		cPanel.colorPanel(blueColor, darkGrayColor);
		cPanel.addButtonsToPanel(con, "horizontal");


		// add subpanels to main panel

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
        mainPanel.add(numPanel.getJPanel(), con);

		// modify constraints for trigPanel
        con.gridwidth = 10;
        con.gridheight = 1;
        con.weightx = 0.8;
        con.weighty = 0.1;
        con.gridx = 0;
        con.gridy = 0;
        // add to main panel
        mainPanel.add(trigPanel.getJPanel(), con);

		// modify constraints for invTrigPanel
        con.gridwidth = 10;
        con.gridheight = 1;
        con.weightx = 0.8;
        con.weighty = 0.1;
        con.gridx = 0;
        con.gridy = 1;
        // add to main panel
        mainPanel.add(invTrigPanel.getJPanel(), con);

		// modify constraints for cPanel
        con.gridwidth = 1;
        con.gridheight = 1;
        con.weightx = 0.8;
        con.weighty = 0.1;
        con.gridx = 8;
        con.gridy = 2;
        // add to main panel
        frame.add(cPanel.getJPanel(), BorderLayout.EAST);

		// modify constraints for funcPanel
        con.gridwidth = 8;
        con.gridheight = 1;
        con.weightx = 0.8;
        con.weighty = 0.1;
        con.gridx = 0;
        con.gridy = 2;
        // add to main panel
        mainPanel.add(funcPanel.getJPanel(), con);

		// modify constraints for primPanel
		con.gridwidth = 1;
        con.gridheight = 9;
        con.weightx = 0.1;
        con.weighty = 0.2;
        con.gridx = 8;
        con.gridy = 2;
		
        // add to main panel
        mainPanel.add(primPanel.getJPanel(), con);
	}
}
