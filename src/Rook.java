public class Rook extends Figure {
    public Rook(String side){
        setSide(side);
    }

    @Override
    public boolean move_rules(int new_x, int new_y, Desk desk, String turn) {
        int new_state = desk.get_state(new_y, new_x);
        int y = this.getY_index();
        int x = this.getX_index();
        int dx = Math.abs(x - new_x);
        int dy = Math.abs(y - new_y);
        boolean cond = (dy != 0 && dx == 0 || dy == 0 && dx !=0);
        boolean flag;

        if(!this.getSide().equals(turn)){
            flag = false;
        } else if (this.getSide().equals("white")) {
            flag = (cond && new_state < 1 && this.checkWall(new_y, new_x,desk));
        } else  if(this.getSide().equals("black")){
            flag = (cond && new_state > -1 && this.checkWall(new_y, new_x, desk));
        } else {
            flag = false;
        }
        return flag;
    }

    private boolean checkWall(int new_y, int new_x, Desk desk) {
        boolean flag = true;
        int y = this.getY_index();
        int x = this.getX_index();


        if (new_x == x && new_y > y) {
            for (int i = y + 1; i <= new_y ; ++i) {
                if(desk.get_state(i,x) != 0 && i != new_y ) {
                    flag = false;
                    break;
                }
            }
        } else if(new_x == x && new_y < y) {
            for (int i = y - 1; i >= new_y ; --i) {
                if(desk.get_state(i,x) != 0 && i != new_y ) {
                    flag = false;
                    break;
                }
            }
        }else if(new_x < x && new_y == y) {
            for (int j = x - 1;  j >= new_x; --j) {
                if(desk.get_state(y,j) != 0 && j != new_x ) {
                    flag = false;
                    break;
                }
            }
        } else if (new_x > x && new_y == y){
            for (int  j = x + 1; j <= new_x; ++j) {
                if(desk.get_state(y,j) != 0 && j != new_x ) {
                    flag = false;
                    break;
                }
            }
        }
        if (flag) {
            flag = (this.getSide().equals("white") && desk.get_state(new_y, new_x) < 0) ||
                    (this.getSide().equals("black") && desk.get_state(new_y, new_x) > 0) || (desk.get_state(new_y, new_x) == 0);
        }

        return flag;
    }

//    @Override
//    public boolean kingCheck(Desk desk, Figure[][] f_deck) {
//        boolean flag = false;
//        int y = this.getY_index();
//        int x = this.getX_index();
//        for (int i = y - 1; i >= 0; --i ) {
//            if(desk.get_state(i,x) != 0) {
//                if (Math.abs(desk.get_state(i,x)) == 6 && !f_deck[i][x].getSide().equals(this.getSide())) {
//                    flag = true;
//                }
//                break;
//            }
//        }
//        for (int i = x - 1; i >= 0; --i ) {
//            if(desk.get_state(y,i) != 0) {
//                if (Math.abs(desk.get_state(y,i)) == 6 && !f_deck[y][i].getSide().equals(this.getSide())) {
//                    flag = true;
//                }
//                break;
//            }
//        }
//        for (int i = y + 1; i <= 7; ++i ) {
//            if(desk.get_state(i,x) != 0) {
//                if (Math.abs(desk.get_state(i,x)) == 6 && !f_deck[i][x].getSide().equals(this.getSide())) {
//                    flag = true;
//                }
//                break;
//            }
//        }
//        for (int i = x + 1; i <= 7; ++i ) {
//            if(desk.get_state(y,i) != 0) {
//                if (Math.abs(desk.get_state(i,x)) == 6 && !f_deck[y][i].getSide().equals(this.getSide())) {
//                    flag = true;
//                }
//                break;
//            }
//        }
//
//
//        return flag;
//
//    }
}
