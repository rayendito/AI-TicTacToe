import java.util.Arrays;
import java.beans.AppletInitializer;
import java.util.ArrayList;

class Game{
    private char[] grid = {'1','2','3','4','5','6','7','8','9'};
    private ArrayList<Integer> availableSlot = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8));
    private char humanChar = 'O';
    private char AIChar = 'X';
    
    public Game(){
        //i'm a constructor! :-D
    }

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

    public boolean checkWin(char player){
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

    public void updateSlot(){
        for(int i = 0; i <= 8; i++){
            if(grid[i] == humanChar || grid[i] == AIChar) availableSlot.remove(i);
        }
    }

    public int minimax(char[] grid){
        if(checkWin(AIChar)) return 100;
        else if(checkWin(humanChar)) return -100;
        else if(availableSlot.size() == 0) return 0;
        else{
            //iterate through all of the available spaces
            for(int i = 0; i < availableSlot.size(); i++){
                char[] newGrid = grid;
                newGrid[i] = AIChar;
            }
            return 0;
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