package com.gildedrose;

interface ICommodity
{
    public void updateQuality();
};

abstract class Commodity implements ICommodity
{
    Item item;

    Commodity(Item item)
    {
        this.item = item;
    }

    public abstract void updateQuality();
}

class GildedRose
{
    class SulfurasHandOfRagnaros extends Commodity
    {
        public static final String NameId = "Sulfuras, Hand of Ragnaros";

        SulfurasHandOfRagnaros(Item item)
        {
            super(item);
        }

        @Override
        public void updateQuality()
        {
            increaseQuality(item);
        }
    };

    class BackstagePasses extends Commodity
    {
        public static final String NameId = "Backstage passes to a TAFKAL80ETC concert";

        BackstagePasses(Item item)
        {
            super(item);
        }

        @Override
        public void updateQuality()
        {
            increaseQuality(item);
            decreaseSellIn(item);
            if (item.sellIn < 0)
                increaseQuality(item);
        }
    };

    class AgedBrie extends Commodity
    {
        public static final String NameId = "Aged Brie";

        AgedBrie(Item item)
        {
            super(item);
        }

        @Override
        public void updateQuality()
        {
            increaseQuality(item);
            decreaseSellIn(item);
            if (item.sellIn < 0)
                increaseQuality(item);

        }
    };

    class GenericItem extends Commodity
    {
        GenericItem(Item item)
        {
            super(item);
        }

        @Override
        public void updateQuality()
        {
            decreaseQuality(item);
            decreaseSellIn(item);
            if (item.sellIn < 0)
                decreaseQuality(item);

        }
    };

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality()
    {
        for (Item item : items)
            newCommodity(item).updateQuality();
    }

    private Commodity newCommodity(Item item)
    {
        if (equals(item, SulfurasHandOfRagnaros.NameId))
            return new SulfurasHandOfRagnaros(item);
        else
        if (equals(item, BackstagePasses.NameId))
            return new BackstagePasses(item);
        else
        if (equals(item, AgedBrie.NameId))
            return new AgedBrie(item);

        return new GenericItem(item);
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
            item.quality = item.quality - 1;
    }

    private void increaseQuality(Item item)
    {
        if (item.quality < 50)
            item.quality = item.quality + 1;
    }
}