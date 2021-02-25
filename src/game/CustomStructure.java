package game;

import java.util.*;

public class CustomStructure<T> {
    private List<T>records=new ArrayList<>();
    private State state;

    public CustomStructure(State state) {
        this.state = state;
    }

    /**
     * add singe record to the data
     * @param data
     */
    public void addData(T data){
        records.add(data);
    }

    @Override
    public String toString() {
        return String.format("""
                ----%s----
                %s
                """,state.toString(),records.toString());
    }
}
