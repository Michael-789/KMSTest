import cashmachine.CashContainer;
import cashmachine.Currency;
import cashmachine.Parser;
import cashmachine.command.Command;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {
    @Test
    public void testCommands(){

        String input = "+ USD 100 2";

        Command command = Parser.parseInput(input);

        command.execute();

        Currency currency = CashContainer.getInstance().getCashMap().get("USD");
        assertEquals (currency.getTotal(),200);
        assertEquals (currency.getBanknotes().get(100),2);


         input = "- USD 100";

         command = Parser.parseInput(input);

        command.execute();

        assertEquals (currency.getTotal(),100);
        assertEquals (currency.getBanknotes().get(100),1);

    }
}
