package indigo

import kotlin.system.exitProcess

val cardsWon = HashMap<String, MutableList<String>>()
var wonLast = ""

open class Player(private val name: String, private val isFirst: Boolean) {
    val cardsInHand = mutableListOf<String>()

    init {
        takeCards()
        cardsWon[name] = mutableListOf()
    }

    fun takeCards() {
        cardsInHand.addAll(Deck.get(5))
    }

    fun printCardsInHand() {
        print("Cards in hand: ")
        for (i in 1..cardsInHand.size) {
            print("$i)${cardsInHand[i - 1]} ")
        }
        println()
    }

    open fun countScore(_name: String): Int {
        if (cardsWon[_name] == null) return 0
        val cards = cardsWon[_name]
        var count = 0
        for (i in cards!!) {
            if (i.first() == '1' || i.first() == 'A' || i.first() == 'J' || i.first() == 'Q' || i.first() == 'K') count++
        }
        return count
    }

    private fun printScore() {
        println("$name wins cards")
        println("Score: Player ${countScore("Player")} - Computer ${countScore("Computer")}")
        println("Cards: Player ${cardsWon["Player"]!!.size} - Computer ${cardsWon["Computer"]!!.size}")
    }

    fun printTotalScore() {
        var playerScore = countScore("Player")
        var compScore = countScore("Computer")
        val playerCards = cardsWon["Player"]!!.size
        val compCards = cardsWon["Computer"]!!.size
        if (playerCards > compCards) playerScore += 3
        else if (playerCards < compCards) compScore += 3
        else {
            if (isFirst) playerScore += 3
            else compScore += 3
        }
        println("Score: Player $playerScore - Computer $compScore")
        println("Cards: Player $playerCards - Computer $compCards")
        println("Game Over")
    }

    fun tableCardsWinCheck() {
        if (Deck.cardsOnTable.size > 1) {
            val card = Deck.cardsOnTable.last()
            val previousCard = Deck.cardsOnTable[Deck.cardsOnTable.size - 2]
            if (card.first() == previousCard.first() || card.last() == previousCard.last()) {
                cardsWon[name]!!.addAll(Deck.getCardsFromTable())
                wonLast = name
                printScore()
            }
        }
    }

    fun chooseCard() {
        println("Choose a card to play (1-${cardsInHand.size}):")
        val number: Int
        try {
            val input = readLine()!!
            if (input == "exit") {
                print("Game Over")
                exitProcess(1)
            }
            number = input.toInt()
            if (number in 1..cardsInHand.size) {
                val card = cardsInHand[number - 1]
                Deck.cardsOnTable.add(card)
                cardsInHand.remove(card)
                tableCardsWinCheck()
            } else {
                chooseCard()
            }
        } catch (e: Exception) {
            chooseCard()
        }
    }

    open fun makeMove() {
    }

    fun takeLastCards() {
        cardsWon[wonLast]!!.addAll(Deck.getCardsFromTable())
    }
}