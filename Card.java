class Card{

    private int value;
    private String suit;

    public Card(int value,String suit){
        this.value = value;
        this.suit = suit;
    }

    public int getValue(){
        return this.value;
    }

    public String toString(){
        String toReturn = "";
        switch (value){
            case 11: toReturn += "J";break;
            case 12: toReturn += "Q";break;
            case 13: toReturn += "K";break;
            case 14: toReturn += "A";break;
            default: toReturn += value+"";break;
            
        }
        if ( suit == "Diamonds" ){
            toReturn+=(char)'\u2666';
        }else if ( suit == "Spades" ){
            toReturn+=(char)'\u2660';
        }else if ( suit == "Clubs" ){
            toReturn+=(char)'\u2663';
        }else if ( suit == "Hearts" ){
            toReturn+=(char)'\u2764';
        }
        return toReturn;
    }

}