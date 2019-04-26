package numbers;

public class Main {

	public static void main(String[] args)
	{
		Functions fun=new Functions();
		int NUMBER=5000;
		Binary bin=new Binary(NUMBER);

		//fun.faculty(new natural(NUMBER)).present();
		fun.fak(NUMBER).present();
		fun.fak(bin).present();
	}
}
