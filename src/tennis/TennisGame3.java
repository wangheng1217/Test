package tennis;

public class TennisGame3 implements TennisGame {
    
    private int score1;
    private int score2;
    private String p1N;
    private String p2N;
    
    private String[] scores = {"Love", "Fifteen", "Thirty", "Forty"};

    public TennisGame3(String p1N, String p2N) {
        this.p1N = p1N;
        this.p2N = p2N;
    }
    
    public String getScore() {
        String score;
        if (score1 <= 3 && score2 <=3 && score1+score2 != 6) {
            score = scores[score1] + (score1 == score2 ? "-All" : "-"+scores[score2]);
        } else if (score1 == score2) {
            score = "Deuce";
        } else {
            score = (Math.abs(score1 - score2) > 1 ? "Win for " : "Advantage ") + (score1 > score2 ? p1N : p2N);
        }
        return score;
    }

/*
    public String getScore2() {
        String s;
        if (p1 < 4 && p2 < 4 && !(p1 + p2 == 6)) {
            String[] p = new String[]{"Love", "Fifteen", "Thirty", "Forty"}; 
            s = p[p1];
            return (p1 == p2) ? s + "-All" : s + "-" + p[p2];
        } else {
            if (p1 == p2)
                return "Deuce";
            s = p1 > p2 ? p1N : p2N;
            return ((p1-p2)*(p1-p2) == 1) ? "Advantage " + s : "Win for " + s;
        }
    }
*/    
    public void wonPoint(String playerName) {
        if (playerName == "player1")
            this.score1 += 1;
        else
            this.score2 += 1;
        
    }

}