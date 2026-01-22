package com.subway;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jh
 * @project com.subway
 * @time 2026/1/22
 */
public class SubwayFeeSystem {
    private static final Map<String, Integer> SUBWAY_STATION = new HashMap<>();
    private static final Map<Integer, Integer> SUBWAY_FEE = new HashMap<>();

    static {
        SUBWAY_STATION.put("沿山大道站", 0);
        SUBWAY_STATION.put("凤凰山站", 1);
        SUBWAY_STATION.put("文德路站", 2);
        SUBWAY_STATION.put("河滨公园站", 3);
        SUBWAY_STATION.put("康安路站", 4);
        SUBWAY_STATION.put("南京工业大学站", 5);
        SUBWAY_STATION.put("兴隆路站", 6);
        SUBWAY_STATION.put("万寿路站", 7);
        SUBWAY_STATION.put("国际健康中心站", 8);

        SUBWAY_FEE.put(0, 2);
        SUBWAY_FEE.put(1, 2);
        SUBWAY_FEE.put(2, 2);
        SUBWAY_FEE.put(3, 3);
        SUBWAY_FEE.put(4, 3);
        SUBWAY_FEE.put(5, 3);
        SUBWAY_FEE.put(6, 4);
        SUBWAY_FEE.put(7, 4);
        SUBWAY_FEE.put(8, 5);
        SUBWAY_FEE.put(9, 5);
    }

    public static int calculateFee(String startStation, String endStation) {
        Integer startIndex = SUBWAY_STATION.get(startStation);
        Integer endIndex = SUBWAY_STATION.get(endStation);
        if (startIndex == null || endIndex == null) {
            throw new IllegalArgumentException("站点名称错误");
        }
        int stationCount = Math.abs(endIndex - startIndex);

        return SUBWAY_FEE.get(stationCount);
    }
}
