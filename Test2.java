编写一个程序，通过已填充的空格来解决数独问题。

一个数独的解法需遵循如下规则：

数字 1-9 在每一行只能出现一次。
数字 1-9 在每一列只能出现一次。
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
空白格用 '.' 表示。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sudoku-solver
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
给定的数独序列只包含数字 1-9 和字符 '.' 。
你可以假设给定的数独只有唯一解。
给定数独永远是 9x9 形式的。

class Solution {
    public void solveSudoku(char[][] board) {
        backTrack(board,0,0);
    }
    private boolean backTrack(char[][] board,int x,int y){
        if(x==9){
            return true;
        }
        if(y==9){
            return backTrack(board,x+1,0);
        }
        if(board[x][y]!='.'){
            return backTrack(board,x,y+1);
        }
        for(char c='1';c<='9';c++){
            if(isValid(board,x,y,c)){
                board[x][y]=c;
                if(backTrack(board,x,y+1)){
                    return true;
                }
                board[x][y]='.';
            }
        }
        return false;
    }
    private boolean isValid(char[][] board,int x,int y,char num){
        for(int i=0;i<9;i++){
            if(board[x][i]==num) return false;
            if(board[i][y]==num) return false;
            if(board[x/3*3+i/3][y/3*3+i%3]==num) return false;
        }
        return true;
    }
}

n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。

每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/n-queens
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    List<List<String>> res=new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        char[][] arr=new char[n][n];
       for(int i=0;i<n;i++){
           Arrays.fill(arr[i],'.');
       }
        backTrack(arr,n,0);
        return res;
    }
    private void backTrack(char[][] arr,int n,int x){
        if(x==n){
            List<String> row=new ArrayList<>();
            for(int i=0;i<n;i++){
                row.add(new String(arr[i]));
            }
            res.add(new ArrayList(row));
            return;
        }
        for(int i=0;i<n;i++){
            if(isValid(arr,n,x,i)){
                arr[x][i]='Q';
                backTrack(arr,n,x+1);
                arr[x][i]='.';
            }
        }
    }
    private boolean isValid(char[][] arr,int n,int x,int y){
        for(int i=0;i<n;i++){
            if(arr[i][y]=='Q') return false;
        }
        for(int i=x-1,j=y-1;i>=0&&j>=0;i--,j--){
            if(arr[i][j]=='Q') return false;
        }
        for(int i=x-1,j=y+1;i>=0&&j<n;i--,j++){
            if(arr[i][j]=='Q') return false;
        }
        for(int i=x+1,j=y+1;i<n&&j<n;i++,j++){
            if(arr[i][j]=='Q') return false;
        }
        for(int i=x+1,j=y-1;i<n&&j>=0;i++,j--){
            if(arr[i][j]=='Q') return false;
        }
        return true;
    }
}
n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
给定一个整数 n，返回 n 皇后不同的解决方案的数量。
class Solution {
    private int count;
    public int totalNQueens(int n) { 
        char[][] board=new char[n][n];
        for(int i=0;i<n;i++){
            Arrays.fill(board[i],'.');
        }
        backTrack(board,n,0);
        return count;
    }
    private void backTrack(char[][] board,int n,int x){
        if(x==n){
            count++;
            return ;
        }
        for(int i=0;i<n;i++){
            if(isValid(board,n,x,i)){
                board[x][i]='Q';
                backTrack(board,n,x+1);
                board[x][i]='.';
            }
        }
    }
    private boolean isValid(char[][] board,int n,int x,int y){
        for(int i=0;i<n;i++){
            if(board[i][y]=='Q') return false;
        }
        for(int i=x-1,j=y-1;i>=0&&j>=0;i--,j--){
            if(board[i][j]=='Q') return false;
        }
        for(int i=x-1,j=y+1;i>=0&&j<n;i--,j++){
            if(board[i][j]=='Q') return false;
        }
        for(int i=x+1,j=y+1;i<n&&j<n;i++,j++){
            if(board[i][j]=='Q') return false;
        }
        for(int i=x+1,j=y-1;i<n&&j>=0;i++,j--){
            if(board[i][j]=='Q') return false;
        }
        return true;
    }
}


设计一种算法，打印 N 皇后在 N × N 棋盘上的各种摆法，其中每个皇后都不同行、不同列，也不在对角线上。这里的“对角线”指的是所有的对角线，不只是平分整个棋盘的那两条对角线。

注意：本题相对原题做了扩展

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/eight-queens-lcci
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    List<List<String>> res=new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        char[][] arr=new char[n][n];
       for(int i=0;i<n;i++){
           Arrays.fill(arr[i],'.');
       }
        backTrack(arr,n,0);
        return res;
    }
    private void backTrack(char[][] arr,int n,int x){
        if(x==n){
            List<String> row=new ArrayList<>();
            for(int i=0;i<n;i++){
                row.add(new String(arr[i]));
            }
            res.add(new ArrayList(row));
            return;
        }
        for(int i=0;i<n;i++){
            if(isValid(arr,n,x,i)){
                arr[x][i]='Q';
                backTrack(arr,n,x+1);
                arr[x][i]='.';
            }
        }
    }
    private boolean isValid(char[][] arr,int n,int x,int y){
        for(int i=0;i<n;i++){
            if(arr[i][y]=='Q') return false;
        }
        for(int i=x-1,j=y-1;i>=0&&j>=0;i--,j--){
            if(arr[i][j]=='Q') return false;
        }
        for(int i=x-1,j=y+1;i>=0&&j<n;i--,j++){
            if(arr[i][j]=='Q') return false;
        }
        for(int i=x+1,j=y+1;i<n&&j<n;i++,j++){
            if(arr[i][j]=='Q') return false;
        }
        for(int i=x+1,j=y-1;i<n&&j>=0;i++,j--){
            if(arr[i][j]=='Q') return false;
        }
        return true;
    }
}

堆箱子。给你一堆n个箱子，箱子宽 wi、深 di、高 hi。箱子不能翻转，将箱子堆起来时，下面箱子的宽度、高度和深度必须大于上面的箱子。实现一种方法，搭出最高的一堆箱子。箱堆的高度为每个箱子高度的总和。

输入使用数组[wi, di, hi]表示每个箱子。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/pile-box-lcci
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public int pileBox(int[][] box) {
        Arrays.sort(box,new Comparator<int[]>(){
            public int compare(int[] a,int[] b){
                return a[0]==b[0]?a[1]==b[1]?a[2]-b[2]:a[1]-b[1]:a[0]-b[0];
            }
        });     
        int row=box.length;
        int max=box[0][2];
        int[] dp=new int[row];
        for(int i=0;i<row;i++){
            dp[i]=box[i][2];
        }
        for(int i=1;i<row;i++){
            for(int j=0;j<i;j++){
                if(box[i][0]>box[j][0]&&box[i][1]>box[j][1]&&box[i][2]>box[j][2])
                dp[i]=Math.max(dp[i],dp[j]+box[i][2]);
                max=Math.max(max,dp[i]);
            }
        }
        return max;
    }
}


