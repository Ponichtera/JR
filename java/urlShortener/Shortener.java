package urlShortener;

import urlShortener.strategy.StorageStrategy;

public class Shortener {
    private Long lastId = 0L;
    private StorageStrategy storageStrategy;

    public Shortener(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    public synchronized Long getId (String string) {
        if (storageStrategy.containsValue(string))
            return storageStrategy.getKey(string);
        else storageStrategy.put(++lastId, string);
        return lastId;
    }


    public synchronized String getString (Long id) {
        return storageStrategy.getValue(id);
    }
}
