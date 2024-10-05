import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class deposit extends JFrame implements ActionListener {

    public static void main(String[] args) {
        new deposit("");
    }

    public void actionPerformed (ActionEvent ae){
        if (ae.getSource()==deposit){
            String numbrer=amount.getText();
            Date date = new Date();
            if (numbrer.equals("")){
                JOptionPane.showMessageDialog(null,"Please Enter the amount you want to Deposit");
            }else {
                try {
                    connection c=new connection();
                    String query="insert into bank values ('"+pinnumber+"' , '"+date+"' , 'Deposit' , '"+numbrer+"')";
                    c.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null,"Rs "+numbrer+"Deposited Successfully");
                    setVisible(false);
                    new transactions(pinnumber).setVisible(true);
                }catch (Exception e){
                    System.out.println(e);
                }
            }

        } else if (ae.getSource()==back) {
            setVisible(false);
            new transactions(pinnumber).setVisible(true);
        }
    }

    JLabel image , text;
    JTextField amount ;
    JButton deposit , back ;
    String pinnumber;

    deposit(String pinnumber){
    this.pinnumber=pinnumber;
        setLayout(null);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2=i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        image=new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        text = new JLabel("Enter the amount you want to deposit");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System",Font.BOLD,16));
        text.setBounds(170,300,400,20);
        image.add(text);

        amount = new JTextField();
        amount.setFont(new Font("Raleway",Font.BOLD,22));
        amount.setBounds(170,350,320,20);
        image.add(amount);

        deposit=new JButton("Deposit");
        deposit.setBounds(355,485,150,30);
        deposit.addActionListener(this);
        image.add(deposit);

        back=new JButton("Back");
        back.setBounds(355,520,150,30);
        back.addActionListener(this);
        image.add(back);

        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
    }
}
