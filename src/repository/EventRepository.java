package app.repository;


import app.model.Event;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class EventRepository {
private final Path dataPath;


public EventRepository(String fileName){
this.dataPath = Paths.get(fileName);
}


public List<Event> load(){
List<Event> list = new ArrayList<>();
if (!Files.exists(dataPath)) return list;
try (BufferedReader br = Files.newBufferedReader(dataPath, StandardCharsets.UTF_8)){
String line;
while ((line = br.readLine()) != null){
line = line.trim();
if (line.isEmpty()) continue;
list.add(Event.fromDataLine(line));
}
} catch (IOException e){
System.err.println("Falha ao ler events.data: " + e.getMessage());
}
return list;
}


public void saveAll(Collection<Event> events){
try {
if (!Files.exists(dataPath)){
Files.createFile(dataPath);
}
try (BufferedWriter bw = Files.newBufferedWriter(dataPath, StandardCharsets.UTF_8)){
for (Event e : events){
bw.write(e.toDataLine());
bw.newLine();
}
}
} catch (IOException e){
System.err.println("Falha ao salvar events.data: " + e.getMessage());
}
}
}