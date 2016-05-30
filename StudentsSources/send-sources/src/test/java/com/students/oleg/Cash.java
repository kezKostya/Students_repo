package com.students.oleg;

import junit.framework.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by coolsmileman on 13.11.2015.
 */
public class Cash {
    ArrayList<Object> arr=new ArrayList<Object>();
    ListIterator<Object> arrIterator=arr.listIterator();

    public void newArr(String name,int val,int m){
        avm bank=new avm();
        bank.setCurrency(name);
        bank.setValue(val,m);
        arr.add(bank);
    }
    public String setMoney(String name,int val,int m){
        boolean check=false;
        if (arr.isEmpty()){
            newArr(name,val,m);
        }
        else {arrIterator =arr.listIterator();
            while (arrIterator.hasNext()) {
                //arrIterator =arr.listIterator();
                avm a=(avm) arrIterator.next();
                //if (((avm) arrIterator.next()).currency.equals(name)) {
                if(a.currency.equals(name)){
                    //arrIterator =arr.listIterator();
                    //((avm) arrIterator.next()).setValue(val, m);
                    a.setValue(val,m);
                    check = true;
                    // break;
                }
            }


            if (!check)
                newArr(name,val,m);
        }
        return("OK");


    }
    public String getMoney(String name,int money){
        String s="";
        arrIterator =arr.listIterator();
        while (arrIterator.hasNext()) {
            avm exam = (avm) arrIterator.next();
            if (exam.currency.equals(name)) {
                s=exam.getValue(money);
                break;
            }
            else
                s="ERROR";
        }
        return(s);
    }
    public ArrayList<String> showMoney(){
        ArrayList<String> money=new ArrayList<String>();
        arrIterator =arr.listIterator();
        while (arrIterator.hasNext()) {
            avm exam = (avm) arrIterator.next();
            for(int i=0;i<exam.value.length;i++) {
                if (exam.money[i] == 0)
                    continue;
                money.add(exam.currency + " " + exam.value[i] + " " + exam.money[i]);
            }
        }
        return(money);
    }
    public String start(String wdyw){
        return(showMoney().toString());
    }
    public String start(String wdyw,String curr,int val,int m){
        return(setMoney(curr,val,m));
    }
    public String start(String wdyw, String curr, int m){
        return(getMoney(curr,m));
    }

    @Test
    public void Test(){

        Assert.assertTrue("OK".equals(start("+", "USD",100,5)));
        Assert.assertTrue("OK".equals(start("+","USD",10,30)));
        Assert.assertTrue("OK".equals(start("+","RUR",100,20)));
        Assert.assertTrue("OK".equals(start("+","RUR",1000,15)));
        Assert.assertTrue("ERROR".equals(start("-","UAH",100)));
        Assert.assertTrue("USD 100 2 10 6".equals(start("-","USD",260)));
        Assert.assertTrue("ERROR".equals(start("-","USD",125)));
        Assert.assertTrue("ERROR".equals(start("-","USD",600)));
        Assert.assertTrue("USD 100 3 10 20".equals(start("-", "USD",500)));
        Assert.assertTrue("[USD 10 4, RUR 1000 15, RUR 100 20]".equals(start("?")));
    }


    /**
     * <value> is the value of notes. Valid values are 10^n and 5*10^n, 0<=n<=3
     * (although some currencies may have larger value notes and some odd values like 2,3,20,25, we do not allow them for simplification).
     */
    @Test
    public void test2(){
        Assert.assertTrue("OK".equals(start("+", "USD",50,5)));
        Assert.assertTrue("USD 50 4".equals(start("-", "USD",200)));
        //50 USD is valid value since it is equal 5*10^n where n=1
    }

    //<number> is any positive integer
    //Reply: OK if successful, ERROR if validation fails
    @Test
    public void test3(){
        Assert.assertTrue("OK".equals(start("+", "USD",100,5)));
        start("+","USD",100,-5);
       // Assert.assertTrue("ERROR".equals(start("+","USD",100,-5)));
        Assert.assertTrue("USD 100 2".equals(start("-", "USD", 200)));

    }

    @Test
    public void test4(){
        Assert.assertTrue("ERROR".equals(start("-", "USD", 600)));
    }


    @Test
    public void test6(){
        Assert.assertTrue("OK".equals(start("+", "USD",100,Integer.MAX_VALUE)));
        Assert.assertTrue("OK".equals(start("+", "USD",100,1)));
        Assert.assertTrue("USD 100 2".equals(start("-", "USD", 200)));
    }

    @Test
    public void test5(){
        Assert.assertTrue("OK".equals(start("+", "USD",100,Integer.MAX_VALUE)));
        Assert.assertTrue("OK".equals(start("+", "USD",100,1)));
        Assert.assertTrue("USD 100 2".equals(start("-", "USD", 200)));
    }

}
