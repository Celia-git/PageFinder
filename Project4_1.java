

import java.util.Scanner;
import java.lang.Math;

/**
 *
 * @author Celia Hough Project 4: Java Programming of OS Concepts
 * CPSC.2800.40676 11/08/2023
 *
 * Project 4-1
 *
 */
public class Project4_1 {

    /**
     * decompose a virtual address into a page number and an offset within the
     * page.
     */
    public static void main(String[] args) {
        // Boundaries of powers for allowable inputs
        int lowerFence = 9;
        int upperFence = 14;

        /* Prompt user for pageSize used in vms
         * int pageSize = 2^n | n is within 9 and 14, inclusive */
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a page size\n");
        int pageSize = in.nextInt();

        while (!isPageSizeValid(pageSize)) {
            System.out.println("Must be a power of 2 between 512 (2^9) and 16384 (2^14), inclusive\n");
            pageSize = in.nextInt();
        }

        /* Prompt User for virtual address
            32-bit addressing: 0 - 4294967295
         */
        System.out.println("Enter a virtual address\n");
        int address = in.nextInt();

        while (address < 0 || address > (Math.pow(2, 32) - 1)) {
            System.out.println("Enter an address between 0 and 4294967295, inclusive\n");
            address = in.nextInt();
        }
        
        // Print Result
        System.out.println(String.format("This address is in virtual page %d at offset %d\n", getPageNumber(address, pageSize), getPageOffset(address, pageSize)));

    }

    // Validate User input Page Size
    public static boolean isPageSizeValid(int pageSize) {

        // Ensure pageSize falls within boundaries 
        boolean is_within_bounds = (pageSize >= 512 && pageSize <= 16384);

        // Ensure pageSize is a power of 2
        double log_2_input = log2(pageSize);
        boolean is_power_of_two = ((int) log_2_input == log_2_input);

        return (is_within_bounds && is_power_of_two);
    }

    // Return Log base 2 of some int
    public static double log2(int n) {
        return (Math.log(n) / Math.log(2));
    }

    /* Calculate page number
            address reference / page size */
    public static int getPageNumber(int address, int pageSize) {
        return (address / pageSize);
    }

    /* Calculate offset 
        address reference MOD page size */
    public static int getPageOffset(int address, int pageSize) {
        return (address % pageSize);
    }

}
