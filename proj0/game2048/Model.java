package game2048;

import java.util.Formatter;
import java.util.Observable;


/** The state of a game of 2048.
 *  @author TODO: Jiachen Qin
 */
public class Model extends Observable {
    /** Current contents of the board. */
    private Board board;
    /** Current score. */
    private int score;
    /** Maximum score so far.  Updated when game ends. */
    private int maxScore;
    /** True iff game is ended. */
    private boolean gameOver;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        board = new Board(size);
        score = maxScore = 0;
        gameOver = false;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        int size = rawValues.length;
        board = new Board(rawValues, score);
        this.score = score;
        this.maxScore = maxScore;
        this.gameOver = gameOver;
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. Should be deprecated and removed.
     *  */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /** Return the number of squares on one side of the board.
     *  Used for testing. Should be deprecated and removed. */
    public int size() {
        return board.size();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        checkGameOver();
        if (gameOver) {
            maxScore = Math.max(score, maxScore);
        }
        return gameOver;
    }

    /** Return the current score. */
    public int score() {
        return score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        gameOver = false;
        board.clear();
        setChanged();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /** Tilt the board toward SIDE. Return true iff this changes the board.
     *
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     * */
    public boolean changecol(int col){  //helpermethod
        boolean changed = false;
        //boolean get_previous_value = false;
        int get_row = size()-1; //每次都是两个合并，所以记录前一个
        int previous_value = 0; //考虑null，不知道是否冗余
        int current_row = size()-1;//从第一个不等于null的开始进行计算
        for (int previous_row=size()-1;previous_row>=0;previous_row-=1) {
            if (tile(col,previous_row)==null) { //是空就跳过
                continue;
            }
            if (previous_value == 0) { //没拿到的时候拿东西
                previous_value = tile(col,previous_row).value();
                get_row = previous_row;
                //get_previous_value = true;
                continue;  //拿到之后再进行运算，且跳过这一轮来减少代码逻辑
            }
            if (previous_value != 0 & tile(col,previous_row).value() != previous_value) { //拿到之后判断：不同就把上一个放掉
                if (current_row != get_row) {
                    changed = true;  //注意判断是否真实移动
                    Tile t = board.tile(col, get_row);
                    board.move(col, current_row, t);
                }
                previous_value = tile(col, previous_row).value();
                get_row = previous_row;
                current_row -= 1; //如果不一样，无论移动没有，下一个要被操作的tilt会变化
            }
            else if (previous_value != 0 & tile(col,previous_row).value() == previous_value) { // 相同则两个都放掉
                score += previous_value*2;
                Tile t = board.tile(col,get_row);
                board.move(col, current_row,t);
                t = board.tile(col, previous_row);
                board.move(col, current_row, t);
                previous_value =0;
                current_row -= 1;
                //get_previous_value = false; //两个合并之后就没有前一个了
                changed = true;
                }
            //查缺补漏：有一个在循环之后没放掉时

                //如果不同，则添加
                //如果相同，则修改
                //考虑到最后一直null？(利用current_row来填充）

        }
        if (previous_value!=0&current_row!=get_row) {
            Tile t = board.tile(col, get_row);
            board.move(col, current_row, t);
            changed = true; //保证最后一个放掉
        }
        return changed;
    }
    public boolean northway(boolean changed) {
        for (int col=0;col<size();col+=1) {
            changed = changecol(col)|changed;
        }
        return changed;
    }
    public boolean tilt(Side side) {
        boolean changed;
        changed = false;
        if (side == Side.NORTH) {
            changed = northway(changed);

                    /**
                    #### up:
            1. Each column has the same operations.
            2. We add two variables to record the previous value and the current column.
            3. If there is null, just be sure to skip. After the last one is reached and it doesn't change, add it to the board
            4. For the rest, add null to it.
                     */
        }
        if (side != Side.NORTH)  {
            board.setViewingPerspective(side);
            changed = northway(changed);
            board.setViewingPerspective(Side.NORTH);
        }
        // TODO: Modify this.board (and perhaps this.score) to account
        // for the tilt to the Side SIDE. If the board changed, set the
        // changed local variable to true.

        checkGameOver();
        if (changed) {
            setChanged();
        }
        return changed;
    }

    /** Checks if the game is over and sets the gameOver variable
     *  appropriately.
     */
    private void checkGameOver() {
        gameOver = checkGameOver(board);
    }

    /** Determine whether game is over. */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     * */
    public static boolean emptySpaceExists(Board b) {
        boolean whetheremptySpaceExists = false;
        for (int i=0;i<b.size();i+=1) {
            for (int j=0;j<b.size();j+=1) {
                if (b.tile(i,j) == null) {
                    whetheremptySpaceExists = true;
                }
            }
        }

        // TODO: Fill in this function.
        return whetheremptySpaceExists;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board b) {
        boolean whethermaxTileExists = false;
        for (int i=0;i<b.size();i+=1) {
            for (int j=0;j<b.size();j+=1) {
                if (b.tile(i,j) == null) {
                    continue;
                }
                if (b.tile(i,j).value() == MAX_PIECE) {
                    whethermaxTileExists = true;
                }
            }
        }

        // TODO: Fill in this function.
        return whethermaxTileExists;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public static boolean atLeastOneMoveExists(Board b) {
        if (Model.emptySpaceExists(b)) {
            return true;
        }
        // TODO: Fill in this function.
        boolean whetheratLeastOneMoveExists = false;
        for (int i=0;i<b.size();i+=1) {
            for (int j=0;j<b.size()-1;j+=1) {
                if (b.tile(i,j).value() == b.tile(i,j+1).value()) {
                    whetheratLeastOneMoveExists = true;
                }
            }
        }
        for (int j=0;j<b.size();j+=1) {
            for (int i=0;i<b.size()-1;i+=1) {
                if (b.tile(i,j).value() == b.tile(i+1,j).value()) {
                    whetheratLeastOneMoveExists = true;
                }
            }
        }
        return whetheratLeastOneMoveExists;
    }


    @Override
     /** Returns the model as a string, used for debugging. */
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    /** Returns whether two models are equal. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    /** Returns hash code of Model’s string. */
    public int hashCode() {
        return toString().hashCode();
    }
}
