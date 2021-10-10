package cashmachine.command;

import lombok.Data;

@Data
public abstract class Command {
    protected String name ;


    public abstract boolean execute();

}
