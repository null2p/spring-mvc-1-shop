package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemA", 10000, 10);
        //when
        itemRepository.save(item);
        //then
        Item foundItem = itemRepository.findById(item.getId());
        assertThat(foundItem).isEqualTo(item);
    }

    @Test
    void findAll() {
        //given
        Item itemA = new Item("itemA", 10000, 10);
        Item itemB = new Item("itemB", 10000, 10);
        itemRepository.save(itemA);
        itemRepository.save(itemB);
        //when
        List<Item> items = itemRepository.findAll();
        //then
        assertThat(items.size()).isEqualTo(2);
        assertThat(items).contains(itemA, itemB);
    }

    @Test
    void update() {
        //given
        Item itemA = new Item("itemA", 10000, 10);
        itemRepository.save(itemA);
        Long itemId = itemA.getId();
        //when
        Item updateItem = new Item("itemB", 9000, 9);
        itemRepository.update(itemId,updateItem);
        //then
        Item resultItem = itemRepository.findById(itemId);
        assertThat(resultItem.getItemName()).isEqualTo(updateItem.getItemName());
        assertThat(resultItem.getPrice()).isEqualTo(updateItem.getPrice());
        assertThat(resultItem.getQuantity()).isEqualTo(updateItem.getQuantity());
    }
}