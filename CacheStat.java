
/**
 *
 * @author Rehan
 */
public class CacheStat {

    int countLD;
    int countST;
    int hitCountLD;
    int hitCountST;
    int missCountLD;
    int missCountST;
    int insCount;
    int totalHitCount;
    int totalMissCount;
    
    double totalParcHit;
    double totalParcMiss;
    double hitParcLD;
    double missParcLD;
    double hitParcST;
    double missParcST;

    public CacheStat() {
        this.countLD = 0;
        this.countST = 0;
        this.hitCountLD = 0;
        this.hitCountST = 0;
        this.missCountLD = 0;
        this.missCountST = 0;
        this.insCount = 0;
    }

    public int getCountLD() {
        return countLD;
    }

    public void setCountLD(int countLD) {
        this.countLD = countLD;
    }

    public int getCountST() {
        return countST;
    }

    public void setCountST(int countST) {
        this.countST = countST;
    }

    public int getHitCountLD() {
        return hitCountLD;
    }

    public void setHitCountLD(int hitCountLD) {
        this.hitCountLD = hitCountLD;
    }

    public int getHitCountST() {
        return hitCountST;
    }

    public void setHitCountST(int hitCountST) {
        this.hitCountST = hitCountST;
    }

    public double getHitParcLD() {
        this.hitParcLD = (double)this.hitCountLD * 100.0 / (double)this.countLD;
        return this.hitParcLD;
    }

    public int getInsCount() {
        return insCount;
    }

    public void setInsCount(int insCount) {
        this.insCount = insCount;
    }

    public int getMissCountLD() {
        return missCountLD;
    }

    public void setMissCountLD(int missCountLD) {
        this.missCountLD = missCountLD;
    }

    public int getMissCountST() {
        return missCountST;
    }

    public void setMissCountST(int missCountST) {
        this.missCountST = missCountST;
    }

    public double getMissParcLD() {
        this.missParcLD = (double)this.missCountLD * 100.0 / (double)this.countLD;
        return this.missParcLD;
    }

    public double getHitParcST() {
        this.hitParcST = (double)this.hitCountST * 100.0 / (double)this.countST;
        return this.hitParcST;
    }

    public double getMissParcST() {
        this.missParcST = (double)this.missCountST * 100.0 / (double)this.countST;
        return this.missParcST;
    }
    
    public int getTotalHitCount() {
        this.totalHitCount = this.hitCountLD + this.hitCountST;
        return this.totalHitCount;
    }

    public int getTotalMissCount() {
        this.totalMissCount = this.missCountLD + this.missCountST;
        return this.totalMissCount;
    }
    
    public double getTotalParcHit() {
        this.totalParcHit = (double)this.totalHitCount * 100.0 / (double)this.insCount;
        return this.totalParcHit;
    }

    public double getTotalParcMiss() {
        this.totalParcMiss = (double)this.totalMissCount * 100.0 / (double)this.insCount;
        return this.totalParcMiss;
    }
    
}
