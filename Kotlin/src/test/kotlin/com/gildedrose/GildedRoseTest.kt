package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    @Test
    fun `Given generic item, When on updateQuality(), correct name of item is returned`() {
        val items = arrayOf<Item>(Item("item", 0, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals("item", app.items[0].name)

    }

    @Test
    fun `Given item with quality 2, When on updateQuality(), Then quality decreases by 1`() {
        val items = arrayOf(Item("item", 1, 2))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(1, app.items[0].quality)
    }

    @Test
    fun `Given item with sellin 1, When on updateQuality(), Then sellin decreases by 1`() {
        val items = arrayOf(Item("item", 1, 2))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.items[0].sellIn)
    }

    @Test
    fun `Given item with quality 0, When on updateQuality(), Then quality cant be less then 0`() {
        val items = arrayOf(Item("item", 5, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.items[0].quality)
    }

    @Test
    fun `Given item with sell date passed, When on updateQuality(), Then quality decreases twice as fast`() {
        val items = arrayOf(Item("item", -2, 4))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(2, app.items[0].quality)
    }

    @Test
    fun `Given item, When on updateQuality(), Then quality cant be more than 50`() {
        val items = arrayOf(Item("Aged Brie", 0, 50))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(50, app.items[0].quality)
    }

    @Test
    fun `Given Aged Brie, When on updateQuality(), Then Aged Brie increases in quality the older it gets`() {
        val items = arrayOf(Item("Aged Brie", 2, 2))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(3, app.items[0].quality)
    }

    @Test
    fun `Given Sulfuras, When on updateQuality(), Then Sulfuras never decreases in quality `() {
        val items = arrayOf(Item("Sulfuras, Hand of Ragnaros", 0, 80))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(80, app.items[0].quality)
    }

    @Test
    fun `Given Sulfuras, When on updateQuality(), Then Sulfuras doesnt need to be sold (sellin remains at 0)`() {
        val items = arrayOf(Item("Sulfuras, Hand of Ragnaros", 0, 80))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.items[0].sellIn)
    }

    @Test
    fun `Given Backstage passes, When on updateQuality(), Then quality increases by 2 when there are 10 days or less`() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 9, 12))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(14, app.items[0].quality)
    }

    @Test
    fun `Given Backstage passes, When on updateQuality(), Then quality increases by 3 when there are 5 days or less`() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 5, 12))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(15, app.items[0].quality)
    }

    @Test
    fun `Given Backstage passes, When on updateQuality(), Then quality drops to 0 after the concert`() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 0, 45))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.items[0].quality)
    }

    @Test
    fun `Given Conjured item, When on updateQuality(), Then quality drops twice as fast`() {
        val items = arrayOf(Item("Conjured", 2, 8))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(6, app.items[0].quality)
    }

}


