package Calculator;


public class Operator {
	//�ж��Ƿ������
    public boolean isOperator(String oper){
        if (oper.equals("+")||oper.equals("-")||oper.equals("/")||oper.equals("*")||oper.equals("(")||oper.equals(")")) 
        {
            return true;
        }
        return false;
    }
 //��������������ȼ�
    public int priority(String s){
        
        if(s=="+"||s=="-")
        	return 1;
        if(s=="*"||s=="/")
        	return 1;
        if(s=="("||s==")")
        	return 1;
        else
        	return 0;
        }
//����
    public int cal(int num1,int num2,String operator){
        
        
        	if(operator=="+")
        		return num1+num2;
        	if(operator=="-")
        		return num1-num2;
        	if(operator=="*")
        		return num1*num2;
        	if(operator=="/")
        		return num1/num2;
        	else
        		return 0;
  
    }
}

