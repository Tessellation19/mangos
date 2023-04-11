package unit11;

// 2021 FRQ #1
// https://apcentral.collegeboard.org/media/pdf/ap21-frq-computer-science-a.pdf#page=3
public class WordMatch {
    private String secret;

    public WordMatch(String word) {
        this.secret = word;
    }

    public int scoreGuess(String guess) {
        int score = 0;
        for(int i = 0; i<secret.length(); i++ ){
            int end = i+guess.length()-1;
            if(secret.substring(i,end).equals(guess)){
                score++;
                secret = secret.substring(end-i);
            }
        }
        return score * guess.length() * guess.length(); // replace me!

    }

    public String findBetterGuess(String guess1, String guess2) {
        int score1 = scoreGuess(guess1);
        int score2 = scoreGuess(guess2);
        String ans = null;
        if(score1>score2){
            ans = guess1;
        }
        if(score2>score1){
            ans = guess2;
        }
        if(score1 == score2){
            if(guess1.compareTo(guess2)>1){
                return guess1;
            }
            if(guess1.compareTo(guess2)<1){
                return guess2;
            }
        }
        return ans; // replace me

    }

    public static void parta1() {
        WordMatch game = new WordMatch("mississippi");
        check(game.scoreGuess("i") == 4);
        check(game.scoreGuess("iss") == 18);
        check(game.scoreGuess("issipp") == 36);
        check(game.scoreGuess("mississippi") == 121);
    }

    public static void parta2() {
        WordMatch game = new WordMatch("aaaabb");
        check(game.scoreGuess("a") == 4);
        check(game.scoreGuess("aa") == 12);
        check(game.scoreGuess("aaa") == 18);
        check(game.scoreGuess("aabb") == 16);
        check(game.scoreGuess("c") == 0);
    }

    public static void partb1() {
        WordMatch game = new WordMatch("concatenation");
        check(game.scoreGuess("ten") == 9);
        check(game.scoreGuess("nation") == 36);
        check(game.findBetterGuess("ten", "nation") == "nation");
    }

    public static void partb2() {
        WordMatch game = new WordMatch("concatenation");
        check(game.scoreGuess("cat") == 9);
        check(game.scoreGuess("con") == 9);
        check(game.findBetterGuess("cat", "con") == "con");
        check(game.findBetterGuess("con", "cat") == "con");
    }

    public static void check(boolean test) throws AssertionError {
        if (!test)
            throw new AssertionError("sad panda");
    }

    public static void main(String[] args) {
        parta1();
        parta2();
        partb1();
        partb2();

        System.out.println("Happy Panda! \uD83D\uDC3C");
    }

}
