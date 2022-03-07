package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {

        for (i in items.indices) {
            if (!(items[i].isAgedBrie()) && !(items[i].isBackStagePass())) {
                if (items[i].quality > 0) {
                    if (!(items[i].isSulfuras())) {
                        items[i].quality = items[i].quality - 1
                    }
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1

                    if ((items[i].isBackStagePass())) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1
                            }
                        }
                    }
                }
            }

            if (!(items[i]).isSulfuras()) {
                items[i].sellIn = items[i].sellIn - 1
            }

            if (items[i].sellIn < 0) {
                if (!(items[i]).isAgedBrie()) {
                    if (!(items[i].isBackStagePass())) {
                        if (items[i].quality > 0) {
                            if (!(items[i].isSulfuras())) {
                                items[i].quality = items[i].quality - 1
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1
                    }
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