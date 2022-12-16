package domain;

import com.jackson.converter.domain.Converter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConverterTest {

    @Test
    void eng_number_divide(){
        Converter converter = new Converter("洪#A0Aa1bA02Ca");
        converter.divide();

        Converter expect = Converter.builder()
                .initialText("洪#A0Aa1bA02Ca")
                .engText("AAAaabC")
                .numberText("0012")
                .build();

        assertThat(converter).isEqualTo(expect);
    }
}
