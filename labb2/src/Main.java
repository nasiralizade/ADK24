
/* Labb 2 i DD2350 Algoritmer, datastrukturer och komplexitet    */
/* Se labbinstruktionerna i kursrummet i Canvas                  */
/* Ursprunglig författare: Viggo Kann KTH viggo@nada.kth.se      */


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {

  public static final String DEFAULT_DIR = "./test";

  public static List<String> readWordList(BufferedReader input) throws IOException {
    List<String> list = new ArrayList<>();
    String s;
    while ((s = input.readLine()) != null && !s.equals("#")) {
      list.add(s);
    }
    return list;
  }
/*
  public static void main(String args[]) throws IOException {
    if (parseArgs(args)) {
      System.exit(0);
    }
    //    long t1 = System.currentTimeMillis();
    BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
    BufferedWriter stdout = new BufferedWriter(new OutputStreamWriter(System.out));
    // Säkrast att specificera att UTF-8 ska användas, för vissa system har annan
    // standardinställning för teckenkodningen.
    List<String> wordList = readWordList(stdin);
    StringBuilder output = new StringBuilder();
    String word;
    while ((word = stdin.readLine()) != null) {
      ClosestWords closestWords = new ClosestWords(word, wordList);
      output.append(word).append(" (").append(closestWords.getMinDistance()).append(")");
      for (String w : closestWords.getClosestWords()) {
        output.append(" ").append(w);
      }
      output.append("\n");
    }
    stdout.write(output.toString());
    stdout.flush();  // Ensure all output is actually written out
    stdout.close();
    stdin.close();
  }
*/



    public static void main(String[] args) throws IOException {
        String inputFilePath = "ordlista.utf8";
        //String testFilePath = "test/testmedordlista.indata";
        String testFilePath = "mytest.indata";

        long t1 = System.currentTimeMillis();
        BufferedReader inputFile = new BufferedReader(new InputStreamReader(new FileInputStream(inputFilePath), "UTF-8"));
        BufferedReader testFile = new BufferedReader(new InputStreamReader(new FileInputStream(testFilePath), "UTF-8"));
        BufferedWriter stdout = new BufferedWriter(new OutputStreamWriter(System.out));

        List<String> wordList = readWordList(inputFile);
        StringBuilder output = new StringBuilder();
        String word;

        while ((word = testFile.readLine()) != null) {
            ClosestWords closestWords = new ClosestWords(word, wordList);
            output.append(word).append(" (").append(closestWords.getMinDistance()).append(")");
            for (String w : closestWords.getClosestWords()) {
                output.append(" ").append(w);
            }
            output.append("\n");
        }
      long tottime = (System.currentTimeMillis() - t1);
        output.append("CPU time for this test: ").append(tottime).append(" ms\n");
        stdout.write(output.toString());
        stdout.flush();  // Ensure all output is actually written out
        stdout.close();

        inputFile.close();
        testFile.close();


        // Save the time to a file
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("tid.txt", true)))) {
            out.println(tottime);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean parseArgs(String[] args) {
        boolean inTestZone = false;
        boolean beenInTestZone = false;
        int finishedTestBatches = 0;
        if (args.length >= 1) {
            for (String current : args) {
                if (current.equals("-t")) {
                    inTestZone = true;
                    beenInTestZone = true;
                    continue;
                }
                if (inTestZone) {
                    if (current.startsWith("-")) {
                        inTestZone = false;
                        continue;
                    }
                    runTestsInCatalogue(current);
                    finishedTestBatches += 1;
                }
            }
        }
        if (beenInTestZone && finishedTestBatches == 0) {
            runTestsInCatalogue(DEFAULT_DIR);
            finishedTestBatches++;
        }
        return finishedTestBatches > 0;
    }

    public static void runTestsInCatalogue(String folder) {
        System.out.println("Processing folder: " + folder);
        File f = new File(folder);
        String[] filenames = f.list();
        Arrays.sort(filenames);
        final int N_FILES = filenames.length;
        for (int i = 0; i < N_FILES; i++) {
            String current = filenames[i];
            if (current.endsWith(".indata")) { // Potential testcase
                int pointPos = current.indexOf(".");
                String testName = current.substring(0, pointPos);
                String outFileName = testName + ".utdata";
                int outFileIndex = Arrays.binarySearch(filenames, 0, N_FILES, outFileName);
                if (outFileIndex >= N_FILES || !filenames[outFileIndex].equals(outFileName)) {
                    System.out.println("Skipping testcase " + testName + " because I could not find matching answers file.");
                    continue;
                }
                runSingleTestCase(folder, testName, current, outFileName);
            }
        }
    }

    public static void runSingleTestCase(String path, String name, String in, String ans) {
        System.out.println("Processing testcase: " + name);
        long t1 = System.currentTimeMillis();
        BufferedReader inFile = null;
        BufferedReader ansFile = null;
        final String inFilePath = path + "/" + in;
        final String outFilePath = path + "/" + ans;
        try {
            inFile = new BufferedReader(new InputStreamReader(new FileInputStream(inFilePath), "UTF-8"));
            ansFile = new BufferedReader(new InputStreamReader(new FileInputStream(outFilePath), "UTF-8"));
        } catch (Exception e) {
            System.out.println("At least one of the files would not open.");
            e.printStackTrace();
            System.exit(4);
        }
        // Säkrast att specificera att UTF-8 ska användas, för vissa system har annan
        // standardinställning för teckenkodningen.
        List<String> wordList = null;
        try {
            wordList = readWordList(inFile);
        } catch (Exception e) {
            System.out.println("Could not read the wordList of this testcase.");
            System.exit(1);
        }
        String word;
        try {
            while ((word = inFile.readLine()) != null) {
                ClosestWords closestWords = new ClosestWords(word, wordList);
                String answerLine;
                if ((answerLine = ansFile.readLine()) == null) {
                    System.err.println("The file " + ans + " ran out of answers but there were questions left in " + in + ".");
                    System.exit(2);
                }
                Iterator<String> it = (Arrays.asList(answerLine.split("\\s"))).iterator();
                String rightWord = it.next();
                if (!rightWord.equals(word)) {
                    System.out.println("Wrong word. Expected " + rightWord + " and found " + word);
                }
                String candidateDistance = "(" + closestWords.getMinDistance() + ")";
                String rightDistance = it.next();
                if (!candidateDistance.equals(rightDistance)) {
                    System.out.println("Wrong minimum distance. Expected " + rightDistance + " and found " + candidateDistance);
                }
                for (String w : closestWords.getClosestWords()) {
                    if (!it.hasNext()) {
                        System.out.println("Suggested solution has more words than the correct solution.");
                        break;
                    }
                    rightWord = it.next();
                    if (!w.equals(rightWord)) {
                        System.out.println("Wrong word. Expected " + rightWord + " but found " + w);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("File reading error while processing this testcase!");
            e.printStackTrace();
            System.exit(3);
        }
        long tottime = (System.currentTimeMillis() - t1);
        System.out.println("CPU time for this test: " + tottime + " ms");
    }
}

