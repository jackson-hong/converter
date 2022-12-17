package domain;

import com.jackson.converter.domain.Alphabet;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AlphabetTest {

    @Test
    void order_capital(){
        Alphabet alphabet = new Alphabet('A');
        assertThat(alphabet.compareTo(new Alphabet('a'))).isEqualTo(-1);
    }

    @Test
    void order_same(){
        Alphabet alphabet = new Alphabet('A');
        assertThat(alphabet.compareTo(new Alphabet('A'))).isEqualTo(1);
    }

    @Test
    void order(){
        Alphabet alphabet = new Alphabet('B');
        assertThat(alphabet.compareTo(new Alphabet('A'))).isEqualTo(1);
    }
}
