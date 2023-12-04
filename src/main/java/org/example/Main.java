package org.example;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import static org.example.Toys.*;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Toys> toys = getToys();
        //changeWeight(toys);
        Random random = new Random();
        OutPrize(toys, random);
    }
}