import java.io.*;
import java.nio.file.*;

public class FNGenMain {
    static String lstFile;
    static String namesFile;
    private final static String BASE_PATH = "o://!@//";
    final static String BLOCK_SPLITTER = "=======================================";
    private final static String LIST_FILE = "list.txt";
    private final static String PARSED_FILE = "parsed.txt";
    final static String FILE_EXTENSION = ".jpg";
    //boolean addBasePath = true;
    //static String[] arrayOfStr;



    public static void main (String[] args) {
        try {
            System.setOut(new java.io.PrintStream (System.out, true, "UTF-8"));
        } catch (IOException e) {
        }


    // set filename to parse
        if (args.length == 0){
            lstFile = BASE_PATH + LIST_FILE;
        } else lstFile = args[0];
//        System.out.println ("lstFile is " + lstFile);

    // set filename to write
        if (args.length > 1 && args[1] != null) {
            namesFile = args[1];
        } else namesFile = BASE_PATH + PARSED_FILE;
//        System.out.println ("Parsed file is " + namesFile);

    // check exist file lstFile
        Path islstFile = Paths.get(lstFile);
        Boolean exists = Files.exists(islstFile);
//        System.out.println("Exist of list file - " + exists);

    // check exist file namesFile
        Path isnamesFile = Paths.get(namesFile);
        exists = Files.exists(isnamesFile);
//        System.out.println("Exist of parsed file - " + exists);

    // read lstFile and build stringBuilder to parse and split
        try (FileReader fileReader = new FileReader(lstFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            StringParser stringParser = new StringParser ();

            while (line != null) {
                // якщо не нал і не порожній, то додаєм в білдер, інакше дописуєм сепаратор
                if (line !=null) {
                    if  (!(line.trim().isEmpty())) { // if line is NOT empty
                        stringBuilder.append(line);
                        stringBuilder.append(System.lineSeparator());
                    } else {
                        //if (!(stringBuilder == null || stringBuilder.toString ().equals (""))) //комент, бо на початок теж даю сеператор. Залишив, може згодиться
                        {
                            stringBuilder.append (BLOCK_SPLITTER);
                            stringBuilder.append (System.lineSeparator ());
                        }
                    }
                }
                line = bufferedReader.readLine();
            }
            stringBuilder.append (BLOCK_SPLITTER); // сепаратор в самий кінець
            stringBuilder.append (System.lineSeparator ());


            String wholeStr = new String (stringBuilder.toString ());
            stringParser.setBlocks (wholeStr);
            //System.out.println (wholeStr);
        } catch (IOException e) {
            System.out.println(e.toString());
        }















    } //psvm ends
}
