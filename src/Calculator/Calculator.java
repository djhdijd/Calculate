package Calculator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;


public class Calculator {
	static Operator ope=new Operator();
	ArrayList<String> Arithmetic=new ArrayList<String>(); 
    private static LinkedList<String> operators=new LinkedList<>();	
    private static LinkedList<String> output=new LinkedList<>();    
    private static StringBuilder sb=new StringBuilder();    
	static int M=20;
	static int [][]N=new int[M][M]; 
	static char [][]C=new char[M][M];
	static int []CN=new int[M];      
	static char [] Str={'+','-','*','/'};
	Scanner scanner=new Scanner(System.in);
	
//随机运算式的生成,调用方法计算结果
	public void calculate_AE(int number) {
		int F=0;
		while(F==0)
		{
			for(int i=0;i<number;i++)
			{
				CN[i]=(int)(Math.random()*2+4);
				for(int j=0;j<CN[i];j++)
				{
					N[i][j]=(int) (Math.random()*100+1);
				}
				for(int k=0;k<CN[i]-1;k++)
				{
					C[i][k]=Str[(int)(Math.random()*3)];	
				}
				for(int k=0;k<CN[i]-1;k++)
				{
				   while(C[i][0]==C[i][1])
						C[i][1]=Str[(int)(Math.random()*3)];
					if(C[i][k]=='-' && (N[i][k]<N[i][k+1]))
					{
						int temp=0;
						temp=N[i][k];
						N[i][k+1]=temp;
						N[i][k]=temp;
					}
					if(C[i][k]=='/' && (N[i][k]%N[i][k+1]!=0))
					{
						int temp=N[i][k];
						N[i][k] = N[i][k] <N[i][k+1]? N[i][k]: N[i][k+1];
						N[i][k+1] = temp > N[i][k+1]? temp: N[i][k+1];
						for(int num = N[i][k]; num >= 1; num--)
						{
							if(N[i][k] % num == 0 && N[i][k+1] % num == 0)
							{
								N[i][k+1]=num;
								break;
							}
						}
					}
				}
				String AE=new String();
				LinkedList<String> list=new LinkedList<>();
				list.add(String.valueOf('('));
				list.add(String.valueOf(N[i][0]));
				list.add(String.valueOf(C[i][0]));
				list.add(String.valueOf(N[i][1]));
				list.add(String.valueOf(')'));
				list.add(String.valueOf(C[i][1]));
				AE+='('+String.valueOf(N[i][0])+String.valueOf(C[i][0])+String.valueOf(N[i][1])+')'+String.valueOf(C[i][1]);
				for(int j=2;j<CN[i]-2;j++)
				{
					list.add(String.valueOf(N[i][j]));
					list.add(String.valueOf(C[i][j]));
					AE+=String.valueOf(N[i][j])+String.valueOf(C[i][j]);
				}
				list.add(String.valueOf('('));
				list.add(String.valueOf(N[i][CN[i]-2]));
				list.add(String.valueOf(C[i][CN[i]-2]));
				list.add(String.valueOf(N[i][CN[i]-1]));
				list.add(String.valueOf(')'));
				AE+='('+String.valueOf(N[i][CN[i]-2])+String.valueOf(C[i][CN[i]-2])+String.valueOf(N[i][CN[i]-1])+')'+'=';
				String sum=transferToPostfix(list);
				char fir =sum.charAt(0);
				if(fir=='-')
					F=0;
				else
					F=1;
				System.out.print(AE);
				System.out.print("\n");
				AE+=sum;
				Arithmetic.add(AE);
			}
		}
	}

	//中缀表达式转为后缀表达式
    private static String transferToPostfix(LinkedList<String> list){
        Iterator<String> it=list.iterator();
        while (it.hasNext()) 
        {
            String s = it.next();
            if (ope.isOperator(s)) 
            {
                if (operators.isEmpty()) 
                {
                    operators.push(s);
                }
                else 
                {
                    if (ope.priority(operators.peek())<=ope.priority(s)&&!s.equals(")")) 
                    {
                        operators.push(s);
                    }
                    else if(!s.equals(")")&&ope.priority(operators.peek())>ope.priority(s))
                    {
                        while (operators.size()!=0&&ope.priority(operators.peek())>=ope.priority(s)&&!operators.peek().equals("(")) 
                        {
                            if (!operators.peek().equals("(")) 
                            {
                                String operator=operators.pop();
                                sb.append(operator).append(" ");
                                output.push(operator);
                            }
                        }
                        operators.push(s);
                    }
                   
                    else if (s.equals(")")) 
                    {
                        while (!operators.peek().equals("(")) 
                        {
                            String operator=operators.pop();
                            sb.append(operator).append(" ");
                            output.push(operator);
                        }
                        operators.pop();
                    }
                }
            }
            else 
            {
                sb.append(s).append(" ");
                output.push(s);
            }
        }
        if (!operators.isEmpty()) 
        {
            Iterator<String> iterator=operators.iterator();
            while (iterator.hasNext()) 
            {
                String operator=iterator.next();
                sb.append(operator).append(" ");
                output.push(operator);
                iterator.remove();
            }
        }
        String sum= calculate();
       return sum;
    }
    
  //根据后缀表达式计算结果
    private static String calculate(){
        LinkedList<String> mList=new LinkedList<>();
        String[] postStr=sb.toString().split(" ");
        for (String s:postStr) 
        {
            if (ope.isOperator(s))
            {
                if (!mList.isEmpty())
                {
                    int num1=Integer.valueOf(mList.pop());
                    int num2=Integer.valueOf(mList.pop());
                    int newNum=ope.cal(num2,num1,s);
                    mList.push(String.valueOf(newNum));
                }
            }
            else 
            { 
                mList.push(s);
            }
        }
        if (!mList.isEmpty())
        {
            return mList.pop();
        }
		return null;
    }

  
    
}


