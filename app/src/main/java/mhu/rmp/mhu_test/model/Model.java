package mhu.rmp.mhu_test.model;

/**
 * Created by Nikam on 11/04/2018.
 */

public class Model {

    public static final int TEST_TYPE=0;
    public static final int SUB_TEST_TYPE=1;


    public int type;
    public int data;
    public String text;



    public Model(int type, String text, int data)
    {
        this.type=type;
        this.data=data;
        this.text=text;

    }


}
