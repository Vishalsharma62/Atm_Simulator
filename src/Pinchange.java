import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pinchange extends JFrame implements ActionListener{
    public static void main(String[] args) {
        new Pinchange("").setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == change) {
            try {
                String npin=pin.getText();
                String rpin=repin.getText();


                if (npin.equals("")){
                    JOptionPane.showMessageDialog(null,"Please enter your new pin");
                    return;
                }
                if (rpin.equals("")){
                    JOptionPane.showMessageDialog(null,"Please re-enter your new pin");
                    return;
                }
                if (!npin.equals(rpin)){
                    JOptionPane.showMessageDialog(null,"Entered Pin Does not Match");
                    return;
                }
                connection c=new connection();
                String query1="update bank set pin = '"+rpin+"' where pin='"+pinnumber+"'";
                String query2="update login set pin = '"+rpin+"' where pin='"+pinnumber+"'";
                String query3="update signup3 set pin = '"+rpin+"' where pin='"+pinnumber+"'";

                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);
                c.s.executeUpdate(query3);

                JOptionPane.showMessageDialog(null,"Pin Changed Successfully");

                setVisible(false);
                new transactions(rpin).setVisible(true);

            } catch (Exception e) {
                System.out.println(e);
            }
        }else {
            setVisible(false);
            new transactions(pinnumber).setVisible(true);
        }
    }

    JLabel image ,text , pintext , repintext ;
    JPasswordField pin , repin ;
    JButton change , back ;
    String pinnumber;

    Pinchange(String pinnumber){
        this.pinnumber=pinnumber;
        setLayout(null);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2=i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        image=new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        text = new JLabel("Change Your Pin");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System",Font.BOLD,16));
        text.setBounds(270,280,500,35);
        image.add(text);

        pintext = new JLabel("New Pin");
        pintext.setForeground(Color.WHITE);
        pintext.setFont(new Font("System",Font.BOLD,16));
        pintext.setBounds(165,320,180,25);
        image.add(pintext);
        pin = new JPasswordField();
        pin.setFont(new Font("Raleway",Font.BOLD,25));
        pin.setBounds(330,320,180,25);
        image.add(pin);

        repintext = new JLabel("Re-Enter Your Pin");
        repintext.setForeground(Color.WHITE);
        repintext.setFont(new Font("System",Font.BOLD,16));
        repintext.setBounds(165,360,180,25);
        image.add(repintext);
        repin = new JPasswordField();
        repin.setFont(new Font("Raleway",Font.BOLD,25));
        repin.setBounds(330,360,180,25);
        image.add(repin);

        change = new JButton("Change Pin");
        change.setBounds(355,485,150,30);
        change.addActionListener(this);
        image.add(change);

        back = new JButton("Back");
        back.setBounds(355,520,150,30);
        back.addActionListener(this);
        image.add(back);

        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
    }
}
