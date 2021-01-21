package com.example;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RestController
public class Application {

	//GET /camelize?original=this_is_a_thing => thisIsAThing
	//GET /camelize?original=this_is_a_thing&initialCap=true => ThisIsAThing
	@GetMapping("/camelize")
	public String getCamelize(@RequestParam String original, @RequestParam(defaultValue = "false") String initialCap) {
		String retStr = "";
		for(int i = 0; i < original.length(); i++){
			if(i == 0 && initialCap.equals("true")){
				retStr += Character.toUpperCase(original.charAt(0));
			} else if(original.charAt(i) == '_' && i+1 < original.length()){
				retStr += Character.toUpperCase(original.charAt(i+1));
				i++;
			} else {
				retStr += original.charAt(i);
			}
		}
		return retStr;
	}

	//GET /redact?original=A little of this and a little of that&badWord=little&badWord=this
	@GetMapping("/redact")
	public String getRedact(@RequestParam String original, @RequestParam List<String> badWord){
		String retStr = original;
		for(String bw : badWord){
			String redacted = bw.replaceAll(".", "*");
			retStr = retStr.replaceAll(bw,redacted);
		}
		return retStr;
	}

	//POST /encode
	//message=a little of this and a little of that&key=mcxrstunopqabyzdefghijklvw
	@PostMapping("/encode")
	public String postEncode(@RequestParam Map<String, String> body){
		String message = body.get("message");
		String key = body.get("key");
		String encodingMap = "abcdefghijklmnopqrstuvwxyz";
		String retStr = "";
		for(int i = 0; i < message.length(); i++){
			char c = message.charAt(i);
			if(c == ' '){
				retStr += ' ';
			} else {
				Integer index = encodingMap.indexOf(c);
				retStr += key.charAt(index);
			}
		}
		return retStr;
	}

	@PostMapping("/s/{find}/{replacement}")
	public String postSed(@PathVariable String find, @PathVariable String replacement, @RequestBody String body) {
		return body.replaceAll(find, replacement);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
