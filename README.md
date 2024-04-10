Spring project of a back-end for a website or webapp, that must save a list of games that users likes it. A user can have many games, and games can have many users so that it can be implemented, in the future, a kind of games recommendation to users with similar game lists.

Javadoc was used to document the project, or most of it. The password field must have to be saved in the database, but not returned, so a DTO pattern is implemented.

Also Mockito is used to test the Repositories, Services and Controllers of the Users and Games models. The use of h2 database is used in testing.
