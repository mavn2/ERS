package me.max;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ErsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErsApplication.class, args);
	}
//	@RequestMapping("/")
//		// Model session attr
//		// Default serve index
//	    public String index() {
//	    	System.out.println(1);
//	        return "index";
//	    }
//	    
//	    @RequestMapping("hi")
//	    public String home() {
//	    	System.out.println(2);
//	        return "index";
//	    }

}
