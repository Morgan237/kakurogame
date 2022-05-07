import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KakuroPlayer extends JFrame implements ActionListener {

    JLabel greyLabel1 = new JLabel("##");
    JLabel greyLabel2 = new JLabel("##");
    JLabel greyLabel3 = new JLabel("##");
    JLabel greyLabel4 = new JLabel("##");
    JLabel greyLabel5 = new JLabel("##");
    JLabel greyLabel6 = new JLabel("##");
    JLabel greyLabel7 = new JLabel("##");
    JLabel greyLabel8 = new JLabel("##");
    JLabel greyLabel9 = new JLabel("##");
    JLabel greyLabel10 = new JLabel("##");
    JLabel greyLabel11 = new JLabel("##");

    JLabel hintLabel1 = new JLabel();
    JLabel hintLabel2 = new JLabel();

    JTextField field1 = new JTextField();
    JTextField field2 = new JTextField();
    JTextField field3 = new JTextField();
    JTextField field4 = new JTextField();
    JTextField field5 = new JTextField();
    JTextField field6 = new JTextField();
    JTextField field7 = new JTextField();

    JMenuBar menuBar;
    JPanel gamePanel;

    JLabel[] greyLabels = {greyLabel1,greyLabel2,greyLabel3,greyLabel4,greyLabel5,greyLabel6,
            greyLabel7,greyLabel8,greyLabel9,greyLabel10,greyLabel11};

    JLabel[] hintLabels = {hintLabel1,hintLabel2};

    JTextField[] textFields = {field1,field2,field3,field4,field5,field6,field7};
    Font font = new Font("Verdana",Font.BOLD,15);


    public KakuroPlayer(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Kakuro Game");
        this.setSize(350,400);
        this.setLocationRelativeTo(null);

        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(5,4));



        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
