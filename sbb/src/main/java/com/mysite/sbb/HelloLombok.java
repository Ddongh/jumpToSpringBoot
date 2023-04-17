package com.mysite.sbb;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
//import lombok.Setter;

@Getter
@RequiredArgsConstructor
//@Setter

public class HelloLombok {
	
	private final String hello; //hello, lombok 속성에 final을 적용
	private final int lombok; //final은 한번 설정한 값을 변경할수 없게 하는 키워드
	
	public static void main(String[] args) {
		HelloLombok helloLombok = new HelloLombok("헬로", 5); //@RequiredArgsConstructor 애너테이션을 적용하면 해당 속성을 필요로하는 생성자가 롬복에 의해 자동으로 생성
//		helloLombok.setHello("헬로");
//		helloLombok.setLombok(5);
		
		System.out.println(helloLombok.getHello());
		System.out.println(helloLombok.getLombok());
	}
}
