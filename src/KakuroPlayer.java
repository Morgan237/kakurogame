import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KakuroPlayer extends JFrame implements ActionListener {

    public KakuroPlayer(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Kakuro Game");
        this.setSize(350,400);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(4,5));

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
