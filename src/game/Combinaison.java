package game;

import java.util.Scanner;

public abstract class Combinaison {

    public abstract String tentativeUser(int compareCodeUser, int compareCodePc);
    public abstract String inputUserResponse();
    public abstract int tentativePc(char compareUserResponse, int prevPropoPc);
    public abstract int[] inputUserCode();
    public abstract int[] codePcGenerator();
    public abstract int[] inputTentativeUser();

    private GetProperties properties = new GetProperties();

    private int size = Integer.parseInt(properties.getSize());
    Scanner sc;


    int getSize() {
        return size;
    }

}