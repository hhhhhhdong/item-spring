package hello.itemservice.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data // -> 제공하는 기능이 많아 다르게 동작할 수 있으므로 주의가 필요
@Getter @Setter
public class Item {

    private Long id;
    private String itemName;
    // Integer 타입인 이유는 null이 들어올 수도 있기 때문에
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
