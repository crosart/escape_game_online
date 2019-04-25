import java.util.Scanner;
import java.util.logging.Logger;

public class Main {

//    private int gameType = 0;
    private static Logger logger = Logger.getLogger(Main.class.getName());
    int gameMode = 0;
    private Game gameChoice;
    private Scanner sc;

    public static void main(String[] args) {

        printIntro();
        Main initiateGame = new Main();
        initiateGame.init();

    }


    public void init() {


        try {

            for (int replayChoice = 0; replayChoice != 3; ) {

                gameChoice = new Recherche();

                if (replayChoice == 0) {

                    gameChoice = this.selectGameMode();

                } else if (replayChoice == 1) {

                    gameChoice = gameChoice.selectMode(gameMode);

                } else if (replayChoice == 2) {

                    gameChoice = this.selectGameMode();

                } else if (replayChoice == 3) {

                    logger.info("Merci d'avoir joué !");

                } else {
                    // throw new Exception();
                    logger.info("Une erreur est survenue ! Veuillez Choisir 1, 2 OU 3");
                    //                   sc = new Scanner(System.in);
                    //                   replayChoice = sc.nextInt();
                }

                logger.info("Voulez-vous : 1 - Rejouer au même mode | 2 - Rejouer autre mode | 3 - Quitter");
                sc = new Scanner(System.in);
                replayChoice = sc.nextInt();

            }


        }


        catch (Exception e) {

            logger.info("Une erreur est survenue !");
            this.init();

        }

    }


    public static void printIntro() {

        logger.info(",---.                             ,---.               ");
        logger.info("|--- ,---.,---.,---.,---.,---.    |  _.,---.,-.-.,---.");
        logger.info("|    `---.|    ,---||   ||---'    |   |,---|| | ||---'");
        logger.info("`---'`---'`---'`---^|---'`---'    `---'`---^` ' '`---'");
        logger.info("                    |  ");
        logger.info("------------------------------------------------------");
        logger.info("            ,---.,   .|    |,   .,---.");
        logger.info("            |   ||\\  ||    ||\\  ||--- ");
        logger.info("            |   || \\ ||    || \\ ||    ");
        logger.info("            `---'`  `'`--- ``  `'`---'");

    }

/*    public void printMenuGame() {

        logger.info("            _______________________________________________");
        logger.info("           /\\                                              \\");
        logger.info("           \\ \\       À quel jeu souhaitez vous jouer ?      \\");
        logger.info("            \\ \\     1 - RECHERCHE +/- || 2 - MASTERMIND      \\");
        logger.info("             \\ \\______________________________________________\\");
        logger.info("              \\////////////////////////////////////////////////");

    }*/

    public void printMenuMode() {

        logger.info("------------------------------------------------------");
        logger.info("            Choisissez votre mode de jeu !            ");
        logger.info("      1 - DEFENDER || 2 - CHALLENGER || 3 - DUEL      ");
        logger.info("------------------------------------------------------");

    }

/*    private Game selectGameType() {


        return new Recherche();

    }
*/


    private Game selectGameMode() {

        sc = new Scanner(System.in);

        try {

            printMenuMode();

            gameMode = sc.nextInt();

            if (gameMode == 1) {
                return gameChoice.defenderMode();
            }
            else if (gameMode == 2) {
                return gameChoice.challengerMode();
            }
            else if (gameMode == 3) {
                return gameChoice.duelMode();
            }
            else {
                sc = null;
                throw new Exception();
            }

        } catch (Exception e) {
            logger.info("Merci de sélectionner votre mode de jeu avec 1, 2, ou 3");
            return selectGameMode();
        }

    }



}
