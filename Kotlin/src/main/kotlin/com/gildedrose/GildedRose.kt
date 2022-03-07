package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {

        items.forEach { item ->
            updateQualityPerItem(item)
        }
    }

    private fun updateQualityPerItem(item: Item) {
        if (!item.isAgedBrie() && !(item.isBackStagePass())) {
            if (item.quality > 0) {
                if (!(item.isSulfuras())) {
                    item.quality = item.quality - 1
                }
            }
        } else {
            if (item.quality < 50) {
                item.quality = item.quality + 1

                if ((item.isBackStagePass())) {
                    if (item.sellIn < 11) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1
                        }
                    }

                    if (item.sellIn < 6) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1
                        }
                    }
                }
            }
        }

        if (!item.isSulfuras()) {
            item.sellIn = item.sellIn - 1
        }

        if (item.sellIn < 0) {
            if (!item.isAgedBrie()) {
                if (!item.isBackStagePass()) {
                    if (item.quality > 0) {
                        if (!(item.isSulfuras())) {
                            item.quality = item.quality - 1
                        }
                    }
                } else {
                    item.quality = item.quality - item.quality
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1
                }
            }
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