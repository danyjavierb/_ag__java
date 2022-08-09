I am going to address the improvements at the level of known patterns to have a respectably good level of code quality, to have a short and quantifiable step by step.

Another thing to mention is that you don't modify the api contracts, a refactor shouldn't have that responsibility.

### The single-responsibility principle: "There should never be more than one reason for a class to change." In other words, every class should have only one responsibility.

The responsibility of the controller must be to orchestrate the specific business logic towards the use case that must be resolved as an input to the output protocol, in this case http.

What I did was move the responsibility to use cases for each mention type, be it tweet or post, in the same way it can be extended at a functional level for each use case that must orchestrate the logic of secondary effects I/O services in this case interactions of DB, then following the same principle, separate the specific logic of side effects into services, but the responsibility of the service must be to orchestrate the I/O interactions, not knowing the proper implementation of the type of I/O, then the responsibility is segregated to repositories that if they interact directly with the DB, the principle is now respected and validated.


### The open–closed principle: "Software entities ... should be open for extension, but closed for modification."

In the original implementation we should modify the implementation of the controller and its internal code, by segmenting the responsibility in use cases for each type of social network interaction it is a matter of extending the implemented strategy (pattern implemented to encourage extensibility) and adding a new case for Reddit for example.

Then with this change the pattern is respected now.

###  The Liskov substitution principle: "Functions that use pointers or references to base classes must be able to use objects of derived classes without knowing it."

in the case of the original implementation it was necessary to be right about the specific implementation of an interaction type to save in the database, now the controller currently only knows that it needs AnalyzeMention but not which one, which allows us to use a strategy to decide which implementation best suits the payload sent by the user, although this pattern must be taken care of because it could increase the complexity of the solution, it keeps it simple (KISS) and encourages the solution to scale easily.

### The interface segregation principle: "Clients should not be forced to depend upon interfaces that they do not use."

in the case of services we are using abstractions on the operations of each service method and not creating a generic one, so they implement a very well segmented one with the expected generic "implements CreateMentionRecord<PostEntity>", which encourages good design and it respects the pattern by encouraging scalability and reducing the cognitive load of the service, which is something that neither modern architects nor teams take into account, affecting newcomers and over time the delivery of the solution.


### The dependency inversion principle: "Depend upon abstractions, concretions."

In the current solution, everything was coupled, which made it very complicated to test the solution. In my implementation, everything is separated by responsibilities and dependencies passed by the constructor (well, of course, they use Spring's own syntactic suggestions, such as beans, but they are applicable to any object-oriented and functional language since it is the same principle as the monad Reader for example).

In short, by not using concretions, it is possible in the testing stage to replace the specific implementations of repository services, use cases, etc., and have good coverage.


code reuse
Package principles
Don't repeat yourself (DRY)
GRASP (object-oriented design)
KISS principle
You aren't gonna need it (YAGNI)

were also taken into account we can talk about them in detail in another instance.

## Aggregates:

Swagger, documenting the API is crucial to work between teams, so it helps a lot to new commers and people who don't have api context.

Actuator, decreases the cognitive load at the time of development is a good package to help the engineer during development,

hybernate's ddl-auto helps with tests like this when migrating models to the db, not recommended in a production product.

Using CrudRepository to quickly access DB operations helps, although you have to take into account the load of the queries, the complexity of the model and, if necessary, moving everything to a raw query.

Graalvm, the generated images could be very large, it is recommended to be more cloud native friendly.

Thank you for reading this document, a hug.

Dany Javier Bautista M
danyjavierb@gmail.com