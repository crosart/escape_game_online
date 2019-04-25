public abstract class Game {

    public abstract Game challengerMode();
    public abstract Game defenderMode();
    public abstract Game duelMode();

   // public Game modeChoice;

    public Game selectMode(int gameMode){

        if (gameMode == 1) {
            this.defenderMode();
        }

        if (gameMode == 2) {
            this.challengerMode();
        }

        if (gameMode == 3) {
            this.duelMode();
        }

        return null;

    }

}
