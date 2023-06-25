package service;

import com.url.shortener.dto.UrlLongRequest;
import com.url.shortener.entity.Url;
import com.url.shortener.repository.UrlRepo;
import com.url.shortener.service.BaseService;
import com.url.shortener.service.UrlService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UrlTest {
    @Mock
    UrlRepo repo;

    @InjectMocks
    UrlService urlService;

    @Mock
    BaseService baseService;

    @Test
    public void convertToShortTest() {
        Url url = new Url();
        url.setLongUrl("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        url.setCreatedDate(new Date());
        url.setId(10);

        when(repo.save(any(Url.class))).thenReturn(url);
        when(baseService.encode(url.getId())).thenReturn("a");

        UrlLongRequest request = new UrlLongRequest();
        request.setLongUrl("https://www.youtube.com/watch?v=dQw4w9WgXcQ");

        assertEquals("a", urlService.convertToShort(request));
    }

    @Test
    public void getLongUrlTest() {
        when(baseService.decode("a")).thenReturn(10);

        Url url = new Url();
        url.setLongUrl("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        url.setCreatedDate(new Date());
        url.setId(10);

        when(repo.findById(10)).thenReturn(Optional.of(url));

        assertEquals("https://www.youtube.com/watch?v=dQw4w9WgXcQ", urlService.getLongUrl("a"));
    }
}
