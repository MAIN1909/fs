package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "fs_card")
public class Card implements Serializable {
    @Id
    private int id;
    private String name;
    private String description;
    private String pic;
    @Column(name = "is_spell")
    private boolean isSpell;
    private Integer damage;
    private Integer armor;
    private String ability;
    private int cost;

    private transient boolean isCardCanMoove;

    public Card() {
    }

    public Card(int id, String name, String description, String pic, boolean isSpell, Integer damage, Integer armor, String ability, int cost) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pic = pic;
        this.isSpell = isSpell;
        this.damage = damage;
        this.armor = armor;
        this.ability = ability;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public boolean isSpell() {
        return isSpell;
    }

    public void setSpell(boolean spell) {
        isSpell = spell;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public Integer getArmor() {
        return armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isCardCanMoove() {
        return isCardCanMoove;
    }

    public void setCardCanMoove(boolean cardCanMoove) {
        isCardCanMoove = cardCanMoove;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (id != card.id) return false;
        if (isSpell != card.isSpell) return false;
        if (damage != card.damage) return false;
        if (armor != card.armor) return false;
        if (cost != card.cost) return false;
        if (name != null ? !name.equals(card.name) : card.name != null) return false;
        if (description != null ? !description.equals(card.description) : card.description != null) return false;
        if (pic != null ? !pic.equals(card.pic) : card.pic != null) return false;
        return ability != null ? ability.equals(card.ability) : card.ability == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (pic != null ? pic.hashCode() : 0);
        result = 31 * result + (isSpell ? 1 : 0);
        result = 31 * result + damage;
        result = 31 * result + armor;
        result = 31 * result + (ability != null ? ability.hashCode() : 0);
        result = 31 * result + cost;
        return result;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pic='" + pic + '\'' +
                ", isSpell=" + isSpell +
                ", damage=" + damage +
                ", armor=" + armor +
                ", ability='" + ability + '\'' +
                ", cost=" + cost +
                '}';
    }
}
