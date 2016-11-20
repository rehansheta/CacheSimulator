
import java.math.BigInteger;

/**
 *
 * @author Rehan
 */
public class CacheSimulator {

    int associativity;
    int cacheSize;
    int lineSize;
    int[][] tags;
    int[] tagCount;
    int nSet;

    public CacheSimulator(int cacheSize, int lineSize, int associativity) {
        this.associativity = associativity;
        this.cacheSize = cacheSize;
        this.lineSize = lineSize;

        this.nSet = (cacheSize * 1024) / (associativity * lineSize);
        this.tags = new int[this.nSet][associativity];
        this.tagCount = new int[this.nSet];

        for (int i = 0; i < this.nSet; i++) {
            this.tagCount[i] = 0;
        }
    }

    public int calcIndex(String address) {
        BigInteger setAddress = new BigInteger(address);
        int offset = (int) (Math.log10(this.lineSize) / Math.log10(2));
        int cacheIndex = (setAddress.shiftRight(offset)).mod(new BigInteger("" + this.nSet)).intValue();
        return cacheIndex;
    }

    public int calcTag(String address) {
        BigInteger setAddress = new BigInteger(address);
        int offsetBitCount = (int) (Math.log10(this.lineSize) / Math.log10(2));
        int cacheTag = (setAddress.shiftRight(offsetBitCount)).divide(new BigInteger("" + this.nSet)).intValue();
        return cacheTag;
    }

    public int accessCache(String address, int cacheIndex, int cacheTag, int nBytes) {
        int nextIndex;
        int cacheMiss = 1;

        BigInteger setAddress = new BigInteger(address);
        int offset = this.lineSize;
        int byteIndex = setAddress.mod(new BigInteger("" + offset)).intValue();

        if ((byteIndex != 0) && (nBytes > this.lineSize - byteIndex)) {
            if (cacheIndex < this.nSet - 1) {
                nextIndex = cacheIndex + 1;
            } else {
                nextIndex = 0;
            }

            int missOne = this.findTags(cacheIndex, cacheTag);
            int missTwo = this.findTags(nextIndex, cacheTag);
            if ((missOne | missTwo) == 0) {
                cacheMiss = 0;
            }
        } else {
            cacheMiss = this.findTags(cacheIndex, cacheTag);
        }

        return cacheMiss;
    }

    public int findTags(int index, int tags) {
        int miss = 1;
        for (int i = 0; i < this.associativity; i++) {
            if (this.getTag(index, i) == tags) {
                miss = 0;
                break;
            }
        }
        if (miss == 1) {
            int way = findWay(index);
            this.replaceBlock(tags, index, way);
            this.tagCount[index] = updateCount(index);
        }

        return miss;
    }

    public void replaceBlock(int tag, int index, int way) {
        this.tags[index][way] = tag;
    }

    // ROUND_ROBIN replacement policy in descending order of ways.
    public int findWay(int index) {
        int way = this.associativity - this.tagCount[index] - 1;
        return way;
    }

    public int updateCount(int index) {
        int count = (this.tagCount[index] + 1) % this.associativity;
        return count;
    }

    public int getAssociativity() {
        return associativity;
    }

    public int getCacheSize() {
        return cacheSize;
    }

    public int getLineSize() {
        return lineSize;
    }

    public int getTag(int index, int way) {
        return tags[index][way];
    }
}
