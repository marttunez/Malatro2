package cl.uchile.dcc
package joker

import score.Score
import hand.pokerhands._
import card.suit._
import card.range.rangeclassification._
import munit.FunSuite

class JokerTest extends FunSuite {

  test("Joker base implementation should return score unchanged for all hooks!!") {
    val joker: Joker = new GreedyJoker()
    val initialScore = new Score(50, 2)

    val dummyHand = new HighCard()
    val dummySuit = Heart
    val dummyClassification = Figure

    val s1 = joker.scoreFlush(initialScore, dummyHand)
    val s2 = joker.scoreThreeOfAKind(initialScore, dummyHand)
    val s3 = joker.scoreHighCard(initialScore, dummyHand)
    val s4 = joker.scorePair(initialScore, dummyHand)
    val s5 = joker.scoreStraightFlush(initialScore, dummyHand)
    val s6 = joker.scoreClover(initialScore, dummySuit)
    val s7 = joker.scoreDiamond(initialScore, dummySuit)
    val s8 = joker.scoreHeart(initialScore, dummySuit)
    val s9 = joker.scorePica(initialScore, dummySuit)
    val s10 = joker.scoreFigures(initialScore, dummyClassification)
    val s11 = joker.scorePairCard(initialScore, dummyClassification)
    val s12 = joker.scoreOddCard(initialScore, dummyClassification)

    assertEquals(s1.calc, 100)
    assertEquals(s2.calc, 100)
    assertEquals(s3.calc, 100)
    assertEquals(s4.calc, 100)
    assertEquals(s5.calc, 100)
    assertEquals(s6.calc, 100)
    assertEquals(s7.calc, 100)
    assertEquals(s8.calc, 100)
    assertEquals(s9.calc, 100)
    assertEquals(s10.calc, 100)
    assertEquals(s11.calc, 100)
    assertEquals(s12.calc, 100)
  }
}