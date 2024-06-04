public class Knight extends Figure{
    public Knight(String side){
        setSide(side);
    }
    @Override
    public boolean move_rules(int new_x, int new_y, Desk desk, String turn) {
        int new_state = desk.get_state(new_y, new_x);
        int y = this.getY_index();
        int x = this.getX_index();
        int dx = Math.abs(x - new_x);
        int dy = Math.abs(y - new_y);
        boolean flag = false;
        if (dx == 0 || dy == 0 || !this.getSide().equals(turn)){
            flag = false;
        } else if (this.getSide().equals("white")) {
            flag = (dy/dx == 2 || dx/dy == 2) && new_state < 1 && checkWall(new_y, new_x,desk);
        } else  if(this.getSide().equals("black")){
            flag = (dy/dx == 2 || dx/dy == 2) && new_state > -1 && checkWall(new_y, new_x, desk);
        }
        return flag;
    }

    private boolean checkWall(int new_y, int new_x, Desk desk) {
        boolean flag = true;
        int y = this.getY_index();
        int x = this.getX_index();
        int scale = 0;

        if (new_x > x && new_y > y) {
            for (int i = y; i <= new_y; ++i) {
                for(int j = x; j <= new_x; ++j) {
                    if (desk.get_state(i,j) !=0) {
                        scale++;
                    }
                }
            }
        } else if(new_x > x && new_y < y) {
            for (int i = y; i >= new_y; --i) {
                for (int j = x; j <= new_x; ++j) {
                    if (desk.get_state(i, j) != 0) {
                        scale++;
                    }
                }
            }
        }else if(new_x < x && new_y < y) {
            for (int i = y; i >= new_y; --i) {
                for (int j = x; j >= new_x; --j) {
                    if (desk.get_state(i, j) != 0) {
                        scale++;
                    }
                }
            }
        } else if (new_x < x && new_y > y){
            for (int i = y ; i <= new_y; ++i) {
                for(int j = x; j >= new_x; --j) {
                    if (desk.get_state(i,j) !=0) {
                        scale++;
                    }
                }
            }
        }
        boolean true_side;
        true_side = this.getSide().equals("white") && desk.get_state(new_y, new_x) < 0 ||
                this.getSide().equals("black") && desk.get_state(new_y, new_x) > 0;
        flag = scale < 5 && desk.get_state(new_y, new_x) == 0 || scale == 5 && true_side;
        return flag;
    }

    @Override
    public boolean kingCheck(Desk desk, Figure[][] f_deck) {
        int y = this.getY_index();
        int x = this.getX_index();
        boolean flag = false;
        if (y + 1 > 7 || y + 2 > 7 || y - 1 < 0 || y - 2 < 0 || x + 1 > 7 || x + 2 > 7 || x - 1 < 0 || x - 2 < 0) {
            flag = false;
        } else if (Math.abs(desk.get_state(y + 1, x + 2)) == 6 && !f_deck[y+1][x+2].getSide().equals(this.getSide())) {
            flag = true;
        } else if (Math.abs(desk.get_state(y - 1, x + 2)) == 6 && !f_deck[y-1][x+2].getSide().equals(this.getSide())) {
            flag = true;
        } else if (Math.abs(desk.get_state(y - 1, x - 2)) == 6 && !f_deck[y-1][x-2].getSide().equals(this.getSide())) {
            flag = true;
        } else if (Math.abs(desk.get_state(y + 1, x - 2)) == 6 && !f_deck[y+1][x-2].getSide().equals(this.getSide())) {
            flag = true;
        } else if (Math.abs(desk.get_state(y - 2, x + 1)) == 6 && !f_deck[y-2][x+1].getSide().equals(this.getSide())) {
            flag = true;
        }else if (Math.abs(desk.get_state(y - 2, x - 1)) == 6 && !f_deck[y-2][x-1].getSide().equals(this.getSide())) {
            flag = true;
        } else if (Math.abs(desk.get_state(y + 2, x + 1)) == 6 && !f_deck[y+2][x+1].getSide().equals(this.getSide())) {
            flag = true;
        } else if (Math.abs(desk.get_state(y + 2, x - 1)) == 6 && !f_deck[y+2][x-1].getSide().equals(this.getSide())) {
            flag = true;
        }

        return false;
    }

}
