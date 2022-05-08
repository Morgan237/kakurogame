import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KakuroPlayer extends JFrame implements ActionListener {

    JLabel greyLabel1 = new JLabel("---");
    JLabel greyLabel2 = new JLabel("---");
    JLabel greyLabel3 = new JLabel("---");
    JLabel greyLabel4 = new JLabel("---");
    JLabel greyLabel5 = new JLabel("---");
    JLabel greyLabel6 = new JLabel("---");
    JLabel greyLabel7 = new JLabel("---");
    JLabel greyLabel8 = new JLabel("---");
    JLabel greyLabel9 = new JLabel("---");
    JLabel greyLabel10 = new JLabel("---");
    JLabel greyLabel11 = new JLabel("---");

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
    Games[] games = new Games[10];


    public KakuroPlayer(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Kakuro Game");
        this.setSize(350,400);
        this.setLocationRelativeTo(null);

        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(5,4));

//        for(int i=0;i<greyLabels.length;i++){
//            gamePanel.add(greyLabels[i]);
//
//        }

        Games games1 = new Games();

        String[] arr = games1.initSetter("25\\ ","2\\ ","5\\8"," \\11","5\\ "," \\15"," \\3",8,2);
        for(int i=0;i<greyLabels.length;i++){
            greyLabels[i].setForeground(Color.white);
            greyLabels[i].setBackground(Color.black);
            greyLabels[i].setOpaque(true);
            greyLabels[i].setBorder(BorderFactory.createLineBorder(Color.black,1));
            greyLabels[i].setHorizontalAlignment(JLabel.CENTER);
            greyLabels[i].setVerticalAlignment(JLabel.CENTER);
            greyLabels[i].setFont(new Font("Verdana",Font.BOLD,30));

        }

        for(int i=0;i<hintLabels.length;i++){
            hintLabels[i].setForeground(Color.black);
            hintLabels[i].setBackground(Color.darkGray);
            hintLabels[i].setOpaque(true);
            hintLabels[i].setHorizontalAlignment(JLabel.CENTER);
            hintLabels[i].setVerticalAlignment(JLabel.CENTER);
            hintLabels[i].setFont(new Font("Verdana",Font.BOLD,30));
        }

        for(int i=0;i<textFields.length;i++){
            textFields[i].setFont(new Font("Verdana",Font.BOLD,30));
            textFields[i].setHorizontalAlignment(JTextField.CENTER);
        }

        greyLabel3.setText(arr[0]);
        greyLabel4.setText(arr[1]);
        greyLabel6.setText(arr[2]);
        greyLabel7.setText(arr[3]);
        greyLabel8.setText(arr[4]);
        greyLabel9.setText(arr[5]);
        greyLabel11.setText(arr[6]);
        hintLabel1.setText(arr[7]);
        hintLabel2.setText(arr[8]);

        gamePanel.add(greyLabel1);
        gamePanel.add(greyLabel2);
        gamePanel.add(greyLabel3);
        gamePanel.add(greyLabel4);
        gamePanel.add(greyLabel5);
        gamePanel.add(greyLabel6);
        gamePanel.add(field1);
        gamePanel.add(field2);
        gamePanel.add(greyLabel7);
        gamePanel.add(field3);
        gamePanel.add(hintLabel1);
        gamePanel.add(greyLabel8);
        gamePanel.add(greyLabel9);
        gamePanel.add(hintLabel2);
        gamePanel.add(field4);
        gamePanel.add(field5);
        gamePanel.add(greyLabel10);
        gamePanel.add(greyLabel11);
        gamePanel.add(field6);
        gamePanel.add(field7);








        this.add(gamePanel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
