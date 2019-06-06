package Parque;

public class Parque {

    public static void main(String[] args) {
        Gato instance1 = new Gato("Fred");
        instance1.breed = "Lol";
        instance1.isMale = true;
        instance1.isAge = 5;
        instance1.Miar();

        Gato instance2 = new Gato("Pedro");
        instance2.breed = "Ted";
        instance2.isMale = false;
        instance2.isAge = 4;
        instance2.Miar();
    }
    
}
