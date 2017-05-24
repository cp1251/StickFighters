package stickfighters.utils;

import org.newdawn.slick.geom.Vector2f;

/**
 * Created by skazkopodatelev on 5/19/2017.
 */
public abstract class MathUtils {
    public static int dotCode(Vector2f A, Vector2f B1, Vector2f B2){
        int code1 = 0;

        if (A.x < B1.x && A.y > B1.y && A.y < B2.y) code1 = 0b0001;
        if (A.x < B1.x && A.y < B1.y) code1 = 0b1001;
        if (A.x < B1.x && A.y > B2.y) code1 = 0b0101;

        if (A.x > B2.x && A.y > B1.y && A.y < B2.y) code1 = 0b0010;
        if (A.x > B2.x && A.y < B1.y) code1 = 0b1010;
        if (A.x > B2.x && A.y > B2.y) code1 = 0b0110;

        if (A.x <= B2.x && A.x >= B1.x && A.y >= B1.y && A.y <= B2.y) code1 = 0b0000;
        if (A.x <= B2.x && A.x >= B1.x && A.y <= B1.y) code1 = 0b1000;
        if (A.x <= B2.x && A.x >= B1.x && A.y >= B2.y) code1 = 0b0100;

        return code1;
    }

    public static boolean algorithm(Vector2f A1, Vector2f A2, Vector2f B1, Vector2f B2) {
        int code1 = dotCode(A1, B1, B2);
        int code2 = dotCode(A2, B1, B2);

        //Log.info(String.valueOf(code1) + " " + String.valueOf(code2) + " " + String.valueOf(code1 & code2));

        return (code1 & code2) == 0;

    }
}
