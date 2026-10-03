public class NewInstance {
  private static Throwable throwable;

  private NewInstance() throws Throwable {
    throw throwable;
  }

  public static synchronized void undeclaredThrow(Throwable throwable) {
    // These exceptions should not be passed
    if (throwable instanceof IllegalAccessException ||
        throwable instanceof InstantiationException) {
      // Unchecked, no declaration required
      throw new IllegalArgumentException(); 
    }

    NewInstance.throwable = throwable;
    try {
      // Next line throws the Throwable argument passed in above,
      // even though the throws clause of class.newInstance fails
      // to declare that this may happen
      NewInstance.class.newInstance();
    } catch (InstantiationException e) { /* Unreachable */
    } catch (IllegalAccessException e) { /* Unreachable */
    } finally { // Avoid memory leak
      NewInstance.throwable = null;
    }
  }
}

public class UndeclaredException {
  public static void main(String[] args) {   
    // No declared checked exceptions
    NewInstance.undeclaredThrow(
        new Exception("Any checked exception"));
  }
}
