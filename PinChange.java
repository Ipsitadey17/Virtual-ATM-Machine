package bank.management.system;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class PinChange extends JFrame implements ActionListener{
    JButton change,back;
    JPasswordField pin,repin;
    JLabel text,pintext,repintext;
    String pinnumber;
    PinChange(String pinnumber){
        this.pinnumber=pinnumber;
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 850, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image= new JLabel(i3);
        image.setBounds(0, 0, 900, 710);
        add(image);
        
        text=new JLabel("CHANGE YOUR PIN");
        text.setBounds(250,210,800,35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);
        
        pintext=new JLabel("NEW PIN");
        pintext.setBounds(160,250,800,45);
        pintext.setForeground(Color.WHITE);
        pintext.setFont(new Font("System", Font.BOLD, 16));
        image.add(pintext);
        
        pin=new JPasswordField();
        pin.setFont(new Font("Raleway", Font.BOLD, 25));
        pin.setBounds(320,260,180,25);
        image.add(pin);
        
        repintext=new JLabel("Re-enter NEW PIN");
        repintext.setBounds(160,280,800,45);
        repintext.setForeground(Color.WHITE);
        repintext.setFont(new Font("System", Font.BOLD, 16));
        image.add(repintext);
        
        repin=new JPasswordField();
        repin.setFont(new Font("Raleway", Font.BOLD, 25));
        repin.setBounds(320,290,180,25);
        image.add(repin);
        
        change=new JButton("Change");
        change.setBounds(373,387,140,30);
        change.addActionListener(this);
        image.add(change);
        
        back=new JButton("Back");
        back.setBounds(373,420,140,30);
        back.addActionListener(this);
        image.add(back);
        
        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
        
        
    }
    
      public void actionPerformed(ActionEvent ae){
          if(ae.getSource()==change){
          try{
              String npin=pin.getText();
              String rpin=repin.getText();
              
              if(!npin.equals(rpin)){
                   JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                   return;
              }
              if(npin.equals("")){
                  JOptionPane.showMessageDialog(null, "Please enter New PIN");
                  return;
              }
              if(rpin.equals("")){
                  JOptionPane.showMessageDialog(null, "Please re-enter New PIN");
                  return;
              }
              Conn conn=new Conn();
               String query1 = "update bank set pin = '"+rpin+"' where pin = '"+pinnumber+"' ";
               String query2 = "update login set pin = '"+rpin+"' where pin = '"+pinnumber+"' ";
               String query3 = "update signupthree set pin = '"+rpin+"' where pin = '"+pinnumber+"' ";
               
               conn.s.executeUpdate(query1);
               conn.s.executeUpdate(query2);
               conn.s.executeUpdate(query3);
               
               JOptionPane.showMessageDialog(null, "PIN changed successfully");
               setVisible(false);
               new Transactions(rpin).setVisible(true);
           }catch(Exception e){
              System.out.println(e);
          }
          
      }else{
              setVisible(false);
              new Transactions(pinnumber).setVisible(true);
          }
    
}
      

    
    public static void main(String args[]) {
        new PinChange("").setVisible(true);
    }
}
