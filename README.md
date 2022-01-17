# Java Programming Nanodegree 

## Java Fundamentals 

### Java Programming Basics

![keywords](./images/keywords.png)

* `native`: A native method is a Java method (either an instance method or a class method) whose implementation is also written in another programming language such as C/C++.
    * Example:
        ```java
        public class DateTimeUtils {
            public native String getSystemTime();

            static {
                System.loadLibrary("nativedatetimeutils");
            }
        }
        ```

* `strictfp`: is a modifier in the Java programming language that restricts floating-point calculations to ensure portability. The strictfp command was introduced into Java with the Java virtual machine (JVM) version 1.2 and is available for use on all currently updated Java VMs.

* `synchronized`: Synchronization in java is the capability to control the access of multiple threads to any shared resource. In the Multithreading concept, multiple threads try to access the shared resources at a time to produce inconsistent results. The synchronization is necessary for reliable communication between threads.

* `throw` and `throws`: The throw keyword is used to throw an exception explicitly. It can throw only one exception at a time. The throws keyword can be used to declare multiple exceptions, separated by a comma.

* `transient`: 
    * Serialization is the process of converting an object into a byte stream, and deserialization is the opposite of it.
    * When we mark any variable as transient, then that variable is not serialized. Since transient fields aren't present in the serialized form of an object, the deserialization process would use the default values for such fields when creating an object out of the serialized form.
    * Example: 
        ```java
        public class Book implements Serializable {
            private static final long serialVersionUID = -2936687026040726549L;
            private String bookName;
            private transient String description;
            private transient int copies;
            // getters and setters
        }
        ```

* Compiling and Running the Code

    * When you want to test your program, you cannot simply run it—you must first compile the code. You can do this by using the `javac` command, followed by the name of the file you want to compile. In the below example, our file is called `Main.java`. So in this case, we will compile the code by running:

    * `javac Main.java`

    * Once the code is compiled, we can run it. To do this, we simply enter java followed by the name of the file. In this exercise it will be:

    * `java Main.java`

* Primitive Values vs Reference Values

    * In Java, there are two general kinds of values we can assign to a variable:

        * A **primitive value** is simply a value, by itself, with no additional data.
        * A **reference value** is a value that refers to an object stored in another location in memory.

    * Objects bundle the primitive value up with additional useful information and behavior. We'll get into how to create and use objects more later on.

    * ![primitive_types](./images/primitive_types.png) 

    * Object Reference Types
        * `Integer age = 42;`

        *  Notice that the keywords for creating object variables begin with an uppercase letter (String and Integer) rather than the lowercase used for primitives (e.g. int and long).

        * Again, one of the benefits of creating an object is that it can include additional data and behavior. For example, String objects are bundled with a method called length that lets us get the length of the string. 

* Type Casting

    * Type casting is changing one type into another type. There are two kinds of type casting: Automatic and manual.

    * Automatic Type Casting

        * Automatic type casting converts a smaller type into a larger type. For example:

        * ```java
            int intNumber = 3;
            double doubleNumber = intNumber;
            System.out.println(doubleNumber);
            ```
        
        * When we print `doubleNumber`, the value will be `3.0`. Notice that there is **no precision lost** going from a smaller type into a larger type. We started with `3` and ended up with `3.0`.
    
    * Manual Type Casting

        * `Manual` type casting is necessary when we want to do either of these things:
            * Convert a larger type into a smaller type
            * Convert one object type into another

        * For example, here we are converting from a larger type (double) to a smaller type (int):

        * ```java
            double doubleNumber = 3.5;
            int intNumber = (int)doubleNumber;
            System.out.println(intNumber);
            ```
        
        * The resulting value will be `3`, not `3.5`. When we go from a larger type into a smaller type, precision is lost. Java cuts off additional data that will not fit in the casted type. So when we go from a double to an int, any values that are not integers will be removed. This is called **truncation**.

