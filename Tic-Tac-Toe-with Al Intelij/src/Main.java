package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[][] board = new char[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }

        makeTheStart(board);

        System.out.println("---------");
        System.out.println("|" + " " + board[0][0] + " " + board[0][1] + " " + board[0][2] + " " + "|");
        System.out.println("|" + " " + board[1][0] + " " + board[1][1] + " " + board[1][2] + " " + "|");
        System.out.println("|" + " " + board[2][0] + " " + board[2][1] + " " + board[2][2] + " " + "|");
        System.out.println("---------");


        boolean yourBoolean = true;

        int counter = counterForO(board) + counterForX(board);
        boolean yourSecondBoolean = true;


        while (yourBoolean) {
            do {


                System.out.print("Enter the coordinates: ");
                String firstAndSecond = scanner.nextLine();
                String[] parts = firstAndSecond.split("");
                String first = parts[0];
                String second = parts[2];


                char characterXo = counterXAndO(counter);

                boolean itIsANumber = itIsANumber(first, second);
                boolean oneToThree = oneToThree(first, second);
                boolean occupiedVar = occupiedSeat(board, first, second, oneToThree);

                if (!itIsANumber) {
                    System.out.println("You should enter numbers!");
                } else if (!oneToThree) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (occupiedVar) {
                    System.out.println("This cell is occupied! Choose another one!");
                } else if (!occupiedVar) {

                    int one = Integer.parseInt(first);
                    int two = Integer.parseInt(second);

                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (board[i][j] == board[one - 1][two - 1]) {
                                board[one - 1][two - 1] = characterXo;

                                break;
                            }
                        }
                    }

                    counter++;
                }
                if (!itIsANumber || !oneToThree || occupiedVar) {
                    continue;
                } else {
                    System.out.println("---------");
                    System.out.println("|" + " " + board[0][0] + " " + board[0][1] + " " + board[0][2] + " " + "|");
                    System.out.println("|" + " " + board[1][0] + " " + board[1][1] + " " + board[1][2] + " " + "|");
                    System.out.println("|" + " " + board[2][0] + " " + board[2][1] + " " + board[2][2] + " " + "|");
                    System.out.println("---------");
                    yourSecondBoolean = false;
                }


            } while (yourSecondBoolean);


            boolean oWinsVar = oWins(board);
            boolean xWinsVar = xWins(board);
            boolean drawOAndX = draw(board);


            if (xWinsVar) {
                System.out.print("X wins");
                yourBoolean = false;
            } else if (oWinsVar) {
                System.out.print("O wins");
                yourBoolean = false;
            } else if (drawOAndX) {
                System.out.print("Draw");
                yourBoolean = false;
            } else {
                System.out.print("Game not finished");
                yourBoolean = false;
            }
        }
    }

// 1. Method it takes the board, and you give 9 numbers with the scanner
    public static char[][] makeTheStart(char[][] board) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the cells: ");
        String oneLetter = scanner.nextLine();

        char[] board2 = oneLetter.toCharArray();
        int k = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = board2[k++];
            }
        }


        return board;
    }

