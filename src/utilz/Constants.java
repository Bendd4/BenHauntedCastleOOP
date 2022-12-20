package utilz;

import main.Game;

public class Constants {

	public static class ObjectConstants	{
	public static final int DOOR = 200;
                public static final int CHEST = 133;
                public static final int SHIELD = 150;
                public static final int SWORD = 160;
                public static final int PAINTING1 = 170;
                public static final int PAINTING2 = 175;
                public static final int CROWN = 180;
                
                public static final int ITEM_WIDTH_DEFAULT = 45;
                public static final int ITEM_HEIGHT_DEFAULT = 60;
                
                public static final int CROWN_WIDTH_DEFAULT = 45;
                public static final int CROWN_HEIGHT_DEFAULT = 40;
                
	public static final int DOOR_WIDTH_DEFAULT = 61;
                public static final int DOOR_HEIGHT_DEFAULT = 96;
                public static final int DOOR_WIDTH = (int) (DOOR_WIDTH_DEFAULT * Game.SCALE);
                public static final int DOOR_HEIGHT = (int) (DOOR_HEIGHT_DEFAULT * Game.SCALE);

                public static int GetSpriteAmount(int object_type) {
                    switch (object_type) {
                        case DOOR:
                            return 7;
                    }

                    return 1;
                }
                
                public static final int CHEST_WIDTH_DEFAULT = 28;
	public static final int CHEST_HEIGHT_DEFAULT = 47;
	public static final int CHEST_WIDTH = (int) (CHEST_WIDTH_DEFAULT * Game.SCALE);
	public static final int CHEST_HEIGHT = (int) (CHEST_HEIGHT_DEFAULT * Game.SCALE);
	}

	public static class EnemyConstants {
		public static final int KNIGHT = 233;

	        public static final int IDLE = 0;
		public static final int RUNNING = 9;
		public static final int DEAD = 4;

		public static final int HIT = 5;
		public static final int ATTACK = 1;

		public static final int KNIGHT_WIDTH_DEFAULT = 132;
		public static final int KNIGHT_HEIGHT_DEFAULT = 86;

		public static final int KNIGHT_WIDTH = (int) (KNIGHT_WIDTH_DEFAULT * Game.SCALE);
		public static final int KNIGHT_HEIGHT = (int) (KNIGHT_HEIGHT_DEFAULT * Game.SCALE);

		public static final int KNIGHT_DRAWOFFSET_X = (int) (26 * Game.SCALE);
		public static final int KNIGHT_DRAWOFFSET_Y = (int) (9 * Game.SCALE);

		public static int GetSpriteAmount(int enemy_type, int enemy_state) {

			
			switch (enemy_type) {
				case KNIGHT:
					switch (enemy_state) {
                                            case RUNNING:
                                                    return 7;
                                            case IDLE:
                                                    return 4;
                                            case HIT:
                                                    return 4;
                                            case DEAD:
                                                    return 6;
                                            case ATTACK:

                                                    return 5;

                                            default:
                                                    return 1;
                                    }
			}

			return 0;


			

		}

		public static int GetMaxHealth(int enemy_type) {
			switch (enemy_type) {
				case KNIGHT:
					return 10;

				default:
					return 1;
			}
		}

		public static int GetEnemyDmg(int enemy_type) {
			switch (enemy_type) {
				case KNIGHT:
					return 10;

				default:
					return 1;
			}
		}

	}

	public static class UI {
		public static class Buttons {
			public static final int B_WIDTH_DEFAULT = 140;
			public static final int B_HEIGHT_DEFAULT = 56;
			public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
			public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
		}

		public static class PauseButtons {
			public static final int SOUND_SIZE_DEFAULT = 42;
			public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE / 1.125);
		}

		public static class URMButton {
			public static final int URM_DEFAULT_SIZE = 56;
			public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE * Game.SCALE / 1.125);
		}

		public static class VolumeButtons {
			public static final int VOLUME_DEFAULT_WIDTH = 28;
			public static final int VOLUME_DEFAULT_HEIGHT = 44;
			public static final int SLIDER_DEFAULT_WIDTH = 215;

			public static final int VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH * Game.SCALE / 1.125);
			public static final int VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT * Game.SCALE / 1.125);
			public static final int SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH * Game.SCALE / 1.125);
		}
	}

	public static class Directions {
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}

	public static class PlayerConstants {
		public static final int IDLE = 3;
		public static final int RUNNING = 4;
		public static final int DEAD = 2;

		public static final int HIT = 5;
		public static final int ATTACK_1 = 0;

		public static int GetSpriteAmount(int player_action) {
			switch (player_action) {
				case RUNNING:
					return 7;
				case IDLE:
					return 8;
				case HIT:
					return 4;
				case DEAD:
					return 6;
				case ATTACK_1:

					return 8;

				default:
					return 1;
			}
		}
	}

}