* Stack vs Heap

    * Java uses two different memory regions when running an application: The stack and the heap.

        * The stack is used to store primitives and object references, while the heap is used to store the objects themselves.
        * Items in the stack get added and removed as a given method executes, while objects in the heap stay until the application is done (or at least, until there are no object references using them from anywhere in the program, at which point they are removed by the garbage collector).
        * Items are removed from the stack in a Last-In-First-Out (LIFO) order, meaning that the last element you added to the stack is the first that gets popped off the stack.
        * Remember that the items in a stack are only maintained as long as the related method is running. By the time a given method has finished running, all of the items on the stack for that method will have been removed.
        * Objects in the heap are accessible from anywhere in the program, while items on a given stack can only be accessed by the related method.

    * ![stack_vs_heap](./images/stack_vs_heap.png) 

* Access Modifiers

    * When we are writing our code, we sometimes need to restrict access to certain data in the application in order to ensure it doesn't get modified in an unintended or harmful way.

    * Classes, Subclasses, and Packages

        * Access modifiers control things like whether the data inside of a class can be accessed only by other code inside of the class or also by code elsewhere in the application.

    * Types of Access Modifiers

        * Public means the class can be accessed from everywhere. If you have a method on a class that you want to expose to all other classes, then use this access modifier.

        * Private means only the defining class can access the data. This provides security, by not allowing other classes to change the data directly. Instead, they must make changes to the data via the provided methods only.

        * Protected means that access is restricted to the defining class, package, or subclass. This will be useful when we get into subclasses and inheritance in a later lesson, as it will allow our subclasses to use variables and methods from the parent class.

        * Default means access is restricted to the defining class or the package. This can be used when we have classes inside the same package that we may want to expose data and methods too.
    
    * ![access_modifiers](./images/access_modifiers.png)

    * ```java
        private void methodName()
        void methodName()  // The default is no access modifier
        protected void methodName()
        public void methodName()
        ```
    
    * Access Modifiers on Variables

        * And we can apply access modifiers to variables as well! We will get into this in detail in the lessons on object-oriented programming. Here are some examples—again, just so you can get the idea:

        * ```java
            private int number;
            int number3;  // Default is no access modifier
            protected int number2;
            public int number1;
            ```

* Array
    * An array is a fixed-sized data structure that is used to store multiple values—such as a series of phone numbers, as we saw in the video.

    * `int [] numbers  = {1, 2, 3, 4};`

    * Here's another way we can create an array and add values to it:

    * `int [] numbers = new int[4];`

    * This approach uses the `new` keyword to create a new array object of size 4, and then we assign values to the four spaces created in the array. This style will feel more normal to you after we have worked with classes and objects later in the course.

* JavaDoc

    * avaDoc is a documentation generator that produces a searchable HTML document defining the classes and interfaces of an application. This makes it easy for you and other developers to understand the API of an application.

    * JavaDoc Comments

        * The JavaDoc tool reads through Java files and parses certain parts of the code to automatically generate useful documentation. One part of the code that will be picked up by JavaDoc is a JavaDoc comment (or simply doc comment).

        * JavaDoc comments are typically added:

            * At the top of a class, right before the class name
            * For each method in a class We'll get some practice with this when we start defining classes later in the course.
    
    * And here's an example:
        * ```java
            /** This program HelloWorld produces a standard output
            *  displaying "Hello World"
            * 
            * @author The author of the class
            * @see A reference to another class
            */
            ```
    
    * Parts of a JavaDoc Comment
        * Notice that JavaDoc comments are broken down into two parts:
            * The description
            * Block tags  

    * In the above example, the description is the first part of the comment, and the block tags are the last part (`@author` and `@see`).

    * In this example, we have a doc comment for a method:

    * ```java
        /**
        * This method displays a simple text output to a provided name
        * 
        *
        * @param name The name of the person we want to say “Hi” too
        * @return results Returns true if the name was printed or
        * false if it failed 
        */
        ```
    
    * Example:
        * ```java
            /**
            * @author jeff.phillips
            *
            */
            public class JavaDocExample {

                /**
                * 
                * @param string
                * @param number
                * @return
                */
                public String sampleMethod(String string, int number) {
                    return "Result";
                }

            }
            ```

### Defining Classes

* ![oo_intro](./images/oo_intro.png)

* ![class_vs_obj](./images/class_vs_obj.png)

