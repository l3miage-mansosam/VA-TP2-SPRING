package fr.uga.l3miage.spring.tp2.exo1.component;
import fr.uga.l3miage.spring.tp2.exo1.components.SongComponent;
import  fr.uga.l3miage.spring.tp2.exo1.models.SongEntity;

import fr.uga.l3miage.spring.tp2.exo1.exceptions.technical.NotFoundSongEntityException;
import fr.uga.l3miage.spring.tp2.exo1.repositories.SongRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Duration;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
 class SongComponentTest {
    @Autowired
    private SongComponent SongComponent;
    @MockBean
    private SongRepository songRepository;

    @Test
    void getSongNotFound() {
        //Given
        when(songRepository.findById(anyString())).thenReturn(Optional.empty());

        //then - when
        assertThrows(NotFoundSongEntityException.class, () -> SongComponent.getSongEntityById("test"));

    }
    @Test
    void getSongFound(){
        //Given
        SongEntity songEntity = SongEntity.builder()
                .title("redemption Song")
                .duration(Duration.ofMinutes(3))
                .build();


        when(songRepository.findById(anyString())).thenReturn(Optional.of(songEntity));

        // when - then
        assertDoesNotThrow(()->SongComponent.getSongEntityById("test"));
    }
}