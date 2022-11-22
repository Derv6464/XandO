public class grid {

    public static void main(String[] args){
        boolean turn = true;
        String[][] arr = new String[3][3];
        for(int i = 0;i<arr.length;i++){
            for(int j = 0;j<arr.length;j++){
                arr[i][j] = " ";
            }
        }
        updateGrid(arr); 
        turn = insert(0,0,turn,arr);   
        updateGrid(arr);
        turn = insert(0,1,turn,arr); 
        updateGrid(arr);

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
        System.out.println("");
    }
    

}
