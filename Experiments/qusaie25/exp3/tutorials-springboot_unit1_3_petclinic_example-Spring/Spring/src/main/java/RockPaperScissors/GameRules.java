package RockPaperScissors;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
public class GameRules {
    static HashMap<String, String[]> gameRules = new HashMap<String, String[]>();

    public static void initializeGame(){
        gameRules.put("rock", new String[]{"fire","scissors", "sponge"});
        gameRules.put("fire", new String[]{"scissors","paper", "sponge"});
        gameRules.put("scissors", new String[]{"air","paper", "sponge"});
        gameRules.put("sponge", new String[]{"paper","air", "water"});
        gameRules.put("paper", new String[]{"air","rock", "water"});
        gameRules.put("air", new String[]{"water","fire", "scissors"});
        gameRules.put("water", new String[]{"rock","fire", "scissors"});
    }

    public static String getWinner(String a, String b){
        initializeGame();
        if(a.equals(b)){
            return "No winner you chose the same thing!";
        } else {
            for (String comp : gameRules.get(a)) {
                if (b.equals(comp)) {
                    return "player1";
                }
            }
            return "player2";
        }
    }

}
