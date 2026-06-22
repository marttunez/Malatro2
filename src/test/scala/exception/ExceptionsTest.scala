package cl.uchile.dcc
package exception

import hand.Hand
import card.Card
import card.range.*
import card.suit.*
import card.range.numbers.*
import game.Game
import joker.*

import cl.uchile.dcc.exception.exceptions.*
import munit.FunSuite

class ExceptionsTest extends FunSuite {


  test("Hand should throw HandFullException when attempting to exceed 8 cards") {
    val hand = new Hand()
    val card = new Card(Two, Heart)

    var i = 0
    while (i < 8) {
      hand.addCard(card)
      i += 1
    }

    intercept[HandFullException] {
      hand.addCard(card)
    }
  }

  test("Hand should throw JokerFullException when attempting to exceed 2 jokers") {
    val hand = new Hand()
    val joker = new EvenSteven()

    hand.addJoker(joker)
    hand.addJoker(joker)

    intercept[JokerFullException] {
      hand.addJoker(joker)
    }
  }

  test("Game should throw InvalidActionException if index list is empty or exceeds 5 cards") {
    val hand = new Hand()
    val card = new Card(Two, Heart)

    var i = 0
    while (i < 6) {
      hand.addCard(card)
      i += 1
    }
    val game = new Game(hand)

    intercept[InvalidActionException] {
      game.playHand(List())
    }
    
    intercept[InvalidActionException] {
      game.playHand(List(0, i, 2, 3, 4, 5))
    }
  }
  

  test("Game should throw InvalidIndexException if an index is out of hand bounds") {
    val hand = new Hand()
    val card = new Card(Two, Heart)
    hand.addCard(card)
    val game = new Game(hand)
    
    intercept[InvalidIndexException] {
      game.playHand(List(1))
    }
    
    intercept[InvalidIndexException] {
      game.playHand(List(-1))
    }
  }

  test("Game should safely process unordered index selections without throwing out-of-bounds errors") {
    val hand = new Hand()
    hand.addCard(new Card(Two, Heart))    
    hand.addCard(new Card(Three, Heart)) 
    hand.addCard(new Card(Four, Heart))   
    val game = new Game(hand)
    val played = game.playHand(List(2, 0))
    assertEquals(played.length, 2)
    assertEquals(hand.getCardsLen, 1)
  }

  test("Game should throw ActionLimitException when attempting to play more than 3 hands") {
    val hand = new Hand()
    val card = new Card(Two, Heart)

    var i = 0
    while (i < 6) {
      hand.addCard(card)
      i += 1
    }

    val game = new Game(hand)

    game.playHand(List(0))
    game.playHand(List(0))
    game.playHand(List(0))

    intercept[ActionLimitException] {
      game.playHand(List(0))
    }
  }

  test("Game should throw ActionLimitException when attempting to discard more than 3 times") {
    val hand = new Hand()
    val card = new Card(Two, Heart)

    var i = 0
    while (i < 6) {
      hand.addCard(card)
      i += 1
    }

    val game = new Game(hand)

    game.discardHand(List(0))
    game.discardHand(List(0))
    game.discardHand(List(0))

    intercept[ActionLimitException] {
      game.discardHand(List(0))
    }
  }

  test("All domain exceptions should correctly inherit from MalatroException") {
    assert(new ActionLimitException("").isInstanceOf[MalatroException])
    assert(new HandFullException("").isInstanceOf[MalatroException])
    assert(new InvalidActionException("").isInstanceOf[MalatroException])
    assert(new InvalidIndexException("").isInstanceOf[MalatroException])
    assert(new JokerFullException("").isInstanceOf[MalatroException])
  }

  test("Game.resetRound should clear counters allowing to play and discard again") {
    val hand = new Hand()
    val card = new Card(Two, Heart)
    var i = 0
    while (i < 6) {
      hand.addCard(card)
      i += 1
    }
    val game = new Game(hand)
    
    game.playHand(List(0))
    game.playHand(List(0))
    game.playHand(List(0))
    
    intercept[ActionLimitException] {
      game.playHand(List(0))
    }

    game.resetRound()

    val playedAfterReset = game.playHand(List(0))
    assertEquals(playedAfterReset.length, 1)
  }

  test("Hand should throw InvalidIndexException when removing card or joker out of bounds") {
    val hand = new Hand()

    intercept[InvalidIndexException] {
      hand.removeCard(0)
    }
    intercept[InvalidIndexException] {
      hand.removeJoker(0)
    }

    intercept[InvalidIndexException] {
      hand.removeCard(-5)
    }
  }
}