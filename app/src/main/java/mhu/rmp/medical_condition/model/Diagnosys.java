package mhu.rmp.medical_condition.model;

/**
 * Created by Ashwin on 28-Jan-18.
 */

public class Diagnosys
{
    public String name;
    public boolean selected;

    public Diagnosys(String _describe, boolean selected)
    {
        name = _describe;
        this.selected = selected;
    }

}
