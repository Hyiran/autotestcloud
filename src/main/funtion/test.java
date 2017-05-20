package main.funtion;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
  String  to="03/01/2017";
  String  time = to.substring(6, 10)+"-"+to.substring(3, 5)+"-"+to.substring(0, 2);
  System.out.print(time);
	}

}
