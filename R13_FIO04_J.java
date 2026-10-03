try {
  final FileInputStream stream = new FileInputStream(fileName);
  try {
    final BufferedReader bufRead =
        new BufferedReader(new InputStreamReader(stream));

    String line;
    while ((line = bufRead.readLine()) != null) {
      sendLine(line);
    }
  } finally {
    if (stream != null) {
      try {
        stream.close();
      } catch (IOException e) {
        // Forward to handler
      }
    }
  }
} catch (IOException e) {
  // Forward to handler
}
