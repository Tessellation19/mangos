package unit11;
public class HighRoll{
    int playerOne = 0;
    int playerTwo = 0;
    int scoreOne = 0;
    int scoreTwo = 0;
    static String winner;
    public void roll(){
        scoreOne = ((int) (Math.random()*6)) + ((int) (Math.random()*6)) + ((int) (Math.random()*6));
        scoreTwo = ((int)(Math.random()*6)) + ((int) (Math.random()*6)) + ((int) (Math.random()*6));
        // System.out.println(scoreOne);
        // System.out.println(scoreTwo);
        if(scoreOne > scoreTwo){
            playerOne += scoreOne;
            // System.out.println("playerOne");
        }
        if(scoreTwo > scoreOne){
            playerTwo += scoreTwo;
            // System.out.println("playerTwo");
        }
    }
    public void turns(){
        while(playerOne < 50 && playerTwo < 50){
            roll();
            // System.out.println(playerOne);
            // System.out.println(playerTwo);
            // System.out.println();
        }
        if(playerOne >= 50 && playerTwo < 50){
            // System.out.println("Player One Wins");
            winner = "One";
        }
        if(playerTwo >= 50 && playerOne < 50){
            // System.out.println("Player Two Wins");
            winner = "Two";
        }
        System.out.println(winner);
    }
    
    public static void main (String args[]){
        HighRoll game = new HighRoll();
        game.turns();
}
}
