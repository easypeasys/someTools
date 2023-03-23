

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class integrateCSV {

    public static void main(String[] args) {
        String file1 = "pokemon_1.csv";
        String file2 = "pokedex_(Update_05.20).csv";
        String outFile = "pokemon_merged.csv";

        String[] titles = new String[]{ "id","Name","type_number","Type 1","Type 2","height_m","weight_kg","total_points",
                "HP","Attack","Defense","Sp. Atk","Sp. Def","Speed","Generation","Legendary",
                "against_normal","against_fire","against_water","against_electric","against_grass","against_ice","against_fight",
                "against_poison","against_ground","against_flying","against_psychic","against_bug","against_rock","against_ghost",
                "against_dragon","against_dark","against_steel","against_fairy"};
        // Create HashMap to store data from file 1
        HashMap<String, String[]> dataMap = new HashMap<String, String[]>();
        HashMap<String, Boolean> foundLostMap = new HashMap<String, Boolean>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file1));
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split(",");
                String[] data = new String[11];
                for (int i = 1; i < parts.length; i++) {
                    data[i-1] = parts[i];
                }
                dataMap.put(parts[1], data);
                foundLostMap.put(parts[1],false);
                line = reader.readLine();
            }
            System.out.println(dataMap.size());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Merge data from file 2 into HashMap
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file2));
            FileWriter writer = new FileWriter(outFile);
            for (String title:titles) {
                writer.write(title+",");
            }
            writer.write("\n");
            String line = reader.readLine();
            int count = 1;
            while (line != null) {
                String[] parts = line.split(",");
                String[] data = dataMap.get(parts[2]);
                if (data != null) {
                    // Write merged data to output file
                    writer.write(count++ + ",");
                    writer.write(data[0] + ",");
                    writer.write(parts[8] + ",");
                    writer.write(data[1] + ",");
                    writer.write(data[2] + ",");
                    writer.write(parts[11] + ",");
                    writer.write(parts[12] + ",");
                    writer.write(parts[17] + ",");
                    writer.write(data[3] + ",");
                    writer.write(data[4] + ",");
                    writer.write(data[5] + ",");
                    writer.write(data[6] + ",");
                    writer.write(data[7] + ",");
                    writer.write(data[8] + ",");
                    writer.write(data[9] + ",");
                    writer.write(data[10] + ",");
                    for (int i = 33; i < parts.length; i++) {
                        writer.write(parts[i] + ",");
                    }
                    foundLostMap.put(parts[2],true);
                    writer.write("\n");
                }

                line = reader.readLine();
            }
            System.out.println("write lines:" + count);
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (Map.Entry<String, Boolean> entry : foundLostMap.entrySet()){
            if (!entry.getValue()) System.out.println(entry.getKey());
        }
    }
}