* ```java
    public class Student {

        private final String id;
        private final String firstName;
        private final String lastName;

        public Student(String id, String firstName,String lastName){
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getId(){
            return id;
        }

        public String getFirstName(){
            return lastName;
        }

        public String getLastName(){
            return lastName;
        }
    }
    ```

    * Instance Variables vs Class Variables

        * Note the difference between instance variables and class variables.

        * Instance Variables are state variables that can have unique values for each object.

        * Class Variables are state variables that belong to the class itself, and are the same for every object. The static keyword identifies this variable as belonging to the class (not to individual objects).

    * ```java
        public class Dog {

            private String dogType;
            private String dogName;
            private String dogColor;
            private int dogAge;

            public Dog(String dogType, String dogName, String dogColor, int dogAge) {
                super();
                this.dogType = dogType;
                this.dogName = dogName;
                this.dogColor = dogColor;
                this.dogAge = dogAge;
            }

            public String getDogType() {
                return dogType;
            }

            public void setDogType(String dogType) {
                this.dogType = dogType;
            }

            public String getDogName() {
                return dogName;
            }

            public void setDogName(String dogName) {
                this.dogName = dogName;
            }

            public String getDogColor() {
                return dogColor;
            }

            public void setDogColor(String dogColor) {
                this.dogColor = dogColor;
            }

            public int getDogAge() {
                return dogAge;
            }

            public void setDogAge(int dogAge) {
                this.dogAge = dogAge;
            }

            @Override
            public String toString() {
                return "Dog type:" + dogType + " Dog name:" + dogName + " Dog Color:" + dogColor + "  Dog age" + dogAge;
            }
        }
        ```

* Garbage Collection

    * Every new object that we instantiate is added to the heap and consumes memory. In programming languages like C or C++, developers are responsible for creating and destroying objects. In contrast, Java has a background process called Garbage Collection (GC) that checks all instantiated objects, and destroys them if they do not have any references—thus freeing up memory, even without any active management on the part of the developer.

### Object-Oriented Programming

* Packages, which are essentially like folders you can use to organize your code and identify exactly which file (or class) you are referring to.
* Inheritance, where one class acquires properties and methods from another class.
* Abstract classes, which cannot be directly instantiated themselves, but that allow us to define the behavior for each of the subclasses.
* Interfaces, which support decoupling and allow us to avoid hardcoding features in an application.
* Polymorphism, which is the ability for an object to take on many forms.

* Packages

    * In Java, we use packages to organize and manage our Java files:

        * If you are used to the concept of namespaces, packages serve essentially the same purpose in Java.
        * Packages are not unlike the traditional folders that you use every day on your computer for organizing your files.
        * By nesting packages and files, we can create a hierarchy that allows us to identify specific files and avoid naming conflicts.

    * The `package` keyword will be located at the top of a Java file, typically on the first line of the file, before the imports and class name. For example:
        * `package project.src.api`

* Inheritance
    * Inheritance is one class acquiring properties and methods from another class. Here are some key points you should remember about inheritance:

        * We want to go from general to specific. The parent or superclass is the most general and the child or subclass is the more specific.

        * By extending the superclass you are stating that the subclass is of the superclass type. When we're not sure if a subclass is inheriting from a parent class, we can use the “is a” test (e.g. a car is a vehicle).

        * The relationship between superclass and subclasses is only one way. The subclasses need to know about the superclass, but the superclass should never know anything about its subclasses.

    * The `Object` Superclass

        * Every class inherits from the superclass Object. Because all objects inherit from the Object class, there are some methods that all objects have, no matter what types they are. For example, all objects have:

            * `clone()`, so that we can clone or make a copy of any object.
            * `equals()`, which we can use to determine if two objects are the same.
            * `hashCode()`, which provides a unique hash code for each object. This is something we'll make use of later on when we need to store and retrieve objects in specific data sets.
            * `toString()`, which we can use to get a description of the current state of an object.

