class Game{
    private char[] grid = {'1','2','3','4','5','6','7','8','9'};
    
    Game(){
        //initialize array list into 3x3 grid for tic tac toe-ing
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
    public static void main(String[] args) {
        Game dor = new Game();
        dor.printGrid();
    }
}