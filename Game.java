import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import javax.print.attribute.standard.PrinterLocation;

import java.util.ArrayList;

class Game{
    private char[] grid = {'1','2','3','4','5','6','7','8','9'};
    private int nAvailableSlot;

    /*constructor?*/
    public Game(){
        //i'm a constructor! :-D
        nAvailableSlot = 9;
    }

    /*getter*/
    public char[] getGrid(){
        return grid;
    }

    public int getNSlot(){
        return nAvailableSlot;
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

    public void updateSlot(int idx, char sopo){
        grid[idx] = sopo;
        nAvailableSlot--;
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

    //input grid sama maks true, return indeks yang paling optimal
    public static int minimax(char[] grid, boolean maks){
        if(availables(grid).size() == 0){
            if (checkWin(grid, 'O')) return -1;
            if (checkWin(grid, 'X')) return 1;
            return 0;
        }
        else{
            ArrayList<Integer> valueHolder = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0,0,0));
            if(maks){
                for(Integer i: availables(grid)){
                    char[] newGrid = Arrays.copyOf(grid, grid.length);
                    newGrid[i] = 'X';
                    valueHolder.set(i, minimax(newGrid, !maks));
                }
                Integer maxVal = Collections.max(valueHolder);
                Integer maxIdx = valueHolder.indexOf(maxVal);
                return maxIdx.intValue();
            }
            else{
                for(Integer i: availables(grid)){
                    char[] newGrid = Arrays.copyOf(grid, grid.length);
                    newGrid[i] = 'X';
                    valueHolder.set(i, minimax(newGrid, !maks));
                }
                Integer minVal = Collections.min(valueHolder);
                Integer minIdx = valueHolder.indexOf(minVal);
                return minIdx.intValue();
            }
        }
    }

    public static void main(String[] args) {
        //scanner obj
        Scanner in = new Scanner(System.in);
        
        //game obj
        Game dor = new Game();

        //gaem
        System.out.println("Selamat bermain Tic Tac Toe!");
        System.out.println("AI by yours truly: rayendito\n");
        dor.printGrid();
        while(dor.getNSlot() > 0){
            //begin
            System.out.println("Giliran anda!");

            //user turn and print
            System.out.print("Pilih angka:");
            int input = in.nextInt();
            dor.updateSlot(input-1, 'O');
            dor.printGrid();

            //AI turn
            System.out.println("AI memilih:");
            int AImove = minimax(dor.getGrid(), true);
            dor.updateSlot(AImove, 'X');
            dor.printGrid();
        }
    }
}