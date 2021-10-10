package cashmachine.command;

import cashmachine.CashContainer;
import cashmachine.Currency;
import lombok.extern.java.Log;

import static cashmachine.Utils.INFO;

@Log
public  class InfoCommand  extends Command{

    public InfoCommand() {
        name = INFO;
    }


    /**
     * execute info command
     * @return
     */
    @Override
    public boolean execute() {
        log.info("executing info command ");
        for (String sCurrency : CashContainer.getInstance().getCashMap().keySet()){
            Currency currency = CashContainer.getInstance().getCashMap().get(sCurrency);
            for (Integer value : currency.getBanknotes().keySet()){
                Integer number = currency.getBanknotes().get(value);
                System.out.println(currency.getName() + " " + value + " " + number) ;
            }
        }
      return true;
    }
}
