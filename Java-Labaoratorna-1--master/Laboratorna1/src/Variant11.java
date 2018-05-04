import java.util.Arrays;

public class Variant11 {
	protected class ClassForBeginTask{
		private double sum;
		private double difference;
		private double  multiplication;
		private double division1,division2;
		ClassForBeginTask(){
			 sum=0;
			 difference=0;
			 multiplication=0;
			 division1=0;
			 division2=0;
		}
		ClassForBeginTask(double a, double b ,double c ,double d ,double e){
			sum=a;
			difference=b;
			multiplication=c;
			division1=d;
			division2=e;
		}
		public void calculate(double a,double b) {
			this.sum=a+b;
			this.difference=a-b;
			this.multiplication=a*b;
			this.division1=Math.abs(a)/Math.abs(b);
			this.division2=Math.abs(b)/Math.abs(a);
		}
	
		@Override
		public boolean equals(Object obj) {
			if(obj==this) {
				return true;
			}
			if(obj==null||obj.getClass()!=this.getClass()) {
				return false;
			}
			
				ClassForBeginTask s =(ClassForBeginTask)obj;
				return (Math.abs(this.sum-s.sum)<0.1&&Math.abs(this.difference-s.difference)<0.1&&Math.abs(this.multiplication-s.multiplication)<0.1&&Math.abs(this.division1-s.division1)<0.1&&Math.abs(this.division2-s.division2)<0.1);
			
		}
		
		
	}
	
protected class ClassForIfTask{
		private int returneda,returnedb;
		ClassForIfTask(){
			returneda=0;
			returnedb=0;
		}
		ClassForIfTask(int a,int b){
			returneda=a;
			returnedb=b;
		}
		public void calculate(int a,int b) {
			 returneda=a;
				returnedb=b;
				 if(returneda!=returnedb){
			         if(returneda>returnedb){
			        	 returnedb=a;
			         }
			         else{
			        	 returneda=returnedb;
			         }     
			     }
			     else{
			    	 returneda=0;
			         returnedb=0;
			     }
			
		}
		
	 @Override
		public boolean equals(Object obj) {
		 if(obj==this) {
				return true;
			}
			if(obj==null||obj.getClass()!=this.getClass()) {
				return false;
			}
			ClassForIfTask  s=(ClassForIfTask)obj;
			return (this.returneda==s.returneda&&this.returnedb==s.returneda);
	 }		
	}
	protected class ClassForIntegerNumberTask{
		private int sum,mul;
		ClassForIntegerNumberTask(){
			sum=0;
			mul=1;
		}
		ClassForIntegerNumberTask(int a, int b){
			sum=a;
			mul=b;
		}
		public void calculate (int k) {
			int temp=k;
			sum+=temp%10;
			mul*=temp%10;
			temp/=10;
			sum+=temp%10;
			mul*=temp%10;
			temp/=10;
			sum+=temp;
			mul*=temp%10;
		}
		@Override 
		public boolean equals(Object obj) {
			if(obj==this) {
				return true;
			}
			if(obj==null||obj.getClass()!=this.getClass()) {
				return false;
			}
			
			ClassForIntegerNumberTask s =(ClassForIntegerNumberTask)obj;
				return (this.sum==s.sum&&this.mul==s.mul);
			
		}
	}
	
	public enum NWSE{
		North, west, south ,east
	}
	/*
	 * @param a is a first number 
	 * @param b is a second number 
	 * @return class ,which contains result of all calculations 
	 * 
	 * 
	 */
	public ClassForBeginTask begintask(double a ,double b) {
		ClassForBeginTask new_object=new ClassForBeginTask();
		if(b-0<0.01||a-0<0.01) {
			 throw new AssertionError("Wrong input");
		}
		new_object.calculate(a, b);
		return new_object;
	}
	/*
	 * @param k
	 * @return class which contains sum and product
	 * 
	 */
	public ClassForIntegerNumberTask integerNumbersTask(int k) {
		if(k<100||k>999) {
			throw new AssertionError("Wron input");
		}
		ClassForIntegerNumberTask main_object= new ClassForIntegerNumberTask();
		main_object.calculate(k);
		return main_object;
	}
	/*
	 * @param a 
	 * @param b
	 * @return boolean
	 *  
	 */
	public boolean booleantask(int a,int b) {
		 
		boolean flag1=(a%2!=0);
		boolean flag2=(b%2!=0);
		return !(flag1^flag2);
	}
	
