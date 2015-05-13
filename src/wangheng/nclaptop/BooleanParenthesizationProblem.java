package wangheng.nclaptop;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/*
 * http://www.geeksforgeeks.org/dynamic-programming-set-37-boolean-parenthesization-problem/
 * 
 */
public class BooleanParenthesizationProblem {

    @Test
    public void test() {
        assertEquals(2, new BooleanParenthesizationProblem().count(new char[] {'T', 'F', 'T'}, new char[] {'^', '&'}));
        assertEquals(2, new BooleanParenthesizationProblem().count(new char[] {'T', 'F', 'F'}, new char[] {'^', '|'}));
        assertEquals(4, new BooleanParenthesizationProblem().count(new char[] {'T', 'T', 'F', 'T'}, new char[] {'|', '&', '^'}));
    }
    
    public int count(char[] symbol, char[] operator) {
        if (symbol.length == 0)
            return 0;

        int[][] t = new int[symbol.length][symbol.length];
        int[][] f = new int[symbol.length][symbol.length];

        for (int i = symbol.length - 1; i >= 0; i--) {
            for (int j = i; j < symbol.length; j++) {
                if (i == j) {
                    t[i][j] = (symbol[i] == 'T') ? 1 : 0;
                    f[i][j] = (symbol[i] == 'F') ? 1 : 0;
                } else {
                    int tC = 0, fC = 0;
                    for (int k = i; k < j; k++) {
                        int tt = t[i][k] * t[k + 1][j];
                        int tf = t[i][k] * f[k + 1][j];
                        int ft = f[i][k] * t[k + 1][j];
                        int ff = f[i][k] * f[k + 1][j];

                        switch (operator[k]) {
                        case '&':
                            tC = tC + tt;
                            fC = fC + tf + ft + ff;
                            break;
                        case '^':
                            tC = tC + tf + ft;
                            fC = fC + tt + ff;
                            break;
                        case '|':
                            tC = tC + tt + tf + ft;
                            fC = fC + ff;
                            break;
                        default:
                            break;
                        }
                    }
                    t[i][j] = tC;
                    f[i][j] = fC;
                }
            }
        }

        return t[0][symbol.length - 1];
    }
    
    char[] symbol;
    char[] operator;
    
    public int count2(char[] symbol, char[] operator) {
        this.symbol = symbol;
        this.operator = operator;
        return count(0, symbol.length-1, true);
    }
    
    public int count(int i, int j, boolean target) {
        if (i > j) return 0;
        if (i == j) return (target ^ (symbol[i] == 'T')) ? 0 : 1;
        
        int c = 0;
        for (int k = i; k < j; k++) {
            char oper = operator[k];
            
            int tt = count(i, k, true) * count(k+1, j, true);
            int tf = count(i, k, true) * count(k+1, j, false);
            int ft = count(i, k, false) * count(k+1, j, true);
            int ff = count(i, k, false) * count(k+1, j, false);

            switch (oper) {
            case '&':
                if (target == true) c = c + tt;
                else c = tf + ft + ff;
                break;
            case '^':
                if (target == true) c = c + tf + ft;
                else c = c + tt + ff;
                break;
            case '|':
                if (target == true) c = c + tt + tf + ft;
                else c = c + ff;
                break;
            default:
                break;
            }
        }
        
        return c;
    }
}
