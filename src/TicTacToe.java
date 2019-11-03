import java.util.*;

//1ST COMMIT FROM DESKTOP - PUSH TRIAL

public class TicTacToe {

    private char[][] board = new char[3][3];
    private char playerOneToken;
    private char playerTwoToken;

    //Constructor
    public TicTacToe() {

        //initialize board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                board[i][j] = '-';
            }//inner for
        }//outer for
    }

    private int getRowNumericValue(char row) {
        int r;
        switch (row) {
            case 'A':
                r = 0;
                break;
            case 'B':
                r = 1;
                break;
            case 'C':
                r = 2;
                break;
            default:
                r = 'I';
                System.out.println("Invalid input!");
        }
        return r;
    }

    public char getBoardSlot(char row, char col) {
        int i = getRowNumericValue(row);
        int j = Character.getNumericValue(col) - 1;
        return board[i][j];
    }

    public void setBoardSlot(char row, char col, char slot) {
        // A == '\u0041', B == '\u0042', C == '\u0043'
        int i = getRowNumericValue(row);
        int j = Character.getNumericValue(col) - 1;
        this.board[i][j] = slot;
    }

    public char getPlayerOneToken() {
        return playerOneToken;
    }

    public void setPlayerOneToken(char playerOneToken) {
        this.playerOneToken = playerOneToken;
    }

    public char getPlayerTwoToken() {
        return playerTwoToken;
    }

    public void setPlayerTwoToken(char playerTwoToken) {
        this.playerTwoToken = playerTwoToken;
    }


/* Board structure
      1   2   3
  A | X | - | - |
  B | X | - | - |
  C | O | - | - |
*/
    public void printBoard() {
        System.out.println("    1   2   3  ");
        System.out.println("A | " + board[0][0] + " | " + board[0][1] + " | " + board[0][2] + " |");
        System.out.println("B | " + board[1][0] + " | " + board[1][1] + " | " + board[1][2] + " |");
        System.out.println("C | " + board[2][0] + " | " + board[2][1] + " | " + board[2][2] + " |");
    }

    public char checkBoard() {
        char boardStatus;
        int emptySlotCount = 0;
        int fullSlotCount = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                if (board[i][j] == '-')
                    emptySlotCount++;
                else
                    fullSlotCount++;
            }//inner for
        }//outer for

        if (emptySlotCount == 9)
            boardStatus = 'E'; //EMPTY
        else if (fullSlotCount == 9)
            boardStatus = 'F'; //FULL
        else
            boardStatus = 'N'; //NEITHER EMPTY NOR FULL

        return boardStatus;
    }

    public char checkRow(char row) {
        char rowStatus;
        int r = getRowNumericValue(row);

        if ( (board[r][0]==board[r][1])&&(board[r][1] == board[r][2]) && (board[r][0] != '-') )
            rowStatus = 'F'; //FULL of tokens of the same type
        else
            rowStatus = 'N'; // N/A

        return rowStatus; //return row number or player token of the row who triggered the positive match?
    }

    public char checkColumn(int col) {
        char columnStatus;
        int c = col - 1;

        if ( (board[0][c]==board[1][c])&&(board[1][c] == board[2][c]) && (board[0][c] != '-') )
            columnStatus = 'F'; //FULL of tokens of the same type
        else
            columnStatus = 'N'; // N/A

        return columnStatus;
    }

    public char checkDiagonal(char diag) {
        char diagonalStatus;

        switch (diag) {
            case 'X':
                if ( (board[0][0]==board[1][1])&&(board[1][1] == board[2][2]) && (board[0][0] != '-') ) {
                    diagonalStatus = 'F'; //FULL of tokens of the same type
                } else {
                    diagonalStatus = 'N'; // N/A
                }
                break;
            case 'Y':
                if ( (board[0][2]==board[1][1])&&(board[1][1] == board[2][0]) && (board[0][2] != '-') ) {
                    diagonalStatus = 'F'; //FULL of tokens of the same type
                } else {
                    diagonalStatus = 'N'; // N/A
                }
                break;
            default:
                diagonalStatus = 'I';
                System.out.println("Invalid input!");
        }

        return diagonalStatus;
    }

}//TicTacToe Class


