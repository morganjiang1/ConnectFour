import java.util.Scanner;

public class ConnectFour {
    
    private String[][] board;
    private static String playerTurn;

    public ConnectFour()
    {
        board = new String[7][7];
        playerTurn = "A";
    }

    public void start()
    {
        System.out.println("Welcome to Connect Four!");
        display();
        move();
    }
    
    public void display()
    {
        int count = 1;
        for (int row = 0; row < board.length; row++)
        {
            for (int col = 0; col < board[0].length; col++)
            {
                if (col == 0)
                {
                System.out.print("|");
                }
                if (row == 0)
                {
                    board[row][col] = String.valueOf(count);
                    System.out.print(board[row][col] + "|");
                    count++;
                }
                else
                {
                board[row][col] = "_";
                System.out.print(board[row][col] + "|");
                }
            }
            System.out.println("");
        }
    }

    public void move()
    {
        if (!boardFull())
        {
            int input = getUserInput();
            while (!validInput(input))
            {
                input = getUserInput();
            }
            int row = board.length-1;
            while (!board[row][input-1].equals("_") &&  row > 1)
            {
                row--;
            }
            if (board[row][input-1].equals("A") || board[row][input-1].equals("B"))
            {
                System.out.println("row is already full!");
            }
            else
            {
            board[row][input-1] = playerTurn;
            updateGame();
            switchTurn();
            }
            move();
        }
            else
            {
                System.out.println("Game Over! It ends in a draw.");
            }
    }

    public int getUserInput()
    {
            Scanner userInput = new Scanner(System.in);
            System.out.print("Player " + playerTurn + ": Enter which column to place your chip: ");
            int input = userInput.nextInt();
            return input;
    }

    public void switchTurn()
    {
        if (checkWin(playerTurn))
        {
            endGame();
        }
        if (playerTurn.equals("A"))
        {
            playerTurn = "B";
        }
        else
        {
            playerTurn = "A";
        }
    }

    public void endGame()
    {
        System.out.println("Game Over. Player " + playerTurn + " wins!");
        System. exit(0);
    }

    public boolean validInput(int input)
    {
        if (String.valueOf(input) == null)
        {
            return false;
        }
        int[] validNums = new int[]{1,2,3,4,5,6,7};
        for (int i = 0; i < validNums.length; i++)
        {
            if (validNums[i] == input)
            {
                return true;
            }
        }
        return false;
    }

    public boolean boardFull()
    {
        for (int row = 1; row < board.length; row++)
        {
            for (int col = 0; col < board[0].length; col++)
            {
                if (!board[row][col].equals("A") && !board[row][col].equals("B"))
                {
                    return false;
                }
            }
        }
        return true;
    }

    public void updateGame()
    {
        for (int row = 0; row < board.length; row++)
        {
            for (int col = 0; col < board[0].length; col++)
            {
                if (col == 0)
                {
                    System.out.print("|");
                }
                System.out.print(board[row][col] + "|");
            }
            System.out.println("");
        }
    }

    public boolean checkWin(String turn)
    {
        for (int row = 0; row < board.length; row++)
        {
            for (int col = 0; col < board[0].length; col++)
            {
                if (checkUpAndDown(col, turn) || checkLeftAndRight(row, turn))
                {
                    return true;
                }
            }
        }
        if (checkLeftDiagonal(turn) || checkRightDiagonal(turn))
        {
            return true;
        }
        return false;
    }

    public boolean checkUpAndDown(int col, String turn)
    {
        for (int outer = 1; outer < board.length-3; outer++)
        {
            boolean fourInRow = true;
            for (int inside = outer; inside < outer+4 ; inside++)
            {
                if (!board[inside][col].equals(turn))
                {
                    fourInRow = false;
                }
            }
            if (fourInRow)
            {
                return true;
            }
        }
        return false;
    }

    public boolean checkLeftAndRight(int row, String turn)
    {
        for (int outer = 0; outer < board[0].length-3; outer++)
        {
            boolean fourInRow = true;
            for (int inside = outer; inside < outer+4 ; inside++)
            {
                if (!board[row][inside].equals(turn))
                {
                    fourInRow = false;
                }
            }
            if (fourInRow)
            {
                return true;
            }
        }
        return false;
    }

    public boolean checkLeftDiagonal(String turn)
    {
        for (int row = 4; row < board.length; row++)
        {
            for (int col = 0; col < board[0].length - 3; col++)
            {
                if (board[row][col].equals(turn) && board[row-1][col+1].equals(turn) && board[row-2][col+2].equals(turn) && board[row-3][col+3].equals(turn) )
                {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkRightDiagonal(String turn)
    {
        for (int row = 1; row < board.length-3; row++)
        {
            for (int col = 0; col < board[0].length - 3; col++)
            {
                if (board[row][col].equals(turn) && board[row+1][col+1].equals(turn) && board[row+2][col+2].equals(turn) && board[row+3][col+3].equals(turn) )
                {
                    return true;
                }
            }
        }
        return false;
    }

}