package Calculator;


public class Operator {
	//判断是否操作符
    public boolean isOperator(String oper){
        if (oper.equals("+")||oper.equals("-")||oper.equals("/")||oper.equals("*")||oper.equals("(")||oper.equals(")")) 
        {
            return true;
        }
        return false;
    }
 //计算操作符的优先级
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
//计算
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

