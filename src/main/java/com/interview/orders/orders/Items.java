package com.interview.orders.orders;

public enum Items {
    Apple(1, "apple"),
    Orange(2, "orange");

    int id;
    String name;
    Items(int id, String name){

    }

    public int getId(Items items){
        return items.id;
    }

    public String getName(Items items) {
        return items.name;
    }

   /* public List<Items> getAllItems() {
        return
    }*/
}
