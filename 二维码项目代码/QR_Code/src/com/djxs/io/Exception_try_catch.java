package com.djxs.io;
/**
 * java异常捕获机制
 * try{}块,用来将可能出现异常的代码片段括起来
 * catch块用来捕获并解决try块中代码抛出的异常
 * 
 * try块是不能单独定义的,后面通常会定义catch
 * 块.也可以直接跟finally块 .
 * @author adminitartor
 *
 */
public class Exception_try_catch {
	public static void main(String[] args) {
		System.out.println("程序开始了");
		try{
			String str = "a";
			System.out.println(str.length());
			System.out.println(str.charAt(0));
			System.out.println(Integer.parseInt(str));
			//try中出现错误的代码下面的内容不会执行
			System.out.println("!!!!");
		}catch(NullPointerException e){
			System.out.println("出现了空指针!");
		}catch(StringIndexOutOfBoundsException e){
			System.out.println("字符串下标越界了!");
		
		/*
		 * catch可以定义多个,针对不同异常有
		 * 不同处理手段时,应当分别捕获并定义
		 * 解决手段.
		 * 但是应当养成一个好习惯,在最下面捕获
		 * Exception,以防代码中出现一个未知
		 * 异常而导致程序中断.
		 * 当捕获的异常之间存在继承关系时,应当
		 * 先捕获子类型异常后捕获父类型异常
		 */
		}catch(Exception e){
			System.out.println("反正就是出了个错!");
		}
		
		System.out.println("程序结束了");
	}
}






