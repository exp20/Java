public class tttest {
    public static void main(String ... args)
    {
        System.out.println(-2>>1);
        for(int i=1; i <=100; i++){
            if(i%3==0 && i%5==0){
                System.out.println(i+" (3 и 5)");
            }
            else{
                if(i%3==0){
                    System.out.println(i+" 3");
                }
                if(i%5==0){
                    System.out.println(i+" 5");
                }
            }
        }
    }

}
