import java.util.Scanner;

public abstract class Combinaison {

    public abstract String tentativeUser(int compareCodeUser, int compareCodePc);
    public abstract String inputUserResponse();
    public abstract int tentativePc(char compareUserResponse, int prevPropoPc);
    public abstract int[] inputUserCode();
    public abstract int[] codePcGenerator();
    public abstract int[] inputTentativeUser();


    private int size = 4;
    private int[] combiGen = new int[size];
    int[] userCombi = new int[size];
    Scanner sc;
    public CombinaisonRecherche propoPc;


    public int getSize() {
        return size;
    }

    public int[] getCombiGen() {
        return combiGen;
    }

    public void setSize(int size) {
        this.size = size;
    }
}