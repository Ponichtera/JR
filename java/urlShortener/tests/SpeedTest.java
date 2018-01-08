package urlShortener.tests;


import org.junit.Test;
import urlShortener.Helper;
import urlShortener.Shortener;
import urlShortener.Launcher;
import urlShortener.strategy.HashBiMapStorageStrategy;
import urlShortener.strategy.HashMapStorageStrategy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SpeedTest {

    private long getTimeForGettingIds (Shortener shortener, Set <String> strings) {
        Date start = new Date();
        Launcher.getIds(shortener,strings);
        Date stop = new Date();
        return stop.getTime() - start.getTime();
    }

    private long getTimeForGettingStrings (Shortener shortener, Set <Long> ids) {
        Date start = new Date();
        Launcher.getStrings(shortener, ids);
        Date stop = new Date();
        return  stop.getTime() - start.getTime();
    }

    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet<>();

        for (int i = 0; i < 10000; i++)
            origStrings.add(Helper.generateRandomString());

        Set<Long> ids1 = Launcher.getIds(shortener1,origStrings);
        Set<Long> ids2 = Launcher.getIds(shortener2,origStrings);
        Long hashMapIdsTime = getTimeForGettingIds(shortener1, origStrings);
        Long hashBiMapIdsTime = getTimeForGettingIds(shortener2, origStrings);

        assertTrue(hashMapIdsTime > hashBiMapIdsTime);

        Long hashMapStringsTime = getTimeForGettingStrings(shortener1, ids1);
        Long hashBiMapStringsTime = getTimeForGettingStrings(shortener2, ids2);

        assertEquals(hashMapStringsTime, hashBiMapStringsTime, 30);
    }
}
