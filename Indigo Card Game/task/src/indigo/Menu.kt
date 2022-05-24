package indigo

class Menu {
    fun mainMenu() {
        val playerStart = startFirst()

        Deck.shuffle()
        Deck.placeCardsToTable(4)

        val player = Human("Player", playerStart)
        val computer = Computer("Computer", !playerStart)

        if (playerStart) {
            while (Deck.cardsInGame.size > 0 || player.cardsInHand.size > 0) {
                player.makeMove()
                computer.makeMove()
            }
        } else {
            while (Deck.cardsInGame.size > 0 || computer.cardsInHand.size > 0) {
                computer.makeMove()
                player.makeMove()
            }
        }
        if (Deck.cardsOnTable.size > 0) {
            println("${Deck.cardsOnTable.size} cards on the table, and the top card is ${Deck.cardsOnTable[Deck.cardsOnTable.size - 1]}")
        } else {
            println("No cards on the table")
        }
        player.takeLastCards()
        player.printTotalScore()
    }

    private fun startFirst(): Boolean {
        println("Play first?")
        return when (readLine()!!.lowercase()) {
            "yes" -> true
            "no" -> false
            else -> startFirst()
        }
    }
}