package unit12;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.BaseStream;
import java.util.ArrayList;
/*TODO
 * FIRST IS FIX DOUBLE LETTER ISSUE 
 * it keeps picking horizontal
 * is spot free isn't working
 * 
 * If the word is too long to fit in the spot, 
 * find a way to find another word that will work 
 * without creating an infinite loop
 * 
 * Write Adjacent method
 * Write a code to see if the word you pick uses characters from your hand or not
 * Write starting rule (must be at h7) into code
 *
 * make a map and make sure code follows said map
 * make it pretty
 *  - add indexies on the outside rings
 *  */
public class Scrabble {
    private static char[][] board = new char[15][15];
    private static String nah = "This is not a legal move";
    private static int playerScore = 0;
    private static int computerScore = 0;
    private static int playerTwoScore = 0;
    private static ArrayList<Character> playerHand = new ArrayList<Character>();
    private static ArrayList<Character> computerHand = new ArrayList<Character>();
    private static ArrayList<Character> playerTwoHand = new ArrayList<Character>();
    private static ArrayList<Character> bag = new ArrayList<Character>();
    private static boolean turn = false;
    private static String[] words = loadFile("./unit12/dictionary.txt").split("\n");

    // Not started
    public static boolean adjacent(int r, int c, String w) {

        return true;
    }

    // Prints opening sequence and rules
    //works, though you could add a bit that makes sure the first word of the game goes through the middle star
    public static void startGame() {
        buildBoard();
        makeBag();
        dealHands();
        System.out.println("Welcome to Scrabble!");
        System.out.println("Your turn will be first, your first word must go through the corrdinate h7");
        System.out.println("The game will tell you if you have made an illegal move and restart your turn");
        System.out.println("Enjoy!");
    }

    // adds all the required letters to bag
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
            // bag.add(' '); // DON'T FORGET THIS :((
        }
        bag.add('k');
        bag.add('j');
        bag.add('x');
        bag.add('q');
        bag.add('z');
    }

    // puts 7 random letters in each hand
    public static void dealHands() {
        int index = 0;
        for (int i = 0; i < 7; i++) {
            index = (int) (Math.random() * bag.size());
            playerHand.add(bag.get(index));
            bag.remove(index);
        }
        for (int i = 0; i < 7; i++) {
            index = (int) (Math.random() * bag.size());
            playerTwoHand.add(bag.get(index));
            bag.remove(index);
        }
    }

    // draws however many letters (a) into whomevers turn it is
    public static void drawLetters(int a) {
        if (turn) {
            for (int i = 0; i < a; i++) {
                int index = (int) (Math.random() * bag.size() - 1);
                playerTwoHand.add(bag.get(index));
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

    //Adds together the scrabble score of each letter in word w
    public static int getWordScore(String w) {
        int score = 0;
        for (int i = 0; i < w.length(); i++) {
            score += convert(w.charAt(i));
        }
        return score;
    }

    //Converts a character to the amount of scrabble points its worth
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

    //loads the dictionary file
    public static String loadFile(String filename) {
        String contents = "";
        Path path = Paths.get(filename);
        try {
            // path = Path.of(ClassLoader.getSystemResource(filename).toURI());
            contents = Files.readString(path);
        } catch (IOException e) {
            // e.printStackTrace();
            // } catch (URISyntaxException e) {
            // e.printStackTrace();
        }
        return contents;
    }

    //adds points s to whomevers turn it is
    public static void addPoints(int s) {
        if (turn) {
            playerTwoScore += s;
        } else {
            playerScore += s;
        }
    }

    //checks if the word is in the dictionary
    public static boolean isInDict(String w) {
        boolean out = false;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(w)) {
                out = true;
            }
        }
        return out;
    }

    //creates the board
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

    //this does not work
    public static boolean isSpotFree(int r, int c, int l, char dir) {
        int count = 0;
            if (dir == 'h') {
                if(c+l<15){
                for (int i = l-1; i >= 0; i--) {
                    if (board[r][c + i] == '^' || board[r][c + i] == '%' || board[r][c + i] == '&'
                            || board[r][c + i] == '*'
                            || board[r][c + i] == '@' || board[r][c + i] == '#') {
                        count++;
                    }
                }
            }
            } else if (dir == 'v') {
                if(r+l<15){
                for (int i = l; i >= 0; i--) {
                    if (board[r + i][c] == '^' || board[r + i][c] == '%' || board[r + i][c] == '&'
                            || board[r + i][c] == '*'
                            || board[r + i][c] == '@' || board[r + i][c] == '#') {
                        count++;
                    }
                }
            }
        }
        if (count == l) {
            return true;
        } else {
            return false;
        }
    }
    
    //puts a word on the board at row r, column c, and in dirrection d
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
            if (turn) {
                playerTwoTurn();
            } else {
                System.out.println(nah);
                playerTurn();
            }
        }
        addPoints(getWordScore(w));
    }

    //prints the board
    public static void boardToString() {
        for (int i = 0; i < board.length; i++) {
            System.out.println(board[i]);
        }
    }

    //Removes the letters in a word from the hand of whomevers turn it is
    public static void removeLetters(String w) {
        ArrayList<Character> word = new ArrayList<Character>();
        for (int i = w.length() - 1; i >= 0; i--) {
            word.add(w.charAt(i));
        }
        if (!turn) {
            playerHand.removeAll(word);
        }
        if (turn) {
            playerTwoHand.removeAll(word);
        }
    }

