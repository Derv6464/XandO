import java.util.Scanner;
import java.util.Random;
public class grid{

    static int rowIn;
    static int columnIn;
    static int go;
    static String[][] arrX = new String[3][3];
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

        for(int i = 0;i<arrX.length;i++){
            for(int j = 0;j<arrX.length;j++){
                arr[i][j] = " ";
            }
        }
        boolean play = true;
        boolean single = false;

        while(play){
            go = 0;
            Scanner in = new Scanner(System.in);
            System.out.println("Do you want to play single player [1] or two player [2]");
            int input = in.nextInt();
            if(input == 1){
                single = true;
            }
            arr = resetBoard(arr);
            
            while((!checkWin(arr,turn,single))&&(!checkDraw(arr))) {
                System.out.println(showTurns(turn)); 
                turn = makeMove(turn,arr,single);
                go = go + 1;
            }
            updateGrid(arr);
            if(!checkWin(arr,turn,single)){
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
            Scanner in2 = new Scanner(System.in);
            String input2 = in2.nextLine();
            if (input2.equals("n")){
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
            arrX[x][y] = "X";        
        }else{
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

    static boolean makeMove(boolean turn, String[][] arr, boolean single){
        if(single && !turn){
            turn = singles(arr, rowIn, columnIn, go, turn);
        }else{
            turn = playerGo(turn, arr);
        }

        return turn;
        
    
    }
    static boolean playerGo(boolean turn, String[][] arr){
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

        while (checkInvalid(row, column)){
            input = in.nextLine();
            row = input.charAt(0);
            column = input.charAt(1);
        }
        int rowIn = convetToNum(row, column)[0];
        int columnIn = convetToNum(row, column)[1];
        
        if (arr[rowIn][columnIn] == " "){
            turn = insert(rowIn,columnIn,turn,arr);
        }else{
            System.out.println("You cannot pick a space already occupied");
        }
    
        return turn;
    }
    static boolean checkWin(String[][] arr,boolean turn, boolean single ){
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

    static boolean singles(String[][] arr,int rowIn,int columnIn, int go, boolean turn){
        Random rand = new Random();
        int rowOut;
        int columnOut;
        System.out.println("go" + go);
        if(go < 2){
            if(arr[1][1] != " "){
                rowOut = rand.nextInt(3);
                if(rowOut == 0 || rowOut == 2){
                    columnOut = 1;
                }else{
                    columnOut = rand.nextInt(3);
                    while(columnOut == 1){
                        columnOut = rand.nextInt(3);
                    }
                turn = insert(rowOut, columnOut, turn, arr);
                }
            }else{
                rowOut = rand.nextInt(3);
                columnOut = rand.nextInt(3);
                while(rowIn == rowOut && columnIn == columnOut){
                    rowOut = rand.nextInt(3);
                    columnOut = rand.nextInt(3);
                }
                if(rowOut == 1){
                    while(columnOut == 1){
                        columnOut = rand.nextInt(3);
                    }
                }
                turn = insert(rowOut, columnOut, turn, arr);
        
            }
        }else{
            turn = makeBlock(arr,go,turn);
    
    //}
        }
        return turn;
    }

    


    static boolean makeBlock(String[][] arr, int go, boolean turn){
            Random rand = new Random();
            int rowOut = rand.nextInt(3);
            int columnOut = rand.nextInt(3);
            while(arr[rowOut][columnIn] != " "){
                rowOut = rand.nextInt(3);
                columnOut = rand.nextInt(3);
            }
            //while(arr[rowOut][columnOut] != " "){
            for(int row = 0;row < 3;row++){
                if(arrX[row][0] == "X" && arrX[row][1] == "X" && arr[row][2] != "O" && arr[row][2] != "X"){
                    rowOut = row;
                    columnOut = 2;
                    System.out.println(1);
                }else if(arrX[row][0] == "X" && arrX[row][2] == "X" && arr[row][1] != "O" && arr[row][1] != "X"){
                    rowOut = row;
                    columnOut = 1;
                    System.out.println(2);
                }else if(arrX[row][1] == "X" && arrX[row][2] == "X" && arr[row][0] != "O" && arr[row][0] != "x"){
                    rowOut = row;
                    columnOut = 0;
                    System.out.println(3);
                }
                System.out.println(rowOut + "," + columnOut);
            }
            for(int col = 0;col < 3;col++){
                if(arrX[0][col] == "X" && arrX[1][col] == "X" && arr[0][col] != "O" && arr[0][col] != "X"){
                    rowOut = 2;
                    columnOut = col;
                    System.out.println(4);
                }else if(arrX[1][col] == "X" && arrX[2][col] == "X" && arr[1][col] != "O" && arr[1][col] != "X"){
                    rowOut = 0;
                    columnOut = col;
                    System.out.println(5);
                }else if(arrX[0][col] == "X" && arrX[2][col] == "X" && arr[1][col] != "O" && arr[1][col] != "X"){
                    rowOut = 1;
                    columnOut = col;
                    System.out.println(6);
                }
            }

            
            if(arrX[0][0] == "X" && arrX[1][1] == "X" && arr[2][2] != "O" && arr[2][2] != "X"){
                rowOut = 2;
                columnOut = 2;
                System.out.println(7);
            }else if(arrX[0][0] == "X" && arrX[2][2] == "X" && arr[1][1] != "O" && arr[1][1] != "X"){
                rowOut = 1;
                columnOut = 1;
                System.out.println(8);
            }else if(arrX[1][1] == "X" && arrX[2][2] == "X" && arr[0][0] != "O" && arr[0][0] != "X"){
                rowOut = 0;
                columnOut = 0;
                System.out.println(9);
            }else if(arrX[0][2] == "X" && arrX[1][1] == "X" && arr[2][0] != "O" && arr[2][0] != "X"){
                rowOut = 2;
                columnOut = 0;
                System.out.println(10);
            }else if(arrX[0][2] == "X" && arrX[2][0] == "X" && arr[1][1] != "O" && arr[1][1] != "X"){
                rowOut = 1;
                columnOut = 1;
                System.out.println(11);
            }else if(arrX[1][1] == "X" && arrX[2][0] == "X" && arr[0][2] != "O" && arr[0][2] != "X"){
                rowOut = 0;
                columnOut = 2;
                System.out.println(12);
            }
            //System.out.println(rowOut + "," + columnOut);
            turn = insert(rowOut, columnOut, turn, arr);


            return turn; 
        }   
}
