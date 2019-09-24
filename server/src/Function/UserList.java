package Function;

import Model.Service;
import javafx.concurrent.Task;
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */
public class UserList {
	/** 유저리스트 생성 **/
	public void set(String data) {						
		Sender s = new Sender();
		
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				
				String dataline = QueryBase.collectUser(data);
				
				// 송신 : 유저리스트목록
				s.sendItem(dataline);

				return null;
			}	
		};
		Service.executorService.submit(task);
	}
}
