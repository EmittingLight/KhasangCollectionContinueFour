import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ProductDuplicatesFinderTest {

    @Test
    void initData() {
        ProductDuplicatesFinder finder = new ProductDuplicatesFinder();
        finder.initData();

        // ��������, ��� ������ �����������
        Assertions.assertEquals(100000, finder.getList1().size());
        Assertions.assertEquals(100000, finder.getList2().size());
    }

    @Test
    void findDuplicates() {
        ProductDuplicatesFinder finder = new ProductDuplicatesFinder();
        finder.initData();

        List<Product> duplicates = finder.findDuplicates(finder.getList1(), finder.getList2());

        // ��������, ��� ������ ���������� �� ������
        Assertions.assertFalse(duplicates.isEmpty());
    }

    @Test
    void findDuplicatesOptimized() {
        ProductDuplicatesFinder finder = new ProductDuplicatesFinder();
        finder.initData();

        List<Product> duplicates = finder.findDuplicatesOptimized(finder.getList1(), finder.getList2());

        // ��������, ��� ������ ���������� �� ������
        Assertions.assertFalse(duplicates.isEmpty());
    }

    @Test
    void findDuplicateProductsAndPrintExecutionTime() {
        ProductDuplicatesFinder finder = new ProductDuplicatesFinder();

        // ������ ������ � �������� ���������� ����������
        Assertions.assertDoesNotThrow(finder::findDuplicateProductsAndPrintExecutionTime);
    }
}
