package cashmachine;

import cashmachine.command.AddCommand;
import cashmachine.command.Command;
import cashmachine.command.InfoCommand;
import cashmachine.command.WithdrawCommand;
import lombok.extern.java.Log;

import static cashmachine.Utils.*;

@Log
public  class Parser {

    /**
     * parsing commands from user imput
     * @param input
     * @return
     */
    public static Command parseInput(String input){

        String[] tokens = input.split(" ");

        if(tokens.length == 1 && INFO.equals(tokens[0])){
            return  new InfoCommand();
        }

        if(tokens.length == 3 && WITHDRAW_MONEY.equals(tokens[0]) ){
            if(verifyCurrency(tokens[1])  && verifyNumber(tokens[2])){
                String currency = tokens[1];
                Integer amount = Integer.parseInt(tokens[2]);
                return  new WithdrawCommand(currency,amount);
            }
        }

        if(tokens.length == 4 && ADD_MONEY.equals(tokens[0]) ){
            if(verifyCurrency(tokens[1])  && verifyValue(tokens[2]) && verifyNumber(tokens[3])){
                String currency = tokens[1];
                Integer value = Integer.parseInt(tokens[2]);
                Integer number = Integer.parseInt(tokens[3]);

                return  new AddCommand(currency,value,number);
            }
        }

        return null;
    }

    /**
     * verifying currency rules
     * @param currency
     * @return
     */
    private static boolean verifyCurrency(String currency){

        if(currency.length() == 3 && currency.equals(currency.toUpperCase())) {
            return true;
        }
        return false;
    }

    /**
     * verifying positive number
     * @param number
     * @return
     */
    private static boolean  verifyNumber(String number){
        try {
            Integer num =Integer.parseInt(number);
            if(num > 0) {
                return true;
            }
        } catch(NumberFormatException e){
           // return false;
        }

        log.warning("Illegal number "+ number);
        return false;

    }

    /**
     * verifying value of banknotes
     * @param value
     * @return
     */
    private static boolean verifyValue(String value ){

        if(!verifyNumber(value)){
            return false;
        }

        Integer intVal =Integer.parseInt(value);

        int i = 0;
        while ((intVal % 10 == 0)  && i++ < 3){
            intVal = intVal/10;
        }

        if(intVal == 1 || intVal == 5){
            return  true;
        }


        return false;

    }


}
