package unit12;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

public class Scrabble {
    private static char[][] board = new char[15][15];
    private static String nah = "This is not a legal move";
    private static int playerScore = 0;
    private static int computerScore = 0;
    private static ArrayList<Character> playerHand = new ArrayList<Character>();
    private static ArrayList<Character> computerHand = new ArrayList<Character>();
    private static ArrayList<Character> bag = new ArrayList<Character>();
    private static boolean turn = false;
    private static String[] words = loadFile("dictionary.txt").split("\n");

    // Not started
    public static boolean adjacent(int r, int c, String w) {

        return true;
    }

    // Not started
    public static String unscrambleWord(ArrayList<Character> hand, char a) {

        return "a";
    }

    // done
    public static void makeBag() {
        for (int i = 0; i < 12; i++) {
            bag.add('e');
        }
        for (int i = 0; i < 9; i++) {
            bag.add('a');
            bag.add('i');
        }
        for (int i = 0; i < 8; i++) {
            bag.add('o');
        }
        for (int i = 0; i < 6; i++) {
            bag.add('n');
            bag.add('r');
            bag.add('t');
        }
        for (int i = 0; i < 4; i++) {
            bag.add('l');
            bag.add('s');
            bag.add('u');
            bag.add('d');
        }
        for (int i = 0; i < 3; i++) {
            bag.add('g');
        }
        for (int i = 0; i < 2; i++) {
            bag.add('b');
            bag.add('c');
            bag.add('m');
            bag.add('p');
            bag.add('f');
            bag.add('h');
            bag.add('v');
            bag.add('w');
            bag.add('y');
            bag.add(' '); // DON'T FORGET THIS :((
        }
        bag.add('k');
        bag.add('j');
        bag.add('x');
        bag.add('q');
        bag.add('z');
    }

    // done
    public static void dealHands() {
        int index = 0;
        for (int i = 0; i < 7; i++) {
            index = (int) (Math.random() * bag.size());
            playerHand.add(bag.get(index));
            bag.remove(index);
        }
        for (int i = 0; i < 7; i++) {
            index = (int) (Math.random() * bag.size());
            computerHand.add(bag.get(index));
            bag.remove(index);
        }
    }

    // This works!!
    public static void drawLetters(int a) {
        if (turn) {
            for (int i = 0; i < a; i++) {
                int index = (int) (Math.random() * bag.size() - 1);
                computerHand.add(bag.get(index));
                bag.remove(index);
            }
        } else if (!turn) {
            for (int i = 0; i < a; i++) {
                int index = (int) (Math.random() * bag.size() - 1);
                playerHand.add(bag.get(index));
                bag.remove(index);
            }
        }
    }

    // This works!!
    public static int getWordScore(String w) {
        int score = 0;
        for (int i = 0; i < w.length(); i++) {
            score += convert(w.charAt(i));
        }
        return score;
    }

    // So does this!
    public static int convert(char l) {
        int out;
        switch (l) {
            case 'e':
            case 'a':
            case 'i':
            case 'o':
            case 'n':
            case 'r':
            case 't':
            case 'l':
            case 's':
            case 'u':
                out = 1;
                break;

            case 'd':
            case 'g':
                out = 2;
                break;
            case 'b':
            case 'c':
            case 'm':
            case 'p':
                out = 3;
                break;
            case 'f':
            case 'h':
            case 'v':
            case 'w':
            case 'y':
                out = 4;
                break;
            case 'k':
                out = 5;
                break;
            case 'j':
            case 'x':
                out = 8;
                break;
            case 'q':
            case 'z':
                out = 10;
                break;
            default:
                out = 0;
        }
        return out;
    }

    // This works!
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

    // Done
    public static void addPoints(int s) {
        if (turn) {
            computerScore += s;
        } else {
            playerScore += s;
        }
    }

    // This Works!!!!
    public static boolean isInDict(String w) {
        boolean out = false;
        for (String thing : words) {
            w: out = true;
            break;
        }
        return out;
    }

