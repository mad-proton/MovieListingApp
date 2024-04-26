import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class MovieListingAppTest {

    private MovieListingApp app;

    @BeforeEach
    void setUp() {
        app = new MovieListingApp();
    }

    @Test
    void testRegisterWithEmail() {
        assertTrue(app.registerWithEmail("example@example.com"));
        assertFalse(app.registerWithEmail("invalid_email"));
    }

    @Test
    void testSearchMovies() {
        Movie movie1 = new Movie("Inception", "Leonardo DiCaprio", "Sci-Fi", new Date(), 160000000);
        Movie movie2 = new Movie("The Matrix", "Keanu Reeves", "Action", new Date(), 63000000);
        app.addMovie(movie1);
        app.addMovie(movie2);

        assertEquals(1, app.searchMovies("Inception").size());
        assertEquals(1, app.searchMovies("Sci-Fi").size());
        assertEquals(1, app.searchMovies("Keanu Reeves").size());
        assertEquals(0, app.searchMovies("Romance").size());
    }

    @Test
    void testViewMovieDetails() {
        Movie movie1 = new Movie("Inception", "Leonardo DiCaprio", "Sci-Fi", new Date(), 160000000);
        app.addMovie(movie1);

        assertEquals(movie1, app.getMovieDetails("Inception"));
        assertNull(app.getMovieDetails("Non-existent Movie"));
    }

    @Test
    void testAddToFavorites() {
        app.registerWithEmail("user@example.com");
        Movie movie1 = new Movie("Inception", "Leonardo DiCaprio", "Sci-Fi", new Date(), 160000000);
        app.addMovie(movie1);

        assertTrue(app.addToFavorites("user@example.com", "Inception"));
        assertFalse(app.addToFavorites("user@example.com", "Non-existent Movie"));
    }

    @Test
    void testRemoveFromFavorites() {
        app.registerWithEmail("user@example.com");
        Movie movie1 = new Movie("Inception", "Leonardo DiCaprio", "Sci-Fi", new Date(), 160000000);
        app.addMovie(movie1);
        app.addToFavorites("user@example.com", "Inception");

        assertTrue(app.removeFromFavorites("user@example.com", "Inception"));
        assertFalse(app.removeFromFavorites("user@example.com", "Non-existent Movie"));
    }

    @Test
    void testViewPersonalDetailsAndFavorites() {
        app.registerWithEmail("user@example.com");
        app.registerWithEmail("anotheruser@example.com");
        Movie movie1 = new Movie("Inception", "Leonardo DiCaprio", "Sci-Fi", new Date(), 160000000);
        app.addMovie(movie1);
        app.addToFavorites("user@example.com", "Inception");

        assertEquals(2, app.getUserPersonalDetails("user@example.com").size());
        assertEquals(2, app.getUserPersonalDetails("nonexistent@example.com").size());

        assertEquals(1, app.getUserFavorites("user@example.com").size());
        assertEquals(0, app.getUserFavorites("anotheruser@example.com").size());
    }

    @Test
    void testSearchFavorites() {
        // Register a user and add movies to their favorites
        app.registerWithEmail("user@example.com");
        Movie movie1 = new Movie("Inception", "Leonardo DiCaprio", "Sci-Fi", new Date(), 160000000);
        Movie movie2 = new Movie("The Matrix", "Keanu Reeves", "Action", new Date(), 63000000);
        app.addMovie(movie1);
        app.addMovie(movie2);
        app.addToFavorites("user@example.com", "Inception");
        app.addToFavorites("user@example.com", "The Matrix");

        // Test searching within user's favorites
        assertEquals(1, app.searchFavorites("user@example.com", "Inception").size());
        assertEquals(2, app.searchFavorites("user@example.com", "a").size()); // Both movies contain 'a' in title or cast
        assertEquals(0, app.searchFavorites("user@example.com", "Romance").size()); // No romance movies in favorites
    }
}
