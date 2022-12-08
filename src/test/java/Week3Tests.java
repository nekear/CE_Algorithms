import com.github.DiachenkoMD.week3.n1_money_exchange.MoneyExchange;
import static org.junit.jupiter.api.Assertions.*;

import com.github.DiachenkoMD.week3.n3_car_fueling.CarFueling;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class Week3Tests {
    @Test
    public void money_exchange(){
        assertEquals(MoneyExchange.exchange(2), 2);
        assertEquals(MoneyExchange.exchange(28), 6);
    }

    @Nested
    class car_fueling{
        @Test
        public void test1(){
            assertEquals( 2, CarFueling.countRefills(0, 400, new int[]{0, 200, 375, 550, 750, 950}));
        }

        @Test
        public void test2(){
            assertEquals(-1, CarFueling.countRefills(0, 3, new int[]{0, 1, 2, 5, 9, 10}));
        }

        @Test
        public void test3(){
            assertEquals(0, CarFueling.countRefills(0, 250, new int[]{0, 100, 150, 200}));
        }
    }

    @Nested
    class car_fueling_custom{
        @Test
        public void test1(){
            assertEquals( 1, CarFueling.countRefills(0, 3, new int[]{0, 2,3,4, 6}));
        }

        @Test
        public void test2(){
            assertEquals(2, CarFueling.countRefills(0, 3, new int[]{0, 1, 2, 3, 4, 5, 6, 7}));
        }

        @Test
        public void test3(){
            assertEquals(-1, CarFueling.countRefills(0, 3, new int[]{0, 1, 2, 6, 7}));
        }

        @Test
        public void test4(){
            assertEquals(4, CarFueling.countRefills(0, 3, new int[]{0, 1, 2, 4, 5, 6, 7, 8, 10, 11, 12}));
        }

        @Test
        public void test5(){
            assertEquals(4, CarFueling.countRefills(0, 3, new int[]{0, 1, 2, 4, 5,  8, 9, 10, 11, 12}));
        }
    }
}
