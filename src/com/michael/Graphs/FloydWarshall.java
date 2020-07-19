package com.michael.Graphs;


/**
 * This algorithm finds the shortest path to all pairs of vertices
 * The algorithm takes O(V^3) time where V are the number of vertices.
 *
 */
public class FloydWarshall {

    static int [][] g; // the graph
    static int n;
    static int [][] ans; //  solution

    public static void solve(){
        // initialization

        int INF = (int) 1e9 + 100;

        for(int r = 0; r < n; r ++){
            for(int c = 0; c < n; c ++){

                if(g[r][c] == 0){
                    ans[r][c] = INF;
                }else{
                    ans[r][c] = g[r][c];
                }
            }
        }

        for(int x = 0; x < n; x ++){

            for(int R = 0; R < n; R ++){

                for(int L = 0; L < n; L ++){

                    ans[R][L] = Math.min(ans[R][L], ans[R][x] + ans[x][L]);
                }
            }
        }
    }


    public  static void main(String [] args){

    }
}
