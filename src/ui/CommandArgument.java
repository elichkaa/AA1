package ui;

public class CommandArgument<T> {
    private final T value;

    public CommandArgument(T value){
        this.value = value;
    }

    public T getValue(){
        return this.value;
    }
}
