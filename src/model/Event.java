package app.model;
public Category getCategory(){ return category; }
public String getDescription(){ return description; }
public LocalDateTime getDateTime(){ return dateTime; }


public boolean isOngoingNow(){
LocalDateTime now = LocalDateTime.now();
// Considera evento "ocorrendo" no intervalo de 2h a partir do horário marcado
return !now.isBefore(dateTime) && now.isBefore(dateTime.plusHours(2));
}


public boolean isPast(){
return LocalDateTime.now().isAfter(dateTime.plusHours(2));
}


public String toDataLine(){
String safeDesc = description.replace("\n", " ").replace("|", "/");
String safeName = name.replace("|", "/");
String safeAddr = address.replace("|", "/");
return String.join("|",
id,
safeName,
safeAddr,
category.name(),
safeDesc,
FILE_FMT.format(dateTime)
);
}


public static Event fromDataLine(String line){
String[] p = line.split("\\|");
if (p.length < 6) throw new IllegalArgumentException("Linha inválida em events.data: " + line);
String id = p[0];
String name = p[1];
String addr = p[2];
Category cat = Category.valueOf(p[3]);
String desc = p[4];
LocalDateTime dt = LocalDateTime.parse(p[5], FILE_FMT);
return new Event(id, name, addr, cat, desc, dt);
}


@Override public String toString(){
return String.format("[%s] %s | %s | %s | %s | %s",
id.substring(0, 8),
name,
address,
category.getLabel(),
FILE_FMT.format(dateTime),
description);
}
}