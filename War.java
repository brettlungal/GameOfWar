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
                //TODO implement this shitty feature
            }else{
                //only remaining case is that p2 wins
                player2Heap.add(p1Card);
                player2Heap.add(p2Card);
                System.out.println("Player 2 wins both cards!");
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