/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package calculatordemo2;

import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
// import java.lang.reflect.Field;  // This brings in the Field feature of Java Reflection

class CalculatorUITest {

    private static CalculatorUI classUnderTest;

    @BeforeAll
    public static void setUp() {
        classUnderTest = new CalculatorUI();
    }

    @DisplayName("Testing that writer writes the display: assumes public fields")
    @Test
    public void writerSetText() {
        JTextArea textAreaUnderTest = classUnderTest.text;
        Double numberToWrite = 22.2;
        classUnderTest.writer(numberToWrite);
        // Test that the value of “text” is “mytext”
        assertEquals(numberToWrite.toString(), textAreaUnderTest.getText());
    }

    @DisplayName("Testing that reader accurately reads text")
    @Test
    public void readerGetText() {
        JTextArea textAreaUnderTest = classUnderTest.text;
        Double value = 10.0;
        Double read = 0.0;
        textAreaUnderTest.setText(value.toString());
        read = classUnderTest.reader();
        // test that value from reader() == value
        assertEquals(read, value);
    }

    @DisplayName("Testing Button[0] writes zero to display: assumes public fields ")
    @Test
    public void writeZeroToDisplay() {
       
        ActionEvent e = new ActionEvent(classUnderTest.jButtons[0], 
                                        ActionEvent.ACTION_PERFORMED, 
                                        "");
        classUnderTest.actionPerformed(e);
        String expectedDisplayText = classUnderTest.buttonValue[0];
        String actualDisplayText = classUnderTest.text.getText();
        assertEquals(expectedDisplayText,actualDisplayText);
    }

    @Test 
    void appPanelIsCreated() {
        assertNotNull(classUnderTest, "app should have a panel object");
    }
}

