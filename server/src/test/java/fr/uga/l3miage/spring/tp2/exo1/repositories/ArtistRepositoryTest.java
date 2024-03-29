package fr.uga.l3miage.spring.tp2.exo1.repositories;


import fr.uga.l3miage.spring.tp2.exo1.models.ArtistEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;

import static fr.uga.l3miage.exo1.enums.GenreMusical.RAP;


@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
public class ArtistRepositoryTest {
    @Autowired
    private ArtistRepository artistRepositoryTest;

    @Test
    void countByGenreMusicalEquals(){
    //given
        ArtistEntity artistEntity = ArtistEntity
                .builder()
                .name("Sami")
              .biography("Sami est un rappeur")
                .genreMusical(RAP)
                .build();

        ArtistEntity artistEntity2 = ArtistEntity
                .builder()
                .name("Sami2")
                .biography("sami 2 est un rappeur aussi")
              .genreMusical(RAP)
                .build();

        artistRepositoryTest.save(artistEntity);
        artistRepositoryTest.save(artistEntity2);

        //when

        int istEntitiesResponses = artistRepositoryTest.countByGenreMusicalEquals(RAP);

        //then
        assertThat(istEntitiesResponses).isEqualTo(2);
    }
}
