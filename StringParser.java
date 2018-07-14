import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class StringParser {
    static ArrayList<String> wholeStr = new ArrayList<> ();


    public void setBlocks(String s){
        String[] blocks = s.split (FNGenMain.BLOCK_SPLITTER);
        for (String b: blocks) {
            setString (b);
        }
    }


    private void setString(String s){
        StringBuilder blockStrBuilder = new StringBuilder ();
        String[] lines = s.split ("\\n"); // розділив блок на рядки

        for (String line: lines) {
            int numsCount = 0; // лічильник кількості номерів в рядку
            String[] words = line.split ("\\s"); // розділив рядок на слова

            for (String word: words){
                try {
                    Integer.parseInt (word);
                    numsCount++;
                    if (word.length () == 3) blockStrBuilder.append ("0");
                    blockStrBuilder.append(word).append(FNGenMain.FILE_EXTENSION).append(System.lineSeparator());
                    wholeStr.add (blockStrBuilder.toString());
                    blockStrBuilder.delete (0, blockStrBuilder.length ()); //очищаю перед наступною ітерацією
                } catch (NumberFormatException e) {}
            }

            if (numsCount == 0) { // якщо номерів в рядку нема (маю прізвище та ім'я)
                blockStrBuilder.append (line).append (System.lineSeparator ());
                System.out.println (line);
            }
        }

        //String ws =
        recFile (Arrays.toString (wholeStr.toArray ()));
    }


    private void recFile(String s) {
        try {
            PrintWriter writer = new PrintWriter(FNGenMain.namesFile, "UTF-8");
            wholeStr.forEach (writer::print);
            writer.close();
        } catch (IOException e) {
            System.out.print(e.toString());
            System.out.println (" - error writing to file.");
        }
    }


}
