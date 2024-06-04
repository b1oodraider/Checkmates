public class VoidTile extends Figure{
    @Override
    public boolean move_rules(int new_x, int new_y, Desk desk, String turn) {
        return false;
    }
}
