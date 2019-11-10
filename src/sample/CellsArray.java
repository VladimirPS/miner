package sample;

class CellsArray {
    public Cell[][] getCellsArray() {
        return cellsArray;
    }

    private Cell[][] cellsArray;

    CellsArray(int ROW, int COL) {
        cellsArray = new Cell[ROW][COL];
            for (int x = 0; x<ROW;x++)
                for (int y = 0 ; y < COL; y++)
                    cellsArray[x][y] = new Cell(x,y,Statement.CLOSED);
    }

    Cell getCell(Cell cell) {
        if (Properties.notOut(cell.x, cell.y))
            return cellsArray[cell.x][cell.y];
        return null;
    }

    void set(int x, int y, Cell cell) {
        if (Properties.notOut(cell.x,cell.y))
            cellsArray[x][y] = cell;

    }
}
