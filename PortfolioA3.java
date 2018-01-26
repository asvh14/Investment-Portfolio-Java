/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portfolioa3;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 *
 * @author asvh1
 */
public class PortfolioA3 extends JFrame
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        
        PortfolioA3 gui = new PortfolioA3(args);
        gui.setVisible(true);
        
        if(args.length < 1)
        {    
            System.out.println("No File Listed");
        }

        if(gui.readFileIO(args[0]) == 1)
        {
            System.out.println("File is empty, nothing to read in");
        }
    }
    
    double[] oldPrice;
    double[] newPrice;
    ArrayList<InvestmentA3> investments;
    ArrayList<InvestmentA3> tempInvestment;
    HashMap<String, ArrayList<Integer>> hashMap;
    
    
    //GUI variables
    public JPanel mainPanel;
    public JPanel menuPanel;
    
    public JPanel optionPanel;
    public JPanel actionPanel;
    public JPanel messagePanel;
    public JPanel getGainPanel;

    public JLabel menuTitle;
    public JTextArea menuMessage;
    
    public JLabel messageTitle;
    public JTextArea messages;
    public JScrollPane messageScroll;
    
    public JPanel buyOptionPanel;
    public JLabel buyTitle;
    public JLabel buyTypeArea;
    public JLabel buySymbolArea;
    public JLabel buyNameArea;
    public JLabel buyQuantityArea;
    public JLabel buyPriceArea;
    public JComboBox buyTypeComboBox;
    public JTextField buySymbolField;
    public JTextField buyNameField;
    public JTextField buyQuantityField;
    public JTextField buyPriceField;
    
    public JPanel buyActionPanel;
    public JButton buyReset;
    public JButton buyBuy;
    
    public JPanel sellOptionPanel;
    public JLabel sellTitle;
    public JLabel sellSymbolArea;
    public JLabel sellQuantityArea;
    public JLabel sellPriceArea;
    public JTextField sellSymbolField;
    public JTextField sellQuantityField;
    public JTextField sellPriceField;   
    
    public JPanel sellActionPanel;
    public JButton sellReset;
    public JButton sellSell;
    
    public JPanel updateOptionPanel;
    public JLabel updateTitle;
    public JLabel updateSymbolArea;
    public JLabel updateNameArea;
    public JLabel updatePriceArea;
    public JTextField updateSymbolField;
    public JTextField updateNameField;
    public JTextField updatePriceField;   
    
    public JPanel updateActionPanel;
    public JButton updatePrev;
    public JButton updateNext;
    public JButton updateSave;
    
    public JPanel getGainOptionPanel;
    public JLabel getGainTitle;
    public JLabel getGainArea;
    public JTextField getGainField;
  
    public JPanel getGainMessage;
    public JLabel getGainMessageTitle;
    public JTextArea getGainOut;
    
    public JPanel searchOptionPanel;
    public JLabel searchTitle;
    public JLabel searchSymbolArea;
    public JLabel searchNameArea;
    public JLabel searchLowArea;
    public JLabel searchHighArea;
    public JTextField searchSymbolField;
    public JTextField searchNameField;
    public JTextField searchLowField;
    public JTextField searchHighField;
    
    public JPanel searchActionPanel;    
    public JButton searchReset;
    public JButton searchSearch;
   
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem buy;
    private JMenuItem sell;
    private JMenuItem update;
    private JMenuItem getGain;
    private JMenuItem search;
    
    private ButtonGroup file;
    private JMenuItem quit;
    public int updateItr = 0;
    public double[] oldUpdateBV;
    
    /**
     * creation and formatting of GUI
     * @param args argument list
     */
    public PortfolioA3(String[] args)
    {
        this.investments = new ArrayList<InvestmentA3>();
        this.hashMap = new HashMap<String, ArrayList<Integer>>();
        
        this.setSize(800,300);
        this.setTitle("Investment Portfolio");
        SpringLayout layout = new SpringLayout();
        getContentPane().setLayout(layout);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
                
        
        JMenuItem buyMI = new JMenuItem("Buy");
        JMenuItem sellMI = new JMenuItem("Sell");
        JMenuItem updateMI = new JMenuItem("Update");
        JMenuItem getGainMI = new JMenuItem("Get Gain");
        JMenuItem searchMI = new JMenuItem("Search");
        JMenuItem quitMI = new JMenuItem("Quit");
        
        //JButton
        
        JMenu commandsMenu = new JMenu("Commands");
        commandsMenu.add(buyMI);
        commandsMenu.add(sellMI);
        commandsMenu.add(updateMI);
        commandsMenu.add(getGainMI);
        commandsMenu.add(searchMI);
        commandsMenu.add(quitMI);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(commandsMenu);
        
        this.setJMenuBar(menuBar);
        
        mainPanel = new JPanel();
        menuPanel = new JPanel();
        
        menuTitle = new JLabel ("Welcome to Investment Portfolio");
        menuMessage = new JTextArea ("Choose a command above to buy or sell an investment, update prices for all investments, get gain for the portfolio, search for relevant investments, or quit the program");
        
        messagePanel = new JPanel();
        messageTitle = new JLabel("Messages");
        messages = new JTextArea();
        messages.setBackground(Color.RED);
        
        messageScroll = new JScrollPane(messages);
        
        buyOptionPanel = new JPanel();
        buyTitle = new JLabel("Buy");
        buyTypeArea = new JLabel("Type");    
        buySymbolArea = new JLabel("Symbol");
        buyNameArea = new JLabel("Name");
        buyQuantityArea = new JLabel("Quantity");
        buyPriceArea = new JLabel("Price");
        buyTypeComboBox = new JComboBox();
        buyTypeComboBox.addItem("Stock");
        buyTypeComboBox.addItem("MutualFund");
        buySymbolField = new JTextField();
        buyNameField = new JTextField();
        buyQuantityField = new JTextField();
        buyPriceField = new JTextField();
        buyActionPanel = new JPanel();
        buyReset = new JButton("Reset");
        buyBuy = new JButton("Buy");
        
        sellOptionPanel = new JPanel();
        sellTitle = new JLabel("Sell");
        sellSymbolArea = new JLabel("Symbol");
        sellQuantityArea = new JLabel("Quantity");
        sellPriceArea = new JLabel("Price");
        sellSymbolField = new JTextField();
        sellQuantityField = new JTextField();
        sellPriceField = new JTextField();
        sellActionPanel = new JPanel();
        sellReset = new JButton("Reset");
        sellSell = new JButton("Sell");
        
        updateOptionPanel = new JPanel();
        updateTitle = new JLabel ("Update");
        updateSymbolArea = new JLabel ("Symbol");
        updateNameArea = new JLabel ("Name");
        updatePriceArea = new JLabel ("Price");
        updateSymbolField = new JTextField();
        updateNameField = new JTextField();
        updatePriceField = new JTextField();
        updateActionPanel = new JPanel();
        updatePrev = new JButton("Prev");
        updateNext = new JButton("Next");
        updateSave = new JButton("Save");
        
        getGainOptionPanel = new JPanel();
        getGainTitle = new JLabel ("Get Gain");
        getGainArea = new JLabel ("Total Gain");
        getGainField = new JTextField();
        getGainMessage = new JPanel();
        getGainMessageTitle = new JLabel ("Total Gain");
        getGainOut = new JTextArea ();
        
        searchOptionPanel = new JPanel();
        searchTitle = new JLabel("Search");
        searchSymbolArea = new JLabel("Symbol");
        searchNameArea = new JLabel("Name");
        searchLowArea = new JLabel("Low Price");
        searchHighArea = new JLabel("High Price");
        searchSymbolField = new JTextField();
        searchNameField	= new JTextField();
        searchLowField = new JTextField();
        searchHighField	= new JTextField();
        searchActionPanel = new JPanel(); 
        searchReset = new JButton("Reset");
        searchSearch = new JButton("Search");
        
        mainPanel.setBounds((new Rectangle(0,0,400,300)));
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.GREEN);
        
        menuPanel.setBounds((new Rectangle(0,0,400,300)));
        menuPanel.setLayout(null);
        menuPanel.setBackground(Color.GREEN);
        menuTitle.setBounds(20, 14, 200, 20);
        menuMessage.setBounds(20, 36, 350, 150);
        
        messagePanel.setBounds((new Rectangle(300,0,500,250)));
        messagePanel.setLayout(null);
        messagePanel.setBackground(Color.GREEN);
        messageTitle.setBounds(220, 5, 120, 20);
        messageScroll.setBounds(120, 25, 350, 200);
        
        buyOptionPanel.setBounds((new Rectangle(0,0,268,180)));
        buyOptionPanel.setLayout(null);
        buyOptionPanel.setBackground(Color.GREEN);
        buyTitle.setBounds(20, 14, 120, 20);          
        buyTypeArea.setBounds(20, 36, 120, 20);    
        buySymbolArea.setBounds(20, 58, 120, 20);          
        buyNameArea.setBounds(20, 80, 120, 20);        
        buyQuantityArea.setBounds(20, 102, 120, 20);         
        buyPriceArea.setBounds(20, 124, 120, 20);      
        buyTypeComboBox.setBounds(120, 33, 120, 23);        
        buySymbolField.setBounds(120, 58, 120, 20);        
        buyNameField.setBounds(120, 80, 120, 20);           
        buyQuantityField.setBounds(120, 102, 120, 20);      
        buyPriceField.setBounds(120, 124, 120, 20);
        buyActionPanel.setBounds((new Rectangle(268,0,132,180)));
        buyActionPanel.setLayout(new GridLayout(2,1));
        buyActionPanel.setBackground(Color.GREEN);
        
        sellOptionPanel.setBounds((new Rectangle(0,0,268,180)));
        sellOptionPanel.setLayout(null);
        sellOptionPanel.setBackground(Color.GREEN);
        sellTitle.setBounds(20, 14, 120, 20);          
        sellSymbolArea.setBounds(20, 36, 120, 20);          
        sellQuantityArea.setBounds(20, 58, 120, 20);
        sellPriceArea.setBounds(20, 80, 120, 20);
        sellSymbolField.setBounds(120, 36, 120, 20);         
        sellQuantityField.setBounds(120, 58, 120, 20);      
        sellPriceField.setBounds(120, 80, 120, 20); 
        sellActionPanel.setBounds((new Rectangle(268,0,132,180)));
        sellActionPanel.setLayout(new GridLayout(2,1));
        sellActionPanel.setBackground(Color.GREEN);
        
        updateOptionPanel.setBounds((new Rectangle(0,0,268,180)));
        updateOptionPanel.setLayout(null);
        updateOptionPanel.setBackground(Color.GREEN);
        updateTitle.setBounds(20, 14, 120, 20);          
        updateSymbolArea.setBounds(20, 36, 120, 20);          
        updateNameArea.setBounds(20, 58, 120, 20);        
        updatePriceArea.setBounds(20, 80, 120, 20);             
        updateSymbolField.setBounds(120, 36, 120, 20);         
        updateNameField.setBounds(120, 58, 120, 20);      
        updatePriceField.setBounds(120, 80, 120, 20);      
        updateActionPanel.setBounds((new Rectangle(268,0,132,180)));
        updateActionPanel.setLayout(new GridLayout(3,1));
        updateActionPanel.setBackground(Color.GREEN);
        
        getGainOptionPanel.setBounds((new Rectangle(0,0,400,100)));
        getGainOptionPanel.setLayout(null);
        getGainOptionPanel.setBackground(Color.GREEN);
        getGainTitle.setBounds(20, 14, 120, 20);          
        getGainArea.setBounds(20, 36, 120, 20);          
        getGainField.setBounds(120, 36, 150, 20);
        getGainMessage.setBounds((new Rectangle(0,100,400,200)));
        getGainMessage.setLayout(null);
        getGainMessageTitle.setBounds(10, 14, 120, 20);          
        getGainOut.setBounds(420, 14, 355, 200);
        
        searchOptionPanel.setBounds((new Rectangle(0,0,268,180)));
        searchOptionPanel.setLayout(null);
        searchOptionPanel.setBackground(Color.GREEN);
        searchTitle.setBounds(20, 14, 120, 20);          
        searchSymbolArea.setBounds(20, 36, 120, 20);          
        searchNameArea.setBounds(20, 58, 120, 20);        
        searchHighArea.setBounds(20, 80, 120, 20);    
        searchLowArea.setBounds(20, 102, 120, 20);  
        searchSymbolField.setBounds(120, 36, 120, 20);         
        searchNameField.setBounds(120, 58, 120, 20);      
        searchHighField.setBounds(120, 80, 120, 20);    
        searchLowField.setBounds(120, 102, 120, 20);
        searchActionPanel.setBounds((new Rectangle(268,0,132,180)));
        searchActionPanel.setLayout(new GridLayout(2,1));
        searchActionPanel.setBackground(Color.GREEN);
        
        menuMessage.setLineWrap(true);
        menuMessage.setWrapStyleWord(true);
        menuMessage.setEditable(false);
        
        messages.setEditable(false);
        messages.setLineWrap(true);
        messages.setWrapStyleWord(true);
        
        updateSymbolField.setEditable(false);
        updateNameField.setEditable(false);
        updatePriceField.setEditable(true);
        
        getGainField.setEditable(false);
        getGainOut.setEditable(false);
        
        //make search fields editable
        
        
        menuPanel.add(menuTitle);
        menuPanel.add(menuMessage);
        
        messagePanel.add(messageTitle);
        messagePanel.add(messageScroll);

        buyOptionPanel.add(buyTitle);
        buyOptionPanel.add(buyTypeArea);
        buyOptionPanel.add(buySymbolArea );
        buyOptionPanel.add(buyNameArea);
        buyOptionPanel.add(buyQuantityArea);
        buyOptionPanel.add(buyPriceArea);
        buyOptionPanel.add(buyTypeComboBox);
        buyOptionPanel.add(buySymbolField);
        buyOptionPanel.add(buyNameField );
        buyOptionPanel.add(buyQuantityField);
        buyOptionPanel.add(buyPriceField);
        buyActionPanel.add(buyReset);
        buyActionPanel.add(buyBuy);

        sellOptionPanel.add(sellTitle);
        sellOptionPanel.add(sellSymbolArea);
        sellOptionPanel.add(sellQuantityArea);       
        sellOptionPanel.add(sellPriceArea);
        sellOptionPanel.add(sellSymbolField);
        sellOptionPanel.add(sellQuantityField);
        sellOptionPanel.add(sellPriceField);
        sellActionPanel.add(sellReset);
        sellActionPanel.add(sellSell);
        
        updateOptionPanel.add(updateTitle);
        updateOptionPanel.add(updateSymbolArea);
        updateOptionPanel.add(updateNameArea);
        updateOptionPanel.add(updatePriceArea);
        updateOptionPanel.add(updateSymbolField);
        updateOptionPanel.add(updateNameField);
        updateOptionPanel.add(updatePriceField);

        getGainOptionPanel.add(getGainTitle);
        getGainOptionPanel.add(getGainArea);
        getGainOptionPanel.add(getGainField);
        getGainMessage.add(getGainMessageTitle);
        getGainMessage.add(getGainOut);

        searchOptionPanel.add(searchTitle);
        searchOptionPanel.add(searchSymbolArea);
        searchOptionPanel.add(searchNameArea);
        searchOptionPanel.add(searchHighArea);
        searchOptionPanel.add(searchLowArea);
        searchOptionPanel.add(searchSymbolField);
        searchOptionPanel.add(searchNameField);
        searchOptionPanel.add(searchHighField);
        searchOptionPanel.add(searchLowField);
        searchActionPanel.add(searchReset);
        searchActionPanel.add(searchSearch);

        quitMI.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                messages.setText("");
                writeFileIO(args[0]);
                
                System.exit(0);
            }
        });
        
        buyMI.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                messages.setText("");
                
                mainPanel.removeAll();
                mainPanel.add(buyOptionPanel);
                mainPanel.add(buyActionPanel);
                mainPanel.add(messagePanel);
        
                setContentPane(mainPanel);
            }
        });
        
                
        sellMI.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                messages.setText("");
                
                mainPanel.removeAll();
                mainPanel.add(sellOptionPanel);
                mainPanel.add(sellActionPanel);
                mainPanel.add(messagePanel);
        
                setContentPane(mainPanel);
            }
        });
        
        updateMI.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                boolean valid = true;
                try
                {
                    if(valid == true)
                    {
                        messages.setText("");

                        mainPanel.removeAll();
                        mainPanel.add(updateOptionPanel);
                        mainPanel.add(updateActionPanel);
                        mainPanel.add(messagePanel);

                        setContentPane(mainPanel);

                        int i = 0;

                        newPrice = new double[investments.size()];
                        oldPrice = new double[investments.size()];
                        oldUpdateBV = new double[investments.size()];

                        for(int m = 0; m < oldPrice.length; m++)
                        {
                            oldPrice[i] = investments.get(i).getPrice();
                        }

                        //loop through entire array, assign value
                        for(int k = 0; k < newPrice.length; k++)
                        {
                            newPrice[i] = 0;
                        }

                        updateSymbolField.setText(investments.get(0).getSymbol());
                        updateNameField.setText(investments.get(0).getName());
                    }
                }
                catch(IndexOutOfBoundsException ie)
                {
                    messages.setText("No Investments to update");
                    valid = false;
                }
            }
        });
        
        getGainMI.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                //refresh
                messages.setText("");
                
                mainPanel.removeAll();
                mainPanel.add(getGainOptionPanel);
                mainPanel.add(messagePanel);
                
                setContentPane(mainPanel);
                
                double totalGain = 0; 
                double gain = 0;
                int i;
                int j;
                
                messages.setText("Gain on each Investment\n");

                //loop through entire array list, add new gain onto itself
                for(i = 0; i < investments.size(); i++)
                {
                    if(investments.get(i).getType().equalsIgnoreCase("mutualfund"))
                    {
                        gain = (investments.get(i).getQuantity() * investments.get(i).getPrice()) - investments.get(i).getBookValue();// - oldBV[i];
                        totalGain += gain;
                        messages.append(investments.get(i).getName());
                        messages.append(String.valueOf(gain)+"\n");
                    }

                    else if(investments.get(i).getType().equalsIgnoreCase("stock"))
                    {
                        gain = (investments.get(i).getQuantity() * investments.get(i).getPrice() - 9.99) - investments.get(i).getBookValue();
                        totalGain += gain;
                        messages.append(investments.get(i).getName());
                        messages.append(String.valueOf(gain)+"\n");
                    }
                }
                
                getGainField.setText(String.valueOf(totalGain));
            }
        });
        
        searchMI.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                messages.setText("Please separate keywords with just a comma");
                
                mainPanel.removeAll();
                mainPanel.add(searchOptionPanel);
                mainPanel.add(searchActionPanel);
                mainPanel.add(messagePanel);
                
                setContentPane(mainPanel);
            }
        });
        
        mainPanel.add(menuPanel);
        
        updateActionPanel.add(updatePrev);
        updateActionPanel.add(updateNext);
        updateActionPanel.add(updateSave);
        
        this.setContentPane(mainPanel);
        //this.add(mainPanel);
        
        //save to file
        
        buyReset.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                //refresh
                buySymbolField.setText("");
                buyNameField.setText("");
                buyQuantityField.setText("");
                buyPriceField.setText("");
                
                messages.setText("");
            }
        });
        
        buyBuy.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                boolean valid = true;
                int counter = 0;
               
                try
                {
                    if(valid == true)
                    {
                        int validQ = Integer.parseInt(buyQuantityField.getText());
                        double validPrice = Double.parseDouble(buyPriceField.getText());
                        double bv = 0;
                        InvestmentA3 temp;
                        
                        if(buyTypeComboBox.getSelectedItem().equals("Stock"))
                        {
                            bv = validQ * validPrice;
                        }
                        
                        else if(buyTypeComboBox.getSelectedItem().equals("MutualFund"))
                        {
                            bv = validQ * validPrice + 9.99;
                        }
                        
                        for(int i = 0; i < investments.size(); i++)
                        {
                            if(investments.get(i).getName().equals(buyNameField.getText()) && investments.get(i).getSymbol().equals(buySymbolField.getText()))
                            {
                                //investments.get(i).setPrice(valid);
                                messages.setText("Adding to existing Investment");
                                investments.get(i).setQuantity(investments.get(i).getQuantity() + validQ);
                                investments.get(i).setBookValue(investments.get(i).getBookValue() + bv);
                            }
                            
                            else
                            {
                                counter++;
                            }
                        }
                        
                        if(counter == investments.size())
                        {
                            if(buyTypeComboBox.getSelectedItem().equals("Stock"))
                            {    
                                temp = new StockA3(buyTypeComboBox.getSelectedItem().toString(), buySymbolField.getText(), buyNameField.getText(), validQ, validPrice, bv);

                                investments.add(temp);
                                messages.setText(temp.toString());
                            }
                            
                            else if(buyTypeComboBox.getSelectedItem().equals("MutualFund"))
                            {    
                                temp = new MutualFundA3(buyTypeComboBox.getSelectedItem().toString(), buySymbolField.getText(), buyNameField.getText(), validQ, validPrice, bv);

                                investments.add(temp);
                                messages.setText(temp.toString());
                            }
                        }
                    }
                }
                catch(NumberFormatException n)
                {
                    messages.setText("Invalid input, please try again");
                    valid = false;
                }
            }
        });
        
        sellReset.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                sellSymbolField.setText("");
                sellQuantityField.setText("");
                sellPriceField.setText("");
            }
        });
        
        sellSell.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                int tempQ;
                double payment;
                boolean valid = true;
                double oldBV;

                try
                {
                    if(valid == true)
                    {
                        int quanToSell = Integer.parseInt(sellQuantityField.getText());
                        
                        //loop through entire array list and check for a match
                        for(int i = 0; i < investments.size(); i++)
                        {
                            oldBV = investments.get(i).getBookValue();
                            if(investments.get(i).getType().equalsIgnoreCase("Stock"))
                            {
                                //create else statement to deal with no matches
                                if(sellSymbolField.getText().equals(investments.get(i).getSymbol()))
                                {
                                    if(quanToSell < investments.get(i).getQuantity())
                                    {
                                        System.out.println("##"+oldBV *(quanToSell/investments.get(i).getQuantity())+"##");
                                        tempQ = investments.get(i).getQuantity() - quanToSell;
                                        investments.get(i).setQuantity(tempQ);
                                        investments.get(i).setBookValue((oldBV * quanToSell)/investments.get(i).getQuantity());
                                        payment = (quanToSell * Double.parseDouble(sellPriceField.getText())) - 9.99;
                                        messages.setText("Payment: $"+String.valueOf(payment));
                                    }

                                    else
                                    {
                                        //send message to message text area
                                        messages.setText("Quantity you want to sell is greater or equal to quantity owned, deleting account");
                                        investments.remove(i);
                                    }
                                }
                            }

                            else if(investments.get(i).getType().equalsIgnoreCase("MutualFund"))
                            {
                                if(sellSymbolField.getText().equals(investments.get(i).getSymbol()))
                                {
                                    if(quanToSell < investments.get(i).getQuantity())
                                    {
                                        System.out.println("^^"+oldBV+"^^");
                                        tempQ = investments.get(i).getQuantity() - quanToSell;
                                        investments.get(i).setQuantity(tempQ);
                                        investments.get(i).setBookValue((oldBV * quanToSell)/investments.get(i).getQuantity());
                                        payment = (quanToSell * Double.parseDouble(sellPriceField.getText())) - 45.00;
                                        messages.setText("Payment: $"+String.valueOf(payment));
                                    }

                                    else    // >= quantity
                                    {
                                        messages.setText("Quantity you want to sell is greater or equal to quantity owned, deleting account");
                                        investments.remove(i);
                                    }
                                }
                            }
                        }
                    }
                }
                
                catch(NumberFormatException n)
                {
                    messages.setText("Invalid input, please try again");
                    valid = false;
                }
            }
        });
         
        updatePrev.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {              
                try
                {
                    updateItr--;
                    oldUpdateBV[updateItr] = investments.get(updateItr).getBookValue();
                    updateSymbolField.setText(investments.get(updateItr).getSymbol());
                    updateNameField.setText(investments.get(updateItr).getName());
                }
                catch(IndexOutOfBoundsException k)
                {
                    messages.setText("You have reached the end of your portfolio");
                    updateItr++;
                }
            }
        });
        
        updateNext.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                try
                {
                    updateItr++;
                    oldUpdateBV[updateItr] = investments.get(updateItr).getBookValue();
                    updateSymbolField.setText(investments.get(updateItr).getSymbol());
                    updateNameField.setText(investments.get(updateItr).getName());
                }
                catch(IndexOutOfBoundsException k)
                {
                    messages.setText("You have reached the end of your portfolio");
                    updateItr--;
                }
            }
        });
        
        updateSave.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {   
                boolean valid = true;
                
                try
                {
                    if(valid == true)
                    {
                        if(investments.get(updateItr).getType().equalsIgnoreCase("mutualfund"))
                        {
                            newPrice[updateItr] = Double.parseDouble(updatePriceField.getText());
                            investments.get(updateItr).setPrice(Double.parseDouble(updatePriceField.getText()));
                        }

                        else if(investments.get(updateItr).getType().equalsIgnoreCase("stock"))
                        {
                            newPrice[updateItr] = Double.parseDouble(updatePriceField.getText());
                            investments.get(updateItr).setPrice(Double.parseDouble(updatePriceField.getText()));
                        } 
                    }
                }
                catch(NumberFormatException n)
                {
                    messages.setText("Only Enter Numbers");
                    valid = false;
                }
            }
        });
        
        searchReset.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                messages.setText("");
                
                searchSymbolField.setText("");
                searchNameField.setText("");
                searchHighField.setText("");
                searchLowField.setText("");
            }
        });
        
        searchSearch.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                messages.setText("Matching Investments\n");

                ArrayList<Integer> retrievedIndexes = new ArrayList<Integer>();
                
                int counter = 0;
                String keyWords;
                String symbol;
                String priceRange;
                int i = 0;
                int j = 0;

                boolean sFlag = true;
                boolean kFlag = true;
                boolean pFlag = true;
                
                boolean valid = true;

                if("".equals(searchSymbolField.getText()))
                {
                    sFlag = false;
                }

                if("".equals(searchNameField.getText()))
                {
                    kFlag = false;
                }
                
                if("".equals(searchHighField.getText()) && "".equals(searchLowField.getText()))
                {
                    pFlag = false;
                }
                
                else if("".equals(searchHighField) && !("".equals(searchLowField.getText())))
                {
                    searchHighField.setText(String.valueOf(1000000));
                }
                
                else if("".equals(searchLowField) && !("".equals(searchHighField.getText())))
                {
                    searchLowField.setText(String.valueOf(0));
                }
                //loop through every objct in array list
                for(j = 0; j < investments.size(); j++)
                {
                    if(sFlag && kFlag && pFlag)
                    {
                        // all fields entered
                        try
                        {
                            if(valid = true)
                            {

                                retrievedIndexes = keyWordCompare(createHashMap(), searchNameField.getText());

                                for(int num : retrievedIndexes)
                                {
                                    if(investments.get(num).getSymbol().equalsIgnoreCase(searchSymbolField.getText()) && (priceComp(investments.get(num).getPrice(), Double.parseDouble(searchHighField.getText()), Double.parseDouble(searchLowField.getText()))) == 1)
                                    {   
                                        messages.append(investments.get(num).toString()+"\n");
                                    }
                                }
                            }
                        }
                        catch(NullPointerException n)
                        {
                            messages.setText("No Match (make sure key words are capitialized if necessary)");
                            valid = false;
                        }

                        break;
                    }

                    if(sFlag && kFlag && !(pFlag))
                    {
                        // no price range
                        try
                        {
                            if(valid = true)
                            {
                                retrievedIndexes = keyWordCompare(createHashMap(), searchNameField.getText());

                                for(int num : retrievedIndexes)
                                {
                                    if(investments.get(num).getSymbol().equalsIgnoreCase(searchSymbolField.getText()))
                                    {   
                                        messages.append(investments.get(num).toString()+"\n");
                                    }
                                }
                            }
                        }
                        catch(NullPointerException n)
                        {
                            messages.setText("No Match (make sure key words are capitialized if necessary");
                            valid = false;
                        }

                        break;
                    }

                    else if(sFlag && !(kFlag) && pFlag)
                    {
                        // no keyWords
                        if(investments.get(j).getSymbol().equalsIgnoreCase(searchSymbolField.getText()) && (priceComp(investments.get(j).getPrice(), Double.parseDouble(searchHighField.getText()), Double.parseDouble(searchLowField.getText()))) == 1)
                        {   
                            messages.append(investments.get(j).toString()+"\n");
                        }
                    }

                    else if(!(sFlag) && kFlag && pFlag)
                    {
                        // no symbol
                        try
                        {
                            if(valid == true)
                            {
                                retrievedIndexes = keyWordCompare(createHashMap(), searchNameField.getText());

                                for(int num : retrievedIndexes)
                                {
                                    if((priceComp(investments.get(num).getPrice(), Double.parseDouble(searchHighField.getText()), Double.parseDouble(searchLowField.getText()))) == 1)
                                    {
                                        messages.append(investments.get(num).toString()+"\n");
                                    }
                                }
                            }
                        }
                        
                        catch(NullPointerException n)
                        {
                            messages.setText("No Match (make sure key words are capitialized if necessary");
                            valid = false;
                        }

                        break;
                    }

                    else if(sFlag && !(kFlag) && !(pFlag))
                    {
                        // only symbol
                        if(investments.get(j).getSymbol().equalsIgnoreCase(searchSymbolField.getText()))
                        {
                            messages.append(investments.get(j).toString()+"\n");
                        }
                    }

                    else if(!(sFlag) && kFlag && !(pFlag))
                    {
                        try
                        {
                            if(valid == true)
                            {
                            // only key words
                                retrievedIndexes = keyWordCompare(createHashMap(), searchNameField.getText());

                                for(int num : retrievedIndexes)
                                {
                                    messages.append(investments.get(num).toString()+"\n");
                                }
                            }
                        }
                        
                        catch(NullPointerException n)
                        {
                            messages.setText("No Match (make sure key words are capitialized if necessary");
                            valid = false;
                        }

                        break;
                    }

                    else if(!(sFlag) && !(kFlag) && pFlag)
                    {
                        // only price range
                        if((priceComp(investments.get(j).getPrice(), Double.parseDouble(searchHighField.getText()), Double.parseDouble(searchLowField.getText()))) == 1)
                        {
                            messages.append(investments.get(j).toString()+"\n");
                        }
                    }

                    else
                    {
                        messages.append(investments.get(j).toString()+"\n");
                    }
                }
            }
        });
    }
    
    /**
    * 
    * @return returns an arraylist to store investments
    */
    public ArrayList<InvestmentA3> getInvestment()
    {
        return investments;
    }
    
    /**
     * reads in investments stored on an output file
     * @param rfile the file name or position in args array of strings
     * @return returns 1 if the file is empty, returns 0 if not
     */
    public int readFileIO(String rfile)
    {
        
        InvestmentA3 in;
        String newType;
        String newSym;
        String newName;
        int newQuan;
        double newPrice;
        double newBV;
        int i = 0;
        
        String[] tempInv;
        BufferedReader reader;
        String delims = ", ";
        
        try
        {
            reader = new BufferedReader(new FileReader(rfile));
            String line = reader.readLine();
            
            if(line == null)
            {
                return 1;
            }
            
            else
            {
                tempInv = line.split(delims);
                newType = tempInv[0];
                newSym = tempInv[1];
                newName = tempInv[2];
                newQuan = Integer.parseInt(tempInv[3]);
                newPrice = Double.parseDouble(tempInv[4]);
                newBV = Double.parseDouble(tempInv[5]);
                
                if(newType.equals("Stock"))
                {
                    in = new StockA3(newType, newSym, newName, newQuan, newPrice, newBV);
                }
                
                else if(newType.equals("MutualFund"))
                {
                    in = new MutualFundA3(newType, newSym, newName, newQuan, newPrice, newBV);
                }

                while((line != null))
                {
                    tempInv = line.split(delims);
            
                    newType = tempInv[0];
                    newSym = tempInv[1];
                    newName = tempInv[2];
                    newQuan = Integer.parseInt(tempInv[3]);
                    newPrice = Double.parseDouble(tempInv[4]);
                    newBV = Double.parseDouble(tempInv[5]);
                   
                    if(newType.equals("Stock"))
                    {
                        in = new StockA3(newType, newSym, newName, newQuan, newPrice, newBV);
                        investments.add(in);
                    }

                    else if(newType.equals("MutualFund"))
                    {
                        in = new MutualFundA3(newType, newSym, newName, newQuan, newPrice, newBV);
                        investments.add(in);
                    }
                    
                    line = reader.readLine();
                    i++;
                }
            
                reader.close();
                
                return 0;
            }
        }
        
        catch(IOException e)
        {
            System.out.println("Failed to read "+rfile+".");
            return 1;
        }
    }
    
    /**
     * writes investments to an output file
     * @param wfile the file name or position in args array of string
     */
    public void writeFileIO(String wfile)
    {
        BufferedWriter writer;
        try
        {
            writer = new BufferedWriter(new FileWriter(wfile));
            
            for(int i = 0; i < investments.size(); i++)
            {
                writer.write(investments.get(i).toString());
                writer.newLine();
            }
            writer.close();
        }
        catch(IOException e)
        {
           System.out.println("Failed to write to "+wfile+"."); 
        }
    }
    
    /**
     * creates the hash map and stores all possible keywords at their respective
     * indexes
     * @return the created hash map
     */
    public HashMap<String,ArrayList<Integer>> createHashMap()
    {
        HashMap<String, ArrayList<Integer>> tempMap = new HashMap<String, ArrayList<Integer>>();

        String space = "\\s+";
        
        for(int i = 0; i < investments.size(); i++)
        {
            String[] allWords = investments.get(i).getName().split(space);
            
            for(String keyCheck: allWords)
            {
                if(tempMap.containsKey(keyCheck.toLowerCase()))
                {
                    tempMap.get(keyCheck).add(i);
                }
                
                else
                {
                    tempMap.put(keyCheck, new ArrayList<Integer>());
                    tempMap.get(keyCheck).add(i);
                }
            }
        }
        return tempMap;
    }
    
    /**
     * 
     * @param hashMap the hash map to search through to match the inputted keyword
     * @param kw the user inputted keywords
     * @return an arraylist of indexes that hold the matching keywords 
     */
    public ArrayList<Integer> keyWordCompare(HashMap<String, ArrayList<Integer>> hashMap, String kw)
    {
        String delims = ",";
        String[] keyWords;
        String kwToFind;
        int j = 0;
        int c = 0;
        
        ArrayList<ArrayList<Integer>> index = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> collisionIndex = new ArrayList<Integer>();

        keyWords = kw.split(delims);

        for(int i = 0; i < investments.size(); i++)
        {
            for(String search: keyWords)
            {
                if(hashMap.containsKey(search))
                {
                    index.add(hashMap.get(search));
                }
                
                else
                {
                    continue;
                }
            }
          
            if(index.isEmpty())
            {
                System.out.println("No Match");
                return null;
            }
            
            collisionIndex = index.get(c);
        
            for(ArrayList<Integer> array : index)
            {
               if(!collisionIndex.isEmpty())
               {    
                   collisionIndex.retainAll(array);
               }
            }
           
            c++;
        }
        
       return collisionIndex; 
    }
    
    /**
     * @param price the price of the investment being compared to the range
     * @param high high price in price range
     * @param low low price in price range
     * @return returns 1 if match, returns 0 no match
     */
    public int priceComp(double price, double high, double low)
    {
        if(price < low || price > high)
        {
            return 0;
        }

        else
        {
            return 1;
        }
    }
}
