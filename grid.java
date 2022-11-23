import java.util.Scanner;
public class grid {

    public static void main(String[] args){
        boolean turn = true;
        String[][] arr = new String[3][3];
        for(int i = 0;i<arr.length;i++){
            for(int j = 0;j<arr.length;j++){
                arr[i][j] = " ";
            }
        }

        while(true){
            if(checkWin(arr,turn)){
                if(turn == true){
                    System.out.println("Player 1 wins");
                }
                else{
                    System.out.println("Player 2 wins");
                }
            }
            turn = makeMove(turn,arr);
        }
        
    }

    static boolean insert(int x, int y,boolean turn,String[][] arr){
        if(turn == true){
            arr[x][y] = "X";            
        }
        else{
            arr[x][y] = "O";           
        }
        turn = !turn;
        return turn;
    }

    static void updateGrid(String[][] arr){
        System.out.println(" "+ arr[0][0] +" | "+ arr[0][1] +" | "+ arr[0][2] +" ");
        System.out.println("------------");
        System.out.println(" "+ arr[1][0] +" | "+ arr[1][1] +" | "+ arr[1][2] +" ");
        System.out.println("------------");
        System.out.println(" "+ arr[2][0] +" | "+ arr[2][1] +" | "+ arr[2][2] +" ");

    }
    static boolean makeMove(boolean turn, String[][] arr){
        java.util.Scanner in = new Scanner(System.in);
        updateGrid(arr);
        System.out.println("Select a row");
        int row = in.nextInt();
        System.out.println("Select a column");
        int column = in.nextInt();
        turn = insert(row,column,turn,arr);
        return turn;
    }
    static boolean checkWin(String[][] arr,boolean turn){
        String s;
        if(!turn){
            s = "X";
        }else{
            s = "O";
        }

        if (checkDiagnol(arr, s)||checkHorizontal(arr, s)||checkVertical(arr, s)){
            return true;
        }else{
            return false;
        }
    }

    static boolean checkHorizontal(String[][] arr,String s){
        for(int row = 0;row<arr.length;row++){
            if((arr[row][0] == s) && (arr[row][1] == s) && (arr[row][2] == s)){
                return true;
            }
        }
        return false;
    }

    static boolean checkVertical(String[][] arr,String s){
        for(int column = 0;column<arr.length;column++){
            if((arr[0][column] == s) && (arr[1][column] == s) && (arr[2][column] == s)){
                return true;
            }
        }
        return false;
    }

    static boolean checkDiagnol(String[][] arr,String s){
        if((arr[0][0] == s) && (arr[1][1] == s) && (arr[2][2] == s)){
            return true;
        }else if((arr[0][2] == s) && (arr[1][1] == s) && (arr[2][0] == s)){
            return true;
        }else{
        return false;
        }
    }

}
