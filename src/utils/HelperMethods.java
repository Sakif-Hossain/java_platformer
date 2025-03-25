package utils;

import main.Game;

public class HelperMethods {
    public static boolean CanMove(float x, float y, int width, int height, int[][] levelData) {
        if (!IsSolid(x, y, levelData)) {
            if (!IsSolid(x + width, y + height, levelData)) {
                if (!IsSolid(x + width, y, levelData)) {
                    if (!IsSolid(x, y + height, levelData)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean IsSolid(float x, float y, int[][] levelData) {
        // checks if outside map
        if (x < 0 || x >= Game.GAME_WIDTH) { return true; }
        if (y < 0 || y >= Game.GAME_HEIGHT) { return true; }

        // check each block
        float xIndex = x / Game.TILE_SIZE;
        float yIndex = y / Game.TILE_SIZE;

        int value = levelData[(int)yIndex][(int)xIndex];
        return value >= 48 || value < 0 || value != 11;
    }
}
