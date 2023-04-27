package unit11.b;

// let's explore inheritance!
public class Bear {
    private Zoo whereILive;
    private String name;
    public Bear(String theName){
        this.name = theName;
    }
    public Bear(){
        this.name = "Mx. Bear";
    }
    public String getName(){
        return name;
    }
    public static void main(String[] args){
       //Panda meiLan = new Panda("Mei Lan");
        
        Bear mxBear = new Bear();
        Panda mxPandaBear = new Panda();
    }
}

// https://wwf.panda.org/wwf_news/?163461/Is-the-giant-panda-a-bear
class Panda extends Bear{
    // public Panda(String pandaName){
    //     super(pandaName);
    // }
    // public Panda(){
    // }
}

// https://en.wikipedia.org/wiki/List_of_giant_pandas
class Zoo {
    private Panda inhabitant;
}

// Zookeeper, Visitor, etc.
