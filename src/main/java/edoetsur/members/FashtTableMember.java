package edoetsur.members;

/**
 * An interface of the hash table member
 * This is needed to support both int/string member types
 */
public interface FashtTableMember {
    /**
     * Calculates the hash on the data member
     * @param seed - the seed used to create the hash function
     * @param maxValue - the maximum value of the hash value
     * @return the hash calculated from the given hash function on the member data, which is represented
     *          by an Integer in the range [0..maxValue-1]
     */
    Integer calcHash(Integer seed, int maxValue);
}
