package notes;

public class DogProblem {
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
        System.out.println(Dog.largerThanFourNeighbors(tested));
        for (int index = 0;index<Dog.largerThanFourNeighbors(tested).length;index+=1) {
            System.out.println(Dog.largerThanFourNeighbors(tested)[index].size);
        }
    }
}
