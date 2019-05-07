package game;

import org.apache.log4j.Logger;
import java.util.Scanner;

public class Main {

    private static Logger logger = Logger.getLogger(Main.class.getName());

    private int gameMode = 0;
    private Game gameChoice;
    private Scanner sc;

    public static void main(String[] args) {

        printIntro();
        Main initiateGame = new Main();
        initiateGame.init();

    }

    private void init() {

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

                    logger.info("Une erreur est survenue ! Veuillez Choisir 1, 2 OU 3");

                }

                logger.info("\r\n\r\n------------------------------------------------------");
                logger.info("                     Voulez-vous :                    ");
                logger.info("\r\n               1 - REJOUER AU MÊME MODE               ");
                logger.info("           2 - REJOUER MAIS À UN AUTRE MODE           ");
                logger.info("                      3 - QUITTER                     ");
                logger.info("------------------------------------------------------");


                sc = new Scanner(System.in);
                replayChoice = sc.nextInt();

            }

            logger.info("\r\n\r\nMerci d'avoir joué !");

        }

        catch (Exception e) {

            logger.info("Une erreur est survenue !");

        }

    }


    private static void printIntro() {

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

    private void printMenuMode() {

        logger.info("\r\n\r\n------------------------------------------------------");
        logger.info("            Choisissez votre mode de jeu !            ");
        logger.info("\r\n      1 - DEFENDER || 2 - CHALLENGER || 3 - DUEL      ");
        logger.info("------------------------------------------------------");

    }

/*
    public void printMenuGame() {

        logger.info("            _______________________________________________");
        logger.info("           /\\                                              \\");
        logger.info("           \\ \\       À quel jeu souhaitez vous jouer ?      \\");
        logger.info("            \\ \\     1 - RECHERCHE +/- || 2 - MASTERMIND      \\");
        logger.info("             \\ \\______________________________________________\\");
        logger.info("              \\////////////////////////////////////////////////");

    }

   private game.Game selectGameType() {

        return new game.Recherche();

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
