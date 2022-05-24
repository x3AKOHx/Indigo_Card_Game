package indigo

class Human(name: String, isFirst: Boolean): Player(name, isFirst) {
    override fun makeMove() {
        Deck.cardsOnTable()
        printCardsInHand()
        chooseCard()
        println()
        if (cardsInHand.size == 0 && Deck.cardsInGame.size > 5) takeCards()
    }
}