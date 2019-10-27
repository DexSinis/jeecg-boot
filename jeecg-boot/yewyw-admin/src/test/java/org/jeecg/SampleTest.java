package org.jeecg;

import java.util.List;

import javax.annotation.Resource;

import com.tls.tls_sigature.tls_sigature;
import org.jeecg.modules.demo.mock.MockController;
import org.jeecg.modules.demo.test.entity.JeecgDemo;
import org.jeecg.modules.demo.test.mapper.JeecgDemoMapper;
import org.jeecg.modules.demo.test.service.IJeecgDemoService;
import org.jeecg.modules.system.service.ISysDataLogService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {

	@Resource
	private JeecgDemoMapper jeecgDemoMapper;
	@Resource
	private IJeecgDemoService jeecgDemoService;
	@Resource
	private ISysDataLogService sysDataLogService;
	@Resource
	private MockController mock;

	@Test
	public void testSelect() {
		System.out.println(("----- selectAll method test ------"));
		List<JeecgDemo> userList = jeecgDemoMapper.selectList(null);
		Assert.assertEquals(5, userList.size());
		userList.forEach(System.out::println);
	}

	@Test
	public void testXmlSql() {
		System.out.println(("----- selectAll method test ------"));
		List<JeecgDemo> userList = jeecgDemoMapper.getDemoByName("Sandy12");
		userList.forEach(System.out::println);
	}

	/**
	 * 测试事务
	 */
	@Test
	public void testTran() {
		jeecgDemoService.testTran();
	}
	
	//author:lvdandan-----date：20190315---for:添加数据日志测试----
	/**
	 * 测试数据日志添加
	 */
	@Test
	public void testDataLogSave() {
		System.out.println(("----- datalog test ------"));
		String tableName = "jeecg_demo";
		String dataId = "4028ef81550c1a7901550c1cd6e70001";
		String dataContent = mock.sysDataLogJson();
		sysDataLogService.addDataLog(tableName, dataId, dataContent);
	}
	//author:lvdandan-----date：20190315---for:添加数据日志测试----


	@Test
	public void genAndVerify() {
		try {
			//Use pemfile keys to test
			String privStr = "-----BEGIN PRIVATE KEY-----\n" +
					"MIGHAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBG0wawIBAQQgkTfHxPa8YusG+va8\n" +
					"1CRztNQBOEr90TBEjlQBZ5d1Y0ChRANCAAS9isP/xLib7EZ1vS5OUy+gOsYBwees\n" +
					"PMDvWiTygPAUsGZv1PHLoa0ciqsElkO1fMGwNrzOKJx1Oo194Ri+SypV\n" +
					"-----END PRIVATE KEY-----";

			//change public pem string to public string
			String pubStr = "-----BEGIN PUBLIC KEY-----\n" +
					"MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEvYrD/8S4m+xGdb0uTlMvoDrGAcHn\n" +
					"rDzA71ok8oDwFLBmb9Txy6GtHIqrBJZDtXzBsDa8ziicdTqNfeEYvksqVQ==\n" +
					"-----END PUBLIC KEY-----";

			// generate signature
			tls_sigature.GenTLSSignatureResult result = tls_sigature.GenTLSSignatureEx(1400000000, "xiaojun", privStr);
			Assert.assertNotEquals(null, result);
			Assert.assertNotEquals(null, result.urlSig);
			Assert.assertNotEquals(0, result.urlSig.length());
			System.out.println(result.urlSig);

			// check signature
			tls_sigature.CheckTLSSignatureResult checkResult = tls_sigature.CheckTLSSignatureEx(result.urlSig, 1400000000, "xiaojun", pubStr);
			Assert.assertNotEquals(null, checkResult);
			Assert.assertTrue(checkResult.verifyResult);

			checkResult = tls_sigature.CheckTLSSignatureEx(result.urlSig, 1400000000, "xiaojun2", pubStr);
			Assert.assertNotEquals(null, checkResult);
			Assert.assertFalse( checkResult.verifyResult);


			// new interface generate signature
			result = tls_sigature.genSig(1400000000, "xiaojun", privStr);
			Assert.assertNotEquals(null, result);
			Assert.assertNotEquals(null, result.urlSig);
			Assert.assertNotEquals(0, result.urlSig.length());

			// check signature
			checkResult = tls_sigature.CheckTLSSignatureEx(result.urlSig, 1400000000, "xiaojun", pubStr);
			Assert.assertNotEquals(null, checkResult);
			Assert.assertTrue(checkResult.verifyResult);

			checkResult = tls_sigature.CheckTLSSignatureEx(result.urlSig, 1400000000, "xiaojun2", pubStr);
			Assert.assertNotEquals(null, checkResult);
			Assert.assertFalse( checkResult.verifyResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
