package word;

/**
 * @author jh
 * @project word
 * @time 2026/1/4
 */
public class Wangguang {
    String name;
    public Wangguang(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void checkAge(Student student){
        int age=18;
        if(student.age<age)
            System.out.println(student.name+"你是未成年不可以进入网吧");
        else
            System.out.println(student.name+"你成已年可以上网");
    }
}
