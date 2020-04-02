根据 百度百科 ，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。

给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：

如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
根据当前状态，写一个函数来计算面板上所有细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/game-of-life
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    private int row;
    private int col;
    public void gameOfLife(int[][] board) {
        row=board.length;
        col=board[0].length;
        int[][] arr=new int[row][col];
        for(int i=0;i<row;i++){
            System.arraycopy(board[i],0,arr[i],0,col);
        }
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(arr[i][j]==1){
                    if(isAlive(arr,i,j)){
                        board[i][j]=1;
                    }else{
                        board[i][j]=0;
                    }
                }else{
                    if(isReAlive(arr,i,j)){
                        board[i][j]=1;
                    }else{
                        board[i][j]=0;
                    }
                }
            }
        }
    }
    private boolean isAlive(int[][] board,int i,int j){
        int[] x={-1,-1,-1,0,0,1,1,1};
        int[] y={-1,0,1,-1,1,-1,0,1};
        int count=0;
        for(int k=0;k<8;k++){
            int newX=i+x[k];
            int newY=j+y[k];
            if(newX>=0&&newY>=0&&newX<row&&newY<col){
                if(board[newX][newY]==1){
                    count++;
                }
            }
        }
        return count==2||count==3;
    }
    private boolean isReAlive(int[][] board,int i,int j){
        int[] x={-1,-1,-1,0,0,1,1,1};
        int[] y={-1,0,1,-1,1,-1,0,1};
        int count=0;
        for(int k=0;k<8;k++){
            int newX=i+x[k];
            int newY=j+y[k];
            if(newX>=0&&newY>=0&&newX<row&&newY<col){
                if(board[newX][newY]==1){
                    count++;
                }
            }
        }
        return count==3;
    }
}

class Solution {
    private int row;
    private int col;
    public void gameOfLife(int[][] board) {
        row=board.length;
        col=board[0].length;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                int count=countAlive(board,i,j);
                if((board[i][j]&1)==1){
                    if(count==2||count==3){
                        board[i][j]=3;
                    }
                }else{
                    if(count==3){
                        board[i][j]=2;
                    }
                }
            }
        }

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                board[i][j]>>=1;
            }
        }
    }
    private int countAlive(int[][] board,int i,int j){
        int[] x={-1,-1,-1,0,0,1,1,1};
        int[] y={-1,0,1,-1,1,-1,0,1};
        int count=0;
        for(int k=0;k<8;k++){
            int newX=i+x[k];
            int newY=j+y[k];
            if(newX>=0&&newY>=0&&newX<row&&newY<col){
                if((board[newX][newY]&1)==1){
                    count++;
                }
            }
        }
        return count;
    }
}

括号。设计一种算法，打印n对括号的所有合法的（例如，开闭一一对应）组合。

说明：解集不能包含重复的子集。
class Solution {
    List<String> res=new LinkedList<>();
    public List<String> generateParenthesis(int n) {
        backTrack(n,0,0,"");
        return res;
    }
    private void backTrack(int n,int left,int right,String s){
        if(right>left||right>n||left>n){
            return;
        }
        if(n==left&&n==right){
            res.add(s);
            return;
        }
        backTrack(n,left+1,right,s+"(");
        backTrack(n,left,right+1,s+")");
    }
}

