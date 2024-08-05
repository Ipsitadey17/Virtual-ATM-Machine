package bank.management.system;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Deposit extends JFrame implements ActionListener{
    JLabel text;
    JTextField amount;
    JButton deposit,back;
    String pinnumber;
    Deposit(String pinnumber){
        this.pinnumber=pinnumber;
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,850, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 710);
        add(image);
        
        text=new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
        text.setBounds(175,220,600,35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);
        
        amount = new JTextField();
        amount.setBounds(175,270,325,25);
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        image.add(amount);
        
        deposit=new JButton("Deposit");
        deposit.setBounds(373,387,140,30);
        deposit.addActionListener(this);
        image.add(deposit);
        back=new JButton("Back");
        back.setBounds(373,420,140,30);
        back.addActionListener(this);
        image.add(back);
        
        setSize(900,900);
        setUndecorated(true);
        setLocation(500,0);
        setVisible(true);
    }
    
     public void actionPerformed(ActionEvent ae){
         if(ae.getSource()==deposit){
             
             String number=amount.getText();
             Date date=new Date();
             if(number.equals("")){
                 JOptionPane.showMessageDialog(null,"Please enter the amount you want to deposit " );
             }else{
                 try{
                 Conn conn=new Conn();
                 String query="insert into bank values('"+pinnumber+"','"+date+"','Deposit','"+number+"')";
                 conn.s.executeUpdate(query);
                 JOptionPane.showMessageDialog(null,"Rs"+number+" Deposit Successfully");
                 setVisible(false);
                 new Transactions(pinnumber).setVisible(true);
                 }catch(Exception e){
                     System.out.println(e);
                 }
             }
         }
         else if(ae.getSource()==back){
             setVisible(false);
             new Transactions(pinnumber).setVisible(true);
         }
         
     }

    
    public static void main(String args[]) {
        new Deposit("").setVisible(true);
       
    }
}
