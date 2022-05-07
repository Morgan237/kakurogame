public class Games {
    String[] setter;
    int[] answer;

    public void initSetter(String g3,String g4,String g6,String g7,String g8,String g9,String g11,int h1,int h2){
        this.setter = new String[]{g3, g4, g6, g7, g8, g9, g11, Integer.toString(h1), Integer.toString(h2)};
    }

    public void initResponse(int f1,int f2,int f3,int f4,int f5,int f6,int f7){
        this.answer = new int[]{f1,f2,f3,f4,f5,f6,f7};
    }

    public Games(){

    }

}
