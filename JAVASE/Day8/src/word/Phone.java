package word;

/**
 * @author jh
 * @project word
 * @time 2026/1/4
 */
public class Phone {
    private String name;
    private String color;
    private String brand;
    private int weight;
    public Phone(String name, String color, String brand, int weight){
        this.name = name;
        this.color = color;
        this.brand = brand;
        this.weight = weight;
    }
    public String getName(){
        return name;
    }
    public String getColor(){
        return color;
    }
    public String getBrand(){
        return brand;

    }
    public int getWeight(){
        return weight;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setColor(String color){
        this.color = color;

    }
    public void setBrand(String brand){
        this.brand = brand;
    }
    public void setWeight(int weight){
        this.weight = weight;
    }

    public void call(){
        System.out.println("正在打电话");
    }
    public void sendMessge(){
        System.out.println("正在发送消息");
    }
}
