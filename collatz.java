//John Crews
//last updated 24 August 2016

//ABOUT:
//The Collatz conjecture states that, by starting at any number, halving it if even and multiplying by three and adding one if odd,
//eventually you reach the number one. However, I personally have never seen this when applied to the general cases of numbers. What do I
//mean by this? I mean that every number can be written as, for instance, 4n, 4n+1, 4n+2, or 4n+3. What happens if we apply those steps to
//these particular binomials? We get:
//4n => 2n
//4n+1 => 12n+4 => 6n+2 => 3n+1
//4n+2 => 2n+1
//4n+3 => 12n+10 => 6n+5 => 18n+16 => 9n+8
//What can each of these cases tell us? That for each positive integer that can be described as 4n, when the Collatz conjecture's rules are
//applied, it will always reduce to 2n, or half of itself. This means that any integer described in this way cannot be the lowest non-Collatz
//number (number that, when the rules are applied, does not reach 1 eventually). It cannot be the lowest because it leads to a lower number,
//which by our very established definitions must be a Collatz-applicable number (does reach 1). Thus, we can place all integers 4n into the
//"excluded case", cases that will never contain the lowest non-Collatz number. This excludes 4, 8, 12, 16, etc. 4n+1 and 4n+2, by the same 
//token, also eventually become values lower than their initial state, and are thus also excluded.
//
//However, 4n+3 enters what I've deemed the "ambiguous case". By amounting to 9n+8, computations cannot continue until n is determined to be
//even or odd. Not knowing that, calculations cannot continue. A binomial in the ambiguous case may bear the lowest Collatz number, but not
//necessarily (hence the name). However, since the 4n+3 case is the only case in the "4n" set that isn't excluded, I can conclude that,
//should a lowest non-Collatz number be found, it must necessarily be a member of the 4n+3 case.
//
//What is all this code, then?
//This is a program that tests binomials, both to potentially see individual cases and to find emergent patterns.
//For example, for the "2n" cases, 2n and 2n+1, 1 in 2 cases are ambiguous, for an occurance rate of 50%. The "4n" cases were ambiguous
//25% of the time. By the time you reach "1048576n", or 2^20, the ambiguous rate drops to 2.6%. While not exciting on its own, it means that
//at least 97.4% of all positive numbers ever cannot be the lowest non-Collatz number. Using larger powers of two only shrinks the
//rate even lower (and eats up more computing power). While not a hard proof of the conjecture, I think this serves as a soft reassurace
//of its correctness, while offering a fresh perspective on the problem to whomever may care.
//
//Happy hunting!
//JRC


package collatz;

public class Collatz {

    
    public static void main(String[] args) {
       for(long i = 4; i <= 1048576; i = i*2){
        long numAmbgCase = 0;
           for(long j = 3; j < i; j=j+4){
               if(testcase(i,j)){
                   numAmbgCase++;
               }
           }
           System.out.println("\n FOR #"+i+", "+numAmbgCase+" ambiguous cases. \n");
       }
    }
    
    public static boolean testcase(long mult, long add){
        //tests binomials of form mult*n+add  
        //returns false if computation returns something in excluded case
        //returns true if computation results in unresolvable, ambiguous case.
        long a = mult;
        long b = add;
        while(((a>mult) ||(a==mult && b>=add))&&(a%2)==0){
            //System.out.println(a+"n + "+b);
            if(b%2 ==0){
                a = a/2;
                b = b/2;
            }
            else{
                a = 3*a;
                b = 3*b + 1;
            }
        }
        if(a<mult || (a==mult && b<add)){
            System.out.println(mult+"n + "+add+" is an excluded case");
            return false;
        }
        else{
            System.out.println(mult+"n + "+add+" is an ambiguous case");
            return true;
        }
    }
    
}