//running class
class PlayTicTacToe {
    public static void main(String[] args) {

        Scanner keyboardInput = new Scanner(System.in);
        TicTacToe game = new TicTacToe();
        boolean pCPU = true;
        boolean quit = false;
        Random random = new Random();
        int x,y; //random int for CPU coordinates
        char coordX, coordY; //random coordinates for CPU

        System.out.println("===Tic Tac Toe===");
        game.printBoard();
        System.out.println("=================");
        System.out.println("INSTRUCTIONS: select a slot to make a move (e.g., B2). Write QUIT to exit the game.");
        System.out.println("=================");

        String inputPlayers, numOfPlayers;
        System.out.print("Please select number of players (1/one or 2/two): ");
        for (;;) { //infinite loop
            inputPlayers = keyboardInput.nextLine();
            numOfPlayers = inputPlayers.trim().toUpperCase();
            if (numOfPlayers.toUpperCase().equals("QUIT")) {
                quit = true;
                break;
            } else if ( !( (numOfPlayers.equals("1")) || (numOfPlayers.equals("ONE")) || (numOfPlayers.equals("2")) || (numOfPlayers.equals("TWO")) ) ) {
                System.out.print("Invalid input! Please select a correct number of players (1/one or 2/two): ");
                //continue; not necessary (redundant)
            } else {

                if ( (numOfPlayers.equals("1")) || (numOfPlayers.equals("ONE")) ) {
                    pCPU = true;
                } else {
                    pCPU = false;
                }

                System.out.println("=================");
                System.out.println(numOfPlayers + " player(s) selected.");
                System.out.println("=================");
                break;
            }
        }//for

        System.out.print("Player 1, please choose your token (X or O): ");
        String inputOne = null;
        String inputTwo = null;
        char pOne = ' ';
        char pTwo = ' ';

        for (;;) { //infinite loop

            if (quit) {
                break;
            } else {
                inputOne = keyboardInput.nextLine();
                pOne = inputOne.trim().toUpperCase().charAt(0);
                if (inputOne.toUpperCase().equals("QUIT")) {
                    quit = true;
                    break;
                } else if (!((pOne == 'X') || (pOne == 'O'))) {
                    System.out.print("Invalid input! Please select a correct token (X or O): ");
                    //continue; not necessary (redundant)
                } else {
                    pTwo = pOne == 'X' ? 'O' : 'X';
                    game.setPlayerOneToken(pOne);
                    game.setPlayerTwoToken(pTwo);
                    break;
                }
            }
        }//for
        System.out.println("=================");
        if (pCPU) //One player mode selected
            System.out.println("P1 token: " + pOne + ", " + "CPU token: " + pTwo + ".");
        else
            System.out.println("P1 token: " + pOne + ", " + "P2 token: " + pTwo + ".");

        System.out.println("=================");
        System.out.println("GAME STARTED");
        System.out.println("=================");


        for (;;) { // main game loop

            //P1 turn
            System.out.print("P1 turn (select a slot): ");
            for (;;) { //infinite loop

                if (quit)
                    break;

                inputOne = keyboardInput.nextLine();
                inputOne = inputOne.trim().toUpperCase();

                if (inputOne.toUpperCase().equals("QUIT")) {
                    quit = true;
                    break;
                } else if (((inputOne.charAt(0) < 'A') || (inputOne.charAt(0) > 'C')) || ((inputOne.charAt(1) < '1') || (inputOne.charAt(1) > '3'))) {
                    System.out.print("Invalid input! Please select a correct slot: ");
                    //continue; not necessary (redundant)
                } else if (game.getBoardSlot(inputOne.charAt(0), inputOne.charAt(1)) != '-') {
                    System.out.print("Slot already filled! Please select a free slot: ");
                    //continue; not necessary (redundant)
                }
                else {
                    game.setBoardSlot(inputOne.charAt(0), inputOne.charAt(1), game.getPlayerOneToken());
                    game.printBoard();
                    System.out.println("=================");
                    break;
                }
            }//for P1


            //Check endgame conditions after each move (full board, tris or quit)
            if (quit) {
                break;
            } else if ( (game.checkRow('A')=='F')||(game.checkRow('B')=='F')||(game.checkRow('C')=='F')||(game.checkColumn(1)=='F')||(game.checkColumn(2)=='F')||(game.checkColumn(3)=='F')||(game.checkDiagonal('X')=='F')||(game.checkDiagonal('X')=='F') ) {
                //if a line gets filled after P1 turn, it means that P1 made the winning move
                System.out.println("=================");
                System.out.println("Player 1 wins!");
                System.out.println("=================");
                break;
            } else if (game.checkBoard() == 'F') {
                System.out.println("=================");
                System.out.println("It's a tie!");
                System.out.println("=================");
                break;
            } /*else {
                break; //to avoid an infinite loop -> //ERROR: unreachable statement (the main loop would always break and would be unable to execute the code below
            } */


            //P2 turn
            if (pCPU)
                System.out.print("CPU turn: ");
            else
                System.out.print("P2 turn (select a slot): ");

            for (;;) { //infinite loop

                if (quit)
                    break;

                if (pCPU) {
                    x = random.nextInt(3) + 1; //X: A, B or C
                    coordX = (char) (x + '@'); //character before A (@ == \u0040', A == '\u0041')
                    y = random.nextInt(3) + 1; //Y: 1, 2 or 3
                    coordY = (char) (y + '0');
                    inputTwo = Character.toString(coordX).concat(Character.toString(coordY));
                } else {
                    inputTwo = keyboardInput.nextLine();
                    inputTwo = inputTwo.trim().toUpperCase();
                }

                if (inputTwo.toUpperCase().equals("QUIT")) {
                    quit = true;
                    break;
                } else if (((inputTwo.charAt(0) < 'A') || (inputTwo.charAt(0) > 'C')) || ((inputTwo.charAt(1) < '1') || (inputTwo.charAt(1) > '3'))) {
                    if (!pCPU) //don't print message if CPU is playing. CPU does not select invalid inputs.
                        System.out.print("Invalid input! Please select a correct slot: ");
                    //continue; not necessary (redundant)
                } else if (game.getBoardSlot(inputTwo.charAt(0), inputTwo.charAt(1)) != '-') {
                    if (!pCPU) //don't print message if CPU is playing. CPU tries another slot on the background.
                        System.out.print("Slot already filled! Please select a free slot: ");
                    //continue; not necessary (redundant)
                } else {
                    System.out.println(inputTwo); // print CPU move.
                    game.setBoardSlot(inputTwo.charAt(0), inputTwo.charAt(1), game.getPlayerTwoToken());
                    game.printBoard();
                    System.out.println("=================");
                    break;
                }
            }//for P2


            //Check endgame conditions after each move (full board, tris or quit)
            if (quit) {
                break;
            } else if ( (game.checkRow('A')=='F')||(game.checkRow('B')=='F')||(game.checkRow('C')=='F')||(game.checkColumn(1)=='F')||(game.checkColumn(2)=='F')||(game.checkColumn(3)=='F')||(game.checkDiagonal('X')=='F')||(game.checkDiagonal('Y')=='F') ) {
                //if a line gets filled after P2 turn, it means that P2 made the winning move
                System.out.println("=================");
                if (pCPU)
                    System.out.println("CPU wins!");
                else
                    System.out.println("Player 2 wins!");
                System.out.println("=================");
                break;
            } else if (game.checkBoard() == 'F') {
                System.out.println("=================");
                System.out.println("It's a tie!");
                System.out.println("=================");
                break;
            } /* else {
                break; //to avoid an infinite loop -> //ERROR: unreachable statement
            } */

        }//for

        if (quit) {
            System.out.println("\n*****************");
            System.out.println("Exit game.");
            System.out.println("*****************");
        } else {
            System.out.println("=================");
            System.out.println("GAME ENDED");
            System.out.println("=================");
        }

    }//psvm
}//running class