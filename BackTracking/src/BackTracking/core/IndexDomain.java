package BackTracking.core;

public class IndexDomain<E> {
    RealDomain<E> realDomain;
    private int n;
    int currentIndex = -1;

    public IndexDomain(RealDomain<E> realDomain) {
        this.realDomain = realDomain;
        this.n = realDomain.size();
    }

    public int nextIndex(){
        if(this == null) throw new RuntimeException("Domain is null");
        return ++currentIndex;
    }

    public boolean hasNext(){
        return currentIndex + 1 < n;
    }

    public void reset(){
        this.currentIndex = -1;
    }

    public E getRealValue(int index){
        if(index < 0 || index >= n) return null;
        return this.realDomain.get(index);
    }

    public RealDomain<E> getRealDomain() {
        return realDomain;
    }

    public void setRealDomain(RealDomain<E> realDomain) {
        this.realDomain = realDomain;
    }
}
