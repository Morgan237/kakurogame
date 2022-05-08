import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.InputMismatchException;

public class KakuroPlayer extends JFrame implements ActionListener, FocusListener {

//    Les greyLabels seront les cases non modifiables
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

//    Les hintLabel seront les cases non modifiables qui donnerons des indices a comment completer le puzzle
    JLabel hintLabel1 = new JLabel();
    JLabel hintLabel2 = new JLabel();

//    Les field seront les cases JTextField modifiables ou le joueur inserera ses reponses
    JTextField field1 = new JTextField();
    JTextField field2 = new JTextField();
    JTextField field3 = new JTextField();
    JTextField field4 = new JTextField();
    JTextField field5 = new JTextField();
    JTextField field6 = new JTextField();
    JTextField field7 = new JTextField();

//    La barre de menu au dessus
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem checker;
    JMenuItem newGame;
    JMenuItem solutionItem;

    JPanel gamePanel;

//    Un tableau pour contenir tout les greyLabels pour pouvoir facilement iterer entre eux
    JLabel[] greyLabels = {greyLabel1,greyLabel2,greyLabel3,greyLabel4,greyLabel5,greyLabel6,
            greyLabel7,greyLabel8,greyLabel9,greyLabel10,greyLabel11};

    //    Un tableau pour contenir tout les hintLabel pour pouvoir facilement iterer entre eux
    JLabel[] hintLabels = {hintLabel1,hintLabel2};

//    boolean pour savoir si les reponses sont actuellement entrain d'etre montrer par le program
    boolean reponse = false;

    //    Un tableau pour contenir tout les textfields pour pouvoir facilement iterer entre eux
    JTextField[] textFields = {field1,field2,field3,field4,field5,field6,field7};
    Font font = new Font("Verdana",Font.BOLD,15);
    Game[] games = new Game[10];
    Game game1 = new Game();

//    ----------------------------------------- CONSTRUCTOR -----------------------------------------------------------------
//    Creation de la fenetre
    public KakuroPlayer(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Kakuro Game");
        this.setSize(450,500);
        this.setLocationRelativeTo(null);

        gamePanel = new JPanel(); // Un JPanel pour retenir toutes les cases
        gamePanel.setLayout(new GridLayout(5,4)); //Un gridLayout sur le JPanel pour donner la forme

//        Des instances de la classe game qui contient toutes le informations pour une partie donnee
        game1.makeGame("25\\--","2\\--","5\\8","--\\11","5\\--","--\\15","--\\3",
                8,2,6,2,3,9,4,2,1);

        for(int i=0;i<greyLabels.length;i++){
//            Bien formatter les greyLabels(Les cases non modifiables)

            greyLabels[i].setForeground(Color.white);
            greyLabels[i].setBackground(Color.black);
            greyLabels[i].setOpaque(true);
            greyLabels[i].setBorder(BorderFactory.createLineBorder(Color.white,1));
            greyLabels[i].setHorizontalAlignment(JLabel.CENTER);
            greyLabels[i].setVerticalAlignment(JLabel.CENTER);
            greyLabels[i].setFont(new Font("Verdana",Font.BOLD,30));

        }

        for(int i=0;i<hintLabels.length;i++){
//            Bien formatter les hintLabels(Les cases d'indice non modifiable)

            hintLabels[i].setForeground(Color.black);
            hintLabels[i].setBackground(Color.darkGray);
            hintLabels[i].setOpaque(true);
            hintLabels[i].setBorder(BorderFactory.createLineBorder(Color.white));
            hintLabels[i].setHorizontalAlignment(JLabel.CENTER);
            hintLabels[i].setVerticalAlignment(JLabel.CENTER);
            hintLabels[i].setFont(new Font("Verdana",Font.BOLD,30));
        }

        for(int i=0;i<textFields.length;i++){
//            Bien formatter les textFields, qui sont les cases modifiables ou les reponses seront insererer
            textFields[i].setFont(new Font("Verdana",Font.BOLD,30));
            textFields[i].setHorizontalAlignment(JTextField.CENTER);
            textFields[i].setBorder(BorderFactory.createLineBorder(Color.lightGray));
            textFields[i].addFocusListener(this);
        }

        //Ajouter toutes les cases dans le JPanel gamePanel dans l'order suivant:

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
        fileMenu = new JMenu("Fichier");
        checker = new JMenuItem("Verifier la solution");
        checker.addActionListener(this);
        newGame = new JMenuItem("Nouvelle Partie");
        newGame.addActionListener(this);
        solutionItem = new JMenuItem("Montrer la solution");
        solutionItem.addActionListener(this);




        menuBar.add(fileMenu);
        fileMenu.add(checker);
        fileMenu.add(solutionItem);
        fileMenu.add(newGame);
        this.setJMenuBar(menuBar);
        this.add(gamePanel);
        this.setVisible(true);
    }
//------------------------------------------ACTION PERFORMED METHOD--------------------------------------------------
//    Methode pour verifirer que la solution proposee est correcte
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == checker){
//            -----------VERIFIER LA SOLUTION PROPOSEE---------
            check();
        } else if(e.getSource() == newGame){
//            ------JOUER UNE AUTRE PARTIE------

        } else if(e.getSource() == solutionItem){
//            ----------AFFICHER LA SOLUTION----------
            if(reponse == false){
                reponse = true;
                solutionItem.setText("Cacher la solution");
                for(int i=0;i<textFields.length;i++){
                    textFields[i].setFont(new Font("Verdana",Font.BOLD,30));
                    textFields[i].setHorizontalAlignment(JTextField.CENTER);
                    textFields[i].setBorder(BorderFactory.createLineBorder(Color.lightGray));
//                    textFields[i].addFocusListener(this);
                    textFields[i].setForeground(Color.green);
                    textFields[i].setFocusable(false);
                }
                field1.setText(String.valueOf(game1.res1));
                field2.setText(String.valueOf(game1.res2));
                field3.setText(String.valueOf(game1.res3));
                field4.setText(String.valueOf(game1.res4));
                field5.setText(String.valueOf(game1.res5));
                field6.setText(String.valueOf(game1.res6));
                field7.setText(String.valueOf(game1.res7));
            } else {
                reponse = false;
                solutionItem.setText("Montrer les reponses");
                for(int i=0;i<textFields.length;i++){
                    textFields[i].setFont(new Font("Verdana",Font.BOLD,30));
                    textFields[i].setHorizontalAlignment(JTextField.CENTER);
                    textFields[i].setBorder(BorderFactory.createLineBorder(Color.lightGray));
//                    textFields[i].addFocusListener(this);
                    textFields[i].setForeground(Color.black);
                    textFields[i].setFocusable(true);
                    textFields[i].setText("");
                }

            }
        }
    }

    @Override
    public void focusGained(FocusEvent e) {

    }
