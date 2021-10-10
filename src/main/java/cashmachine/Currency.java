package cashmachine;

import lombok.Data;

import java.util.HashMap;
import java.util.SortedMap;

@Data
public class Currency {
    String name;
    SortedMap<Integer,Integer> banknotes ;
    long total = 0;

    /**
     *
     * @param name
     * @param banknotes
     * @param total
     */
    public Currency(String name, SortedMap<Integer, Integer> banknotes,long total) {
        this.name = name;
        this.banknotes = banknotes;
        this.total = total;
    }


}