* Polymorphism

    * Polymorphism is the ability of an object to take on many forms.

    * In Java, any kind of inheritance can be used to support polymorphism. In our vehicle example, each of the vehicles has two forms—for instance, a Car object is both a Car and also a Vehicle (since it inherits from the Vehicle class). Any Car object thus has two forms. This is polymorphism.

    * If we wanted to get the speed of all the Car, Boat, and Plane objects, we can easily do this because of polymorphism—we simply create a list containing all objects that are of type Vehicle and get the speed on every Vehicle object, regardless of whatever other types that object might be.

    * ```java
        // Create an array of size 3 and type Vehicle
        Vehicle [] vehicles = new Vehicle[3];

        // Instantiate three new objects and add them to the array.
        // It looks like these are all different types (Car, Plane, and Boat),
        // but they all inherit from the Vehicle class, so in addition to the types
        // they get from their subclasses, they are also all Vehicle objects.
        vehicles[0] = new Car(); 
        vehicles[1] = new Plane();
        vehicles[2] = new Boat();

        // Iterate over the array and print the speed
        // of each of the Vehicle objects.
        for (int i = 0; i < vehicles.length; i++) {
            vehicles[i].speed();
        }
        ```

* Abstract Classes

    * An abstract class has the following key characteristics:

        * It defines the behavior for each of the subclasses, but we cannot directly instantiate the abstract class itself.
        * It allows us to create abstract methods.
            * An abstract method is a method that does not contain an implementation body. Instead, it simply provides a header for the method.
            * Subclasses that extend an abstract class are required to override all abstract methods and provide a specific implementation.
    
    * ```java
        public abstract class Vehicle {
            protected String start;
            protected String stop;
            protected String direction;

            public Vehicle(String start, String stop,
                                        String direction) {
                this.start = start;
                this.stop = stop;
                this.direction = direction;
            }

            public abstract void speed();    

        }

        public class Car extends Vehicle {

        public Car() {
            super("Car start", "Car stop", "Car direction");
            }
            @Override
            public void speed() {
                System.out.println("55");
            }
        }
        ```

* Interfaces

    * Interfaces allow us to avoid hardcoding features in an application. We can move specific implementation details into subclasses, and then use an interface to communicate between the application and the subclasses.

    * Interfaces vs Abstract Classes


        * Here are some of the similarities and differences between abstract classes and interfaces:

        * Abstract class

            * Can have class variables.
            * Can have both **abstract** methods and **concrete** methods that are shared with the subclasses.
            * Can have instance variables, i.e. variables that are specific to individual subclasses.
            * Subclasses can only extend one class.
        
        * Interfaces
            * Can have class variables.
            * Every method in an interface is abstract.
            * **Cannot have instance variables**. Variables in an interface must be the same for every class implementing the interface.
            * Classes can implement more than one interface and **have multiple inheritance**.
    
    * When to Use an Interface
        
        * We use an interface when:

            * We expect unrelated classes will be implementing our interface.
            * We want to support multiple inheritance.
            * We want to specify the behavior for a data type, but we do not care about the implementation.


### Common Types

* Exceptions, which help us handle errors.
* Enums, which help us set variables from a list of predefined values.
* Dates and Calendar, which help us store and retrieve dates.
* Regular Expressions (RegEx) which help us look for string patterns.
* Advanced String features, which will help us manipulate and process strings more efficiently.

* The **Error Class** and the **Exception Class**
* The Java error-handling framework uses two different classes to identify abnormal software events:
    * The **Error class** is used to indicate a serious problem that the application should not try to handle.
    * The **Exception class** is used when there is a less catastrophic event that the application should try to handle.

* The Throwable Class

    * Both Error and Exception classes inherit from the abstract class **throwable**. This means that both errors and exceptions will contain:

        * The **type of problem** – the class type, either **Exception** or **Error**

        * The **problem message** – whatever description you've provided within the class, such as "out of memory"

        * The **stack trace** where the exception occurred – the order in which things ran and the place in that sequence where the problem occurred (this information is used by developers to track down issues and resolve problems in the code)

