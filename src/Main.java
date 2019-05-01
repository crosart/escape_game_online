import java.util.Scanner;
//  import java.util.logging.Logger;

public class Main {

//  private int gameType = 0;
//  private static Logger logger = Logger.getLogger(Main.class.getName());

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

                    System.out.println("Merci d'avoir joué !");

                } else {

                    System.out.println("Une erreur est survenue ! Veuillez Choisir 1, 2 OU 3");

                }

                System.out.println("\r\n\r\n------------------------------------------------------");
                System.out.println("                     Voulez-vous :                    ");
                System.out.println("\r\n               1 - REJOUER AU MÊME MODE               ");
                System.out.println("           2 - REJOUER MAIS À UN AUTRE MODE           ");
                System.out.println("                      3 - QUITTER                     ");
                System.out.println("------------------------------------------------------");


                sc = new Scanner(System.in);
                replayChoice = sc.nextInt();

            }

            System.out.println("\r\n\r\nMerci d'avoir joué !");

        }

        catch (Exception e) {

            System.out.println("Une erreur est survenue !");

        }

    }


    private static void printIntro() {

        System.out.println(",---.                             ,---.               ");
        System.out.println("|--- ,---.,---.,---.,---.,---.    |  _.,---.,-.-.,---.");
        System.out.println("|    `---.|    ,---||   ||---'    |   |,---|| | ||---'");
        System.out.println("`---'`---'`---'`---^|---'`---'    `---'`---^` ' '`---'");
        System.out.println("                    |  ");
        System.out.println("------------------------------------------------------");
        System.out.println("            ,---.,   .|    |,   .,---.");
        System.out.println("            |   ||\\  ||    ||\\  ||--- ");
        System.out.println("            |   || \\ ||    || \\ ||    ");
        System.out.println("            `---'`  `'`--- ``  `'`---'");

    }

    private void printMenuMode() {

        System.out.println("\r\n\r\n------------------------------------------------------");
        System.out.println("            Choisissez votre mode de jeu !            ");
        System.out.println("\r\n      1 - DEFENDER || 2 - CHALLENGER || 3 - DUEL      ");
        System.out.println("------------------------------------------------------");

    }

/*
    public void printMenuGame() {

        System.out.println("            _______________________________________________");
        System.out.println("           /\\                                              \\");
        System.out.println("           \\ \\       À quel jeu souhaitez vous jouer ?      \\");
        System.out.println("            \\ \\     1 - RECHERCHE +/- || 2 - MASTERMIND      \\");
        System.out.println("             \\ \\______________________________________________\\");
        System.out.println("              \\////////////////////////////////////////////////");

    }

   private Game selectGameType() {

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

            System.out.println("Merci de sélectionner votre mode de jeu avec 1, 2, ou 3");
            return selectGameMode();

        }

    }

}
