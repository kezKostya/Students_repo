package com.students;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by kkolesnichenko on 10/19/2015.
 */
public class ExceptionExamplesTests {

    @Test
    public void testTryCatchFinally(){
        try{
            throw new RuntimeException();
        }catch(RuntimeException ex){

        }

        try {
            throw new RuntimeException();
        }catch(RuntimeException ex){

        }finally {
            System.out.println("Executes always");
        }

        try{
            throw new RuntimeException();
        }finally{
            System.out.println("Executes always");
        }

    }

    @Test(expected = IOException.class)
    public void testTryWithResources() throws IOException {
        readFirstLineFromFile("");
    }

    static String readFirstLineFromFile(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }catch(RuntimeException ex){
            throw ex;
        }
        catch(IOException ex){
            throw ex;
        }
        //this part wouldn't compile FileNotFoundException extends from IOException
        /*catch(FileNotFoundException ex){
            throw ex;
        }
        */
        finally {
            System.out.println("Executes always");
        }
    }

    static String readFirstLineFromFileWithFinallyBlock(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            return br.readLine();
        } finally {
            if (br != null) br.close();
        }
    }

    static String finallyStatementReturn(){
        try{
            return "normal";
        }finally{
            return "finally";
        }

    }

    @Test
    public void testFinallyStatementReturn() throws IOException {
        Assert.assertTrue("finally".equals(finallyStatementReturn()));
    }


    /**
     * If we have more than one parent with the same method signature than child should throw intersection of checked exception list
     */
    public  interface SomeInterface{
        void doSomething() throws IOException, SQLException,java.security.GeneralSecurityException;
    }

    public abstract class SomeClass{
        public abstract void doSomething() throws FileNotFoundException,java.security.GeneralSecurityException;
    }

    public class SomeChild extends SomeClass implements SomeInterface{


        @Override
        public void doSomething() throws FileNotFoundException,java.security.GeneralSecurityException{

        }
    }


}
