package orel.vpecherskii.minesweeper.support;

public enum GameState {
    PLAYING,
    WIN,
    LOSE;
    private static GameState gameState;

    public static void setGameState(GameState gameState){
        GameState.gameState=gameState;
    }
    public static GameState getGameState(){return GameState.gameState;}


}
