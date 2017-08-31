package com.discExchange;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

@RunWith(JUnit4.class)
public class JavaFeaturesTest{

    @Test
    public void parameterInMethod_byValue_shouldNotChange()
    {
        final int initialValue = 100;
        int myValue = initialValue;

                changeInt(myValue, 10);

        assertThat(myValue).isEqualTo(initialValue);
    }

    private void changeInt(int oldValue, int newValue){
        oldValue = newValue;
    }

    @Test
    public void parameterInMethod_immutableReferenceType_shouldNotChange()
    {
        final int initialValue = 100;
        final int newValue = 10;

        Integer myInteger = new Integer(initialValue);
        changeInteger(myInteger, newValue);
        assertThat(myInteger).isEqualTo(initialValue);

        int myValue = initialValue;
        changeInteger(myValue, newValue);
        assertThat(myValue).isEqualTo(initialValue);
    }

    private void changeInteger(Integer oldValue, int newValue) {
        oldValue = newValue;
    }

    private void changeString(String initial, String newValue){
        //initial.
    }

    @Test
    public void getFromOtherModule(){
        String name = MyStaticClass.NAME;
        assertThat(name).isEqualToIgnoringCase("myName");
    }

}


