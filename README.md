# MyContactsApp-OOPS
# UC1 - User Registration
Actor:Create user

## Description
User is created with name,emaild,phonenumber.

## Concepts used
Typecasting,constructor,inheritance

# UC2-UserAuthentication
Actor Registered User

## Description
User logs in with their registered email id to access the contact list. This is used for user authentication.

## Concepts used
Password hashing with MessageDigest, session management, Optional for handling login results.
# UC3-User Profile Management
Actor:Logged in user

## Description
User updates profile information, changes password, or manages preferences.

## Concepts used
User class with setter methods, validation encapsulated in methods
# UC4-User Contact Management
Actor:Logged in user

## Description
User adds a new contact with name, phone numbers, email addresses, and optional fields

## Concepts used
Collections (List for multiple phones/emails), LocalDateTime for timestamps, UUID for unique IDs
# UC5-View contact Management
Actor:Logged in user

## Description
User views complete information of a specific contact.

## Concepts used
String formatting, Optional for nullable fields, immutable view objects
# UC6-Edit Contact
Actor:Logged in user

## Description
User modifies existing contact information.

## Concepts used
Deep copy vs shallow copy, defensive copying, validation before state change
# UC7-Delete Contact
Actor:Logged in user

## Description
User removes a contact from their list with confirmation.

## Concepts used
Lifecycle management, cascade delete for related entities
## UC8-Bulk Operations
Actor:Logged in user

# Description
User performs operations on multiple contacts (delete, tag, export).

# Concepts used
Streams API, lambda expressions, method references, batch processing
# UC9-Search COntacts
Actor:Logged in user

## Description
User searches contacts by name, phone, email, or tags.

## Concepts used
Predicate interface, Stream filtering, regex pattern matching, case-insensitive comparison
# UC10-Advanced Filtering
Actor:Logged in user

## Description
User applies multiple filters (by tag, date added, frequently contacted)..

## Concepts used
Comparator for sorting, multi-level filtering with streams, functional interfaces
# UC11 - Create and Manage Tags
Actor:Logged in user

## Description
User creates custom tags (Family, Work, Friends) for organizing contacts.

## Concepts used
Set for storing unique tags Overriding equals() and hashCode()
# UC12 - Apply Tags to Contacts
Actor:Logged in user

## Description
User assigns one or multiple tags to contacts.

## Concepts used
add() and remove() methods in collections Basic collection handling using loops
