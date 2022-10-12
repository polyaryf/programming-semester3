package model;

public class Cat extends Pet{

    public Cat(String name, String color) {
        super(name, color);
    }

    public String sayHello(){
        return "Meow, I'm " + getName() + ". "
                    + "I'm glad to see you :)";

    }
}
