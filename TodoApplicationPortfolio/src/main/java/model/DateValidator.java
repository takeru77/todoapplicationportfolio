package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValidator {
	SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	public boolean isValidDate(String str) {
		// もし期限日を設定しなくても大丈夫にする設定
		// 期限日が設定されていない、空白文字しかないという場合も
		// ちゃんとマッチする。何故なら*は0文字以上という意味だから。
		if (str.matches("^\\s*$")) {
			return true;
		}
		
		try {
			DATE_FORMAT.setLenient(false);
			DATE_FORMAT.parse(str);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
}
