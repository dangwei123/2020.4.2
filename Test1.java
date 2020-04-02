幂集。编写一种方法，返回某集合的所有子集。集合中不包含重复的元素。

说明：解集不能包含重复的子集。
class Solution {
    List<List<Integer>> res=new LinkedList<>();
    public List<List<Integer>> subsets(int[] nums) {
        backTrack(nums,0,nums.length,new LinkedList<Integer>());
        return res;
    }
    private void backTrack(int[] nums,int index,int len,LinkedList<Integer> row){
        res.add(new LinkedList(row));
        if(index==len){
            return;
        }
        for(int i=index;i<len;i++){
            row.add(nums[i]);
            backTrack(nums,i+1,len,row);
            row.removeLast();
        }
    }
}

你要开发一座金矿，地质勘测学家已经探明了这座金矿中的资源分布，并用大小为 m * n 的网格 grid 进行了标注。每个单元格中的整数就表示这一单元格中的黄金数量；如果该单元格是空的，那么就是 0。

为了使收益最大化，矿工需要按以下规则来开采黄金：

每当矿工进入一个单元，就会收集该单元格中的所有黄金。
矿工每次可以从当前位置向上下左右四个方向走。
每个单元格只能被开采（进入）一次。
不得开采（进入）黄金数目为 0 的单元格。
矿工可以从网格中 任意一个 有黄金的单元格出发或者是停止。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/path-with-maximum-gold
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    private int max;
    private int[] dx={0,1,-1,0,0};
    private int[] dy={0,0,0,1,-1};
    private int row;
    private int col;
    public int getMaximumGold(int[][] grid) {
        row=grid.length;
        col=grid[0].length;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(grid[i][j]!=0){
                    backTrack(grid,i,j,0,new boolean[row][col]);
                }
            }
        }
        return max;
    }
    private void backTrack(int[][] grid,int i,int j,int sum,boolean[][] isVisited){
        for(int k=0;k<5;k++){
            int x=i+dx[k];
            int y=j+dy[k];
            if(x<0||y<0||x>=row||y>=col||grid[x][y]==0||isVisited[x][y]){
                continue;
            }
            sum+=grid[x][y];
            isVisited[x][y]=true;
            max=Math.max(max,sum);
            backTrack(grid,x,y,sum,isVisited);
            isVisited[x][y]=false;
            sum-=grid[x][y];
        }
    }
}

给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。

请返回所有可行解 s 中最长长度。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    private int max;
    public int maxLength(List<String> arr) {
        backTrack(arr,arr.size(),0,"");
        return max;
    }
    private void backTrack(List<String> arr,int size,int index,String s){
        if(index==size){
            return;
        }
        for(int i=index;i<size;i++){
            if(isVaild(arr.get(i))&&isVaild(s,arr.get(i))){
                s+=arr.get(i);
                int len=arr.get(i).length();
                max=Math.max(max,s.length());
                backTrack(arr,size,i+1,s);
                s=s.substring(0,s.length()-len);
            }
        }
    }
    private boolean isVaild(String a,String b){
        for(int j=0;j<b.length();j++){
            if(a.contains(b.charAt(j)+"")){
                return false;
            }
        }
        return true;
    }
    private boolean isVaild(String s){
        for(int i=0;i<s.length()-1;i++){
            char c=s.charAt(i);
            for(int j=i+1;j<s.length();j++){
                if(c==s.charAt(j)){
                    return false;
                }
            }
        }
        return true;
    }
}

我们定义「顺次数」为：每一位上的数字都比前一位上的数字大 1 的整数。

请你返回由 [low, high] 范围内所有顺次数组成的 有序 列表（从小到大排序）。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sequential-digits
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        String s="123456789";
        List<Integer> res=new ArrayList<>();
        int left=String.valueOf(low).length();
        int right=String.valueOf(high).length();
        while(left<=right){
            int i=0;
            while(i+left<=s.length()){
                int t=Integer.valueOf(s.substring(i,i+left));
                if(t>=low&&t<=high){
                    res.add(t);
                }
                if(t>high){
                    return res;
                }
                i++;
            }
            left++;
        }
        return res;
    }
}