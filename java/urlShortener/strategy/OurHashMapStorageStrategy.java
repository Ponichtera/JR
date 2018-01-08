package urlShortener.strategy;

public class OurHashMapStorageStrategy implements StorageStrategy{

    private static final int        DEFAULT_INITIAL_CAPACITY    = 16;
    private static final float      DEFAULT_LOAD_FACTOR         = 0.75F;
    private Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
    private int size;
    private int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    private float loadFactor = DEFAULT_LOAD_FACTOR;


    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        if (value == null)
            return false;

       for(Entry table : table) {
           for (Entry entry = table; entry != null; entry = entry.next)
               if (value.equals(entry.value))
                   return true;
       }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        int hash = hash(key);
    addEntry(hash, key, value, indexFor(hash, table.length));
    }

    @Override
    public Long getKey(String value) {
        if (value == null) return 0L;

        for (Entry table : table) {
            for(Entry entry = table; entry != null; entry = entry.next)
                if (entry.value.equals(value))
                    return entry.key;
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        Entry entry = getEntry(key);
        return entry == null ? null : getEntry(key).value;
    }

    public int hash(Long k) {
        return k.hashCode();
    }

    public int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    public Entry getEntry(Long key){
        if (size == 0) return null;

        int hash = (key == null) ? 0 : hash(key);
        int i = indexFor(hash, table.length);

        for (Entry entry = table[i]; entry != null; entry = entry.next ){
            if ( entry.hash == hash &&
                    ( entry.key == key || (key != null && key.equals(entry.key))))
                return entry;
        }

        return null;
    }

    public void resize(int newCapacity) {
        Entry[] oldTable = table;
        int oldCapacity = oldTable.length;
        if (oldCapacity == (1 << 30)) {
            threshold = Integer.MAX_VALUE;
            return;
        }
        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int)Math.min(newCapacity * loadFactor, (1 << 30) + 1);
    }
    public void transfer(Entry[] newTable) {
        int newCapacity = newTable.length;
        for (Entry entry : table) {
            while(entry != null) {
                Entry next = entry.next;
                int i = indexFor(entry.hash, newCapacity);
                entry.next = newTable[i];
                newTable[i] = entry;
                entry = next;
            }
        }
    }

    public void addEntry(int hash, Long key, String value, int bucketIndex) {
        if ((size >= threshold) && (table[bucketIndex] != null)) {
            resize(2 * table.length);
            hash = (null != key) ? hash(key) : 0;
            bucketIndex = indexFor(hash, table.length);
        }
        createEntry(hash, key, value, bucketIndex);
    }

    public void createEntry(int hash, Long key, String value, int bucketIndex) {
        Entry e = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, e);
        size++;
    }
}