//plays the players turn, and then the computer just sits there and doesn't do anything
    public static void play() {
        startGame();
        while (bag.size() > 0) {
            if (turn) {
                computerTurn();
                turn = false;
            } else {
                playerTurn();
                turn = true;
            }
            boardToString();
            System.out.println("Player " + playerScore);
            System.out.println("Computer " + computerScore);

        }
        if (playerScore > computerScore) {
            System.out.println("You win!");
        } else if (computerScore >= playerScore) {
            System.out.println("You lose!");
        } else if (computerScore == playerScore) {
            System.out.println("Tis a tie!");
        }
    }

    //changes an integer to a character (a=0, z=25)
    public static char reverseConvert(int c) {
        char out = '^';
        for (char i = 'a'; i <= 'z'; i++) {
            if (Character.getNumericValue(i) - 10 == c) {
                out = i;
            }
        }
        return out;
    }

    //Finds the best direction for the computer to play the best word
    public static char findDirection(String w, int r, int c) {
        int s = getWordScore(w);
        int hs = changeTotalScore(r, c, w, 'h');
        int vs = changeTotalScore(r, c, w, 'v');
        if (hs > vs) {
            return 'h';
        }
        if (vs > hs) {
            return 'v';
        } else {
            return 'h';
        }
    }

    //adds a word to the board, as well as checking if it is in the dictionary and the spot is available. If not it restarts whomevers turn it is
    public static void addWord(String w, int r, char col, char d) {
        int c = Character.getNumericValue(col) - 10;
        if (isLegal(w, r, c, d)) {
            putOnBoard(w, r, c, d);
            removeLetters(w);
        } else if (turn) {
            playerTwoTurn();
        } else if(!turn) {
            System.out.println(nah);
            playerTurn();
        }

    }

    // This works
    public static void playerTurn() {
        Scanner s = new Scanner(System.in);
        String word;
        String position;
        String direction;
        int row;
        char col;
        System.out.println("It is now your turn");
        System.out.println("Here are your tiles " + playerHand);
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
        int c = Character.getNumericValue(col)-10;
        System.out.println("What direction does your word go? Ex. v (for vertical) or h (for horizontal)");
        direction = s.nextLine();
        char d = direction.charAt(0);
        if(isLegal(word, row, c, d)){
            putOnBoard(word, row, c, d);
            removeLetters(word);
            drawLetters(word.length());
        }
        else{
            row = 0;
            col = 0;
            System.out.println(nah);
            playerTurn();
        }
        turn = false;
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

    //infinite loop? somewhere?
    public static void computerTurn() {
        String bestWord = " ";
        String curWord;
        int bestRow = 0;
        int bestCol = 0;
            for(int r=board.length-1; r>=0; r--){
                for(int c = board[r].length-1; c>=0; c--){
                if(Character.getNumericValue(board[r][c])>=10 && Character.getNumericValue(board[r][c])<=35 ){
                    curWord = unscrambleWord(computerHand, board[r][c],r,c);
                    if(isLegal(curWord, r, c, 'h') || isLegal(curWord, r, c, 'v')){
                        bestWord = curWord;
                        bestRow = r;
                        bestCol = c;
                    }
                }
            }
        }
        System.out.println(computerHand);
        System.out.println(bestWord);
        char dir = findDirection(bestWord, bestRow, bestCol);
        if (isLegal(bestWord, bestRow, bestCol, dir)) {
            putOnBoard(bestWord, bestRow, bestCol, dir);
            removeLetters(bestWord);
            drawLetters(bestWord.length());
        }
        else{
            System.out.println("There are no possible solutions, the computer will now skip its turn");
        }
    }
    public static boolean isLegal(String word, int row, int col, char dir){
        if(isInDict(word) && isSpotFree(row, col, word.length(), dir) && adjacent(row, col, word) && ((dir == 'v' && row+word.length()<board.length)||(dir == 'h' && col+word.length()<board[row].length))){
            return true;
        }
        else{return false;}
    }
    // need to figure out how to get words without double letters
    public static String unscrambleWord(ArrayList<Character> hand, char a, int r, int c) {
        ArrayList<String> potential = new ArrayList<String>();
        String currantWord = " ";
        hand.add(a);
        hand = sortAlphabetical(hand);
        System.out.println(hand);
        //System.out.println(hand.size());
        // for each word in the dictionary
        for (int i = 0; i < words.length; i++) {
            ArrayList<Character> tempHand = new ArrayList<Character>();
            for (int j = 0; j < hand.size(); j++) {
                tempHand.add(hand.get(j));
            }
            currantWord = words[i];
            if(currantWord.length()<=hand.size()){
            //make currantWord into an ArrayList
            ArrayList<Character> curWord = new ArrayList<Character>();
            for (int j = 0; j < currantWord.length(); j++) {
                curWord.add(currantWord.charAt(j));
            }
            curWord = sortAlphabetical(curWord);
            //System.out.println(curWord);
            while(curWord.size()<=tempHand.size()&&curWord.size()<0){
            for (int j = 0; j < curWord.size(); j++) {
                if(tempHand.get(j)==curWord.get(j)){
                    c++;
                }
                else{
                    tempHand.remove(j);
                    j--;
                }
            }
        }
            if (tempHand.equals(curWord)) {
                //System.out.println(currantWord);
                //potential.add(currantWord);
                for (int l = 0; l < currantWord.length(); l++) {
                    if (currantWord.charAt(l) == a) {
                        //System.out.println(currantWord);
                        potential.add(currantWord);
                    }
                }
            }
        }
            
        }
        //System.out.println(potential);
        String best = " ";
        for (int p = 0; p < potential.size(); p++) {
            if (getWordScore(potential.get(p)) >= getWordScore(best)&&(isLegal(potential.get(p),r,c,'v')||isLegal(potential.get(p),r,c,'h'))) {
                best = potential.get(p);
            }
        }
        return best;
    }
    public static ArrayList<Character> sortAlphabetical(ArrayList<Character> bob){
        for (int i = 0; i < bob.size()-1; i++) {
            for (int j = i+1; j < bob.size(); j++) {
                if(bob.get(j)>=bob.get(i)){
                    char q = bob.get(j);
                    bob.remove(j);
                    bob.add(i, q);
                }
            }
        }
        return bob;
    }
    public static void playTwo(){
        startGame();
        while (bag.size() > 0) {
            if (turn) {
                playerTwoTurn();
                turn = false;
            } else {
                playerTurn();
                turn = true;
            }
            boardToString();
            System.out.println("Player one" + playerScore);
            System.out.println("Player two " + playerTwoScore);

        }
        if (playerScore > playerTwoScore) {
            System.out.println("You win!");
        } else if (playerTwoScore >= playerScore) {
            System.out.println("You lose!");
        } else if (playerTwoScore == playerScore) {
            System.out.println("Tis a tie!");
        }
    }
    public static void playerTwoTurn(){
        Scanner s = new Scanner(System.in);
        String word;
        String position;
        String direction;
        int row;
        char col;
        System.out.println("It is now player two's turn");
        System.out.println("Here are your tiles " + playerTwoHand);
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
            playerTwoTurn();
        }
        int c = Character.getNumericValue(col)-10;
        System.out.println("What direction does your word go? Ex. v (for vertical) or h (for horizontal)");
        direction = s.nextLine();
        char d = direction.charAt(0);
        if(isLegal(word, row, c, d)){
            putOnBoard(word, row, c, d);
            removeLetters(word);
            drawLetters(word.length());
        }
        else{
            row = 0;
            col = 0;
            System.out.println(nah);
            playerTwoTurn();
        }
        turn = true;
    }
    public static void main(String[] args) {
        // String word = "appltyz";
        // char mu = 'e';
        // ArrayList<Character> words = new ArrayList<Character>();
        // for (int i = 0; i < word.length(); i++) {
        //     words.add(word.charAt(i));   
        // }
        // System.out.println(unscrambleWord(words, mu, 0, 0));
        //System.out.println(sortAlphabetical(words));
        playTwo();
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
 * 
 * 
 * Make IT Pretty
 */