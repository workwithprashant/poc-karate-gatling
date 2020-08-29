package webui;

import com.intuit.karate.PerfContext;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Perf manager.
 *
 * @author prashant.a.patil @dell.com
 */
public class PerfManager {

    /**
     * The constant perfEvents.
     */
    public static HashMap<String, Long> perfEvents = new HashMap<>();

    /**
     * Start perf event string.
     *
     * @param perfEventName the perf event name
     * @return the string
     */
    public static String startPerfEvent(String perfEventName) {
        long startTime = System.currentTimeMillis();
        String uniquePerfEventName = String.format("%s#%s", perfEventName, createUUID());
        perfEvents.put(uniquePerfEventName, startTime);
        return uniquePerfEventName;
    }

    /**
     * Stop perf event map.
     *
     * @param uniquePerfEventName the unique perf event name
     * @param map                 the map
     * @param context             the context
     * @return the map
     */
    public static Map<String, Object> stopPerfEvent(String uniquePerfEventName, Map<String, Object> map, PerfContext context) {
        String perfEventName = uniquePerfEventName.split("#")[0];
        long endTime = System.currentTimeMillis();
        context.capturePerfEvent(perfEventName, perfEvents.get(uniquePerfEventName), endTime);
        perfEvents.remove(uniquePerfEventName);
        return Collections.singletonMap("success", true);
    }

    /**
     * Create uuid string.
     *
     * @return the string
     */
    public static String createUUID() {
        return java.util.UUID.randomUUID().toString();
    }
}
