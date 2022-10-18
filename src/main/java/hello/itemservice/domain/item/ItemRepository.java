package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    // static -> spring bean이어서 static이 필요없기도 하지만
    //           따로 new를 통해 객체가 생성될 경우를 여러개가 생성될 수도 있기 때문에
    // HashMap -> singleton, static 으로 생성되었기 때문에 멀티쓰레드 환경에서 동시에 접근할 경우
    //            문제가 생길 수 있기 때문에 실무에서는 ConcurrentHashMap 같은 것을 사용해야 한다.
    //            아래의 long도 마찬가지로 AtomicLong 같은 것을 사용해야 한다.
    private static final Map<Long, Item> store = new HashMap<>(); // static final
    private static long sequence = 0L; // static

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
        // ArrayList로 감싸서 return하는 이유
        //  리스트의 값을 바꿔도 store에 영향을 안받게 하기 위해
        //  타입을 맞추기위해
    }

    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
        // Item 객체를 파라미터로 받는 것이 애매하다.
        //  Item 객체에는 id도 포함되어 있는데 여기서는 사용하지 않기 때문에
        //  다른 개발자가 updateParam의 id 값을 보고 이건뭐지 의아해 할 수 있다.
        //  그래서 사실 ItemParamDto 같은 객체를 만들어 name, price, quantity 세개의 필드만 가지도록 하는것이 바람직 하다.
    }

    public void clearStore() {
        store.clear();
    }
}
