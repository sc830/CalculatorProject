package calculatordemo2;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

public class CalculatorPanel {

    public JPanel panel;
    protected ArrayList<JButton> buttonList;

    CalculatorPanel() {
        buttonList = new ArrayList<>();
        panel = new JPanel(new GridBagLayout());
        panel.setVisible(true);
        panel.setBorder(BorderFactory.createEmptyBorder());
    }

    // overriden constructor: adds listener to buttons
    CalculatorPanel(ArrayList<JButton> buttons, CalculatorUI listener) {
        buttonList = new ArrayList<>();
        panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder());
        for (JButton button : buttons) {
            buttonList.add(button);
            button.addActionListener(listener);
        }
    }
    
    public void colorPanel(Color panelColor, Color buttonColor) {
        for (JButton button : buttonList) {
            panel.setBackground(panelColor);
            button.setBackground(buttonColor);
        }
    }

    // overriden function to change button foreground color
    public void colorPanel(Color panelColor, Color buttonColor, Color textColor) {
        for (JButton button : buttonList) {
            panel.setBackground(panelColor);
            button.setBackground(buttonColor);
            button.setForeground(textColor);
        }
    }

    public void addButtonsToPanel(GridBagConstraints con, String orientation) {

        if (orientation.equals("horizontal")) {
            con.gridx = 0;
            con.gridy = 0;

            for (JButton button : buttonList) {
                panel.add(button, con);
                con.gridx++;
            }
        }
        else if (orientation.equals("vertical")) {
            con.gridx = 0;
            con.gridy = 0;
            
            for (JButton button : buttonList) {
                panel.add(button, con);
                con.gridy++;
            }
        }
        else if (orientation.equals("number grid")) {
            System.out.println("number panel grid");
            con.gridx = 0;
            con.gridy = 3;
            
                for (int i = 0; i < buttonList.size(); i++) {
                    if (i == 0) {
                        con.gridwidth = 3;    
                    }
            
                    panel.add(buttonList.get(i), con);
                    con.gridwidth = 1;
            
                    if (i != 0) {
                        con.gridx++;
                    }
                    else {
                        con.gridy--;
                    }
                        
                    if (con.gridx == 3) {
                        con.gridx = 0;
                        con.gridy--;
                    }
                }
        }
    }

    public ArrayList<JButton> getButtonList() {
        return buttonList;
    }

    public JPanel getJPanel() {
        return panel;
    }
    
}
