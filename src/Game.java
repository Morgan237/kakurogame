public class Game {
//    Classe de jeu Game qui contient toutes les informations pour une partie donee
    String g3,g4,g6,g7,g8,g9,g11; // Valeurs des cases non-modifiables;
    int h1,h2; // valeurs des cases d'indice pour une partie donee
    int res1,res2,res3,res4,res5,res6,res7; // Valeurs des reponses pour une partie donee

//    Methode pour tout initier
    public Game makeGame(String g3, String g4, String g6, String g7, String g8, String g9, String g11, int h1, int h2,int res1,
                         int res2,int res3,int res4,int res5,int res6,int res7) {
        this.g3 = g3;
        this.g4 = g4;
        this.g6 = g6;
        this.g7 = g7;
        this.g8 = g8;
        this.g9 = g9;
        this.g11 = g11;
        this.h1 = h1;
        this.h2 = h2;
        this.res1 = res1;
        this.res2 = res2;
        this.res3 = res3;
        this.res4 = res4;
        this.res5 = res5;
        this.res6 = res6;
        this.res7 = res7;

        return this;
    }
}

