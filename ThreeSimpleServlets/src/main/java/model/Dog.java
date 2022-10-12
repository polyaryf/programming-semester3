package model;

public class Dog extends Pet{

    public Dog(String name, String color) {
        super(name, color);
    }

    public String sayHello(){
        return "Woof, I'm " + getName() + ". "
                    + "I'm glad to see you :)";
    }
}
