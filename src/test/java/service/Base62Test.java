package service;


import com.url.shortener.service.BaseException;
import com.url.shortener.service.BaseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Base62Test {

    BaseService baseService = new BaseService();

    @Test
    void throwIfNegativeNumber() {
        assertThrows(BaseException.class, () -> baseService.encode(-1));
    }

    @Test
    void ifParameterIsZero() {
        assertEquals("0", baseService.encode(0));
    }

    @Test
    void ifNumberGreaterThanMax() {
        assertThrows(BaseException.class, () -> baseService.decode("2lkCB12"));
    }

    @Test
    void ifOverflowInteger() {
        assertThrows(BaseException.class, () -> baseService.decode("2lkCB2"));
    }

    private static Stream<Arguments> data() {
        return Stream.of(Arguments.of(10, "a"), Arguments.of(1024, "gw"), Arguments.of(2147483647, "2lkCB1"));
    }

    @ParameterizedTest
    @MethodSource("data")
    void correctEncode(int base10, String base62) {
        assertThat(baseService.encode(base10), is(base62));
    }

    @ParameterizedTest
    @MethodSource("data")
    void correctDecode(int base10, String base62) {
        assertThat(baseService.decode(base62), is(base10));
    }
}