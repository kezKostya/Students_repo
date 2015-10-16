package com.students;

/**
 * Created by kkolesnichenko on 10/16/2015.
 */
public class StringWrapperTest {
    public static class StringWrapper{
        private String wrappedString;

        public StringWrapper(String wrappedString) {
            this.wrappedString = wrappedString;
        }

        public String getWrappedString() {
            return wrappedString;
        }

        public void setWrappedString(String wrappedString) {
            this.wrappedString = wrappedString;
        }
    }

    public static StringWrapper finalModification(final StringWrapper sringWrapper){
        sringWrapper.setWrappedString("Other");
        return sringWrapper;
    }

    @org.junit.Test
    public void testWrappedString (){
        StringWrapper wrapper1=new StringWrapper("Some");
        StringWrapper wrapper2=finalModification(wrapper1);
        System.out.println(wrapper1.getWrappedString());
        System.out.println(wrapper2.getWrappedString());
    }

}
