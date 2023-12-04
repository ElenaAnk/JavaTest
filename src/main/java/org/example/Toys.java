package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Toys {
    private int id_toys;
    private String name;
    private int weight;



    public static List<Toys> getToys() { //создать игрушки
        Toys toy1 = new Toys(1,"constructor",20);
        Toys toy2 = new Toys(2,"robot",20);
        Toys toy3 = new Toys(3,"doll",60);

        List<Toys> toys = new ArrayList<> (Arrays.asList(toy1,toy2,toy3));
        return toys;
    }


    public static void changeWeight(List<Toys> toys) { //изменить вес игрушки
        System.out.println("Введите id игрушки: ");
        int a = Integer.parseInt(new Scanner(System.in).nextLine());
        System.out.println("Введите новый вес игрушки: ");
        int b = Integer.parseInt(new Scanner(System.in).nextLine());
        for (Toys toy : toys) {
            if (toy.getId_toys() == a) {
                toy.setWeight(b);
            }
        }
    }
    public static int getCount(List<Toys> toys) { //количество игрушек для розыгрыша
        int count = 0;
        for (Toys toy: toys) {
            count+=1;
        }
        return count;
    }
    public static int getNumberToyPlay(Random random, List<Toys> toys) {
        int numberToyPlay = random.nextInt(1,getCount(toys)+1); //число игрушки случайное
        return numberToyPlay;
    }
    public static int getTotalWeight(List<Toys> toys) {
        int totalWeight = 0; //общий вес
        for (Toys toy : toys) {
            totalWeight+=toy.getWeight();
        }
        return totalWeight;
    }
    public static int getNumberPlay(Random random, List<Toys> toys) {
        int numberPlay = random.nextInt(1,getTotalWeight(toys)+1); //случайное число от общего веса
        return numberPlay;
    }
    public static List<Toys> getToysWinner(List<Toys> toys, Random random) { //розыгрыщ игрушек
        System.out.println("Сколько игрушек необходимо разыграть: ");
        List<Toys> winnerToys = new ArrayList<> ();
        int toyCount = Integer.parseInt(new Scanner(System.in).nextLine());
        while (winnerToys.size()<toyCount){
            boolean flag = false;
            while (!flag){
                for (Toys toy : toys) {
                    if (getNumberToyPlay(random, toys)==toy.getId_toys()){
                        if (getNumberPlay(random, toys)<=toy.getWeight()){
                            winnerToys.add(toy);
                            flag=true;
                        }
                        else{
                            getNumberToyPlay(random, toys);
                            getNumberPlay(random, toys);
                        }}
                }
            }}
        return winnerToys;
    }
    public static void OutPrize(List<Toys> toys, Random random) throws IOException {
        List<Toys> giveOutPrize = getToysWinner(toys, random);
        System.out.println("Сколько игрушек нужно выдать: ");
        int col = Integer.parseInt(new Scanner(System.in).nextLine());
        while (col>0){
            Toys prizeToy = giveOutPrize.remove(0);
            try (FileWriter writer = new FileWriter("toys.txt", true)) {
                writer.write("id_toys=" + prizeToy.getId_toys()+", name="+prizeToy.getName()+ "\n");
            }
            col-=1;
        }
        int count = 0;
        for (Toys toys1 : giveOutPrize) {
            count+=1;
        }

        System.out.println("К выдаче осталось " + count + " игрушек");
    }




}
