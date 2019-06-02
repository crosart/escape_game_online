package game;

import java.util.Scanner;
import org.apache.log4j.Logger;

public class CombinaisonRecherche extends Combinaison {

    /*
    size - The number of characters in the code
    newPropoPc - The new proposition from the program
     */

    private static Logger logger = Logger.getLogger(Main.class.getName());
    private int size = getSize();
    private int newPropoPc = 0;

    /*
    Generates the program's initial code

    propoPcInit - The initial generated code
     */

    int[] codePcInit() {

        int[] propoPcInit = new int[size];

        for (int i = 0; i < size; i++) {

            propoPcInit[i] = 5;

        }

        return propoPcInit;

    }

    /*
    Generates a random code

    codePcGen - The generated code
     */

    @Override
    public int[] codePcGenerator() {

        int[] codePcGen = new int[size];

        for (int i = 0; i < size; i++){

            codePcGen[i] = (int)(Math.random() * 10);

        }

        return codePcGen;

    }

    /*
    Registers the initial user's code

    combiInput - The user's input code
    userCombi - The user's code converted to an Array of int
     */

    @Override
    public int[] inputUserCode() {

        sc = new Scanner(System.in);
        String combiInput;
        int[] userCombi = new int[size];

        logger.info("Veuillez saisir votre combinaison secrète à " + size + " chiffres !");

        try {

            combiInput = sc.nextLine();

            if (combiInput.length() != size) {

                throw new Exception();

            }

            else {

                for (int i = 0; i < size; i++) {

                    userCombi[i] = Integer.parseInt("" + combiInput.charAt(i));

                }

            }

            return userCombi;

        }

        catch (Exception e) {

            logger.info("Une erreur est survenue ! Rappel, votre combinaison secrète doit comporter " + size + " chiffres uniquement.");
            return inputUserCode();

        }

    }

    /*
    Registers the user's response

    equal - Used to verify if the user's input is valid
    minus - Used to verify if the user's input is valid
    plus - Used to verify if the user's input is valid
    inputUser - The user's input response
     */

    @Override
    public String inputUserResponse() {

        sc = new Scanner(System.in);
        CharSequence equal = "=";
        CharSequence minus = "-";
        CharSequence plus = "+";
        String inputUser;

        logger.info("Quelle est votre Réponse ? ( - | + | = )");
        inputUser = sc.nextLine();

        try {

            if ((!inputUser.contains(equal) && !inputUser.contains(minus) && !inputUser.contains(plus)) || inputUser.length() != size){

                throw new Exception();

            }

            else return inputUser;

        }

        catch (Exception e){

            logger.info("Une erreur est survenue ! Rappel, votre réponse secrète doit comporter " + size + " caractères uniquement. ( - | + | = )");
            return inputUserResponse();

        }

    }

    /*
    Registers the user's proposition

    stringCodeUser - The user's input proposition
    codeUser - The user's proposition converted to an Array of int
     */

    @Override
    public int[] inputTentativeUser() {

        sc = new Scanner(System.in);
        String stringCodeUser;
        int[] codeUser = new int[size];


        try {

            stringCodeUser = sc.nextLine();

            if (stringCodeUser.length() != size) {

                throw new Exception();

            }

            else {

                for (int i = 0; i < size; i++) {

                    codeUser[i] = Integer.parseInt("" + stringCodeUser.charAt(i));

                }

            }

            return codeUser;

        }

        catch (Exception e) {

            logger.info("Une erreur est survenue ! Rappel, votre combinaison secrète doit comporter " + size + " chiffres uniquement.");
            return inputTentativeUser();

        }

    }

    /*
    Compares the user's proposition to the program's generated code and generates a response

    pcResponse - The program's response
     */

    @Override
    public String tentativeUser(int compareCodeUser, int compareCodePc) {

        String pcResponse = "";

        if (compareCodeUser == compareCodePc) {

            pcResponse = "=";

        }

        if (compareCodeUser > compareCodePc) {

            pcResponse = "-";

        }

        if (compareCodeUser < compareCodePc) {

            pcResponse = "+";

        }

        return pcResponse;

    }

    /*
    Generates a new proposition for the program

    prevPropoPc - The previous proposition from the program
    newPropoPc - The new proposition from the program
     */

    @Override
    public int tentativePc(char compareUserResponse, int prevPropoPc) {

        if (compareUserResponse == '=') {

            newPropoPc = prevPropoPc;

        }

        else if (compareUserResponse == '-') {

            if (prevPropoPc <= 5) {

                newPropoPc = prevPropoPc / 2;

            }

            else {

                newPropoPc = prevPropoPc - ((prevPropoPc - 5) / 2);

            }

        }

        else if (compareUserResponse == '+') {

            if (prevPropoPc >= 5) {

                newPropoPc = prevPropoPc + ((10 - prevPropoPc) / 2);

            }

            else {

                newPropoPc = prevPropoPc + ((5 - prevPropoPc) / 2);

            }

        }

        return newPropoPc;

    }

}
