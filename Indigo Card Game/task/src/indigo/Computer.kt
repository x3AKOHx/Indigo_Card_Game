package indigo

class Computer(name: String, isFirst: Boolean): Player(name, isFirst) {
    override fun makeMove() {
        Deck.cardsOnTable()
        println(cardsInHand.joinToString(" "))
        val card = computerMove()
        println("Computer plays $card")
        Deck.cardsOnTable.add(card)
        cardsInHand.remove(card)
        tableCardsWinCheck()
        println()
        if (cardsInHand.size == 0 && Deck.cardsInGame.size > 5) takeCards()
    }

    private fun computerMove(): String {
        if (cardsInHand.size == 1) return cardsInHand[0]
        return if (Deck.cardsOnTable.size > 0) {
            val candidates = mutableListOf<String>()
            for (i in cardsInHand) {
                if (i.first() == Deck.cardsOnTable.last().first() || i.last() == Deck.cardsOnTable.last().last())
                    candidates.add(i)
            }
            if (candidates.size > 1) bestPick(candidates)
            else if (candidates.size == 1) candidates[0]
            else bestPick(cardsInHand)
        } else {
            bestPick(cardsInHand)
        }
    }

    private fun bestPick(list: MutableList<String>): String {
        val bestPick: String
        val peaks = mutableListOf<String>()
        val hearts = mutableListOf<String>()
        val clubs = mutableListOf<String>()
        val diamonds = mutableListOf<String>()

        for (i in list) {
            if (i.last() == '♠') peaks.add(i)
            if (i.last() == '♥') hearts.add(i)
            if (i.last() == '♣') clubs.add(i)
            if (i.last() == '♦') diamonds.add(i)
        }

        bestPick = if (peaks.size >= hearts.size && peaks.size >= clubs.size && peaks.size >= diamonds.size) {
            peaks.shuffle()
            peaks[0]
        } else if (hearts.size >= peaks.size && hearts.size >= clubs.size && hearts.size >= diamonds.size) {
            hearts.shuffle()
            hearts[0]
        } else if (clubs.size >= peaks.size && clubs.size >= hearts.size && clubs.size >= diamonds.size) {
            clubs.shuffle()
            clubs[0]
        } else  {
            diamonds.shuffle()
            diamonds[0]
        }
        return bestPick
    }
}