import java.util.Random;
class Deck {
    
    private Card[] theDeck;
    String[] suits = {"Spades","Hearts","Clubs","Diamonds"};
    public Deck(){
        theDeck = new Card[52];
        int cardCount = 0;
        for(String suit:suits){
            for ( int i = 2; i<=14;i++){
                theDeck[cardCount] = new Card(i,suit);
                cardCount++;
            }
        }
    }

    public void shuffle(){
        //get random indices
        Random rand = new Random();
        //perform 52 random swaps
        for ( int i =0; i<52;i++){
            int randPos1 = rand.nextInt(52);
            int randPos2 = rand.nextInt(52);
            //swap positions
            Card temp = theDeck[randPos1];
            theDeck[randPos1] = theDeck[randPos2];
            theDeck[randPos2] = temp;
        }
        
    }

    public Card[] getDeck(){
        return theDeck;
    }

    public String toString(){
        String toReturn = "";

        for ( int i = 0 ;i < 52; i++ ){
            System.out.println(theDeck[i]);
        }

        return toReturn;
    }

}