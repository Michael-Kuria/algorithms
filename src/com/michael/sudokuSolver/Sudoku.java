package com.michael.sudokuSolver;

/**
 * The algorithm presented will test each and number 1 - 9 on the empty slots until a fit is found
 */
public class Sudoku {

    /**
     * Goes through the entire sudoku row by row to find an empty cell.
     * @param sudoku
     * @return empty cell
     */
    public static int[] getEmptyCell(int[][] sudoku){
        for(int i = 0; i < sudoku.length; i ++){
            for(int j = 0; j < sudoku.length; j ++){
                if(sudoku[i][j] == 0){
                    return new int[] {i,j};
                }
            }
        }
        return new int[] {-1,-1};
    }

    /**
     * SUDOKU RULES
     * --------------
     * There should be 1 - 9 in a :
         * 1) row.
         * 2) column.
         * 3) 3 X 3 grid
     * Check the row, column, the cell grid (3 X 3) if num is a fit
     * @param sudoku
     * @param row
     * @param col
     * @param num
     * @return
     */
    public static boolean isValid(int[][] sudoku , int row, int col, int num){
        for(int i = 0; i < sudoku.length ; i ++){
            if(sudoku[row][i] == num)
                return  false;
        }
        //check the column
        for(int i = 0; i < sudoku.length; i ++){
            if(sudoku[i][col] == num)
                return false;
        }

        //check the 3 x 3 grid
        for(int i = 0; i < sudoku.length; i ++){

            int gridX = row/3 * 3;
            int gridY = col/3 * 3;

            for(int m = gridX; m < gridX + 3; m ++){
                for(int n = gridY; n < gridY + 3; n ++){
                    if(sudoku[m][n] == num)
                        return false;
                }
            }

        }

        return true;
    }


    /**
     * Solves the sudoku and backtracks if there were mistakes with our fits
     * @param sudoku
     * @return true if the sudoku is solved and false otherwise
     */
    public static boolean solveSudoku(int[][] sudoku){

        int[] emptyCell = getEmptyCell(sudoku);
        int row = emptyCell[0];
        int col = emptyCell[1];

        if(row == -1) {
            return true;
        }

        for(int k= 1; k <= 9; k ++){
            if(isValid(sudoku,row,col,k)){
                sudoku[row][col] = k;
                if(solveSudoku(sudoku))
                    return true;
               /* printSudoku(sudoku);
                System.out.println();
                System.out.println();*/

                sudoku[row][col] = 0;
            }



        }
        return false;

    }

    /**
     * prints the given sudoku in a .pretty() way showing the 3 X 3 grids
     * @param sudoku
     */
    public static void printSudoku(int[][] sudoku){
        for(int i = 0; i < sudoku.length; i ++){
            if((i ) % 3 == 0){
                System.out.println("  -----------------------------------");
            }
            for(int j = 0; j < sudoku.length; j ++){
                if((j) % 3 == 0){
                    System.out.print(" |  ");
                }
                System.out.print(sudoku[i][j] + "  ");

            }
            System.out.println("");
        }
    }



    public static void main(String [] args){
        int[][] sudoku = {
                {0,0,3,0,4,0,8,0,1},
                {4,0,5,6,0,0,0,0,9},
                {1,0,0,7,0,8,0,3,0},
                {5,0,0,0,6,1,0,0,0},
                {2,0,0,3,0,0,0,0,0},
                {0,0,6,0,0,7,1,0,3},
                {9,0,0,5,0,2,7,0,0},
                {0,0,2,0,0,0,0,0,8},
                {0,0,7,0,0,0,4,0,0}

        };

        int[][] sudoku2 ={
                {9,0,0,0,1,6,0,3,2},
                {0,4,0,0,0,0,0,0,0},
                {0,0,0,7,0,3,0,0,0},
                {4,0,0,0,9,8,0,6,5},
                {2,0,0,0,0,0,0,0,1},
                {8,1,0,3,2,0,0,0,9},
                {0,0,0,5,0,1,0,0,0},
                {0,0,0,0,0,0,0,2,0},
                {3,7,0,9,4,0,0,0,6}

        };
        System.out.println("Unsolved Sudoku.....");
        printSudoku(sudoku);
        solveSudoku(sudoku);
        System.out.println("Solved sudoku ........");
        printSudoku(sudoku);

    }
}
