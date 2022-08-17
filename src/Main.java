import java.awt.*;

import javax.swing.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import java.text.*;
import java.util.Arrays;

public class Main extends JPanel
                  implements PropertyChangeListener{
    /** declare variable */
    private String[] mods;
    private String[] remainders;
    private int result;
    /** Labels to identify the field */
    private JLabel modsLabel;
    private JLabel remaindersLabel;
    private JLabel resultLabel;

    /** Strings for the labels */
    private static String modsString = "Numbers you would like to mod by (space-separated): ";
    private static String remaindersString = "Remainders (space-separated): ";
    private static String resultString = "Result: ";

    /** Fields for data entry */
    private JFormattedTextField modsField;
    private JFormattedTextField remaindersField;
    private JFormattedTextField resultField;

    /** Formats to format and parse numbers */
    private NumberFormat modsFormat;
    private NumberFormat remaindersFormat;
    private NumberFormat resultFormat;

    public Main() {
        super(new BorderLayout());
        //setUpFormats();
        // add a line computing the result
        /** create the labels */
        modsLabel = new JLabel(modsString);
        remaindersLabel = new JLabel(remaindersString);
        resultLabel = new JLabel(resultString);
        /** create text fields and set them up */
        modsField = new JFormattedTextField(modsFormat);
        modsField.setValue("no mods");
        modsField.setColumns(10);
        modsField.addPropertyChangeListener("value", this);

        remaindersField = new JFormattedTextField(remaindersFormat);
        remaindersField.setValue("no remainders");
        remaindersField.setColumns(10);
        remaindersField.addPropertyChangeListener("value", this);

        resultField = new JFormattedTextField(resultFormat);
        resultField.setValue("no result");
        resultField.setColumns(10);
        resultField.setEditable(false);
        resultField.setForeground(Color.red);

        /** tell accessibility tools */
        modsLabel.setLabelFor(modsField);
        remaindersLabel.setLabelFor(remaindersField);
        resultLabel.setLabelFor(resultField);

        /** lay out the labels in a panel */
        JPanel labelPane = new JPanel(new GridLayout(0,1));
        labelPane.add(modsLabel);
        labelPane.add(remaindersLabel);
        labelPane.add(resultLabel);
        /** layout the text fields in a panel */
        JPanel fieldPane = new JPanel(new GridLayout(0,1));
        fieldPane.add(modsField);
        fieldPane.add(remaindersField);
        fieldPane.add(resultField);
        /** put panels in this panel, labels on left, text fields on right */
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(labelPane, BorderLayout.CENTER);
        add(fieldPane, BorderLayout.LINE_END);
    }
    /** Called when a field's "value" property changes. */
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        Object source = e.getSource();
        if (source == modsField) {
            mods = (modsField.getText().split(" ")); //maybe wrong
        } else if (source == remaindersField) {
            remainders = (remaindersField.getText().split(" "));
        }
        if(modsField.getText().split(" ").length == remaindersField.getText().split(" ").length) {
            int result = Calculator.solve(mods, remainders);
            resultField.setValue(result); // check this
        }

    }
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("gui");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add contents to the window.
        frame.add(new Main()); // just seems wrong

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }



    public static void main (String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });


        /*
        Scanner input = new Scanner(System.in);
        System.out.println("Please list the numbers you would like to mod by. (Space separated): \n");
        String[] mods = input.nextLine().split(" ");
        System.out.println("\nPlease list the remainder when modding by each of the numbers already listed respectively. (Space separated):\n");
        String[] remainders = input.nextLine().split(" ");
        int result = Calculator.solve(mods, remainders);
        System.out.println("The answer is: " + result);

         */
    }
}
