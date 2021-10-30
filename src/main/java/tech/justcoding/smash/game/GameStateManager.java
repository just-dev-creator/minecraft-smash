package tech.justcoding.smash.game;

public class GameStateManager {
    public enum GameState {
        LOBBY,
        RUNNING,
        FINISHED
    }

    public static GameState gameState;
}
