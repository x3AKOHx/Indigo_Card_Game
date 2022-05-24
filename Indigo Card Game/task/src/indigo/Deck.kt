package indigo

object Deck {
    private val cards = listOf("A♠", "2♠", "3♠", "4♠", "5♠", "6♠", "7♠", "8♠", "9♠", "10♠", "J♠", "Q♠", "K♠",
        "A♥", "2♥", "3♥", "4♥", "5♥", "6♥", "7♥", "8♥", "9♥", "10♥", "J♥", "Q♥", "K♥",
        "A♦", "2♦", "3♦", "4♦", "5♦", "6♦", "7♦", "8♦", "9♦", "10♦", "J♦", "Q♦", "K♦",
        "A♣", "2♣", "3♣", "4♣", "5♣", "6♣", "7♣", "8♣", "9♣", "10♣", "J♣", "Q♣", "K♣")

    var cardsInGame = cards.toMutableList()
    var cardsOnTable = mutableListOf<String>()

    fun shuffle() {
        cardsInGame.shuffle()
    }

    fun get() {
        println("Number of cards:")
        val number: Int
        try {
            number = readLine()!!.toInt()
            if (number in 1..52) {
                if (number > cardsInGame.size) {
                    println("The remaining cards are insufficient to meet the request.")
                } else {
                    val topDeck = cardsInGame.subList(0, number)
                    println(topDeck.joinToString(" "))
                    cardsInGame.removeAll(topDeck)
                }
            } else {
                println("Invalid number of cards.")
            }
        } catch (e: Exception) {
            println("Invalid number of cards.")
        }
    }

    fun get(number: Int): MutableList<String> {
        val topDeck = mutableListOf<String>()
        for (i in 0..number) {
            topDeck.add(cardsInGame[0])
            cardsInGame.removeAt(0)
        }
        return topDeck
    }

    fun getCardsFromTable(): MutableList<String> {
        val cards = mutableListOf<String>()
        for (i in 0 until cardsOnTable.size) {
            cards.add(cardsOnTable[0])
            cardsOnTable.removeAt(0)
        }
        return cards
    }

    fun placeCardsToTable(number: Int) {
        val topDeck = cardsInGame.subList(0, number)
        println("Initial cards on the table: " + topDeck.joinToString(" "))
        println()
        cardsOnTable.addAll(topDeck)
        cardsInGame.removeAll(cardsInGame.subList(0, number))
    }

    fun cardsOnTable() {
        if (cardsOnTable.size == 0) {
            println("No cards on the table")
        } else {
            println("${cardsOnTable.size} cards on the table, and the top card is ${cardsOnTable[cardsOnTable.size - 1]}")
        }
    }
}