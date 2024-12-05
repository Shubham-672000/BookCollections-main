The issue you're encountering occurs due to the recursive nature of the bidirectional relationship between Category and Book in a Many-to-One and One-to-Many mapping. In a bidirectional relationship, if the entities reference each other directly without proper handling, serializing the entities into JSON will lead to infinite recursion.

Root Cause
Bidirectional Relationship Issue:

In a bidirectional relationship, Category has a List<Book> (One-to-Many), and Book has a Category reference (Many-to-One). When serializing one side, the serializer tries to serialize the other side, which again references back to the first entity, causing a loop.
Jackson Serialization:

By default, Jackson (used in Spring Boot for JSON processing) tries to serialize all fields, including relationships. Since both entities reference each other, Jackson keeps traversing the relationship endlessly.
Solution
To fix this issue, you need to break the recursion during serialization. You can achieve this using one of the following approaches:

1. Use @JsonIgnore to Break the Loop
Add @JsonIgnore on one side of the relationship to prevent serialization of the recursive field.
java
Copy code
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookName;
    private String authorName;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore // Prevent recursion during serialization
    private Category category;

    // Getters and Setters
}
This will ensure that while serializing Book, the Category field will not be included in the JSON, breaking the recursive chain.

2. Use @JsonManagedReference and @JsonBackReference
These annotations are specifically designed to handle bidirectional relationships in JSON serialization.
java
Copy code
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cId;

    private String cName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonManagedReference // Forward part of the relationship
    private List<Book> bookList = new ArrayList<>();

    // Getters and Setters
}

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookName;
    private String authorName;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference // Backward part of the relationship
    private Category category;

    // Getters and Setters
}
How it works:

@JsonManagedReference marks the parent side of the relationship (e.g., Category).
@JsonBackReference marks the child side of the relationship (e.g., Book).
This ensures only one direction of the relationship is serialized, breaking the loop.
3. Use DTOs (Data Transfer Objects)
Instead of exposing your entity classes directly, use DTOs to control what gets serialized.
Create separate DTOs for Category and Book that only include the fields you want to expose.
java
Copy code
public class CategoryDTO {
    private Long cId;
    private String cName;
    private List<BookDTO> bookList;

    // Constructor, Getters, and Setters
}

public class BookDTO {
    private Long id;
    private String bookName;
    private String authorName;

    // Constructor, Getters, and Setters
}
Use a mapper (like MapStruct or manual conversion) to convert entities into DTOs before returning them in your API.

4. Use Jackson Configuration
If you don’t want to annotate your entity classes, configure Jackson globally to handle circular references:
java
Copy code
@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, false);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper;
    }
}
Recommended Approach
Using @JsonManagedReference and @JsonBackReference is a clean and effective way to handle bidirectional relationships for serialization. If you need more control, consider using DTOs to decouple the database entities from the API response.