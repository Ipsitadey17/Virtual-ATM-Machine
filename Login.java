package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    JButton login , signup , clear ;
    JTextField cardTextField ;
    JPasswordField pinTextField;
    Login() {
        
        setTitle("AUTOMATED TELLER MACHINE");
        setLayout(null);
        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2= i1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT); 
        ImageIcon i3= new ImageIcon(i2);
        JLabel label1= new JLabel(i3);
        add(label1);
        JLabel text=new JLabel("Welcome to ATM");
        text.setFont(new Font("Osward", Font.BOLD , 38));
        text.setBounds(200, 40 ,400 ,40);
        add(text);
        JLabel cardno=new JLabel("Card No.:");
        cardno.setFont(new Font("Railway", Font.BOLD , 28));
        cardno.setBounds(120, 150 ,150 ,30);
        add(cardno);
        cardTextField=new JTextField();
        cardTextField.setBounds(300, 150, 230, 30);
        cardTextField.setFont(new Font("Aerial", Font.BOLD , 14));
        add(cardTextField);
        JLabel pin=new JLabel("PIN:");
        pin.setFont(new Font("Railway", Font.BOLD , 28));
        pin.setBounds(120, 220 ,400 ,30);
        add(pin);
        pinTextField=new JPasswordField();
        pinTextField.setBounds(300, 220, 230, 30);
        pinTextField.setFont(new Font("Aerial", Font.BOLD , 14));
        add(pinTextField);
        login=new JButton("SIGN IN");
        login.setBounds(300, 300, 100, 30);
        login.addActionListener(this);
        add(login);
        login.setBackground(Color.BLACK );
        login.setForeground(Color.WHITE );
        clear=new JButton("CLEAR");
        clear.setBounds(430, 300, 100, 30);
        clear.addActionListener(this);
        add(clear);
        clear.setBackground(Color.BLACK );
        clear.setForeground(Color.WHITE );
        signup=new JButton("SIGN UP");
        signup.setBounds(300, 350, 230, 30);
        signup.addActionListener(this);
        add(signup);
        signup.setBackground(Color.BLACK );
        signup.setForeground(Color.WHITE );
        getContentPane().setBackground(Color.WHITE );
        label1.setBounds(70, 10, 100, 100);
        setSize(800 , 480);
        setVisible(true);
        setLocation(350 , 200);
    }
    public void actionPerformed(ActionEvent ae){
         if(ae.getSource()==clear)
         {
             cardTextField.setText("");
             pinTextField.setText("");
         }else if(ae.getSource()==login){
             Conn conn=new Conn();
             String cardnumber=cardTextField.getText();
             String pinnumber=pinTextField.getText();
             String query="select * from login where cardnumber='"+cardnumber+"'and pin='"+pinnumber+"'";
             try{
                 ResultSet rs=conn.s.executeQuery(query);
                 if(rs.next()){
                     setVisible(false);
                     new Transactions(pinnumber).setVisible(true);
                 }else{
                     JOptionPane.showMessageDialog(null,"Incorrect Card Number or Pin");
                 }
             }catch(Exception e){
                 System.out.println(e);
             }
             
             
         }else if(ae.getSource()==signup){
             setVisible(false);
             new SignupOne().setVisible(true);
         }
    }
    public static void main(String args[]) {
        new Login();
    }
}
