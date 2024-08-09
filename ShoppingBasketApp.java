import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;


public class ShoppingBasketApp extends JFrame{

     private List<String> itemsInBasket = new ArrayList<>();

     private List<String> itemsPriceBasket = new ArrayList<>();

     private DefaultListModel<String> listModel = new DefaultListModel<>();

     private JList<String> basketList = new JList<>(listModel);

     private JTextArea savedProductsArea = new JTextArea(10, 30);

     private double itemsTotalPrice = 0;

     public ShoppingBasketApp(){
        setTitle("Shopping Basket");
        setSize(1000 , 400);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        initComponents();


     }

     private void initComponents(){

        JLabel itemLabel = new JLabel("Item:");

        JTextField itemTextField = new JTextField(15);

        JLabel itemPrice = new JLabel("Price:");

        JLabel totalPrice = new JLabel("Total price: ");



        JTextField itemsPriceField = new JTextField(15);

        JButton addButton = new JButton("Add to Basket");

        JButton removeButton = new JButton("Remove Selected!");

        JPanel inputPanel = new JPanel();

        inputPanel.add(itemLabel);

        inputPanel.add(itemTextField);

        inputPanel.add(itemPrice);

        inputPanel.add(itemsPriceField);

        inputPanel.add(addButton);
 
        inputPanel.add(removeButton);

        inputPanel.add(totalPrice);

        //Saved products

        JPanel savedProductsPanel = new JPanel();

        savedProductsPanel.setBorder(BorderFactory.createTitledBorder("Saved Products"));

        savedProductsArea.setEditable(false);

        savedProductsPanel.add(new JScrollPane(savedProductsArea));

         


        //Main layout window
        setLayout(new BorderLayout());

        add(inputPanel,BorderLayout.NORTH);

        add(new JScrollPane(basketList), BorderLayout.CENTER);


        //Add Button fuctionality

        addButton.addActionListener(new ActionListener() {

            @Override 
            public void actionPerformed(ActionEvent e){

                String item = itemTextField.getText().trim();

                String priceText = itemsPriceField.getText().trim();


                String product = item + " " + priceText + "lv.";

                if(!item.isEmpty() && !priceText.isEmpty()){

                    double price = Double.parseDouble(priceText);

                    itemsTotalPrice += price;

                    String formatPrice = String.format("%.2f", itemsTotalPrice);

                    totalPrice.setText("Total price: " + formatPrice + "lv.");

                    itemsInBasket.add(item);
                    itemsPriceBasket.add(priceText);
                    listModel.addElement(product);
                    itemTextField.setText("");
                    itemsPriceField.setText("");


                }
            }

        });

        //Remove Button functionality

        removeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                int selectedIndex = basketList.getSelectedIndex();

                if(selectedIndex != -1){

                    double price = Double.parseDouble(itemsPriceBasket.get(selectedIndex));
                    itemsTotalPrice -= price;

                    String formatPrice = String.format("%.2f", itemsTotalPrice);

                    totalPrice.setText("Total price: " + formatPrice + "lv.");



                    listModel.remove(selectedIndex);
                    itemsInBasket.remove(selectedIndex);
                    itemsPriceBasket.remove(selectedIndex);

                    updateSavedProductsArea();
                }

            }
        });


     }

     private void updateSavedProductsArea(){

        savedProductsArea.setText("");
        for(String item : itemsInBasket ){

            savedProductsArea.append(item + "\n");
        }
     }

     public static void main(String[] args) {
         SwingUtilities.invokeLater(() ->
         {
            new ShoppingBasketApp().setVisible(true);
         });

     }


}