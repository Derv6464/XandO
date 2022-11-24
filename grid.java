import java.util.Scanner;
public class grid {

    public static void main(String[] args){
        boolean turn = true;
        int p1Score = 0;
        int p2Score = 0;
        String[][] arr = new String[3][3];
        for(int i = 0;i<arr.length;i++){
            for(int j = 0;j<arr.length;j++){
                arr[i][j] = " ";
            }
        }
        boolean play = true;

        while(play){
            arr = resetBoard(arr);
            
            while((!checkWin(arr,turn))&&(!checkDraw(arr))) {
            System.out.println(showTurns(turn)); 
               turn = makeMove(turn,arr);
            }
            updateGrid(arr);
            if(!checkWin(arr,turn)){
                System.out.println("The game ended in a draw");
         }
            else if(!turn){
                System.out.println("Player 1 wins");
                p1Score = scoreCounter(true,p1Score,p2Score)[0];

         }
         else{
                System.out.println("Player 2 wins");
                p2Score = scoreCounter(false,p1Score,p2Score)[1];
            }
            System.out.println("Player 1's score is: "+p1Score);
            System.out.println("Player 2's score is: "+p2Score);
            System.out.println("Do you want to play again? [y/n]");
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            if (input.equals("y")){
                play = true;
                turn = !turn;
            }else{
                play = false;
                System.out.println("Final Score:");
                System.out.println("Player 1's score is: "+p1Score);
                System.out.println("Player 2's score is: "+p2Score);
            }

        }
        
    }

    static boolean insert(int x, int y,boolean turn,String[][] arr){
        if(turn){
            arr[x][y] = "X";            
        }
        else{
            
            arr[x][y] = "O";           
        }
        turn = !turn;
        return turn;
    }
    static String showTurns(boolean turns){
        if(turns){
            return "It's your turn player 1 (X)"; 
        }
        else{
            return "It's your turn player 2 (O)"; 
        }
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
        System.out.println("Select a square");
        Scanner in = new Scanner(System.in);
        updateGrid(arr);
        String input = in.nextLine();

        while (input.length() > 2){
            System.out.println("Your input is not valid, only enter two characters");
            System.out.println("Select a square");
            input = in.nextLine();
        }
        
        char row = input.charAt(0);
        char column = input.charAt(1);
       // String rowString = String.valueOf(row);
        //String columnString = String.valueOf(column);

        while (checkInvalid(row, column)){
            input = in.nextLine();
            row = input.charAt(0);
            column = input.charAt(1);
        }
        int rowIn = convetToNum(row, column)[0];
        int columnIn = convetToNum(row, column)[1];
        System.out.println(rowIn);
        System.out.println(columnIn);

        
        if (arr[rowIn][columnIn] == " "){
            turn = insert(rowIn,columnIn,turn,arr);
        }else{
            System.out.println("You cannot pick a space already occupied");
        }
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

    static boolean checkDraw(String[][] arr){
        for(int i = 0;i<arr.length;i++){
            for(int j = 0;j<arr.length;j++){
                if (arr[i][j] == " "){
                    return false;
                }
            }
        }return true;
    }


    static int[] scoreCounter(boolean player,int p1Score,int p2Score){
        if (player){
            p1Score = p1Score +1;
        }else{
            p2Score = p2Score +1;
        }

        return new int[] {p1Score,p2Score};
    }

    static String[][] resetBoard(String [][] arr){
        for(int i = 0;i<arr.length;i++){
            for(int j = 0;j<arr.length;j++){
                arr[i][j] = " ";
            }
        }
        return arr;
    }

    static int[] convetToNum(char row, char colunm){
        int rowNum;
        int colunmNum;
        if (row =='A' || row == 'a'){
            rowNum = 0;
        }else if (row == 'B' || row == 'b'){
            rowNum = 1;
        }else   {
                rowNum = 2;
        }
        if (colunm == '1' ){
            colunmNum = 0;
        }else if (colunm == '2'){
            colunmNum = 1;
        }else {
                colunmNum = 2;
        }

        return new int[] {rowNum,colunmNum};
    
    
    }
    static boolean checkInvalid(char row,char column){
        if (row != 'A' && row != 'B' && row != 'C' && row != 'a' && row != 'b' && row != 'c'){
            System.out.println("Invalid Entry");
            return true;
        }
        if (column != '1' && column != '2' && column != '3'){
            System.out.println("Invalid Entry");
            return true;
        }  
        return false;
    }
}
