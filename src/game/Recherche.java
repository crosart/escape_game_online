package game;

import java.util.Arrays;
import org.apache.log4j.Logger;

public class Recherche extends Game {

    private static Logger logger = Logger.getLogger(Main.class.getName());
    private GetProperties properties = new GetProperties();
    private boolean responseFound = false;
    private int size = Integer.parseInt(properties.getSize());
    private int turnMax = Integer.parseInt(properties.getTries());
    private int devmode = Integer.parseInt(properties.getDevmode());
    private int turn = 0;

    @Override
    public Game defenderMode() {


        CombinaisonRecherche defenderRecherche = new CombinaisonRecherche();

        logger.info("\r\n");
        defenderRecherche.inputUserCode();

        logger.info("\r\n\r\n||||| TOUR 1 |||||\r\n\r\n");

        int[] prevPropoPc = defenderRecherche.codePcInit();
        int[] newPropoPc = new int[size];

        logger.info("Je pense que la solution est " + Arrays.toString(defenderRecherche.codePcInit()) + " !");

        while (turn != turnMax && !responseFound) {


            String userResponse = defenderRecherche.inputUserResponse();

            for (int i = 0; i < size; i++) {


                char splitUserResponse = userResponse.charAt(i);
                int splitPrevPropoPc = prevPropoPc[i];

                newPropoPc[i] = defenderRecherche.tentativePc(splitUserResponse, splitPrevPropoPc);

            }

            if (userResponse.matches("\\={" + Integer.valueOf(size).toString() + "}")) {

                responseFound = true;
                logger.info("\r\n\r\nJ'ai la solution ! C'est bien votre combinaison !");
                break;

            }

            if (turn < (turnMax-1)) {

                logger.info("\r\n\r\n||||| TOUR " + (turn + 2) + " |||||\r\n\r\n");

            }



            if (!responseFound) {

                turn++;
                prevPropoPc = newPropoPc;

            }

            if (turn != turnMax) {

                logger.info("Je pense que la solution est " + Arrays.toString(newPropoPc) + " !");

            }

            if (turn == turnMax) {

                logger.info("\r\n\r\nDésolé je n'ai pas trouvé la solution !");

            }

        }

        return null;

    }

    @Override
    public Game challengerMode() {

        CombinaisonRecherche challengerRecherche = new CombinaisonRecherche();
        int[] codePc = challengerRecherche.codePcGenerator();
        String pcResponse = new String();


        logger.info("\r\n");
        logger.info("Ma combinaison secrète à " + size + " chiffres a été générée !");


        if (devmode == 1) {

            logger.info("\r\nMode développeur : Ma combinaison est " + Arrays.toString(codePc));

        }

        while (turn != turnMax && !responseFound) {

            logger.info("\r\n\r\n||||| TOUR " + (turn + 1) + " |||||\r\n\r\n");
            logger.info("Quelle est votre proposition ?");
            int[] tentativeUser = challengerRecherche.inputTentativeUser();



            for (int i = 0; i < size; i++) {

                pcResponse = pcResponse + challengerRecherche.tentativeUser(tentativeUser[i], codePc[i]);

            }

            if (pcResponse.matches("\\={" + Integer.valueOf(size).toString() + "}")) {

                responseFound = true;
                logger.info("\r\n\r\nBravo ! Vous avez trouvé ma combinaison !");
                break;

            }




            if (!responseFound) {

                turn++;
                logger.info("Voici ma réponse : " + pcResponse);

                pcResponse = "";

            }

            if (turn == turnMax) {

                logger.info("\r\n\r\nDommage ! Vous n'avez pas trouvé la solution dans le nombre de tours imparti !");
                logger.info("Mon code était : " + Arrays.toString(codePc));

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

        logger.info("\r\n");
        logger.info("Ma combinaison secrète à " + size + " chiffres a été générée, à vous de saisir la vôtre !");

        if (devmode == 1) {

            logger.info("\r\nMode développeur : Ma combinaison est " + Arrays.toString(codePc) + "\r\n");

        }

        duelRecherche.inputUserCode();


        while (!responseFound) {

            logger.info("\r\n\r\n||||| TOUR " + (turn + 1) + " |||||\r\n\r\n");
            logger.info("Quelle est votre proposition ?");

            if (turn != 0) {
                logger.info("Rappel >>> Votre tentative précédente : " + Arrays.toString(tentativeUser) + " || Ma réponse : " + pcResponse);
            }
            pcResponse = "";
            tentativeUser = duelRecherche.inputTentativeUser();

            for (int i = 0; i < size; i++) {

                pcResponse = pcResponse + duelRecherche.tentativeUser(tentativeUser[i], codePc[i]);

            }

            if (pcResponse.matches("\\={" + Integer.valueOf(size).toString() + "}")) {

                responseFound = true;
                logger.info("\r\n\r\nBravo ! Vous avez trouvé ma combinaison en premier !");
                break;

            }

            logger.info("\r\nVoici ma réponse : " + pcResponse + "\r\n\r\nÀ mon tour de tenter !");
            logger.info("\r\nJe pense que la solution est " + Arrays.toString(newPropoPc) + " !");
            String userResponse = duelRecherche.inputUserResponse();

            for (int i = 0; i < size; i++) {

                char splitUserResponse = userResponse.charAt(i);
                int splitPrevPropoPc = prevPropoPc[i];

                newPropoPc[i] = duelRecherche.tentativePc(splitUserResponse, splitPrevPropoPc);
                prevPropoPc[i] = newPropoPc[i];

            }

            if (userResponse.matches("\\={" + Integer.valueOf(size).toString() + "}")) {

                responseFound = true;
                logger.info("\r\n\r\nJ'ai trouvé votre combinaison en premier ! J'ai gagné !");
                break;

            }

            turn++;

        }

        return null;

    }

}
