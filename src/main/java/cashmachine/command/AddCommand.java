package cashmachine.command;

import cashmachine.CashContainer;
import cashmachine.Currency;
import lombok.extern.java.Log;

import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;

import static cashmachine.Utils.ADD_MONEY;

@Log
public  class AddCommand extends Command{

    String currency;
    Integer value ;
    Integer number;

    /**
     *
     * @param currency
     * @param value
     * @param number
     */
    public AddCommand(String currency, Integer value, Integer number) {
        name = ADD_MONEY;
        this.currency = currency;
        this.value = value;
        this.number = number;
    }

    /**
     * execute add command
     * @return
     */
    @Override
    public boolean execute() {
        log.info("executing add command ");



        Currency exCur = CashContainer.getInstance().getCashMap().get(currency);
       if(exCur != null){
           Integer exNumber = exCur.getBanknotes().get(value);
           if(exNumber != null){
               number += exNumber;
           }
           exCur.getBanknotes().put(value,number );
           exCur.setTotal(exCur.getTotal() + value * number);
           CashContainer.getInstance().getCashMap().put(currency,exCur);
       }
       else {
           SortedMap<Integer, Integer> banknotes = new TreeMap<>(Collections.reverseOrder());
           banknotes.put(value,number);
           CashContainer.getInstance().getCashMap().put(currency,new Currency(currency,banknotes,value * number) );
       }
       return true;

    }
}
