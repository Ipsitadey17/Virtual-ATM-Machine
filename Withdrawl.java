package bank.management.system;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Withdrawl extends JFrame implements ActionListener{
    JLabel text;
    JTextField amount;
    JButton withdrawl,back;
    String pinnumber;
    Withdrawl(String pinnumber){
        this.pinnumber=pinnumber;
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,850, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 710);
        add(image);
        
        text=new JLabel("ENTER AMOUNT YOU WANT TO WITHDRAWL");
        text.setBounds(165,220,600,35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);
        
        amount = new JTextField();
        amount.setBounds(175,270,325,25);
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        image.add(amount);
        
        withdrawl=new JButton("Withdraw");
        withdrawl.setBounds(373,387,140,30);
        withdrawl.addActionListener(this);
        image.add(withdrawl);
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
         if(ae.getSource()==withdrawl){
             
             String number=amount.getText();
             Date date=new Date();
             if(number.equals("")){
                 JOptionPane.showMessageDialog(null,"Please enter the amount you want to withdrawl " );
             }else{
                 try{
                 Conn conn=new Conn();
                 String query="insert into bank values('"+pinnumber+"','"+date+"','Withdrawl','"+number+"')";
                 conn.s.executeUpdate(query);
                 JOptionPane.showMessageDialog(null,"Rs"+number+" Withdraw Successfully");
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
        new Withdrawl("").setVisible(true);
       
    }
}
