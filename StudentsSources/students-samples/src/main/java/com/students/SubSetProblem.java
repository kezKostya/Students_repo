package com.students;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Created by kkolesnichenko on 5/27/2016.
 * Requires 1.8 java for compilation(was used stream API)
 */
public class SubSetProblem {


    private static boolean debug=false;

    public static Set<Integer> existsSubsetMatchesSum(Integer[] array, int key){
        //sort array - O(log N)
        //return existsSubsetMatchesSum(new TreeSet<>(Arrays.asList(array)), key,true,new HashMap<>());
       return existsSubsetMatchesSumWithoutRecursion(new HashSet<>(Arrays.asList(array)), key);
    }

    /**
     * Gets sorted set.
     * 1. Find A=sum(negative) O(N)
     * 2. Find B=sum(positive) O(N)
     * 3. check that A<=key<=B
     * 4. Recursively decrease set to 2 dimensional set and search there value (key - currentValue)
     * Additional to algorithm improvements
     * 5. subSetSums - improvement that remembers collected sums of positive numbers and negative numbers(use additional memory)
     * @see https://en.wikipedia.org/wiki/Subset_sum_problem#Pseudo-polynomial_time_dynamic_programming_solution
     * @param integers set of integers
     * @param key - sum of subset fo find
     * @return sun set of integers that matches key
     */
    private static Set<Integer> existsSubsetMatchesSum(NavigableSet<Integer> integers, int key, boolean checkRanges, Map<Integer,Set<Integer>> subSetSums){
        if(integers.isEmpty()) return Collections.emptySet();
        Set<Integer> tmpSet=subSetSums.get(key);
        if(tmpSet!=null){
            return tmpSet;
        }
        if(checkRanges) {

            if(key<0) {
                NavigableSet<Integer> negativeElements = integers.headSet(0, false);
                Integer negativeSum = 0;
                for (Integer i : negativeElements) {
                    negativeSum += i;
                    if (key == negativeSum){
                        return negativeElements.headSet(i, true);
                    }
                    else{
                        subSetSums.put(negativeSum,negativeElements.headSet(i, true));
                    }
                }
                if (key < negativeSum) {
                    return  Collections.emptySet();
                }
            }

            if(key>0) {
                NavigableSet<Integer> positiveElements = integers.tailSet(0, false);
                Integer positiveSumm = 0;
                for (Integer i : positiveElements) {
                     positiveSumm += i;
                    if (key == positiveSumm) {
                        return positiveElements.subSet(0, false, i, true);
                    }else{
                        subSetSums.put(positiveSumm,positiveElements.subSet(0, false, i, true));
                    }
                }
                if (key > positiveSumm) {
                    return Collections.emptySet();
                }

            }
        }

        Iterator<Integer> values=integers.iterator();
        Integer currentNumber =values.next();
        Integer firstNumber=currentNumber;

        boolean[] q=new boolean[integers.size()];
        boolean currentKeyMatchExpected=(key==currentNumber);
        Set result=new HashSet<>();
        if(currentNumber==key){
            if(debug && currentKeyMatchExpected) System.out.println("[DEBUG]Current number match expected=" + key);

            result.add(currentNumber);
            return result;
        }
        q[0]=currentKeyMatchExpected;

        int i=1;
        while(values.hasNext()){
            currentNumber=values.next();


            result.addAll(existsSubsetMatchesSum(integers.subSet(firstNumber, true, currentNumber, false), key - currentNumber, false,subSetSums));
            if(debug && !result.isEmpty()) {
                System.out.println("[DEBUG]Found in subset from "+firstNumber+" to "+currentNumber +" sum matching="+ key);
            }
            boolean val=q[i-1]||key==currentNumber||!result.isEmpty();
            if(val){
                result.add(currentNumber);
                  return result;
            }
            q[i]=val;
            i++;
        }
        return result;
    }


