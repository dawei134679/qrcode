package com.djxs.io;

/**
 * finally块 finally块只能出现在异常处理机制的最后 即:try后面或者最后一个catch之后.
 * finally块可以保证无论try块中的代码是否 抛出异常该块里的代码都一定会执行. 所以通常会将无论程序是否出错都要运行的
 * 代码放在finally中确保执行,比如流操作 中的关闭流以及其他释放资源等操作.
 * 
 * @author adminitartor
 *
 */
public class Exception_finally {
	public static void main(String[] args) {
		System.out.println("程序开始了");

		try {
			String str = "";
			int n = 1 / 0;
			System.out.println(str.length());
		} catch (Exception e) {
			System.out.println("程序出错了");
		} finally {
			System.out.println("finally中的代码执行了!");
		}

		System.out.println("程序结束了");
	}
}
