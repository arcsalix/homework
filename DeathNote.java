import java.util.ArrayList;
import java.util.List;

      // мне нравится это аниме, решила сделать домашнее задание, используя его. "творческий" подход :)
class Person {
    public String name;
    public String reason;
    public Person(String name, String reason) {
        this.name = name;
        this.reason = reason;
    }

    public Person(Person other) {
        this.name = other.name;
        this.reason = other.reason;
    }
    @Override
    public String toString() {
        return name + " (" + reason + ")";
    }
}

public final class DeathNote {
    private final String owner;
    private final List<String> rules;
    private final List<Person> victims;
    public DeathNote(String owner, List<String> rules, List<Person> victims) {
        this.owner = owner;
        this.rules = new ArrayList<>(rules);
        this.victims = new ArrayList<>();
        for (Person p : victims) {
            this.victims.add(new Person(p));
        }
    }
    public String getOwner() {
        return owner;
    }
    public List<String> getRules() {
        return new ArrayList<>(rules);
    }
    public List<Person> getVictims() {
        List<Person> copy = new ArrayList<>();
        for (Person p : this.victims) {
            copy.add(new Person(p));
        }
        return copy;  
     }
    public static void main(String[] args) {
        List<String> rules = new ArrayList<>();
        rules.add("человек, чье имя будет записано в эту тетрадь, умрет.");
        rules.add("тетрадь не будет действовать, если пишущий имя не видел лицо этого человека.");
        rules.add("через 40 секунд, если не был написан способ смерти, человек умрет от сердечного приступа.");
        
        List<Person> victims = new ArrayList<>();
        victims.add(new Person("преступник А", "убийство"));
        victims.add(new Person("линд л. нейлор", "проверка"));
        victims.add(new Person("L", "поиск киры"));
            victims.add(new Person("нир", "поиск киры"));
        victims.add(new Person("mello", "вмешательство"));
        victims.add(new Person("аманэ миса", "соучастие"));
        DeathNote dn = new DeathNote("ягами лайт", rules, victims);

        System.out.println("+ тетрадь смерти +");
        System.out.println("владелец: " + dn.getOwner());
        System.out.println("правила: " + dn.getRules());
        System.out.println("жертвы: " + dn.getVictims());

        // проверка: попытка изменить жертву через список, полученный снаружи
        List<Person> retrievedVictims = dn.getVictims();
        retrievedVictims.get(0).name = "невиновный";
        
        System.out.println("\nжертвы после попытки взлома через геттер: " + dn.getVictims());

        System.out.println("\n--- Добавляем новую жертву ---");
        List<Person> updatedVictims = new ArrayList<>(dn.getVictims());
        updatedVictims.add(new Person("рэй пембер", "расследование"));
        
        DeathNote dnUpdated = new DeathNote(dn.getOwner(), dn.getRules(), updatedVictims);
        
        System.out.println("старая тетрадь (не изменилась): " + dn.getVictims());
        System.out.println("новая тетрадь (с новой жертвой): " + dnUpdated.getVictims());
    }
}