    /**
     * Made almost the same that previous method, but without recursion.
     * Add with additional 2^N memory since we are collecting all the sums of all elements in the set
     * This method doesn't require sorting.
     * Since recursion uses Stak memory in Java, this approach seems to be preferred.
     * @param integers
     * @param key
     * @return
     */
    public static Set<Integer> existsSubsetMatchesSumWithoutRecursion(Set<Integer> integers, int key){
        if(integers.isEmpty()) return Collections.emptySet();
        Map<Integer,Set<Integer>> subSetSums=new HashMap<>();

        //Bounds check: A<=key<=B, where A=sum(negative) and B=sum(positive)
        //this checks could be safely removed, but are used to improve behaviour for worst cases
        if(key<0) {
            //check that sum of all negative integers < key
            Iterator<Integer> negativeIntegers = integers.stream().filter((i) -> i < 0).iterator();
            Set<Integer> negativeNumbersSet = new HashSet<>();
            final AtomicInteger negativeSum =new AtomicInteger(0);
            Set result=subSetIteration(negativeIntegers, key, subSetSums, currentNumber -> {
                negativeNumbersSet.add(currentNumber);
                negativeSum.addAndGet(currentNumber);
                Set<Integer> singleSet = new HashSet<>();
                singleSet.add(currentNumber);
                subSetSums.put(currentNumber, singleSet);
                Set<Integer> compositeSet = new HashSet<>(negativeNumbersSet);
                subSetSums.put(negativeSum.get(), compositeSet);
            });
            if(!result.isEmpty()){
                return result;
            }
            if (key < negativeSum.get()) {
                return Collections.emptySet();
            }
        }

        if(key>0) {
            //check that sum of all positive integers > key
            Iterator<Integer> positiveIntegers = integers.stream().filter((i) -> i >= 0).iterator();

            Set<Integer> positiveNumbersSet = new HashSet<>();
            final AtomicInteger positiveSum =new AtomicInteger(0);
            Set result=subSetIteration(positiveIntegers, key, subSetSums, currentNumber -> {
                positiveNumbersSet.add(currentNumber);
                positiveSum.addAndGet(currentNumber);
                Set<Integer> singleSet = new HashSet<>();
                singleSet.add(currentNumber);
                subSetSums.put(currentNumber, singleSet);
                Set<Integer> compositeSet = new HashSet<>(positiveNumbersSet);
                subSetSums.put(positiveSum.get(), compositeSet);
            });
            if(!result.isEmpty()){
               return result;
            }
            if (key > positiveSum.get()) {
                return Collections.emptySet();
            }
        }

        //return subSetSums.get(key);

        Set result=subSetIteration(integers.iterator(), key, subSetSums, currentNumber ->
            collectSumOfSubSets(subSetSums, currentNumber)
        );
        if(!result.isEmpty()){
            return result;
        }


        return Collections.emptySet();
    }


    /**
     * Modification of previous way without bounds check: A<=key<=B, where A=sum(negative) and B=sum(positive)
     * The most simple ;)
     * @param integers
     * @param key
     * @return
     */
    public static Set<Integer> existsSubsetMatchesSumWithoutRecursionWithoutBoundaryCheck(Set<Integer> integers, int key){
        if(integers.isEmpty()) return Collections.emptySet();
        Map<Integer,Set<Integer>> subSetSums=new HashMap<>();
          //return subSetSums.get(key);
        Set result=subSetIteration(integers.iterator(), key, subSetSums, currentNumber ->
                        collectSumOfSubSets(subSetSums, currentNumber)
        );

        return result;
    }

