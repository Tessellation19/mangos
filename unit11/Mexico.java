package unit11;

public class Mexico {
    int playerOne = 6;
    int playerTwo = 6;
    int roleOne = 0;
    int roleTwo = 0;
    public void roll(){
        roleOne = ((int)(Math.random()*playerOne)) + ((int)(Math.random()*playerOne));
        roleTwo = ((int)(Math.random()*playerTwo)) + ((int)(Math.random()*playerTwo));
        System.out.println(roleOne);
        System.out.println(roleTwo);
    }
    
public void play(){
    while(playerOne >0 && playerTwo > 0 && playerOne<=6 && playerTwo<=6){
        roll();
        if(roleOne > roleTwo){
            playerTwo -= 1;
            System.out.println("two"+playerTwo);
        }
        if(roleTwo > roleOne){
            playerOne -= 1;
            System.out.println("one"+playerOne);
        }
    }
    if(playerOne > 0){
        System.out.println("Player One Wins");
    }
    if(playerTwo > 0){
        System.out.println("Player Two Wins");
    }
}
}
