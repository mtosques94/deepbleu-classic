package com.example.mtosq.deepbleu;

import com.example.mtosq.deepbleu.pieces.Bishop;
import com.example.mtosq.deepbleu.pieces.King;
import com.example.mtosq.deepbleu.pieces.Knight;
import com.example.mtosq.deepbleu.pieces.Pawn;
import com.example.mtosq.deepbleu.pieces.Queen;
import com.example.mtosq.deepbleu.pieces.Rook;

import java.io.Serializable;

public class ChessMove implements Serializable {

    static final char[] COLUMNS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    int fromRow;
    int fromCol;
    int toRow;
    int toCol;

    //If this move captures a piece, we will hold a reference to the captured piece here.
    //Otherwise we can't rewind the game properly.
    Piece enemyCaptured = null;

    /**
     * ConsolePlayer input constructor
     * @param fromCol source column as character
     * @param fromRow source row as character
     * @param toRow destination row as integer
     * @param toCol destination column as integer
     */
    public ChessMove(char fromCol, int fromRow, char toCol, int toRow) {
        for (int x = 0; x < COLUMNS.length; x++) {
            if (fromCol == COLUMNS[x])
                this.fromCol = x;
            if (toCol == COLUMNS[x])
                this.toCol = x;
        }
        this.fromRow = 8 - fromRow;
        this.toRow = 8 - toRow;
    }

    /**
     * Low level constructor used by GUIPlayer and ComputerPlayer
     * @param fromRow source row as integer
     * @param fromCol source column as integer
     * @param toRow destination row as integer
     * @param toCol destination column as integer
     */
    public ChessMove(int fromRow, int fromCol, int toRow, int toCol) {
        this.fromCol = fromCol;
        this.fromRow = fromRow;
        this.toRow = toRow;
        this.toCol = toCol;
    }

    /**
     * Deep-copy constructor
     * @param m ChessMove to clone
     */
    public ChessMove(ChessMove m) {
        this.fromCol = m.fromCol;
        this.fromRow = m.fromRow;
        this.toCol = m.toCol;
        this.toRow = m.toRow;
        if (m.enemyCaptured != null) {
            if (m.enemyCaptured instanceof Pawn)
                this.enemyCaptured = new Pawn((Pawn) m.enemyCaptured);
            if (m.enemyCaptured instanceof Bishop)
                this.enemyCaptured = new Bishop((Bishop) m.enemyCaptured);
            if (m.enemyCaptured instanceof King)
                this.enemyCaptured = new King((King) m.enemyCaptured);
            if (m.enemyCaptured instanceof Knight)
                this.enemyCaptured = new Knight((Knight) m.enemyCaptured);
            if (m.enemyCaptured instanceof Queen)
                this.enemyCaptured = new Queen((Queen) m.enemyCaptured);
            if (m.enemyCaptured instanceof Rook)
                this.enemyCaptured = new Rook((Rook) m.enemyCaptured);
        }
    }

    /**
     * Ex: "from a2 to a4"
     * @return this move as text
     */
    @Override
    public String toString() {
        char fcol = '!';
        char tcol = '!';
        for (int x = 0; x < COLUMNS.length; x++) {
            if (x == fromCol)
                fcol = COLUMNS[x];
            if (x == toCol)
                tcol = COLUMNS[x];
        }
        return "from " + fcol + (8 - fromRow)
                + " to " + tcol + (8 - toRow);
    }
}
