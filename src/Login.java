import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{

    JButton login,clear,signup;
    JTextField cardTextField;
    JPasswordField pinTextField;

    Login() {
        setTitle("AUTOMATED TELLER MACHINE");

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        JLabel label = new JLabel(i3);
        label.setBounds(70, 30, 100, 100);
        add(label);

        JLabel text = new JLabel("Welcome to ATM");
        text.setFont(new Font("Osward", Font.BOLD, 40));
        text.setBounds(200, 40, 800, 50);
        add(text);

        JLabel cardno = new JLabel("Card No. :");
        cardno.setFont(new Font("Osward", Font.BOLD, 30));
        cardno.setBounds(120, 150, 200, 40);
        add(cardno);

        cardTextField = new JTextField();
        cardTextField.setBounds(300, 150, 240, 40);
        cardTextField.setFont(new Font("Arial",Font.BOLD,15));
        add(cardTextField);

        JLabel pin = new JLabel("PIN :");
        pin.setFont(new Font("Osward", Font.BOLD, 30));
        pin.setBounds(120, 220, 800, 40);
        add(pin);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(300, 220, 240, 40);
        pinTextField.setFont(new Font("Arial",Font.BOLD,15));
        add(pinTextField);

        login =new JButton("Sign in");
        login.setBounds(300,290,100,30);
        login.setForeground(Color.BLACK);
        login.setBackground(Color.WHITE);
        login.addActionListener(this);
        add(login);

        clear =new JButton("CLEAR");
        clear.setBounds(440,290,100,30);
        clear.setForeground(Color.BLACK);
        clear.setBackground(Color.WHITE);
        clear.addActionListener(this);
        add(clear);

        signup =new JButton("Sign up");
        signup.setBounds(300,340,240,30);
        signup.setForeground(Color.BLACK);
        signup.setBackground(Color.WHITE);
        signup.addActionListener(this);
        add(signup);

        getContentPane().setBackground(Color.WHITE);

        setSize(800, 480);
        setVisible(true);
        setLocation(350, 200);
    }

    public void actionPerformed(ActionEvent ae){
        if (ae.getSource()==login){
            connection c=new connection();
            String cardnumber = cardTextField.getText();
            String pinnumber = pinTextField.getText();
            String query="select * from login where cardnumber = '"+cardnumber+"'and pin = '"+pinnumber+"'";
            try{
                ResultSet rs=c.s.executeQuery(query);
                if (rs.next()){
                    setVisible(false);
                    new transactions(pinnumber).setVisible(true);
                }else {
                    JOptionPane.showMessageDialog(null,"Incorrect Cardnumber or Pin");
                }
            }catch (Exception e){
                System.out.println(e);
            }
        } else if (ae.getSource()==clear) {
            cardTextField.setText("Enter Your Card Number");
            pinTextField.setText("");
        } else if (ae.getSource()==signup) {
            setVisible(false);
            new signup1().setVisible(true);
        }
    }
    public static void main(String[] args) {
        new Login();
    }
}
