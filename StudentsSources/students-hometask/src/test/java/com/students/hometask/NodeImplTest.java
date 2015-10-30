package com.students.hometask;

import com.students.annotation.validation.ValidationFabric;
import org.junit.Test;

/**
 * Created by kkolesnichenko on 10/30/2015.
 */
public class NodeImplTest {

    @Test(expected = IllegalStateException.class)
    public void testEmptyNode(){
        Node node=new NodeImpl();
        ValidationFabric.validate(node);
    }
}
