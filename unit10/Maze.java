package unit10;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Maze {
    private boolean solution;
    private char[][] maze;

    /**
     * Instantiates a Maze instance based on the provided arguments
     * 
     * @param rows the number of rows
     * @param cols the number of columns
     * @param line the values to be placed in the maze.
     */
    public Maze(int rows, int cols, String line) {
        // TODO part a
        maze = new char[rows][cols];
        int spot = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                maze[r][c] = line.charAt(spot);
                spot++;
            }
        }
    }

    /**
     * @return the starting coordinates as "r c"
     */
    public String getStart() { /* Not shown, plz ignore implementation */
        int z = Arrays.stream(maze).map(String::new).collect(Collectors.joining("")).indexOf('@');
        return "" + z / maze[0].length + " " + z % maze[0].length;
    }

    /**
     * @return the ending coordinates as "r c"
     */
    public String getEnd() { /* Not shown, plz ignore implementation */
        int z = Arrays.stream(maze).map(String::new).collect(Collectors.joining("")).indexOf('$');
        return "" + z / maze[0].length + " " + z % maze[0].length;
    }

    /**
     * Uses recursion to see if the maze has a solution or not.
     * 
     * Suggested algorithm:
     * if R and C are in bounds and spot is !#
     * - if you are at $:
     * - - set has a solution
     * - else:
     * - - mark spot as checked
     * - - recur up
     * - - recur down
     * - - recur left
     * - - recur right
     * 
     * @param r current row index
     * @param c current column index
     */
    public boolean isInBounds(int rw, int cl){
        boolean out = false;
        if(!(rw<0 || rw>=maze.length)&&!(cl<0 || cl>=maze[rw].length)){
            out = true;
        }
        return out;
    }
    public boolean isTravelable(int r, int c){
        if(isInBounds(r, c) && maze[r][c] != '#'&& maze[r][c] != 'v'){
            return true;
        }
        else{return false;}
    }
    private void explore(int r, int c) {
            if(maze[r][c]=='$'){
                solution=true;
            }
            else if(maze[r][c]!= '#'){
                maze[r][c] = 'v';
                if(isTravelable(r-1, c)){
                    explore(r-1,c);
                }
                if(isTravelable(r+1, c)){
                    explore(r+1,c);
                }
                if(isTravelable(r, c+1)){
                    explore(r,c+1);
                }
                if(isTravelable(r, c-1)){
                    explore(r,c-1);
                }
            }
        }

    /**
     * Determines if there is a solution (a path of '.') for this maze.
     * 
     * @return true if the maze has a path from Start (@) to End ($).
     */
    public boolean hasSolution() {
        String start = getStart();
        String end = getEnd(); 
        char startR = start.charAt(0);
        char startC = start.charAt(2);
        int startRow = Character.getNumericValue(startR);
        int startCol = Character.getNumericValue(startC);
        System.out.println(startRow+""+startCol);
        explore(startRow, startCol);
        
        return solution; // replace me!

    }

    // HINT overriding toString may be handy. :)

    public static void check(boolean test) throws AssertionError {
        if (!test)
            throw new AssertionError("sad panda");
    }

    public static void main(String[] args) {
        Maze example = new Maze(3, 3, "#.@.....$");
        check(example.hasSolution());

        Maze case1 = new Maze(5, 7, ".#.#....#.#.##@.....$#...#.##..#...");
        check(case1.hasSolution());

        Maze case2 = new Maze(4, 4, ".#.$.##..##.@..#");
        check(!case2.hasSolution());

        Maze test = new Maze(3, 3, "#.@.....$");
        check(test.hasSolution());

        test = new Maze(3, 3, "##@#####$");
        check(!test.hasSolution());

        test = new Maze(3, 3, "##@#..#.$");
        check(test.hasSolution());

        test = new Maze(3, 3, "#.@#.##.$");
        check(test.hasSolution());

        test = new Maze(3, 3, "##@#.##.$");
        check(!test.hasSolution());

        System.out.println("Happy Panda! \uD83D\uDC3C");

    }

}
