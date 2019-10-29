import java.util.ArrayList;

public class InputThread{
    public enum TYPE{
        STUDENT,
        PROGRAM
    }

    TYPE type;

    public InputThread(TYPE type, ArrayList<> array){
        this.type = type;
    }

}