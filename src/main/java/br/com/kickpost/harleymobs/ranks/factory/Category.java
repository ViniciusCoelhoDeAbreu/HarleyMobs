package br.com.kickpost.harleymobs.ranks.factory;

import br.com.kickpost.harleymobs.ranks.type.*;
import br.com.kickpost.harleymobs.utils.*;

public class Category
{
    private String itemName;
    private CategoryType categoryType;
    
    public Category(final String itemName, final CategoryType categoryType) {
        this.itemName = itemName;
        this.categoryType = categoryType;
    }
    
    public String getItemName() {
        return this.itemName;
    }
    
    public CategoryType getCategoryType() {
        return this.categoryType;
    }
    
    public String toString(final Rank rank) {
        switch (this.categoryType) {
            case ASSASSINO: {
                return String.valueOf(ObjectUtils.getFormatter(rank.getCost())) + " Cabeças";
            }
            case FAZENDEIRO: {
                return "Colher " + ObjectUtils.getFormatter(rank.getCost()) + " " + ObjectUtils.format(this.itemName);
            }
            case MINERADOR: {
                return "Quebrar " + ObjectUtils.getFormatter(rank.getCost()) + " " + ObjectUtils.format(this.itemName);
            }
            default: {
                return null;
            }
        }
    }
}
