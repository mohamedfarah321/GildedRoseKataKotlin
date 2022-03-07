package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        items.forEach { item ->
            item.updateItem()
        }
    }

    private fun Item.updateItem() {
        if (isSulfuras()) return
        if (isAgedBrie()) return updateAgedBrie()
        if (isBackStagePass()) return updateBackstagePass()

        updateNormalItem()
    }

    private fun Item.updateAgedBrie() {
        decreaseSellIn()
        increaseQualityBy(1)
    }

    private fun Item.updateBackstagePass() {
        when (sellIn) {
            in Int.MIN_VALUE .. 0 -> quality = 0
            in 0..5 -> increaseQualityBy(3)
            in 6..10 -> increaseQualityBy(2)
            else -> increaseQualityBy(1)
        }
    }

    private fun Item.updateNormalItem() {
        decreaseSellIn()
        if (sellIn < 0) decreaseQualityBy(2) else decreaseQualityBy(1)
    }

    private fun Item.decreaseSellIn() {
        sellIn--
    }

    private fun Item.increaseQualityBy(delta: Int) {
        quality += delta
        quality = quality.coerceAtMost(50)
    }

    private fun Item.decreaseQualityBy(delta: Int) {
        quality -= delta
        quality = quality.coerceAtLeast(0)
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