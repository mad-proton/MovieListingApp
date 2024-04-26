import java.util.*;

public class MovieListingApp {
    private Map<String, Movie> movies;
    private Map<String, String> users;
    private Map<String, Set<String>> userFavorites;

    public MovieListingApp() {
        movies = new HashMap<>();
        users = new HashMap<>();
        userFavorites = new HashMap<>();
    }

    // Function to add a movie
    public void addMovie(Movie movie) {
        movies.put(movie.getTitle(), movie);
    }

    // Function to register a user with email
    public boolean registerWithEmail(String email) {
        if (email == null || !email.contains("@")) {
            return false; // Invalid email format
        }
        users.put(email, email);
        userFavorites.put(email, new HashSet<>());
        return true;
    }

    // Function to search for movies by title, cast, or category
    public List<Movie> searchMovies(String query) {
        List<Movie> result = new ArrayList<>();
        for (Movie movie : movies.values()) {
            if (movie.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                    movie.getCast().toLowerCase().contains(query.toLowerCase()) ||
                    movie.getCategory().toLowerCase().contains(query.toLowerCase())) {
                result.add(movie);
            }
        }
        Collections.sort(result, Comparator.comparing(Movie::getTitle));
        return result;
    }

    // Function to get details of a movie by title
    public Movie getMovieDetails(String title) {
        return movies.get(title);
    }

    // Function to add a movie to user's favorites
    public boolean addToFavorites(String userEmail, String movieTitle) {
        if (!users.containsKey(userEmail) || !movies.containsKey(movieTitle)) {
            return false; // User or movie not found
        }
        userFavorites.get(userEmail).add(movieTitle);
        return true;
    }

    // Function to remove a movie from user's favorites
    public boolean removeFromFavorites(String userEmail, String movieTitle) {
        if (!users.containsKey(userEmail) || !userFavorites.get(userEmail).contains(movieTitle)) {
            return false; // User or movie not found in favorites
        }
        userFavorites.get(userEmail).remove(movieTitle);
        return true;
    }

    // Function to get user's favorites
    public Set<String> getUserFavorites(String userEmail) {
        return userFavorites.getOrDefault(userEmail, new HashSet<>());
    }

    public Set<String> getUserPersonalDetails(String userEmail) {
        return userFavorites.keySet(); // Assuming personal details include all registered emails
    }


    public List<Movie> searchFavorites(String userEmail, String query) {
        Set<String> favorites = userFavorites.getOrDefault(userEmail, new HashSet<>());
        List<Movie> result = new ArrayList<>();
        for (String title : favorites) {
            Movie movie = movies.get(title);
            if (movie != null && (movie.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                    movie.getCast().toLowerCase().contains(query.toLowerCase()) ||
                    movie.getCategory().toLowerCase().contains(query.toLowerCase()))) {
                result.add(movie);
            }
        }
        Collections.sort(result, Comparator.comparing(Movie::getTitle));
        return result;
    }

    // Function to get all registered users' emails (personal details)
    public Set<String> getAllUserEmails() {
        return users.keySet();
    }

    // Getters and Setters for movies (if needed)
    public Map<String, Movie> getMovies() {
        return movies;
    }

    public void setMovies(Map<String, Movie> movies) {
        this.movies = movies;
    }

    // Getters and Setters for users (if needed)
    public Map<String, String> getUsers() {
        return users;
    }

    public void setUsers(Map<String, String> users) {
        this.users = users;
    }

    // Getters and Setters for userFavorites (if needed)
    public Map<String, Set<String>> getUserFavorites() {
        return userFavorites;
    }

    public void setUserFavorites(Map<String, Set<String>> userFavorites) {
        this.userFavorites = userFavorites;
    }
}
