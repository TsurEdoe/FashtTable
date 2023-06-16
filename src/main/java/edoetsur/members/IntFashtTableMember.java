package edoetsur.members;

import edoetsur.utils.MurmurHash3;

/**
 * A hash table member that holds its data in an integer format
 */
public class IntFashtTableMember implements FashtTableMember {
    private final Integer data;

    public IntFashtTableMember(Integer data) {
        this.data = data;
    }

    @Override
    public Integer calcHash(Integer seed, int maxValue) {
        /*
             I'm using modulo in order keep the hash value within the size of the bit set so it could be used as an index.
             Also, modulo maintains the equal spread functionality required
        */
        return MurmurHash3.hash32(data, seed) % maxValue;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
