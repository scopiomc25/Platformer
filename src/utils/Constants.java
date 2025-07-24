package utils;

import main.Game;

public class Constants {
    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }
    public static class UI{
        public static class Buttons {
            public static final int B_WIDTH_DEFAULT = 140;
            public static final int B_HEIGHT_DEFAULT = 56;
            public static final int B_WIDTH = (int)(B_WIDTH_DEFAULT * Game.SCALE);
            public static final int B_HEIGHT = (int)(B_HEIGHT_DEFAULT * Game.SCALE);
        }
    }

    public static class PlayerConstants {
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMPING = 2;
        public static final int FALLING = 3;
        public static final int HITTING = 4;
        public static final int GOT_HIT = 5;
        public static final int DEATH = 6;

        public static int getSpriteAmount(int playerAction) {
            switch (playerAction) {
                case IDLE:
                    return 5;
                case RUNNING:
                    return 6;
                case JUMPING, HITTING:
                    return 3;
                case GOT_HIT:
                    return 4;
                case DEATH:
                    return 8;
                case FALLING:
                default:
                    return 1;

            }
        }
    }
}
