package edoetsur.utils;

import edoetsur.members.FashtTableMember;
import edoetsur.members.IntFashtTableMember;
import edoetsur.members.StringFashtTableMember;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is a utilities class holding help functions.
 */
public class FashtTableUtils {
    static final int NUMBER_OF_ARGUMENTS = 4;

    /**
     * Verifies the arguments given to the program
     * @param args - arguments inputted into the ptogram
     * @return whether or not the arguments are valid
     */
    public static boolean verifyArguments(String[] args) {
        if (args.length != NUMBER_OF_ARGUMENTS) {
            return false;
        }

        if (Integer.parseInt(args[0]) == 0) {
            System.out.println("Can't use empty table");
            return false;
        }

        if (Integer.parseInt(args[1]) == 0) {
            System.out.println("Can't use 0 hash functions");
            return false;
        }

        if (!new File(args[2]).exists()) {
            System.out.println("Can't open input file, file doesn't exist");
            return false;
        }

        if (!new File(args[3]).exists()) {
            System.out.println("Can't open test file, file doesn't exist");
            return false;
        }

        return true;
    }

    /**
     * Reads a given file and extracts all of the members from it (comma separated values)
     * @param filePath - path to the members file
     * @return a list containing all of the members in the file
     */
    public static ArrayList<FashtTableMember> getMembersFromFile(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            return null;
        }

        File inputFile = new File(filePath);
        if (!inputFile.exists()) {
            return null;
        }

        ArrayList<FashtTableMember> inputMembers = new ArrayList<>();

        try {
            // Reading the file
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String currentRow;
            // Iterating the file rows
            while ((currentRow = br.readLine()) != null)  {
                // Iterating the values in a current row
                for (String memberData : currentRow.split(",")) {
                    try {
                        // If the member is an int, creates an instance of IntFashtTableMember
                        inputMembers.add(new IntFashtTableMember(Integer.parseInt(memberData)));
                    } catch (NumberFormatException numberFormatException) {
                        // If the integer parsing fails, the value is used to create a StringFashtTableMember
                        inputMembers.add(new StringFashtTableMember(memberData));
                    }
                }
            }
        } catch (IOException ioException) {
            System.out.println("Can't open/read file " + filePath + ": " + ioException.getMessage());
            return null;
        }

        return inputMembers;
    }

    /**
     * Calculates the K hash values for a given member.
     * @param member - the member to calculate the hash values for
     * @param seeds - the seeds used to create the functions calculate said hash values
     * @param maxValue - the size of the BitArray (m), given to constraint the maximum value of the hash functions
     * @return an int[] containing K hash values, each an int in the range of [0..m-1]
     */
    public static int[] calculateMemberHashes(FashtTableMember member, Integer[] seeds, int maxValue) {
        int[] hashes = new int[seeds.length];
        for (int i = 0; i < seeds.length; i++) {
            // I'm using Math.abs() in order to use the return value as an index (there cannot be a negative index)
            hashes[i] = Math.abs(member.calcHash(seeds[i], maxValue));
        }
        return hashes;
    }
}
