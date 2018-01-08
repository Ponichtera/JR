package urlShortener.tests;


import org.junit.Test;
import urlShortener.Helper;
import urlShortener.Shortener;
import urlShortener.strategy.*;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FunctionalTest {

    public void testStorage(Shortener shortener) {
        String s1 = Helper.generateRandomString();
        String s2 = Helper.generateRandomString();
        String s3 = s1;

        Long id1 = shortener.getId(s1);
        Long id2 = shortener.getId(s2);
        Long id3 = shortener.getId(s3);

        assertNotEquals(id1, id2);
        assertNotEquals(id3, id2);

        assertEquals(id1, id3);

        String id2value1 = shortener.getString(id1);
        String id2value2 = shortener.getString(id2);
        String id2value3 = shortener.getString(id3);

        assertEquals(s1, id2value1);
        assertEquals(s2, id2value2);
        assertEquals(s3, id2value3);
    }

    @Test
    public void testHashMapStorageStrategy() {
        StorageStrategy storageStrategy = new HashMapStorageStrategy();
        Shortener shortener = new Shortener(storageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy() {
        StorageStrategy storageStrategy = new OurHashMapStorageStrategy();
        Shortener shortener = new Shortener(storageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy() {
        StorageStrategy storageStrategy = new FileStorageStrategy();
        Shortener shortener = new Shortener(storageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy() {
        StorageStrategy storageStrategy = new HashBiMapStorageStrategy();
        Shortener shortener = new Shortener(storageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy() {
        StorageStrategy storageStrategy = new DualHashBidiMapStorageStrategy();
        Shortener shortener = new Shortener(storageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy() {
        StorageStrategy storageStrategy = new OurHashBiMapStorageStrategy();
        Shortener shortener = new Shortener(storageStrategy);
        testStorage(shortener);
    }

}