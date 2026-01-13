import com.employee.management.entity.Designer;
import com.employee.management.entity.Employee;
import com.employee.management.entity.Programmer;
import com.employee.management.service.Accountant;
import com.employee.management.service.Manager;
import com.employee.management.test.Tseter;

/**
 * @author jh
 * @project
 * @time 2026/1/12
 */
public class Main {
    public static void main(String[] args) {
        Employee duFu = new Programmer("杜甫", 18000);
        Employee baiJuYi = new Tseter("白居易", 10000, -1000);
        Employee liShangYin = new Designer("李商隐", 7000);
        Manager liBai = new Manager("李白");
        Accountant liQingZhao = new Accountant("李清照");

        liBai.fireEmployee(duFu);
        liBai.fireEmployee(baiJuYi);
        liBai.fireEmployee(liShangYin);

        liQingZhao.settleSalary(duFu);
        liQingZhao.settleSalary(baiJuYi);
        liQingZhao.settleSalary(liShangYin);
    }
}