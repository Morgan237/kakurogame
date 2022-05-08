import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.InputMismatchException;

public class KakuroPlayer extends JFrame implements ActionListener, FocusListener {

    JLabel greyLabel1 = new JLabel("");
    JLabel greyLabel2 = new JLabel("");
    JLabel greyLabel3 = new JLabel("---");
    JLabel greyLabel4 = new JLabel("---");
    JLabel greyLabel5 = new JLabel("");
    JLabel greyLabel6 = new JLabel("---");
    JLabel greyLabel7 = new JLabel("---");
    JLabel greyLabel8 = new JLabel("---");
    JLabel greyLabel9 = new JLabel("---");
    JLabel greyLabel10 = new JLabel("");
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
    JMenu fileMenu;
    JMenuItem checker;
    JMenuItem newGame;

    JPanel gamePanel;

    JLabel[] greyLabels = {greyLabel1,greyLabel2,greyLabel3,greyLabel4,greyLabel5,greyLabel6,
            greyLabel7,greyLabel8,greyLabel9,greyLabel10,greyLabel11};

    JLabel[] hintLabels = {hintLabel1,hintLabel2};

    JTextField[] textFields = {field1,field2,field3,field4,field5,field6,field7};
    Font font = new Font("Verdana",Font.BOLD,15);
    Game[] games = new Game[10];
    Game game1 = new Game();

//    ----------------------------------------- CONSTRUCTOR -----------------------------------------------------------------
    public KakuroPlayer(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Kakuro Game");
        this.setSize(350,400);
        this.setLocationRelativeTo(null);

        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(5,4));

        game1.makeGame("25\\--","2\\--","5\\8","--\\11","5\\--","--\\15","--\\3",
                8,2,6,2,3,9,4,2,1);

        for(int i=0;i<greyLabels.length;i++){
            greyLabels[i].setForeground(Color.white);
            greyLabels[i].setBackground(Color.black);
            greyLabels[i].setOpaque(true);
            greyLabels[i].setBorder(BorderFactory.createLineBorder(Color.white,1));
            greyLabels[i].setHorizontalAlignment(JLabel.CENTER);
            greyLabels[i].setVerticalAlignment(JLabel.CENTER);
            greyLabels[i].setFont(new Font("Verdana",Font.BOLD,30));

        }

        for(int i=0;i<hintLabels.length;i++){
            hintLabels[i].setForeground(Color.black);
            hintLabels[i].setBackground(Color.darkGray);
            hintLabels[i].setOpaque(true);
            hintLabels[i].setBorder(BorderFactory.createLineBorder(Color.white));
            hintLabels[i].setHorizontalAlignment(JLabel.CENTER);
            hintLabels[i].setVerticalAlignment(JLabel.CENTER);
            hintLabels[i].setFont(new Font("Verdana",Font.BOLD,30));
        }

        for(int i=0;i<textFields.length;i++){
            textFields[i].setFont(new Font("Verdana",Font.BOLD,30));
            textFields[i].setHorizontalAlignment(JTextField.CENTER);
            textFields[i].setBorder(BorderFactory.createLineBorder(Color.lightGray));
            textFields[i].addFocusListener(this);
        }


        greyLabel3.setText(game1.g3);
        greyLabel4.setText(game1.g4);
        greyLabel6.setText(game1.g6);
        greyLabel7.setText(game1.g7);
        greyLabel8.setText(game1.g8);
        greyLabel9.setText(game1.g9);
        greyLabel11.setText(game1.g11);
        hintLabel1.setText(String.valueOf(game1.h1));
        hintLabel2.setText(String.valueOf(game1.h2));

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

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        checker = new JMenuItem("Check");
        checker.addActionListener(this);
        newGame = new JMenuItem("New Game");
        newGame.addActionListener(this);




        menuBar.add(fileMenu);
        fileMenu.add(newGame);
        fileMenu.add(checker);
        this.setJMenuBar(menuBar);
        this.add(gamePanel);
        this.setVisible(true);
    }
