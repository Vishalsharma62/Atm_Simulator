import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MiniStatement extends JFrame{
    public static void main(String[] args) {
        new MiniStatement("");
    }
    String pinnumber;

    MiniStatement (String pinnumber){
        this.pinnumber=pinnumber;
        setTitle("Mini Statement");

        setLayout(null);

        JLabel mini =new JLabel();
        mini.setBounds(20,140,400,200);
        add(mini);

        JLabel bank=new JLabel("Indian Overseas Bank");
        bank.setBounds(150,20,200,20);
        add(bank);


        JLabel card =new JLabel();
        card.setBounds(20,80,300,20);
        add(card);

        JLabel bala =new JLabel();
        bala.setBounds(20,400,300,30);
        add(bala);

        try {
            connection c=new connection();
            ResultSet rs=c.s.executeQuery("select * from login where pin = '"+pinnumber+"'");
            while (rs.next()){
                card.setText("Card Number : "+rs.getString("cardnumber").substring(0,4) + "XXXXXXXX"+rs.getString("cardnumber").substring(12));

            }
        }catch (Exception e){
            System.out.println(e);
        }

        try {
            connection c=new connection();
            int balance=0;
            ResultSet rs=c.s.executeQuery("select * from bank where pin = '"+pinnumber+"'");
            while (rs.next()){
               mini.setText(mini.getText() + "<html>" + rs.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount")+ "<br><br><html>");
                if (rs.getString("type").equals("Deposit")){
                    balance +=Integer.parseInt(rs.getString("amount"));
                }else {
                    balance -=Integer.parseInt(rs.getString("amount"));
                }
            }
            bala.setText("Your Current Account Balance is Rs "+balance);
        }catch (Exception e){
            System.out.println(e);
        }

        setSize(400,600);
        setLocation(50,20);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }
}
