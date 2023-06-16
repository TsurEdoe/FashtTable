package edoetsur;

import edoetsur.utils.FashtTableUtils;
import edoetsur.members.FashtTableMember;

import java.text.MessageFormat;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        if (args == null || !FashtTableUtils.verifyArguments(args)) {
            System.out.println("Bad arguments given. Usage: " +
                    "<size_of_table_in_bits> <number_of_hash_functions> <path_to_input_file> <path_to_test_file>");
            return;
        }

        ArrayList<FashtTableMember> inputMembers = FashtTableUtils.getMembersFromFile(args[2]);
        ArrayList<FashtTableMember> testMembers = FashtTableUtils.getMembersFromFile(args[3]);

        FashtTable fashtTable = new FashtTable(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("\n\n---------- INSERTING MEMBERS ----------\n");
        fashtTable.insertMembers(inputMembers);
        System.out.println("\n\n----------- TESTING MEMBERS -----------\n");
        for (FashtTableMember memberToTest : testMembers) {
            if (fashtTable.testMember(memberToTest)) {
                System.out.println(MessageFormat.format("Member {0} is IN the table", memberToTest));
            } else {
                System.out.println(MessageFormat.format("Member {0} is NOT IN the table", memberToTest));
            }
        }
    }
}
