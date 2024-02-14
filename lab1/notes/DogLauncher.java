package notes;

public class DogLauncher {
    public static void notmain(String[] args) {
        Dog smallDog;
        new Dog(20);
        smallDog = new Dog(5);
        Dog hugeDog = new Dog(150);
        smallDog.makeNoise();
        hugeDog.makeNoise();
    }
    public static void not_main(String[] args) {
        Dog[] dogs = new Dog[3];
        dogs[0] = new Dog(50);
        dogs[1] = new Dog(20);
        dogs[1].makeNoise();
        System.out.println(dogs[2]);
        Dog bigger = Dog.maxDog(dogs[0],dogs[1]);
        System.out.println(bigger.size);
        bigger.makeNoise();
        Dog aliendog = new Dog(60);
        System.out.println(aliendog.greaterDog(dogs[1]).size);
        System.out.println(Dog.binomen); //虽然类变量也可以用实例.变量来获取，但是还是建议用类.变量来获取
    }
    public static void main(String[] args){
        Dog[] tested = new Dog[7];
        Dog[] tested2 = new Dog[]{new Dog(20),new Dog(30)};
        tested[0] = new Dog(10);
        tested[1] = new Dog(20);
        tested[2] = new Dog(30);
        tested[3] = new Dog(25);
        tested[4] = new Dog(20);
        tested[5] = new Dog(40);
        tested[6] = new Dog(10);
        //,new Dog(10),new Dog(3),new Dog(4),new Dog(8),new Dog(12),new Dog(1)
        Dog[] values = Dog.largerThanFourNeighbors(tested);
        System.out.println(values);
        for (int index = 0;index<Dog.largerThanFourNeighbors(tested).length;index+=1) {
            System.out.println(Dog.largerThanFourNeighbors(tested)[index].size);
        }
    }
}
