import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.*;

class UpdateFrame extends JFrame implements ItemListener, ActionListener
{
   JComboBox products;
   JTextField special, reg;
   JCheckBox onSpecial;
   JPanel p1, p2, pa, pb;
   TitledBorder title1, title2;
   ProdList list;
   
   JMenuBar mb;
   JMenu update;
   JMenuItem save;
   
   Container con;
   
   String str;
   UpdateFrame(String title)
   {
      super(title);
      con = getContentPane();
      con.setLayout(new GridLayout(1,3,50,50));
      
      list = new ProdList();
      list.createList();
      products = new JComboBox();
      for(int i = 0; i<list.pList.length; i++)
         products.addItem(list.pList[i].getpTitle());
         
      products.setFont(new Font("Arial", Font.BOLD, 25));
      products.addItemListener(this);
      
      p1 = new JPanel();
      pa = new JPanel();
      pb = new JPanel();
      p1.setLayout(new GridLayout(3,1,3,3));
      p1.add(pa);
      p1.add(products);
      p1.add(pb);
         
      special = new JTextField(10);
      title1 = new TitledBorder("On Special Price");
      title1.setTitleFont(new Font("Arial", Font.BOLD, 20));
      special.setBorder(title1);
      special.setBackground(Color.yellow);
      special.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 25));
      
      reg = new JTextField(10);
      title2 = new TitledBorder("Regular Price");
      title2.setTitleFont(new Font("Arial", Font.BOLD, 20));      
      reg.setBorder(title2);
      reg.setBackground(Color.green);
      reg.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 25));
      
      p2 = new JPanel();
      p2.setLayout(new GridLayout(2,1,5,5));
      p2.add(special);
      p2.add(reg);
      
      onSpecial = new JCheckBox("On Special");
      onSpecial.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 25));
      
      con.add(p1);
      con.add(p2);
      con.add(onSpecial);
      
      mb = new JMenuBar();
      update = new JMenu("Update Price");
      update.setMnemonic('U');
      update.setFont(new Font("Arial", Font.BOLD, 20));
      
      save = new JMenuItem("Save Current Record");
      save.setMnemonic('S');
      save.setFont(new Font("Arial", Font.BOLD, 20));
      save.addActionListener(this);
      
      update.add(save);
      mb.add(update);
      
      setJMenuBar(mb);
      str = "";    
   }
   public void updateProduct(ProdList list2)
   {
      for(int i = 0; i < list.pList.length; i++)
      {
         list2.pList[i].setpSpecial(list.pList[i].getpSpecial());
         list2.pList[i].setpSpecialPrice(list.pList[i].getpSpecialPrice());
         list2.pList[i].setpRegPrice(list.pList[i].getpRegPrice());
      }   
   }
   public void actionPerformed(ActionEvent e)
   {  
      double temp1 = 0, temp2 = 0;
      if(e.getSource() == save)
      {
         for(int i = 0; i < list.pList.length; i++)
         {
            if(list.pList[i].getpTitle().equals(str))
            {
               temp1 = Double.parseDouble(reg.getText());
               list.pList[i].setpRegPrice(temp1);
               
               temp2 = Double.parseDouble(special.getText());
               list.pList[i].setpSpecialPrice(temp2);
               
               list.pList[i].setpSpecial(onSpecial.isSelected());
            }
         }
         JOptionPane.showMessageDialog(this,"Update Complete","Confirmation",JOptionPane.INFORMATION_MESSAGE);
      }
   }
   public void itemStateChanged(ItemEvent e)
   {
      if(products.getSelectedIndex() != -1)
      {
         str = (String)products.getSelectedItem();
         for(int i = 0; i < list.pList.length; i++)
         {
            if(str.equals(list.pList[i].getpTitle()))
            {
               special.setText(""+list.pList[i].getpSpecialPrice());
               reg.setText(""+list.pList[i].getpRegPrice());  
               
               if(list.pList[i].getpSpecial())
                  onSpecial.setSelected(true);
               else
                  onSpecial.setSelected(false);       
            }
         }
      }
   }
}