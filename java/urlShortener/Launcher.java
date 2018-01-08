package urlShortener;


import urlShortener.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Launcher {
    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        if (strings == null) return null;
        Set<Long> result = new HashSet<>();

        for ( String entry : strings)
            result.add(shortener.getId(entry));

        return result;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        if (keys == null) return null;
        Set<String> result = new HashSet<>();

        for ( Long entry : keys)
            result.add(shortener.getString(entry));

        return result;
    }


    public static void testStrategy(StorageStrategy storageStrategy, long elementsNumer){
        Helper.printMessage(storageStrategy.getClass().getSimpleName());
        Shortener shortener = new Shortener(storageStrategy);
        Set<String> testSet = new HashSet<>();

        for (int i = 0; i < elementsNumer; i++)
            testSet.add(Helper.generateRandomString());

        Date start = new Date();
        Set<Long> ids = getIds(shortener, testSet);
        Date stop = new Date();

        long testTime = stop.getTime() - start.getTime();
        Helper.printMessage("getIds time = " + testTime);

        start = new Date();
        Set<String> strings = getStrings(shortener, ids);
        stop  = new Date();

        testTime = stop.getTime() - start.getTime();
        Helper.printMessage("getStrings time = " + testTime);

        Helper.printMessage(testSet.equals(strings) ? "Test passed.": "Test failed.");

    }

    public static void main (String[] args) {
        StorageStrategy hashMapStrategy = new HashMapStorageStrategy();
        Launcher.testStrategy(hashMapStrategy, 10000);

        StorageStrategy ourHashMapStrategy = new OurHashMapStorageStrategy();
        Launcher.testStrategy(ourHashMapStrategy, 10000);

        //StorageStrategy fileStrategy = new FileStorageStrategy();
        //Launcher.testStrategy(fileStrategy, 1000);

        StorageStrategy ourBiMap = new OurHashBiMapStorageStrategy();
        Launcher.testStrategy(ourBiMap, 10000);

        StorageStrategy biMap = new OurHashBiMapStorageStrategy();
        Launcher.testStrategy(biMap, 10000);

        StorageStrategy bidimap = new DualHashBidiMapStorageStrategy();
        Launcher.testStrategy(bidimap, 10000);

    }
}
