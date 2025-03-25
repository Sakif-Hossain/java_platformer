package utils;

import main.Game;

public class HelperMethods {
    public static boolean CanMove(float x, float y, float width, float height, int[][] levelData) {
        if (IsMoveable(x, y, levelData)) {
            if (IsMoveable(x + width, y + height, levelData)) {
                if (IsMoveable(x + width, y, levelData)) {
                    if (IsMoveable(x, y + height, levelData)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean IsMoveable(float x, float y, int[][] levelData) {
        // checks if outside map
        if (x < 0 || x >= Game.GAME_WIDTH) { return false; }
        if (y < 0 || y >= Game.GAME_HEIGHT) { return false; }

        // check each block
        float xIndex = x / Game.TILE_SIZE;
        float yIndex = y / Game.TILE_SIZE;

        int value = levelData[(int)yIndex][(int)xIndex];
        return value < 48 && value >= 0 && value == 11;
    }
}
