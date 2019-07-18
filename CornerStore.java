import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.*;
import java.text.NumberFormat;
import javax.swing.ImageIcon;
import javax.swing.event.*;

class CornerStore extends JFrame implements MouseListener, ActionListener, ListSelectionListener, ItemListener
{
   Container con;
   
   JButton bakery, dairy, juice, fruit, other;
   JButton select, clear, clearAll, reset, enter, submit;
   JButton keypad[] = new JButton[6];
   
   JList selectProd, yourSelection;
   Vector vt1, vt2;
   ProdList product;
   
   JLabel price, prodPhoto, instruc;
   JScrollPane jpQty, jpLabel, jpSelection;
   
   JComboBox qty;
   
   JPasswordField employee, credit;
   
   JRadioButton delivery;
   JTextField total;
   
   JPanel pa, p1, p2, pb, p12, p, p3, key, pc, p4, pd, p5, p6, p456; 
   
   UpdateFrame jm;
   
   String chosenOne, password;
   
   boolean f1, f2, f3, f4, f5;
   
   double totalPrice;
   
   int msg;
   CornerStore(String title)
   {
      super(title);
      setSize(1500,800);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
   }
   void createScreen()
   {
      con = getContentPane();
      con.setLayout(new GridLayout(3,3,5,5));  
        
      bakery = new JButton("Bakery");
      bakery.setFont(new Font("Arial", Font.BOLD, 25));
      bakery.addMouseListener(this);
      bakery.addActionListener(this);
      
      dairy = new JButton("Dairy");
      dairy.setFont(new Font("Arial", Font.BOLD, 25));
      dairy.addMouseListener(this);
      dairy.addActionListener(this);
      
      juice = new JButton("Juice");
      juice.setFont(new Font("Arial", Font.BOLD, 25));
      juice.addMouseListener(this);
      juice.addActionListener(this);
      
      fruit = new JButton("Fruit");
      fruit.setFont(new Font("Arial", Font.BOLD, 25));
      fruit.addMouseListener(this);
      fruit.addActionListener(this);
      
      other = new JButton("Other");
      other.setFont(new Font("Arial", Font.BOLD, 25));
      other.addMouseListener(this);
      other.addActionListener(this);
      
      f1 = f2 = f3 = f4 = f5 = false;

      pa = new JPanel();
      pa.setLayout(new GridLayout(2,2,5,5));
      
      pa.add(bakery);
      pa.add(dairy);
      pa.add(juice);
      pa.add(fruit);
      
      p1 = new JPanel();
      p1.setLayout(new GridLayout(2,1,5,5));
      p1.add(pa);
      p1.add(other);
      p1.setBorder(new TitledBorder("Select Type"));
      
      product = new ProdList();
      product.createList();
      
      vt1 = new Vector();
      selectProd = new JList(vt1);
          
      selectProd.setBorder(new TitledBorder("Select Product"));
      selectProd.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 25));
      
      selectProd.addListSelectionListener(this);
      
      price = new JLabel();   
      price.setBorder(new TitledBorder("Price"));
      price.setBackground(Color.yellow);
      price.setOpaque(true);
      price.setFont(new Font("Arial", Font.ITALIC + Font.BOLD, 25));
      
      qty = new JComboBox();
      qty.setBorder(new TitledBorder("Qty"));
      qty.addItem(1);
      qty.addItem(2);
      qty.addItem(3);
      qty.addItem(4);
      qty.addItem(5);
      qty.setFont(new Font("Arial", Font.ITALIC + Font.BOLD, 25));
      qty.addItemListener(this);
      
      select = new JButton("Select");
      select.addActionListener(this);
      
      pb = new JPanel();
      pb.setLayout(new GridLayout(1,1,5,5));
      pb.add(qty);
      pb.add(select);
      
      p2 = new JPanel();
      p2.setLayout(new GridLayout(2,1,5,5));
      p2.add(price);
      p2.add(pb);
      
      p12 = new JPanel();
      p12.setLayout(new GridLayout(1,3,5,5));
      
      p12.add(p1);
      p12.add(selectProd);
      p12.add(p2);
      
      prodPhoto = new JLabel();
      prodPhoto.setBackground(Color.yellow);
      prodPhoto.setOpaque(true);
      
      jpLabel = new JScrollPane(prodPhoto);
      
      vt2 = new Vector();
      yourSelection = new JList(vt2);
      
      jpSelection = new JScrollPane(yourSelection);
      jpSelection.setBorder(new TitledBorder("Your Selection"));
      yourSelection.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 25));
      
      clear = new JButton("Clear");
      clear.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 25));
      clear.addActionListener(this);
      
      clearAll = new JButton("Clear All");
      clearAll.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 25));
      clearAll.addActionListener(this);
      
      p = new JPanel();
      p.setLayout(new GridLayout(2,1,5,5));
      p.add(clear);
      p.add(clearAll);
      p.setBorder(new TitledBorder("For Clearing Your Selection"));
      
      p3 = new JPanel();
      p3.setLayout(new GridLayout(1,3,5,5));
      p3.add(jpLabel);
      p3.add(jpSelection);
      p3.add(p);
     
      instruc = new JLabel("Use Keypad to enter password for settings");
      employee = new JPasswordField(10);
      employee.setEnabled(false);
      password = "1430511";
      keypad[0] = new JButton("0");
      keypad[1] = new JButton("1"); 
      keypad[2] = new JButton("2");
      keypad[3] = new JButton("3");
      keypad[4] = new JButton("4");
      keypad[5] = new JButton("5");
      
      for(int i = 0; i < keypad.length; i++)
         keypad[i].addActionListener(this);
      
      key = new JPanel();
      key.setLayout(new GridLayout(2,3,5,5));
      for(int i = 0; i<keypad.length; i++)
         key.add(keypad[i]);
      
      reset = new JButton("Reset");
      reset.addActionListener(this);
      
      enter = new JButton("Enter");
      enter.addActionListener(this);
      
      jm = new UpdateFrame("Price Update");
      jm.setSize(1500,500);
      
      pc = new JPanel();
      pc.setLayout(new GridLayout(2,2,5,5));
      pc.add(employee);
      pc.add(key);
      pc.add(reset);
      pc.add(enter);
      
      p4 = new JPanel();
      p4.setLayout(new GridLayout(2,1,5,5));
      p4.setBorder(new TitledBorder("For Employee use ONLY"));
      p4.setBackground(Color.red);
      p4.add(instruc);
      p4.add(pc);    
      
      delivery = new JRadioButton("Home Delivery - $5.99");
      delivery.addItemListener(this);
      total = new JTextField("Total: ");
      total.setBackground(Color.cyan);
      total.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 25));
      totalPrice = 0;
      
      p5 = new JPanel();
      pd = new JPanel();
      p5.setLayout(new GridLayout(3,1,5,5));
      p5.setBorder(new TitledBorder("Total"));
      p5.add(delivery);
      p5.add(total);
      p5.add(pd);
      
      credit = new JPasswordField(10);
      submit = new JButton("Submit");
      submit.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 25));
      submit.addActionListener(this);
      
      msg = 0;
      
      p6 = new JPanel();
      p6.setLayout(new GridLayout(2,1,5,5));
      p6.setBorder(new TitledBorder("Enter CC#"));
      p6.add(credit);
      p6.add(submit);
      
      p456 = new JPanel();
      p456.setLayout(new GridLayout(1,3,5,5));
      p456.add(p4);
      p456.add(p5);
      p456.add(p6);
           
     con.add(p12); 
     con.add(p3);
     con.add(p456);
}
   public void itemStateChanged(ItemEvent e){}

   public void valueChanged(ListSelectionEvent e)
   {
      if(selectProd.getSelectedIndex()!= -1)
      {
         chosenOne = (String)selectProd.getSelectedValue();
         for(int i = 0; i < product.pList.length;i++)
            if(chosenOne.equals(product.pList[i].getpTitle()))
            {
               if(product.pList[i].getpSpecial())
                  price.setText("** Special: $"+product.pList[i].getpSpecialPrice()+"**"+ "   " +"Reg: $"+product.pList[i].getpRegPrice()); 
               else
                  price.setText("Reg: $"+product.pList[i].getpRegPrice());  
            }
      }
   }
   public void actionPerformed(ActionEvent e)
   {
      vt1.clear();
      if(e.getSource() == bakery)
      {
         prodPhoto.setIcon(new ImageIcon("bakery.jpg"));
         for(int i = 0; i < product.pList.length; i++)
            if(product.pList[i].getpType() == 'b')
               vt1.add(product.pList[i].getpTitle());
         selectProd.setListData(vt1);
         
         f1 = true;
         f2 = f3 = f4 = f5 = false;
         bakery.setBackground(Color.yellow);
            dairy.setBackground(new JButton().getBackground());
            juice.setBackground(new JButton().getBackground());
            fruit.setBackground(new JButton().getBackground());
            other.setBackground(new JButton().getBackground());           
      }
      else if(e.getSource() == dairy)
      {
         prodPhoto.setIcon(new ImageIcon("dairy.jpg"));
         for(int i = 0; i < product.pList.length; i++)
            if(product.pList[i].getpType() == 'd')
               vt1.add(product.pList[i].getpTitle());
         selectProd.setListData(vt1);
         
         f2 = true;
         f1 = f3 = f4 = f5 = false;
         dairy.setBackground(Color.yellow);
            bakery.setBackground(new JButton().getBackground());
            juice.setBackground(new JButton().getBackground());
            fruit.setBackground(new JButton().getBackground());
            other.setBackground(new JButton().getBackground()); 
      }
      else if(e.getSource() == juice)
      {
         prodPhoto.setIcon(new ImageIcon("juice.jpg"));
         for(int i = 0; i < product.pList.length; i++)
            if(product.pList[i].getpType() == 'j')
               vt1.add(product.pList[i].getpTitle());
         selectProd.setListData(vt1);
         
         f3 = true;
         f1 = f2 = f4 = f5 = false;
         juice.setBackground(Color.yellow);
            bakery.setBackground(new JButton().getBackground());
            dairy.setBackground(new JButton().getBackground());
            fruit.setBackground(new JButton().getBackground());
            other.setBackground(new JButton().getBackground());         
      }
      else if(e.getSource() == fruit)
      {
         prodPhoto.setIcon(new ImageIcon("fruit.jpg"));
         for(int i = 0; i < product.pList.length; i++)
            if(product.pList[i].getpType() == 'f')
               vt1.add(product.pList[i].getpTitle());
         selectProd.setListData(vt1);
         
         f4 = true;
         f1 = f2 = f3 = f5 = false;
         fruit.setBackground(Color.yellow);
            bakery.setBackground(new JButton().getBackground());
            dairy.setBackground(new JButton().getBackground());
            juice.setBackground(new JButton().getBackground());
            other.setBackground(new JButton().getBackground());           
      } 
      else if(e.getSource() == other)
      {
         prodPhoto.setIcon(new ImageIcon("other.jpg"));
         for(int i = 0; i < product.pList.length; i++)
            if(product.pList[i].getpType() == 'o')
               vt1.add(product.pList[i].getpTitle());
         selectProd.setListData(vt1);
         
         f5 = true;
         f1 = f2 = f3 = f4 = false;
         other.setBackground(Color.yellow); 
            bakery.setBackground(new JButton().getBackground());
            dairy.setBackground(new JButton().getBackground());
            juice.setBackground(new JButton().getBackground());
            fruit.setBackground(new JButton().getBackground());         
      }
      else if(e.getSource() == select)
      {
         String temp = "";
         double sum = 0;
         char c = '\0';
         for(int i = 0; i < product.pList.length; i++)
         {
            if(chosenOne.equals(product.pList[i].getpTitle()))
            {
               if(product.pList[i].getpSpecial())
               {
                  sum = (int)qty.getSelectedItem()*product.pList[i].getpSpecialPrice();
                  temp = product.pList[i].getpTitle() + " -- " + NumberFormat.getCurrencyInstance(new Locale("en","US")).format(sum) + " **";
               }
               else
               {
                  sum = (int)qty.getSelectedItem()*product.pList[i].getpRegPrice();
                  temp = product.pList[i].getpTitle() + " -- " + NumberFormat.getCurrencyInstance(new Locale("en","US")).format(sum);
               }
               vt2.add(temp);
               yourSelection.setListData(vt2);
               totalPrice += sum;
               total.setText("Total: " + NumberFormat.getCurrencyInstance(new Locale("en","US")).format(totalPrice) +" - (NOT inc. delivery)");
            
               c = product.pList[i].getpType();
            }
         }
         for(int i = 0; i < product.pList.length; i++)
            if(product.pList[i].getpType() == c)
               vt1.add(product.pList[i].getpTitle());         
         selectProd.setListData(vt1);
      }
      else if(e.getSource() == submit)
      {
         msg = JOptionPane.showConfirmDialog(this, "Are You SURE ?","WARNING",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
         if(yourSelection.getModel().getSize() == 0)
         {
            JOptionPane.showMessageDialog(this, "No Product selected, Please select a Product", "Not Allowed",JOptionPane.ERROR_MESSAGE);
         }
         else if(credit.getPassword().length == 0)
         {
            JOptionPane.showMessageDialog(this, "Please Enter a VALID Credit Card", "Not Allowed",JOptionPane.ERROR_MESSAGE);
         }
         else if(msg == JOptionPane.YES_OPTION)
         {
            vt2.clear();
            yourSelection.setListData(vt2);         
            total.setText("Total: $0.00");
            if(delivery.isSelected())
            {
               total.setText("Total: " + NumberFormat.getCurrencyInstance(new Locale("en","US")).format(totalPrice + 5.99) +" - (Including Delivery)");
               JOptionPane.showMessageDialog(this, "Total: " + NumberFormat.getCurrencyInstance(new Locale("en","US")).format(totalPrice + 5.99) +" INC. Delivery has been charged to your card, Thank you","Confirmation",JOptionPane.INFORMATION_MESSAGE);
            }
            else
               JOptionPane.showMessageDialog(this, "Total: " + NumberFormat.getCurrencyInstance(new Locale("en","US")).format(totalPrice) +" NOT INC. Delivery has been charged to your card, Thank you","Confirmation",JOptionPane.INFORMATION_MESSAGE);
            
            totalPrice = 0;
            total.setText("Total:");
            credit.setText("");
            delivery.setSelected(false);
         }
      }
      else if(e.getSource() == clear)
      {
         if(yourSelection.getSelectedIndex() != -1)
         {
            String temp = (String)yourSelection.getSelectedValue();
            vt2.remove(yourSelection.getSelectedIndex());
            yourSelection.setListData(vt2);
            temp = temp.replaceAll("[^0-9.]", "");
            totalPrice -= Double.parseDouble(temp);
            total.setText("Total: " + NumberFormat.getCurrencyInstance(new Locale("en","US")).format(totalPrice) +" - (NOT inc. Delivery)");
         }
         else
            JOptionPane.showMessageDialog(this, "No Product selected, Please select a Product", "Not Allowed",JOptionPane.ERROR_MESSAGE);
      }
      else if(e.getSource() == clearAll)
      {
         if(yourSelection.getModel().getSize() != 0)
         {
            msg = JOptionPane.showConfirmDialog(this, "Are You SURE ?","WARNING",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
            if(msg == JOptionPane.YES_OPTION)   
            {
               vt2.clear();
               yourSelection.setListData(vt2);
               totalPrice = 0;
               total.setText("Total: $0.00");      
            }
         }
         else
              JOptionPane.showMessageDialog(this, "No Product selected, Please select a Product", "Not Allowed",JOptionPane.ERROR_MESSAGE);
             
      }
      else if(e.getSource() == keypad[0]||e.getSource() == keypad[1]||e.getSource() == keypad[2]||e.getSource() == keypad[3]||e.getSource() == keypad[4]||e.getSource() == keypad[5])
      {
         for(int i = 0; i < keypad.length;i++)
            if(e.getSource() == keypad[i])
               employee.setText(employee.getText() + keypad[i].getText());
      }
      else if(e.getSource() == enter)
      {
         if(!employee.getText().equals(password))
         {
            JOptionPane.showMessageDialog(this,"WRONG Password, please re-enter","Not Allowed", JOptionPane.ERROR_MESSAGE);
            if(yourSelection.getModel().getSize() != 0)
               JOptionPane.showMessageDialog(this,"Allow Customer to finish FIRST","Not Allowed", JOptionPane.ERROR_MESSAGE);         
         }
         else
         {
            if(yourSelection.getModel().getSize() != 0)
               JOptionPane.showMessageDialog(this,"Allow Customer to finish FIRST","Not Allowed", JOptionPane.ERROR_MESSAGE);
            else
            {
               vt1.clear();
               selectProd.setListData(vt1);
               price.setText("");
               prodPhoto.setIcon(null);
               bakery.setBackground(new JButton().getBackground());
               dairy.setBackground(new JButton().getBackground());
               juice.setBackground(new JButton().getBackground());
               fruit.setBackground(new JButton().getBackground());
               other.setBackground(new JButton().getBackground());
               f1 = f2 = f3 = f4 = f5 = false;
               qty.setSelectedIndex(0);
               employee.setText("");
               
               jm.addWindowListener(new WindowAdapter() {
                  public void windowClosing(WindowEvent e) {
                     jm.updateProduct(product);
                  }
               });
               
               jm.setVisible(true);
            }
         }        
       }
      else if(e.getSource() == reset)
         employee.setText("");
  }
   public void mouseClicked(MouseEvent e){}
   
   public void mouseEntered(MouseEvent e)
   {
      if(e.getSource() == bakery)
      {     
         if(!f1)
         {
            bakery.setBackground(Color.green);
            if(!f2)
               dairy.setBackground(new JButton().getBackground());
            if(!f3)
               juice.setBackground(new JButton().getBackground());
            if(!f4)
               fruit.setBackground(new JButton().getBackground());
            if(!f5)
               other.setBackground(new JButton().getBackground());            
         }
         else
            bakery.setBackground(Color.yellow);
      }
      else if(e.getSource() == dairy)
      {
         if(!f2)
         {
            dairy.setBackground(Color.green);
            if(!f1)
               bakery.setBackground(new JButton().getBackground());
            if(!f3)
               juice.setBackground(new JButton().getBackground());
            if(!f4)
               fruit.setBackground(new JButton().getBackground());
            if(!f5)
               other.setBackground(new JButton().getBackground());  
         }
         else
            dairy.setBackground(Color.yellow);  
      }
      else if(e.getSource() == juice)
      {
         if(!f3)
         {
            juice.setBackground(Color.green);
            if(!f1)
               bakery.setBackground(new JButton().getBackground());
            if(!f2)
               dairy.setBackground(new JButton().getBackground());
            if(!f4)
               fruit.setBackground(new JButton().getBackground());
            if(!f5)
               other.setBackground(new JButton().getBackground());   
         }
         else
            juice.setBackground(Color.yellow);
      }
      else if(e.getSource() == fruit)
      {
         if(!f4)
         {
            fruit.setBackground(Color.green);
            if(!f1)
               bakery.setBackground(new JButton().getBackground());
            if(!f2)
               dairy.setBackground(new JButton().getBackground());
            if(!f3)
               juice.setBackground(new JButton().getBackground());
            if(!f5)
               other.setBackground(new JButton().getBackground());     
         }
         else
            fruit.setBackground(Color.yellow);
      }
      else if(e.getSource() == other)
      {
         if(!f5)
         {
            other.setBackground(Color.green);
            if(!f1)
               bakery.setBackground(new JButton().getBackground());
            if(!f2)
               dairy.setBackground(new JButton().getBackground());
            if(!f3)
               juice.setBackground(new JButton().getBackground());
            if(!f4)
               fruit.setBackground(new JButton().getBackground());  
         }
         else
            other.setBackground(Color.yellow);
      }
   }
   
   public void mouseExited(MouseEvent e)
   {       
      if(e.getSource() == bakery && !f1)
         bakery.setBackground(new JButton().getBackground());
      else if(e.getSource() == dairy && !f2)
         dairy.setBackground(new JButton().getBackground());
      else if(e.getSource() == juice && !f3)
         juice.setBackground(new JButton().getBackground());
      else if(e.getSource() == fruit && !f4)
         fruit.setBackground(new JButton().getBackground());
      else if(e.getSource() == other && !f5)
         other.setBackground(new JButton().getBackground());              
   }
   
   public void mousePressed(MouseEvent e){}
   
   public void mouseReleased(MouseEvent e)
   {
      if(e.getSource() == bakery)
         bakery.setBackground(Color.yellow);
      else if(e.getSource() == dairy)
         dairy.setBackground(Color.yellow);
      else if(e.getSource() == juice)
         juice.setBackground(Color.yellow);
      else if(e.getSource() == fruit)
         fruit.setBackground(Color.yellow);
      else if(e.getSource() == other)
         other.setBackground(Color.yellow);
   }	  
   public static void main(String args[])
   {
      CornerStore obj = new CornerStore("HOA THIEN HA Corner Store App");
      obj.createScreen();
   }
}