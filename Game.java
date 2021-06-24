import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;

class Game{
    private char[] grid = {' ',' ',' ',' ',' ',' ',' ',' ',' '};
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

    //input grid sama maks true, return roots of bobot?
    public static int minimax(char[] grid, boolean maks){
        if(availables(grid).size() == 0 || checkWin(grid, 'O') || checkWin(grid, 'X')){
            if (checkWin(grid, 'O')) return -1;
            if (checkWin(grid, 'X')) return 1;
            return 0;
        }
        else{
            ArrayList<Integer> valueHolder = new ArrayList<Integer>();
            if(maks){
                for(Integer i: availables(grid)){
                    char[] newGrid = Arrays.copyOf(grid, grid.length);
                    newGrid[i] = 'X';
                    valueHolder.add(minimax(newGrid, !maks));
                }
                return Collections.max(valueHolder);
            }
            else{
                for(Integer i: availables(grid)){
                    char[] newGrid = Arrays.copyOf(grid, grid.length);
                    newGrid[i] = 'O';
                    valueHolder.add(minimax(newGrid, !maks));
                }
                return Collections.min(valueHolder);
            }
        }
    }

    //find the best step, input grid, output indx to go
    public static int nextStepAI(char[] grid){
        //get indexes of available slots
        ArrayList<Integer> avail = availables(grid);

        //create a map to define indexes associated with corresponding values
        HashMap<Integer, Integer> indeksBobot = new HashMap<>();

        //trying out each slot and see what happens, call the minimax function
        for(Integer i: avail){
            char[] newGrid = Arrays.copyOf(grid, grid.length);
            newGrid[i] = 'X';
            indeksBobot.put(i, minimax(newGrid, false));
        }

        //find the key of the largest value ov value(lmao gjls) bc AI in this case is the maximizer
        Map.Entry<Integer, Integer> maxs = null;
        for(Map.Entry<Integer, Integer> entry: indeksBobot.entrySet()){
            if (maxs == null || entry.getValue().compareTo(maxs.getValue()) > 0){
                maxs = entry;
            }
        }

        //r3turn

        return maxs.getKey();
    }

    public static void main(String[] args) {
        //scanner obj
        Scanner in = new Scanner(System.in);
        
        //game obj
        Game dor = new Game();

        //gaem
        System.out.println("Selamat bermain Tic Tac Toe dengan AI!");
        System.out.println("Anda bermain sebagai 'O', AI bermain sebagai 'X'");
        System.out.println("Nomor kotak urut 1-9 dari kiri atas ke kanan bawah, seperti membaca buku :D");
        System.out.println("<>'d with <3, rayendito\n");
        dor.printGrid();
        while(dor.getNSlot() > 0){
            //begin
            System.out.println("Giliran anda!");

            //user turn and print
            System.out.print("Pilih angka: ");
            int input = in.nextInt();
            dor.updateSlot(input-1, 'O');
            dor.printGrid();
            if(checkWin(dor.getGrid(), 'O')) System.out.println("To print this line is to do something impossible");
            else if(dor.getNSlot() == 0){
                System.out.println("Draw! Thank you for playing");
                break;
            }

            // AI turn
            int AImove = nextStepAI(dor.getGrid());
            System.out.println("AI memilih: "+(AImove+1));
            dor.updateSlot(AImove, 'X');
            dor.printGrid();
            if(checkWin(dor.getGrid(), 'X')){
                System.out.println("AI Wins!, thank you for playing");
            }
        }
        in.close();
    }
}