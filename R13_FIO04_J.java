public int processFile(String fileName)
                       throws IOException, FileNotFoundException {
  FileInputStream stream = new FileInputStream(fileName);
  BufferedReader bufRead =
      new BufferedReader(new InputStreamReader(stream));
  String line;
  while ((line = bufRead.readLine()) != null) {
    sendLine(line);
  }
  return 1;
}
