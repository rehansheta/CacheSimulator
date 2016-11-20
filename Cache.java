
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rehan
 */
public class Cache {

    /**
     * @param args cache_size_in_KB cache_line_size_in_Bytes associativity input_file_name output_file_name
     */
    public static void main(String[] args) {
        
        FileWriter outputWriter = null;
        
        try {

            int cacheSize = Integer.parseInt(args[0]);
            int lineSize = Integer.parseInt(args[1]);
            int associativity = Integer.parseInt(args[2]);

            CacheSimulator simCache = new CacheSimulator(cacheSize, lineSize, associativity);
            CacheStat stat = new CacheStat();

            Scanner inputScannner = new Scanner(new File(args[3]));
            outputWriter = new FileWriter(args[4]);

            while (inputScannner.hasNext()) {
                String instruction = inputScannner.nextLine();
                stat.setInsCount(stat.getInsCount() + 1);

                String[] input = instruction.split(" ");
                String address = input[1].substring(0, input[1].length() - 1);

                int cacheIndex = simCache.calcIndex(address);
                int cacheTag = simCache.calcTag(address);
                
                int cacheMiss = simCache.accessCache(address, cacheIndex, cacheTag, Integer.parseInt(input[2]));

                if (cacheMiss == 0) {
                    if (input[0].equalsIgnoreCase("LD")) {
                        stat.setCountLD(stat.getCountLD() + 1);
                        stat.setHitCountLD(stat.getHitCountLD() + 1);
                    } else {
                        stat.setCountST(stat.getCountST() + 1);
                        stat.setHitCountST(stat.getHitCountST() + 1);

                    }
                } else {
                    if (input[0].equalsIgnoreCase("LD")) {
                        stat.setCountLD(stat.getCountLD() + 1);
                        stat.setMissCountLD(stat.getMissCountLD() + 1);
                    } else {
                        stat.setCountST(stat.getCountST() + 1);
                        stat.setMissCountST(stat.getMissCountST() + 1);
                    }
                }
            }

            DecimalFormat df = new DecimalFormat("#0.00");
            
            outputWriter.write("Load-Hits: " + stat.getHitCountLD() + ", " + df.format(stat.getHitParcLD()) + "%");
            outputWriter.write("\nLoad-Misses: " + stat.getMissCountLD() + ", " + df.format(stat.getMissParcLD()) + "%");
            outputWriter.write("\nLoad-Accesses: " + stat.getCountLD() + ", 100.00%");

            outputWriter.write("\n");
            
            outputWriter.write("\nStore-Hits: " + stat.getHitCountST() + ", " + df.format(stat.getHitParcST()) + "%");
            outputWriter.write("\nStore-Misses: " + stat.getMissCountST() + ", " + df.format(stat.getMissParcST()) + "%");
            outputWriter.write("\nStore-Accesses: " + stat.getCountST() + ", 100.00%");

            outputWriter.write("\n");
            
            outputWriter.write("\nTotal-Hits: " + stat.getTotalHitCount() + ", " + df.format(stat.getTotalParcHit()) + "%");
            outputWriter.write("\nTotal-Misses: " + stat.getTotalMissCount() + ", " + df.format(stat.getTotalParcMiss()) + "%");
            outputWriter.write("\nTotal-Accesses: " + stat.getInsCount() + ", 100.00%\n\n");

        } catch (IOException ex) {
            Logger.getLogger(Cache.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally {
            try {
                outputWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(Cache.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
