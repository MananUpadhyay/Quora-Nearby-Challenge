/**
 *
 * @author Manan Upadhyay
 * Email: mupadhya@usc.edu
 * GitHub: https://github.com/MananUpadhyay
 * class: Solution, Solution to Quora's Nearby Challenge.
 *
 */
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Solution {
    public static void main(String[] args) throws Exception {
   
        Quora q = new Quora();
        q.readUserInput();
        // q.readInputFile(inputFile);
        System.out.println("Output: ");
        for (int i = 0; i < q.QUERY_COUNT; i++) {
            q.solveQuery(Quora.mQuery[i]);
        }
    }
    
}