// 2. Method it gives if it is a draw
    public static boolean draw(char[][] board) {
        boolean drawBoolean = false;
        int counterX = counterForX(board);
        int counterO = counterForO(board);
        boolean booleanX = xWins(board);
        boolean booleanO = oWins(board);

        if (counterO + counterX == 9 && !booleanX && !booleanO) {
            drawBoolean = true;
        }

        return drawBoolean;
    }

    // 3. Method that counter the X in the board 2d array
    public static int counterForX(char[][] board) {
        int count = 0;
        for (char[] chars : board) {
            for (char aChar : chars)
                if (aChar == 'X') {
                    count++;
                }
        }
        return count;
    }

    // 4. Method that counter the O in the board 2d array
    public static int counterForO(char[][] board) {
        int count = 0;
        for (char[] chars : board) {
            for (char aChar : chars)
                if (aChar == 'O') {
                    count++;
                }
        }
        return count;
    }

    // 5. Method that concludes if player with X wins

    public static boolean xWins(char[][] board) {
        boolean booleanLogicX = false;

        if (board[0][0] == 'X' && board[0][1] == 'X' && board[0][2] == 'X') {
            booleanLogicX = true;
        } else if (board[1][0] == 'X' && board[1][1] == 'X' && board[1][2] == 'X') {
            booleanLogicX = true;
        } else if (board[2][0] == 'X' && board[2][1] == 'X' && board[2][2] == 'X') {
            booleanLogicX = true;
        } else if (board[0][0] == 'X' && board[1][0] == 'X' && board[2][0] == 'X') {
            booleanLogicX = true;
        } else if (board[0][1] == 'X' && board[1][1] == 'X' && board[2][1] == 'X') {
            booleanLogicX = true;
        } else if (board[0][2] == 'X' && board[1][2] == 'X' && board[2][2] == 'X') {
            booleanLogicX = true;
        } else if (board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == 'X') {
            booleanLogicX = true;
        } else if (board[0][2] == 'X' && board[1][1] == 'X' && board[2][0] == 'X') {
            booleanLogicX = true;
        }
        return booleanLogicX;
    }

    // 6. Method that concludes if player with O wins
    public static boolean oWins(char[][] board) {
        boolean booleanLogicO = false;

        if (board[0][0] == 'O' && board[0][1] == 'O' && board[0][2] == 'O') {
            booleanLogicO = true;
        } else if (board[1][0] == 'O' && board[1][1] == 'O' && board[1][2] == 'O') {
            booleanLogicO = true;
        } else if (board[2][0] == 'O' && board[2][1] == 'O' && board[2][2] == 'O') {
            booleanLogicO = true;
        } else if (board[0][0] == 'O' && board[1][0] == 'O' && board[2][0] == 'O') {
            booleanLogicO = true;
        } else if (board[0][1] == 'O' && board[1][1] == 'O' && board[2][1] == 'O') {
            booleanLogicO = true;
        } else if (board[0][2] == 'O' && board[1][2] == 'O' && board[2][2] == 'O') {
            booleanLogicO = true;
        } else if (board[0][0] == 'O' && board[1][1] == 'O' && board[2][2] == 'O') {
            booleanLogicO = true;
        } else if (board[0][2] == 'O' && board[1][1] == 'O' && board[2][0] == 'O') {
            booleanLogicO = true;
        }
        return booleanLogicO;
    }

// 7. With the help of counter and while  it takes the decision which person will play
    public static char counterXAndO(int counter) {
        char characterXo = '\u0000';


        if (counter == 0) {
            characterXo = 'X';
        } else if (counter == 1) {
            characterXo = 'O';
        } else if (counter == 2) {
            characterXo = 'X';
        } else if (counter == 3) {
            characterXo = 'O';
        } else if (counter == 4) {
            characterXo = 'X';
        } else if (counter == 5) {
            characterXo = 'O';
        } else if (counter == 6) {
            characterXo = 'X';
        } else if (counter == 7) {
            characterXo = 'O';
        } else if (counter == 8) {
            characterXo = 'X';

        }
        return characterXo;
    }


    // 8. Method understands if you don't give numbers for input
    public static boolean itIsANumber(String first, String second) {
        boolean var = false;

        try {

            if (first.matches("\\d") || second.matches("\\d")) {
                var = true;
            }
        } catch (NumberFormatException e) {
            var = false;
        }
        return var;
    }

    // 9. Method for coordinates should be from 1 to 3!
    public static boolean oneToThree(String first1, String second1) {
        boolean oneToThree = false;

        try {
            int first = Integer.parseInt(first1);
            int second = Integer.parseInt(second1);
            if (first >= 1 && first <= 3 && second >= 1 && second <= 3) {
                oneToThree = true;
            }
        } catch (NumberFormatException e) {
            oneToThree = false;

        }
        return oneToThree;
    }

    // 10. Method to see if a seat is occupied
    public static boolean occupiedSeat(char[][] board, String first1, String second1, boolean oneToThree) {
        boolean occupied = false;

        if (oneToThree) {
            int first = Integer.parseInt(first1);
            int second = Integer.parseInt(second1);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == board[first - 1][second - 1] && (board[i][j] == 'X' || board[i][j] == 'O')) {

                        occupied = true;
                        break;
                    }
                }
            }
        }
        return occupied;
    }
}
