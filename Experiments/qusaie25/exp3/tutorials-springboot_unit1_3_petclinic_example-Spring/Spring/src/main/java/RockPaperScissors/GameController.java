package RockPaperScissors;

import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

    String user1;

    String user2;

    String winner;


    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String noInputGame() {

        return "ROCK POUNDS OUT FIRE, CRUSHES SCISSORS & SPONGE.\n" +
                "FIRE MELTS SCISSORS, BURNS PAPER & SPONGE.\n" +
                "SCISSORS SWISH THROUGH AIR, CUT PAPER & SPONGE.\n" +
                "SPONGE SOAKS PAPER, USES AIR POCKETS, ABSORBS WATER.\n" +
                "PAPER FANS AIR, COVERS ROCK, FLOATS ON WATER.\n" +
                "AIR BLOWS OUT FIRE, ERODES ROCK, EVAPORATES WATER.\n" +
                "WATER ERODES ROCK, PUTS OUT FIRE, RUSTS SCISSORS.\n" +
                "user 1 enter your choice followed by user 2 with /enter";
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{gameInput}")
    public String userInput(@PathVariable String gameInput){
        if(user1 == null){
            user1 = gameInput;
            return "user1 has made their choice";
        } else {
            user2 = gameInput;
            winner = GameRules.getWinner(user1, user2);
            return "user2 has made their choice, use /winner to see who won!";
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/winner")
    public String winner(){
        if(user1 != null && user2 != null){
            return "the winner is" + winner + "user /reset to play again!";
        } else {
            return "not yet, enter your choices!!!";
        }
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/reset")
    public String userInput(){
        user1 = null;
        user2 = null;
        return "ROCK POUNDS OUT FIRE, CRUSHES SCISSORS & SPONGE.\n" +
                "FIRE MELTS SCISSORS, BURNS PAPER & SPONGE.\n" +
                "SCISSORS SWISH THROUGH AIR, CUT PAPER & SPONGE.\n" +
                "SPONGE SOAKS PAPER, USES AIR POCKETS, ABSORBS WATER.\n" +
                "PAPER FANS AIR, COVERS ROCK, FLOATS ON WATER.\n" +
                "AIR BLOWS OUT FIRE, ERODES ROCK, EVAPORATES WATER.\n" +
                "WATER ERODES ROCK, PUTS OUT FIRE, RUSTS SCISSORS.\n" +
                "user 1 enter your choice followed by user 2 with /enter";
    }
}