//----------------------VERIFIER LES CONDITIONS POUR LES VALEURS ENTRER-------------------------------------
    @Override
    public void focusLost(FocusEvent e) {
        if(e.getSource() == field1){
            try{
                int field1Value = Integer.parseInt(field1.getText());
                if(field1Value > 9){
                    JOptionPane.showMessageDialog(null,"Cette valeur ne peut pas etre superieure au chiffre 9");
                    field1.requestFocus();

                } else if(Integer.toString(field1Value).equals(field2.getText())){
                    JOptionPane.showMessageDialog(null,"Deux cases sur la meme rangee ne peuvent avoir la meme valeur");
                    field1.requestFocus();
                }
            }catch (NumberFormatException ex){
                if(!field1.getText().isEmpty()){
                    System.out.println("Error : " + ex);
                    JOptionPane.showMessageDialog(null,"La valeur de cases ne peuvent etre que des nombres","Erreur",JOptionPane.ERROR_MESSAGE);
                    field1.requestFocus();
                }
            } catch (Exception ex){
                System.out.println("Error : " + ex);
            }

        } else if(e.getSource() == field2){
            try{
                int field2Value = Integer.parseInt(field2.getText());
                if(field2Value > 9){
                    JOptionPane.showMessageDialog(null,"Cette valeur ne peut pas etre superieure au chiffre 9");
                    field2.requestFocus();

                } else if(Integer.toString(field2Value).equals(field1.getText())){
                    JOptionPane.showMessageDialog(null,"Deux cases sur la meme rangee ne peuvent avoir la meme valeur");
                    field2.requestFocus();
                }
            }catch (NumberFormatException ex){
                if(!field2.getText().isEmpty()){
                    System.out.println("Error : " + ex);
                    JOptionPane.showMessageDialog(null,"La valeur de cases ne peuvent etre que des nombres","Erreur",JOptionPane.ERROR_MESSAGE);
                    field2.requestFocus();
                }
            } catch (Exception ex){
                System.out.println("Error : " + ex);
            }

        } else if(e.getSource() == field3){
            try{
                int field3Value = Integer.parseInt(field3.getText());
                if(field3Value > 9){
                    JOptionPane.showMessageDialog(null,"Cette valeur ne peut pas etre superieure au chiffre 9");
                    field3.requestFocus();

                } else if(Integer.toString(field3Value).equals(hintLabel1.getText())){
                    JOptionPane.showMessageDialog(null,"Deux cases sur la meme rangee ne peuvent avoir la meme valeur");
                    field3.requestFocus();
                }
            }catch (NumberFormatException ex){
                if(!field3.getText().isEmpty()){
                    System.out.println("Error : " + ex);
                    JOptionPane.showMessageDialog(null,"La valeur de cases ne peuvent etre que des nombres","Erreur",JOptionPane.ERROR_MESSAGE);
                    field3.requestFocus();
                }
            } catch (Exception ex){
                System.out.println("Error : " + ex);
            }

        } else if(e.getSource() == field4){
            try{
                int field4Value = Integer.parseInt(field4.getText());
                if(field4Value > 9){
                    JOptionPane.showMessageDialog(null,"Cette valeur ne peut pas etre superieure au chiffre 9");
                    field4.requestFocus();

                } else if(Integer.toString(field4Value).equals(field5.getText()) || Integer.toString(field4Value).equals(hintLabel2.getText())){
                    JOptionPane.showMessageDialog(null,"Deux cases sur la meme rangee ne peuvent avoir la meme valeur");
                    field4.requestFocus();
                }
            }catch (NumberFormatException ex){
                if(!field4.getText().isEmpty()){
                    System.out.println("Error : " + ex);
                    JOptionPane.showMessageDialog(null,"La valeur de cases ne peuvent etre que des nombres","Erreur",JOptionPane.ERROR_MESSAGE);
                    field4.requestFocus();
                }
            } catch (Exception ex){
                System.out.println("Error : " + ex);
            }

        } else if(e.getSource() == field5){
            try{
                int field5Value = Integer.parseInt(field5.getText());
                if(field5Value > 9){
                    JOptionPane.showMessageDialog(null,"Cette valeur ne peut pas etre superieure au chiffre 9");
                    field5.requestFocus();

                } else if(Integer.toString(field5Value).equals(field4.getText()) || Integer.toString(field5Value).equals(hintLabel2.getText())){
                    JOptionPane.showMessageDialog(null,"Deux cases sur la meme rangee ne peuvent avoir la meme valeur");
                    field5.requestFocus();
                }
            }catch (NumberFormatException ex){
                if(!field5.getText().isEmpty()){
                    System.out.println("Error : " + ex);
                    JOptionPane.showMessageDialog(null,"La valeur de cases ne peuvent etre que des nombres","Erreur",JOptionPane.ERROR_MESSAGE);
                    field5.requestFocus();
                }
            } catch (Exception ex){
                System.out.println("Error : " + ex);
            }

        } else if(e.getSource() == field6){
            try{
                int field6Value = Integer.parseInt(field6.getText());
                if(field6Value > 9){
                    JOptionPane.showMessageDialog(null,"Cette valeur ne peut pas etre superieure au chiffre 9");
                    field6.requestFocus();

                } else if(Integer.toString(field6Value).equals(field7.getText())){
                    JOptionPane.showMessageDialog(null,"Deux cases sur la meme rangee ne peuvent avoir la meme valeur");
                    field6.requestFocus();
                }
            }catch (NumberFormatException ex){
                if(!field6.getText().isEmpty()){
                    System.out.println("Error : " + ex);
                    JOptionPane.showMessageDialog(null,"La valeur de cases ne peuvent etre que des nombres","Erreur",JOptionPane.ERROR_MESSAGE);
                    field6.requestFocus();
                }
            } catch (Exception ex){
                System.out.println("Error : " + ex);
            }

        } else if(e.getSource() == field7){
            try{
                int field7Value = Integer.parseInt(field7.getText());
                if(field7Value > 9){
                    JOptionPane.showMessageDialog(null,"Cette valeur ne peut pas etre superieure au chiffre 9");
                    field7.requestFocus();

                } else if(Integer.toString(field7Value).equals(field6.getText())){
                    JOptionPane.showMessageDialog(null,"Deux cases sur la meme rangee ne peuvent avoir la meme valeur");
                    field7.requestFocus();
                }
            }catch (NumberFormatException ex){
                if(!field7.getText().isEmpty()){
                    System.out.println("Error : " + ex);
                    JOptionPane.showMessageDialog(null,"La valeur de cases ne peuvent etre que des nombres","Erreur",JOptionPane.ERROR_MESSAGE);
                    field7.requestFocus();
                }
            } catch (Exception ex){
                System.out.println("Error : " + ex);
            }
        }
    }

    public void check(){
//        --------METHODE POUR VERIFIER LA VALIDITE DE LA SOLUTION PROPOSEE---------------
        if(Integer.parseInt(field1.getText()) == game1.res1 && Integer.parseInt(field2.getText()) == game1.res2
            && Integer.parseInt(field3.getText()) == game1.res3 && Integer.parseInt(field4.getText()) == game1.res4
            && Integer.parseInt(field5.getText()) == game1.res5 && Integer.parseInt(field6.getText()) == game1.res6
            && Integer.parseInt(field7.getText()) == game1.res7){

            JOptionPane.showMessageDialog(null,"Felicitations! Vous avez ganer.","Success",JOptionPane.INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(this,"Dommage!La solution est fausse.\n Essayez encore","Essayez encore",JOptionPane.ERROR_MESSAGE);
        }
    }
}
