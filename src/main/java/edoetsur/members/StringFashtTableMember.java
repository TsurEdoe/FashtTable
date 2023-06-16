package edoetsur.members;

import edoetsur.utils.MurmurHash3;

import java.nio.charset.StandardCharsets;

/**
 * A hash table member that holds its data in an string format
 */
public class StringFashtTableMember implements FashtTableMember {
    private final String data;

    public StringFashtTableMember(String data) {
        this.data = data;
    }

    public Integer calcHash(Integer seed, int maxValue) {
        /*
             I'm using modulo in order keep the hash value within the size of the bit set so it could be used as an index.
             Also, modulo maintains the equal spread functionality required
        */
        return MurmurHash3.hash32(data.getBytes(StandardCharsets.US_ASCII), data.length(), seed) % maxValue;
    }

    @Override
    public String toString() {
        return data;
    }
}
