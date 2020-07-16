package com.sanop.platformer;

public class Utils {
    public static Double getRadian(Double x, Double y){
        return Math.atan2(y, x) * 180 / Math.PI - 90;
    }

    public static Double[] toPlayerset(Double startX, Double startY, Engine engine){
        Double pcentx = (engine.getPlayerRightX() + engine.getPlayerLeftX()) / 2.0f;
        Double pcenty = (engine.getPlayerBottomY() + engine.getPlayerTopY()) / 2.0f;
        Double vlen = Math.sqrt((pcentx - startX)*(pcentx - startX) + (pcenty - startY)*(pcenty - startY));
        Double[] res = {(pcentx - startX) / vlen, (pcenty - startY) / vlen, getRadian((pcentx - startX), (pcenty - startY))};
        return res;
    }

    public static Double[] toCenterRand(Double startX, Double startY, Integer integer){
        Double pcentx = 640.0;
        Double pcenty = 360.0;
        Double vlen = Math.sqrt((pcentx - startX)*(pcentx - startX) + (pcenty - startY)*(pcenty - startY));
        Double[] res = {startX + (pcentx - startX) / vlen * 8 * integer, startY + (pcenty - startY) / vlen * 8 * integer, getRadian((pcentx - startX), (pcenty - startY))};
        return res;
    }

    public static int getTickByBeat (double beat) {
        return (int)(3600 * beat / 128) + 3;
    }
}
