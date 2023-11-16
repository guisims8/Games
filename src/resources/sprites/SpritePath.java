package resources.sprites;

public class SpritePath {
    public static final String commonPathR =
            "C:\\Users\\guisi\\Documents\\AcademiaDeCodigo\\" +
                    "Pong\\resources\\images\\nyanCatSprites" +
                    "\\movingRight\\nyan";
    public static final String commonPathL =
            "C:\\Users\\guisi\\Documents\\AcademiaDeCodigo\\" +
                    "Pong\\resources\\images\\nyanCatSprites" +
                    "\\movingLeft\\nyan";

    public static final String commonPathEndGame =
            "C:\\Users\\guisi\\Documents\\AcademiaDeCodigo\\" +
                    "Pong\\resources\\images\\nyanCatSprites" +
                    "\\endGame\\nyan";
    public static final String
            NYAN1R = commonPathR + "1.png",
            NYAN2R = commonPathR + "2.png",
            NYAN3R = commonPathR + "3.png",
            NYAN4R = commonPathR + "4.png",
            NYAN5R = commonPathR + "5.png",
            NYAN1L = commonPathL + "1.png",
            NYAN2L = commonPathL + "2.png",
            NYAN3L = commonPathL + "3.png",
            NYAN4L = commonPathL + "4.png",
            NYAN5L = commonPathL + "5.png",
            NYAN1_END = commonPathEndGame + "1.png",
            NYAN2_END = commonPathEndGame + "2.png",
            NYAN3_END = commonPathEndGame + "3.png",
            NYAN4_END = commonPathEndGame + "4.png",
            NYAN5_END = commonPathEndGame + "5.png";

    public static final String[] GOING_RIGHT_SPRITES = {
            NYAN1R, NYAN2R, NYAN3R, NYAN4R, NYAN5R,
    };
    public static final String[] GOING_LEFT_SPRITES = {
            NYAN1L, NYAN2L, NYAN3L, NYAN4L, NYAN5L,
    };

    public static final String[] END_GAME_SPRITES = {
            NYAN1_END, NYAN2_END, NYAN3_END, NYAN4_END, NYAN5_END,
    };
}
