package wangheng.leetcode;

public class ZigzagConversionSolution {

    public String convert(String s, int nRows) {
        if (nRows == 1)
            return s;
        int blockSize = 2 * nRows - 2;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < s.length() + blockSize; j = j + blockSize) {
                if (i == 0) {
                    append(sb, getCharAt(s, j));
                } else if (i == nRows - 1) {
                    append(sb, getCharAt(s, j + nRows - 1));
                } else {
                    append(sb, getCharAt(s, j - i));
                    append(sb, getCharAt(s, j + i));
                }
            }
        }
        return sb.toString();
    }

    private Character getCharAt(String s, int index) {
        if (index >= 0 && index < s.length())
            return s.charAt(index);
        else
            return null;
    }

    private void append(StringBuffer sb, Character c) {
        if (c != null) {
            sb.append(c);
        }
    }

    /*
    public String convert(String s, int nRows) {
        if (nRows == 1 || nRows >= s.length())
            return s;

        int blockSize = 2 * nRows - 2;
        int blockWidth = nRows - 1;

        int nColumns = ((s.length() - 1) / blockSize + 1) * blockWidth;
        Character[][] p = new Character[nRows][nColumns];

        for (int i = 0; i < s.length(); i++) {
            int nBlock = i / blockSize;
            int nIndex = i % blockSize;
            int rowIndex, columnIndex;
            if (nIndex < nRows) {
                rowIndex = nIndex;
                columnIndex = nBlock * blockWidth;
            } else {
                rowIndex = nRows - 1 - (nIndex - (nRows - 1));
                columnIndex = nBlock * blockWidth + (nIndex - (nRows - 1));
            }
            p[rowIndex][columnIndex] = s.charAt(i);
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++) {
                Character c = p[i][j];
                if (c != null) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
    */
}