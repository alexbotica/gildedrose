package com.gildedrose;

interface Commodity
{
    public void updateQuality(Item item);
};

class GildedRose
{
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final String BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    public static final String AGED_BRIE = "Aged Brie";

    class SulfurasHandOfRagnaros implements Commodity
    {
        @Override
        public void updateQuality(Item item)
        {
            increaseQuality(item);
        }
    };

    class BackstagePasses implements Commodity
    {
        @Override
        public void updateQuality(Item item)
        {

        }
    };

    class AgedBrie implements Commodity
    {
        @Override
        public void updateQuality(Item item)
        {

        }
    };

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality()
    {
        for (Item item : items) 
        {
            Commodity cm = newCommodity(item);

            if (cm != null)
                cm.updateQuality(item);
            else
            {
                changeQuality(item);

                decreaseSellIn(item);

                if (item.sellIn < 0) {
                    changeQuality(item);
                }
            }
        }
    }

    private Commodity newCommodity(Item item)
    {
        if (equals(item, SULFURAS_HAND_OF_RAGNAROS))
            return new SulfurasHandOfRagnaros();

        return null;
    }

    private void changeQuality(Item item)
    {
        if (!equals(item, AGED_BRIE) && !equals(item, BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT))
        {
             decreaseQuality(item);
        }
        else
        {
            increaseQuality(item);
        }
    }

    private boolean equals(Item item, String name)
    {
        return item.name.equals(name);
    }

    private void decreaseSellIn(Item item)
    {
        item.sellIn = item.sellIn - 1;
    }

    private void decreaseQuality(Item item)
    {
        if (item.quality > 0)
        {
             item.quality = item.quality - 1;
        }
    }

    private void increaseQuality(Item item)
    {
        if (item.quality < 50)
        {
            item.quality = item.quality + 1;
        }
    }
}