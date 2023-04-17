package unit11;

// 2021 FRQ #3
// https://apcentral.collegeboard.org/media/pdf/ap21-frq-computer-science-a.pdf#page=10
import java.util.ArrayList;
import java.util.Arrays;

class MemberInfo {
    private String name;
    private int gradYear;
    private boolean hasGoodStanding;

    public MemberInfo(String name, int gradYear, boolean hasGoodStanding) {
        this.name = name;
        this.gradYear = gradYear;
        this.hasGoodStanding = hasGoodStanding;
    }

    public String getName() {
        return this.name;
    }

    public int getGradYear() {
        return this.gradYear;
    }

    public boolean inGoodStanding() {
        return hasGoodStanding;
    }

}

public class ClubMembers {

    private ArrayList<MemberInfo> members;

    public ClubMembers(ArrayList<MemberInfo> members) {
        this.members = members;
    }

    public ClubMembers() {
        this(new ArrayList<MemberInfo>());
    }

    public void addMembers(String[] names, int gradYear) {
        int i = 0;
        while(i<names.length){
            MemberInfo member = new MemberInfo(names[i], gradYear, true);
            members.add(member);
        }
    }

    public ArrayList<MemberInfo> removeMembers(int year) {
        int i =0;
        while(i<members.size()){
            if(members.get(i).getGradYear() == year){
                members.remove(i);
            }
        }
        return null; // replace me
    }

    public static void check(boolean test) throws AssertionError {
        if (!test)
            throw new AssertionError("sad panda");
    }

    public static void main(String[] args) {
        MemberInfo[] folks = {
                new MemberInfo(
                        "SMITH, JANE",
                        2019,
                        false),
                new MemberInfo(
                        "FOX, STEVE",
                        2018,
                        true),
                new MemberInfo(
                        "XIN, MICHAEL",
                        2017,
                        false),
                new MemberInfo(
                        "GARCIA, MARIA",
                        2020,
                        true)
        };

        ClubMembers cm = new ClubMembers(new ArrayList<MemberInfo>(Arrays.asList(folks)));
        ArrayList<MemberInfo> returned = cm.removeMembers(2018);
        check(returned.size() == 1);
        check(returned.get(0) == folks[1]);
        check(cm.members.size() == 2);
        check(cm.members.contains(folks[0]));
        check(cm.members.contains(folks[3]));
        System.out.println("Happy Panda! \uD83D\uDC3C");

    }
}