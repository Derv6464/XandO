import java.util.Dictionary;
import java.util.Hashtable;
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

        while(!checkWin(arr,turn)){
            turn = makeMove(turn,arr);
        }
        updateGrid(arr);
        if(!turn){
            System.out.println("Player 1 wins");
        }
        else{
            System.out.println("Player 2 wins");
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
        System.out.println("A   "+ arr[0][0] +" | "+ arr[0][1] +" | "+ arr[0][2] +" ");
        System.out.println("   ------------");
        System.out.println("B   "+ arr[1][0] +" | "+ arr[1][1] +" | "+ arr[1][2] +" ");
        System.out.println("   ------------");
        System.out.println("C   "+ arr[2][0] +" | "+ arr[2][1] +" | "+ arr[2][2] +" ");
        System.out.println("    1   2   3");
       // System.out.println("");

    }
    static boolean makeMove(boolean turn, String[][] arr){
        Dictionary<String,Integer> codes = new Hashtable<String,Integer>();
        codes.put("A",0);
        codes.put("B",1);
        codes.put("C",2);
        codes.put("1",0);
        codes.put("2",1);
        codes.put("3",2);

        System.out.println("Select a square:");
        Scanner in = new Scanner(System.in);
        updateGrid(arr);
        String input = in.nextLine();
        while (input.length() > 2){
            System.out.println("Your input is not valid, only enter two characters");
            System.out.println("Select a square");
            input = in.nextLine();
        }
        char row = input.charAt(0);
        if (row != 'A' && row != 'B' && row != 'C'){
            System.out.println("Invalid Entry");
            makeMove(turn, arr);
        }
        char column = input.charAt(1);
        if (column != '1' && column != '2' && column != '3'){
            System.out.println("Invalid Entry");
            makeMove(turn, arr);
        }  
        String rowString = String.valueOf(row);
        String columnString = String.valueOf(column);          
        int rowIn = codes.get(rowString); 
        int columnIn = codes.get(columnString); 
        turn = insert(rowIn,columnIn,turn,arr);
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
        }else
        return false;
        }
    }


