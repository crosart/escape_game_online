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
        defenderRecherche.inputUserCode();

        System.out.println("\r\n\r\n||||| TOUR 1 |||||\r\n\r\n");

        int[] prevPropoPc = defenderRecherche.codePcInit();
        int[] newPropoPc = new int[size];

        System.out.println("Je pense que la solution est " + Arrays.toString(defenderRecherche.codePcInit()) + " !");

        while (turn != turnMax && !responseFound) {


            String userResponse = defenderRecherche.inputUserResponse();
            System.out.println("\r\n\r\n||||| TOUR " + (turn + 2) + " |||||\r\n\r\n");

            for (int i = 0; i < size; i++) {


                char splitUserResponse = userResponse.charAt(i);
                int splitPrevPropoPc = prevPropoPc[i];

                newPropoPc[i] = defenderRecherche.tentativePc(splitUserResponse, splitPrevPropoPc);

            }

            if (userResponse.matches("\\={" + Integer.valueOf(size).toString() + "}")) {

                responseFound = true;
                System.out.println("J'ai la solution ! C'est bien votre combinaison !");
                break;

            }

            System.out.println("Je pense que la solution est " + Arrays.toString(newPropoPc) + " !");

            if (!responseFound) {

                turn++;
                prevPropoPc = newPropoPc;

            }

            if (turn == turnMax) {

                System.out.println("Désolé je n'ai pas trouvé la solution !");

            }

        }

        return null;

    }

    @Override
    public Game challengerMode() {

        CombinaisonRecherche challengerRecherche = new CombinaisonRecherche();
        int[] codePc = challengerRecherche.codePcGenerator();
        String pcResponse = new String();



        System.out.println("Ma combinaison secrète à " + size + " chiffres a été générée, quelle est votre proposition ?");

        while (turn != turnMax && !responseFound) {

            int[] tentativeUser = challengerRecherche.inputTentativeUser();

            for (int i = 0; i < size; i++) {

                pcResponse = pcResponse + challengerRecherche.tentativeUser(tentativeUser[i], codePc[i]);

            }

            if (pcResponse.matches("\\={" + Integer.valueOf(size).toString() + "}")) {

                responseFound = true;
                System.out.println("Bravo ! Vous avez trouvé ma combinaison !");
                break;

            }

            System.out.println("Voici ma réponse : " + pcResponse);

            if (!responseFound) {

                turn++;
                pcResponse = "";

            }

            if (turn == turnMax) {

                System.out.println("Dommage ! Vous n'avez pas trouvé la solution dans le nombre de tours imparti !");

            }

        }

        return null;

    }

    @Override
    public Game duelMode() {

        CombinaisonRecherche duelRecherche = new CombinaisonRecherche();
        int[] codePc = duelRecherche.codePcGenerator();


        int[] prevPropoPc = duelRecherche.codePcInit();
        int[] newPropoPc = duelRecherche.codePcInit();

        System.out.println("Ma combinaison secrète à " + size + " chiffres a été générée, à vous de saisir la vôtre !");
        duelRecherche.inputUserCode();


        while (turn != turnMax && !responseFound) {

            System.out.println("Quelle est votre proposition ?");
            int[] tentativeUser = duelRecherche.inputTentativeUser();


            String pcResponse = new String();

            for (int i = 0; i < size; i++) {

                pcResponse = pcResponse + duelRecherche.tentativeUser(tentativeUser[i], codePc[i]);

            }

            if (pcResponse.matches("\\={" + Integer.valueOf(size).toString() + "}")) {

                responseFound = true;
                System.out.println("Bravo ! Vous avez trouvé ma combinaison !");
                break;

            }

            System.out.println("Voici ma réponse : " + pcResponse + "| A mon tour de tenter !");

            System.out.println("Je pense que la solution est " + Arrays.toString(newPropoPc) + " !");
            String userResponse = duelRecherche.inputUserResponse();

            for (int i = 0; i < size; i++) {

                char splitUserResponse = userResponse.charAt(i);
                int splitPrevPropoPc = prevPropoPc[i];

                newPropoPc[i] = duelRecherche.tentativePc(splitUserResponse, splitPrevPropoPc);
                prevPropoPc[i] = newPropoPc[i];

            }

            if (userResponse.matches("\\={" + Integer.valueOf(size).toString() + "}")) {

                responseFound = true;
                System.out.println("J'ai trouvé votre combinaison !");
                break;

            }

            turn++;

        }

        return null;
    }

}
