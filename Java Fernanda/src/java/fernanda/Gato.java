package Parque;

public class Gato {
    public String name;
    public int isAge;
    public String breed;
    public boolean isMale = false;
    Gato registration;
    public Gato(String name){
        this.name = name;
    }
    
    public void Miar(){
        System.out.printf("\n%s: Miau", this.name);
    }
}

