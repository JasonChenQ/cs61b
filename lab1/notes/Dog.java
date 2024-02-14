package notes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dog {
    public int size;
    public static String binomen = "Dog";
    public Dog(int w){
        size = w;  //much like the __init__ in Python
    }
    public void makeNoise() {
        if (size < 10) {
            System.out.println("yip!");
        } else if (size < 30) {
            System.out.println("Wolf");
        } else {
            System.out.println("Bark!");
        }
    }
    public static Dog maxDog(Dog d1, Dog d2) { //建立一个两只其他狗的比较方法
        if (d1.size>d2.size) {
            return d1;
        }
        else {
            return d2;
        }
    }
    public Dog greaterDog(Dog d2) { //使用了自己，所以是non-static
        if (this.size > d2.size) {
            return this;
        }
        else {
            return d2;
        }
    }
    public static Dog[] largerThanFourNeighbors2(Dog[] dogs){
        List<Dog> list_wanted = new ArrayList<Dog>();
        for (int index = 0;index<dogs.length;index += 1){
            boolean whether_to_add = true;

            for (int another_dogs_index = index-2;another_dogs_index<=index+2;another_dogs_index+=1) {
                if (another_dogs_index >= dogs.length | another_dogs_index < 0 | another_dogs_index == index) {
                    continue;
                }
                if (dogs[another_dogs_index].size >= dogs[index].size) {
                    //System.out.print(another_dogs_index);
                    //System.out.print(index);
                    whether_to_add = false;
                    break;
                }
            }
            //System.out.println(whether_to_add);
            if (whether_to_add){
                list_wanted.add(dogs[index]);
            }


        }
        Dog[] ret_dogs = list_wanted.toArray(new Dog[list_wanted.size()]);
        //System.out.println(list_wanted);

        return ret_dogs;

    }
    public static Dog[] largerThanFourNeighbors(Dog[] dogs){
        Dog[] returndog = new Dog[dogs.length];
        int cnt = 0;
        for (int i = 0;i<dogs.length;i+=1){
            if (isBiggestOfFour(dogs,i)){
                returndog[cnt] = dogs[i];
                cnt += 1;
            }
        }
        returndog = returnvaluewithnonull(returndog,cnt);
        return returndog;
    }
    public static boolean isBiggestOfFour(Dog[] dogs, int i){
        boolean whether_to_add = true;

        for (int another_dogs_index = -2;another_dogs_index<=2;another_dogs_index+=1) {
            if (validindex(dogs,i,another_dogs_index)) {


            if (dogs[another_dogs_index+i].size >= dogs[i].size) {
                whether_to_add = false;
                break;
            }
            }
        }
        return whether_to_add;
    }
    public static boolean validindex(Dog[] dogs,int k,int m){
        return m != 0 & k+m<dogs.length & k+m>=0;
    }
    public static Dog[] returnvaluewithnonull(Dog[] dogs,int cnt){
        Dog[] newreturn = new Dog[cnt];
        for (int i =0;i<cnt;i+=1){
            newreturn[i] = dogs[i];
        }
        return newreturn;
    }

}
