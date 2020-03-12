package UnionFind;

public class NumberOfIslands {
	/*
    @param: char[][] grid input '0' and '1'. '1' stands for land, '0' stands for water
    @return: int, numbers of islands
    Algorithm: UnionFind
    */
    
    class UnionFind {
        int[] father;
        int count;
        UnionFind(int n) {
            father = new int[n];            
            for (int i = 0; i < n; i++) {
                father[i] = i;
            }
        }         
        int find(int x) {
            if (father[x] == x) {
                return x;
            }else {
                return father[x] = find(father[x]);
            }
        }
        void connect(int a, int b) {
            int father_a = find(a);
            int father_b = find(b);
            if (father_a != father_b) {
                father[father_a] = father_b;
                count--;
            }
        }
        void setCount(int count) {
            this.count = count;
        }
        int getCount() {
            return count;
        }
    }
    
    public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length, col = grid[0].length;
        
        UnionFind unionFind = new UnionFind(row * col);
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    count++;
                }
            }
        }
        unionFind.setCount(count);
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    if (i > 0 && grid[i-1][j] == '1') {
                        unionFind.connect(i * col + j, (i - 1) * col + j);
                    }
                    if (j > 0 && grid[i][j-1] == '1') {
                        unionFind.connect(i * col + j, i * col + (j - 1));
                    }
                    if (i < row - 1 && grid[i+1][j] == '1') {
                        unionFind.connect(i * col + j, (i + 1) * col + j);
                    }
                    if (j < col - 1 && grid[i][j+1] == '1') {
                        unionFind.connect(i * col + j, i * col + j + 1);
                    }
                }
            }
        }
        return unionFind.getCount();        
    }
}
