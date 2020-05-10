import java.util.*;
class War {
    
    static Stack<Card> player1 = new Stack<Card>();
    static Queue<Card> player1Heap = new ArrayDeque<Card>();

    static Stack<Card> player2 = new Stack<Card>();
    static Queue<Card> player2Heap = new ArrayDeque<Card>();

    static Deck gameDeck = new Deck();
    static boolean gameOver = false;

    public static void main( String[] args ){
        //start war
        gameDeck.shuffle();
        deal();
        beginWar();
    }

    public static void beginWar(){

        while ( !gameOver ){

            refillPlayer1();
            refillPlayer2();
            if ( gameOver ){
                return;
            }

            Card p1Card = player1.pop();
            Card p2Card = player2.pop();
            System.out.println("Player 1: "+p1Card);
            System.out.println("Player 2: "+p2Card);
    
            if ( p1Card.getValue() > p2Card.getValue() ){
                //p1 wins
                player1Heap.add(p2Card);
                player1Heap.add(p1Card);    //dont forget to take your own card back
                System.out.println("Player 1 wins both cards!");
            }else if ( p1Card.getValue() == p2Card.getValue() ){
                //war, not gonna touch this case now
                System.out.println("War!");
                itsWar(p1Card,p2Card);
            }else{
                //only remaining case is that p2 wins
                player2Heap.add(p1Card);
                player2Heap.add(p2Card);
                System.out.println("Player 2 wins both cards!");
            }

        }
    }

    public static void itsWar( Card p1Card , Card p2Card ){
        Stack<Card> player1Bet = new Stack<Card>();
        Stack<Card> player2Bet = new Stack<Card>();

        boolean cardsEqual = true;
        while ( cardsEqual ){

            if ( player1.size() < 2 ){
                //not enough cards for war, refill deck
                refillPlayer1();
                //check if we got more cards
                if ( player1.size() < 2 ){
                    //cant participate
                    System.out.println("Player 1 cannot participate in war, PLAYER 2 WINS!!");
                    gameOver=true;
                    return;
                }
            }
            
            if ( player2.size() < 2 ){
                //not enough cards for war, refill deck
                refillPlayer2();
                if ( player2.size() < 2 ){
                    //cant participate
                    System.out.println("Player 2 cannot participate in war, PLAYER 1 WINS!!");
                    gameOver=true;
                    return;
                }
            }
            
            //if we made it this far, both players have enough cards for a war
            //get the bet cards
            System.out.println("Player 1 bets: "+player1.peek());
            player1Bet.push(player1.pop());
            System.out.println("Player 2 bets: "+player2.peek());
            player2Bet.push(player2.pop());

            //get the cards for comparison
            System.out.println("Player 1: "+player1.peek());
            player1Bet.push(player1.pop());
            System.out.println("Player 2: "+player2.peek());
            player2Bet.push(player2.pop());
            //lets determine if we need to do this again after were done
            if ( player1Bet.peek().getValue() > player2Bet.peek().getValue()){
                //player 1 wins, gets all cards
                System.out.println("Player 1 wins the war!");
                cardsEqual = false;
                while ( !player1Bet.isEmpty() ){
                    player1Heap.add(player1Bet.pop());
                    player1Heap.add(player2Bet.pop()); //theoretically player1Bet and player2Bet should be same size
                }
                player1Heap.add(p1Card);
                player1Heap.add(p2Card);
            }else if ( player1Bet.peek().getValue() < player2Bet.peek().getValue() ){
                //player 2 wins, gets all cards
                System.out.println("Player 2 wins the war!");
                cardsEqual = false;
                while ( !player2Bet.isEmpty() ){
                    player2Heap.add(player1Bet.pop());
                    player2Heap.add(player2Bet.pop()); //theoretically player1Bet and player2Bet should be same size
                }
                player2Heap.add(p1Card);
                player2Heap.add(p2Card);
            }else{
                //they are equal, gotta do it again
                System.out.println("War again!!");
                //nothing needs to be done here
            }

        }//while
    }

    public static void refillPlayer1(){

        if ( player1.isEmpty() ){
            //refill hand from heap
            System.out.println("Player 1 hand is empty");
            if ( !player1Heap.isEmpty() ){
                System.out.println("Refilling player 1 hand from heap...");
                while ( !player1Heap.isEmpty() ){
                    player1.push(player1Heap.remove());
                }
            } else {
                //p1 loses
                gameOver = true;
                System.out.println("PLAYER 2 WINS!!!!");
                return;
            }
        }
    }

    public static void refillPlayer2(){

        if ( player2.isEmpty()){
            System.out.println("Player 2 hand is empty");
            //refill hand from heap if not empty
            if ( !player2Heap.isEmpty() ){
                System.out.println("Refilling player 2 hand from heap...");
                while ( !player2Heap.isEmpty() ){
                    player2.push(player2Heap.remove());
                }
            }else{
                //p2 loses
                gameOver = true;
                System.out.println("PLAYER 1 WINS!!!!");
                return;
            }
        }

    }

    public static void deal(){
        Card[] deck = gameDeck.getDeck();
        for (int i = 0; i < 52; i++){
            if ( i%2 == 0 ){
                player1.push(deck[i]);
            }else{
                player2.push(deck[i]);
            }
        }
    }
}