    // This works!
    public static void buildBoard() {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = '^';
            }
        }
        // DOUBLE LETTER = @
        int[] rows = { 0, 2, 3, 6 };
        int[] cols = { 3, 6, 7, 6 };
        for (int i = 0; i < 4; i++) {
            int j = rows[i];
            int p = cols[i];
            board[j][p] = '@';
            board[p][j] = '@';
            board[14 - j][p] = '@';
            board[p][14 - j] = '@';
            board[j][14 - p] = '@';
            board[14 - p][j] = '@';
            board[14 - p][14 - j] = '@';
            board[14 - j][14 - p] = '@';
        }
        // 03, 26, 37, 66

        // TRIPLE LETTER = #
        for (int i = 1; i < 15; i += 4) {
            for (int p = 1; p < 15; p += 4) {
                board[i][p] = '#';
                board[p][i] = '#';
            }
        }
        // DOUBLE WORD = *
        for (int i = 0; i < 15; i++) {
            board[i][i] = '*';
            board[i][14 - i] = '*';
            board[14 - i][i] = '*';
        }
        int t = 5;
        board[t][t] = '#';
        board[14 - t][t] = '#';
        board[t][14 - t] = '#';
        board[14 - t][14 - t] = '#';
        // TRIPLE WORD == &
        for (int i = 0; i < 15; i += 7) {
            for (int l = 0; l < 15; l += 7) {
                board[i][l] = '&';
            }
        }
        // Center star = %
        board[7][7] = '%';
    }

    // This works!!
    public static boolean isSpotFree(int r, int c, int l, char dir) {
        int count = 0;
        if (dir == 'h') {
            for (int i = 0; i < l; i++) {
                if (board[r][c + i] == '^' || board[r][c + i] == '%' || board[r][c + i] == '&' || board[r][c + i] == '*'
                        || board[r][c + i] == '@' || board[r][c + i] == '#') {
                    count++;
                }
            }
        } else if (dir == 'v') {
            for (int i = 0; i < l; i++) {
                if (board[r][c + i] == '^' || board[r][c + i] == '%' || board[r][c + i] == '&' || board[r][c + i] == '*'
                        || board[r][c + i] == '@' || board[r][c + i] == '#') {
                    count++;
                }
            }
        }
        if (count == l) {
            return true;
        } else {
            return false;
        }
    }

    // This works!
    public static void putOnBoard(String w, int r, int c, char d) {
        if (d == 'v') {
            for (int i = 0; i < w.length(); i++) {
                board[r + i][c] = w.charAt(i);
            }
        } else if (d == 'h') {
            for (int i = 0; i < w.length(); i++) {
                board[r][c + i] = w.charAt(i);
            }
        } else {
            System.out.println(nah);
            if (turn) {
                computerTurn();
            } else {
                playerTurn();
            }
        }

    }

    // This works!
    public static void boardToString() {
        for (int i = 0; i < board.length; i++) {
            System.out.println(board[i]);
        }
    }

    // Does this work? Haven't checked yet
    public static void removeLetters(String w) {
        ArrayList<Character> word = new ArrayList<Character>();
        for (int i = 0; i < w.length(); i++) {
            word.add(w.charAt(i));
        }
        if (!turn) {
            for (int i = 0; i < playerHand.size(); i++) {
                for (int j = 0; j < w.length(); j++) {
                    if (playerHand.get(i) == word.get(j)) {
                        playerHand.remove(i);
                        word.remove(j);
                    }
                }
            }
        }
        if (turn) {
            for (int i = 0; i < computerHand.size(); i++) {
                for (int j = 0; j < w.length(); j++) {
                    if (computerHand.get(i) == word.get(j)) {
                        computerHand.remove(i);
                        word.remove(j);
                    }
                }
            }
        }
    }

    // it should work hopefully...
    public static void play() {
        buildBoard();
        makeBag();
        dealHands();
        while (bag.size() > 0) {
            if (turn) {
                computerTurn();
                turn = false;
            } else {
                playerTurn();
                turn = true;
            }
            System.out.println("Player" + playerScore);
            System.out.println("Computer" + computerScore);
            boardToString();
        }
        if (playerScore > computerScore) {
            System.out.println("You win!");
        } else if (computerScore >= playerScore) {
            System.out.println("You lose!");
        } else if (computerScore == playerScore) {
            System.out.println("Tis a tie!");
        }
    }

    // Haven't checked
    public static char reverseConvert(int c) {
        char out = '^';
        for (char i = 'a'; i <= 'z'; i++) {
            if (Character.getNumericValue(i) - 10 == c) {
                out = i;
            }
        }
        return out;
    }
 //needs checking
 public static char findDirection(String w, int r, int c){
    int s = getWordScore(w);
    int hs = changeTotalScore(r, c, w, 'h');
    int vs = changeTotalScore(r,c,w,'v');
    if(hs>vs){
        return 'h';
    }
    if(vs>hs){
        return 'v';
    }
    else{
        return 'h';
    }
}

    // Haven't checked
    public static void addWord(String w, int r, char col, char d) {
        int c = Character.getNumericValue(col) - 10;
        if (isInDict(w) && isSpotFree(r, c, w.length(), d) && adjacent(r, c, w)) {
            putOnBoard(w, r, c, d);
            removeLetters(w);
        } else if (turn) {
            System.out.println(nah);
            computerTurn();
        } else {
            System.out.println(nah);
            playerTurn();
        }

    }

    // This works, though will work better when addWords is done
    public static void playerTurn() {
        Scanner s = new Scanner(System.in);
        String word;
        String position;
        String direction;
        int row;
        char col;
        System.out.println("It is now your turn");
        System.out.println("What word would you like to play?");
        word = s.nextLine();
        System.out.println("Where would you like to place the first letter of the word? Ex. h11");
        position = s.nextLine();
        if (position.length() == 2) {
            row = position.charAt(1);
            row = Character.getNumericValue(row);
            col = position.charAt(0);
        } else if (position.length() == 3) {
            int rowOne = position.charAt(1);
            rowOne = Character.getNumericValue(rowOne) * 10;
            int rowTwo = position.charAt(2);
            rowTwo = Character.getNumericValue(rowTwo);
            row = rowOne + rowTwo;
            col = position.charAt(0);
        } else {
            row = 0;
            col = 0;
            System.out.println(nah);
            playerTurn();
        }
        System.out.println("What direction does your word go? ex. vertical or horizontal");
        direction = s.nextLine();

        addWord(word, row, col, direction);
        removeLetters(word);
        turn = false;
        s.close();
    }

    // haven't checked yet
    public static int changeTotalScore(int r, int c, String w, char d) {
        int ws = 0;
        int score = 0;
        for (int i = 0; i < w.length(); i++) {
            ws += convert(w.charAt(i));
        }
        for (int i = 0; i < w.length(); i++) {
            int lS = convert(w.charAt(i));
            score += changeLetterScore(r, c, lS);
            changeWordScore(r, c, w.length(), ws, d);
        }
        return score;
    }

    // needs checking
    public static int changeLetterScore(int r, int c, int s) {
        char a = board[r][c];
        switch (a) {
            case '@':
                s *= 2;
                break;
            case '#':
                s *= 3;
                break;
        }
        return s;
    }

    // needs checking
    public static int changeWordScore(int r, int c, int l, int s, char d) {
        char f = '!';
        if (d == 'v') {
            for (int i = r; i < l; i++) {
                f = board[i][c];
                switch (f) {
                    case '&':
                        s *= 3;
                        break;
                    case '#':
                        s *= 2;
                        break;
                }
            }
        } else if (d == 'h') {
            for (int i = c; i < l; i++) {
                f = board[r][i];
                switch (f) {
                    case '&':
                        s *= 3;
                        break;
                    case '#':
                        s *= 2;
                        break;
                }
            }
        } else {

        }
        return s;
    }

    // Should work haven't check yet
    public static void computerTurn() {
        String bestWord = " ";
        String curWord;
        int bestRow = 0;
        int bestCol = 0;
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] != '^') {
                    curWord = unscrambleWord(computerHand, board[r][c]);
                    if (isInDict(curWord)
                            && (isSpotFree(r, c, curWord.length(), 'v') || isSpotFree(r, c, curWord.length(), 'h'))
                            && adjacent(r, c, curWord) && getWordScore(curWord) > getWordScore(bestWord)) {
                        bestWord = curWord;
                        bestRow = r;
                        bestCol = c;
                    }
                }
            }
        }
        char dir = findDirection(bestWord, bestRow, bestCol);
        char besCol = reverseConvert(bestCol);
        addWord(bestWord, bestRow, besCol, dir);
        removeLetters(bestWord);
    }

    public static void main(String[] args) {
        System.out.println(reverseConvert(7));
    }
}

/*
 * Import scrabble dictionary
 * 
 * ADDING WORDS
 * make sure word is in dictionary - isInDict
 * check if spot is already taken - isSpotFree
 * Add them to board - putOnBoard
 * Make sure all adjacent words are in dictionary - adjacent
 * Convert letters to scores - convert
 * Check for doubles and triples - checkPosition
 * Add total points from word to player's score - addPoints
 * 
 * TAKING INPUT
 * have player input a word, a starting point, and a direction - PlayerTurn
 * Make sure word is valid and add to board- addWord
 * Add score to playerScore - addPoints
 * draw new letters - drawLetters
 * 
 * PLAYING AGAINST YOU
 * Check each spot that has had a piece placed on it
 * Unscramble letters in hand + letter on board
 * Check to see if word fits on the board and how many points its worth
 * if points are larger than largest points, set word to new best word and
 * points to largest points
 * add best word
 * take out letters from array
 * draw new letters
 * remove new letters from bag
 *
 * 
 * TURNS
 * while bag.length >0
 * turn is true
 * computer
 * print score and board
 * turn = false
 * turn is false
 * player
 * print score and board
 * turn = true
 * 
 * compare the two scores
 * print winner
 */