public class Desk {
    //state < 0     black figure
    //state > 0     white figure
    // state == 0   empty tile

    //figures: 1 - pawn 2 -knight 3 - bishop 4 - rook 5 - queen 6 - king
    private int[][] desk = {{4,2,3,5,6,3,2,4},
                            {1,1,1,1,1,1,1,1},
                            {0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0},
                            {-1,-1,-1,-1,-1,-1,-1,-1},
                            {-4,-2,-3,-5,-6,-3,-2,-4}};
    public int get_state(int y, int x) {
        return desk[y][x];
    }
    public void set_state(int state, int x, int y){
        desk[y][x] = state;
    }
}
