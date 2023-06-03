package src;

public class Hash  {
    private static final int TABLE_SIZE = 4;
    private String[] table;

    public Hash() {
        table = new String[TABLE_SIZE];
    }

    private int hashFunction(String key) {
        // Truncate the key to the first three characters
        String truncatedKey = key.substring(0, 3);
        // Calculate the hash code by summing the ASCII values of the truncated key characters
        int hashCode = 0;
        for (int i = 0; i < truncatedKey.length(); i++) {
            hashCode += truncatedKey.charAt(i);
        }
        // Map the hash code to a valid index within the table size
        return hashCode % TABLE_SIZE;
    }

    public void insert(String key, String value) {
        int index = hashFunction(key);
        table[index] = value;
    }

    public String search(String key) {
        int index = hashFunction(key);
        return table[index];
    }
    public static void main(String[] args) {
    }
}
