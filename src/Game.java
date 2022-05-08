public class Game {
    String g3;
    String g4;
    String g6;
    String g7;
    String g8;
    String g9;
    String g11;
    int h1;
    int h2;

    public Game makeGame(String g3,String g4,String g6,String g7,String g8,String g9,String g11,int h1,int h2){
        this.g3 = g3;
        this.g4 = g4;
        this.g6 = g6;
        this.g7 = g7;
        this.g8 = g8;
        this.g9 = g9;
        this.g11 = g11;
        this.h1 = h1;
        this.h2 = h2;

        return this;
    }
}
//
//public class Games {
//    String[] setter;
//    int[] answer;
//
//    public String[] initSetter(String g3,String g4,String g6,String g7,String g8,String g9,String g11,int h1,int h2){
//        this.setter = new String[]{g3, g4, g6, g7, g8, g9, g11, Integer.toString(h1), Integer.toString(h2)};
//        return this.setter;
//    }
//
////    public void initResponse(int f1,int f2,int f3,int f4,int f5,int f6,int f7){
////        this.answer = new int[]{f1,f2,f3,f4,f5,f6,f7};
////    }
//
//    public Games(){
//
//    }
//
//}
