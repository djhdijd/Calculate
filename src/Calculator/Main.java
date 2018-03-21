package Calculator;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

public class Main {
	static int number;
	public static void main(String[] args) throws IOException
	{
		@SuppressWarnings("resource")
		Scanner numb = new Scanner(System.in);  
        System.out.print("请输入运算题的数目n:");  
        int number = numb.nextInt(); 
		Calculator CA=new Calculator();	
        CA.calculate_AE(number);
       
		System.out.print("生成文件保存至文档result.txt\n");
		File f=new File("result.txt");
        BufferedWriter bw=new BufferedWriter(new FileWriter(f));
        bw.write("201571030103");//ID number
        bw.newLine();
        for(int i=0;i<CA.Arithmetic.size();i++){
            bw.write(CA.Arithmetic.get(i));
            bw.newLine();
        }
        bw.close();
	}
}


