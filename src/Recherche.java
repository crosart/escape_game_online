import java.util.Arrays;

public class Recherche extends Game {

    private GetProperties properties = new GetProperties();
    private boolean responseFound = false;
    private int size = Integer.parseInt(properties.getSize());
    private int turnMax = Integer.parseInt(properties.getTries());
    private int devmode = Integer.parseInt(properties.getDevmode());
    private int turn = 0;

    @Override
    public Game defenderMode() {


        CombinaisonRecherche defenderRecherche = new CombinaisonRecherche();

        System.out.println("\r\n");
        defenderRecherche.inputUserCode();

        System.out.println("\r\n\r\n||||| TOUR 1 |||||\r\n\r\n");

        int[] prevPropoPc = defenderRecherche.codePcInit();
        int[] newPropoPc = new int[size];

        System.out.println("Je pense que la solution est " + Arrays.toString(defenderRecherche.codePcInit()) + " !");

        while (turn != turnMax && !responseFound) {


            String userResponse = defenderRecherche.inputUserResponse();

            for (int i = 0; i < size; i++) {


                char splitUserResponse = userResponse.charAt(i);
                int splitPrevPropoPc = prevPropoPc[i];

                newPropoPc[i] = defenderRecherche.tentativePc(splitUserResponse, splitPrevPropoPc);

            }

            if (userResponse.matches("\\={" + Integer.valueOf(size).toString() + "}")) {

                responseFound = true;
                System.out.println("\r\n\r\nJ'ai la solution ! C'est bien votre combinaison !");
                break;

            }

            if (turn < (turnMax-1)) {

                System.out.println("\r\n\r\n||||| TOUR " + (turn + 2) + " |||||\r\n\r\n");

            }



            if (!responseFound) {

                turn++;
                prevPropoPc = newPropoPc;

            }

            if (turn != turnMax) {

                System.out.println("Je pense que la solution est " + Arrays.toString(newPropoPc) + " !");

            }

            if (turn == turnMax) {

                System.out.println("\r\n\r\nDésolé je n'ai pas trouvé la solution !");

            }

        }

        return null;

    }

    @Override
    public Game challengerMode() {

        CombinaisonRecherche challengerRecherche = new CombinaisonRecherche();
        int[] codePc = challengerRecherche.codePcGenerator();
        String pcResponse = new String();


        System.out.println("\r\n");
        System.out.println("Ma combinaison secrète à " + size + " chiffres a été générée !");

        while (turn != turnMax && !responseFound) {

            System.out.println("\r\n\r\n||||| TOUR " + (turn + 1) + " |||||\r\n\r\n");
            System.out.println("Quelle est votre proposition ?");
            int[] tentativeUser = challengerRecherche.inputTentativeUser();



            for (int i = 0; i < size; i++) {

                pcResponse = pcResponse + challengerRecherche.tentativeUser(tentativeUser[i], codePc[i]);

            }

            if (pcResponse.matches("\\={" + Integer.valueOf(size).toString() + "}")) {

                responseFound = true;
                System.out.println("\r\n\r\nBravo ! Vous avez trouvé ma combinaison !");
                break;

            }




            if (!responseFound) {

                turn++;
                System.out.println("Voici ma réponse : " + pcResponse);

                pcResponse = "";

            }

            if (turn == turnMax) {

                System.out.println("\r\n\r\nDommage ! Vous n'avez pas trouvé la solution dans le nombre de tours imparti !");

            }

        }

        return null;

    }

    @Override
    public Game duelMode() {

        CombinaisonRecherche duelRecherche = new CombinaisonRecherche();
        int[] codePc = duelRecherche.codePcGenerator();
        int[] tentativeUser = null;
        String pcResponse = new String();
        int[] prevPropoPc = duelRecherche.codePcInit();
        int[] newPropoPc = duelRecherche.codePcInit();

        System.out.println("\r\n");
        System.out.println("Ma combinaison secrète à " + size + " chiffres a été générée, à vous de saisir la vôtre !");
        duelRecherche.inputUserCode();


        while (!responseFound) {

            System.out.println("\r\n\r\n||||| TOUR " + (turn + 1) + " |||||\r\n\r\n");
            System.out.println("Quelle est votre proposition ?");

            if (turn != 0) {
                System.out.println("Rappel du tour précédent : Votre tentative : " + Arrays.toString(tentativeUser) + " || Ma réponse : " + pcResponse);
            }
            pcResponse = "";
            tentativeUser = duelRecherche.inputTentativeUser();




            for (int i = 0; i < size; i++) {

                pcResponse = pcResponse + duelRecherche.tentativeUser(tentativeUser[i], codePc[i]);

            }

            if (pcResponse.matches("\\={" + Integer.valueOf(size).toString() + "}")) {

                responseFound = true;
                System.out.println("\r\n\r\nBravo ! Vous avez trouvé ma combinaison en premier !");
                break;

            }

            System.out.println("Voici ma réponse : " + pcResponse + "\r\n\r\nÀ mon tour de tenter !");

            System.out.println("\r\nJe pense que la solution est " + Arrays.toString(newPropoPc) + " !");
            String userResponse = duelRecherche.inputUserResponse();

            for (int i = 0; i < size; i++) {

                char splitUserResponse = userResponse.charAt(i);
                int splitPrevPropoPc = prevPropoPc[i];

                newPropoPc[i] = duelRecherche.tentativePc(splitUserResponse, splitPrevPropoPc);
                prevPropoPc[i] = newPropoPc[i];

            }

            if (userResponse.matches("\\={" + Integer.valueOf(size).toString() + "}")) {

                responseFound = true;
                System.out.println("\r\n\r\nJ'ai trouvé votre combinaison en premier ! J'ai gagné !");
                break;

            }

            turn++;

        }

        return null;
    }

}
