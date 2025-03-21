package utils;

public class Constants {

    public static final class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class PlayerConstants {
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMP = 2;
        public static final int FALLING = 3;
        public static final int CROUCH = 4;
        public static final int HIT = 5;
        public static final int ATTACK = 6;
        public static final int JUMP_ATTACK_1 = 7;
        public static final int JUMP_ATTACK_2 = 8;

        public static int GetNumberOfSprite(int player_state) {
            return switch (player_state) {
                case PlayerConstants.IDLE -> 5;
                case PlayerConstants.RUNNING -> 6;
                case PlayerConstants.JUMP, PlayerConstants.ATTACK, PlayerConstants.JUMP_ATTACK_1,
                     PlayerConstants.JUMP_ATTACK_2 -> 3;
                case PlayerConstants.FALLING -> 1;
                case PlayerConstants.CROUCH -> 2;
                case PlayerConstants.HIT -> 4;
                default -> 1;
            };
        }
    }
}
