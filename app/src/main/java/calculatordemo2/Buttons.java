package calculatordemo2;

import java.util.ArrayList;
import javax.swing.JButton;


// a separate class to create buttons to easily add more buttons
public class Buttons {

    public JButton add, sub, mult, div, equal, cancel, sqrRt, sqr, inverse, cos, sin, tan, acos, asin, atan;
    private ArrayList<JButton> numButtons;
    private ArrayList<JButton> primButtons;
    private ArrayList<JButton> trigButtons;
    private ArrayList<JButton> invTrigButtons;
    private ArrayList<JButton> funcButtons;
    private ArrayList<JButton> miscButtons;

    Buttons() {
        numButtons = new ArrayList<>();
        primButtons = new ArrayList<>();
        trigButtons = new ArrayList<>();
        invTrigButtons = new ArrayList<>();
        funcButtons = new ArrayList<>();
        miscButtons = new ArrayList<>();
        createButtons();
    }


    // creates buttons and adds to appropriate list
    private void createButtons() {
        String value = "";
		JButton buttonHolder;
		for (int i = 0; i < 10; i++) {
			value = String.valueOf(i);
			buttonHolder = new JButton(value);
			numButtons.add(buttonHolder);
		}

        add = new JButton("+");
		primButtons.add(add);

		sub = new JButton("-");
		primButtons.add(sub);

		mult = new JButton("*");
		primButtons.add(mult);

		div = new JButton("/");
		primButtons.add(div);

		equal = new JButton("=");
		primButtons.add(equal);



		sqrRt = new JButton("√");
		funcButtons.add(sqrRt);

		sqr = new JButton("x*x");
		funcButtons.add(sqr);

		inverse = new JButton("1/x");
		funcButtons.add(inverse);



		cos = new JButton("Cos");
		trigButtons.add(cos);

		sin = new JButton("Sin");
		trigButtons.add(sin);

		tan = new JButton("Tan");
		trigButtons.add(tan);



		acos = new JButton("cos^-1");
		invTrigButtons.add(acos);

		asin = new JButton("sin^-1");
		invTrigButtons.add(asin);

		atan = new JButton("tan^-1");
		invTrigButtons.add(atan);



		cancel = new JButton("C");
        miscButtons.add(cancel);
    }

    public ArrayList<JButton> getNumButtons() {
        return numButtons;
    }

    public ArrayList<JButton> getPrimButtons() {
        return primButtons;
    }

    public ArrayList<JButton> getTrigButtons() {
        return trigButtons;
    }

    public ArrayList<JButton> getInvTrigButtons() {
        return invTrigButtons;
    }

    public ArrayList<JButton> getFuncButtons() {
        return funcButtons;
    }

    public ArrayList<JButton> getMiscButtons() {
        return miscButtons;
    }

    // returns the JButton object with corresponding label
    public JButton get(String label) {
        for (JButton button : numButtons) {
            if (label.equals(button.getText())) { return button; }
        }
        for (JButton button : primButtons) {
            if (label.equals(button.getText())) { return button; }
        }
        for (JButton button : trigButtons) {
            if (label.equals(button.getText())) { return button; }
        }
        for (JButton button : invTrigButtons) {
            if (label.equals(button.getText())) { return button; }
        }
        for (JButton button : funcButtons) {
            if (label.equals(button.getText())) { return button; }
        }
        for (JButton button : miscButtons) {
            if (label.equals(button.getText())) { return button; }
        }
        return new JButton("null"); // never reached if called properly
    }
    
}
