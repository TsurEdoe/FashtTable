package edoetsur;

import edoetsur.utils.FashtTableUtils;
import edoetsur.members.FashtTableMember;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Random;

/**
 * This class is the needed hash table. (FashtTable stands for Fast-Hash-Table)
 * Actions implemented:
 *      1) Insertion: Inserting a member into this table consists of calculating a total of K hash values (in range of [0..m-1] on the
 *          member and setting the bits in the BitArray (the K hash values are used as the indexes for the BitArray).
 *      2) Containment: Testing a member, or checking to see if the table contains the member, consists of calculating the
 *          same K hash values using the hash functions. If all the bits in the needed indexes are set, the table contains
 *          the member.
 */
public class FashtTable {
    /**
     * Used to hold the information concerning which members the table holds
     */
    private BitSet bitSet;

    /**
     * Used to create the same hash functions used to calculate the indexes of the BitArray to set for each member
     */
    private Integer[] seeds;

    /**
     * Constructor - initializing an empty table
     * @param sizeOfTableInBits - the size of the BitArray (m)
     * @param numberOfHashFunctions - the number of hash functions (K)
     */
    public FashtTable(int sizeOfTableInBits, int numberOfHashFunctions) {
        bitSet = new BitSet(sizeOfTableInBits);
        seeds = new Integer[numberOfHashFunctions];

        Random rand = new Random();

        for (int i = 0; i < numberOfHashFunctions; i++) {
            seeds[i] = rand.nextInt();
        }
    }

    /**
     * This function inserts an array of members into the table
     * @param membersToInsert - members to insert to the table
     */
    public void insertMembers(ArrayList<FashtTableMember> membersToInsert) {
        if (membersToInsert == null || membersToInsert.size() == 0) {
            System.out.println("Error initializing FashtTable: null/empty member array given");
            return;
        }

        System.out.println("Initializing FashtTable with " + membersToInsert.size() +" members...");

        for (FashtTableMember memberToInsert : membersToInsert) {
            insertMember(memberToInsert);
        }
    }

    /**
     * A private method to insert a single member into the table.
     * Calculates the K hash functions and sets one of the m bits for every hash value.
     * @param memberToInsert - the memeber to insert into the table
     */
    private void insertMember(FashtTableMember memberToInsert) {
        if (memberToInsert == null) {
            System.out.println("Error inserting member to FashtTable: null member given");
            return;
        }

        int[] memberToInsertHashes = FashtTableUtils.calculateMemberHashes(memberToInsert, seeds, bitSet.size());

        System.out.println(MessageFormat.format("Inserting member: {0} (hashes: {1})", memberToInsert, Arrays.toString(memberToInsertHashes)));

        for (int memberHash : memberToInsertHashes) {
            bitSet.set(memberHash);
        }
    }

    /**
     * Tests whether the given member had been inserted into the table by checking if all K the bits in the BitArray are
     * set (indexes for the K bits are calculated by using the K hash functions).
     * @param memberToTest - the member to check
     * @return whether or not the given member had been inserted into table before
     */
    public boolean testMember(FashtTableMember memberToTest) {
        int[] memberToTestHashes = FashtTableUtils.calculateMemberHashes(memberToTest, seeds, bitSet.size());

        System.out.println(MessageFormat.format("Testing member: {0} (hashes: {1})", memberToTest, Arrays.toString(memberToTestHashes)));

        for (int memberHash : memberToTestHashes) {
            if (!bitSet.get(memberHash)) {
                return false;
            }
        }
        return true;
    }
}
