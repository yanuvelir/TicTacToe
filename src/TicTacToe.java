import java.util.Scanner;

public class TicTacToe {

    public static final String EMPTY = "   ", CROSS = " X ", ZERO = " 0 ";
    public static String activePlayer;
    public static final int ROWS = 3, COLUMNS = 3;
    public static String[][] mesh = new String[ROWS][COLUMNS];
    public static int gameStatus;
    public static final int STATUS_GAME_GO_ON = 0, STATUS_DEAD_HEAT = 1, STATUS_WIN_X = 3, STATUS_WIN_0 = 4;
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        startGame();
        do{
            retrieveGamersInput();
            analyzeMesh();
            showTheMesh();
            if (gameStatus == STATUS_WIN_X){
                System.out.println("Congratulations!!! the WINNER is *X* ");
            } else if (gameStatus == STATUS_WIN_0){
                System.out.println("Congratulations!!! the WINNER is *0* ");
            } else if (gameStatus == STATUS_DEAD_HEAT){
                System.out.println("The game is finished with DEAD HEAT result!");
            }
            activePlayer = (activePlayer == CROSS ? ZERO : CROSS);
        }
        while (gameStatus == STATUS_GAME_GO_ON);

    }

    public static void startGame() {
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                mesh[row][column] = EMPTY;
            }
        }
        activePlayer = CROSS;
        showTheMesh();

    }

    public static void retrieveGamersInput() {
        boolean inputTrue = false;
        do {
            System.out.println("Player '" + activePlayer + "' , input a row (1-3) and column (1-3)");
            int row = sc.nextInt() - 1;
            int column = sc.nextInt() - 1;
            if (row >= 0 && row < ROWS && column >= 0 && column < COLUMNS && mesh[row][column] == EMPTY) {
                mesh[row][column] = activePlayer;
                inputTrue = true;
            } else {
                System.out.println("Selected point (" + (column + 1) + "," + (column + 1) +
                        ") it could not used. Try again...");
            }
        }
        while (!inputTrue);
    }

    public static void analyzeMesh() {

        String winner = findTheWinner();
        if (winner.equals(CROSS)){
            gameStatus = STATUS_WIN_X;
        } else if (winner.equals(ZERO)){
            gameStatus = STATUS_WIN_0;
        } else  if (meshIsFilledIn()){
            gameStatus = STATUS_DEAD_HEAT;
        } else {
            gameStatus = STATUS_GAME_GO_ON;
        }
    }

    public static boolean meshIsFilledIn(){

        for (int row = 0; row < ROWS; row++){
            for (int column = 0; column < COLUMNS; column++){
                if (mesh[row][column]==EMPTY){
                    return false;
                }
            }
        }
        return true;
    }

    public static String findTheWinner() {
        int numberOfMatches;

        // Check rows
        for (int row = 0; row < ROWS; row++) {
            numberOfMatches = 0;
            for (int column = 0; column < COLUMNS; column++) {
                if (!mesh[row][column].equals(EMPTY) && mesh[row][column].equals(mesh[row][0])) {
                    numberOfMatches++;
                }
            }
            if (numberOfMatches == 3) {
                return mesh[row][0];
            }
        }

        // Check columns
        for (int column = 0; column < COLUMNS; column++) {
            numberOfMatches = 0;
            for (int row = 0; row < ROWS; row++) {
                if (!mesh[row][column].equals(EMPTY) && mesh[row][column].equals(mesh[0][column])) {
                    numberOfMatches++;
                }
            }
            if (numberOfMatches == 3) {
                return mesh[0][column];
            }
        }

        // Check diagonals
        if (!mesh[0][0].equals(EMPTY) && mesh[0][0].equals(mesh[1][1]) && mesh[0][0].equals(mesh[2][2])) {
            return mesh[0][0];
        }
        if (!mesh[0][2].equals(EMPTY) && mesh[1][1].equals(mesh[0][2]) && mesh[2][0].equals(mesh[0][2])) {
            return mesh[0][2];
        }

        return EMPTY; // No winner found
    }

//    public static String findTheWinner() {
//        int numberOfMatches;
//        for (int row = 0; row < ROWS; row++) {
//            numberOfMatches = 0;
//            for (int column = 0; column < COLUMNS; column++) {
//                if (mesh[row][0] != EMPTY && mesh[row][0].equals(mesh[row][column])) {
//                    numberOfMatches++;
//                }
//            }
//            if (numberOfMatches == 3) {
//                return mesh[row][0];
//            }
//        }

        // 3 in column
//        for (int column = 0; column < COLUMNS; column++){
//            numberOfMatches = 0;
//            for(int row = 0; row < ROWS; row++){
//                if(mesh[0][column] != EMPTY && mesh[0][column].equals(mesh[row][column])){
//                    numberOfMatches++;
//                }
//            }
//            if (numberOfMatches == 3){
//                return mesh[0][column];
//            }
//        }
//
//        if (mesh[0][0] != EMPTY && mesh[0][0] == mesh[1][1] && mesh [0][0] == mesh[2][2]){
//            return mesh[0][0];
//        }
//        if (mesh[0][2] != EMPTY && mesh[1][1] == mesh[0][2] && mesh [2][0] == mesh[2][2]){
//            return mesh[0][2];
//        }
//
//        return EMPTY; // Add a default return value in case no winner is found
//    }



    public static void showTheMesh() {
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                System.out.print(mesh[row][column]);
                if (column != COLUMNS - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (row != ROWS - 1) {
                for (int i = 0; i < COLUMNS; i++) {
                    System.out.print("___");
                    if (i != COLUMNS - 1) {
                        System.out.print("|");
                    }
                }
                System.out.println();
            }
        }
        System.out.println();
    }


}

//    public static void showTheMesh(){
//
//        for (int row = 0; row < ROWS; row++ ){
//            for (int column = 0; column < COLUMNS; column++){
//                System.out.print(mesh[row][column]);
//                if (column != COLUMNS-1){
//                    System.out.print("|");
//                }
//            }
//            if (row != ROWS-1){
//                System.out.println("__________");
//            }
//        }
//        System.out.println();
//    }
