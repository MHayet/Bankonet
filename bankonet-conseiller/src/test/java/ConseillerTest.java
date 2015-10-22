import org.junit.Assert;
import org.junit.Test;

import com.bankonet.lib.Client;

public class ConseillerTest {

	@Test
	public void test() {
		Client client = new Client();
		client.getComptesList();
	}

}
