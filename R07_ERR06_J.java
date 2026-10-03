public static synchronized void undeclaredThrow(Throwable throwable) {
  // These exceptions should not be passed
  if (throwable instanceof IllegalAccessException ||
      throwable instanceof InstantiationException) {
    // Unchecked, no declaration required
    throw new IllegalArgumentException(); 
  }

  NewInstance.throwable = throwable;
  try {
    Constructor constructor =
        NewInstance.class.getConstructor(new Class<?>[0]);
    constructor.newInstance();
  } catch (InstantiationException e) { /* Unreachable */
  } catch (IllegalAccessException e) { /* Unreachable */
  } catch (InvocationTargetException e) {
    System.out.println("Exception thrown: "
        + e.getCause().toString());
  } finally { // Avoid memory leak
    NewInstance.throwable = null;
  }
}