//------------------------------------------ACTION PERFORME METHOD--------------------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == checker){
            check();
        } else if(e.getSource() == newGame){

        }
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {
        if(e.getSource() == field1){
            try{
                int field1Value = Integer.parseInt(field1.getText());
                if(field1Value > 9){
                    JOptionPane.showMessageDialog(null,"This value cannot be greatter than 9");
                    field1.requestFocus();

                } else if(Integer.toString(field1Value).equals(field2.getText())){
                    JOptionPane.showMessageDialog(null,"Two adjacent fields cannot have thesame value");
                    field1.requestFocus();
                }
            }catch (NumberFormatException ex){
                System.out.println("Error : " + ex);
                JOptionPane.showMessageDialog(this,"La valeur de cases ne peuvent etre que des nombres","Erreur",JOptionPane.ERROR_MESSAGE);
                field1.requestFocus();
            } catch (Exception ex){
                System.out.println("Error : " + ex);
            }

        } else if(e.getSource() == field2){
            try{
                int field2Value = Integer.parseInt(field2.getText());
                if(field2Value > 9){
                    JOptionPane.showMessageDialog(null,"This value cannot be greatter than 9");
                    field2.requestFocus();

                } else if(Integer.toString(field2Value).equals(field1.getText())){
                    JOptionPane.showMessageDialog(null,"Two adjacent fields cannot have thesame value");
                    field2.requestFocus();
                }
            }catch (NumberFormatException ex){
                System.out.println("Error : " + ex);
                JOptionPane.showMessageDialog(this,"La valeur de cases ne peuvent etre que des nombres","Erreur",JOptionPane.ERROR_MESSAGE);
                field2.requestFocus();
            } catch (Exception ex){
                System.out.println("Error : " + ex);
            }

        } else if(e.getSource() == field3){
            try{
                int field3Value = Integer.parseInt(field3.getText());
                if(field3Value > 9){
                    JOptionPane.showMessageDialog(null,"This value cannot be greatter than 9");
                    field3.requestFocus();

                } else if(Integer.toString(field3Value).equals(hintLabel1.getText())){
                    JOptionPane.showMessageDialog(null,"Two adjacent fields cannot have thesame value");
                    field3.requestFocus();
                }
            }catch (NumberFormatException ex){
                System.out.println("Error : " + ex);
                JOptionPane.showMessageDialog(this,"La valeur de cases ne peuvent etre que des nombres","Erreur",JOptionPane.ERROR_MESSAGE);
                field3.requestFocus();
            } catch (Exception ex){
                System.out.println("Error : " + ex);
            }

        } else if(e.getSource() == field4){
            try{
                int field4Value = Integer.parseInt(field4.getText());
                if(field4Value > 9){
                    JOptionPane.showMessageDialog(null,"This value cannot be greatter than 9");
                    field4.requestFocus();

                } else if(Integer.toString(field4Value).equals(field5.getText()) || Integer.toString(field4Value).equals(hintLabel2.getText())){
                    JOptionPane.showMessageDialog(null,"Adjacent fields cannot have thesame value");
                    field4.requestFocus();
                }
            }catch (NumberFormatException ex){
                System.out.println("Error : " + ex);
                JOptionPane.showMessageDialog(this,"La valeur de cases ne peuvent etre que des nombres","Erreur",JOptionPane.ERROR_MESSAGE);
                field4.requestFocus();
            } catch (Exception ex){
                System.out.println("Error : " + ex);
            }

        } else if(e.getSource() == field5){
            try{
                int field5Value = Integer.parseInt(field5.getText());
                if(field5Value > 9){
                    JOptionPane.showMessageDialog(null,"This value cannot be greatter than 9");
                    field5.requestFocus();

                } else if(Integer.toString(field5Value).equals(field4.getText()) || Integer.toString(field5Value).equals(hintLabel2.getText())){
                    JOptionPane.showMessageDialog(null,"Adjacent fields cannot have thesame value");
                    field5.requestFocus();
                }
            }catch (NumberFormatException ex){
                System.out.println("Error : " + ex);
                JOptionPane.showMessageDialog(this,"La valeur de cases ne peuvent etre que des nombres","Erreur",JOptionPane.ERROR_MESSAGE);
                field5.requestFocus();
            } catch (Exception ex){
                System.out.println("Error : " + ex);
            }

        } else if(e.getSource() == field6){
            try{
                int field6Value = Integer.parseInt(field6.getText());
                if(field6Value > 9){
                    JOptionPane.showMessageDialog(null,"This value cannot be greatter than 9");
                    field6.requestFocus();

                } else if(Integer.toString(field6Value).equals(field7.getText())){
                    JOptionPane.showMessageDialog(null,"Two adjacent fields cannot have thesame value");
                    field6.requestFocus();
                }
            }catch (NumberFormatException ex){
                System.out.println("Error : " + ex);
                JOptionPane.showMessageDialog(this,"La valeur de cases ne peuvent etre que des nombres","Erreur",JOptionPane.ERROR_MESSAGE);
                field6.requestFocus();
            } catch (Exception ex){
                System.out.println("Error : " + ex);
            }

        } else if(e.getSource() == field7){
            try{
                int field7Value = Integer.parseInt(field7.getText());
                if(field7Value > 9){
                    JOptionPane.showMessageDialog(null,"This value cannot be greatter than 9");
                    field7.requestFocus();

                } else if(Integer.toString(field7Value).equals(field6.getText())){
                    JOptionPane.showMessageDialog(null,"Two adjacent fields cannot have thesame value");
                    field7.requestFocus();
                }
            }catch (NumberFormatException ex){
                System.out.println("Error : " + ex);
                JOptionPane.showMessageDialog(this,"La valeur de cases ne peuvent etre que des nombres","Erreur",JOptionPane.ERROR_MESSAGE);
                field7.requestFocus();
            } catch (Exception ex){
                System.out.println("Error : " + ex);
            }
        }
    }

    public void check(){
        if(Integer.parseInt(field1.getText()) == game1.res1 && Integer.parseInt(field2.getText()) == game1.res2
            && Integer.parseInt(field3.getText()) == game1.res3 && Integer.parseInt(field4.getText()) == game1.res4
            && Integer.parseInt(field5.getText()) == game1.res5 && Integer.parseInt(field6.getText()) == game1.res6
            && Integer.parseInt(field7.getText()) == game1.res7){

            JOptionPane.showMessageDialog(null,"Congratulations! You won.","Success",JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,"Too bad! The solution is incorrect.\n Try again.","Try again",JOptionPane.ERROR_MESSAGE);
        }
    }
}
