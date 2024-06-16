package kr.ac.kopo.trash.ui;

public class ExitUI extends BaseUI {

	@Override
	public void texecute() throws Exception {
		System.out.println("------------------------------------------");
		System.out.println("\n\t프로그램을 종료합니다\n");
		System.out.println("------------------------------------------");
		System.exit(0);		
	}		
}
