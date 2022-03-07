//Sukhman Singh 18041216
package Question1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FactorySimulatorGUI extends JPanel implements ActionListener
{
    private JLabel amountLabel;
    private JButton addDispatchButton;
    private JButton removeDispatchButton;
    private JLabel conLabel;
    private JSlider maxConsumptionSlider;
    private JButton addMachineButton;
    private JButton removeMachineButton;
    private JLabel proLabel;
    private JSlider maxProductionSlider;
    private DrawPanel drawPanel;
    private Timer timer;
    private ArrayList<ConveyorBelt> beltArray;
    private ArrayList<Dispatcher> dispatchArray;
    private ArrayList<Machine> machineArray;
    
    public FactorySimulatorGUI()
    {
        super(new BorderLayout());
        JPanel southPanel = new JPanel(new GridBagLayout());
        JPanel northPanel = new JPanel();     
        GridBagConstraints c = new GridBagConstraints();
        
        c.insets = new Insets(12,5,0,5);
        c.gridx = 0;
        c.gridy = 0;
        addDispatchButton = new JButton("Add Dispatcher");
        addDispatchButton.addActionListener(this);
        southPanel.add(addDispatchButton, c);
        
        c.gridx = 1;
        c.gridy = 0;
        removeDispatchButton = new JButton("Remove Dispatcher");
        removeDispatchButton.addActionListener(this);
        southPanel.add(removeDispatchButton, c);
        
        c.insets = new Insets(0,5,0,5);
        c.gridx = 2;
        c.gridy = 0;
        conLabel = new JLabel("Max Consumption Time: 650ms");
        southPanel.add(conLabel, c);
        
        c.insets = new Insets(0,5,5,5);
        c.gridx = 2;
        c.gridy = 1;
        maxConsumptionSlider = new JSlider(JSlider.HORIZONTAL, Machine.MIN_CONSUMPTION_TIME, Machine.MAX_CONSUMPTION_TIME, 650);
        maxConsumptionSlider.addChangeListener(new ConListener());
        southPanel.add(maxConsumptionSlider,c);
        
        c.insets = new Insets(12,5,0,5);
        c.gridx = 3;
        c.gridy = 0;
        addMachineButton = new JButton("Add Machine");
        addMachineButton.addActionListener(this);
        southPanel.add(addMachineButton, c);
        
        c.gridx = 4;
        c.gridy = 0;
        removeMachineButton = new JButton("Remove Machine");
        removeMachineButton.addActionListener(this);
        southPanel.add(removeMachineButton, c);
        
        c.insets = new Insets(0,5,0,5);
        c.gridx = 5;
        c.gridy = 0;
        proLabel = new JLabel("Max Production Time: 650ms");
        southPanel.add(proLabel, c);
        
        c.insets = new Insets(0,5,5,5);
        c.gridx = 5;
        c.gridy = 1;
        maxProductionSlider = new JSlider(JSlider.HORIZONTAL, Machine.MIN_PRODUCTION_TIME, Machine.MAX_PRODUCTION_TIME, 650);
        maxProductionSlider.addChangeListener(new ProListener());
        southPanel.add(maxProductionSlider, c);
      
        add(southPanel,BorderLayout.SOUTH);
        drawPanel = new DrawPanel();
        add(drawPanel,BorderLayout.CENTER);
        
        timer = new Timer(7,this);
        timer.start();
        
        beltArray = new ArrayList<>();
        for(int i = 0; i < 5; i++)
        {
            beltArray.add(new ConveyorBelt(8));
        }
        
        dispatchArray = new ArrayList<>();
        machineArray = new ArrayList<>();
        
        amountLabel = new JLabel(">>> Number of Dispatchers = "+dispatchArray.size()
                +", Number of Machines = "+machineArray.size());
        northPanel.add(amountLabel);
        add(northPanel, BorderLayout.NORTH);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Object source = e.getSource();
        
        if(source == addDispatchButton)
        {
            Dispatcher dispatch = new Dispatcher(beltArray);
            dispatchArray.add(dispatch);
            
            Thread dispatchThread = new Thread(dispatch);
            dispatchThread.start();
        }
        
        if(source == removeDispatchButton)
        {
            if(!dispatchArray.isEmpty())
            {
                dispatchArray.get(dispatchArray.size()-1).kill();
                dispatchArray.remove(dispatchArray.size()-1);
            }
        }
        
        if(source == addMachineButton)
        {
            Machine machine = new Machine(beltArray);
            machineArray.add(machine);
            
            Thread machineThread = new Thread(machine);
            machineThread.start();
        }
        
        if(source == removeMachineButton)
        {
            if(!machineArray.isEmpty())
            {
                machineArray.get(machineArray.size()-1).kill();
                machineArray.remove(machineArray.size()-1);
            }
        }
        
        if(source == timer)
        {
            drawPanel.repaint();
        }
        
        amountLabel.setText(">>> Number of Dispatchers = "+dispatchArray.size()
                +", Number of Machines = "+machineArray.size());
    }

    private class ConListener implements ChangeListener 
    {
        @Override
        public void stateChanged(ChangeEvent e) 
        {
            JSlider source = (JSlider)e.getSource();
            
            if(!source.getValueIsAdjusting())
            {
                Machine.MAX_CONSUMPTION_TIME = (int)source.getValue();
                conLabel.setText("Max Consumption Time: "+(int)source.getValue()+"ms");
            }
        }
    }
    
    private class ProListener implements ChangeListener 
    {
        @Override
        public void stateChanged(ChangeEvent e) 
        {
            JSlider source = (JSlider)e.getSource();
            
            if(!source.getValueIsAdjusting())
            {
                Machine.MAX_PRODUCTION_TIME = (int)source.getValue();
                proLabel.setText("Max Production Time: "+Machine.MAX_PRODUCTION_TIME+"ms");
            }
        }
    }
    
    private class DrawPanel extends JPanel
    {
        public DrawPanel()
        {
            super();
            setPreferredSize(new Dimension(500,500));
            setBackground(Color.LIGHT_GRAY);  
        }
        
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            int x = 115;
            int y = 35;
            
            for(int i = 0; i < beltArray.size(); i++)
            {
                beltArray.get(i).drawBelt(g, x, y, 90, 70);
                y += 90;
            }
        }
    }
    
    public static void main(String[] args) 
    {
        JFrame frame = new JFrame("Factory Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new FactorySimulatorGUI());
        frame.pack();
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenDimension = tk.getScreenSize();
        Dimension frameDimension = frame.getSize();
        
        frame.setLocation((screenDimension.width - frameDimension.width)/2,(screenDimension.height-frameDimension.height)/2);
        frame.setVisible(true);
    }
    
    
}