    /**
     * One more modification of previous way.
     * We are iterating over negative numbers and collecting all their sums
     * And then we are iterating over positive numbers and collecting all their sums
     * @param integers
     * @param key
     * @return
     */
    public static Set<Integer> existsSubsetMatchesSumWithoutRecursion2(Set<Integer> integers, int key){
        if(integers.isEmpty()) return Collections.emptySet();
        Map<Integer,Set<Integer>> subSetSums=new HashMap<>();
        //check that sum of all negative integers < key
        Iterator<Integer> negativeIntegers = integers.stream().filter((i) -> i < 0).iterator();
        final AtomicInteger negativeSum =new AtomicInteger(0);
        Set result=subSetIteration(negativeIntegers, key, subSetSums, currentNumber -> {
            negativeSum.addAndGet(currentNumber);
            collectSumOfSubSets(subSetSums, currentNumber);
        });
        if(!result.isEmpty()){
            return result;
        }
        if (key < negativeSum.get()) {
            return Collections.emptySet();
        }
        //check that sum of all positive integers > key
        Iterator<Integer> positiveIntegers = integers.stream().filter((i) -> i >= 0).iterator();
        final AtomicInteger positiveSum = new AtomicInteger(0);
        result=subSetIteration(positiveIntegers, key, subSetSums, currentNumber -> {
            positiveSum.addAndGet(currentNumber);
            collectSumOfSubSets(subSetSums, currentNumber);
        });

        //we don't need this at this moment. All iterations are complete.
        /*if (key > positiveSum.get()) {
            return Collections.emptySet();
        }
        */

        return result;
    }


    private static Set<Integer> subSetIteration(Iterator<Integer> iterator,Integer key,Map<Integer,Set<Integer>> subSetSums, Consumer<Integer> consumer){
        while(iterator.hasNext()){
            Integer currentNumber = iterator.next();
            Set<Integer> singleSet=new HashSet<>();
            singleSet.add(currentNumber);
            if(currentNumber==key){
                return singleSet;
            }

            Set<Integer> matched= subSetSums.get(key - currentNumber);
            if(matched!=null && !matched.isEmpty() &&  !matched.contains(currentNumber)){
                singleSet.addAll(matched);
                return singleSet;
            }
            consumer.accept(currentNumber);

        }
        return Collections.emptySet();
    }

    private static void collectSumOfSubSets(Map<Integer,Set<Integer>> subSetSums, int currentNumber){
        Map<Integer,Set<Integer>> tmpSubSetSums=new HashMap<>();
        Set<Integer> singleSet=new HashSet<>();
        singleSet.add(currentNumber);
        for(Map.Entry<Integer,Set<Integer>> sums:subSetSums.entrySet()){
            if(sums.getValue().contains(currentNumber)){
                continue;
            }
            Set<Integer> tmpSubSet=new HashSet<>(sums.getValue());
            tmpSubSet.add(currentNumber);
            tmpSubSetSums.put(sums.getKey()+currentNumber,tmpSubSet);
        }
        subSetSums.putAll(tmpSubSetSums);
        subSetSums.put(currentNumber,singleSet);
    }

    public static void main(String[] args) {
        //Integer[] array={1,2,5,9,10};
        Integer[] array={-7,-3,-2,5,8};
        check(array, -12,true);
        check(array, -10,true);

        check(array, -50,false);

        check(array, -9,true);

        check(array, -7,true);
        check(array, -5,true);
        check(array, -2,true);
        check(array, 0,true);
        check(array,1,true);
        check(array,2,true);
        check(array,6,true);
        check(array,11,true);

        check(array,13,true);

        check(array,15,false);

       check(array,-4,true);
        check(array,4,true);


        debug=true;
         array=new Integer[]{1,3,4,5,8,10,23};

        check(array,42,true);

        //check(array, 150,false);



    }

    private static void check( Integer[] array, int key, boolean expectedToFound){

        Set<Integer> subSet=existsSubsetMatchesSum(array,key);
        System.out.println("Exists subset for key=" + key + ". Used elements=" + subSet.toString());
        if(subSet.isEmpty()&& expectedToFound) {
            System.err.println("Fails expected to found subset for key="+key);
        }
       /* else{
            //check sum
            if(!subSet.isEmpty()&& !expectedToFound) {
                Integer colectedSum = subSet.stream().mapToInt(Integer::intValue).sum();
                if (key != colectedSum) {
                    System.err.println("Collected sum=" + colectedSum + ". Key=" + key);
                }
            }
        }*/

        if(!subSet.isEmpty()) {
            Integer colectedSum = subSet.stream().mapToInt(Integer::intValue).sum();
            if (key != colectedSum) {
                System.err.println("Collected sum=" + colectedSum + ". Key=" + key);
            }
        }

        System.out.println();
    }








}