* Checked vs Unchecked

    * Unchecked Exceptions

        * Unchecked exceptions are exceptions that are unknown to the compiler.
        * Because these exceptions are only known at runtime, they are also referred to as runtime exceptions.
        * They are a result of a programming error, typically arithmetic errors (such as division by 0).
        * Unchecked exceptions are used when when we expect that the caller of the method cannot recover from the exception.
    
    * Checked Exceptions

        * Checked exceptions are known to the compiler.
        * If we are calling a method that potentially throws a checked exception, it must be handled (or we will get an error from the compiler).
        * Checked exceptions are used when we expect that the caller of the method can recover from the exception.

    * ![throwable_exception](./images/throwable_exception.png) 

    * Example: 
        * ```java
            try {
                read();
            }
            catch (FileNotFoundException ex){
                ex.getLocalizedMessage();
            }
            finally {
            }
            ```
    
    * Remember, `FileNotFoundException` is a class and—as with all exceptions—it inherits from the `Throwable` class.

    * **Exception handlers handle one specific Exception class type.**

    * You can create and throw your own exceptions by **extending the Exception classes**

    *  There are two types of exceptions in Java: Checked and unchecked. Checked exceptions will get caught at compile time and will not allow the code to build until they are either in a catch block or thrown. Unchecked (or runtime) exceptions are not checked by the compiler.

* What is an Enum?

    * In many cases when developing software we need to provide a predefined value for a single variable type. Enumerations (or Enums for short) are a special data type of constants that allow a variable to be set from an enumerated list.

    * An Enum is a Class

        * In Java, **the declaration of an Enum defines a class**. This class can exist within another class or as a standalone class.

    * Example:
        * ```java
            enum Stoplight {
                RED,
                YELLOW,
                GREEN
            }
            Stoplight myStoplight = Stoplight.RED;
            ```
        * ```java
            public class Main {
                enum StopLight {
                    RED,
                    YELLOW,
                    GREEN
                }

                public static void main(String[] args) {
                    StopLight myStoplight = Stoplight.RED; 
                    System.out.println(myStoplight);
                }
            }
            ```

* Scanner

    * The `Scanner` class can read and parse simple text. Here are some key points to keep in mind:

        * It parses primitive types and `String` types into tokens.

        * By default it uses whitespaces to delimitate each word. However, it can also use regular expressions.

        * The Scanner class can read from several different types of sources, like strings, files and `System.in` (to get input from the command line).

        * ```java
            Scanner scanner = new Scanner(System.in);
            ```
        
        * ```java
            Scanner scanner = new Scanner("This is a line");
            System.out.println(scanner.nextLine());
            // This is a line
            ```
        
        * ```java
            Scanner scanner = new Scanner("This is a line");

            while(scanner.hasNext()) {
                // In this next example, we are using the next method to read the first token. The next method finds and returns the next complete token.
                System.out.println(scanner.next());
            }
            ```

* Dates and Calendar

    * The Date Class

        * The Date class represents a specific instance in time. We can instantiate a new Date object like so: `Date date = new Date();`
    
    * The Calendar Class

        * The Calendar class is an abstract class that provides methods for manipulating date and time. The basic syntax for instantiating a new Calendar object looks like this: `Calendar calendar = Calendar.getInstance();`


