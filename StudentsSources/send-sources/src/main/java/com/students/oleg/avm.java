package com.students.oleg;

/**
 * Created by coolsmileman on 13.11.2015.
 */
class avm{
    String currency;
    Integer value[]={1000,100,10,1};
    Integer money[]={0,0,0,0};
    public void setCurrency(String name){
        currency=name;
    }
    public void setValue(int val,int m){
        for(int i=0;i<value.length;i++)
            if (value[i]==val)
                money[i]+=m;
    }
    public String getValue(int m){
        Integer a1=0,a2=0;
        String str= currency;
        Integer[] v=value.clone();
        Integer[] mon=money.clone();
        for(int i=0;i<value.length;)
            if(m-value[i]>=0 && money[i]>0){
                m=m-value[i];
                money[i]--;
                a1=value[i];
                a2++;
            }
            else{
                i++;
                if(a1!=0 && a2!=0)
                    str+=" "+a1+" "+a2;
                a1=0;
                a2=0;
            }
        if (m>0){
            money=mon;
            value=v;
            return("ERROR");
        }
        else
            return(str);

    }
}