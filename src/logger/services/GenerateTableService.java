package logger.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateTableService {
    private static final StringBuilder tableStringBuilder = new StringBuilder();
    private static final char SYMBOL_JOIN = '+';
    private static final char SYMBOL_VER_SPLIT = '|';
    private static final char SYMBOL_HOR_SPLIT = '-';
    private static final char NEW_LINE = '\n';
    private static final int PADDING_SIZE = 2;
    private static Map<Integer, Integer> colMaxWidthMap;

    private GenerateTableService() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * @param headers list of table headers
     * @param rows    list of table rows
     * @return string representation of table
     */
    public static String execute(List<String> headers, List<List<String>> rows) {
        colMaxWidthMap = getColMaxWidthMap(headers, rows);

        addRowSeparator(headers.size());
        tableStringBuilder.append(NEW_LINE);

        for (int headerIdx = 0; headerIdx < headers.size(); headerIdx++) {
            fillCell(headers.get(headerIdx), headerIdx, true);
        }
        tableStringBuilder.append(NEW_LINE);
        addRowSeparator(headers.size());

        for (List<String> row : rows) {
            tableStringBuilder.append(NEW_LINE);

            for (int cellIndex = 0; cellIndex < row.size(); cellIndex++) {
                fillCell(row.get(cellIndex), cellIndex, false);
            }
        }

        tableStringBuilder.append(NEW_LINE);
        addRowSeparator(headers.size());

        return tableStringBuilder.toString();
    }

    /**
     * @param headers list of headers
     * @param rows    list of rows
     * @return mapping of column index to max width of the column
     */
    private static Map<Integer, Integer> getColMaxWidthMap(List<String> headers, List<List<String>> rows) {
        Map<Integer, Integer> colMaxWidthMap = new HashMap<>();

        for (int colIdx = 0; colIdx < headers.size(); colIdx++) {
            colMaxWidthMap.put(colIdx, headers.get(colIdx).length());
        }
        for (List<String> row : rows) {
            for (int colIdx = 0; colIdx < row.size(); colIdx++) {
                colMaxWidthMap.put(colIdx, Math.max(colMaxWidthMap.get(colIdx), row.get(colIdx).length()));
            }
        }

        colMaxWidthMap.forEach((key, value) -> {
            if (value % 2 != 0) {
                colMaxWidthMap.put(key, value + 1);
            }
        });

        return colMaxWidthMap;
    }

    /**
     * Add row separator to string builder i.e. '+-----+-----+'
     *
     * @param columnsCount number of columns
     */
    private static void addRowSeparator(int columnsCount) {
        for (int i = 0; i < columnsCount; i++) {
            if (i == 0) {
                tableStringBuilder.append(SYMBOL_JOIN);
            }

            tableStringBuilder.append(String.valueOf(SYMBOL_HOR_SPLIT).repeat(colMaxWidthMap.get(i) + PADDING_SIZE * 2));

            tableStringBuilder.append(SYMBOL_JOIN);
        }
    }

    /**
     * Fill table cell with padding and content
     *
     * @param cellContent cell content
     * @param colIdx      column index
     */
    private static void fillCell(String cellContent, int colIdx, boolean isHeader) {
        if (colIdx == 0) {
            tableStringBuilder.append(SYMBOL_VER_SPLIT);
        }

        int cellPaddingSize = getCellPaddingSize(colIdx, cellContent.length());
        addPadding(cellPaddingSize);

        if (isHeader) {
            tableStringBuilder.append(ColorStringService.color(cellContent, ColorStringService.BLACK_BOLD));
        } else {
            tableStringBuilder.append(cellContent);
        }

        if (cellContent.length() % 2 != 0) {
            tableStringBuilder.append(' ');
        }
        addPadding(cellPaddingSize);

        tableStringBuilder.append(SYMBOL_VER_SPLIT);
    }

    /**
     * Get current cell new padding size if cell's content is smaller than max cell width
     *
     * @param colIdx      column index
     * @param entryLength cell content length
     * @return new padding size
     */
    private static int getCellPaddingSize(int colIdx, int entryLength) {
        if (entryLength % 2 != 0) {
            entryLength++;
        }

        int newPaddingSize = PADDING_SIZE;
        if (entryLength < colMaxWidthMap.get(colIdx)) {
            newPaddingSize += (colMaxWidthMap.get(colIdx) - entryLength) / 2;
        }

        return newPaddingSize;
    }

    /**
     * Add empty spaces to string builder
     *
     * @param count number of empty spaces
     */
    private static void addPadding(int count) {
        tableStringBuilder.append(" ".repeat(count));
    }
}