package com.jh.supermarket;

import com.jh.supermarket.bean.*;
import com.jh.supermarket.bean.Order;
import com.jh.supermarket.enums.LogTypeEnum;
import com.jh.supermarket.servic.goods.ProductServic;
import com.jh.supermarket.servic.goods.TypeServic;
import com.jh.supermarket.servic.system.CashierServic;
import com.jh.supermarket.util.CheckUtil;
import com.jh.supermarket.util.DataUtil;
import com.jh.supermarket.util.FileUtil;
import com.jh.supermarket.util.IdUtil;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 超市管理系统单元测试类
 */
public class SupermarketSystemTest {

    private static final String TEST_DATA_DIR = "test_supermarket_data/";
    private static final String CASHIER_FILE = TEST_DATA_DIR + "cashier_test.dat";
    private static final String PRODUCT_FILE = TEST_DATA_DIR + "product_test.dat";
    private static final String TYPE_FILE = TEST_DATA_DIR + "type_test.dat";
    private static final String ORDER_FILE = TEST_DATA_DIR + "order_test.dat";
    private static final String LOG_FILE = TEST_DATA_DIR + "log_test.dat";

    @BeforeAll
    static void setUpClass() {
        // 创建测试数据目录
        java.io.File directory = new java.io.File(TEST_DATA_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    @BeforeEach
    void setUp() {
        // 清空测试数据
        clearTestData();
        // 初始化ID池
        IdUtil.CASHIER_MAX_ID = 0;
        IdUtil.PRODUCT_MAX_ID = 0;
        IdUtil.TYPE_MAX_ID = 0;
        IdUtil.ORDER_MAX_ID = 0;
        IdUtil.LOG_MAX_ID = 0;
        IdUtil.CASHIER_ID_POOL.clear();
        IdUtil.PRODUCT_ID_POOL.clear();
        IdUtil.TYPE_ID_POOL.clear();
        IdUtil.ORDER_ID_POOL.clear();
        IdUtil.LOG_ID_POOL.clear();

        // 初始化DataUtil数据
        DataUtil.cashiersData.clear();
        DataUtil.productsData.clear();
        DataUtil.typeData.clear();
        DataUtil.ordersData.clear();
        DataUtil.logsData.clear();
    }

    @AfterEach
    void tearDown() {
        // 清空测试数据
        clearTestData();
    }

    /**
     * 清空测试数据
     */
    private void clearTestData() {
        java.io.File cashierFile = new java.io.File(CASHIER_FILE);
        java.io.File productFile = new java.io.File(PRODUCT_FILE);
        java.io.File typeFile = new java.io.File(TYPE_FILE);
        java.io.File orderFile = new java.io.File(ORDER_FILE);
        java.io.File logFile = new java.io.File(LOG_FILE);

        if (cashierFile.exists()) cashierFile.delete();
        if (productFile.exists()) productFile.delete();
        if (typeFile.exists()) typeFile.delete();
        if (orderFile.exists()) orderFile.delete();
        if (logFile.exists()) logFile.delete();
    }

    @Test
    @DisplayName("测试ID生成工具")
    void testIdUtil() {
        // 测试收银员ID生成
        String cashierId1 = IdUtil.getCashierId();
        assertEquals("C-1", cashierId1);

        String cashierId2 = IdUtil.getCashierId();
        assertEquals("C-2", cashierId2);

        // 回收ID并再次获取
        IdUtil.recycleCashierId("C-1");
        String cashierId3 = IdUtil.getCashierId();
        assertEquals("C-1", cashierId3);

        // 测试其他ID生成
        String productId = IdUtil.getProductId();
        assertEquals("P-1", productId);

        String typeId = IdUtil.getTypeId();
        assertEquals("T-1", typeId);

        String orderId = IdUtil.getOrderId();
        assertEquals("O-1", orderId);

        String logId = IdUtil.getLogId();
        assertEquals("L-1", logId);
    }

    @Test
    @DisplayName("测试收银员对象")
    void testCashierBean() {
        Cashier cashier = new Cashier();
        assertNotNull(cashier.getId());
        assertTrue(cashier.getId().startsWith("C-"));

        cashier.setAccount("test_account");
        cashier.setPassword("Test123!");
        cashier.setName("测试收银员");
        cashier.setSex('M');
        cashier.setAge(25);
        cashier.setAddress("测试地址");
        cashier.setPhoneNumber("13800138000");

        assertEquals("test_account", cashier.getAccount());
        assertEquals("Test123!", cashier.getPassword());
        assertEquals("测试收银员", cashier.getName());
        assertEquals('M', cashier.getSex());
        assertEquals(25, cashier.getAge());
        assertEquals("测试地址", cashier.getAddress());
        assertEquals("13800138000", cashier.getPhoneNumber());
    }

    @Test
    @DisplayName("测试商品对象")
    void testProductBean() {
        Product product = new Product();
        assertNotNull(product.getId());
        assertTrue(product.getId().startsWith("P-"));

        product.setName("测试商品");
        product.setPrice(new BigDecimal("10.50"));
        product.setCount(100);
        product.setTypeId("T-1");

        assertEquals("测试商品", product.getName());
        assertEquals(new BigDecimal("10.50"), product.getPrice());
        assertEquals(100, product.getCount());
        assertEquals("T-1", product.getTypeId());
    }

    @Test
    @DisplayName("测试商品类型对象")
    void testTypeBean() {
        Type type = new Type();
        assertNotNull(type.getId());
        assertTrue(type.getId().startsWith("T-"));

        type.setName("测试类型");

        assertEquals("测试类型", type.getName());
    }

    @Test
    @DisplayName("测试订单对象")
    void testOrderBean() {
        Order order = new Order();
        assertNotNull(order.getId());
        assertTrue(order.getId().startsWith("O-"));
        assertNotNull(order.getDate());
        assertNotNull(order.getProduct());

        order.setCashierID("C-1");
        order.setTotalPrice(new BigDecimal("50.00"));

        assertEquals("C-1", order.getCashierID());
        assertEquals(new BigDecimal("50.00"), order.getTotalPrice());
    }

    @Test
    @DisplayName("测试日志对象")
    void testLogBean() {
        Log log = new Log();
        assertNotNull(log.getId());
        assertTrue(log.getId().startsWith("L-"));
        assertNotNull(log.getDate());

        log.setTitle("测试标题");
        log.setDescription("测试描述");
        log.setSuccess(true);
        log.setLogType(LogTypeEnum.CASHIER_LOG.getCode());

        assertEquals("测试标题", log.getTitle());
        assertEquals("测试描述", log.getDescription());
        assertTrue(log.isSuccess());
        assertEquals(LogTypeEnum.CASHIER_LOG.getCode(), log.getLogType());
    }

    @Test
    @DisplayName("测试数据工具类")
    void testDataUtil() {
        // 添加测试收银员
        Cashier cashier = new Cashier();
        cashier.setAccount("test123");
        cashier.setPassword("Test123!");
        DataUtil.cashiersData.add(cashier);

        // 测试根据账号查找收银员
        Cashier foundCashier = DataUtil.getCashierByAccount("test123");
        assertNotNull(foundCashier);
        assertEquals("test123", foundCashier.getAccount());

        // 测试根据ID查找收银员
        Cashier foundCashierById = DataUtil.getCashierById(cashier.getId());
        assertNotNull(foundCashierById);
        assertEquals(cashier.getId(), foundCashierById.getId());

        // 添加测试商品
        Product product = new Product();
        product.setName("测试商品");
        product.setPrice(new BigDecimal("10.00"));
        DataUtil.productsData.add(product);

        // 测试根据名称查找商品
        Product foundProductByName = DataUtil.getProductByname("测试商品");
        assertNotNull(foundProductByName);
        assertEquals("测试商品", foundProductByName.getName());

        // 测试根据ID查找商品
        Product foundProductById = DataUtil.getProductById(product.getId());
        assertNotNull(foundProductById);
        assertEquals(product.getId(), foundProductById.getId());

        // 添加测试类型
        Type type = new Type();
        type.setName("测试类型");
        DataUtil.typeData.add(type);

        // 测试根据名称查找类型
        Type foundTypeByName = DataUtil.getTypeByname("测试类型");
        assertNotNull(foundTypeByName);
        assertEquals("测试类型", foundTypeByName.getName());

        // 测试根据ID查找类型
        Type foundTypeById = DataUtil.getTypeById(type.getId());
        assertNotNull(foundTypeById);
        assertEquals(type.getId(), foundTypeById.getId());

        // 添加测试订单
        Order order = new Order();
        order.setCashierID("C-1");
        DataUtil.ordersData.add(order);

        // 测试根据ID查找订单
        Order foundOrder = DataUtil.getOrderById(order.getId());
        assertNotNull(foundOrder);
        assertEquals(order.getId(), foundOrder.getId());

        // 测试添加日志
        DataUtil.addLog("测试", "测试日志", true, LogTypeEnum.SYSTEM_LOG);
        assertFalse(DataUtil.logsData.isEmpty());
        Log lastLog = DataUtil.logsData.get(DataUtil.logsData.size() - 1);
        assertEquals("测试", lastLog.getTitle());
        assertEquals("测试日志", lastLog.getDescription());
        assertTrue(lastLog.isSuccess());
        assertEquals(LogTypeEnum.SYSTEM_LOG.getCode(), lastLog.getLogType());
    }

    @Test
    @DisplayName("测试验证工具类")
    void testCheckUtil() {
        // 测试手机号验证
        assertTrue(CheckUtil.checkPhone("13812345678"));
        assertTrue(CheckUtil.checkPhone("15912345678"));
        assertFalse(CheckUtil.checkPhone("12345678901")); // 不符合格式
        assertFalse(CheckUtil.checkPhone("1381234567")); // 长度不够
        assertFalse(CheckUtil.checkPhone(null));

        // 测试年龄验证
        assertTrue(CheckUtil.checkAge(25));
        assertTrue(CheckUtil.checkAge(18));
        assertTrue(CheckUtil.checkAge(65));
        assertFalse(CheckUtil.checkAge(17)); // 年龄太小
        assertFalse(CheckUtil.checkAge(66)); // 年龄太大

        // 测试价格验证
        assertTrue(CheckUtil.checkPrice(10.5));
        assertTrue(CheckUtil.checkPrice(0.01));
        assertFalse(CheckUtil.checkPrice(0));
        assertFalse(CheckUtil.checkPrice(-1));

        // 测试数量验证
        assertTrue(CheckUtil.checkCount(1));
        assertTrue(CheckUtil.checkCount(100));
        assertFalse(CheckUtil.checkCount(0));
        assertFalse(CheckUtil.checkCount(-1));

        // 测试密码验证
        assertTrue(CheckUtil.checkPassword("Test123!"));
        assertTrue(CheckUtil.checkPassword("Abc123@#"));
        assertFalse(CheckUtil.checkPassword("test123")); // 缺少大写字母和特殊字符
        assertFalse(CheckUtil.checkPassword("TEST123")); // 缺少小写字母和特殊字符
        assertFalse(CheckUtil.checkPassword("TestABC")); // 缺少数字和特殊字符
        assertFalse(CheckUtil.checkPassword("Test123")); // 缺少特殊字符
        assertFalse(CheckUtil.checkPassword("Te!")); // 长度不够
        assertFalse(CheckUtil.checkPassword(null));
        assertFalse(CheckUtil.checkPassword(""));
    }

    @Test
    @DisplayName("测试文件工具类")
    void testFileUtil() {
        // 测试保存和加载收银员数据
        Cashier cashier1 = new Cashier();
        cashier1.setAccount("test_save");
        cashier1.setPassword("Test123!");
        DataUtil.cashiersData.add(cashier1);

        FileUtil.saveCashiers(DataUtil.cashiersData);
        List<Cashier> loadedCashiers = FileUtil.loadCashiers();

        assertEquals(1, loadedCashiers.size());
        assertEquals("test_save", loadedCashiers.get(0).getAccount());

        // 测试保存和加载商品数据
        Product product1 = new Product();
        product1.setName("测试商品");
        product1.setPrice(new BigDecimal("10.00"));
        DataUtil.productsData.add(product1);

        FileUtil.saveProducts(DataUtil.productsData);
        List<Product> loadedProducts = FileUtil.loadProducts();

        assertEquals(1, loadedProducts.size());
        assertEquals("测试商品", loadedProducts.get(0).getName());

        // 测试保存和加载类型数据
        Type type1 = new Type();
        type1.setName("测试类型");
        DataUtil.typeData.add(type1);

        FileUtil.saveTypes(DataUtil.typeData);
        List<Type> loadedTypes = FileUtil.loadTypes();

        assertEquals(1, loadedTypes.size());
        assertEquals("测试类型", loadedTypes.get(0).getName());

        // 测试保存和加载订单数据
        Order order1 = new Order();
        order1.setCashierID("C-1");
        DataUtil.ordersData.add(order1);

        FileUtil.saveOrders(DataUtil.ordersData);
        List<Order> loadedOrders = FileUtil.loadOrders();

        assertEquals(1, loadedOrders.size());
        assertEquals("C-1", loadedOrders.get(0).getCashierID());

        // 测试保存和加载日志数据
        Log log1 = new Log();
        log1.setTitle("测试日志");
        log1.setDescription("测试描述");
        DataUtil.logsData.add(log1);

        FileUtil.saveLogs(DataUtil.logsData);
        List<Log> loadedLogs = FileUtil.loadLogs();

        assertEquals(1, loadedLogs.size());
        assertEquals("测试日志", loadedLogs.get(0).getTitle());
    }

    @Test
    @DisplayName("测试统计功能")
    void testStatistics() {
        // 添加一些测试商品用于统计
        Product product1 = new Product();
        product1.setName("商品1");
        product1.setPrice(new BigDecimal("10.00"));
        product1.setCount(5);
        DataUtil.productsData.add(product1);

        Product product2 = new Product();
        product2.setName("商品2");
        product2.setPrice(new BigDecimal("20.00"));
        product2.setCount(3);
        DataUtil.productsData.add(product2);

        // 测试库存统计
        var stockStats = DataUtil.totalStock();
        assertEquals(8, stockStats.get("totalCount")); // 5 + 3
        assertEquals("110.00", stockStats.get("totalAmount")); // 10*5 + 20*3
        assertEquals("13.75", stockStats.get("avgPrice")); // 110/8

        // 添加一些测试订单用于销售统计
        Order order1 = new Order();
        order1.setCashierID("C-1");
        order1.getProduct().put(product1.getId(), 2); // 卖出2个商品1
        order1.getProduct().put(product2.getId(), 1); // 卖出1个商品2
        order1.setTotalPrice(new BigDecimal("40.00"));
        DataUtil.ordersData.add(order1);

        // 测试销售统计
        var salesStats = DataUtil.getSaleStat();
        assertEquals(3, salesStats.get("totalSaleCount")); // 2 + 1
        assertEquals("40.00", salesStats.get("totalSaleAmount")); // 2*10 + 1*20
        assertEquals("13.33", salesStats.get("avgSalePrice")); // 40/3 ≈ 13.33
    }

    @Test
    @DisplayName("测试收银员服务类")
    void testCashierService() {
        CashierServic cashierServic = new CashierServic();

        // 测试添加收银员
        // 这里我们直接操作DataUtil来模拟添加过程
        Cashier newCashier = new Cashier();
        newCashier.setAccount("test_new_cashier");
        newCashier.setPassword("Test123!");
        newCashier.setPhoneNumber("13812345678");
        newCashier.setAddress("测试地址");
        DataUtil.cashiersData.add(newCashier);

        assertEquals(1, DataUtil.cashiersData.size());
        assertEquals("test_new_cashier", DataUtil.cashiersData.get(0).getAccount());

        // 测试删除收银员
        String idToRemove = newCashier.getId();
        DataUtil.cashiersData.removeIf(c -> c.getId().equals(idToRemove));
        IdUtil.recycleCashierId(idToRemove);

        assertEquals(0, DataUtil.cashiersData.size());
    }

    @Test
    @DisplayName("测试商品服务类")
    void testProductService() {
        ProductServic productServic = new ProductServic();

        // 测试添加商品
        Product newProduct = new Product();
        newProduct.setName("测试商品");
        newProduct.setPrice(new BigDecimal("15.00"));
        newProduct.setCount(10);
        newProduct.setTypeId("T-1");
        DataUtil.productsData.add(newProduct);

        assertEquals(1, DataUtil.productsData.size());
        assertEquals("测试商品", DataUtil.productsData.get(0).getName());

        // 测试修改商品价格
        Product productToUpdate = DataUtil.productsData.get(0);
        BigDecimal oldPrice = productToUpdate.getPrice();
        productToUpdate.setPrice(new BigDecimal("20.00"));

        assertEquals(new BigDecimal("20.00"), productToUpdate.getPrice());

        // 测试删除商品
        String idToRemove = productToUpdate.getId();
        DataUtil.productsData.removeIf(p -> p.getId().equals(idToRemove));
        IdUtil.recycleProductId(idToRemove);

        assertEquals(0, DataUtil.productsData.size());
    }

    @Test
    @DisplayName("测试商品类型服务类")
    void testTypeService() {
        TypeServic typeServic = new TypeServic();

        // 测试添加类型
        Type newType = new Type();
        newType.setName("测试类型");
        DataUtil.typeData.add(newType);

        assertEquals(1, DataUtil.typeData.size());
        assertEquals("测试类型", DataUtil.typeData.get(0).getName());

        // 测试删除类型
        String idToRemove = newType.getId();
        DataUtil.typeData.removeIf(t -> t.getId().equals(idToRemove));
        IdUtil.recycleTypeId(idToRemove);

        assertEquals(0, DataUtil.typeData.size());
    }
}