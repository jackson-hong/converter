package domain;

import com.jackson.converter.controller.ConvertResponse;
import com.jackson.converter.controller.base.Response;
import com.jackson.converter.domain.Converter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConverterTest {

    @Test
    void eng_number_divide(){
        Converter converter = new Converter("洪#A0Aa1bA02Ca");
        converter.convert();

        Converter expect = Converter.builder()
                .initialText("洪#A0Aa1bA02Ca")
                .result("A0A0A1a2abC")
                .build();

        assertThat(converter).isEqualTo(expect);
    }

    @Test
    void quotient_10(){
        Converter converter = Converter.builder()
                .result("12345678910")
                .build();

        ConvertResponse response = converter.toResponse(10);

        ConvertResponse expect = ConvertResponse.builder()
                .quotient("1234567891")
                .remainder("0")
                .build();

        assertThat(response).isEqualTo(expect);
    }

    @Test
    void quotient_3(){
        Converter converter = Converter.builder()
                .result("12345678910")
                .build();

        ConvertResponse response = converter.toResponse(3);

        ConvertResponse expect = ConvertResponse.builder()
                .quotient("123456789")
                .remainder("10")
                .build();

        assertThat(response).isEqualTo(expect);
    }
}
