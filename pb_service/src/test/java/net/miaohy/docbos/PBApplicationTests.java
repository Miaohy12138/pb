package net.miaohy.docbos;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class PBApplicationTests {

	@Test
	public void contextLoads() {
	}

	public static void main(String[] args) {
		String password = "admin";

		String hexString = DigestUtils.md5Hex(password);
		System.out.println(hexString);
		String ids = "5,20,16,30,27,40,36,47,49";
		String  [] splits = ids.split(",");
		System.out.println(Arrays.toString(splits));
	}

}
