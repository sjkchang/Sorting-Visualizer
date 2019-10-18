package Sorting;

import java.awt.Color;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Frame extends JFrame {
    private final int MAX_SPEED = 200;
    private final int MIN_SPEED = 1;
    private final int MAX_SIZE = 500;
    private final int MIN_SIZE = 1;
    private final int DEFAULT_SPEED = 10;
    private final int DEFAULT_SIZE = 50;

    private final String[] Sorts = {"Bubble",  "Bubble(fast)", "Selection", "Selection(fast)", "Insertion", "Insertion(fast)"};

    private int sizeMod;

    private JPanel wrapper, arrayWrapper, buttonWrapper;
    private JPanel[] arrayRects;
    private JButton startButton;
    private JComboBox<String> sortSelection;
    private JSlider speedSlider, sizeSlider;
    private JLabel speedValue, sizeValue;
    private GridBagConstraints constraints;

    public Frame(){
        super("Sorting Visualizer");

        startButton = new JButton("Start");
        buttonWrapper = new JPanel();
        arrayWrapper = new JPanel();
        wrapper = new JPanel();
        sortSelection = new JComboBox<String>();
        speedSlider = new JSlider(MIN_SPEED, MAX_SPEED, DEFAULT_SPEED);
        sizeSlider = new JSlider(MIN_SIZE, MAX_SIZE, DEFAULT_SIZE);
        speedValue = new JLabel("Speed: 10 ms");
        sizeValue = new JLabel("Size: 50 values");
        constraints = new GridBagConstraints();

        for(String s : Sorts) sortSelection.addItem(s);

        arrayWrapper.setLayout(new GridBagLayout());
        wrapper.setLayout(new BorderLayout());

        constraints.insets = new Insets(0,1,0,1);
        constraints.anchor = GridBagConstraints.SOUTH;

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SortingVisualizer.startSort((String) sortSelection.getSelectedItem());
            }
        });

        //Creates Speed Slider
        speedSlider.setMinorTickSpacing(5);
        speedSlider.setMajorTickSpacing(50);
        speedSlider.setPaintTicks(true);
        speedSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                speedValue.setText(("Speed: " + Integer.toString(speedSlider.getValue()) + "ms"));
                validate();
                SortingVisualizer.sleep = speedSlider.getValue();
            }
        });

        //Creates Size Slider
        sizeSlider.setMinorTickSpacing(10);
        sizeSlider.setMajorTickSpacing(100);
        sizeSlider.setPaintTicks(true);
        sizeSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                sizeValue.setText(("Size: " + Integer.toString(sizeSlider.getValue()) + " values"));
                validate();
                SortingVisualizer.toBeSortedSize = sizeSlider.getValue();
            }
        });

        buttonWrapper.add(speedValue);
        buttonWrapper.add(speedSlider);
        buttonWrapper.add(sizeValue);
        buttonWrapper.add(sizeSlider);
        buttonWrapper.add(startButton);
        buttonWrapper.add(sortSelection);

        wrapper.add(buttonWrapper, BorderLayout.NORTH);
        wrapper.add(arrayWrapper);

        add(wrapper);

        setExtendedState(JFrame.MAXIMIZED_BOTH );


        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // preDrawArray reinitializes the array of panels that represent the values. They are set based on the size of the window.
    public void preDrawArray(Integer[] squares){
        arrayRects = new JPanel[SortingVisualizer.toBeSortedSize];
        arrayWrapper.removeAll();
        // 90% of the windows height, divided by the size of the sorted array.
        sizeMod =  (int) ((getHeight()*0.9)/(arrayRects.length));
        for(int i = 0; i<SortingVisualizer.toBeSortedSize; i++){
            arrayRects[i] = new JPanel();
            arrayRects[i].setPreferredSize(new Dimension(SortingVisualizer.blockWidth, squares[i]* sizeMod));
            arrayRects[i].setBackground(Color.black);
            arrayWrapper.add(arrayRects[i], constraints);
        }
        repaint();
        validate();
    }


    public void reDrawArray(Integer[] a){
        reDrawArray(a, -1);
    }

    public void reDrawArray(Integer[] a, int y){
        reDrawArray(a, y, -1);
    }

    public void reDrawArray(Integer[] a, int y, int z){
        reDrawArray(a, y, z, -1);
    }

    // reDrawArray does similar to preDrawArray except it does not reinitialize the panel array.
    public void reDrawArray(Integer[] squares, int working, int comparing, int reading){
        arrayWrapper.removeAll();
        for(int i = 0; i< arrayRects.length; i++){
            arrayRects[i] = new JPanel();
            arrayRects[i].setPreferredSize(new Dimension(SortingVisualizer.blockWidth, squares[i]* sizeMod));
            if (i == working){
                arrayRects[i].setBackground(Color.blue);
            }else if(i == comparing){
                arrayRects[i].setBackground(Color.green);
            }else if(i == reading){
                arrayRects[i].setBackground(Color.yellow);
            }else{
                arrayRects[i].setBackground(Color.black);
            }
            arrayWrapper.add(arrayRects[i], constraints);
        }
        repaint();
        validate();
    }
}
