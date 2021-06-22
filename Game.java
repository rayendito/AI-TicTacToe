import java.util.Arrays;
import java.util.Collections;
import java.lang.reflect.Array;
import java.util.ArrayList;

class Game{
    private char[] grid = {'1','2','3','4','5','6','7','8','9'};
    private ArrayList<Integer> availableSlot;
    private char humanChar;
    private char AIChar;

    /*constructor?*/
    public Game(){
        //i'm a constructor! :-D
        availableSlot = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8));
        humanChar = 'O';
        AIChar = 'X';
    }

    /*getter*/
    public char[] getGrid(){
        return grid;
    }

    public ArrayList<Integer>  getAvailableSlot(){
        return availableSlot;
    }

    public char getHumanChar(){
        return humanChar;
    }

    public char getAIChar(){
        return AIChar;
    }

    /*non-getter Methods*/
    public void printGrid(){
        //hardcoded bc static lmao
        System.out.println("┏━━━┳━━━┳━━━┓");
        System.out.printf("┃ %c ┃ %c ┃ %c ┃%n",grid[0],grid[1],grid[2]);
        System.out.println("┣━━━╋━━━╋━━━┫");
        System.out.printf("┃ %c ┃ %c ┃ %c ┃%n",grid[3],grid[4],grid[5]);
        System.out.println("┣━━━╋━━━╋━━━┫");
        System.out.printf("┃ %c ┃ %c ┃ %c ┃%n",grid[6],grid[7],grid[8]);
        System.out.println("┗━━━┻━━━┻━━━┛");
    }

    public void updateSlot(){
        for(int i = 0; i <= 8; i++){
            if(grid[i] == humanChar || grid[i] == AIChar) availableSlot.remove(i);
        }
    }

    /*static functions*/
    public static boolean checkWin(char[] grid, char player){
        //winning states copy pasted from freecodecamp article by Ahmad Abdolsaheb
        if ((grid[0] == player && grid[1] == player && grid[2] == player) ||
            (grid[3] == player && grid[4] == player && grid[5] == player) ||
            (grid[6] == player && grid[7] == player && grid[8] == player) ||
            (grid[0] == player && grid[3] == player && grid[6] == player) ||
            (grid[1] == player && grid[4] == player && grid[7] == player) ||
            (grid[2] == player && grid[5] == player && grid[8] == player) ||
            (grid[0] == player && grid[4] == player && grid[8] == player) ||
            (grid[2] == player && grid[4] == player && grid[6] == player))
            {
            return true;
        }
        else return false;
    }

    //balikin ArrayList of indeks dari grid yang bisa diisi
    public static ArrayList<Integer> availables(char[] grid){
        ArrayList<Integer> retval = new ArrayList<>();
        for(int i = 0; i < grid.length; i++){
            if (grid[i] != 'O' && grid[i] != 'X'){
                retval.add(i);
            }
        }
        return retval;
    }

    public static int minimax(char[] grid, boolean maks){
        if(availables(grid).size() == 0){
            if (checkWin(grid, 'O')) return -1;
            if (checkWin(grid, 'X')) return 1;
            return 0;
        }
        else{
            ArrayList<Integer> valueHolder = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0,0));
            if(maks){
                for(Integer i: availables(grid)){
                    char[] newGrid = grid;
                    newGrid[i] = 'X';
                    valueHolder.set(i, minimax(newGrid, !maks));
                }
                Integer maxVal = Collections.max(valueHolder);
                Integer maxIdx = valueHolder.indexOf(maxVal);
                return maxIdx.intValue();
            }
            else{
                for(Integer i: availables(grid)){
                    char[] newGrid = grid;
                    newGrid[i] = 'X';
                    valueHolder.set(i, minimax(newGrid, !maks));
                }
                Integer minVal = Collections.min(valueHolder);
                Integer minIdx = valueHolder.indexOf(minVal);
                return minIdx.intValue();
            }
        }
    }

    public void getBestStep(){
        //teehee bwelom
    }

    public static void main(String[] args) {
        Game dor = new Game();
        dor.printGrid();

    }
}