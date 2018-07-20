import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class FromDB {
    private static String[] questions = new String[90];
    private static String[] optionsA = new String[90];
    private static String[] optionsB = new String[90];
    private static String[] optionsC = new String[90];
    private static String[] optionsD = new String[90];
    private static int[] correctOption = new int[90];
    private ArrayList<Integer> numbers = new ArrayList<Integer>();
    private static Random rdm = new Random();
    private static int index = -1;
    int[] randomNumbers = new int[88];
    static int m, indexOfRandomNumber = 0;
    int indexOfquestion = 0;
    int indexOfA = 0, indexOfB = 0, indexOfC = 0, indexOfD = 0;
    int indexOfCorrectOption = 0;

    public void connection() {

        try {
            // Connects to local server with username:root, password:xxxx
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/questionbank", "root", "xxxx");
            Statement myStat = myConn.createStatement();
            ResultSet myRs = myStat
                    .executeQuery("Select question,optionA,optionB,optionC,optionD, correctOption from game_question");
            // Assigns question and its options
            while (myRs.next()) {
                questions[indexOfquestion] = myRs.getString("question");
                optionsA[indexOfA] = myRs.getString("optionA");
                optionsB[indexOfB] = myRs.getString("optionB");
                optionsC[indexOfC] = myRs.getString("optionC");
                optionsD[indexOfD] = myRs.getString("optionD");
                correctOption[indexOfCorrectOption] = myRs.getInt("correctOption");
                indexOfA++;
                indexOfB++;
                indexOfC++;
                indexOfD++;
                indexOfquestion++;
                indexOfCorrectOption++;
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("An unexpected error occured");
            System.out.println("Check your connectivity with Database");
        }

    }

    public void randomIndex() { // defines a random number

        for (int i = 0; i < 88; i++)

            numbers.add(i + 1);

        Collections.shuffle(numbers); // shuffling numbers
    }

    public static void printQuestion(int i) {
        System.out.println("Question " + i + " " + questions[i] + "\n" + "A : " + optionsA[i] + "\n" + "B : "
                + optionsB[i] + "\n" + "C : " + optionsC[i] + "\n" + "D : " + optionsD[i] + "\n" + "Correct One : "
                + correctOption[i]);
    }

    public Integer getNumbers() {
        index++;
        return numbers.get(index);
    }

    public String getQuestion(int index) {
        return questions[index];
    }

    public String getOptionsA(int index) {
        return optionsA[index];
    }

    public String getOptionsB(int index) {
        return optionsB[index];
    }

    public String getOptionsC(int index) {
        return optionsC[index];
    }

    public String getOptionsD(int index) {
        return optionsD[index];
    }

    // Brings correct option according to question index

    public String getCorrectOption(int index) {
        String correctAnswer = "";
        if (correctOption[index] == 0)
            correctAnswer = getOptionsA(index);
        else if (correctOption[index] == 1)
            correctAnswer = getOptionsB(index);
        else if (correctOption[index] == 2)
            correctAnswer = getOptionsC(index);
        else if (correctOption[index] == 3)
            correctAnswer = getOptionsD(index);

        return correctAnswer;
    }

}