	/*
	 * @param a
	 * @param b
	 * 
	 */
	public ClassForIfTask iftask(int a,int b){
		ClassForIfTask object= new ClassForIfTask();
		object.calculate(a, b);
		return object;
    }
	/*
	 * @param C is a start direction
	 * @param N1 is the first action
	 * @param N@ is the second action 
	 * 
	 */
	public char casetask(char C,int N1,int N2) {
		int N=0;
		char res='s';
		if(C!='S'&&C!='Y'&&C!='V'&&C!='Z'||
			N1!=1&&N1!=-1&&N1!=2||
			N2!=1&&N2!=-1&&N1!=2
				){
			throw new AssertionError("Wron input");
		}
		switch(C) {
		case 'S': N=0; break;
        case 'Y': N=2; break;
        case 'V': N=-1; break;    
        case 'Z': N=1; break;
       // default : N=0;break;
		}
		 switch(N+N1+N2)
		    {
		      
		    case 0: return 'S'; 
	        case 1: return 'Z'; 
	        case -1: return'V';
	        case 2: return 'Y';
	        case -2:return 'Y'; 
	        case 3: return'V'; 
	        case -3: return 'Z'; 
	        case 4:  return 'S';  
	        case 5: return 'Z'; 
	        case 6:  return 'S';  
		    }
		 return res;
	}
	/*
	 * @param n
	 * @return res
	 */
    public int fortask(int n){
    	if(n<1) {
    		throw new AssertionError("Wron input");
    	}
    	
        int res=0;
        for(int i=0;i<n;i++){
            res+=n+i;    
        }
        return res;
    }
    /*
     * @param N 
     * @return res
     * 
     */
    public int whiletask(int N){
    	if(N<1) {
    		throw new AssertionError("Wron input");
    	}
        int count=0;
        int res=0;
        while(res<N){
            res=0;
            for(int i=1;i<=count;i++){
                res+=i;    
            }        
        count++;
        }
        
        return count;
    }
    /*
     * @param a is an array 
     * @param n 
     * @return minimal
     * 
     */
    public int arraytask (int[] a,int n){
        int min=a[0];
        for(int i=1;i<n;i+=2){
            if(a[i]<min){
                min=a[i];
            }    
        }
        return min;
    }
    /*
     * @param a is an array 
     * @param n
     * @param m
     * @return returns an array with swapped left upper left and lower right sides of an array
     * 
     */
    public int[][] matrixtask(int[][] a,int n, int m){
    	if(n%2!=0||m%2!=0) {
    		throw new AssertionError("Wron input");
    	}
        int tempn,tempm;
          int[][] tempmas=new int[n][m];
        
        tempn=n/2;
        tempm=m/2;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                tempmas[i][j]=0;
            }
        }
        for(int i=0;i<tempmas.length;i++) {
    	   for(int j=0;j<tempmas[i].length;j++) {
    		   
    		   tempmas[i][j]=a[i][j];
    	   }
       }
        for(int i=0;i<tempm;i++) {
        	for(int j=0;j<tempn;j++) {
        		tempmas[i+tempm][j+tempn]=a[i][j];
        	}
        }
        
        for(int i=0;i<tempm;i++) {
        	for(int j=0;j<tempn;j++) {
        		tempmas[i][j]=a[i+tempm][j+tempn];
        		
        	}
        }
        a=tempmas;
        return a;
    }

    

	
	public static void  main(String[] args) {
		
		
		
	}
}
