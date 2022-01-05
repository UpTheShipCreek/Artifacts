import java.util.Random;

public class Main{
        public static void main(String[] args){
        int i;
        int N = Integer.parseInt(args[0]);
        String Movement = args[1];
        String Condition = args[2];

        Artifact[] artifacts;
        artifacts = new Artifact[N];
        Random rand = new Random();
        for(i = 0; i < N; i++){
            if(rand.nextInt(2) == 1){
                artifacts[i] = new Artifact(i+1, rand.nextInt(2000)+1, random_name());
            }
            else{
                if(rand.nextInt(2) == 1){
                    artifacts[i] = new Painting(rand.nextInt(20)+1, rand.nextInt(100), rand.nextInt(3), rand.nextInt(3), rand.nextInt(3), i+1, rand.nextInt(2000)+1, random_name());
                }
                else{
                    artifacts[i] = new Sculpure(rand.nextInt(20)+1, rand.nextInt(3), rand.nextInt(3), rand.nextInt(3), i+1, rand.nextInt(2000)+1, random_name());
                }
            }
        }
        auction(artifacts, Movement, Condition, N);
    }

    public static void auction(Artifact[] artifacts, String w_movement, String w_condition, int N){
        int i;
        for(i = 0; i < N; i++){
            artifacts[i].getIndex();
            artifacts[i].getInfo();
            if(artifacts[i].evaluate(w_movement, w_condition)){
                System.out.println("The artifact passed the evaluation");
            }
            else {
                System.out.println("The artifact didn't pass the evaluation");
            }
        }
    }
    public static String random_name(){
        int i;
        Random rand = new Random();
        String[] names = {"Amelia", "Aiden", "Emma", "Ezra", "Luna", "Lucas", "Phil", "Patty"};
        i = rand.nextInt(7);
        return names[i];
    }
}

class Artifact{
    int Index, Year;
    String Creator;

    public Artifact(){}
    public Artifact(int index, int year, String creator){
        this.Index = index;
        this.Year = year;
        this.Creator = creator;
        System.out.println("Creating instance of class Artifact...");
    }
    public void getInfo(){
        System.out.println("The creator of this artifact is " + Creator + " and it dates back to the year " + Year);
    }
    public void getIndex(){
        System.out.println(Index);
    }
    public boolean evaluate(String w_movement, String w_condition){
        return false;
    }
}

class Masterpiece extends Artifact{
    String Movement, Condition;

    public Masterpiece(int movement_index, int condition_index, int index, int year, String creator){
        super(index, year, creator);
        this.Movement = getMovement(movement_index);
        this.Condition = getCondition(condition_index);
        System.out.println("Creating instance of class Masterpiece...");
    }

    public void getInfo(){
        System.out.println("The creator of this masterpiece is " + Creator + " and it dates back to the year " + Year);
        System.out.println("This masterpiece belongs in the movement of: " +Movement+ " and it is in " +Condition+ " condition");
    }

    private static final String[] Movements = {"impressionism", "expressionism", "naturalism"};

    public static String getMovement(int index){
        return Movements[index];
    }
    private static final String[] Conditions = {"excellent", "good", "bad"};

    public static String getCondition(int index){
        return Conditions[index];
    }

    public boolean evaluate(String movement, String condition){
        if(Movement != movement){
            return false;
        }
        else if(Condition == "excellent"){
            return true;
        }
        else if(condition == "excellent"){
            return false;
        }
        else if(Condition == "good"){
            return true;
        }
        else if(condition == "bad"){
            return true;
        }
        else return false;
    }
}
class Painting extends Masterpiece{
    int Length, Width;
    String Technique;

    public Painting(int length, int width, int technique_index, int movement_index, int condition_index, int index, int year, String creator){
        super(movement_index, condition_index, index, year, creator);
        this.Length = length;
        this.Width = width;
        Technique = getTechnique(technique_index);
        System.out.println("Creating instance of class Painting...");
    }
    public void getInfo(){
        System.out.println("The creator of this painting is " + Creator + " and it dates back to the year " + Year);
        System.out.printf("This specific painting belongs in the movement of " +Movement+ ". It was made using " + Technique + ", it is in " +Condition+ " condition ");
        int area = Length * Width;
        System.out.println("and it has a surface area of " + area);
    }

    public boolean evaluate(String movement, String condition){
        if(Movement.equals(movement)){
            System.out.println("Wrong movement");
            return false;
        }
        else if(Condition.equals("excellent")){
            return true;
        }
        else if(condition.equals("excellent")){
            System.out.println("Not excellent");
            return false;
        }
        else if(Condition.equals("good")){
            return true;
        }
        else if(condition.equals("bad")){
            return true;
        }
        else{
            System.out.println("Not good");
            return false;
        }
    }

    private static final String[] Techniques = {"oil", "aquarelle", "tempera"};

    public static String getTechnique(int index){
        return Techniques[index];
    }
}
class Sculpure extends Masterpiece{
    int Volume;
    String Material;

    public Sculpure(int volume, int material_index, int movement_index, int condition_index, int index, int year, String creator){
        super(movement_index, condition_index, index, year, creator);
        this.Volume = volume;
        this.Material = getMaterial(material_index);
        System.out.println("Creating instance of class Sculpure...");
    }
    public void getInfo(){
        System.out.println("The creator of this sculpure is " + Creator + " and it dates back to the year " + Year);
        System.out.printf("This specific scuplure belongs in the movement of " +Movement+ ". It is in " +Condition+ " condition");
        System.out.println(", is made our of " +Material+ " and has a volume of " + Volume);
    }

    public boolean evaluate(String movement, String condition){
        if(Movement.equals(movement)){
            System.out.println("Wrong movement");
            return false;
        }
        else if(Condition.equals("excellent")){
            return true;
        }
        else if(condition.equals("excellent")){
            System.out.println("Not excellent");
            return false;
        }
        else if(Condition.equals("good")){
            return true;
        }
        else if(condition.equals("bad")){
            return true;
        }
        else{
            System.out.println("Not good");
            return false;
        }
    }

    private static final String[] Materials = {"iron", "stone", "wood"};

    public static String getMaterial(int index){
        return Materials[index];
    }
}



