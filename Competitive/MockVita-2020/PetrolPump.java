import java.util.*;

public class PetrolPump {
    

    private static Scanner sc=new Scanner(System.in);
    private static int min=Integer.MAX_VALUE;
    private static Deque<Integer> result;
    public static void main(String[] args) {
        ArrayList<Integer> x=new ArrayList<>();
        String input=sc.nextLine();
        String[] k=input.split(" ");
        int sum=0;
        for(int i=0;i<k.length;i++) {
            x.add(Integer.parseInt(k[i]));
            sum+=Integer.parseInt(k[i]);
        }
        calc(sum/2,x,0,new LinkedList<Integer>(),0,x.size());
        System.out.println(min);
    }

    private static void calc(int sum, ArrayList<Integer> x, int currSum, Deque<Integer> currList, int i, int length) {
        if(x.isEmpty()||i==length) {
            if(currSum<min&&currSum>sum) {
                min=currSum;
                result=currList;
                return;
            }
            return;
        }
        int k=x.get(i);
        currSum+=k;
        currList.push(k);
        calc(sum,x,currSum,currList,i+1,length);
        currSum-=k;
        currList.remove();
        calc(sum,x,currSum,currList,i+1,length);

    }
}