import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FNGenMain {

    static private String lstFile;
    static private String namesFile;
    static private final ArrayList<String> wholeStr = new ArrayList<> ();
    static private final ArrayList<String> fullNames = new ArrayList<> ();
    final static private String BASE_PATH = "o://!@//";

    //initialization
    final static private String BLOCK_SPLITTER = "=======================================";
    final static private String LIST_FILE = "list.txt";
    final static private String PARSED_FILE = "parsed.txt";
    final static private String FILE_EXTENSION = ".jpg";
    final static private char[] WASTE_CHARS = {',', ':', '-', '.', ';', '=', '_'}; //chars to delete in all strings (could make reading of all chars from file)

    public static void main (String[] args) {





        fullNames.add ("\n-======== ТІЛЬКИ ІМЕНА ========-" + "\n");

        // кодування файлів - в UTF-8
        try {
            System.setOut(new java.io.PrintStream (System.out, true, "UTF-8"));
        } catch (IOException e) {
            System.out.println ("Something happened with UTF-8 encoding!");
        }

        // set filename to parse
        if (args.length == 0){
            lstFile = BASE_PATH + LIST_FILE;
        } else lstFile = args[0];

        // set filename to write
        if (args.length > 1 && args[1] != null) {
            namesFile = args[1];
        } else namesFile = BASE_PATH + PARSED_FILE;

        // checking of exist lstFile
        if (!Files.exists(Paths.get(lstFile))) {
            System.out.println ("ERROR! There is no file \"" + LIST_FILE + "\" in work directory!");
            System.out.println ("Use the file name as the first parameter of the program or check it name is \"list.txt\"!");
            return;
        }

        // read lstFile and build stringBuilder to parse and split
        try (FileReader fileReader = new FileReader(lstFile);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();

            while (line != null) {
                // якщо не нал і не порожній, то додаєм в білдер, інакше дописуєм сепаратор
                if  (!(line.trim().isEmpty())) { // if line is NOT empty

                    // видалення зайвих знаків
                    StringBuilder tmpStr = new StringBuilder (line);
                    for (int i = 0; i < tmpStr.length (); i++){
                        for (char WASTE_CHAR : WASTE_CHARS) {
                            if (tmpStr.charAt (i) == WASTE_CHAR) tmpStr.setCharAt (i, ' ');
                        }
                    }
                    stringBuilder.append(tmpStr);
                    stringBuilder.append(System.lineSeparator());
                } else {
                    {
                        stringBuilder.append (BLOCK_SPLITTER);
                        stringBuilder.append (System.lineSeparator ());
                    }
                }
                line = bufferedReader.readLine();
            }
            stringBuilder.append (BLOCK_SPLITTER); // сепаратор в самий кінець
            stringBuilder.append (System.lineSeparator ());

            setBlocks (stringBuilder.toString ());
            //System.out.println (stringBuilder.toString ());
        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }


    //methods transferred to this class from the external class because of its unnecessary. All of them set to static
    private static void setBlocks (String s){
        String[] blocks = s.split (BLOCK_SPLITTER);
        for (String b: blocks) {
            setString (b);
        }
    }

    private static void setString (String s){
        StringBuilder blockStrBuilder = new StringBuilder ();
        String[] lines = s.split ("\\n"); // розділив блок на рядки

        for (String line: lines) {
            int numsCount = 0; // лічильник кількості номерів в рядку
            String[] words = line.split ("\\s"); // розділив рядок на слова

            for (String word: words){
                try {
                    Integer.parseInt (word); //ignored
                    numsCount++;

                    if (word.length () < 4)                                 //
                        for (int i = 0; i < (4 - word.length ()); i++)      // append zeros to non4digit numbers to make them 4digit
                            blockStrBuilder.append ("0");                   //

                    blockStrBuilder.append(word).append(FILE_EXTENSION).append(System.lineSeparator());
                    wholeStr.add (blockStrBuilder.toString());
                    blockStrBuilder.delete (0, blockStrBuilder.length ()); //clean before next iteration
                } catch (NumberFormatException e) { }
            }

            if (numsCount == 0) { // if there is no numbers in string (name amd surname found)
                fullNames.add (line.trim () + "\n");
                blockStrBuilder.append (line);
            }
        }

        // write output file to disk
        try {
            PrintWriter writer = new PrintWriter (namesFile, "UTF-8");
            wholeStr.forEach (writer::print);
            fullNames.forEach (writer::print);
            writer.close ();
        } catch (IOException e) {
            System.out.print (e.toString ());
            System.out.println (" - error writing to file.");
        }

    }
}
