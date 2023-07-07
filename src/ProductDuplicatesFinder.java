import java.util.*;

public class ProductDuplicatesFinder {
    private final int NUMBER_OF_PRODUCTS = 100000;
    private final List<Product> list1 = new ArrayList<>(NUMBER_OF_PRODUCTS);
    private final List<Product> list2 = new ArrayList<>(NUMBER_OF_PRODUCTS);
    private final Random random = new Random();

    public void initData() {
        fillList1WithUniqueProducts();
        fillList2WithDuplicatesFromList1();
    }

    private void fillList1WithUniqueProducts() {
        for (int i = 0; i < NUMBER_OF_PRODUCTS; i++) {
            byte[] internalCode = getInternalCode();
            Product product = new Product();
            product.setName(generatePrefixedName(i));
            product.setCategory(getRandomProductCategory());
            product.setInternalCode(internalCode);
            list1.add(product);
        }
    }

    private void fillList2WithDuplicatesFromList1() {
        Set<Integer> duplicateIndices = getRandomDuplicateIndices();
        int duplicateCount = 0;
        for (int i = 0; i < NUMBER_OF_PRODUCTS; i++) {
            Product product;
            if (duplicateIndices.contains(i)) {
                product = list1.get(duplicateCount);
                duplicateCount++;
            } else {
                product = new Product();
                product.setCategory(getRandomProductCategory());
                product.setInternalCode(getInternalCode());
                product.setName(generatePrefixedName(i));
            }
            list2.add(product);
        }
    }

    private Set<Integer> getRandomDuplicateIndices() {
        Set<Integer> duplicateIndices = new HashSet<>();
        while (duplicateIndices.size() < 1000) {
            int randomIndex = random.nextInt(NUMBER_OF_PRODUCTS);
            duplicateIndices.add(randomIndex);
        }
        return duplicateIndices;
    }

    private String getRandomProductCategory() {
        String[] categories = {"Electronics", "Home Decor", "Sports Equipment"};
        int index = random.nextInt(categories.length);
        return categories[index];
    }

    private String generatePrefixedName(int modifier) {
        String[] results = {"Great option", "Standard option", "Poor option"};
        int randomIndex = random.nextInt(results.length);
        return results[randomIndex] + modifier;
    }

    private byte[] getInternalCode() {
        byte[] result = new byte[16];
        random.nextBytes(result);
        return result;
    }

    public static List<Product> findDuplicates(Collection<Product> list1, Collection<Product> list2) {
        List<Product> duplicates = new ArrayList<>();
        for (Product product1 : list1) {
            for (Product product2 : list2) {
                if (product1.equals(product2)) {
                    duplicates.add(product1);
                    break;
                }
            }
        }
        return duplicates;
    }

    public static List<Product> findDuplicatesOptimized(Collection<Product> list1, Collection<Product> list2) {
        List<Product> duplicates = new ArrayList<>();
        Map<ProductWrapper, Boolean> map = new HashMap<>();
        for (Product product : list1) {
            ProductWrapper wrapper = new ProductWrapper(product);
            map.put(wrapper, true);
        }
        for (Product product : list2) {
            ProductWrapper wrapper = new ProductWrapper(product);
            if (map.containsKey(wrapper)) {
                duplicates.add(product);
            }
        }
        return duplicates;
    }

    public void findDuplicateProductsAndPrintExecutionTime() {
        System.out.println("Инициализация данных");
        System.out.println();
        initData();

        System.out.println("====== Поиск дубликатов без использования хэш-таблицы: списки сравниваются элемент за элементом. ======");
        // Замер времени для первой реализации
        long startTimestamp1 = System.currentTimeMillis();
        List<Product> duplicates1 = findDuplicates(list1, list2);
        long endTimestamp1 = System.currentTimeMillis();

        System.out.println("Количество дубликатов: " + duplicates1.size());
        System.out.println("Время выполнения (первая реализация): " + (endTimestamp1 - startTimestamp1) + " мс");

        System.out.println();

        System.out.println("====== Поиск дубликатов (с использованием хэш-таблицы) ======");
        long startTimestamp2 = System.currentTimeMillis();
        List<Product> duplicates2 = findDuplicatesOptimized(list1, list2);
        long endTimestamp2 = System.currentTimeMillis();

        System.out.println("Количество дубликатов: " + duplicates2.size());
        System.out.println("Время выполнения (вторая реализация): " + (endTimestamp2 - startTimestamp2) + " мс");
    }
}

















