package sample;

class CellsArray {

    private Cell[][] cellsArray;

    CellsArray(int ROW, int COL, Statement statement) {
        cellsArray = new Cell[ROW][COL];
        for (int x = 0; x < ROW; x++)
            for (int y = 0; y < COL; y++)
                cellsArray[x][y] = new Cell(x, y, statement);
    }

    Cell getCell (int x, int y) {
        if (Properties.notOut(x, y))
            return cellsArray[x][y];
        return null;
    }

    void set(int x, int y, Statement statement) {
        if (Properties.notOut(x, y))
            cellsArray[x][y].setStatement(statement);

    }
    public Cell[][] getCellsArray() {
        return cellsArray;
    }
}
