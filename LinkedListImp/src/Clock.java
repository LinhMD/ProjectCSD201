import java.util.Objects;

public class Clock {
    String id = "";
    String manifest = "";
    int price = 0;
    int guarantee = 0;

    public Clock() {
    }

    public Clock(String id) {
        this.id = id;
    }

    public Clock(String id, String manifest, int price, int guarantee) {
        this.id = id;
        this.manifest = manifest;
        this.price = price;
        this.guarantee = guarantee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManifest() {
        return manifest;
    }

    public void setManifest(String manifest) {
        this.manifest = manifest;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(int guarantee) {
        this.guarantee = guarantee;
    }

    @Override
    public String toString() {
        return  "id='" + id + '\'' +
                ", manifest='" + manifest + '\'' +
                ", price=" + price +
                ", guarantee=" + guarantee;
    }
    public Clock input(){
        this.id = InputValidation.getString("input id","");
        this.manifest = InputValidation.getString("input manifest", "");
        this.guarantee = InputValidation.getAnInteger("input guarantee","");
        this.price = InputValidation.getAnInteger("Input price", "",0 ,Integer.MAX_VALUE );
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clock)) return false;
        Clock clock = (Clock) o;
        return getId().equals(clock.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

