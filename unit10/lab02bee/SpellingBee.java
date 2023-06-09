import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SpellingBee {

    private static char[] letters;
    private static char mustUse;


    // TODO construct me!
    public SpellingBee(char[] charecters, char mu){
        mustUse = mu;
        letters = charecters;
    }

    public static boolean checkWord(String word) {
        boolean out = false;
        int c = 0;
        //must be not be under 4 letters
        if(word.length()>=4){
            //loop through word
            for(int i = 0; i<word.length(); i++){
            //loop through letters
            for(int k = 0; k<letters.length; k++){
            if(word.charAt(i)==letters[k]){
                c++;
            }
            }
        }
        if(c==word.length()){
            for(int i = 0; i<word.length(); i++){
                if(word.charAt(i) == mustUse){
                    out = true;
                }
        }
        }
    }
        return out;
    }

    /**
     * Loads the contents of file "filename" as a String.
     * 
     * @param filename the file to load
     * @return the contents of file "filename"
     */
    public static String loadFile(String filename) {
        String contents = "";
        Path path = Paths.get(filename);
        try {
            path = Path.of(ClassLoader.getSystemResource(filename).toURI());
            contents = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return contents;
    }
    public static void mergeSort(String[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        String[] l = new String[mid];
        String[] r = new String[n - mid];
    
        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);
    
        merge(a, l, r, mid, n - mid);
    }

    public static void merge(
        String[] a, String[] l, String[] r, int left, int right) {
 
    int i = 0, j = 0, k = 0;
    while (i < left && j < right) {
        if (l[i].compareTo( r[j])<0) {
            a[k++] = l[i++];
        }
        else {
            a[k++] = r[j++];
        }
    }
    while (i < left) {
        a[k++] = l[i++];
    }
    while (j < right) {
        a[k++] = r[j++];
    }
}
    public static void main(String[] args) {
        String[] words = loadFile("words_dropped.txt").split("\n");
        System.out.println("Loaded " + words.length + " words");
        //TODO solve me!
        SpellingBee bee = new SpellingBee("eybpoga".toCharArray(), 'a');
        for(int i = 0; i<words.length; i++){
            if(checkWord(words[i])){
                System.out.println(words[i]);
            }
        }
        //TODO sort words!
        // mergeSort(words, words.length);
        // //TODO what position in the sorted list is the word "search" ?
        // boolean yay = false;
        // for(int i = 0; i<words.length; i++){
        //     if(words[i].equals("search")){
        //         System.out.println(i);
        //         yay = true;
        //     }
        // }
        // if(yay == false){
        //     System.out.println("Search does not exist in this array");
        // }
    }
}
