public class Pawn extends Figure{
    public Pawn(String side){
        setSide(side);
    }
    @Override
    public boolean move_rules(int new_x, int new_y, Desk desk, String turn) {
        int new_state = desk.get_state(new_y, new_x);
        int y = this.getY_index();
        int x = this.getX_index();
        int dx = Math.abs(x - new_x);
        int dy = Math.abs(y - new_y);
        boolean flag;
        if (!this.getSide().equals(turn)){
            flag = false;
        }else if (this.getSide().equals("white")) {
            flag = ((dy == 2 && y == 1 && checkWall(new_y, desk)) ||
                    (dy == 1 && dx == 1 && new_state < 0) ||
                    (dy == 1 && dx == 0 && new_state ==0))
                    && new_y > y;
        } else  if(this.getSide().equals("black")){
            flag = ((dy == 2 &&  y == 6 && checkWall(new_y, desk)) ||
                    (dy == 1 && dx == 1 && new_state > 0) ||
                    (dy == 1 && dx == 0 && new_state ==0))
                    && new_y < y;
        } else {
            flag = false;
        }
        return flag;
    }

    private boolean checkWall(int new_y, Desk desk) {
        boolean flag = true;
        int y = this.getY_index();
        if(y < new_y){
            for (int i = y + 1; i <= new_y; ++i) {
                if (desk.get_state(i, this.getX_index()) != 0) {
                    flag = false;
                    break;
                }
            }
        } else {
            for (int i = y - 1; i >= new_y; --i) {
                if (desk.get_state(i, this.getX_index()) != 0) {
                    flag = false;
                    break;
                }
            }
        }

        return flag;
    }

//    @Override
//    public boolean kingCheck(Desk desk, Figure[][] f_deck) {
//        boolean flag = false;
//        int y = this.getY_index();
//        int x = this.getX_index();
//
//        if (this.getSide().equals("white")) {
//            if(x == 0) {
//                flag = Math.abs(desk.get_state(y+1,x+1)) == 6 && !f_deck[y+1][x+1].getSide().equals(this.getSide());
//            } else if (x == 7) {
//                flag = Math.abs(desk.get_state(y+1,x-1)) == 6 && !f_deck[y+1][x-1].getSide().equals(this.getSide());
//            } else if (y == 7) {
//                flag = false;
//            }else {
//                if (Math.abs(desk.get_state(y+1,x+1)) == 6 && !f_deck[y+1][x+1].getSide().equals(this.getSide())) {
//                    flag = true;
//                } else if (Math.abs(desk.get_state(y+1,x-1)) == 6 && !f_deck[y+1][x-1].getSide().equals(this.getSide())) {
//                    flag = true;
//                }
//            }
//
//        } else {
//            if (x == 0) {
//                flag = Math.abs(desk.get_state(y-1,x+1)) == 6 && !f_deck[y-1][x+1].getSide().equals(this.getSide());
//            } else if (x == 7) {
//                flag = Math.abs(desk.get_state(y-1,x-1)) == 6 && !f_deck[y-1][x-1].getSide().equals(this.getSide());
//            } else if(y == 0) {
//                flag = false;
//            } else {
//                if (Math.abs(desk.get_state(y-1,x+1)) == 6 && !f_deck[y-1][x+1].getSide().equals(this.getSide())) {
//                    flag = true;
//                }  else if (Math.abs(desk.get_state(y-1,x-1)) == 6 && !f_deck[y-1][x-1].getSide().equals(this.getSide())) {
//                    flag = true;
//                }
//            }
//
//        }
//
//        return flag;
//
//    }
}
