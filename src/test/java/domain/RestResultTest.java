package domain;

import com.jackson.converter.common.type.TextType;
import com.jackson.converter.domain.RestResult;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class RestResultTest {
    @Test
    void organize(){
        String html = "<html>\n" +
                "<head><title>jackson</title></head>\n" +
                "<body>\n" +
                "<center><h1>jackson</h1></center>\n" +
                "<hr><center> NWS </center>\n" +
                "</body>\n" +
                "</html>";
        ResponseEntity<String> result = ResponseEntity.ok(html);
        RestResult restResult = new RestResult(result);
        assertThat(restResult.organize(TextType.NO_HTML)).isEqualTo("jackson jackson NWS");
    }
}
