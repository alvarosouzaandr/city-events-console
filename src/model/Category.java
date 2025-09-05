package app.model;


public enum Category {
PARTY("Festa"),
SPORTS("Evento Esportivo"),
SHOW("Show"),
EDUCATION("Educação"),
BUSINESS("Negócios"),
OTHER("Outros");


private final String label;
Category(String label) { this.label = label; }
public String getLabel(){ return label; }


public static Category fromInt(int i){
switch (i){
case 1: return PARTY;
case 2: return SPORTS;
case 3: return SHOW;
case 4: return EDUCATION;
case 5: return BUSINESS;
default: return OTHER;
}
}


public static void printMenu(){
System.out.println("Categorias:");
System.out.println(" 1) Festa");
System.out.println(" 2) Evento Esportivo");
System.out.println(" 3) Show");
System.out.println(" 4) Educação");
System.out.println(" 5) Negócios");
System.out.println(" 6) Outros");
}
}