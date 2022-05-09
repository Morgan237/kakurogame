import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.InputMismatchException;

public class KakuroPlayer extends JFrame implements ActionListener, FocusListener, WindowListener {

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
    JMenuBar menuBar; //Barre de menu
    JMenu fileMenu; // le menu fichier
    JMenuItem checker; // fonction pour verifier si les reponses proposee sont exactes
    JMenuItem newGame; // fonction pour lancer une autre partie
    JMenuItem solutionItem; //fonction pour montrer la solution

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
    Font font = new Font("Verdana",Font.BOLD,15); // la police pour toutes les cases

    Game[] games = new Game[10]; // un tableau pour contenir toutes les instances de la classe Game pour pouvoir
                                    // choisir une partie à jouer aléatoirement

//    Instances de la classe Game

    Game tempGame; // La partie qui sera en train d'etre joue à un l'instant
    Game game1 = new Game();
    Game game2 = new Game();
    Game game3 = new Game();
    Game game4 = new Game();
    Game game5 = new Game();
    Game game6 = new Game();

//    ----------------------------------------- CONSTRUCTOR -----------------------------------------------------------------
//    Creation de la fenetre
    public KakuroPlayer(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Kakuro Game");
        this.setSize(450,500);
        this.setLocationRelativeTo(null);

        gamePanel = new JPanel(); // Un JPanel pour retenir toutes les cases
        gamePanel.setLayout(new GridLayout(5,4)); //Un gridLayout sur le JPanel pour donner la forme

//        Des instances de la classe game qui contiennent toutes le informations pour une partie donnee
        game1.makeGame("25\\--","02\\--","05\\08","--\\11","05\\--","--\\15","--\\03",
                8,2,6,2,3,9,4,2,1);
        game2.makeGame("17\\--","08\\--","11\\15","--\\07","09\\--","--\\12","--\\11",
                5,9,7,8,2,1,2,4,7);
        game3.makeGame("24\\--","01\\--","14\\10","--\\14","11\\--","--\\18","--\\09",
                9,9,8,1,5,2,7,5,4);
        game4.makeGame("18\\--","09\\--","15\\16","--\\06","08\\--","--\\12","--\\07",
                9,9,7,9,6,2,1,0,7);
        game5.makeGame("20\\--","05\\--","11\\11","--\\08","02\\--","--\\18","--\\02",
                6,9,5,5,2,7,2,2,0);
        game6.makeGame("17\\--","06\\--","13\\07","--\\11","11\\--","--\\18","--\\11",
                2,4,1,6,9,5,9,1,2);

        games[0] = game1;
        games[1] = game2;
        games[2] = game3;
        games[3] = game4;
        games[4] = game5;
        games[5] = game6;

        start();

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
            start();
        } else if(e.getSource() == solutionItem){
//            ----------AFFICHER LA SOLUTION----------
            if(reponse == false){
                reponse = true;
                solutionItem.setText("Cacher la solution");
                for(int i=0;i<textFields.length;i++){
                    textFields[i].setFont(new Font("Verdana",Font.BOLD,30));
                    textFields[i].setHorizontalAlignment(JTextField.CENTER);
                    textFields[i].setBorder(BorderFactory.createLineBorder(Color.lightGray));
                    textFields[i].setForeground(Color.green);
                    textFields[i].setFocusable(false);
                }
                field1.setText(String.valueOf(tempGame.res1));
                field2.setText(String.valueOf(tempGame.res2));
                field3.setText(String.valueOf(tempGame.res3));
                field4.setText(String.valueOf(tempGame.res4));
                field5.setText(String.valueOf(tempGame.res5));
                field6.setText(String.valueOf(tempGame.res6));
                field7.setText(String.valueOf(tempGame.res7));
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
                field1.requestFocus();

            }
        }
    }

    @Override
    public void focusGained(FocusEvent e) {

    }
