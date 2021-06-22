class Game{
    private char[][] grid = {{'1','2','3'},{'4','5','6'},{'7','8','9'}};;
    
    Game(){
        //initialize array list into 3x3 grid for tic tac toe-ing
    }

    public void printGrid(){
        //hardcoded bc static lmao
        System.out.println("┏━━━┳━━━┳━━━┓");
        System.out.printf("┃ %c ┃ %c ┃ %c ┃%n",grid[0][0],grid[0][1],grid[0][2]);
        System.out.println("┣━━━╋━━━╋━━━┫");
        System.out.printf("┃ %c ┃ %c ┃ %c ┃%n",grid[1][0],grid[1][1],grid[1][2]);
        System.out.println("┣━━━╋━━━╋━━━┫");
        System.out.printf("┃ %c ┃ %c ┃ %c ┃%n",grid[2][0],grid[2][1],grid[2][2]);
        System.out.println("┗━━━┻━━━┻━━━┛");
    }
    public static void main(String[] args) {
        Game dor = new Game();
        dor.printGrid();
    }
}