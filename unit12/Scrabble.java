package unit12;

public class Scrabble {
    private char[][] board = new char[15][15];
    private String help;
    private String nah = "This is not a legal move";
    
    public void addWord(String w, int r, char col, String dir){
        int c = Character.getNumericValue(col);
        if(dir == "vertical"){
            for (int i = 0; i < w.length(); i++) {
                board[r+i][c] = w.charAt(i);
            }
        }
        if(dir == "horizontal"){
            for (int i = 0; i < w.length(); i++) {
                board[r][c+i] = w.charAt(i);
            }
        }
        else{
            System.out.println(nah);
        }
    }

    public boolean isInDict(String w){
        //Read file and check if word is in dictionary
        return false;
    }

    public int getScore(String w){
        
        for (int i = 0; i < w.length(); i++) {
            convert(w.charAt(i));
        }
        return 4;
    }

    public int convert(char l){
        if(l == 'e' || l == 'a' || l == 'i' || l == 'o'||l == 'n'||l == 'r'||l == 't'||l == 'l'||l == 's'||l == 'u'){
            return 1;
        }
        if(l == 'd'||l == 'g'){
            return 2;
        }
        if(l == 'b'||l == 'c'||l == 'm'||l == 'p'){
            return 3;
        }
        if(l == 'f'||l == 'h'||l == 'v'||l == 'w'||l == 'y'){
            return 4;
        }
        if(l == 'k'){
            return 5;
        }
        if(l == 'j'||l == 'x'){
            return 8;
        }
        if(l == 'q'||l == 'z'){
            return 10;
        }
        else{return 0;}
    }
}


/* 
 * Import scrabble dictionary
 * 
 * ADDING WORDS
 *  make sure word is in dictionary
 *  Add them to board
 *  Make sure all adjacent words are in dictionary
 *  Convert letters to scores
 *  Check for doubles and triples
 *  Add total points from word to player's score
 * 
 * TAKING INPUT
 *  have player input a word, a starting point, and a direction
 *  Make sure word is valid
 *  Add word to board
 *  Add score to 
 *  Print each score and board
 * 
 * PLAYING AGAINST YOU
 *  Unscramble letters in hand + mystery letters
 *  Check to see if word fits on the board and how many points its worth
 *  Add points to array
 *  Cycle array to find the best score and add word
 *  Print each score and board
 */

/* ADDING WORDS
 *  Make sure all adjacent words are in dictionary
 *  Convert letters to scores
 *  Check for doubles and triples
 *  Add total points from word to player's score
 * 
 * TAKING INPUT
 *  have player input a word, a starting point, and a direction
 *  Make sure word is valid
 *  Add word to board
 *  Add score to 
 *  Print each score and board
 * 
 * PLAYING AGAINST YOU
 *  Unscramble letters in hand + mystery letters
 *  Check to see if word fits on the board and how many points its worth
 *  Add points to array
 *  Cycle array to find the best score and add word
 *  Print each score and board
 */