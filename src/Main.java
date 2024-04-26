import java.util.Scanner;

public class Main {
    private static final MovieListingApp app = new MovieListingApp();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Movie Listing App!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Register with Email");
            System.out.println("2. Search Movies");
            System.out.println("3. View Movie Details");
            System.out.println("4. Add to Favorites");
            System.out.println("5. Remove from Favorites");
            System.out.println("6. Search Favorites");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerWithEmail();
                    break;
                case 2:
                    searchMovies();
                    break;
                case 3:
                    viewMovieDetails();
                    break;
                case 4:
                    addToFavorites();
                    break;
                case 5:
                    removeFromFavorites();
                    break;
                case 6:
                    searchFavorites();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        }
    }

    private static void registerWithEmail() {
        System.out.print("Enter your email address: ");
        String email = scanner.nextLine();
        if (app.registerWithEmail(email)) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Invalid email format. Please try again.");
        }
    }

    private static void searchMovies() {
        System.out.print("Enter search query: ");
        String query = scanner.nextLine();
        app.searchMovies(query).forEach(System.out::println);
    }

    private static void viewMovieDetails() {
        System.out.print("Enter movie title: ");
        String title = scanner.nextLine();
        Movie movie = app.getMovieDetails(title);
        if (movie != null) {
            System.out.println("Title: " + movie.getTitle());
            System.out.println("Cast: " + movie.getCast());
            System.out.println("Category: " + movie.getCategory());
            System.out.println("Release Date: " + movie.getReleaseDate());
            System.out.println("Budget: " + movie.getBudget());
        } else {
            System.out.println("Movie not found.");
        }
    }

    private static void addToFavorites() {
        System.out.print("Enter your email address: ");
        String email = scanner.nextLine();
        System.out.print("Enter movie title: ");
        String title = scanner.nextLine();
        if (app.addToFavorites(email, title)) {
            System.out.println("Movie added to favorites successfully.");
        } else {
            System.out.println("Failed to add movie to favorites. User or movie not found.");
        }
    }

    private static void removeFromFavorites() {
        System.out.print("Enter your email address: ");
        String email = scanner.nextLine();
        System.out.print("Enter movie title: ");
        String title = scanner.nextLine();
        if (app.removeFromFavorites(email, title)) {
            System.out.println("Movie removed from favorites successfully.");
        } else {
            System.out.println("Failed to remove movie from favorites. User or movie not found in favorites.");
        }
    }

    private static void searchFavorites() {
        System.out.print("Enter your email address: ");
        String email = scanner.nextLine();
        System.out.print("Enter search query: ");
        String query = scanner.nextLine();
        app.searchFavorites(email, query).forEach(System.out::println);
    }
}
