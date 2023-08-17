package models;

import java.util.List;
import java.util.Objects;

public class Provider extends User{


    private List<Item> providedItems;



    public List<Item> getProvidedItems() {
        return providedItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Provider providers)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getProvidedItems(), providers.getProvidedItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getProvidedItems());
    }

    public void setProvidedItems(List<Item> providedItems) {
        this.providedItems = providedItems;
    }
}
