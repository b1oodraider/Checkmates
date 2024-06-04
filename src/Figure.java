public abstract class Figure {
    private int x_index;
    private int y_index;
    private String side;

    public int getX_index() {
        return x_index;
    }
    public int getY_index() {
        return y_index;
    }
    public String getSide(){
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }


    public void set_coordinate(int y_index, int x_index) {
        this.x_index = x_index;
        this.y_index = y_index;
    }

    public boolean move_rules(int new_x, int new_y, Desk desk, String turn) {
        return true;
    }
    public int move(int new_x, int new_y, Desk desk, String turn){
        int flag = 0;
        if(move_rules(new_x, new_y,desk, turn)) {
            if(desk.get_state(new_y, new_x) != 0) {
                flag = 1;
            }
        } else {
            flag = -1;
        }
        return flag;
    }

    public boolean kingCheck(Desk desk, Figure[][] f_deck) {
        return false;
    }


}
