package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {

        items.forEach { item ->
            updateQualityPerItem(item)
        }
    }

    private fun updateQualityPerItem(item: Item) {

        if (item.isSulfuras()) return

        if (item.isAgedBrie()) {
            item.increaseQualityBy(1)
            decreaseSellIn(item)
            return
        }

        if (item.isBackStagePass()) {
            item.increaseQualityBy(1)
            if (item.sellIn <= 10) {
                item.increaseQualityBy(1)
            }
            if (item.sellIn <= 5) {
                item.increaseQualityBy(1)
            }
            if (item.sellIn <= 0) {
                item.quality = 0
            }
            return
        }

        decreaseQuality(item)
        decreaseSellIn(item)

        if (item.sellIn < 0) {
            decreaseQuality(item)
        }
    }

    private fun decreaseSellIn(item: Item) {
        item.sellIn--
    }

    private fun Item.increaseQualityBy(delta: Int) {
        quality += delta
        quality = quality.coerceAtMost(50)
    }

    private fun decreaseQuality(item: Item) {
        if (item.quality > 0) {
            item.quality--
        }
    }


    private fun Item.isAgedBrie() = name.contains(AGED_BRIE, ignoreCase = true)
    private fun Item.isSulfuras() = name.contains(SULFURAS, ignoreCase = true)
    private fun Item.isBackStagePass() = name.contains(BACKSTAGE_PASS, ignoreCase = true)

    companion object {
        private const val AGED_BRIE = "Aged Brie"
        private const val SULFURAS = "Sulfuras"
        private const val BACKSTAGE_PASS = "Backstage pass"
    }

}