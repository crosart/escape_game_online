import java.util.Scanner;

public class CombinaisonRecherche extends Combinaison {

    private int size = getSize();
    private int newPropoPc = 0;

    int[] codePcInit() {

        int[] propoPcInit = new int[size];

        for (int i = 0; i < size; i++) {

            propoPcInit[i] = 5;

        }

        return propoPcInit;

    }

    @Override
    public int[] codePcGenerator() {

        int[] codePcGen = new int[size];

        for (int i = 0; i < size; i++){

            codePcGen[i] = (int)(Math.random() * 10);

        }

        return codePcGen;

    }

    @Override
    public int[] inputUserCode() {

        sc = new Scanner(System.in);
        String combiInput;
        int[] userCombi = new int[size];

        System.out.println("Veuillez saisir votre combinaison secrète à " + size + " chiffres !");

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

            System.out.println("Une erreur est survenue ! Rappel, votre combinaison secrète doit comporter " + size + " chiffres uniquement.");
            return inputUserCode();

        }

    }

    @Override
    public String inputUserResponse() {

        sc = new Scanner(System.in);
        CharSequence equal = "=";
        CharSequence minus = "-";
        CharSequence plus = "+";
        String inputUser;

        System.out.println("Quelle est votre Réponse ? ( - | + | = )");
        inputUser = sc.nextLine();

        try {

            if ((!inputUser.contains(equal) && !inputUser.contains(minus) && !inputUser.contains(plus)) || inputUser.length() != size){

                throw new Exception();

            }

            else return inputUser;

        }

        catch (Exception e){

            System.out.println("Une erreur est survenue ! Rappel, votre réponse secrète doit comporter " + size + " caractères uniquement. ( - | + | = )");
            return inputUserResponse();

        }

    }

    @Override
    public int[] inputTentativeUser() {

        sc = new Scanner(System.in);
        int[] codeUser = new int[size];
        String stringCodeUser;

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

            System.out.println("Une erreur est survenue ! Rappel, votre combinaison secrète doit comporter " + size + " chiffres uniquement.");
            return inputTentativeUser();

        }

    }

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
