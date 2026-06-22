package cl.uchile.dcc
package card

import card.range.numbers._
import card.range.figures._
import card.range.rangeclassification._
import card.suit._
import munit.FunSuite

class RangeAndSuitTest extends FunSuite {

  test("Numeric ranks should hold proper values and classifications") {
    // Test Ace
    assertEquals(Ace.Order, 1)
    assertEquals(Ace.Value, 11)
    assertEquals(Ace.Classification, Odd)

    // Test Two
    assertEquals(Two.Order, 2)
    assertEquals(Two.Value, 2)
    assertEquals(Two.Classification, NotOdd)

    // Test Five
    assertEquals(Five.Order, 5)
    assertEquals(Five.Value, 5)
    assertEquals(Five.Classification, Odd)

    // Test Ten
    assertEquals(Ten.Order, 10)
    assertEquals(Ten.Value, 10)
    assertEquals(Ten.Classification, NotOdd)
  }

  test("Figure ranks should constants return value 10 and Figure classification") {
    assertEquals(Jay.Order, 11)
    assertEquals(Jay.Value, 10)
    assertEquals(Jay.Classification, Figure)

    assertEquals(Queen.Order, 12)
    assertEquals(Queen.Value, 10)
    assertEquals(Queen.Classification, Figure)

    assertEquals(King.Order, 13)
    assertEquals(King.Value, 10)
    assertEquals(King.Classification, Figure)
  }

  test("Suit singletons objects should be distinct marker entities") {
    assert(Clover.isInstanceOf[Suit])
    assert(Diamond.isInstanceOf[Suit])
    assert(Heart.isInstanceOf[Suit])
    assert(Pica.isInstanceOf[Suit])
  }
}