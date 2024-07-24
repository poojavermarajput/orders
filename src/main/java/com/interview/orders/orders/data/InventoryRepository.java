package com.interview.orders.orders.data;

import com.interview.orders.orders.core.Inventory;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InventoryRepository {

    List<Inventory> inventories = new ArrayList<>();

    public static AtomicInteger inventoryId = new AtomicInteger(0);

//    private Map<String, List<Meeting>> meetingMap = new HashMap<>();


    @PostConstruct
    public void init() {
        /*inventoryId.incrementAndGet();
        Inventory inventory = new Inventory(inventoryId.get(), "apple", 0.60);
        inventories.add(inventory);
        Inventory inventory2 = new Inventory(inventoryId.get(), "orange", 0.25);
        inventories.add(inventory2);*/

        /*meetingMap.put("Ankit", List.of(new Meeting(1, 2), new Meeting(3, 4), new Meeting(5, 6), new Meeting(10, 11)));
        meetingMap.put("Pooja", List.of(new Meeting(1, 4), new Meeting(7, 11))); // (2, 5) - (1-5), 7-11
        meetingMap.put("Smythe", List.of(new Meeting(2, 4), new Meeting(5, 11))); //*/
    }
    public List<Inventory> getInventory(){
       /* inventoryId.incrementAndGet();
        Inventory inventory = new Inventory(inventoryId.get(), "apple", 0.60);
        inventories.add(inventory);
        Inventory inventory2 = new Inventory(inventoryId.get(), "orange", 0.25);
        inventories.add(inventory2);*/

        return inventories;
    }

    public void addItems(List<Inventory> inventoryList){
        inventoryList.forEach(item -> {
            inventoryId.incrementAndGet();
            Inventory inventory = new Inventory(inventoryId.get(), item.getName(), item.getPrice());
            inventories.add(inventory);
        });
    }

    public Map<String, Double> getPrices(List<String> itemNames) {
        List<Inventory> inventoryToRetrieve = inventories.stream().filter(item-> itemNames.contains(item.getName())).toList();

        return inventoryToRetrieve.stream().collect(
                Collectors
                        .toMap(
                                Inventory::getName,
                                Inventory::getPrice));
                                /*(x, y)
                                        -> x + ", " + y,*/
                                //HashMap::new));

    }
}