* RegEx

    * Regular expressions (often abbreviated as `RegEx`) are used to match or find strings based on a specialized syntax.

    * The `regEx` package in Java contains three classes to support these operations

        * Pattern
        * Matcher
        * PatternSyntaxException.
    
    * To use RegEx in Java, we have to do two main theings:

        * Create a `Pattern` based on a specialized syntax
        * Use the `Matcher` to determine if the pattern exists in the String provided
    
    * Helpful RegEx Resources

        * As a Java developer, do you need to thoroughly learn and memorize RegEx syntax? Not necessarily. Unless you're using it very heavily, it will probably suffice to look up the expression you need when you need it. Along those lines, here are some handy resources that you may want to bookmark for later reference:

            * For use of the `RegEx` class in Java, see the official Java docs on regular expression syntax.
            
            * For coming up with the regular expression itself, try playing with [`RegExr.com`](https://regexr.com/). You can enter some text and then try out different expressions; when there is a match, the text will be highlighted. The page also has a handy cheatsheet for commonly needed RegEx characters.
    
    * ```java
        String emailRegex = "^(.+)@(.+).(.+)$"
        Pattern pattern = Pattern.compile(emailRegex)

        Matcher matcher = pattern.matcher("jeff@example.com")

        ```
    
* String Methods

    * Java provides advanced memory management for `String` objects by using a `String pool`. A String pool is a way of storing only **one copy of a String**.

    * To understand this, we have to look at what is happening in memory when we create Strings and assign them to variables. Strings in Java are **immutable**, meaning they cannot be changed after they are created. When we "change" the String in a variable, what actually happens is that, behind the scenes, Java creates a **new String** in the String pool—and changes the variable's reference value to point to this new String. The old String object may remain in place, unchanged.

    * Also note that **multiple variables may use the same String reference**. This means that if we have two variables with exactly the same String (e.g., "Hello") they will all reference the same String object.

    * ![strings](./images/strings.png)

    * ![strings2](./images/strings2.png)

    * ![string_ref](./images/string_ref.png)

    * ![strings_methods](./images/strings_methods.png)

### Generics and Collections

* Generics allow us to write methods that handle a whole group of different data types—in other words, to write methods that are generic.

    * The benefits of using Generics are:

        * Stronger type checks at compile time.
        * They remove the need to cast objects.
        * They allow developers to implement generic algorithms.

    * Put another way, generics are a way to parameterize class types into classes, methods, and variables.

    * `List<String> strings = new ArrayList<String>();`

        * ArrayList is `non-synchronized`. Vector is `synchronized`. ArrayList increments 50% of its current size if element added exceeds its capacity. Vector increments 100% of its current size if element added exceeds its capacity

* Collections

    * Collections are a set of data structures that were introduced in Java 5.0 to solve problems with consistency between data structures and to address performance issues. They were developed with the following goals in mind:

        * High performance/efficiency.
        * High degree of interoperability. The new data structures all needed to behave similarly to each other. In practice, this means that Collections data structures implement similar interfaces and extend similar abstract classes.
        * Integrate with existing APIs seamlessly. The Java framework had to extend and adapt to the new data structures easily. They needed to integrate the new data structures seamlessly with little disruption to the existing API.
    
    * The Collections framework consists of several different data structure classes like `List`, `Set`, `Maps` and `Queues`. In addition, there are utility classes like `Collections` and `Arrays` that provide methods for sorting and creating empty lists. Below is an example in which we create a List of type `String` and then add and remove data.

    * ```java
        List<String> myList = new ArrayList<String>();

        myList.add("one");
        myList.add("two");
        myList.add("three");

        //We could also remove the element "one" by using the index 0 instead of the value "one".
        myList.remove("one");
        ```
    
    * ```java
        List<String> names = new LinkedList<String>();
        names.add("Mike");
        names.add("Bob");
        names.add("Alice");

        Iterator<String> iterator = names.iterator();

        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        ```

    * ```java
        public class CollectionsExercise {

            public static void main(String[] args) {

                List<String> listOfItems = new LinkedList<String>();
                listOfItems.add("Mike");
                listOfItems.add("Bob");
                listOfItems.add("Alice");

                for (String name : listOfItems) {
                    System.out.println(name);
                }
            }
        }
        ```

* Sorting Collections

    * The collections framework provides a sort method that can be used to sort lists containing the following types of objects:

        * Strings
        * Wrapper objects
        * User-defined classes
    
    * Strings
        * A list of Strings can be sorted by simply passing the list to the `Collections.sort()` method. In the example bellow, the names will be sorted in ascending order.
        
        * ```java
            List<String> names = new LinkedList<String>();

            names.add("Mike");
            names.add("Bob");
            names.add("Alice");

            Collections.sort(names);
            ```
    
    * Wrapper Objects
        * Wrapper objects were introduced in Java to wrap primitive variable types into objects. In the example below, the primitive `int` values are being converted to `Integer` objects and then sorted.

        * ```java
            List<Integer> numbers = new LinkedList<Integer>();

            numbers.add(201);
            numbers.add(100);
            numbers.add(101);

            Collections.sort(numbers);
            ```
    
    * User-Defined Classes

        * User-defined classes will need to implement the `Comparable Interface` in order to use the `Collections.sort()` method. The Comparable Interface provides a method, `compareTo`, which is used to compare two objects of the same type. In the example below we are using the Person object's name field to compare `Person` objects. This line of code, `name.compareTo(person.name)`, handles all of the hard work.

        * ```java
            import java.util.*;  

            class Person implements Comparable<Person> {  
                public String name;  
                public Person(String name) {  
                    this.name = name;  
                }  
                public int compareTo(Person person) {  
                    return name.compareTo(person.name);  
                }   
            }  
            public class PersonSort {
                public static void main(String[] args) {
                    ArrayList<Person> people = new ArrayList<Person>();
                    people.add(new Person("Same"));
                    people.add(new Person("Mike"));
                    people.add(new Person("Apple"));

                    // This implementation is a stable, adaptive, iterative mergesort ... This implementation dumps the specified list into an array, sorts the array, and iterates over the list resetting each element from the corresponding position in the array.
                    Collections.sort(people);
                    for (Person person : people) {
                    System.out.println(person.name);
                    }
                }
            }
            ```

### Advanced Collections

* Maps

    * Without Maps: Linear Lookup Time

    * When we have a list of objects and need to search for an item in that list, the time required to search will grow linearly with the number of objects. The more items added to the list, the longer it will take to iterate over all of the items. Processing a list of items like this is said to take linear time, because a graph of the relationship looks like a straight line.

    * With Maps: Constant Lookup Time
        * Maps provide a solution. Maps are data structures that use `key-value pairs`. That is, every value in the map is paired with a key. When we want to retrieve a value, we simply provide the corresponding key.

        * Importantly, this retrieval is performed in constant time, meaning that the amount of time required to retrieve an item from the map takes a certain, fixed (constant) amount of time, and this time doesn't change based on how many items we add.
    
    * **Map is an Interface**

        * The Java Map is an Interface that provides three different distinct views of the data:

            * A list of the keys
            * A list of the values
            * A set of key-value mappings
    
    * ```java
        Map<String, Person> mapOfPeople = new HashMap<String, Person>();

        Person mike = new Person("Mike", "mike@email.com");
        Person shaun = new Person("Shaun", "shaun@email.com");
        Person sally = new Person("Sally", "sally@email.com");
        Person cesar = new Person("Cesar", "cesar@email.com");

        mapOfPeople.put(mike.getEmail(), mike);
        mapOfPeople.put(shaun.getEmail(), shaun);
        mapOfPeople.put(sally.getEmail(), sally);
        mapOfPeople.put(cesar.getEmail(), cesar);

        mapOfPeople.get("mike@email.com");

        for (String email : mapOfPeople.keySet()) {
            System.out.println(email);
        }
        ```

* Sets

    * Sometimes, as with the example of a list of phone numbers, it's important for the values in a collection to be unique. Fortunately, we have `Sets`. A Set is a collection type that has no duplicate values.

    * Note that Set extends the `Collection` interface and therefore provides the same methods as other `Collection` data structures do.

    * Concrete Implementations
        * There are three concrete implementations for the Set interface:
            * HashSet
            * TreeSet
            * LinkedHashSet
    
    * **Each of these implementations vary, but all of them enforce the no-duplicate-values requirement of the Set interface.**

    * In the Collections framework, the Set is an interface and cannot be directly used to instantiate a class. In the example below we are creating a Set of strings. Notice, we are using the concrete class HashSet to instantiate our Set object.

    * ```java
        Set<String> mySet = new HashSet<String>();

        mySet.add("Hello");
        mySet.add("Hello");
        mySet.add("Hellos");

        String foundObject;

        for (String text : mySet) {
            if(text.equals("Hello"){
                foundObject  = text;
            }
        }
        ```

* Queues

    * Sometimes we need to process items in a `First-In, First-Out (FIFO)` order.

    * Queues are data structures that allow us to process items in a `First-In, First-Out (FIFO)` order. The first item placed in a queue is also the first one removed from the queue.

    * Some key points about Queues in Java:

        * `Queue` is an interface that implements the `Collection` interface
        * We put newly added elements at the end of the queue
        * We pop elements off the front of the queue
    
    * In the collections framework the Queue is an interface and cannot be directly used to instantiate a class. In the example below, we are creating a Queue of strings. Notice that we are using the concrete class `LinkedList` to instantiate our `Queue`.

    * ```java
        Queue<String> myQueue = new LinkedList<String>();
        // Queue queueB = new PriorityQueue();

        myQueue.add("Hi");
        myQueue.add("There");

        while (!myQueue.isEmpty()) {
            System.out.println(myQueue.poll());
        }

        ```