//----------------------VERIFIER LES CONDITIONS POUR LES VALEURS ENTRER-------------------------------------
//    Cet-a-dire verifier que
//    --> Les valeurs dans les cases n'excedent pas 9
//    --> Les colonnes et lignes contigues n'ont pas les memes valeurs
    @Override
    public void focusLost(FocusEvent e) {
        if(e.getSource() == field1){
            try{
                int field1Value = Integer.parseInt(field1.getText());
                if(field1Value > 9){
                    JOptionPane.showMessageDialog(null,"Cette valeur ne peut pas etre superieure au chiffre 9");
                    field1.requestFocus();

                } else if(Integer.toString(field1Value).equals(field2.getText()) || Integer.toString(field1Value).equals(hintLabel1.getText())
                        || Integer.toString(field1Value).equals(field4.getText()) || Integer.toString(field1Value).equals(field6.getText())){
                    JOptionPane.showMessageDialog(null,"Les colonnes ou lignes contiguës ne peuvent contenir le meme nombre!");
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
                    JOptionPane.showMessageDialog(null,"Les colonnes ou lignes contiguës ne peuvent contenir le meme nombre!");
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

                } else if(Integer.toString(field3Value).equals(hintLabel1.getText()) || Integer.toString(field3Value).equals(hintLabel2.getText())){
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

                } else if(Integer.toString(field4Value).equals(field5.getText()) || Integer.toString(field4Value).equals(hintLabel2.getText()) ||
                        Integer.toString(field4Value).equals(hintLabel1.getText()) || Integer.toString(field4Value).equals(field1.getText())
                        || Integer.toString(field4Value).equals(field6.getText())){

                    JOptionPane.showMessageDialog(null,"Les colonnes ou lignes contiguës ne peuvent contenir le meme nombre!");
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

                } else if(Integer.toString(field5Value).equals(field4.getText()) || Integer.toString(field5Value).equals(hintLabel2.getText()) ||
                        Integer.toString(field5Value).equals(field7.getText())){

                    JOptionPane.showMessageDialog(null,"Les colonnes ou lignes contiguës ne peuvent contenir le meme nombre!");
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

                } else if(Integer.toString(field6Value).equals(field7.getText()) || Integer.toString(field6Value).equals(field4.getText()) ||
                        Integer.toString(field6Value).equals(hintLabel1.getText()) || Integer.toString(field6Value).equals(field1.getText())){

                    JOptionPane.showMessageDialog(null,"Les colonnes ou lignes contiguës ne peuvent contenir le meme nombre!");
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

                } else if(Integer.toString(field7Value).equals(field6.getText()) || Integer.toString(field7Value).equals(field5.getText())){

                    JOptionPane.showMessageDialog(null,"Les colonnes ou lignes contiguës ne peuvent contenir le meme nombre!");
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



        if(Integer.parseInt(field1.getText()) == tempGame.res1 && Integer.parseInt(field2.getText()) == tempGame.res2
            && Integer.parseInt(field3.getText()) == tempGame.res3 && Integer.parseInt(field4.getText()) == tempGame.res4
            && Integer.parseInt(field5.getText()) == tempGame.res5 && Integer.parseInt(field6.getText()) == tempGame.res6
            && Integer.parseInt(field7.getText()) == tempGame.res7){

            JOptionPane.showMessageDialog(null,"Felicitations! Vous avez gagner.","Success",JOptionPane.INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(this,"Dommage!La solution est fausse.\n Essayez encore","Essayez encore",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void start(){
        tempGame = games[(int)Math.floor(Math.random() * 6)];
        greyLabel3.setText(tempGame.g3);
        greyLabel4.setText(tempGame.g4);
        greyLabel6.setText(tempGame.g6);
        greyLabel7.setText(tempGame.g7);
        greyLabel8.setText(tempGame.g8);
        greyLabel9.setText(tempGame.g9);
        greyLabel11.setText(tempGame.g11);
        hintLabel1.setText(String.valueOf(tempGame.h1));
        hintLabel2.setText(String.valueOf(tempGame.h2));
        field1.requestFocus();

        for(int i=0;i<textFields.length;i++){
          textFields[i].setText("");
        }
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {
        start();
    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
