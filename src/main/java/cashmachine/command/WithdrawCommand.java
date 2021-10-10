package cashmachine.command;

import cashmachine.CashContainer;
import cashmachine.Currency;
import lombok.extern.java.Log;

import java.util.HashMap;

import static cashmachine.Utils.WITHDRAW_MONEY;

@Log
public  class WithdrawCommand extends Command{
    String currency;
    Integer amount ;

    /**
     *
     * @param currency
     * @param amount
     */
    public WithdrawCommand(String currency,Integer amount) {
        name = WITHDRAW_MONEY;
        this.currency = currency;
        this.amount = amount;
    }

    /**
     *   execute withdraw command
     * @return false if not succeed
     */
    @Override
    public boolean execute() {
        log.info("executing withdraw command ");

        Currency exCur = CashContainer.getInstance().getCashMap().get(currency);
        boolean isSucceed = false;

        if(exCur != null && exCur.getTotal() >= amount){

            HashMap<Integer,Integer>  potentialWithdrawMap = new HashMap<>();
            int restAmount = amount;

            for (Integer value : exCur.getBanknotes().keySet()){
                Integer number = exCur.getBanknotes().get(value);

                int totalNum = value * number;
                int withdrawAmount = restAmount / value;
                if(totalNum >= restAmount){
                    if(restAmount % value == 0) {
                        exCur.getBanknotes().put(value, number - withdrawAmount);
                        exCur.setTotal(exCur.getTotal() - amount);
                        isSucceed =true;
                        System.out.println(value + " " + withdrawAmount);
                        break;
                    }

                    potentialWithdrawMap.put(value,withdrawAmount);
                    restAmount -= withdrawAmount * value;
                }
                else{
                    potentialWithdrawMap.put(value,number);
                    restAmount -= number * value;

                }

            }

            if(isSucceed){
                for (Integer value: potentialWithdrawMap.keySet()){
                    Integer subNum = potentialWithdrawMap.get(value);
                    Integer number = exCur.getBanknotes().get(value);
                    exCur.getBanknotes().put(value,number - subNum);
                    System.out.println(value + " " + subNum);

                }
            }

        }
        else {
            log.warning("No currency " + currency + " or the sum is bigger then existing ");
        }

        if(isSucceed){
            return true;
        }

         return false;

    }
}
