public class King extends Figure{
    public King(String side){
        setSide(side);
    }

    @Override
    public boolean move_rules(int new_x, int new_y, Desk desk, String turn) {
        int new_state = desk.get_state(new_y, new_x);
        int y = this.getY_index();
        int x = this.getX_index();
        int dx = Math.abs(x - new_x);
        int dy = Math.abs(y - new_y);
        boolean cond = dy <= 1 && dx <= 1;
        boolean flag = false;

        if (!this.getSide().equals(turn)) {
            flag = false;
        }else if (this.getSide().equals("white")) {
            flag = (cond && new_state < 1 && this.checkWall(new_y, new_x,desk));
        } else  if(this.getSide().equals("black")){
            flag = (cond && new_state > -1 && this.checkWall(new_y, new_x, desk));
        }
        return flag;
    }

    private boolean checkWall(int new_y, int new_x, Desk desk) {
        boolean flag = true;
        int y = this.getY_index();
        int x = this.getX_index();


        for (int i = y - 1 ; i <= y + 1 ; ++i ) {
            if (i < 0 || i > 7) {
                continue;
            }
            for (int j = x - 1;j <= x + 1; ++j) {
                if(j > 7 || j < 0) {
                    continue;
                }
                if (Math.abs(desk.get_state(i,j)) == 6) {
                    flag = false;
                }
            }
        }
        if (flag) {
            flag = (this.getSide().equals("white") && desk.get_state(new_y, new_x) < 0) ||
                    (this.getSide().equals("black") && desk.get_state(new_y, new_x) > 0) || (desk.get_state(new_y, new_x) == 0);
        }

        return flag;
    }
}
