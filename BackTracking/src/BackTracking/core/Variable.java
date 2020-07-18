package BackTracking.core;

public class Variable {
    IndexDomain indexDomain;
    int d_Index = -1;
    double cost = 0;

    public Variable(IndexDomain indexDomain) {
        this.indexDomain = indexDomain;
    }

    public Variable(IndexDomain indexDomain, double cost) {
        this.indexDomain = indexDomain;
        this.cost = cost;
    }

    public int getD_Index() {
        return d_Index;
    }

    public void setD_Index(int d_Index) {
        this.d_Index = d_Index;
    }

    public IndexDomain getIndexDomain() {
        return indexDomain;
    }

    public void setIndexDomain(IndexDomain indexDomain) {
        this.indexDomain = indexDomain;
    }
}
