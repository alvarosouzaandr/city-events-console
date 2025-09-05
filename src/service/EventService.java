package app.service;


import app.model.Event;
import app.repository.EventRepository;


import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


public class EventService {
private final Map<String, Event> events = new HashMap<>();
private final EventRepository repo;


public EventService(EventRepository repo){
this.repo = repo;
// Carrega do arquivo ao iniciar
repo.load().forEach(e -> events.put(e.getId(), e));
}


public void addEvent(Event e){
events.put(e.getId(), e);
persist();
}


public List<Event> listAllSorted(){
LocalDateTime now = LocalDateTime.now();
return events.values().stream()
.sorted(Comparator.comparing(Event::getDateTime))
.collect(Collectors.toList());
}


public List<Event> listOngoing(){
return events.values().stream()
.filter(Event::isOngoingNow)
.sorted(Comparator.comparing(Event::getDateTime))
.collect(Collectors.toList());
}


public List<Event> listPast(){
return events.values().stream()
.filter(Event::isPast)
.sorted(Comparator.comparing(Event::getDateTime).reversed())
.collect(Collectors.toList());
}