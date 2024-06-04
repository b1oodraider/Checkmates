import java.util.Scanner;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Desk desk = new Desk();
        Figure[][] f_deck = new Figure[8][8];
        makeDesk(f_deck, desk);



        String[] turn = {"white","black"};
        Scanner scr = new Scanner(System.in);
        int[] coords = new int[4];
        int turn_iter = 0;
        
        while(true) {


            if (checkMate(desk, f_deck, turn[turn_iter%2])){
                System.out.println("checkmate! " + turn[turn_iter%2] + " win!");
                break;
            }

            print(f_deck, desk);
            if (!scr.hasNextInt()) {
                break;
            }
            coords[0] = scr.nextInt() - 1;
            coords[1] = scr.nextInt() - 1;
            coords[2] = scr.nextInt() - 1;
            coords[3] = scr.nextInt() - 1;
            if (!makeTurn(f_deck, desk, turn[turn_iter%2],coords)) {
                System.out.println("wrong turn. Try again!");
            }

            turn_iter++;
            update(f_deck, desk);

        }

    }
    static boolean makeTurn(Figure[][] f_deck, Desk desk, String turn, int[] coords) {
        boolean flag = true;
        
        if (f_deck[coords[1]][coords[0]].move(coords[2], coords[3], desk, turn) == 1 && !kinCheck(desk, f_deck, turn)) {
            f_deck[coords[3]][coords[2]] = f_deck[coords[1]][coords[0]];
            f_deck[coords[1]][coords[0]] = new VoidTile();
            f_deck[coords[3]][coords[2]].set_coordinate(coords[3], coords[2]);
        } else if (f_deck[coords[1]][coords[0]].move(coords[2], coords[3], desk, turn) == 0 && !kinCheck(desk, f_deck, turn)) {
            Figure temp_f = f_deck[coords[3]][coords[2]];
            f_deck[coords[3]][coords[2]] = f_deck[coords[1]][coords[0]];
            f_deck[coords[1]][coords[0]] = temp_f;
            f_deck[coords[3]][coords[2]].set_coordinate(coords[3], coords[2]);
            f_deck[coords[1]][coords[0]].set_coordinate(coords[1], coords[0]);
        } else {
            flag = false;
        }
        return flag;
    }

    static void print(Figure[][] f_deck, Desk desk) {
        for(int i = 0; i < 8; ++i){
            for(int j = 0; j < 8; ++j) {
                if(f_deck[i][j] instanceof Pawn){
                    System.out.print("^");
                } else if (f_deck[i][j] instanceof Knight){
                    System.out.print("K");
                }else if (f_deck[i][j] instanceof Bishop){
                    System.out.print("B");
                }else if (f_deck[i][j] instanceof Rook){
                    System.out.print("R");
                }else if (f_deck[i][j] instanceof Queen){
                    System.out.print("Q");
                }else if (f_deck[i][j] instanceof King){
                    System.out.print("T");
                }else if (f_deck[i][j] instanceof VoidTile){
                    System.out.print("_");
                }
            }
            for(int j = 0; j < 8; ++j) {
                System.out.print("\t" + desk.get_state(i, j) + "\t");
            }
            System.out.println();
        }
    }

    static void update(Figure[][] f_deck, Desk desk){
        int state;
        for(int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (f_deck[i][j] instanceof Pawn) {
                    state = (f_deck[i][j].getSide().equals("black"))? -1 : 1;
                    desk.set_state(state, j, i);
                } else if (f_deck[i][j] instanceof Knight) {
                    state = (f_deck[i][j].getSide().equals("black"))? -2 : 2;
                    desk.set_state(state, j, i);
                } else if (f_deck[i][j] instanceof Bishop) {
                    state = (f_deck[i][j].getSide().equals("black"))? -3 : 3;
                    desk.set_state(state, j, i);
                } else if (f_deck[i][j] instanceof Rook) {
                    state = (f_deck[i][j].getSide().equals("black"))? -4 : 4;
                    desk.set_state(state, j, i);
                } else if (f_deck[i][j] instanceof Queen) {
                    state = (f_deck[i][j].getSide().equals("black"))? -5 : 5;
                    desk.set_state(state, j, i);
                } else if (f_deck[i][j] instanceof King) {
                    state = (f_deck[i][j].getSide().equals("black"))? -6 : 6;
                    desk.set_state(state, j, i);
                } else if (f_deck[i][j] instanceof VoidTile) {
                    desk.set_state(0, j, i);
                }
            }
        }
    }

    static void makeDesk(Figure[][] f_deck, Desk desk) {
        int temp;
        for(int i = 0; i < 8; ++i){
            for(int j = 0; j < 8; ++j) {
                temp = desk.get_state(i,j);
                switch(temp){
                    case 1:
                        f_deck[i][j] = new Pawn("white");
                        f_deck[i][j].set_coordinate(i,j);
                        break;
                    case 2:
                        f_deck[i][j] = new Knight("white");
                        f_deck[i][j].set_coordinate(i,j);
                        break;
                    case 3:
                        f_deck[i][j] = new Bishop("white");
                        f_deck[i][j].set_coordinate(i,j);
                        break;
                    case 4:
                        f_deck[i][j] = new Rook("white");
                        f_deck[i][j].set_coordinate(i,j);
                        break;
                    case 5:
                        f_deck[i][j] = new Queen("white");
                        f_deck[i][j].set_coordinate(i,j);
                        break;
                    case 6:
                        f_deck[i][j] = new King("white");
                        f_deck[i][j].set_coordinate(i,j);
                        break;
                    case -1:
                        f_deck[i][j] = new Pawn("black");
                        f_deck[i][j].set_coordinate(i,j);
                        break;
                    case -2:
                        f_deck[i][j] = new Knight("black");
                        f_deck[i][j].set_coordinate(i,j);
                        break;
                    case -3:
                        f_deck[i][j] = new Bishop("black");
                        f_deck[i][j].set_coordinate(i,j);
                        break;
                    case -4:
                        f_deck[i][j] = new Rook("black");
                        f_deck[i][j].set_coordinate(i,j);
                        break;
                    case -5:
                        f_deck[i][j] = new Queen("black");
                        f_deck[i][j].set_coordinate(i,j);
                        break;
                    case -6:
                        f_deck[i][j] = new King("black");
                        f_deck[i][j].set_coordinate(i,j);
                        break;
                    default:
                        f_deck[i][j] = new VoidTile();
                        f_deck[i][j].set_coordinate(i,j);
                }
            }
        }
    }
    static boolean kinCheck(Desk desk, Figure[][] f_deck, String turn) {
        boolean flag = false;
        for(int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j){
                if (f_deck[i][j] instanceof VoidTile) {
                    continue;
                }
                if( !f_deck[i][j].getSide().equals(turn) && f_deck[i][j].kingCheck(desk, f_deck)){
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    static boolean checkMate(Desk desk, Figure[][] f_deck, String turn) {
        for(int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j){
                if (f_deck[i][j] instanceof VoidTile) {
                    continue;
                }
                if(!f_deck[i][j].getSide().equals(turn)){
                    continue;
                }
                int x = f_deck[i][j].getX_index();
                int y = f_deck[i][j].getY_index();
                for(int k = 0; k < 8; ++k) {
                    for(int r = 0; r < 8; ++r){
                        int[] temp = {x, y, r, k};
                        if (makeTurn(f_deck, desk, turn, temp)) {
                            makeDesk(f_deck, desk);
                            return false;
                        }

                    }
                }
            }
        }
        return true;
    }

}