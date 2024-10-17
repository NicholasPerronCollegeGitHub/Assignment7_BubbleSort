import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class App {

    public static int[] createRandomArray(int arrayLength){
        int[] RandomArray = new int[arrayLength];
        Random rand = new Random();
        for(int i = 0; i < RandomArray.length; i++){
            RandomArray[i] = rand.nextInt(10);
        }
        return(RandomArray);
    }

    public static void writeArrayToFile(int[] array, String filename){
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename)))){
                for(int i = 0; i < array.length; i++){
                    writer.write(array[i] +"\n");
                }
            }catch (IOException e){
                System.out.println("Error, file could not be written.");
            }
    }

    public static int[] readFileToArray(String filename, boolean outputError){
            try(BufferedReader reader = new BufferedReader(new FileReader(new File(filename)))){
                String line;
                List<Integer> list = new ArrayList<>();
                while((line = reader.readLine()) != null){
                    list.add(Integer.parseInt(line));
                }
                int[] array = new int[list.size()];
                for(int i = 0; i < array.length; i++){
                    array[i] = list.get(i);
                }
                return array;
            }catch (Exception e){
                if(outputError){
                    System.out.println("Error, File could not be read");
                }
            }
            return null;
    }
    public static void bubbleSort(int[] array){
        for(int i = 0; i < array.length; i++){
            boolean swapmade = false;
            for(int t = 0; t < (array.length - (i + 1)); t++){
                int a = array[t];
                int b = array[t + 1];
                if(a > b){
                    array[t] = b;
                    array[t+1] = a;
                    swapmade = true;
                }
            }
            if(!swapmade){
                return;
            }
        }
    }
    public static void main(String[] args) throws Exception {
        String filename = null;
        int arrayLength = 0;
        int[] array;
        char mode = '!';
        Scanner scnr = new Scanner(System.in);
        while(mode != 'F' && mode !='R'){
            System.out.print("Please enter either [R] for a random array of numbers, or [F] to specify a file to sort: ");
            mode = scnr.nextLine().toUpperCase().charAt(0);
        }
        if(mode == 'F'){
            
            while(readFileToArray(filename, false) == null){
                System.out.print("Please enter the file you would like to sort: ");
                filename = scnr.nextLine();
                array = readFileToArray(filename, true);
            }
            array = readFileToArray(filename, true);
        }else{
            while(arrayLength < 1){
                System.out.print("Please enter a number greater than 0 for the random array length: ");
                arrayLength = scnr.nextInt();
                scnr.nextLine();
            }
            array = createRandomArray(arrayLength);
        }
        bubbleSort(array);
        writeArrayToFile(array, "Output.txt");
        scnr.close();
    }
}
