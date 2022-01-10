import java.util.Random; //importing random for all the random jobs

public class Main{
        public static void main(String[] args){
        int i;
        int N = Integer.parseInt(args[0]); //the first command line argument is the number of artifacts that will be created
        String Movement = args[1]; //the second argument is the desired movement of the artifact
        String Condition = args[2]; //the third is the desired condition threshold of the artifact

        Artifact[] artifacts;
        artifacts = new Artifact[N];
        Random rand = new Random();
        for(i = 0; i < N; i++){
            if(rand.nextInt(2) == 1){ //depending on the coin flip we create either a generic artifact, a sculpure or a painting
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
        for(i = 0; i < N; i++){ //for every artifact in the array of artifacts
            artifacts[i].getIndex();
            artifacts[i].getInfo();
            if(artifacts[i].evaluate(w_movement, w_condition)){ //evaluate every artifact
                System.out.println("The artifact passed the evaluation");
            }
            else {
                System.out.println("The artifact didn't pass the evaluation");
            }
        }
    }
    public static String random_name(){ //this function returns a random name from an array of names
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
        super(index, year, creator); //calling the constructor of artifact
        this.Movement = getMovement(movement_index);
        this.Condition = getCondition(condition_index);
        System.out.println("Creating instance of class Masterpiece...");
    }

    public void getInfo(){
        System.out.println("The creator of this masterpiece is " + Creator + " and it dates back to the year " + Year);
        System.out.println("This masterpiece belongs in the movement of: " +Movement+ " and it is in " +Condition+ " condition");
    }

    private static final String[] Movements = {"impressionism", "expressionism", "naturalism"}; //array of all the possible values of movements 

    public static String getMovement(int index){ //return function of movements depending on index
        return Movements[index];
    }
    private static final String[] Conditions = {"excellent", "good", "bad"}; //array of the possible values of condition

    public static String getCondition(int index){ //return function of condition depending on index
        return Conditions[index];
    }

    public boolean evaluate(String movement, String condition){
        if(Movement.equals(movement) == false){     //if the artifact is of the wrong movement, reject it right off the bat
            System.out.println("Wrong movement");
            return false;
        }
        else if(Condition.equals("excellent")){ //if it is of the right movement and in excellent condition accept it 
            return true;
        }
        else if(condition.equals("excellent") == false){ //if it is not in excellent condition and we need it to be in excellent condition, reject it 
            System.out.println("Not excellent");
            return false;
        }
        else if(Condition.equals("good")){  //if we don't need it to be in excellent condition and the artifact itself is in good condition then accept it
            return true;
        }
        else if(condition.equals("bad")){ //if it is not in good condition but we don't need it to be good anyway, accept it
            return true;
        }
        else{
            System.out.println("Not good"); //elsewise reject it 
            return false;
        }
    }
}
class Painting extends Masterpiece{
    int Length, Width;
    String Technique;

    public Painting(int length, int width, int technique_index, int movement_index, int condition_index, int index, int year, String creator){
        super(movement_index, condition_index, index, year, creator); //calling the constructor of masterpiece
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
        if(Movement.equals(movement) == false){     //if the artifact is of the wrong movement, reject it right off the bat
            System.out.println("Wrong movement");
            return false;
        }
        else if(Condition.equals("excellent")){ //if it is of the right movement and in excellent condition accept it 
            return true;
        }
        else if(condition.equals("excellent") == false){ //if it is not in excellent condition and we need it to be in excellent condition, reject it 
            System.out.println("Not excellent");
            return false;
        }
        else if(Condition.equals("good")){  //if we don't need it to be in excellent condition and the artifact itself is in good condition then accept it
            return true;
        }
        else if(condition.equals("bad")){ //if it is not in good condition but we don't need it to be good anyway, accept it
            return true;
        }
        else{
            System.out.println("Not good"); //elsewise reject it 
            return false;
        }
    }
    private static final String[] Techniques = {"oil", "aquarelle", "tempera"}; //array of tenchniques

    public static String getTechnique(int index){  //return function of techniques depending on the index
        return Techniques[index];
    }
}
class Sculpure extends Masterpiece{
    int Volume;
    String Material;

    public Sculpure(int volume, int material_index, int movement_index, int condition_index, int index, int year, String creator){
        super(movement_index, condition_index, index, year, creator); //calling the constructor of masterpiece
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
        if(Movement.equals(movement) == false){     //if the artifact is of the wrong movement, reject it right off the bat
            System.out.println("Wrong movement");
            return false;
        }
        else if(Condition.equals("excellent")){ //if it is of the right movement and in excellent condition accept it 
            return true;
        }
        else if(condition.equals("excellent") == false){ //if it is not in excellent condition and we need it to be in excellent condition, reject it 
            System.out.println("Not excellent");
            return false;
        }
        else if(Condition.equals("good")){  //if we don't need it to be in excellent condition and the artifact itself is in good condition then accept it
            return true;
        }
        else if(condition.equals("bad")){ //if it is not in good condition but we don't need it to be good anyway, accept it
            return true;
        }
        else{
            System.out.println("Not good"); //elsewise reject it 
            return false;
        }
    }

    private static final String[] Materials = {"iron", "stone", "wood"}; //array of materials

    public static String getMaterial(int index){ //return function of materials depending on the index
        return Materials[index];
    }
}



